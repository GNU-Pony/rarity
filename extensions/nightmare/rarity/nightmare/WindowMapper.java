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
 * Maps and unmaps created and destroyed windows
 * 
 * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class WindowMapper implements Blackboard.BlackboardObserver
{
    /**
     * Private constructor
     */
    private WindowMapper()
    {
	/* Do nothing */
    }
    
    
    
    /**
     * The instance
     */
    private static WindowMapper instance = null;
    
    
    
    /**
     * Starts the service
     */
    public static void start()
    {
	instance = new WindowMapper();
	Blackboard.getInstance(XEvent.class).registerObserver(instance);
    }
    
    /**
     * Stops the service
     */
    public static void stop()
    {
	Blackboard.getInstance(XEvent.class).unregisterObserver(instance);
	instance = null;
    }
    
    
    
    /**
     * This method is invoked when the a message is pinned on the blackboard
     * 
     * @param  message  The broadcasted message
     */
    public void messageBroadcasted(final Blackboard.BlackboardMessage message)
    {
	if (message instanceof XEvent.CreateWindow)
	{
	    final XEvent.CreateWindow event = (XEvent.CreateWindow)message;
	    if (event.overrideRedirect == false)
		Rarity.newWindow((int)(event.window), event.position.x, event.position.y, event.size.width, event.size.height);
	}
	else if (message instanceof XEvent.DestroyWindow)
	{
	    final XEvent.DestroyWindow event = (XEvent.DestroyWindow)message;
	    Window.removeWindow(Window.getByAddress(event.window));
	}
    }
    
}

