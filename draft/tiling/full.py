#!/usr/bin/env python
from common import *


def full(scrX, scrY, count, masterPri, masterSec, masterTer, selected):
    monads = [None] * count
    for i in range(count):
        if i == selected:
            w = min(max(0, scrX + masterPri), scrX)
            h = min(max(0, scrY + masterSec), scrY)
            monads[i] = [0, min(max(0, masterTer), scrY - h), w, h]
        else:
            monads[i] = [0, 0, 0, 0]
    return monads


if __name__ == '__main__':
    mainloop(full, 'Full')

