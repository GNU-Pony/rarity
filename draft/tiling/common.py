#!/usr/bin/env python
import sys
from tall import *
from grid import *
from video import *
from full import *
from split import *


def p(text):
    sys.stdout.buffer.write(text.encode('utf-8'));

def draw(x, y, w, h, index, sel):
    colour = '\033[00;0%i;%i%im' % ((index // 7) & 1, 4 if sel else 3, (index % 7) + 1)
    p(colour)
    p('\033[%i;%iH' % (y + 1, x + 1))
    if h == 1:
        p('─' * w)
    elif h == 2:
        if w == 1:
            p('│\033[D\033[B│')
        else:
            p('┌%s┐\033[%iD\033[B└%s┘' % ('─' * (w - 2), w, '─' * (w - 2)))
    else:
        if w == 1:
            p('│\033[D\033[B' * (h - 1) + '│')
        else:
            for i in range(h):
                if i == 0:
                    p('┌%s┐' % ('─' * (w - 2)))
                elif i == h - 1:
                    p('\033[%i;%iH└%s┘' % (y + 1 + i, x + 1, '─' * (w - 2)))
                else:
                    p('\033[%i;%iH│\033[m%s%s│' % (y + 1 + i, x + 1, ' ' * (w - 2), colour))
    p('\033[00m')


def mdiffY(m, x, w, y, h):
    mx = [m[0] - x, m[0] + m[2] - 1 - x, m[0] - x - w + 1, m[0] + m[2] - x - w]
    mx = min([_ ** 2 for _ in mx])
    my = [m[1] - y, m[1] + m[3] - 1 - y, m[1] - y - h + 1, m[1] + m[3] - y - h]
    my = min([_ ** 2 for _ in my])
    return (mx + my) * 2 + (0 if mx == 0 else 1)

def mdiffX(m, x, w, y, h):
    mx = [m[0] - x, m[0] + m[2] - 1 - x, m[0] - x - w + 1, m[0] + m[2] - x - w]
    mx = min([_ ** 2 for _ in mx])
    my = [m[1] - y, m[1] + m[3] - 1 - y, m[1] - y - h + 1, m[1] + m[3] - y - h]
    my = min([_ ** 2 for _ in my])
    return (mx + my) * 2 + (0 if my == 0 else 1)


def mainloop(algorithm, algorithmName):
    algorithms = {'Tall' : ('Grid', grid), 'Grid' : ('Video', video), 'Video' : ('Full', full), 'Full' : ('Split', split), 'Split' : ('Tall', tall)}
    
    modeState = [[0, 1, 2, 3]]
    
    scrY = int(sys.argv[1]) - 1
    scrX = int(sys.argv[2])
    
    count = 3
    indices = [i for i in range(count)]
    selected = 0
    monads = []
    
    masterPri = 0
    masterSec = 0
    masterTer = 0
    
    modes = []
    for i in range(3, len(sys.argv)):
        modes.append(sys.argv[i][0].upper() + sys.argv[i][1:].lower())
    
    c = None
    first = True
    while True:
        try:
            if first:
                first = False
            else:
                c = sys.stdin.read(1)
        except:
            break
        if c == ' ':
            (algorithmName, algorithm) = algorithms[algorithmName]
        elif c == 'j':
            if count > 0:
                selected = (selected + 1) % count
                while monads[selected][2] * monads[selected][3] == 0:
                    selected = (selected + 1) % count
        elif c == 'k':
            if count > 0:
                selected = (selected + count - 1) % count
                while monads[selected][2] * monads[selected][3] == 0:
                    selected = (selected + count - 1) % count
        elif c == 'J':
            if count > 0:
                indices = indices[:selected] + indices[selected + 1:] + [indices[selected]]
        elif c == 'K':
            if count > 0:
                indices = indices[:selected] + [indices[-1]] + indices[selected : -1]
        elif c == '\n':
             (indices[selected], indices[0]) = (indices[0], indices[selected])
             selected = 0
        elif c == '+':
            indices.append(count);
            count += 1
            if (count == 1) and (selected == -1):
                selected = 0
        elif c == '-':
            count = 0 if count == 0 else count -1
            if selected == count:
                selected -= 1
            del indices[count]
        elif c == 'q':
            break
        elif c == 'h':
            masterPri -= 1
        elif c == 'l':
            masterPri += 1
        elif c == 'H':
            masterSec -= 1
        elif c == 'L':
            masterSec += 1
        elif c == ',':
            masterTer -= 1
        elif c == '.':
            masterTer += 1
        elif c == 'n':
            masterPri = 0
            masterSec = 0
            masterTer = 0
        elif c == 'e':
            if len(modes) > 0:
                modes = modes[:-1]
        elif c == 'E':
            if len(modes) > 0:
                modes = modes[1:]
        elif c == 'r':
            modes.append('Rotate')
            modeState.append(rotate_state(modeState[-1]))
            (modeState, modes) = simplifyState(modeState, modes)
        elif c == 'R':
            modes.append('Rotate')
            modes.append('Rotate')
            modes.append('Rotate')
            modeState.append(rotate_state(modeState[-1]))
            modeState.append(rotate_state(modeState[-1]))
            modeState.append(rotate_state(modeState[-1]))
            (modeState, modes) = simplifyState(modeState, modes)
        elif c == 't':
            modes.append('Transpose')
            modeState.append(transpose_state(modeState[-1]))
            (modeState, modes) = simplifyState(modeState, modes)
        elif c == 'f':
            modes.append('Flip')
            modeState.append(flip_state(modeState[-1]))
            (modeState, modes) = simplifyState(modeState, modes)
        elif c == 'm':
            modes.append('Mirror')
            modeState.append(mirror_state(modeState[-1]))
            (modeState, modes) = simplifyState(modeState, modes)
        elif c == 'w':
            (x, y, w, h) = monads[selected]
            candidates = []
            i = 0
            for monad in monads:
                if monad[1] + monad[3] - 1 < y and monad[2] * monad[3] != 0:
                    candidates.append((monad, i))
                i += 1
            candidates.sort(key = lambda m : mdiffY(m[0], x, w, y, h))
            if len(candidates) > 0:
                selected = candidates[0][1]
        elif c == 's':
            (x, y, w, h) = monads[selected]
            candidates = []
            i = 0
            y += h - 1
            for monad in monads:
                if monad[1] > y and monad[2] * monad[3] != 0:
                    candidates.append((monad, i))
                i += 1
            y -= h - 1
            candidates.sort(key = lambda m : mdiffY(m[0], x, w, y, h))
            if len(candidates) > 0:
                selected = candidates[0][1]
        elif c == 'a':
            (x, y, w, h) = monads[selected]
            candidates = []
            i = 0
            for monad in monads:
                if monad[0] + monad[2] - 1 < x and monad[2] * monad[3] != 0:
                    candidates.append((monad, i))
                i += 1
            candidates.sort(key = lambda m : mdiffX(m[0], x, w, y, h))
            if len(candidates) > 0:
                selected = candidates[0][1]
        elif c == 'd':
            (x, y, w, h) = monads[selected]
            candidates = []
            i = 0
            x += w - 1
            for monad in monads:
                if monad[0] > x and monad[2] * monad[3] != 0:
                    candidates.append((monad, i))
                i += 1
            x -= w - 1
            candidates.sort(key = lambda m : mdiffX(m[0], x, w, y, h))
            if len(candidates) > 0:
                selected = candidates[0][1]
        elif c is not None:
            continue
        
        for i in range(len(modes)):
            if modes[len(modes) - i - 1] == 'Transpose':
                (scrX, scrY) = transpose_pre(scrX, scrY)
            if modes[len(modes) - i - 1] == 'Rotate':
                (scrX, scrY) = rotate_pre(scrX, scrY)
        monads = algorithm(scrX, scrY, count, masterPri, masterSec, masterTer, selected)
        for i in range(len(modes)):
            if modes[i] == 'Mirror':
                mirror(monads, scrX, scrY)
            elif modes[i] == 'Flip':
                flip(monads, scrX, scrY)
            elif modes[i] == 'Transpose':
                transpose(monads, scrX, scrY)
                (scrX, scrY) = transpose_post(scrX, scrY)
            elif modes[i] == 'Rotate':
                rotate(monads, scrX, scrY)
                (scrX, scrY) = rotate_post(scrX, scrY)
        
        p('\033[H\033[2J')
        p('\033[H%s' % ' '.join([_ for _ in reversed(modes)] + [algorithmName]))
        for i in range(count):
            monad = monads[i]
            if monad[2] * monad[3] != 0:
                draw(monad[0], monad[1] + 1, monad[2], monad[3], indices[i], selected == i)
        sys.stdout.buffer.flush()



def simplifyState(state, modes):
    use = 0
    (a, b, c, d) = state[-1]
    while True:
        if state[use][0] == a and state[use][1] == b and state[use][2] == c and state[use][3] == d:
            break
        use += 1
    return (state[:use + 1], modes[:use])



def mirror(monads, scrX, scrY):
    for monad in monads:
        monad[0] = scrX - monad[0] - monad[2]
    return monads

def mirror_state(state):
    return [state[1], state[0], state[3], state[2]]


def flip(monads, scrX, scrY):
    for monad in monads:
        monad[1] = scrY - monad[1] - monad[3]
    return monads

def flip_state(state):
    return [state[2], state[3], state[0], state[1]]


def transpose(monads, scrX, scrY):
    for monad in monads:
        (monad[1], monad[0]) = (monad[0], monad[1])
        (monad[3], monad[2]) = (monad[2], monad[3])
    return monads

def transpose_pre(scrX, scrY):
    return (scrY, scrX)

def transpose_post(scrX, scrY):
    return (scrY, scrX)

def transpose_state(state):
    return [state[0], state[2], state[1], state[3]]


def rotate(monads, scrX, scrY):
    transpose(monads, scrX, scrY)
    mirror(monads, scrY, scrX)
    return monads

def rotate_pre(scrX, scrY):
    return (scrY, scrX)

def rotate_post(scrX, scrY):
    return (scrY, scrX)

def rotate_state(state):
    return [state[2], state[0], state[3], state[1]]

