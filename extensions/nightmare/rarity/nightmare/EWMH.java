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
 * Extended Window Manager Hints
 * 
 * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class EWMH
{
    /**
     * Window type hints, in order of preference
     */
    public static final XAtom _NET_WM_WINDOW_TYPE = new XAtom("_NET_WM_WINDOW_TYPE", true);
    
    /**
     * Window spanning the entire screen and acts a replacement for the root window
     */
    public static final int _NET_WM_WINDOW_TYPE_DESKTOP = (new XAtom("_NET_WM_WINDOW_TYPE_DESKTOP", true)).atom;
    
    /**
     * Dock or panel, typically on top of all windows or shrinking the usable real estate
     */
    public static final int _NET_WM_WINDOW_TYPE_DOCK = (new XAtom("_NET_WM_WINDOW_TYPE_DOCK", true)).atom;
    
    /**
     * Torn off toolbar, WM_TRANSIENT_FOR may have been set to indicate the main window
     */
    public static final int _NET_WM_WINDOW_TYPE_TOOLBAR = (new XAtom("_NET_WM_WINDOW_TYPE_TOOLBAR", true)).atom;
    
    /**
     * Torn off menu, WM_TRANSIENT_FOR may have been set to indicate the main window
     */
    public static final int _NET_WM_WINDOW_TYPE_MENU = (new XAtom("_NET_WM_WINDOW_TYPE_MENU", true)).atom;
    
    /**
     * Small persistent utility window, such as a palette or toolbox,
     * WM_TRANSIENT_FOR may have been set to indicate the main window
     */
    public static final int _NET_WM_WINDOW_TYPE_UTILITY = (new XAtom("_NET_WM_WINDOW_TYPE_UTILITY", true)).atom;
    
    /**
     * Splash screen displayed as an application is starting up
     */
    public static final int _NET_WM_WINDOW_TYPE_SPLASH = (new XAtom("_NET_WM_WINDOW_TYPE_SPLASH", true)).atom;
    
    /**
     * Dialogue window
     */
    public static final int _NET_WM_WINDOW_TYPE_DIALOG = (new XAtom("_NET_WM_WINDOW_TYPE_DIALOG", true)).atom;
    
    /**
     * Dropdown menu, typically appears when the user clicks on a menubar
     */
    public static final int _NET_WM_WINDOW_TYPE_DROPDOWN_MENU = (new XAtom("_NET_WM_WINDOW_TYPE_DROPDOWN_MENU", true)).atom;
    
    /**
     * Popup menu, typically appears when the user right clicks on more advanced components such as text areas
     */
    public static final int _NET_WM_WINDOW_TYPE_POPUP_MENU = (new XAtom("_NET_WM_WINDOW_TYPE_POPUP_MENU", true)).atom;
    
    /**
     * Small window with explanatory text, typically appears when the user has hovered the rat over an object for a while
     */
    public static final int _NET_WM_WINDOW_TYPE_TOOLTIP = (new XAtom("_NET_WM_WINDOW_TYPE_TOOLTIP", true)).atom;
    
    /**
     * Notification window, for example a balloon in the corner of the monitor
     */
    public static final int _NET_WM_WINDOW_TYPE_NOTIFICATION = (new XAtom("_NET_WM_WINDOW_TYPE_NOTIFICATION", true)).atom;
    
    /**
     * Combo box menu window
     */
    public static final int _NET_WM_WINDOW_TYPE_COMBO = (new XAtom("_NET_WM_WINDOW_TYPE_COMBO", true)).atom;
    
    /**
     * Window containing a representation of an object that is being dragged
     */
    public static final int _NET_WM_WINDOW_TYPE_DND = (new XAtom("_NET_WM_WINDOW_TYPE_DND", true)).atom;
    
    /**
     * Normal, top-level, window
     */
    public static final int _NET_WM_WINDOW_TYPE_NORMAL = (new XAtom("_NET_WM_WINDOW_TYPE_NORMAL", true)).atom;
    
    
    
    public static final XAtom WM_TRANSIENT_FOR = (new XAtom("WM_TRANSIENT_FOR", true)).atom;
    
}

