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
package rarity;


/**
 * Xinerama X11 extension interaction class
 * 
 * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class Xinerama
{
    /**
     * Constructor hiding
     */
    private Xinerama()
    {
	/* do nothing */
    }
    
    
    
    /**
     * Initialise Xinerama
     * 
     * @return  Whether Xinerama support is enable
     */
    public static native boolean initialise();
    // int have_xinerama = 0;
    // static XineramaScreenInfo* xinerama_screens = 0;
    // int xinerama_screen_count = 0;
    // 
    // int major, minor;
    // if (!XineramaQueryExtension(display, &major, &minor))  return 0;
    // if (!XineramaQueryVersion(display, &major, &minor) != Success)  return 0;
    // if (major != 1) return 0;
    // if (!XineramaIsActive(display)) return 0;
    // xinerama_screens = XineramaQueryScreens(display, &xinerama_screen_count);
    // if ((xinerama_screens == NULL) || (xinerama_screen_count < 2))  return 0;
    // 
    // have_xinerama = 1;
    // return 1;
    
    /**
     * Terminate Xinerama
     */
    public static native void terminate();
    // if ((xinerama_screens))
    //   XFree(xinerama_screens);
    
    /**
     * Gets the number of Xinerama screens
     * 
     * @return  The number of Xinerama screens
     */
    public static native int screenCount();
    // return xinerama_screen_count;
    
    /**
     * Gets the left position of the a Xinerama screen
     * 
     * @param   index  The index of the screen
     * @return         The left position of the screen
     */
    public static native int screenLeft(int index);
    // return xinerama_screens[index].x_org;
    
    /**
     * Gets the top position of the a Xinerama screen
     * 
     * @param   index  The index of the screen
     * @return         The top position of the screen
     */
    public static native int screenTop(int index);
    // return xinerama_screens[index].y_org;
    
    /**
     * Gets the width of the a Xinerama screen
     * 
     * @param   index  The index of the screen
     * @return         The width of the screen
     */
    public static native int screenWidth(int index);
    // return xinerama_screens[index].width;
    
    /**
     * Gets the height of the a Xinerama screen
     * 
     * @param   index  The index of the screen
     * @return         The height of the screen
     */
    public static native int screenHeight(int index);
    // return xinerama_screens[index].height;
    
}

