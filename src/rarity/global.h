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
#include <X11/Xlib.h>


/* X11 display and screen variables */

/**
 * The X11 display we are using
 */
Display* display = 0;


/* Xinerama variables */

/**
 * Whether we have Xinerama enabled
 */
int have_xinerama = 0;

/**
 * The number of Xinerama screens we have
 */
int xinerama_screen_count = 0;


/* text atoms */

Atom xa_string;
Atom xa_compound_text;
Atom xa_utf8_string;


/* wm atoms */

Atom wm_name;
Atom wm_state;
Atom wm_change_state;
Atom wm_protocols;
Atom wm_delete;
Atom wm_take_focus;
Atom wm_colormaps;


/* _net_wm atoms */

Atom _net_wm_pid;
Atom _net_supported;
Atom _net_wm_window_type;
Atom _net_wm_window_type_dialog;
Atom _net_wm_name;

