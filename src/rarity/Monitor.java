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

import java.awt.*;
import java.util.Vector;


/**
 * The class is used to get information about the existing monitor (physcial screens) in the environment
 * 
 * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class Monitor extends PropertyBase
{
    /**
     * The index of the screen the monitor is allocated to
     */
    public static final String SCREEN = "screen";
    
    /**
     * The monitor's virtual resolution on the X-axis
     */
    public static final String RESOLUTION_X = "resolutionX";
    
    /**
     * The monitor's virtual resolution on the Y-axis
     */
    public static final String RESOLUTION_Y = "resolutionY";
    
    /**
     * The monitor's offset on the X-axis
     */
    public static final String OFFSET_X = "offsetX";
    
    /**
     * The monitor's offset on the Y-axis
     */
    public static final String OFFSET_Y = "offsetY";
    
    /**
     * The left margin of the monitor, that is how much on the left side that is occupied by docked windows
     */
    public static final String MARGIN_LEFT = "marginLeft";
    
    /**
     * The right margin of the monitor, that is how much on the right side that is occupied by docked windows
     */
    public static final String MARGIN_RIGHT = "marginRight";
    
    /**
     * The top margin of the monitor, that is how much on the top side that is occupied by docked windows
     */
    public static final String MARGIN_TOP = "marginTop";
    
    /**
     * The bottom margin of the monitor, that is how much on the bottom side that is occupied by docked windows
     */
    public static final String MARGIN_BOTTOM = "marginBottom";
    
    /**
     * The unoccupied monitor width
     */
    public static final String WIDTH = "width";
    
    /**
     * The unoccupied monitor height
     */
    public static final String HEIGHT = "height";
    
    /**
     * Whether the monitor is the primary monitor
     */
    public static final String PRIMARY = "primary";
    
    
    
    /**
     * Constructor
     * 
     * @param  screen       The index of the screen the monitor is allocated to
     * @param  width        The monitor's virtual resolution on the X-axis
     * @param  height       The monitor's virtual resolution on the Y-axis
     * @param  left         The monitor's offset on the X-axis
     * @param  top          The monitor's offset on the Y-axis
     * @param  primary      Whether the monitor is the primary monitor
     */
    protected Monitor(final int screen, final int width, final int height, final int left, final int top, final boolean primary)
    {
	this.set(SCREEN, screen);
	this.set(PRIMARY, primary);
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
		    final Monitor monitor = (Monitor)(params[0]);
		    return monitor.getInteger(RESOLUTION_X) - monitor.getInteger(MARGIN_LEFT) - monitor.getInteger(MARGIN_RIGHT);
		}
	    });
	this.set(HEIGHT, new Lambda()
	    {
		@Override
		public Object evaluate(final Object... params)
		{
		    final Monitor monitor = (Monitor)(params[0]);
		    return monitor.getInteger(RESOLUTION_Y) - monitor.getInteger(MARGIN_TOP) - monitor.getInteger(MARGIN_BOTTOM);
		}
	    });
    }
    
    
    
    /**
     * All monitors in index order
     */
    static Vector<Monitor> monitors = new Vector<Monitor>();
    
    
    
    /**
     * Blackboard message for {@link Monitor} collective property and existance updates synchronisation
     * 
     * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
     */
    public static class SynchronisationMessage implements Blackboard.BlackboardMessage
    {
	/**
	 * Update is beginning
	 */
	public final static int BEGIN = 0;
	
	/**
	 * An entire monitor has been update, added, or removed
	 */
	public final static int MONITOR_DONE = 1;
	
	/**
	 * All monitor in a screen has been completed, however exclucind removal of monitors
	 */
	public final static int SCREEN_DONE = 2;
	
	/**
	 * Update is complete
	 */
	public final static int DONE = 3;
	
	
	
	/**
	 * Constructor
	 * 
	 * @param  state  The update state
	 */
	protected SynchronisationMessage(final int state)
	{
	    this.state = state;
	}
	
	
	
	/**
	 * The updated state
	 */
	public final int state;
	
    }
    
    /**
     * Blackboard message for {@link Monitor} property updates
     * 
     * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
     */
    public static class PropertyMessage implements Blackboard.BlackboardMessage
    {
	/**
	 * Constructor
	 * 
	 * @param  monitor   The updated monitor
	 * @param  property  The updated property
	 */
	protected PropertyMessage(final Monitor monitor, final String property)
	{
	    this.monitor = monitor;
	    this.property = property;
	    int i = 0;
	    for (final Monitor scr : Monitor.monitors)
		if (scr == monitor)
		    break;
		else
		    i++;
	    this.index = i;
	}
	
	
	
	/**
	 * The updated monitor
	 */
	public final Monitor monitor;
	
	/**
	 * The index of the updated monitor
	 */
	public final int index;
	
	/**
	 * The updated property
	 */
	public final String property;
	
    }
    
    
    /**
     * Blackboard message for {@link Monitor} existance updates
     * 
     * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
     */
    public static class ExistanceMessage implements Blackboard.BlackboardMessage
    {
	/**
	 * A new monitor has been added
	 */
	public static final int ADDED = 0;
	
	/**
	 * A monitor is being removed
	 */
	public static final int REMOVING = 1;
	
	/**
	 * A monitor has been removed
	 */
	public static final int REMOVED = 2;
	
	
	
	/**
	 * Constructor
	 * 
	 * @param  action  Either {@link #ADDED}, {@link #REMOVING} or {@link #REMOVED}
	 * @param  index   The index of the monitor in question
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
     * Refresh the monitor vector
     */
    public static void refresh()
    {
	synchronized (monitors)
	{   final Blackboard blackboard = Blackboard.getInstance(Monitor.class);
	    blackboard.broadcastMessage(new Monitor.SynchronisationMessage(Monitor.SynchronisationMessage.BEGIN));
	    
	    int index = 0;
	    int count = monitors.size();
	    
	    begin();
	    final int screenCount = getScreenCount();
	    for (int screen = 0; screen < screenCount; screen++)
	    {
		selectScreen(screen);
		final int monitorCount = getScreenMonitorCount();
		for (int monitor = 0; monitor < monitorCount; monitor++)
		{
		    selectMonitor(monitor);
		    if (isConnected() == false)
			continue;
		    
		    final int width = getWidth();
		    final int height = getHeight();
		    final int left = getXOffset();
		    final int top = getYOffset();
		    final boolean primary = isPrimary();
		    
		    if (index < count)
		    {	final Monitor mon = monitors.get(index);
			mon.set(WIDTH, width);
			mon.set(HEIGHT, height);
			mon.set(OFFSET_X, left);
			mon.set(OFFSET_Y, top);
			mon.set(SCREEN, screen);
			mon.set(PRIMARY, primary);
		    }
		    else
		    {	monitors.add(new Monitor(screen, width, height, left, top, primary));
			blackboard.broadcastMessage(new Monitor.ExistanceMessage(Monitor.ExistanceMessage.ADDED, index));
		    }
		    
		    index++;
		    blackboard.broadcastMessage(new Monitor.SynchronisationMessage(Monitor.SynchronisationMessage.MONITOR_DONE));
		}
		blackboard.broadcastMessage(new Monitor.SynchronisationMessage(Monitor.SynchronisationMessage.SCREEN_DONE));
	    }
	    
	    while (count-- > index)
	    {
		blackboard.broadcastMessage(new Monitor.ExistanceMessage(Monitor.ExistanceMessage.REMOVING, index));
		monitors.remove(index);
		blackboard.broadcastMessage(new Monitor.ExistanceMessage(Monitor.ExistanceMessage.REMOVED, index));
	    }
	    
	    blackboard.broadcastMessage(new Monitor.SynchronisationMessage(Monitor.SynchronisationMessage.DONE));
	}
    }
    
    
    
    /**
     * Initialise update process
     */
    private static native void begin();
    
    /**
     * The number of screens in the environment
     * 
     * @return  The number of screens in the environment
     */
    private static native int getScreenCount();
    
    /**
     * Select a screen
     * 
     * @param  screen  The index of the screen
     */
    private static native void selectScreen(int screen);
    
    /**
     * The number of monitors in the environment for the selected screen
     * 
     * @return  The number of monitors in the environment for the selected screen
     */
    private static native int getScreenMonitorCount();
    
    /**
     * Select a monitor
     * 
     * @param  monitor  The index of the monitor under the selected screen
     */
    private static native void selectMonitor(int monitor);
    
    /**
     * Checks whether the selected monitor is connected
     * 
     * @return  Whether the selected monitor is connected
     */
    private static native boolean isConnected();
    
    /**
     * Checks whether the selected monitor is the primary monitor its screen
     * 
     * @return  Whether the selected monitor is the primary monitor its screen
     */
    private static native boolean isPrimary();
    
    /**
     * Gets the selected monitor's virtual resolution width
     * 
     * @return  The selected monitor's virtual resolution width
     */
    private static native int getWidth();
    
    /**
     * Gets the selected monitor's virtual resolution height
     * 
     * @return  The selected monitor's virtual resolution height
     */
    private static native int getHeight();
    
    /**
     * Gets the selected monitor's offset on the X-axis from its screens top left corner
     * 
     * @return  The selected monitor's offset on the X-axis from its screens top left corner
     */
    private static native int getXOffset();
    
    /**
     * Gets the selected monitor's offset on the Y-axis from its screens top left corner
     * 
     * @return  The selected monitor's offset on the Y-axis from its screens top left corner
     */
    private static native int getYOffset();
    
    
    
    /**
     * Gets a monitor by its index
     * 
     * @param   index  The monitor's index
     * @return         The monitor
     */
    public static Monitor getMonitor(final int index)
    {
	synchronized (monitors)
	{   return monitors.get(index);
	}
    }
    
    /**
     * Gets the number of monitors on the system
     * 
     * @return  The number of monitors on the system
     */
    public static int getMonitorCount()
    {
	synchronized (monitors)
	{   return monitors.size();
	}
    }
    
    
    /**
     * {@inheritDoc}
     */
    protected void updated(final String property, final Object value)
    {
	Blackboard.getInstance(Monitor.class).broadcastMessage(new Monitor.PropertyMessage(this, property));
    }
    
}

