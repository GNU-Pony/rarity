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



void eventButtonPress(XEvent* xe)
{
  XButtonEvent e = xe->xbutton;
}

void eventButtonRelease(XEvent* xe)
{
  XButtonEvent e = xe->xbutton;
}

void eventClientMessage(XEvent* xe)
{
  XClientMessageEvent e = xe->xclient;
}

void eventCirculateNotify(XEvent* xe)
{
  XCirculateEvent e = xe->xcirculate;
}

void eventCirculateRequest(XEvent* xe)
{
  XCirculateRequestEvent e = xe->xcirculaterequest;
}

void eventColormapNotify(XEvent* xe)
{
  XColormapEvent e = xe->xcolormap;
}

void eventConfigureNotify(XEvent* xe)
{
  XConfigureEvent e = xe->xconfigure;
}

void eventConfigureRequest(XEvent* xe)
{
  XConfigureRequestEvent e = xe->xconfigurerequest;
}

void eventCreateNotify(XEvent* xe)
{
  XCreateWindowEvent e = xe->xcreatewindow;
}

void eventDestroyNotify(XEvent* xe)
{
  XDestroyWindowEvent e = xe->xdestroywindow;
}

void eventEnterNotify(XEvent* xe)
{
  XCrossingEvent e = xe->xcrossing;
}

void eventExpose(XEvent* xe)
{
  XExposeEvent e = xe->xexpose;
}

void eventFocusIn(XEvent* xe)
{
  XFocusChangeEvent e = xe->xfocus;
}

void eventFocusOut(XEvent* xe)
{
  XFocusChangeEvent e = xe->xfocus;
}

void eventGraphicsExpose(XEvent* xe)
{
  XGraphicsExposeEvent e = xe->xgraphicsexpose;
}

void eventGravityNotify(XEvent* xe)
{
  XGravityEvent e = xe->xgravity;
}

void eventKeymapNotify(XEvent* xe)
{
  XKeymapEvent e = xe->xkeymap;
}

void eventKeyPress(XEvent* xe)
{
  XKeyEvent e = xe->xkey;
}

void eventKeyRelease(XEvent* xe)
{
  XKeyEvent e = xe->xkey;
}

void eventLeaveNotify(XEvent* xe)
{
  XCrossingEvent e = xe->xcrossing;
}

void eventMapNotify(XEvent* xe)
{
  XMapEvent e = xe->xmap;
}

void eventMappingNotify(XEvent* xe)
{
  XMappingEvent e = xe->xmapping;
}

void eventMapRequest(XEvent* xe)
{
  XMapRequestEvent e = xe->xmaprequest;
}

void eventMotionNotify(XEvent* xe)
{
  XMotionEvent e = xe->xmotion;
}

void eventNoExpose(XEvent* xe)
{
  XNoExposeEvent e = xe->xnoexpose;
}

void eventPropertyNotify(XEvent* xe)
{
  XPropertyEvent e = xe->xproperty;
}

void eventReparentNotify(XEvent* xe)
{
  XReparentEvent e = xe->xreparent;
}

void eventResizeRequest(XEvent* xe)
{
  XResizeRequestEvent e = xe->xresizerequest;
}

void eventSelectionClear(XEvent* xe)
{
  XSelectionClearEvent e = xe->xselectionclear;
}

void eventSelectionNotify(XEvent* xe)
{
  XSelectionEvent e = xe->xselection;
}

void eventSelectionRequest(XEvent* xe)
{
  XSelectionRequestEvent e = xe->xselectionrequest;
}

void eventUnmapNotify(XEvent* xe)
{
  XUnmapEvent e = xe->xunmap;
}

void eventVisibilityNotify(XEvent* xe)
{
  XVisibilityEvent e = xe->xvisibility;
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

