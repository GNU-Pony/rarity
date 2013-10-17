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


/* Debugging macros */
#ifdef DEBUG
#define debug  fprintf
#else
static inline void debug(void* x, ...)
{
  (void) x;
  /* do nothing */
}
#endif



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
void (*event_handlers[32])(XEvent*, JNIEnv*, jclass);



/**
 * Initalise static stuff
 */
void Java_rarity_Rarity_staticInit(JNIEnv* env, jclass class)
{
  (void) env;
  (void) class;
}


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
  
  /* TODO: which atoms should be set and to what? */
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
 * @param  xe  The X event
 */
void eventButtonPress(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventButtonPress\n");
  XButtonEvent e = xe->xbutton;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, time_ms = e.time;
  int window = e.window, root = e.root, subwindow = e.subwindow;
  int x = e.x, y = e.y, x_root = e.x_root, y_root = e.y_root;
  int state = e.state, button = e.button, same_screen = e.same_screen;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventButtonPress", "(JZIIIIIIIIIIZ)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, time_ms, window, root, subwindow, x, y, x_root, y_root, state, button, same_screen);
  debug(stderr, "exits eventButtonPress\n");
}

/**
 * @param  xe  The X event
 */
void eventButtonRelease(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventButtonRelease\n");
  XButtonEvent e = xe->xbutton;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, time_ms = e.time;
  int window = e.window, root = e.root, subwindow = e.subwindow;
  int x = e.x, y = e.y, x_root = e.x_root, y_root = e.y_root;
  int state = e.state, button = e.button, same_screen = e.same_screen;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventButtonRelease", "(JZIIIIIIIIIIZ)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, time_ms, window, root, subwindow, x, y, x_root, y_root, state, button, same_screen);
  debug(stderr, "exits eventButtonRelease\n");
}

/**
 * @param  xe  The X event
 */
void eventClientMessage(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventClientMessage\n");
  XClientMessageEvent e = xe->xclient;
  long long serial = (long long)e.serial;
  int send_event = e.send_event;
  int window = e.window, format = e.format;
  Atom message_type = e.message_type;
  
  jbyteArray jdata = (*env)->NewByteArray(env, 20);
  (*env)->SetByteArrayRegion(env, jdata, 0, 20, (jbyte*)(e.data.b));
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventClientMessage", "(JZIJI[B)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, window, &message_type, format, jdata);
  debug(stderr, "exits eventClientMessage\n");
}

/**
 * @param  xe  The X event
 */
void eventCirculateNotify(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventCirculateNotify\n");
  XCirculateEvent e = xe->xcirculate;
  long long serial = (long long)e.serial;
  int send_event = e.send_event;
  int event = e.event, window = e.window, place = e.place;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventButtonRelease", "(JZIII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, event, window, place);
  debug(stderr, "exits eventCirculateNotify\n");
}

/**
 * @param  xe  The X event
 */
void eventCirculateRequest(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventCirculateRequest\n");
  XCirculateRequestEvent e = xe->xcirculaterequest;
  long long serial = (long long)e.serial;
  int send_event = e.send_event;
  int parent = e.parent, window = e.window, place = e.place;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventCirculateRequest", "(JZIII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, parent, window, place);
  debug(stderr, "exits eventCirculateRequest\n");
}

/**
 * @param  xe  The X event
 */
void eventColormapNotify(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventColormapNotify\n");
  XColormapEvent e = xe->xcolormap;
  long long serial = (long long)e.serial;
  int send_event = e.send_event;
  int window = e.window, colormap = e.colormap, new = e.new, state = e.state;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventColormapNotify", "(JZIIIZI)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, window, colormap, new, state);
  debug(stderr, "exits eventColormapNotify\n");
}

/**
 * @param  xe  The X event
 */
void eventConfigureNotify(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventConfigureNotify\n");
  XConfigureEvent e = xe->xconfigure;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, event = e.event, window = e.window, above = e.above;
  int x = e.x, y = e.y, width = e.width, height = e.height, border_width = e.border_width, override_redirect = e.override_redirect;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventConfigureNotify", "(JZIIIIIIIIZ)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, event, window, above, x, y, width, height, border_width, override_redirect);
  debug(stderr, "exits eventConfigureNotify\n");
}

/**
 * @param  xe  The X event
 */
void eventConfigureRequest(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventConfigureRequest\n");
  XConfigureRequestEvent e = xe->xconfigurerequest;
  long long serial = (long long)e.serial, value_mask = (long long)e.value_mask;
  int send_event = e.send_event, parent = e.parent, window = e.window, above = e.above;
  int x = e.x, y = e.y, width = e.width, height = e.height, border_width = e.border_width, detail = e.detail;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventConfigureRequest", "(JZJIIIIIIIII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, value_mask, parent, window, above, x, y, width, height, border_width, detail);
  debug(stderr, "exits eventConfigureRequest\n");
}

/**
 * @param  xe  The X event
 */
void eventCreateNotify(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventCreateNotify\n");
  XCreateWindowEvent e = xe->xcreatewindow;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, parent = e.parent, window = e.window;
  int x = e.x, y = e.y, width = e.width, height = e.height, border_width = e.border_width, override_redirect = e.override_redirect;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventCreateNotify", "(JZIIIIIIIZ)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, parent, window, x, y, width, height, border_width, override_redirect);
  debug(stderr, "exits eventCreateNotify\n");
}

/**
 * @param  xe  The X event
 */
void eventDestroyNotify(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventDestroyNotify\n");
  XDestroyWindowEvent e = xe->xdestroywindow;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, event = e.event, window = e.window;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventDestroyNotify", "(JZII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, event, window);
  debug(stderr, "exits eventDestroyNotify\n");
}

/**
 * @param  xe  The X event
 */
void eventEnterNotify(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventEnterNotify\n");
  XCrossingEvent e = xe->xcrossing;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window, root = e.root, subwindow = e.subwindow, time = e.time;
  int x = e.x, y = e.y, x_root = e.x_root, y_root = e.y_root, mode = e.mode, detail = e.detail;
  int same_screen = e.same_screen, focus = e.focus, state = e.state;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventEnterNotify", "(JZIIIIIIIIIIZZI)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, window, root, subwindow, time, x, y, x_root, y_root, mode, detail, same_screen, focus, state);
  debug(stderr, "exits eventEnterNotify\n");
}

/**
 * @param  xe  The X event
 */
void eventExpose(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventExpose\n");
  XExposeEvent e = xe->xexpose;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window;
  int x = e.x, y = e.y, width = e.width, height = e.height, count = e.count;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventExpose", "(JZIIIIII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, window, x, y, width, height, count);
  debug(stderr, "exits eventExpose\n");
}

/**
 * @param  xe  The X event
 */
void eventFocusIn(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventFocusIn\n");
  XFocusChangeEvent e = xe->xfocus;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window;
  int mode = e.mode, detail = e.detail;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventFocusIn", "(JZIII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, window, mode, detail);
  debug(stderr, "exits eventFocusIn\n");
}

/**
 * @param  xe  The X event
 */
void eventFocusOut(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventFocusOut\n");
  XFocusChangeEvent e = xe->xfocus;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window;
  int mode = e.mode, detail = e.detail;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventFocusOut", "(JZIII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, window, mode, detail);
  debug(stderr, "exits eventFocusOut\n");
}

/**
 * @param  xe  The X event
 */
void eventGraphicsExpose(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventGraphicsExpose\n");
  XGraphicsExposeEvent e = xe->xgraphicsexpose;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, drawable = e.drawable;
  int x = e.x, y = e.y, width = e.width, height = e.height, count = e.count, major_code = e.major_code, minor_code = e.minor_code;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventGraphicsExpose", "(JZIIIIIIII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, drawable, x, y, width, height, count, major_code, minor_code);
  debug(stderr, "exits eventGraphicsExpose\n");
}

/**
 * @param  xe  The X event
 */
void eventGravityNotify(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventGravityNotify\n");
  XGravityEvent e = xe->xgravity;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, event = e.event, window = e.window;
  int x = e.x, y = e.y;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventGravityNotify", "(JZIIII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, event, window, x, y);
  debug(stderr, "exits eventGravityNotify\n");
}

/**
 * @param  xe  The X event
 */
void eventKeymapNotify(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventKeymapNotify\n");
  XKeymapEvent e = xe->xkeymap;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window;
  
  jbyteArray jkey_vector = (*env)->NewByteArray(env, 32);
  (*env)->SetByteArrayRegion(env, jkey_vector, 0, 32, (jbyte*)(e.key_vector));
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventKeymapNotify", "(JZI[B)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, window, jkey_vector);
  debug(stderr, "exits eventKeymapNotify\n");
}

/**
 * @param  xe  The X event
 */
void eventKeyPress(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventKeyPress\n");
  XKeyEvent e = xe->xkey;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, time_ms = e.time;
  int window = e.window, root = e.root, subwindow = e.subwindow;
  int x = e.x, y = e.y, x_root = e.x_root, y_root = e.y_root;
  int state = e.state, keycode = e.keycode, same_screen = e.same_screen;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventKeyPress", "(JZIIIIIIIIIIZ)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, time_ms, window, root, subwindow, x, y, x_root, y_root, state, keycode, same_screen);
  debug(stderr, "exits eventKeyPress\n");
}

/**
 * @param  xe  The X event
 */
void eventKeyRelease(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventKeyRelease\n");
  XKeyEvent e = xe->xkey;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, time_ms = e.time;
  int window = e.window, root = e.root, subwindow = e.subwindow;
  int x = e.x, y = e.y, x_root = e.x_root, y_root = e.y_root;
  int state = e.state, keycode = e.keycode, same_screen = e.same_screen;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventKeyRelease", "(JZIIIIIIIIIIZ)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, time_ms, window, root, subwindow, x, y, x_root, y_root, state, keycode, same_screen);
  debug(stderr, "exits eventKeyRelease\n");
}

/**
 * @param  xe  The X event
 */
void eventLeaveNotify(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventLeaveNotify\n");
  XCrossingEvent e = xe->xcrossing;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window, root = e.root, subwindow = e.subwindow, time = e.time;
  int x = e.x, y = e.y, x_root = e.x_root, y_root = e.y_root, mode = e.mode, detail = e.detail;
  int same_screen = e.same_screen, focus = e.focus, state = e.state;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventLeaveNotify", "(JZIIIIIIIIIIZZI)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, window, root, subwindow, time, x, y, x_root, y_root, mode, detail, same_screen, focus, state);
  debug(stderr, "exits eventLeaveNotify\n");
}

/**
 * @param  xe  The X event
 */
void eventMapNotify(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventMapNotify\n");
  XMapEvent e = xe->xmap;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, event = e.event, window = e.window;
  int override_redirect = e.override_redirect;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventMapNotify", "(JZIIZ)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, event, window, override_redirect);
  debug(stderr, "exits eventMapNotify\n");
}

/**
 * @param  xe  The X event
 */
void eventMappingNotify(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventMappingNotify\n");
  XMappingEvent e = xe->xmapping;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window, request = e.request, first_keycode = e.first_keycode, count = e.count;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventMappingNotify", "(JZIIII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, window, request, first_keycode, count);
  debug(stderr, "exits eventMappingNotify\n");
}

/**
 * @param  xe  The X event
 */
void eventMapRequest(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventMapRequest\n");
  XMapRequestEvent e = xe->xmaprequest;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, parent = e.parent, window = e.window;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventMapRequest", "(JZII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, parent, window);
  debug(stderr, "exits eventMapRequest\n");
}

/**
 * @param  xe  The X event
 */
void eventMotionNotify(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventMotionNotify\n");
  XMotionEvent e = xe->xmotion;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, time_ms = e.time;
  int window = e.window, root = e.root, subwindow = e.subwindow;
  int x = e.x, y = e.y, x_root = e.x_root, y_root = e.y_root;
  int state = e.state, same_screen = e.same_screen;
  char is_hint = e.is_hint;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventMapRequest", "(JZIIIIIIIIIZZ)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, time_ms, window, root, subwindow, x, y, x_root, y_root, state, same_screen, is_hint);
  debug(stderr, "exits eventMotionNotify\n");
}

/**
 * @param  xe  The X event
 */
void eventNoExpose(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventNoExpose\n");
  XNoExposeEvent e = xe->xnoexpose;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, drawable = e.drawable;
  int major_code = e.major_code, minor_code = e.minor_code;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventNoExpose", "(JZIII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, drawable, major_code, minor_code);
  debug(stderr, "exits eventNoExpose\n");
}

/**
 * @param  xe  The X event
 */
void eventPropertyNotify(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventPropertyNotify\n");
  XPropertyEvent e = xe->xproperty;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window, time = e.time, state = e.state;
  Atom atom = e.atom;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventPropertyNotify", "(JZIIII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, window, time, state, atom);
  debug(stderr, "exits eventPropertyNotify\n");
}

/**
 * @param  xe  The X event
 */
void eventReparentNotify(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventReparentNotify\n");
  XReparentEvent e = xe->xreparent;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, event = e.event, window = e.window, parent = e.parent;
  int x = e.x, y = e.y, override_redirect = e.override_redirect;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventReparentNotify", "(JZIIIIIZ)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, event, window, parent, x, y, override_redirect);
  debug(stderr, "exits eventReparentNotify\n");
}

/**
 * @param  xe  The X event
 */
void eventResizeRequest(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventResizeRequest\n");
  XResizeRequestEvent e = xe->xresizerequest;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window;
  int width = e.width, height = e.height;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventResizeRequest", "(JZIII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, window, width, height);
  debug(stderr, "exits eventResizeRequest\n");
}

/**
 * @param  xe  The X event
 */
void eventSelectionClear(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventSelectionClear\n");
  XSelectionClearEvent e = xe->xselectionclear;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window, time = e.time;
  Atom selection = e.selection;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventSelectionClear", "(JZIII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, window, time, selection);
  debug(stderr, "exits eventSelectionClear\n");
}

/**
 * @param  xe  The X event
 */
void eventSelectionNotify(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventSelectionNotify\n");
  XSelectionEvent e = xe->xselection;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, requestor = e.requestor, time = e.time;
  Atom selection = e.selection, target = e.target, property = e.property;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventSelectionNotify", "(JZIIIII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, requestor, time, selection, target, property);
  debug(stderr, "exits eventSelectionNotify\n");
}

/**
 * @param  xe  The X event
 */
void eventSelectionRequest(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventSelectionRequest\n");
  XSelectionRequestEvent e = xe->xselectionrequest;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, owner = e.owner, requestor = e.requestor, time = e.time;
  Atom selection = e.selection, target = e.target, property = e.property;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventSelectionRequest", "(JZIIIIII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, owner, requestor, time, selection, target, property);
  debug(stderr, "exits eventSelectionRequest\n");
}

/**
 * @param  xe  The X event
 */
void eventUnmapNotify(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventUnmapNotify\n");
  XUnmapEvent e = xe->xunmap;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, event = e.event, window = e.window, from_configure = from_configure;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventUnmapNotify", "(JZIIZ)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, event, window, from_configure);
  debug(stderr, "exits eventUnmapNotify\n");
}

/**
 * @param  xe  The X event
 */
void eventVisibilityNotify(XEvent* xe, JNIEnv* env, jclass class)
{
  debug(stderr, "enters eventVisibilityNotify\n");
  XVisibilityEvent e = xe->xvisibility;
  long long serial = (long long)e.serial;
  int send_event = e.send_event, window = e.window, state = state;
  
  jmethodID method = (*env)->GetStaticMethodID(env, class, "eventVisibilityNotify", "(JZII)V");
  (*env)->CallStaticVoidMethod(env, class, method, serial, send_event, window, state);
  debug(stderr, "exits eventVisibilityNotify\n");
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
      
      XFlush(display);
      FD_SET(x_fd, &fd_set);
      
      if ((QLength(display) > 0) || (select(x_fd + 1, &fd_set, 0, 0, 0) == 1))
      {
          XNextEvent(display, &e);
	  if (e.type < 32)
	    {
	      void (*function)(XEvent*, JNIEnv*, jclass) = *(event_handlers + e.type);
	      if (function != 0)
		function(&e, env, class);
	    }
          XSync(display, 0);
      }
    }
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
      int x, y;
      XGetGeometry(display, *(windows + i), &_root, &x, &y, &width, &height, &_border, &_depth);
      jmethodID method = (*env)->GetStaticMethodID(env, class, "newWindow", "(IIIII)V");
      (*env)->CallStaticVoidMethod(env, class, method, (jint)*(windows + i), (jint)x, (jint)y, (jint)width, (jint)height);
    }
  XFree(windows);
}

