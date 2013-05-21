#!/usr/bin/env python
from common import *


def split(scrX, scrY, count, masterPri, masterSec, masterTer, selected):
    cols = max(1, min(count, 2 + max(masterTer, -masterTer)))
    mx = min(max(1, scrX // cols + masterPri), scrX)
    monads = [None] * count
    for i in range(count):
        if i == 0:
            monads[i] = [0, 0, mx, scrY]
        elif i < cols:
            x1 = int((i - 1) * (scrX - mx) / (cols - 1))
            x2 = int(i * (scrX - mx) / (cols - 1) - x1)
            monads[i] = [x1 + mx, 0, x2, scrY]
        else:
            monads[i] = [0, 0, 0, 0]
    return monads


if __name__ == '__main__':
    mainloop(split, 'Split')

