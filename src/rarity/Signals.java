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
 * Signal handling class
 * 
 * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class Signals
{
    /**
     * Constructor hiding
     */
    private Signals()
    {
	/* do nothing */
    }
    
    
    
    /**
     * Message broadcasted when a signal is handled
     */
    public static class Message implements Blackboard.BlackboardMessage
    {
	/**
	 * Constructor
	 * 
	 * @param  signal  The signal
	 */
	public Message(final int signal)
	{
	    this.signal = signal;
	}
	
	
	
	/**
	 * The signal
	 */
	public final int signal;
	
    }
    
    
    
    /**
     * Start signal handling
     * 
     * @param  signal  The signal to handle
     */
    public static native void startHandle(int signal);
    
    /**
     * This method is invoked by the native code when a signal has been captured
     * 
     * @param  signal  The captured signal
     */
    public static void signal(final int signal)
    {
	Blackboard.getInstance(Signals.class).broadcastMessage(new Signals.Message(signal));
    }
    
    
    
    #for signal in $(cat /usr/include/asm/signal.h | grep '^#define SIG' | grep -v '^#define SIGRT' | grep -v '^#define SIGSTKSZ' | cut -f 1 | cut -d ' ' -f 2); do
    #    value=$((cat /usr/include/asm/signal.h | grep '#define SIG' ; echo "$signal") | cpp | tail -n 1)
	 public static final int <"$signal$"> = <"$value$">;
    #done
    
}

