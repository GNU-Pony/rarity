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
     * X11 event mask class
     */
    public static class EventMask
    {
	/**
	 * Constructor hiding
	 */
	private EventMask()
	{
	    /* do nothing */
	}
	
	
	
	public static final int KEY_PRESS             = 1 << 0;
	public static final int KEY_RELEASE           = 1 << 1;
	public static final int BUTTON_PRESS          = 1 << 2;
	public static final int BUTTON_RELEASE        = 1 << 3;
	public static final int ENTER_WINDOW          = 1 << 4;
	public static final int LEAVE_WINDOW          = 1 << 5;
	public static final int POINTER_MOTION        = 1 << 6;
	public static final int POINTER_MOTIONHINT    = 1 << 7;
	public static final int BUTTON_1_MOTION       = 1 << 8;
	public static final int BUTTON_2_MOTION       = 1 << 9;
	public static final int BUTTON_3_MOTION       = 1 << 10;
	public static final int BUTTON_4_MOTION       = 1 << 11;
	public static final int BUTTON_5_MOTION       = 1 << 12;
	public static final int BUTTON_MOTION         = 1 << 13;
	public static final int KEYMAP_STATE          = 1 << 14;
	public static final int EXPOSURE              = 1 << 15;
	public static final int VISIBILITY_CHANGE     = 1 << 16;
	public static final int STRUCTURE_NOTIFY      = 1 << 17;
	public static final int RESIZE_REDIRECT       = 1 << 18;
	public static final int SUBSTRUCTURE_NOTIFY   = 1 << 19;
	public static final int SUBSTRUCTURE_REDIRECT = 1 << 20;
	public static final int FOCUS_CHANGE          = 1 << 21;
	public static final int PROPERTY_CHANGE       = 1 << 22;
	public static final int COLORMAP_CHANGE       = 1 << 23;
	public static final int OWNER_GRAB_BUTTON     = 1 << 24;
    }
    
    
    
    /**
     * Constructor hiding
     */
    private X11()
    {
	/* do nothing */
    }
    
    
    
    /**
     * Open X display
     * 
     * @return  Whether the actions as successful
     */
    public static native boolean openDisplay();
    // Display* display = XOpenDisplay(0);
    // return display != 0;
    
    /**
     * Close X display
     */
    public static native void closeDisplay();
    // XCloseDisplay(display);
    
    /**
     * Gets the number of X screens in the opened display
     * 
     * @return  The number of X screens
     */
    public static native int screenCount();
    // return ScreenCount(display);
    
    /**
     * Gets the index of the default X screen on the display
     * 
     * @return  The number of X screens
     */
    public static native int defaultScreen();
    // return DefaultScreen(display);
    
    /**
     * Gets the width of a screen in pixels
     * 
     * @param   index  The index of the screen
     * @return         The width of the screen
     */
    public static native int screenWidth(int index);
    // return DisplayWidth(display, index);
    
    /**
     * Gets the height of a screen in pixels
     * 
     * @param   index  The index of the screen
     * @return         The height of the screen
     */
    public static native int screenHeight(int index);
    // return DisplayHeight(display, index);
    
    /**
     * Gets the width of a screen in millimetres
     * 
     * @param   index  The index of the screen
     * @return         The width of the screen, in millimetres
     */
    public static native int screenWidthMM(int index);
    // return DisplayWidthMM(display, index);
    
    /**
     * Gets the height of a screen in millimetres
     * 
     * @param   index  The index of the screen
     * @return         The height of the screen, in millimetres
     */
    public static native int screenHeightMM(int index);
    // return DisplayHeightMM(display, index);
    
    /**
     * Gets the dots per inch for a screen
     * 
     * @param   index  The index of the screen
     * @return         The height of the screen, in millimetres
     */
    public static int dotsPerInch(int index)
    {
	return (int)(25.4 * screenHeight(index) / screenHeightMM(index));
    }
    
    /**
     * Flushes synchronises the display without discarding events
     */
    public static native void sync();
    // XSync(display, 0);
    
    /**
     * Flushes the display
     */
    public static native void flush();
    // XFlush(display);
    
    /**
     * Perform actions to active a screen
     * 
     * @param  index  The index of the screen
     */
    public static native void activateScreen(int index);
    
    /**
     * Perform actions to deactive a screen
     * 
     * @param  index  The index of the screen
     */
    public static native void deactivateScreen(int index);
    
    /**
     * Select input for a screen's root window
     * 
     * @param  index   The index of the screen
     * @param  events  Event mask
     */
    public static native void selectRootInput(int index, int events);
    // XSelectInput(dpy, RootWindow(display, index), events);
    
}

