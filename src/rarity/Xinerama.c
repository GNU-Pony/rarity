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
#include "Xinerama.h"



#ifndef NO_XINERAMA
#  ifdef HAVE_X11_EXTENSIONS_XINERAMA_H
#    include <X11/extensions/Xinerama.h>
#    define XINERAMA
#  endif
#endif



/**
 * Whether we have Xinerama enabled
 */
int have_xinerama = 0;

/**
 * Our Xinerama screens
 */
static XineramaScreenInfo* xinerama_screens = 0;

/**
 * The number of Xinerama screens we have
 */
int xinerama_screen_count = 0;



/**
 * Initialise Xinerama
 * 
 * @return  Whether Xinerama support is enable
 */
jboolean Java_rarity_Xinerama_initialise(JNIEnv* env, jclass class);
{
  (void) env;
  (void) class;
  
  #ifdef XINERAMA
    int major, minor;
    if (!XineramaQueryExtension(display, &major, &minor))
      return 0;
    if (!XineramaQueryVersion(display, &major, &minor) != Success)
      return 0;
    if (major != 1)
      return 0;
    if (!XineramaIsActive(display))
      return 0;
    xinerama_screens = XineramaQueryScreens(display, &xinerama_screen_count);
    if ((xinerama_screens == 0) || (xinerama_screen_count < 2))
      return 0;
    
    have_xinerama = 1;
    return 1;
  #else
    return 0;
  #endif
}


/**
 * Terminate Xinerama
 */
void Java_rarity_Xinerama_terminate(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  #ifdef XINERAMA
    if ((xinerama_screens))
      XFree(xinerama_screens);
  #endif
}


/**
 * Gets the number of Xinerama screens
 * 
 * @return  The number of Xinerama screens
 */
jint Java_rarity_Xinerama_screenCount(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return xinerama_screen_count;
} 


/**
 * Gets the left position of the a Xinerama screen
 * 
 * @param   index  The index of the screen
 * @return         The left position of the screen
 */
jint Java_rarity_Xinerama_screenLeft(JNIEnv* env, jclass class, jint index)
{
  (void) env;
  (void) class;
  #ifdef XINERAMA
    return xinerama_screens[index].x_org;
  #else
    (void) index;
    return 0;
  #endif
}


/**
 * Gets the top position of the a Xinerama screen
 * 
 * @param   index  The index of the screen
 * @return         The top position of the screen
 */
jint Java_rarity_Xinerama_screenTop(JNIEnv* env, jclass class, jint index)
{
  (void) env;
  (void) class;
  #ifdef XINERAMA
    return xinerama_screens[index].y_org;
  #else
    (void) index;
    return 0;
  #endif
} 


/**
 * Gets the width of the a Xinerama screen
 * 
 * @param   index  The index of the screen
 * @return         The width of the screen
 */
jint Java_rarity_Xinerama_screenWidth(JNIEnv* env, jclass class, jint index)
{
  (void) env;
  (void) class;
  #ifdef XINERAMA
    return xinerama_screens[index].width;
  #else
    (void) index;
    return 0;
  #endif
}


/**
 * Gets the height of the a Xinerama screen
 * 
 * @param   index  The index of the screen
 * @return         The height of the screen
 */
jint Java_rarity_Xinerama_screenHeight(JNIEnv* env, jclass class, jint index)
{
  (void) env;
  (void) class;
  #ifdef XINERAMA
    return xinerama_screens[index].height;
  #else
    (void) index;
    return 0;
  #endif
}

