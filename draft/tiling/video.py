#!/usr/bin/env python
from common import *


def video(scrX, scrY, count, masterPri, masterSec, masterTer, selected):
    lower = count - 1
    mx = min(max(1, scrX + (0 if masterTer == 0 else masterSec)), scrX)
    right = 0 if masterSec >= 0 else min(max(0, masterTer), lower)
    lower -= right
    my = min(max(0, scrY * 3 // 4 + masterPri), scrY) if lower > 0 else scrY
    monads = [None] * count
    for i in range(count):
        if i == 0:
            monads[i] = [0, 0, mx, my]
        elif i <= right:
            h = my / right
            y = int((i - 1) * h)
            monads[i] = [mx, y, scrX - mx, int(i * h - y)]
        else:
            w = scrX / lower
            x = int((i - 1 - right) * w)
            monads[i] = [x, my, int((i - right) * w - x), scrY - my]
    return monads


if __name__ == '__main__':
    mainloop(video, 'Video')

