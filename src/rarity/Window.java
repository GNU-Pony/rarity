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
     * Constructor
     * 
     * @param  width    The window's width
     * @param  height   The window's height
     * @parma  pointer  Pointer to the window object in native code
     */
    protected Window(final int width, final int height, final long pointer)
    {
	this.set(WIDTH, width);
	this.set(HEIGHT, height);
	this.set(LEFT, 0);
	this.set(TOP, 0);
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
		    return window
	    return null;
	}
    }
    
    
    /**
     * {@inheritDoc}
     */
    protected void updated(final String property, final Object value)
    {
	Blackboard.getInstance(Window.class).broadcastMessage(new Window.PropertyMessage(this, property));
    }
    
}

