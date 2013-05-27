/**
 * rarity – An extensible tiling window manager
 * 
 * Copyright © 2013  Mattias Andrée (maandree@member.fsf.org)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
#include "Signals.h"

#include <signal.h>



/**
 * JNI environment variable passed to Java_rarity_Signals_startHandle
 */
static JNIEnv* _env;



static void signalCaptured(int signal);

#define S(i)  static void signalCaptured_##i() { signalCaptured(i); }
S(0) S(1) S(2) S(3) S(4) S(5) S(6) S(7) S(8) S(9) S(10)
S(11) S(12) S(13) S(14) S(15) S(16) S(17) S(18) S(19) S(20)
S(21) S(22) S(23) S(24) S(25) S(26) S(27) S(28) S(29) S(30) S(31)



/**
 * Start signal handling
 * 
 * @param  signal  The signal to handle
 */
void Java_rarity_Signals_startHandle(JNIEnv* env, jclass class, jint signal)
{
  (void) class;
  _env = env;
  
  struct sigaction act;
  if (!(sigaction(signal, 0, &act)))
    if (act.sa_handler != SIG_IGN)
      {
	/* Too bad C does not have lambda support */
        if      (signal ==  1)  act.sa_handler = signalCaptured_1;
        else if (signal ==  2)  act.sa_handler = signalCaptured_2;
        else if (signal ==  3)  act.sa_handler = signalCaptured_3;
        else if (signal ==  4)  act.sa_handler = signalCaptured_4;
        else if (signal ==  5)  act.sa_handler = signalCaptured_5;
        else if (signal ==  6)  act.sa_handler = signalCaptured_6;
        else if (signal ==  7)  act.sa_handler = signalCaptured_7;
        else if (signal ==  8)  act.sa_handler = signalCaptured_8;
        else if (signal ==  9)  act.sa_handler = signalCaptured_9;
        else if (signal == 10)  act.sa_handler = signalCaptured_10;
        else if (signal == 11)  act.sa_handler = signalCaptured_11;
        else if (signal == 12)  act.sa_handler = signalCaptured_12;
        else if (signal == 13)  act.sa_handler = signalCaptured_13;
        else if (signal == 14)  act.sa_handler = signalCaptured_14;
        else if (signal == 15)  act.sa_handler = signalCaptured_15;
        else if (signal == 16)  act.sa_handler = signalCaptured_16;
        else if (signal == 17)  act.sa_handler = signalCaptured_17;
        else if (signal == 18)  act.sa_handler = signalCaptured_18;
        else if (signal == 19)  act.sa_handler = signalCaptured_19;
        else if (signal == 20)  act.sa_handler = signalCaptured_20;
        else if (signal == 21)  act.sa_handler = signalCaptured_21;
        else if (signal == 22)  act.sa_handler = signalCaptured_22;
        else if (signal == 23)  act.sa_handler = signalCaptured_23;
        else if (signal == 24)  act.sa_handler = signalCaptured_24;
        else if (signal == 25)  act.sa_handler = signalCaptured_25;
        else if (signal == 26)  act.sa_handler = signalCaptured_26;
        else if (signal == 27)  act.sa_handler = signalCaptured_27;
        else if (signal == 28)  act.sa_handler = signalCaptured_28;
        else if (signal == 29)  act.sa_handler = signalCaptured_29;
        else if (signal == 30)  act.sa_handler = signalCaptured_30;
        else if (signal == 31)  act.sa_handler = signalCaptured_31;
	else                    act.sa_handler = signalCaptured_0;
        sigemptyset(&act.sa_mask);
        act.sa_flags = 0;
      }
}


/**
 * Signal up to the Java part that a signal has been captured
 * 
 * @param  signal  The signal
 */
void signalCaptured(int signal)
{
  jclass classSignals = (*_env)->FindClass(_env, "rarity.Signals");
  jmethodID method = (*_env)->GetStaticMethodID(_env, classSignals, "signal", "(I)V");
  (*_env)->CallStaticVoidMethod(_env, classSignals, method, signal);
}

