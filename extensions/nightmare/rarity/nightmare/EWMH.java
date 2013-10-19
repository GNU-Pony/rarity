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
     * List of supported window states, stored on the root window
     */
    public static final XAtom _NET_SUPPORTED = new XAtom("_NET_SUPPORTED", true);
    
    /**
     * List, in order of age, of windows managed by the window mananger, stored on the root window
     */
    public static final XAtom _NET_CLIENT_LIST = new XAtom("_NET_CLIENT_LIST", true);
    
    /**
     * List, in bottom-to-stacking order, of windows managed by the window mananger, stored on the root window
     */
    public static final XAtom _NET_CLIENT_LIST_STACKING = new XAtom("_NET_CLIENT_LIST", true);
    
    public static final XAtom _NET_NUMBER_OF_DESKTOPS = new XAtom("_NET_NUMBER_OF_DESKTOPS", true);
    
    public static final XAtom _NET_DESKTOP_GEOMETRY = new XAtom("_NET_DESKTOP_GEOMETRY", true);
    
    public static final XAtom _NET_DESKTOP_VIEWPORT = new XAtom("_NET_DESKTOP_VIEWPORT", true);
    
    public static final XAtom _NET_CURRENT_DESKTOP = new XAtom("_NET_CURRENT_DESKTOP", true);
    
    public static final XAtom _NET_DESKTOP_NAMES = new XAtom("_NET_DESKTOP_NAMES", true);
    
    public static final XAtom _NET_ACTIVE_WINDOW = new XAtom("_NET_ACTIVE_WINDOW", true);
    
    public static final XAtom _NET_WORKAREA = new XAtom("_NET_WORKAREA", true);
    
    public static final XAtom _NET_SUPPORTING_WM_CHECK = new XAtom("_NET_SUPPORTING_WM_CHECK", true);
    
    public static final XAtom _NET_VIRTUAL_ROOTS = new XAtom("_NET_VIRTUAL_ROOTS", true);
    
    public static final XAtom _NET_DESKTOP_LAYOUT = new XAtom("_NET_DESKTOP_LAYOUT", true);
    
    public static final XAtom _NET_SHOWING_DESKTOP = new XAtom("_NET_SHOWING_DESKTOP", true);
    
    
    
    public static final XAtom _NET_CLOSE_WINDOW = new XAtom("_NET_CLOSE_WINDOW", true);
    
    public static final XAtom _NET_MOVERESIZE_WINDOW = new XAtom("_NET_MOVERESIZE_WINDOW", true);
    
    public static final XAtom _NET_WM_MOVERESIZE = new XAtom("_NET_WM_MOVERESIZE", true);
    
    public static final XAtom _NET_RESTACK_WINDOW = new XAtom("_NET_RESTACK_WINDOW", true);
    
    public static final XAtom _NET_REQUEST_FRAME_EXTENTS = new XAtom("_NET_REQUEST_FRAME_EXTENTS", true);
    
    
    
    public static final XAtom _NET_WM_NAME = new XAtom("_NET_WM_NAME", true);
    
    public static final XAtom _NET_WM_VISIBLE_NAME = new XAtom("_NET_WM_VISIBLE_NAME", true);
    
    public static final XAtom _NET_WM_ICON_NAME = new XAtom("_NET_WM_ICON_NAME", true);
    
    public static final XAtom _NET_WM_VISIBLE_ICON_NAME = new XAtom("_NET_WM_VISIBLE_ICON_NAME", true);
    
    public static final XAtom _NET_WM_DESKTOP = new XAtom("_NET_WM_DESKTOP", true);
    
    
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
    
    
    public static final XAtom _NET_WM_STATE = new XAtom("_NET_WM_STATE", true);
    
    public static final int _NET_WM_STATE_MODAL = (new XAtom("_NET_WM_STATE_MODAL", true)).atom;
    
    public static final int _NET_WM_STATE_STICKY = (new XAtom("_NET_WM_STATE_STICKY", true)).atom;
    
    public static final int _NET_WM_STATE_MAXIMIZED_VERT = (new XAtom("_NET_WM_STATE_MAXIMIZED_VERT", true)).atom;
    
    public static final int _NET_WM_STATE_MAXIMIZED_HORZ = (new XAtom("_NET_WM_STATE_MAXIMIZED_HORZ", true)).atom;
    
    public static final int _NET_WM_STATE_SHADED = (new XAtom("_NET_WM_STATE_SHADED", true)).atom;
    
    public static final int _NET_WM_STATE_SKIP_TASKBAR = (new XAtom("_NET_WM_STATE_SKIP_TASKBAR", true)).atom;
    
    public static final int _NET_WM_STATE_SKIP_PAGER = (new XAtom("_NET_WM_STATE_SKIP_PAGER", true)).atom;
    
    public static final int _NET_WM_STATE_HIDDEN = (new XAtom("_NET_WM_STATE_HIDDEN", true)).atom;
    
    public static final int _NET_WM_STATE_FULLSCREEN = (new XAtom("_NET_WM_STATE_FULLSCREEN", true)).atom;
    
    public static final int _NET_WM_STATE_ABOVE = (new XAtom("_NET_WM_STATE_ABOVE", true)).atom;
    
    public static final int _NET_WM_STATE_BELOW = (new XAtom("_NET_WM_STATE_BELOW", true)).atom;
    
    public static final int _NET_WM_STATE_DEMANDS_ATTENTION = (new XAtom("_NET_WM_STATE_DEMANDS_ATTENTION", true)).atom;
    
    public static final int _NET_WM_STATE_REMOVE = 0;
    
    public static final int _NET_WM_STATE_ADD = 1;
    
    public static final int _NET_WM_STATE_TOGGLE = 2;
    
    
    public static final XAtom _NET_WM_ALLOWED_ACTIONS = new XAtom("_NET_WM_ALLOWED_ACTIONS", true);
    
    public static final int _NET_WM_ACTION_MOVE = (new XAtom("_NET_WM_ACTION_MOVE", true)).atom;
    
    public static final int _NET_WM_ACTION_RESIZE = (new XAtom("_NET_WM_ACTION_RESIZE", true)).atom;
    
    public static final int _NET_WM_ACTION_MINIMIZE = (new XAtom("_NET_WM_ACTION_MINIMIZE", true)).atom;
    
    public static final int _NET_WM_ACTION_SHADE = (new XAtom("_NET_WM_ACTION_SHADE", true)).atom;
    
    public static final int _NET_WM_ACTION_STICK = (new XAtom("_NET_WM_ACTION_STICK", true)).atom;
    
    public static final int _NET_WM_ACTION_MAXIMIZE_HORZ = (new XAtom("_NET_WM_ACTION_MAXIMIZE_HORZ", true)).atom;
    
    public static final int _NET_WM_ACTION_MAXIMIZE_VERT = (new XAtom("_NET_WM_ACTION_MAXIMIZE_VERT", true)).atom;
    
    public static final int _NET_WM_ACTION_FULLSCREEN = (new XAtom("_NET_WM_ACTION_FULLSCREEN", true)).atom;
    
    public static final int _NET_WM_ACTION_CHANGE_DESKTOP = (new XAtom("_NET_WM_ACTION_CHANGE_DESKTOP", true)).atom;
    
    public static final int _NET_WM_ACTION_CLOSE = (new XAtom("_NET_WM_ACTION_CLOSE", true)).atom;
    
    public static final int _NET_WM_ACTION_ABOVE = (new XAtom("_NET_WM_ACTION_ABOVE", true)).atom;
    
    public static final int _NET_WM_ACTION_BELOW = (new XAtom("_NET_WM_ACTION_BELOW", true)).atom;
    
    
    public static final XAtom _NET_WM_STRUT = new XAtom("_NET_WM_STRUT", true);
    
    public static final XAtom _NET_WM_STRUT_PARTIAL = new XAtom("_NET_WM_STRUT_PARTIAL", true);
    
    public static final XAtom _NET_WM_ICON_GEOMETRY = new XAtom("_NET_WM_ICON_GEOMETRY", true);
    
    public static final XAtom _NET_WM_ICON = new XAtom("_NET_WM_ICON", true);
    
    public static final XAtom _NET_WM_PID = new XAtom("_NET_WM_PID", true);
    
    public static final XAtom _NET_WM_HANDLED_ICONS = new XAtom("_NET_WM_HANDLED_ICONS", true);
    
    public static final XAtom _NET_WM_USER_TIME = new XAtom("_NET_WM_USER_TIME", true);
    
    public static final XAtom _NET_WM_USER_TIME_WINDOW = new XAtom("_NET_WM_USER_TIME_WINDOW", true);
    
    public static final XAtom _NET_FRAME_EXTENTS = new XAtom("_NET_FRAME_EXTENTS", true);
    
    
    
    public static final XAtom _NET_WM_PING = new XAtom("_NET_WM_PING", true);
    
    public static final XAtom _NET_WM_SYNC_REQUEST = new XAtom("_NET_WM_SYNC_REQUEST", true);
    
    public static final XAtom _NET_WM_FULLSCREEN_MONITORS = new XAtom("_NET_WM_FULLSCREEN_MONITORS", true);
    
    
    
    public static final XAtom _NET_WM_FULL_PLACEMENT = new XAtom("_NET_WM_FULL_PLACEMENT", true);
    
    
    
    public static final XAtom WM_TRANSIENT_FOR = new XAtom("WM_TRANSIENT_FOR", true);
    
}

