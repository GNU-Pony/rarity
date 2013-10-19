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
package rarity.nightmare;
import rarity.*;

import java.util.*;


/**
 * Sets the size of newly created windows
 * 
 * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class WindowSizer implements Blackboard.BlackboardObserver
{
    /**
     * Private constructor
     */
    private WindowSizer()
    {
	/* Do nothing */
    }
    
    
    
    /**
     * The instance
     */
    private static WindowSizer instance = null;
    
    /**
     * Mapped windows
     */
    private Vector<Window> windows = new Vector<>();
    
    
    
    /**
     * Starts the service
     */
    public static void start()
    {
	instance = new WindowSizer();
	Blackboard.getInstance(Window.ExistanceMessage.class).registerObserver(instance);
    }
    
    /**
     * Stops the service
     */
    public static void stop()
    {
	Blackboard.getInstance(Window.ExistanceMessage.class).unregisterObserver(instance);
	instance = null;
    }
    
    
    
    /**
     * This method is invoked when the a message is pinned on the blackboard
     * 
     * @param  message  The broadcasted message
     */
    public void messageBroadcasted(final Blackboard.BlackboardMessage message)
    {
	<"">System.err.println("WindowSizer: receives message " + message.getClass());
	if (message instanceof Window.ExistanceMessage)
	    synchronized (this.windows)
	    {
		final Window.ExistanceMessage event = (Window.ExistanceMessage)message;
		final Window window = Window.getWindow(event.index);
		
		if (event.action == Window.ExistanceMessage.REMOVING)
		{
		    <"">System.err.println("  Removing: " + event.index + ":" + window.pointer);
		    int i = 0;
		    for (int n = this.windows.size(); i < n; i++)
			if (this.windows.get(i).pointer == window.pointer)
			    break;
		    if (i < this.windows.size())
			this.windows.remove(i);
		    else
			return;
		}
		else if (event.action == Window.ExistanceMessage.ADDED)
		{
		    <"">System.err.println("  Added: " + event.index + ":" + window.pointer);
		    final int[] types = window.getProperty(EWMH._NET_WM_WINDOW_TYPE).getIntArray();
		    normal_test: {
			for (final int type : types)
			    if (     type == EWMH._NET_WM_WINDOW_TYPE_NORMAL ||
				     type == EWMH._NET_WM_WINDOW_TYPE_DIALOG ||
				     type == EWMH._NET_WM_WINDOW_TYPE_SPLASH ||
				     type == EWMH._NET_WM_WINDOW_TYPE_UTILITY)
				break normal_test;
			    else if (type == EWMH._NET_WM_WINDOW_TYPE_MENU          ||
				     type == EWMH._NET_WM_WINDOW_TYPE_DROPDOWN_MENU ||
				     type == EWMH._NET_WM_WINDOW_TYPE_POPUP_MENU    || 
				     type == EWMH._NET_WM_WINDOW_TYPE_TOOLTIP       ||
				     type == EWMH._NET_WM_WINDOW_TYPE_COMBO         ||
				     type == EWMH._NET_WM_WINDOW_TYPE_DND)
				return;
			    else if (type == EWMH._NET_WM_WINDOW_TYPE_DOCK ||
				     type == EWMH._NET_WM_WINDOW_TYPE_DESKTOP)
				return;
			//if (types.length == 0)
			//    if (window.getProperty(EWMH.WM_TRANSIENT_FOR).isSet())
			//	break normal_test; /* Implicitly _NET_WM_WINDOW_TYPE_DIALOG */
			//    else
			//	break normal_test; /* Implicitly _NET_WM_WINDOW_TYPE_NORMAL */
			return;
		    }
		    this.windows.add(window);
		}
		else if (event.action == Window.ExistanceMessage.REMOVED)
		    return;
		
		final Monitor monitor = Monitor.getMonitor(0);
		int x = monitor.getInteger(Monitor.OFFSET_X) + monitor.getInteger(Monitor.MARGIN_LEFT);
		int y = monitor.getInteger(Monitor.OFFSET_Y) + monitor.getInteger(Monitor.MARGIN_TOP);
		int width = monitor.getInteger(Monitor.WIDTH);
		int height = monitor.getInteger(Monitor.HEIGHT);
		
		/* Spiral layout algorithm */
		boolean far = false;
		boolean wide = width >= height;
		for (final Window win : windows)
		{
		    final boolean haveMore = win != windows.get(windows.size() - 1);
		    
		    int w = width, x_ = 0;
		    int h = height, y_ = 0;
		    
		    if (haveMore)
			if (wide)
			{   w >>= 1;
			    width -= w;
			    if (far)
				x_ = width;
			}
			else
			{   h >>= 1;
			    height -= h;
			    if (far)
				y_ = height;
			}
		    
		    win.set(Window.LEFT, x + x_);
		    win.set(Window.TOP, y + y_);
		    win.set(Window.WIDTH, w);
		    win.set(Window.HEIGHT, h);
		    
		    if (far == false)
			if (wide)
			    x += w;
			else
			    y += h;
		    
		    wide ^= true;
		    if (wide)
			far ^= true;
		    
		    win.updateArea();
		}
	    }
    }
    
}

