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
    public static final int SIGHUP = 1;
    public static final int SIGINT = 2;
    public static final int SIGQUIT = 3;
    public static final int SIGILL = 4;
    public static final int SIGTRAP = 5;
    public static final int SIGABRT = 6;
    public static final int SIGIOT = 6;
    public static final int SIGBUS = 7;
    public static final int SIGFPE = 8;
    public static final int SIGKILL = 9;
    public static final int SIGUSR1 = 10;
    public static final int SIGSEGV = 11;
    public static final int SIGUSR2 = 12;
    public static final int SIGPIPE = 13;
    public static final int SIGALRM = 14;
    public static final int SIGTERM = 15;
    public static final int SIGSTKFLT = 16;
    public static final int SIGCHLD = 17;
    public static final int SIGCONT = 18;
    public static final int SIGSTOP = 19;
    public static final int SIGTSTP = 20;
    public static final int SIGTTIN = 21;
    public static final int SIGTTOU = 22;
    public static final int SIGURG = 23;
    public static final int SIGXCPU = 24;
    public static final int SIGXFSZ = 25;
    public static final int SIGVTALRM = 26;
    public static final int SIGPROF = 27;
    public static final int SIGWINCH = 28;
    public static final int SIGIO = 29;
    public static final int SIGPOLL = 29;
    public static final int SIGPWR = 30;
    public static final int SIGSYS = 31;
    
    
    
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
    
}

