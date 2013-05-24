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
 * X11 interaction class
 * 
 * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class X11
{
    /**
     * Open X display
     * 
     * @return  Whether the actions as successful
     */
    public native boolean openDisplay();
    // Display* display = XOpenDisplay(0);
    // return display != 0;
    
    /**
     * Close X display
     */
    public native void closeDisplay();
    // XCloseDisplay(display);
    
    /**
     * Gets the number of X screens in the opened display
     * 
     * @return  The number of X screens
     */
    public native int screenCount();
    // return ScreenCount(display);
    
    /**
     * Gets the index of the default X screen on the display
     * 
     * @return  The number of X screens
     */
    public native int defaultScreen();
    // return DisplayScreen(display);
    
    /**
     * Gets the width of a screen in pixels
     * 
     * @param   index  The index of the screen
     * @return         The width of the screen
     */
    public native int screenWidth(int index);
    // return DisplayScreen(display, index);
    
    /**
     * Gets the height of a screen in pixels
     * 
     * @param   index  The index of the screen
     * @return         The height of the screen
     */
    public native int screenHeight(int index);
    // return DisplayScreen(display, index);
    
    /**
     * Gets the width of a screen in millimetres
     * 
     * @param   index  The index of the screen
     * @return         The width of the screen, in millimetres
     */
    public native int screenWidthMM(int index);
    // return DisplayScreen(display, index);
    
    /**
     * Gets the height of a screen in millimetres
     * 
     * @param   index  The index of the screen
     * @return         The height of the screen, in millimetres
     */
    public native int screenHeightMM(int index);
    // return DisplayScreen(display, index);
    
    /**
     * Gets the dots per inch for a screen
     * 
     * @param   index  The index of the screen
     * @return         The height of the screen, in millimetres
     */
    public int dotsPerInch(int index)
    {
	return (int)(25.4 * ScreenHeight(index) / ScreenHeightMM(index));
    }
    
    /**
     * Synchronised the display without discarding events
     */
    public native void sync();
    // XSync(display, 0);
    
}
