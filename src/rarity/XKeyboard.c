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
#include "XKeyboard.h"
#include "global.h"



/**
 * Gets the key symbol from a key code
 * 
 * @param   keycode    The key code
 * @param   modifiers  The modifers
 * @return             The key symbol
 */
jint Java_rarity_XKeyboard_toKeySymbol(JNIEnv* env, jclass class, jbyte keycode, jint group, jint modifiers)
{
  (void) env;
  (void) class;
  
  return (jint)XkbKeycodeToKeysym(display, (KeyCode)keycode, (unsigned int)group, (unsigned int)modifiers);
}


/**
 * Gets a key code from a key symbol
 * 
 * @param   keysym  The key symbol
 * @return          The key code
 */
jbyte Java_rarity_XKeyboard_toKeyCode(JNIEnv* env, jclass class, jint keysym)
{
  (void) env;
  (void) class;
  
  return (jbyte)XKeysymToKeycode(display, (KeySym)keysym);
}


/**
 * Gets the lower case key symbol from a key symbol
 * 
 * @param   keysym  The key symbol
 * @return          The lower case key symbol
 */
jint Java_rarity_XKeyboard_toLowerKeySymbol(JNIEnv* env, jclass class, jint keysym)
{
  (void) env;
  (void) class;
  
  KeySym lower, upper;
  XConvertCase(keysym, &lower, &upper);
  return (jint)lower;
}

  
/**
 * Gets the lower case key symbol from a key symbol
 * 
 * @param   keysym  The key symbol
 * @return          The lower case key symbol
 */
jint Java_rarity_XKeyboard_toUpperKeySymbol(JNIEnv* env, jclass class, jint keysym)
{
  (void) env;
  (void) class;
  
  KeySym lower, upper;
  XConvertCase(keysym, &lower, &upper);
  return (jint)upper;
}

