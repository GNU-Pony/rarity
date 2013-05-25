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
     * Constructor hiding
     */
    private Rarity()
    {
	/* do nothing */
    }
    
    
    
    /**
     * Mane method
     * 
     * @param  args  Command line arguments excluding program name
     */
    public static void main(final String... args)
    {
	try
	{
	    setLocale();
	    setXAtoms();
	}
	catch (final Throwable err)
	{
	    err.printStackTrace(System.err);
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
    
}

