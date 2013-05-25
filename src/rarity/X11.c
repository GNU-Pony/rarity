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
#include "X11.h"

#include <X11/Xlib.h>


/**
 * The X11 display we are using
 */
Display* display = 0;



/**
 * Open X display
 * 
 * @return  Whether the actions as successful
 */
jboolean Java_rarity_X11_openDisplay(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  display = XOpenDisplay(0);
  return display != 0;
}


/**
 * Close X display
 */
void Java_rarity_X11_closeDisplay(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  XCloseDisplay(display);
}


/**
 * Gets the number of X screens in the opened display
 * 
 * @return  The number of X screens
 */
jint Java_rarity_X11_screenCount(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return ScreenCount(display);
}


/**
 * Gets the index of the default X screen on the display
 * 
 * @return  The number of X screens
 */
jint Java_rarity_X11_defaultScreen(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return DefaultScreen(display);
}


/**
 * Gets the width of a screen in pixels
 * 
 * @param   index  The index of the screen
 * @return         The width of the screen
 */
jint Java_rarity_X11_screenWidth(JNIEnv* env, jclass class, jint index)
{
  (void) env;
  (void) class;
  return DisplayWidth(display, index);
}


/**
 * Gets the height of a screen in pixels
 * 
 * @param   index  The index of the screen
 * @return         The height of the screen
 */
jint Java_rarity_X11_screenHeight(JNIEnv* env, jclass class, jint index)
{
  (void) env;
  (void) class;
  return DisplayHeight(display, index);
}


/**
 * Gets the width of a screen in millimetres
 * 
 * @param   index  The index of the screen
 * @return         The width of the screen, in millimetres
 */
jint Java_rarity_X11_screenWidthMM(JNIEnv* env, jclass class, jint index)
{
  (void) env;
  (void) class;
  return DisplayWidthMM(display, index);
}


/**
 * Gets the height of a screen in millimetres
 * 
 * @param   index  The index of the screen
 * @return         The height of the screen, in millimetres
 */
jint Java_rarity_X11_screenHeightMM(JNIEnv* env, jclass class, jint index)
{
  (void) env;
  (void) class;
  return DisplayHeightMM(display, index);
}


/**
 * Flushes synchronises the display without discarding events
 */
void Java_rarity_X11_sync(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  XSync(display, 0);
}


/**
 * Flushes the display
 */
void Java_rarity_X11_flush(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  XFlush(display);
}


/**
 * Perform actions to active a screen
 * 
 * @param  index  The index of the screen
 */
void Java_rarity_X11_activateScreen(JNIEnv* env, jclass class, jint index)
{
  (void) env;
  (void) class;
}


/**
 * Perform actions to deactive a screen
 * 
 * @param  index  The index of the screen
 */
void Java_rarity_X11_deactivateScreen(JNIEnv* env, jclass class, jint index)
{
  (void) env;
  (void) class;
}

