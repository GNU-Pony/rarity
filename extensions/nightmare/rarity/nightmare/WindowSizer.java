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


/**
 * Sets the size of newly created windows
 * 
 * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class WindowSizer implements Blackboard.BlackboardObserver
{
    private static XAtom _NET_WM_WINDOW_TYPE = null;
    private static int _NET_WM_WINDOW_TYPE_DESKTOP;
    private static int _NET_WM_WINDOW_TYPE_DOCK;
    private static int _NET_WM_WINDOW_TYPE_TOOLBAR;
    private static int _NET_WM_WINDOW_TYPE_MENU;
    private static int _NET_WM_WINDOW_TYPE_UTILITY;
    private static int _NET_WM_WINDOW_TYPE_SPLASH;
    private static int _NET_WM_WINDOW_TYPE_DIALOG;
    private static int _NET_WM_WINDOW_TYPE_DROPDOWN_MENU;
    private static int _NET_WM_WINDOW_TYPE_POPUP_MENU;
    private static int _NET_WM_WINDOW_TYPE_TOOLTIP;
    private static int _NET_WM_WINDOW_TYPE_NOTIFICATION;
    private static int _NET_WM_WINDOW_TYPE_COMBO;
    private static int _NET_WM_WINDOW_TYPE_DND;
    private static int _NET_WM_WINDOW_TYPE_NORMAL;
    
    
    
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
	<"">System.err.println("WindowSizer: recieves message " + message.getClass());
	if (message instanceof Window.ExistanceMessage)
	{
	    if (_NET_WM_WINDOW_TYPE == null)
	    {
		_NET_WM_WINDOW_TYPE = new XAtom("_NET_WM_WINDOW_TYPE", false);
		_NET_WM_WINDOW_TYPE_DESKTOP       = (new XAtom("_NET_WM_WINDOW_TYPE_DESKTOP",       false)).atom;//
		_NET_WM_WINDOW_TYPE_DOCK          = (new XAtom("_NET_WM_WINDOW_TYPE_DOCK",          false)).atom;//
		_NET_WM_WINDOW_TYPE_TOOLBAR       = (new XAtom("_NET_WM_WINDOW_TYPE_TOOLBAR",       false)).atom;//
		_NET_WM_WINDOW_TYPE_MENU          = (new XAtom("_NET_WM_WINDOW_TYPE_MENU",          false)).atom;
		_NET_WM_WINDOW_TYPE_UTILITY       = (new XAtom("_NET_WM_WINDOW_TYPE_UTILITY",       false)).atom;//
		_NET_WM_WINDOW_TYPE_SPLASH        = (new XAtom("_NET_WM_WINDOW_TYPE_SPLASH",        false)).atom;//
		_NET_WM_WINDOW_TYPE_DIALOG        = (new XAtom("_NET_WM_WINDOW_TYPE_DIALOG",        false)).atom;
		_NET_WM_WINDOW_TYPE_DROPDOWN_MENU = (new XAtom("_NET_WM_WINDOW_TYPE_DROPDOWN_MENU", false)).atom;
		_NET_WM_WINDOW_TYPE_POPUP_MENU    = (new XAtom("_NET_WM_WINDOW_TYPE_POPUP_MENU",    false)).atom;
		_NET_WM_WINDOW_TYPE_TOOLTIP       = (new XAtom("_NET_WM_WINDOW_TYPE_TOOLTIP",       false)).atom;
		_NET_WM_WINDOW_TYPE_NOTIFICATION  = (new XAtom("_NET_WM_WINDOW_TYPE_NOTIFICATION",  false)).atom;//
		_NET_WM_WINDOW_TYPE_COMBO         = (new XAtom("_NET_WM_WINDOW_TYPE_COMBO",         false)).atom;
		_NET_WM_WINDOW_TYPE_DND           = (new XAtom("_NET_WM_WINDOW_TYPE_DND",           false)).atom;
		_NET_WM_WINDOW_TYPE_NORMAL        = (new XAtom("_NET_WM_WINDOW_TYPE_NORMAL",        false)).atom;
	    }
	    
	    final Window.ExistanceMessage event = (Window.ExistanceMessage)message;
	    if (event.action != Window.ExistanceMessage.ADDED)
		return;
	    final Window window = Window.getWindow(event.index);
	    final int[] types = window.getProperty(_NET_WM_WINDOW_TYPE).getIntArray();
	    
	    normal_test: {
		for (final int type : types)
		    if (     type == _NET_WM_WINDOW_TYPE_NORMAL ||
			     type == _NET_WM_WINDOW_TYPE_DIALOG)
			break normal_test;
		    else if (type == _NET_WM_WINDOW_TYPE_MENU          ||
			     type == _NET_WM_WINDOW_TYPE_DROPDOWN_MENU ||
			     type == _NET_WM_WINDOW_TYPE_POPUP_MENU    || 
			     type == _NET_WM_WINDOW_TYPE_TOOLTIP       ||
			     type == _NET_WM_WINDOW_TYPE_COMBO         ||
			     type == _NET_WM_WINDOW_TYPE_DND)
			return;
		return;
	    }
	    
	    final Monitor monitor = Monitor.getMonitor(0);
	    int x = monitor.getInteger(Monitor.OFFSET_X) + monitor.getInteger(Monitor.MARGIN_LEFT);
	    int y = monitor.getInteger(Monitor.OFFSET_Y) + monitor.getInteger(Monitor.MARGIN_TOP);
	    int width = monitor.getInteger(Monitor.WIDTH);
	    int height = monitor.getInteger(Monitor.HEIGHT);
	    
	    window.set(Window.LEFT, x);
	    window.set(Window.TOP, y);
	    window.set(Window.WIDTH, width);
	    window.set(Window.HEIGHT, height);
	    window.updateArea();
	}
    }
    
}

