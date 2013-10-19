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
 * The class is used to get information about the existing screens in the environment
 * 
 * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class Screen extends PropertyBase
{
    /**
     * The screen's virtual resolution on the X-axis
     */
    public static final String RESOLUTION_X = "resolutionX";
    
    /**
     * The screen's virtual resolution on the Y-axis
     */
    public static final String RESOLUTION_Y = "resolutionY";
    
    /**
     * The screen's offset on the X-axis
     */
    public static final String OFFSET_X = "offsetX";
    
    /**
     * The screen's offset on the Y-axis
     */
    public static final String OFFSET_Y = "offsetY";
    
    /**
     * The left margin of the screen, that is how much on the left side that is occupied by docked windows
     */
    public static final String MARGIN_LEFT = "marginLeft";
    
    /**
     * The right margin of the screen, that is how much on the right side that is occupied by docked windows
     */
    public static final String MARGIN_RIGHT = "marginRight";
    
    /**
     * The top margin of the screen, that is how much on the top side that is occupied by docked windows
     */
    public static final String MARGIN_TOP = "marginTop";
    
    /**
     * The bottom margin of the screen, that is how much on the bottom side that is occupied by docked windows
     */
    public static final String MARGIN_BOTTOM = "marginBottom";
    
    /**
     * The unoccupied screen width
     */
    public static final String WIDTH = "width";
    
    /**
     * The unoccupied screen height
     */
    public static final String HEIGHT = "height";
    
    
    
    /**
     * Constructor
     * 
     * @param  width   The screen's virtual resolution on the X-axis
     * @param  height  The screen's virtual resolution on the Y-axis
     * @param  left    The screen's offset on the X-axis
     * @param  top     The screen's offset on the Y-axis
     */
    protected Screen(final int width, final int height, final int left, final int top, final int index)
    {
	this.set(RESOLUTION_X, width);
	this.set(RESOLUTION_Y, height);
	this.set(OFFSET_X, left);
	this.set(OFFSET_Y, top);
	this.set(MARGIN_LEFT, 0);
	this.set(MARGIN_RIGHT, 0);
	this.set(MARGIN_TOP, 0);
	this.set(MARGIN_BOTTOM, 0);
	this.set(WIDTH, new Lambda()
	    {
		@Override
		public Object evaluate(final Object... params)
		{
		    final Screen screen = (Screen)(params[0]);
		    return screen.getInteger(RESOLUTION_X) - screen.getInteger(MARGIN_LEFT) - screen.getInteger(MARGIN_RIGHT);
		}
	    });
	this.set(HEIGHT, new Lambda()
	    {
		@Override
		public Object evaluate(final Object... params)
		{
		    final Screen screen = (Screen)(params[0]);
		    return screen.getInteger(RESOLUTION_Y) - screen.getInteger(MARGIN_TOP) - screen.getInteger(MARGIN_BOTTOM);
		}
	    });
	this.root = X11.rootWindow(index);
    }
    
    
    
    /**
     * All screens in index order
     */
    static Vector<Screen> screens = new Vector<Screen>();
    
    /**
     * The screen's root window
     */
    private final long root;
    
    
    
    /**
     * Blackboard message for {@link Screen} property updates
     * 
     * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
     */
    public static class PropertyMessage implements Blackboard.BlackboardMessage
    {
	/**
	 * Constructor
	 * 
	 * @param  screen    The updated screen
	 * @param  property  The updated property
	 */
	protected PropertyMessage(final Screen screen, final String property)
	{
	    this.screen = screen;
	    this.property = property;
	    int i = 0;
	    for (final Screen scr : Screen.screens)
		if (scr == screen)
		    break;
		else
		    i++;
	    this.index = i;
	}
	
	
	
	/**
	 * The updated screen
	 */
	public final Screen screen;
	
	/**
	 * The index of the updated screen
	 */
	public final int index;
	
	/**
	 * The updated property
	 */
	public final String property;
	
    }
    
    
    /**
     * Blackboard message for {@link Screen} existance updates
     * 
     * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
     */
    public static class ExistanceMessage implements Blackboard.BlackboardMessage
    {
	/**
	 * A new screen has been added
	 */
	public static final int ADDED = 0;
	
	/**
	 * A screen is being removed
	 */
	public static final int REMOVING = 1;
	
	/**
	 * A screen has been removed
	 */
	public static final int REMOVED = 2;
	
	
	
	/**
	 * Constructor
	 * 
	 * @param  action  Either {@link #ADDED}, {@link #REMOVING} or {@link #REMOVED}
	 * @param  index   The index of the screen in question
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
     * Gets a screen by its index
     * 
     * @param   index  The screen's index
     * @return         The screen
     */
    public static Screen getScreen(final int index)
    {
	synchronized (screens)
	{   return screens.get(index);
	}
    }
    
    /**
     * Gets the number of screens on the system
     * 
     * @return  The number of screens on the system
     */
    public static int getScreenCount()
    {
	synchronized (screens)
	{   return screens.size();
	}
    }
    
    
    /**
     * Gets the screen's root window
     * 
     * @return  The screen's root window
     */
    public Window getRootWindow()
    {	
	synchronized (Window.windows)
	{   Window rc = Window.getByAddress(this.root);
	    if (rc == null)
	    {	final int w = this.getInteger(RESOLUTION_X);
		final int h = this.getInteger(RESOLUTION_Y);
		Window.windows.add(rc = new Window(0, 0, w, h, this.root));
	    }
	    return rc;
	}
    }
    
    
    /**
     * {@inheritDoc}
     */
    protected void updated(final String property, final Object value)
    {
	Blackboard.getInstance(Screen.class).broadcastMessage(new Screen.PropertyMessage(this, property));
    }
    
}

