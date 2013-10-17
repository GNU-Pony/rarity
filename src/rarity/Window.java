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

import java.util.Vector;


/**
 * Window instances in the environment
 * 
 * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class Window extends PropertyBase
{
    /**
     * The window's width
     */
    public static final String WIDTH = "width";
    
    /**
     * The window's height
     */
    public static final String HEIGHT = "height";
    
    /**
     * The window's offset from the left edge of the primary screen
     */
    public static final String LEFT = "left";
    
    /**
     * The window's offset from the upper edge of the primary screen
     */
    public static final String TOP = "top";
    
    /**
     * Whether the window is visible
     */
    public static final String VISIBLE = "visible";
    
    
    /**
     * Replace value
     */
    public static final int MODE_REPLACE = 0; // PropModeReplace (value does not reflect native value)
    
    /**
     * Prepend to value
     */
    public static final int MODE_PREPEND = 1; // PropModePrepend (value does not reflect native value)
    
    /**
     * Append to value
     */
    public static final int MODE_APPEND = 2; // PropModeAppend (value does not reflect native value)
    
    
    
    /**
     * Constructor
     * 
     * @param  left     The left X-axis position of the window
     * @param  top      The top Y-axis position of the window
     * @param  width    The window's width
     * @param  height   The window's height
     * @parma  pointer  Pointer to the window object in native code
     */
    protected Window(final int left, final int top, final int width, final int height, final long pointer)
    {
	this.set(LEFT, left);
	this.set(TOP, top);
	this.set(WIDTH, width);
	this.set(HEIGHT, height);
	this.set(VISIBLE, true);
	this.pointer = pointer;
    }
    
    
    
    /**
     * All windows in index order
     */
    static Vector<Window> windows = new Vector<Window>();
    
    /**
     * Pointer to the window object in native code
     */
    public final long pointer;
    
    
    
    /**
     * Blackboard message for {@link Window} property updates
     * 
     * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
     */
    public static class PropertyMessage implements Blackboard.BlackboardMessage
    {
	/**
	 * Constructor
	 * 
	 * @param  window    The updated window
	 * @param  property  The updated property
	 */
	protected PropertyMessage(final Window window, final String property)
	{
	    this.window = window;
	    this.property = property;
	    int i = 0;
	    for (final Window win : Window.windows)
		if (win == window)
		    break;
		else
		    i++;
	    this.index = i;
	}
	
	
	
	/**
	 * The updated window
	 */
	public final Window window;
	
	/**
	 * The index of the updated window
	 */
	public final int index;
	
	/**
	 * The updated property
	 */
	public final String property;
	
    }
    
    
    /**
     * Blackboard message for {@link Window} existance updates
     * 
     * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
     */
    public static class ExistanceMessage implements Blackboard.BlackboardMessage
    {
	/**
	 * A new window has been added
	 */
	public static final int ADDED = 0;
	
	/**
	 * A window is being removed
	 */
	public static final int REMOVING = 1;
	
	/**
	 * A window has been removed
	 */
	public static final int REMOVED = 2;
	
	
	
	/**
	 * Constructor
	 * 
	 * @param  action  Either {@link #ADDED}, {@link #REMOVING} or {@link #REMOVED}
	 * @param  index   The index of the window in question
	 */
	protected ExistanceMessage(final int action, final int index)
	{
	    this.action = action;
	    this.index = index;
	}
	
	
	
	/**
	 * Either {@link #ADDED}, {@link #REMOVING} or {@link #REMOVED}
	 */
	public final int action;
	
	/**
	 * The index of the window in question
	 */
	public final int index;
	
    }
    
    
    
    /**
     * Gets a window by its index
     * 
     * @param   index  The window's index
     * @return         The window
     */
    public static Window getWindow(final int index)
    {
	synchronized (windows)
	{   return windows.get(index);
	}
    }
    
    /**
     * Gets the number of windows in the environment
     * 
     * @return  The number of windows in the environment
     */
    public static int getWindowCount()
    {
	synchronized (windows)
	{   return windows.size();
	}
    }
    
    /**
     * Gets a window by its address
     * 
     * @param   address  The window's address
     * @return           The window, {@code null} if it does not mapped
     */
    public static Window getByAddress(final long address)
    {
	/* We are going to assume that there are not so many windows that a mash map should be used */
	synchronized (windows)
	{   for (final Window window : windows)
		if (window.pointer == address)
		    return window;
	    return null;
	}
    }
    
    /**
     * Unlist a window
     * 
     * @param  window  The windo to unlist
     */
    public static void removeWindow(final Window window)
    {
	synchronized (windows)
	{   int index = 0;
	    for (final Window canidate : windows)
		if (canidate == window)
		    break;
		else
		    index++;
	    if (index == windows.size())
		return;
	    final Blackboard blackboard = Blackboard.getInstance(Window.class);
	    blackboard.broadcastMessage(new Window.ExistanceMessage(Window.ExistanceMessage.REMOVING, index));
	    windows.remove(index);
	    blackboard.broadcastMessage(new Window.ExistanceMessage(Window.ExistanceMessage.REMOVED, index));
	}
    }
    
    
    /**
     * {@inheritDoc}
     */
    protected void updated(final String property, final Object value)
    {
	Blackboard.getInstance(Window.class).broadcastMessage(new Window.PropertyMessage(this, property));
    }
    
    
    /**
     * Flush position changes to the display
     */
    public void updatePosition()
    {
	xMoveWindow(this.pointer, getInteger(LEFT), getInteger(TOP));
    }
    
    /**
     * Flush size changes to the display
     */
    public void updateSize()
    {
	xResizeWindow(this.pointer, getInteger(WIDTH), getInteger(HEIGHT));
    }
    
    /**
     * Flush position and size changes to the display
     */
    public void updateArea()
    {
	xMoveResizeWindow(this.pointer, getInteger(LEFT), getInteger(TOP), getInteger(WIDTH), getInteger(HEIGHT));
    }
    
    
    /**
     * Flush position changes to the display
     * 
     * @param  address  The memory address of the window
     * @param  x        The left X-axis position of the window
     * @param  y        The top Y-axis position of the window
     */
    private static native void xMoveWindow(long address, int x, int y);
    
    /**
     * Flush size changes to the display
     * 
     * @param  address  The memory address of the window
     * @param  width    The width of the window
     * @param  height   The height of the window
     */
    private static native void xResizeWindow(long address, int width, int height);
    
    /**
     * Flush position and size changes to the display
     * 
     * @param  address  The memory address of the window
     * @param  x        The left X-axis position of the window
     * @param  y        The top Y-axis position of the window
     * @param  width    The width of the window
     * @param  height   The height of the window
     */
    private static native void xMoveResizeWindow(long address, int x, int y, int width, int height);
    
    
    public void deleteProperty(final XAtom property)
    {
    }
    // BadAtom BadWindow
    
    public void rotateProperties(final XAtom[] properties, final int positions)
    {
    }
    // BadAtom BadMatch BadWindow
    
    public void setProperty(final XAtom property, final XAtom type, final int format, final int mode, final byte[] data)
    {
    }
    // BadAlloc BadAtom BadMatch BadValue BadWindow
    
    public XAtom[] listProperties()
    {
	return null;
    }
    // use XFree :: BadWindow
    
    public void getProperty() //// XGetWindowProperty
    {
    }
    // use XFree :: BadAtom BadValue BadWindow
    
}

