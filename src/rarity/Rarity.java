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
 * Mane class
 * 
 * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class Rarity
{
    /**
     * Whether Xinerama is used
     */
    public static boolean usingXinerama = false;
    
    
    
    /**
     * Constructor hiding
     */
    private Rarity()
    {
	/* do nothing */
    }
    
    
    
    /**
     * Mane method
     * 
     * @param  args  Command line arguments excluding program name, the first is the library to load
     */
    public static void main(final String... args)
    {
	try
	{   System.load(args[0]);
	}
	catch (final Throwable err)
	{   System.err.println("Rarity: Unable to load library: rarity");
	    abort();
	}
	
	final int SCREEN_INPUT = X11.EventMask.PROPERTY_CHANGE
	                       | X11.EventMask.COLORMAP_CHANGE
	                       | X11.EventMask.SUBSTRUCTURE_REDIRECT
	                       | X11.EventMask.SUBSTRUCTURE_NOTIFY
	                       | X11.EventMask.STRUCTURE_NOTIFY;
	
	boolean abort = false;
	try
	{
	    setLocale();
	    X11.openDisplay();
	    setXAtoms();
	    usingXinerama = Xinerama.initialise();
	    
	    int screenCount;
	    synchronized (Screen.screens)
	    {   screenCount = X11.screenCount();
		for (int i = 0; i < screenCount; i++)
		{   X11.activateScreen(i);
		    X11.selectRootInput(i, SCREEN_INPUT);
		    Screen.screens.add(new Screen(X11.screenWidth(i), X11.screenHeight(i), 0, 0));
		    Screen.ExistanceMessage e = new Screen.ExistanceMessage(Screen.ExistanceMessage.ADDED, i);
		    Blackboard.getInstance(Screen.class).broadcastMessage(e);
	    }   }
	    
	    scanForWindows();
	    X11.sync();
	    eventLoop();
	}
	catch (final Throwable err)
	{
	    err.printStackTrace(System.err);
	    abort = true;
	}
	finally
	{
	    synchronized (Screen.screens)
	    {   Screen.ExistanceMessage e;
		int screenCount = X11.screenCount();
		for (int i = 0; i < screenCount; i++)
		{   e = new Screen.ExistanceMessage(Screen.ExistanceMessage.REMOVING, i);
		    Blackboard.getInstance(Screen.class).broadcastMessage(e);
		    X11.selectRootInput(i, 0);
		    X11.deactivateScreen(i);
		    Screen.screens.remove(0);
		    e = new Screen.ExistanceMessage(Screen.ExistanceMessage.REMOVED, i);
		    Blackboard.getInstance(Screen.class).broadcastMessage(e);
	    }   }
	    Xinerama.terminate();
	    X11.closeDisplay();
	    if (abort)
	    	abort();
	}
    }
    
    
    
    /**
     * Set locale stuff
     */
    private static native void setLocale();
    // setlocale(LC_CTYPE, "");
    // if (XSupportsLocale())
    //   XSetLocaleModifiers("");
    
    
    /**
     * Sets X atoms
     */
    private static native void setXAtoms();
    // xa_string                  = XA_STRING;
    // xa_compound_text           = XInternAtom(dpy, "COMPOUND_TEXT",              False);
    // xa_utf8_string             = XInternAtom(dpy, "UTF8_STRING",                False);
    // 
    // wm_name                    = XInternAtom(dpy, "WM_NAME",                    False);
    // wm_state                   = XInternAtom(dpy, "WM_STATE",                   False);
    // wm_change_state            = XInternAtom(dpy, "WM_CHANGE_STATE",            False);
    // wm_protocols               = XInternAtom(dpy, "WM_PROTOCOLS",               False);
    // wm_delete                  = XInternAtom(dpy, "WM_DELETE_WINDOW",           False);
    // wm_take_focus              = XInternAtom(dpy, "WM_TAKE_FOCUS",              False);
    // wm_colormaps               = XInternAtom(dpy, "WM_COLORMAP_WINDOWS",        False);
    // 
    // _net_wm_pid                = XInternAtom(dpy, "_NET_WM_PID",                False);
    // _net_supported             = XInternAtom(dpy, "_NET_SUPPORTED",             False);
    // _net_wm_window_type        = XInternAtom(dpy, "_NET_WM_WINDOW_TYPE",        False);
    // _net_wm_window_type_dialog = XInternAtom(dpy, "_NET_WM_WINDOW_TYPE_DIALOG", False);
    // _net_wm_name               = XInternAtom(dpy, "_NET_WM_NAME",               False);
    
    
    /**
     * Exit with SIGABRT
     */
    public static native void abort();
    // abort();
    
    
    /**
     * Start event loop
     */
    public static native void eventLoop();
    
    
    
    /**
     * Gets the pointer to the atom for XA_STRING
     * 
     * @param  The pointer to the atom
     */
    public static native long getAtomPointerXA_STRING();
    // return (jlong)(void*)&xa_string;
    
    /**
     * Gets the pointer to the atom for XA_COMPOUND_TEXT
     * 
     * @param  The pointer to the atom
     */
    public static native long getAtomPointerXA_COMPOUND_TEXT();
    // return (jlong)(void*)&xa_compound_text;
    
    /**
     * Gets the pointer to the atom for XA_UTF8_STRING
     * 
     * @param  The pointer to the atom
     */
    public static native long getAtomPointerXA_UTF8_STRING();
    // return (jlong)(void*)&xa_utf8_string;
    
    
    /**
     * Gets the pointer to the atom for WM_NAME
     * 
     * @param  The pointer to the atom
     */
    public static native long getAtomPointerWM_NAME();
    // return (jlong)(void*)&wm_name;
    
    /**
     * Gets the pointer to the atom for WM_STATE
     * 
     * @param  The pointer to the atom
     */
    public static native long getAtomPointerWM_STATE();
    // return (jlong)(void*)&wm_state;
    
    /**
     * Gets the pointer to the atom for WM_CHANGE_STATE
     * 
     * @param  The pointer to the atom
     */
    public static native long getAtomPointerWM_CHANGE_STATE();
    // return (jlong)(void*)&wm_change_state;
    
    /**
     * Gets the pointer to the atom for WM_PROTOCOLS
     * 
     * @param  The pointer to the atom
     */
    public static native long getAtomPointerWM_PROTOCOLS();
    // return (jlong)(void*)&wm_protocols;
    
    /**
     * Gets the pointer to the atom for WM_DELETE
     * 
     * @param  The pointer to the atom
     */
    public static native long getAtomPointerWM_DELETE();
    // return (jlong)(void*)&wm_delete;
    
    /**
     * Gets the pointer to the atom for WM_TAKE_FOCUS
     * 
     * @param  The pointer to the atom
     */
    public static native long getAtomPointerWM_TAKE_FOCUS();
    // return (jlong)(void*)&wm_take_focus;
    
    /**
     * Gets the pointer to the atom for WM_COLORMAPS
     * 
     * @param  The pointer to the atom
     */
    public static native long getAtomPointerWM_COLORMAPS();
    // return (jlong)(void*)&wm_colormaps;
    
    
    /**
     * Gets the pointer to the atom for _NET_WM_PID
     * 
     * @param  The pointer to the atom
     */
    public static native long getAtomPointer_NET_WM_PID();
    // return (jlong)(void*)&_net_wm_pid;
    
    /**
     * Gets the pointer to the atom for _NET_SUPPORTED
     * 
     * @param  The pointer to the atom
     */
    public static native long getAtomPointer_NET_SUPPORTED();
    // return (jlong)(void*)&_net_supported;
    
    /**
     * Gets the pointer to the atom for _NET_WM_WINDOW_TYPE
     * 
     * @param  The pointer to the atom
     */
    public static native long getAtomPointer_NET_WM_WINDOW_TYPE();
    // return (jlong)(void*)&_net_wm_window_type;
    
    /**
     * Gets the pointer to the atom for _NET_WM_WINDOW_TYPE_DIALOG
     * 
     * @param  The pointer to the atom
     */
    public static native long getAtomPointer_NET_WM_WINDOW_TYPE_DIALOG();
    // return (jlong)(void*)&_net_wm_window_type_dialog;
    
    /**
     * Gets the pointer to the atom for _NET_WM_NAME
     * 
     * @param  The pointer to the atom
     */
    public static native long getAtomPointer_NET_WM_NAME();
    // return (jlong)(void*)&_net_wm_name;
    
    
    /**
     * Scan for existing windows
     * 
     * @param  screen  The index of the screen to scan
     */
    private static native void scanForWindows(int screen);
    // unsigned int i, n;
    // Window _root, _parent, *windows;
    // XQueryTree(display, RootWindow(display, screen), &_root, &_parent, &windows, &n);
    // for (i = 0; i < n; i++)
    //   {
    //     unsigned int width, height, _border, _depth;
    //     int _x, _y;
    //     XGetGeometry(display, *(windows + i), _root, &_x, &_y, &width, &height, _border, _depth);
    //     $invoke$ rarity.Rarity.newWindow((jint)*(windows + i), (jint)width, (jint)height);
    //   }
    // XFree(windows);
    
    
    /**
     * Invoked by native code when a new window has been found
     * 
     * @param  pointer  The pointer to the window
     * @param  width    The width of the window
     * @param  height   The height of the window
     */
    public static void newWindow(final int pointer, final int width, final int height)
    {
	final Window window = new Window(width, height, pointer);
	synchronized (Window.windows)
	{
	    final int index = Window.windows.size();
	    Window.windows.add(window);
	    Window.ExistanceMessage e = new Window.ExistanceMessage(Window.ExistanceMessage.ADDED, index);
	    Blackboard.getInstance(Window.class).broadcastMessage(e);
	}
    }
    
}

