#! /usr/bin/python3

import argparse
import contextlib
import json
import jwt
import requests
import subprocess
import sys
import time
import pprint
from pathlib import Path


@contextlib.contextmanager
def open_file(name):
    if name == "-":
        yield sys.stdin.buffer
    else:
        yield open(name, "rb")


def main():
    parser = argparse.ArgumentParser(
        description="Add GitHub token remote for application"
    )

    parser.add_argument(
        "--path",
        type=Path,
        help="Local repository path",
        required=True,
    )
    parser.add_argument(
        "--repo",
        metavar="SERVER/OWNER/REPO",
        help="Repository path, without protocol. E.g. 'github.com/OWNER/REPO'",
        required=True,
    )
    parser.add_argument(
        "--branch",
        metavar="BRANCH",
        help="Source branch name",
        required=True,
    )
    parser.add_argument(
        "--id",
        type=int,
        help="GitHub application ID",
        required=True,
    )
    parser.add_argument(
        "--key",
        help="Private key file for signing JWT token, ('-' for stdin)",
        required=True,
    )
    parser.add_argument(
        "--pull",
        metavar="BRANCH",
        help="Create pull request targeting BRANCH from source branch",
    )
    parser.add_argument(
        "--expire",
        metavar="MINUTES",
        type=int,
        default=10,
        help="Expiration time (minutes)",
    )
    parser.add_argument(
        "--title",
        help="Pull request title",
    )

    args = parser.parse_args()

    server, owner, repo = args.repo.split("/", 2)

    now = int(time.time())

    with open_file(args.key) as f:
        jwt_token = jwt.encode(
            {
                "iat": now - 60,
                "exp": now + (args.expire * 60),
                "iss": str(args.id),
            },
            f.read(),
            algorithm="RS256",
        )

    r = requests.get(
        f"https://api.{server}/app/installations",
        headers={
            "Authorization": f"Bearer {jwt_token}",
            "Accept": "application/vnd.github.v3+json",
        },
    )

    if r.status_code != 200:
        print(r.text)
        return 1

    installations = r.json()

    for install in installations:
        if install["account"]["login"] == owner:
            install_id = install["id"]
            break
    else:
        print(f"No installation for {owner} found")
        return 1

    r = requests.post(
        f"https://api.{server}/app/installations/{install_id}/access_tokens",
        headers={
            "Authorization": f"Bearer {jwt_token}",
            "Accept": "application/vnd.github.v3+json",
        },
    )

    if r.status_code != 201:
        print(r.text)
        return 1

    token = r.json()["token"]
    push_url = f"https://x-access-token:{token}@{server}/{owner}/{repo}"

    subprocess.run(
        [
            "git",
            "-C",
            args.path.absolute(),
            "push",
            "-f",
            push_url,
            f"HEAD:refs/heads/{args.branch}",
        ],
        check=True,
    )

    if args.pull:
        pulls_url = f"https://api.{server}/repos/{owner}/{repo}/pulls"
        r = requests.get(
            pulls_url,
            headers={
                "Authorization": f"token {token}",
                "Accept": "application/vnd.github.v3+json",
            },
        )

        if r.status_code != 200:
            print(r.text)
            return 1

        for p in r.json():
            if (
                p["state"] == "open"
                and p["head"]["ref"] == args.branch
                and p["head"]["repo"]["full_name"] == f"{owner}/{repo}"
                and p["base"]["ref"] == args.pull
                and p["base"]["repo"]["full_name"] == f"{owner}/{repo}"
            ):
                print(f"Pull request for {args.branch} -> {args.pull} already open")
                return 0

        if args.title:
            title = args.title
        else:
            title = subprocess.run(
                [
                    "git",
                    "-C",
                    args.path.absolute(),
                    "show",
                    "--pretty=format:%s",
                    "-s",
                    "HEAD",
                ],
                stdout=subprocess.PIPE,
                check=True,
            ).stdout.decode("utf-8")

        r = requests.post(
            pulls_url,
            headers={
                "Authorization": f"token {token}",
                "Accept": "application/vnd.github.v3+json",
            },
            data=json.dumps(
                {
                    "title": title,
                    "head": args.branch,
                    "base": args.pull,
                }
            ),
        )

        if r.status_code != 201:
            print(r.text)
            return 1

    return 0


if __name__ == "__main__":
    sys.exit(main())
