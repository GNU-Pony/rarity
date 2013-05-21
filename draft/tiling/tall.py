#!/usr/bin/env python
from common import *


def tall(scrX, scrY, count, masterPri, masterSec, masterTer, selected):
    t = min(max(-1, -masterTer), count - 1)
    tx = 1 if t + 1 == count else 2
    my = 0 if count == 0 or t == -1 else scrY // (1 + t) + masterSec
    monads = [None] * count
    for i in range(count):
        if (i == 0) and (i <= t):
            monads[i] = [0, 0, scrX // tx + masterPri, my]
        elif i <= t:
            h = (scrY - my) / t
            y = int((i - 1) * h)
            monads[i] = [0, my + y, scrX // tx + masterPri, int(i * h - y)]
        else:
            h = scrY / (count - t - 1)
            y = int((i - t - 1) * h)
            x = 0 if t == -1 else scrX // 2 + masterPri
            monads[i] = [x, y, scrX - x, int((i - t) * h - y)]
    return monads


if __name__ == '__main__':
    mainloop(tall, 'Tall')

