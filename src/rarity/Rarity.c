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
#include "Rarity.h"
#include "global.h"


/**
 * The X11 display we are using
 */
Display* display = 0;

/**
 * Whether we have Xinerama enabled
 */
int have_xinerama = 0;

/**
 * The number of Xinerama screens we have
 */
int xinerama_screen_count = 0;

/**
 * Whether the event handlers have been set
 */
long event_handlers_initialised = 0;

/**
 * Event handlers
 */
void (*event_handlers[32])(XEvent*);



/**
 * Set locale stuff
 */
void Java_rarity_Rarity_setLocale(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  setlocale(LC_CTYPE, "");
  if (XSupportsLocale())
    XSetLocaleModifiers("");
}


/**
 * Sets X atoms
 */
void Java_rarity_Rarity_setXAtoms(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  
  xa_string                  = XA_STRING;
  xa_compound_text           = XInternAtom(display, "COMPOUND_TEXT",              0);
  xa_utf8_string             = XInternAtom(display, "UTF8_STRING",                0);
  
  wm_name                    = XInternAtom(display, "WM_NAME",                    0);
  wm_state                   = XInternAtom(display, "WM_STATE",                   0);
  wm_change_state            = XInternAtom(display, "WM_CHANGE_STATE",            0);
  wm_protocols               = XInternAtom(display, "WM_PROTOCOLS",               0);
  wm_delete                  = XInternAtom(display, "WM_DELETE_WINDOW",           0);
  wm_take_focus              = XInternAtom(display, "WM_TAKE_FOCUS",              0);
  wm_colormaps               = XInternAtom(display, "WM_COLORMAP_WINDOWS",        0);
  
  _net_wm_pid                = XInternAtom(display, "_NET_WM_PID",                0);
  _net_supported             = XInternAtom(display, "_NET_SUPPORTED",             0);
  _net_wm_window_type        = XInternAtom(display, "_NET_WM_WINDOW_TYPE",        0);
  _net_wm_window_type_dialog = XInternAtom(display, "_NET_WM_WINDOW_TYPE_DIALOG", 0);
  _net_wm_name               = XInternAtom(display, "_NET_WM_NAME",               0);
}


/**
 * Exit with SIGABRT
 */
void Java_rarity_Rarity_abort(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  abort();
}



/**
 * Rat button press
 * 
 * @param  xe  The X event
 */
void eventButtonPress(XEvent* xe)
{
  XButtonEvent e = xe->xbutton;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, time_ms = e.time;
  int window = e.window, root = e.root, subwindow = e.subwindow;
  int x = e.x, y = e.y, x_root = e.x_root, y_root = e.y_root;
  int state = e.state, button = e.button, same_screen = e.same_screen;
}

/**
 * Rat button release
 * 
 * @param  xe  The X event
 */
void eventButtonRelease(XEvent* xe)
{
  XButtonEvent e = xe->xbutton;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, time_ms = e.time;
  int window = e.window, root = e.root, subwindow = e.subwindow;
  int x = e.x, y = e.y, x_root = e.x_root, y_root = e.y_root;
  int state = e.state, button = e.button, same_screen = e.same_screen;
}

/**
 * TODO doc eventClientMessage
 * 
 * @param  xe  The X event
 */
void eventClientMessage(XEvent* xe)
{
  XClientMessageEvent e = xe->xclient;
  long long serial = (long long)e.serial;
  int send_event = e.send_event;
  int window = e.window, format = e.format;
  Atom message_type = e.message_type;
  char data[20] = e.data.b;
}

/**
 * TODO doc eventCirculateNotify
 * 
 * @param  xe  The X event
 */
void eventCirculateNotify(XEvent* xe)
{
  XCirculateEvent e = xe->xcirculate;
  long long serial = (long long)e.serial;
  int send_event = e.send_event;
  int event = e.event, window = e.window, place = e.place;
}

/**
 * TODO doc eventCirculateRequest
 * 
 * @param  xe  The X event
 */
void eventCirculateRequest(XEvent* xe)
{
  XCirculateRequestEvent e = xe->xcirculaterequest;
  long long serial = (long long)e.serial;
  int send_event = e.send_event;
  int parent = e.parent, window = e.window, place = e.place;
}

/**
 * TODO doc eventColormapNotify
 * 
 * @param  xe  The X event
 */
void eventColormapNotify(XEvent* xe)
{
  XColormapEvent e = xe->xcolormap;
  long long serial = (long long)e.serial;
  int send_event = e.send_event;
  int window = e.window, colormap = e.colormap, new = e.new, state = e.state;
}

/**
 * TODO doc eventConfigureNotify
 * 
 * @param  xe  The X event
 */
void eventConfigureNotify(XEvent* xe)
{
  XConfigureEvent e = xe->xconfigure;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, event = e.event, window = e.window, above = e.above;
  int x = e.x, y = e.y, width = e.width, height = e.height, border_width = e.border_width, override_redirect = e.override_redirect;
}

/**
 * TODO doc eventConfigureRequest
 * 
 * @param  xe  The X event
 */
void eventConfigureRequest(XEvent* xe)
{
  XConfigureRequestEvent e = xe->xconfigurerequest;
  long long serial = (long long)e.serial, value_mask = (long long)e.value_mask;
  int send_event = e.send_event, parent = e.parent, window = e.window, above = e.above;
  int x = e.x, y = e.y, width = e.width, height = e.height, border_width = e.border_width, detail = e.detail;
}

/**
 * A new window has been created
 * 
 * @param  xe  The X event
 */
void eventCreateNotify(XEvent* xe)
{
  XCreateWindowEvent e = xe->xcreatewindow;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, parent = e.parent, window = e.window;
  int x = e.x, y = e.y, width = e.width, height = e.height, border_width = e.border_width, override_redirect = e.override_redirect;
}

/**
 * A window has been destroyed
 * 
 * @param  xe  The X event
 */
void eventDestroyNotify(XEvent* xe)
{
  XDestroyWindowEvent e = xe->xdestroywindow;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, event = e.event, window = e.window;
}

/**
 * Rat enters window
 * 
 * @param  xe  The X event
 */
void eventEnterNotify(XEvent* xe)
{
  XCrossingEvent e = xe->xcrossing;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window, root = e.root, subwindow = e.subwindow, time = e.time;
  int x = e.x, y = e.y, x_root = e.x_root, y_root = e.y_root, mode = e.mode, detail = e.detail;
  int same_screen = e.same_screen, focus = e.focus, state = e.state;
}

/**
 * TODO doc eventExpose
 * 
 * @param  xe  The X event
 */
void eventExpose(XEvent* xe)
{
  XExposeEvent e = xe->xexpose;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window;
  int x = e.x, y = e.y, width = e.width, height = e.height, count = e.count;
}

/**
 * Window gets focus
 * 
 * @param  xe  The X event
 */
void eventFocusIn(XEvent* xe)
{
  XFocusChangeEvent e = xe->xfocus;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window;
  int mode = e.mode, detail = e.detail;
}

/**
 * Window loses focus
 * 
 * @param  xe  The X event
 */
void eventFocusOut(XEvent* xe)
{
  XFocusChangeEvent e = xe->xfocus;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window;
  int mode = e.mode, detail = e.detail;
}

/**
 * TODO doc eventGraphicsExpose
 * 
 * @param  xe  The X event
 */
void eventGraphicsExpose(XEvent* xe)
{
  XGraphicsExposeEvent e = xe->xgraphicsexpose;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, drawable = e.drawable;
  int x = e.x, y = e.y, width = e.width, height = e.height, count = e.count, major_code = e.major_code, minor_code = e.minor_code;
}

/**
 * TODO doc eventGravityNotify
 * 
 * @param  xe  The X event
 */
void eventGravityNotify(XEvent* xe)
{
  XGravityEvent e = xe->xgravity;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, event = e.event, window = e.window;
  int x = e.x, y = e.y;
}

/**
 * TODO doc eventKeymapNotify
 * 
 * @param  xe  The X event
 */
void eventKeymapNotify(XEvent* xe)
{
  XKeymapEvent e = xe->xkeymap;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window;
  char key_vector[32] = e.key_vector;
}

/**
 * Keyboard key pressed
 * 
 * @param  xe  The X event
 */
void eventKeyPress(XEvent* xe)
{
  XKeyEvent e = xe->xkey;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, time_ms = e.time;
  int window = e.window, root = e.root, subwindow = e.subwindow;
  int x = e.x, y = e.y, x_root = e.x_root, y_root = e.y_root;
  int state = e.state, keycode = e.keycode, same_screen = e.same_screen;
}

/**
 * Keyboard key released
 * 
 * @param  xe  The X event
 */
void eventKeyRelease(XEvent* xe)
{
  XKeyEvent e = xe->xkey;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, time_ms = e.time;
  int window = e.window, root = e.root, subwindow = e.subwindow;
  int x = e.x, y = e.y, x_root = e.x_root, y_root = e.y_root;
  int state = e.state, keycode = e.keycode, same_screen = e.same_screen;
}

/**
 * Rat leaves window
 * 
 * @param  xe  The X event
 */
void eventLeaveNotify(XEvent* xe)
{
  XCrossingEvent e = xe->xcrossing;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window, root = e.root, subwindow = e.subwindow, time = e.time;
  int x = e.x, y = e.y, x_root = e.x_root, y_root = e.y_root, mode = e.mode, detail = e.detail;
  int same_screen = e.same_screen, focus = e.focus, state = e.state;
}

/**
 * TODO doc eventMapNotify
 * 
 * @param  xe  The X event
 */
void eventMapNotify(XEvent* xe)
{
  XMapEvent e = xe->xmap;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, event = e.event, window = e.window;
  int override_redirect = e.override_redirect;
}

/**
 * TODO doc eventMappingNotify
 * 
 * @param  xe  The X event
 */
void eventMappingNotify(XEvent* xe)
{
  XMappingEvent e = xe->xmapping;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, event = e.event, window = e.window;
  int override_redirect = e.override_redirect;
}

/**
 * TODO doc eventMapRequest
 * 
 * @param  xe  The X event
 */
void eventMapRequest(XEvent* xe)
{
  XMapRequestEvent e = xe->xmaprequest;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, parent = e.parent, window = e.window;
}

/**
 * Rat moves
 * 
 * @param  xe  The X event
 */
void eventMotionNotify(XEvent* xe)
{
  XMotionEvent e = xe->xmotion;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, time_ms = e.time;
  int window = e.window, root = e.root, subwindow = e.subwindow;
  int x = e.x, y = e.y, x_root = e.x_root, y_root = e.y_root;
  int state = e.state, same_screen = e.same_screen;
  char is_hint = e.is_hint;
}

/**
 * TODO doc eventNoExpose
 * 
 * @param  xe  The X event
 */
void eventNoExpose(XEvent* xe)
{
  XNoExposeEvent e = xe->xnoexpose;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, drawable = e.drawable;
  int major_code = e.major_code, minor_code = e.minor_code;
}

/**
 * TODO doc eventPropertyNotify
 * 
 * @param  xe  The X event
 */
void eventPropertyNotify(XEvent* xe)
{
  XPropertyEvent e = xe->xproperty;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window, time = e.time, state = e.state;
  Atom atom = e.atom;
}

/**
 * TODO doc eventReparentNotify
 * 
 * @param  xe  The X event
 */
void eventReparentNotify(XEvent* xe)
{
  XReparentEvent e = xe->xreparent;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, event = e.event, window = e.window, parent = e.parent;
  int x = e.x, y = e.y, override_redirect = e.override_redirect;
}

/**
 * TODO doc eventResizeRequest
 * 
 * @param  xe  The X event
 */
void eventResizeRequest(XEvent* xe)
{
  XResizeRequestEvent e = xe->xresizerequest;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window;
  int width = e.width, height = e.height;
}

/**
 * TODO doc eventSelectionClear
 * 
 * @param  xe  The X event
 */
void eventSelectionClear(XEvent* xe)
{
  XSelectionClearEvent e = xe->xselectionclear;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window, time = e.time;
  Atom selection = e.selection;
}

/**
 * TODO doc eventSelectionNotify
 * 
 * @param  xe  The X event
 */
void eventSelectionNotify(XEvent* xe)
{
  XSelectionEvent e = xe->xselection;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, requestor = e.requestor, time = e.time;
  Atom selection = e.selection, target = e.target, property = e.property;
}

/**
 * TODO doc eventSelectionRequest
 * 
 * @param  xe  The X event
 */
void eventSelectionRequest(XEvent* xe)
{
  XSelectionRequestEvent e = xe->xselectionrequest;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, owner = e.owner, requestor = e.requestor, time = e.time;
  Atom selection = e.selection, target = e.target, property = e.property;
}

/**
 * TODO doc eventUnmapNotify
 * 
 * @param  xe  The X event
 */
void eventUnmapNotify(XEvent* xe)
{
  XUnmapEvent e = xe->xunmap;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, event = e.event, window = e.window, from_configure = from_configure;
}

/**
 * 
 * 
 * @param  xe  The X event
 */
void eventVisibilityNotify(XEvent* xe)
{
  XVisibilityEvent e = xe->xvisibility;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window, state = state;
}



/**
 * Start event loop
 */
void Java_rarity_Rarity_eventLoop(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  
  XEvent e;
  int x_fd = ConnectionNumber(display);
  fd_set fd_set;
  FD_ZERO(&fd_set);
  
  if (event_handlers_initialised == 0)
    {
      long i;
      event_handlers_initialised = 1;
      for (i = 0; i < 32; i++)
	*(event_handlers + i) = 0;
      
      *(event_handlers + ButtonPress)      = eventButtonPress;
      *(event_handlers + ButtonRelease)    = eventButtonRelease;
      *(event_handlers + ClientMessage)    = eventClientMessage;
      *(event_handlers + CirculateNotify)  = eventCirculateNotify;
      *(event_handlers + CirculateRequest) = eventCirculateRequest;
      *(event_handlers + ColormapNotify)   = eventColormapNotify;
      *(event_handlers + ConfigureNotify)  = eventConfigureNotify;
      *(event_handlers + ConfigureRequest) = eventConfigureRequest;
      *(event_handlers + CreateNotify)     = eventCreateNotify;
      *(event_handlers + DestroyNotify)    = eventDestroyNotify;
      *(event_handlers + EnterNotify)      = eventEnterNotify;
      *(event_handlers + Expose)           = eventExpose;
      *(event_handlers + FocusIn)          = eventFocusIn;
      *(event_handlers + FocusOut)         = eventFocusOut;
      *(event_handlers + GraphicsExpose)   = eventGraphicsExpose;
      *(event_handlers + GravityNotify)    = eventGravityNotify;
      *(event_handlers + KeymapNotify)     = eventKeymapNotify;
      *(event_handlers + KeyPress)         = eventKeyPress;
      *(event_handlers + KeyRelease)       = eventKeyRelease;
      *(event_handlers + LeaveNotify)      = eventLeaveNotify;
      *(event_handlers + MapNotify)        = eventMapNotify;
      *(event_handlers + MappingNotify)    = eventMappingNotify;
      *(event_handlers + MapRequest)       = eventMapRequest;
      *(event_handlers + MotionNotify)     = eventMotionNotify;
      *(event_handlers + NoExpose)         = eventNoExpose;
      *(event_handlers + PropertyNotify)   = eventPropertyNotify;
      *(event_handlers + ReparentNotify)   = eventReparentNotify;
      *(event_handlers + ResizeRequest)    = eventResizeRequest;
      *(event_handlers + SelectionClear)   = eventSelectionClear;
      *(event_handlers + SelectionNotify)  = eventSelectionNotify;
      *(event_handlers + SelectionRequest) = eventSelectionRequest;
      *(event_handlers + UnmapNotify)      = eventUnmapNotify;
      *(event_handlers + VisibilityNotify) = eventVisibilityNotify;
    }
  
  for (;;)
    {
      /* TODO handle graceful exit */
      
      FD_SET(x_fd, &fd_set);
      XFlush(display);
      
      if ((QLength(display) > 0) || (select(x_fd + 1, &fd_set, 0, 0, 0) == 1))
        {
          XNextEvent(display, &e);
	  if (e.type < 32)
	    {
	      void (*function)(XEvent*) = *(event_handlers + e.type);
	      if (function != 0)
		function(&e);
	    }
          XSync(display, 0);
	}
    }
}



/**
 * Gets the pointer to the atom for XA_STRING
 * 
 * @param  The pointer to the atom
 */
jlong Java_rarity_Rarity_getAtomPointerXA_1STRING(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return (jlong)(void*)&xa_string;
}


/**
 * Gets the pointer to the atom for XA_COMPOUND_TEXT
 * 
 * @param  The pointer to the atom
 */
jlong Java_rarity_Rarity_getAtomPointerXA_1COMPOUND_1TEXT(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return (jlong)(void*)&xa_compound_text;
}


/**
 * Gets the pointer to the atom for XA_UTF8_STRING
 * 
 * @param  The pointer to the atom
 */
jlong Java_rarity_Rarity_getAtomPointerXA_1UTF8_1STRING(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return (jlong)(void*)&xa_utf8_string;
}

 

/**
 * Gets the pointer to the atom for WM_NAME
 * 
 * @param  The pointer to the atom
 */
jlong Java_rarity_Rarity_getAtomPointerWM_1NAME(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return (jlong)(void*)&wm_name;
}


/**
 * Gets the pointer to the atom for WM_STATE
 * 
 * @param  The pointer to the atom
 */
jlong Java_rarity_Rarity_getAtomPointerWM_1STATE(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return (jlong)(void*)&wm_state;
}


/**
 * Gets the pointer to the atom for WM_CHANGE_STATE
 * 
 * @param  The pointer to the atom
 */
jlong Java_rarity_Rarity_getAtomPointerWM_1CHANGE_1STATE(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return (jlong)(void*)&wm_change_state;
}


/**
 * Gets the pointer to the atom for WM_PROTOCOLS
 * 
 * @param  The pointer to the atom
 */
jlong Java_rarity_Rarity_getAtomPointerWM_1PROTOCOLS(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return (jlong)(void*)&wm_protocols;
}


/**
 * Gets the pointer to the atom for WM_DELETE
 * 
 * @param  The pointer to the atom
 */
jlong Java_rarity_Rarity_getAtomPointerWM_1DELETE(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return (jlong)(void*)&wm_delete;
}


/**
 * Gets the pointer to the atom for WM_TAKE_FOCUS
 * 
 * @param  The pointer to the atom
 */
jlong Java_rarity_Rarity_getAtomPointerWM_1TAKE_1FOCUS(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return (jlong)(void*)&wm_take_focus;
}


/**
 * Gets the pointer to the atom for WM_COLORMAPS
 * 
 * @param  The pointer to the atom
 */
jlong Java_rarity_Rarity_getAtomPointerWM_1COLORMAPS(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return (jlong)(void*)&wm_colormaps;
}



/**
 * Gets the pointer to the atom for _NET_WM_PID
 * 
 * @param  The pointer to the atom
 */
jlong Java_rarity_Rarity_getAtomPointer_1NET_1WM_1PID(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return (jlong)(void*)&_net_wm_pid;
}


/**
 * Gets the pointer to the atom for _NET_SUPPORTED
 * 
 * @param  The pointer to the atom
 */
jlong Java_rarity_Rarity_getAtomPointer_1NET_1SUPPORTED(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return (jlong)(void*)&_net_supported;
}


/**
 * Gets the pointer to the atom for _NET_WM_WINDOW_TYPE
 * 
 * @param  The pointer to the atom
 */
jlong Java_rarity_Rarity_getAtomPointer_1NET_1WM_1WINDOW_1TYPE(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return (jlong)(void*)&_net_wm_window_type;
}


/**
 * Gets the pointer to the atom for _NET_WM_WINDOW_TYPE_DIALOG
 * 
 * @param  The pointer to the atom
 */
jlong Java_rarity_Rarity_getAtomPointer_1NET_1WM_1WINDOW_1TYPE_1DIALOG(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return (jlong)(void*)&_net_wm_window_type_dialog;
}


/**
 * Gets the pointer to the atom for _NET_WM_NAME
 * 
 * @param  The pointer to the atom
 */
jlong Java_rarity_Rarity_getAtomPointer_1NET_1WM_1NAME(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
  return (jlong)(void*)&_net_wm_name;
}


/**
 * Scan for existing windows
 * 
 * @param  screen  The index of the screen to scan
 */
void Java_rarity_Rarity_scanForWindows(JNIEnv* env, jclass class, jint screen)
{
  (void) class;
  unsigned int i, n;
  Window _root, _parent, *windows;
  XQueryTree(display, RootWindow(display, screen), &_root, &_parent, &windows, &n);
  for (i = 0; i < n; i++)
    {
      unsigned int width, height, _border, _depth;
      int _x, _y;
      XGetGeometry(display, *(windows + i), &_root, &_x, &_y, &width, &height, &_border, &_depth);
      jmethodID method = (*env)->GetStaticMethodID(env, class, "newWindow", "(III)V");
      (*env)->CallStaticVoidMethod(env, class, method, (jint)*(windows + i), (jint)width, (jint)height);
    }
  XFree(windows);
}

