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
#include "Window.h"
#include "global.h"



/**
 * Flush position changes to the display
 * 
 * @param  address  The memory address of the window
 * @param  x        The left X-axis position of the window
 * @param  y        The top Y-axis position of the window
 */
void Java_rarity_Window_xMoveWindow(JNIEnv* env, jclass class, jlong address, jint x, jint y)
{
  (void) env;
  (void) class;
  
  XMoveWindow(display, (Window)(long)address, x, y);
  
  // TODO: XMoveWindow can generate a BadWindow error.
}


/**
 * Flush size changes to the display
 * 
 * @param  address  The memory address of the window
 * @param  width    The width of the window
 * @param  height   The height of the window
 */
void Java_rarity_Window_xResizeWindow(JNIEnv* env, jclass class, jlong address, jint width, jint height)
{
  (void) env;
  (void) class;
  
  XResizeWindow(display, (Window)(long)address, width, height);
  
  // TODO: XResizeWindow can generate BadValue and BadWindow errors.
}


/**
 * Flush position and size changes to the display
 * 
 * @param  address  The memory address of the window
 * @param  x        The left X-axis position of the window
 * @param  y        The top Y-axis position of the window
 * @param  width    The width of the window
 * @param  height   The height of the window
 */
void Java_rarity_Window_xMoveResizeWindow(JNIEnv* env, jclass class, jlong address, jint x, jint y, jint width, jint height)
{
  (void) env;
  (void) class;
  
  XMoveResizeWindow(display, (Window)(long)address, x, y, width, height);
  
  // TODO: XMoveResizeWindow can generate BadValue and BadWindow errors.
}

