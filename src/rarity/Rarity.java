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
	{   System.load("<"$LIBPATH$">/<"$LIB$">");
	}
	catch (final Throwable err)
	{   System.err.println("Rarity: Unable to load library: rarity");
	    abort();
	}
	
	PluginHandler.restartPlugins();
	
	final int SCREEN_INPUT = X11.EventMask.PROPERTY_CHANGE
	                       | X11.EventMask.COLORMAP_CHANGE
	                       | X11.EventMask.SUBSTRUCTURE_NOTIFY
	                       | X11.EventMask.STRUCTURE_NOTIFY;
	
	boolean abort = false;
	try
	{
	    staticInit();
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
		    Blackboard.getInstance(Screen.ExistanceMessage.class).broadcastMessage(e);
	    }   }
	    
	    Monitor.refresh();
	    
	    for (int i = 0, n = usingXinerama ? Xinerama.screenCount() : X11.screenCount(); i < n; i++)
		scanForWindows(i);
	    
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
		    Blackboard.getInstance(Screen.ExistanceMessage.class).broadcastMessage(e);
		    X11.selectRootInput(i, 0);
		    X11.deactivateScreen(i);
		    Screen.screens.remove(0);
		    e = new Screen.ExistanceMessage(Screen.ExistanceMessage.REMOVED, i);
		    Blackboard.getInstance(Screen.ExistanceMessage.class).broadcastMessage(e);
	    }   }
	    Xinerama.terminate();
	    X11.closeDisplay();
	    if (abort)
	    	abort();
	}
    }
    
    
    /**
     * Intialise static variables
     */
    private static native void staticInit();
    
    
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
    //     int x, y;
    //     XGetGeometry(display, *(windows + i), _root, &x, &y, &width, &height, _border, _depth);
    //     $invoke$ rarity.Rarity.newWindow((jint)*(windows + i), (jint)x, (jint)y, (jint)width, (jint)height);
    //   }
    // XFree(windows);
    
    
    /**
     * Invoked by native code when a new window has been found
     * 
     * @param  left     The left X-axis position of the window
     * @param  top      The top Y-axis position of the window
     * @param  pointer  The pointer to the window
     * @param  width    The width of the window
     * @param  height   The height of the window
     */
    public static void newWindow(final int pointer, final int left, final int top, final int width, final int height)
    {
	final Window window = new Window(left, top, width, height, pointer);
	synchronized (Window.windows)
	{
	    final int index = Window.windows.size();
	    Window.windows.add(window);
	    Window.ExistanceMessage e = new Window.ExistanceMessage(Window.ExistanceMessage.ADDED, index);
	    Blackboard.getInstance(Window.ExistanceMessage.class).broadcastMessage(e);
	}
    }
    
    
    public static void eventButtonPress(final long serial, final boolean sendEvent, final int time, final int window, final int root, final int subwindow, final int x, final int y, final int xRoot, final int yRoot, final int state, final int button, final boolean sameScreen)
    {
	(new XEvent.RatButton(serial, sendEvent, time, window, root, subwindow, x, y, xRoot, yRoot, state, button, sameScreen, XEvent.RatButton.PRESSED)).broadcast();
    }
    
    
    public static void eventButtonRelease(final long serial, final boolean sendEvent, final int time, final int window, final int root, final int subwindow, final int x, final int y, final int xRoot, final int yRoot, final int state, final int button, final boolean sameScreen)
    {
	(new XEvent.RatButton(serial, sendEvent, time, window, root, subwindow, x, y, xRoot, yRoot, state, button, sameScreen, XEvent.RatButton.RELEASED)).broadcast();
    }
    
    
    public static void eventClientMessage(final long serial, final boolean sendEvent, final int window, final long messageType, final int format, final byte[] data)
    {
	(new XEvent.ClientMessage(serial, sendEvent, window, messageType, format, data)).broadcast();
    }
    
    
    public static void eventCirculateNotify(final long serial, final boolean sendEvent, final int event, final int window, final int place)
    {
	(new XEvent.Circulate(serial, sendEvent, event, window, place, XEvent.Circulate.NOTIFY)).broadcast();
    }
    
    
    public static void eventCirculateRequest(final long serial, final boolean sendEvent, final int parent, final int window, final int place)
    {
	(new XEvent.Circulate(serial, sendEvent, parent, window, place, XEvent.Circulate.REQUEST)).broadcast();
    }
    
    
    public static void eventColormapNotify(final long serial, final boolean sendEvent, final int window, final int colormap, final boolean isNew, final int state)
    {
	(new XEvent.ColormapNotify(serial, sendEvent, window, colormap, isNew, state)).broadcast();
    }
    
    
    public static void eventConfigureNotify(final long serial, final boolean sendEvent, final int event, final int window, final int above, final int x, final int y, final int width, final int height, final int borderWidth, final boolean overrideRedirect)
    {
	(new XEvent.ConfigureNotify(serial, sendEvent, event, window, above, x, y, width, height, borderWidth, overrideRedirect)).broadcast();
    }
    
    
    public static void eventConfigureRequest(final long serial, final boolean sendEvent, final long valueMask, final int parent, final int window, final int above, final int x, final int y, final int width, final int height, final int borderWidth, final int detail)
    {
	(new XEvent.ConfigureRequest(serial, sendEvent, valueMask, parent, window, above, x, y, width, height, borderWidth, detail)).broadcast();
    }
    
    
    public static void eventCreateNotify(final long serial, final boolean sendEvent, final int parent, final int window, final int x, final int y, final int width, final int height, final int borderWidth, final boolean overrideRedirect)
    {
	(new XEvent.CreateWindow(serial, sendEvent, parent, window, x, y, width, height, borderWidth, overrideRedirect)).broadcast();
    }
    
    
    public static void eventDestroyNotify(final long serial, final boolean sendEvent, final int event, final int window)
    {
	(new XEvent.DestroyWindow(serial, sendEvent, event, window)).broadcast();
    }
    
    
    public static void eventEnterNotify(final long serial, final boolean sendEvent, final int window, final int root, final int subwindow, final int time, final int x, final int y, final int xRoot, final int yRoot, final int mode, final int detail, final boolean sameScreen, final boolean focus, final int state)
    {
	(new XEvent.RatWindow(serial, sendEvent, window, root, subwindow, time, x, y, xRoot, yRoot, mode, detail, sameScreen, focus, state, XEvent.RatWindow.ENTER)).broadcast();
    }
    
    
    public static void eventExpose(final long serial, final boolean sendEvent, final int window, final int x, final int y, final int width, final int height, final int count)
    {
	(new XEvent.Expose(serial, sendEvent, window, x, y, width, height, count)).broadcast();
    }
    
    
    public static void eventFocusIn(final long serial, final boolean sendEvent, final int window, final int mode, final int detail)
    {
	(new XEvent.Focus(serial, sendEvent, window, mode, detail, XEvent.Focus.FOCUS_IN)).broadcast();
    }
    
    
    public static void eventFocusOut(final long serial, final boolean sendEvent, final int window, final int mode, final int detail)
    {
	(new XEvent.Focus(serial, sendEvent, window, mode, detail, XEvent.Focus.FOCUS_OUT)).broadcast();
    }
    
    
    public static void eventGraphicsExpose(final long serial, final boolean sendEvent, final int drawable, final int x, final int y, final int width, final int height, final int count, final int majorCode, final int minorCode)
    {
	(new XEvent.GraphicsExpose(serial, sendEvent, drawable, x, y, width, height, count, majorCode, minorCode)).broadcast();
    }
    
    
    public static void eventGravityNotify(final long serial, final boolean sendEvent, final int event, final int window, final int x, final int y)
    {
	(new XEvent.Gravity(serial, sendEvent, event, window, x, y)).broadcast();
    }
    
    
    public static void eventKeymapNotify(final long serial, final boolean sendEvent, final int window, final byte[] keyVector)
    {
	(new XEvent.Keymap(serial, sendEvent, window, keyVector)).broadcast();
    }
    
    
    public static void eventKeyPress(final long serial, final boolean sendEvent, final int time, final int window, final int root, final int subwindow, final int x, final int y, final int xRoot, final int yRoot, final int state, final int keycode, final boolean sameScreen)
    {
	(new XEvent.Key(serial, sendEvent, time, window, root, subwindow, x, y, xRoot, yRoot, state, keycode, sameScreen, XEvent.Key.PRESSED)).broadcast();
    }
    
    
    public static void eventKeyRelease(final long serial, final boolean sendEvent, final int time, final int window, final int root, final int subwindow, final int x, final int y, final int xRoot, final int yRoot, final int state, final int keycode, final boolean sameScreen)
    {
	(new XEvent.Key(serial, sendEvent, time, window, root, subwindow, x, y, xRoot, yRoot, state, keycode, sameScreen, XEvent.Key.RELEASED)).broadcast();
    }
    
    
    public static void eventLeaveNotify(final long serial, final boolean sendEvent, final int window, final int root, final int subwindow, final int time, final int x, final int y, final int xRoot, final int yRoot, final int mode, final int detail, final boolean sameScreen, final boolean focus, final int state)
    {
	(new XEvent.RatWindow(serial, sendEvent, window, root, subwindow, time, x, y, xRoot, yRoot, mode, detail, sameScreen, focus, state, XEvent.RatWindow.LEAVE)).broadcast();
    }
    
    
    public static void eventMapNotify(final long serial, final boolean sendEvent, final int event, final int window, final boolean overrideRedirect)
    {
	(new XEvent.MapNotify(serial, sendEvent, event, window, overrideRedirect)).broadcast();
    }
    
    
    public static void eventMappingNotify(final long serial, final boolean sendEvent, final int window, final int request, final int firstKeycode, final int count)
    {
	(new XEvent.MappingNotify(serial, sendEvent, window, request, firstKeycode, count)).broadcast();
    }
    
    
    public static void eventMapRequest(final long serial, final boolean sendEvent, final int parent, final int window)
    {
	(new XEvent.MapRequest(serial, sendEvent, parent, window)).broadcast();
    }
    
    
    public static void eventMotionNotify(final long serial, final boolean sendEvent, final int time, final int window, final int root, final int subwindow, final int x, final int y, final int xRoot, final int yRoot, final int state, final boolean sameScreen, final boolean isHint)
    {
	(new XEvent.RatMove(serial, sendEvent, time, window, root, subwindow, x, y, xRoot, yRoot, state, sameScreen, isHint)).broadcast();
    }
    
    
    public static void eventNoExpose(final long serial, final boolean sendEvent, final int drawable, final int majorCode, final int minorCode)
    {
	(new XEvent.NoExpose(serial, sendEvent, drawable, majorCode, minorCode)).broadcast();
    }
    
    
    public static void eventPropertyNotify(final long serial, final boolean sendEvent, final int window, final int time, final int state, final int atom)
    {
	(new XEvent.PropertyNotify(serial, sendEvent, window, time, state, atom)).broadcast();
    }
    
    
    public static void eventReparentNotify(final long serial, final boolean sendEvent, final int event, final int window, final int parent, final int x, final int y, final boolean overrideRedirect)
    {
	(new XEvent.ReparentNotify(serial, sendEvent, event, window, parent, x, y, overrideRedirect)).broadcast();
    }
    
    
    public static void eventResizeRequest(final long serial, final boolean sendEvent, final int event, final int window, final int width, final int height)
    {
	(new XEvent.ResizeRequest(serial, sendEvent, event, window, width, height)).broadcast();
    }
    
    
    public static void eventSelectionClear(final long serial, final boolean sendEvent, final int window, final int time, final int selection)
    {
	(new XEvent.SelectionClear(serial, sendEvent, window, time, selection)).broadcast();
    }
    
    
    public static void eventSelectionNotify(final long serial, final boolean sendEvent, final int requestor, final int time, final int selection, final int target, final int property)
    {
	(new XEvent.SelectionNotify(serial, sendEvent, requestor, time, selection, target, property)).broadcast();
    }
    
    
    public static void eventSelectionRequest(final long serial, final boolean sendEvent, final int owner, final int requestor, final int time, final int selection, final int target, final int property)
    {
	(new XEvent.SelectionRequest(serial, sendEvent, owner, requestor, time, selection, target, property)).broadcast();
    }
    
    
    public static void eventUnmapNotify(final long serial, final boolean sendEvent, final int event, final int window, final boolean fromConfigure)
    {
	(new XEvent.UnmapNotify(serial, sendEvent, event, window, fromConfigure)).broadcast();
    }
    
    
    public static void eventVisibilityNotify(final long serial, final boolean sendEvent, final int window, final boolean state)
    {
	(new XEvent.VisibilityNotify(serial, sendEvent, window, state)).broadcast();
    }
    
}

