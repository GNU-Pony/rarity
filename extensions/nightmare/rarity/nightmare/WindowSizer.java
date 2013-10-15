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
	System.err.println("WindowSizer: recieves message " + message.getClass());
	if (message instanceof Window.ExistanceMessage)
	{
	    final Window.ExistanceMessage event = (Window.ExistanceMessage)message;
	    if (event.action != Window.ExistanceMessage.ADDED)
		return;
	    final Window window = Window.getWindow(event.index);
	    if (Screen.getScreenCount() < 1)
		return;
	    
	    final PhysicalScreen screen = PhysicalScreen.getScreen(0);
	    int x = screen.getInteger(PhysicalScreen.OFFSET_X) + screen.getInteger(PhysicalScreen.MARGIN_LEFT);
	    int y = screen.getInteger(PhysicalScreen.OFFSET_Y) + screen.getInteger(PhysicalScreen.MARGIN_TOP);
	    int width = screen.getInteger(PhysicalScreen.WIDTH);
	    int height = screen.getInteger(PhysicalScreen.HEIGHT);
	    
	    window.set(Window.LEFT, x);
	    window.set(Window.TOP, y);
	    window.set(Window.WIDTH, width);
	    window.set(Window.HEIGHT, height);
	    window.updateArea();
	}
    }
    
}

