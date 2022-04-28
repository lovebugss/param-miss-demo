#!/usr/bin/python
# -*- coding: UTF-8 -*-
import requests
import asyncio


async def send_post(url):
    data = {
        "appId": "xxxxxxx",
    }
    header = {
        "content-type": "application/x-www-form-urlencoded",
        "Accept-Encoding": "gzip, deflate"
    }
    res = requests.post(url, data=data, headers=header)
    status_code = res.status_code
    # print(res.text)
    if status_code == 400:
        resp = res.text
        print(status_code, resp)
    return status_code


async def handler(loop):
    tasks = []
    domain = "http://localhost:8888"
    url_list = [
        "/api/user/update",
        "/api/user/delete"

    ]
    for i in range(1, 200000):
        tasks.append(loop.create_task(send_post(domain + url_list[i % len(url_list)])))
    result = []
    for task in asyncio.as_completed(tasks):
        resp = await task
        result.append(resp)
    return result


def main():
    loop = asyncio.new_event_loop()
    asyncio.set_event_loop(loop)
    result = loop.run_until_complete(handler(loop))
    loop.close()
    # print(result)


if __name__ == '__main__':
    main()
