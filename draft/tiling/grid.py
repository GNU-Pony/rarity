#!/usr/bin/env python
from common import *


def grid(scrX, scrY, count, masterPri, masterSec, masterTer, selected):
    if count == 0:
        return []
    cols = min(max(1, int(count ** 0.5 + 0.5) + masterTer), count)
    monads = [None] * count
    rows = [0] * cols
    for i in range(count):
        rows[cols - (i % cols) - 1] += 1
    for i in range(count):
        (col, row) = (cols - (i % cols) - 1, i // cols)
        row = rows[col] - row - 1
        (x, y) = (col * scrX // cols, row * scrY // rows[col])
        monads[i] = [x, y, (col + 1) * scrX // cols - x, (row + 1) * scrY // rows[col] - y]
    return monads


if __name__ == '__main__':
    mainloop(grid, 'Grid')

