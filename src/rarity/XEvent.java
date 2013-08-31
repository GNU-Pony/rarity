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

import java.awt.Point;
import java.awt.Dimension;


/**
 * X Window System event class
 * 
 * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public abstract class XEvent implements Blackboard.BlackboardMessage
{
    public XEvent(final long serial, final boolean sendEvent)
    {
	this.serial = serial;
	this.sendEvent = sendEvent;
    }
    
    
    
    public final long serial;
    public final boolean sendEvent;
    
    
    
    public static abstract class InputEvent extends XEvent
    {
	public InputEvent(final long serial, final boolean sendEvent, final int time, final int window, final int root, final int subwindow, final int x, final int y, final int xRoot, final int yRoot, final int state, final boolean sameScreen)
	{
	    super(serial, sendEvent);
	    this.time = time;
	    this.window = window;
	    this.root = root;
	    this.subwindow = subwindow;
	    this.position = new Point(x, y);
	    this.positionRoot = new Point(xRoot, yRoot);
	    this.state = state;
	    this.sameScreen = sameScreen;
	}
	
	public final int time;
	public final long window;
	public final long root;
	public final long subwindow;
	public final Point position;
	public final Point positionRoot;
	public final int state;
	public final boolean sameScreen;
    }
    
    
    public static class Key extends InputEvent
    {
	public static final int PRESSED = 0;
	public static final int RELEASED = 1;
	
	public Key(final long serial, final boolean sendEvent, final int time, final int window, final int root, final int subwindow, final int x, final int y, final int xRoot, final int yRoot, final int state, final int keycode, final boolean sameScreen, final int action)
	{
	    super(serial, sendEvent, time, window, root, subwindow, x, y, xRoot, yRoot, state, sameScreen);
	    this.keycode = keycode;
	    this.action = action;
	}
	
	public final int keycode;
	public final int action;
    }
    
    
    public static abstract class RatEvent extends InputEvent
    {
	public RatEvent(final long serial, final boolean sendEvent, final int time, final int window, final int root, final int subwindow, final int x, final int y, final int xRoot, final int yRoot, final int state, final boolean sameScreen)
	{
	    super(serial, sendEvent, time, window, root, subwindow, x, y, xRoot, yRoot, state, sameScreen);
	}
    }
    
    
    public static class RatButton extends RatEvent
    {
	public static final int PRESSED = 0;
	public static final int RELEASED = 1;
	
	public RatButton(final long serial, final boolean sendEvent, final int time, final int window, final int root, final int subwindow, final int x, final int y, final int xRoot, final int yRoot, final int state, final int button, final boolean sameScreen, final int action)
	{
	    super(serial, sendEvent, time, window, root, subwindow, x, y, xRoot, yRoot, state, sameScreen);
	    this.button = button;
	    this.action = action;
	}
	
	public final int button;
	public final int action;
    }
    
    
    public static class RatWindow extends RatEvent
    {
	public static final int ENTER = 0;
	public static final int LEAVE = 1;
	
	public RatWindow(final long serial, final boolean sendEvent, final int time, final int window, final int root, final int subwindow, final int x, final int y, final int xRoot, final int yRoot, final int mode, final int detail, final boolean sameScreen, final boolean focus, final int state, final int action)
	{
	    super(serial, sendEvent, time, window, root, subwindow, x, y, xRoot, yRoot, state, sameScreen);
	    this.mode = mode;
	    this.detail = detail;
	    this.focus = focus;
	    this.action = action;
	}
	
	public final int mode;
	public final int detail;
	public final boolean focus;
	public final int action;
    }
    
    
    public static class RatMove extends RatEvent
    {
	public RatMove(final long serial, final boolean sendEvent, final int time, final int window, final int root, final int subwindow, final int x, final int y, final int xRoot, final int yRoot, final int state, final boolean sameScreen, final boolean isHint)
	{
	    super(serial, sendEvent, time, window, root, subwindow, x, y, xRoot, yRoot, state, sameScreen);
	    this.isHint = isHint;
	}
	
	public final boolean isHint;
    }
    
    
    public static class ConfigureEvent extends XEvent
    {
	public ConfigureEvent(final long serial, final boolean sendEvent, final int window, final int above, final int x, final int y, final int width, final int height, final int borderWidth)
	{
	    super(serial, sendEvent);
	    this.window = window;
	    this.above = above;
	    this.position = new Point(x, y);
	    this.size = new Dimension(width, height);
	    this.borderWidth = borderWidth;
	}
	
	public final long window;
	public final long above;
	public final Point position;
	public final Dimension size;
	public final int borderWidth;
    }
    
    
    public static class ConfigureNotify extends ConfigureEvent
    {
	public ConfigureNotify(final long serial, final boolean sendEvent, final int event, final int window, final int above, final int x, final int y, final int width, final int height, final int borderWidth, final boolean overrideRedirect)
	{
	    super(serial, sendEvent, window, above, x, y, width, height, borderWidth);
	    this.event = event;
	    this.overrideRedirect = overrideRedirect;
	}
	
	public final long event;
	public final boolean overrideRedirect;
    }
    
    
    public static class ConfigureRequest extends ConfigureEvent
    {
	public ConfigureRequest(final long serial, final boolean sendEvent, final long valueMask, final int parent, final int window, final int above, final int x, final int y, final int width, final int height, final int borderWidth, final int detail)
	{
	    super(serial, sendEvent, window, above, x, y, width, height, borderWidth);
	    this.valueMask = valueMask;
	    this.parent = parent;
	    this.detail = detail;
	}
	
	public final long valueMask;
	public final long parent;
	public final int detail;
    }
    
    
    public static abstract class ExposeEvent extends XEvent
    {
	public ExposeEvent(final long serial, final boolean sendEvent)
	{
	    super(serial, sendEvent);
	}
    }
    
    
    public static class Expose extends ExposeEvent
    {
	public Expose(final long serial, final boolean sendEvent, final int window, final int x, final int y, final int width, final int height, final int count)
	{
	    super(serial, sendEvent);
	    this.window = window;
	    this.position = new Point(x, y);
	    this.size = new Dimension(width, height);
	    this.count = count;
	}
	
	public final long window;
	public final Point position;
	public final Dimension size;
	public final int count;
    }
    
    
    public static class GraphicsExpose extends ExposeEvent
    {
	public GraphicsExpose(final long serial, final boolean sendEvent, final int drawable, final int x, final int y, final int width, final int height, final int count, final int majorCode, final int minorCode)
	{
	    super(serial, sendEvent);
	    this.drawable = drawable;
	    this.position = new Point(x, y);
	    this.size = new Dimension(width, height);
	    this.count = count;
	    this.majorCode = majorCode;
	    this.minorCode = minorCode;
	}
	
	public final long drawable;
	public final Point position;
	public final Dimension size;
	public final int count;
	public final int majorCode;
	public final int minorCode;
    }
    
    
    public static class NoExpose extends ExposeEvent
    {
	public NoExpose(final long serial, final boolean sendEvent, final int drawable, final int majorCode, final int minorCode)
	{
	    super(serial, sendEvent);
	    this.drawable = drawable;
	    this.majorCode = majorCode;
	    this.minorCode = minorCode;
	}
	
	public final long drawable;
	public final int majorCode;
	public final int minorCode;
    }
    
    
    public static class MapEvent extends XEvent
    {
	public MapEvent(final long serial, final boolean sendEvent, final int window)
	{
	    super(serial, sendEvent);
	    this.window = window;
	}
	
	public final long window;
    }
    
    
    public static class MapNotify extends MapEvent
    {
	public MapNotify(final long serial, final boolean sendEvent, final int event, final int window, final boolean overrideRedirect)
	{
	    super(serial, sendEvent, window);
	    this.event = event;
	    this.overrideRedirect = overrideRedirect;
	}
	
	public final long event;
	public final boolean overrideRedirect;
    }
    
    
    public static class MappingNotify extends MapEvent
    {
	public MappingNotify(final long serial, final boolean sendEvent, final int window, final int request, final int firstKeycode, final int count)
	{
	    super(serial, sendEvent, window);
	    this.request = request;
	    this.firstKeycode = firstKeycode;
	    this.count = count;
	}
	
	public final long request;
	public final int firstKeycode;
	public final int count;
    }
    
    
    public static class MapRequest extends MapEvent
    {
	public MapRequest(final long serial, final boolean sendEvent, final int parent, final int window)
	{
	    super(serial, sendEvent, window);
	    this.parent = parent;
	}
	
	public final long parent;
    }
    
    
    public static class UnmapNotify extends MapEvent
    {
	public UnmapNotify(final long serial, final boolean sendEvent, final int event, final int window, final boolean fromConfigure)
	{
	    super(serial, sendEvent, window);
	    this.event = event;
	    this.fromConfigure = fromConfigure;
	}
	
	public final long event;
	public final boolean fromConfigure;
    }
    
    
    public static class SelectionEvent extends XEvent
    {
	public SelectionEvent(final long serial, final boolean sendEvent, final int time, final int selection)
	{
	    super(serial, sendEvent);
	    this.time = time;
	    this.selection = selection;
	}
	
	public final int time;
	public final int selection;
    }
    
    
    public static class SelectionClear extends SelectionEvent
    {
	public SelectionClear(final long serial, final boolean sendEvent, final int window, final int time, final int selection)
	{
	    super(serial, sendEvent, time, selection);
	    this.window = window;
	}
	
	public final long window;
    }
    
    
    public static class SelectionNotify extends SelectionEvent
    {
	public SelectionNotify(final long serial, final boolean sendEvent, final int requestor, final int time, final int selection, final int target, final int property)
	{
	    super(serial, sendEvent, time, selection);
	    this.requestor = requestor;
	    this.target = target;
	    this.property = property;
	}
	
	public final long requestor;
	public final int target;
	public final int property;
    }
    
    
    public static class SelectionRequest extends SelectionEvent
    {
	public SelectionRequest(final long serial, final boolean sendEvent, final int owner, final int requestor, final int time, final int selection, final int target, final int property)
	{
	    super(serial, sendEvent, time, selection);
	    this.owner = owner;
	    this.requestor = requestor;
	    this.target = target;
	    this.property = property;
	}
	
	public final long owner;
	public final long requestor;
	public final int target;
	public final int property;
    }
    
    
    public static class ClientMessage extends XEvent
    {
	public ClientMessage(final long serial, final boolean sendEvent, final int window, final long messageType, final int format, final byte[] data)
	{
	    super(serial, sendEvent);
	    this.window = window;
	    this.messageType = messageType;
	    this.format = format;
	    this.data = data;
	}
	
	public final long window;
	public final long messageType;
	public final int format;
	public final byte[] data;
    }
    
    
    public static class Circulate extends XEvent
    {
        public static final int NOTIFY = 0;
	public static final int REQUEST = 1;
	
	public Circulate(final long serial, final boolean sendEvent, final int other, final int window, final int place, final int action)
	{
	    super(serial, sendEvent);
	    this.other = other;
	    this.window = window;
	    this.place = place;
	    this.action = action;
	}
	
	public final long other;
	public final long window;
	public final int place;
	public final int action;
    }
    
    
    public static class ColormapNotify extends XEvent
    {
	public ColormapNotify(final long serial, final boolean sendEvent, final int window, final int colormap, final boolean isNew, final int state)
	{
	    super(serial, sendEvent);
	    this.window = window;
	    this.colormap = colormap;
	    this.isNew = isNew;
	    this.state = state;
	}
	
	public final long window;
	public final long colormap;
	public final boolean isNew;
	public final int state;
    }
    
    
    public static class CreateWindow extends XEvent
    {
	public CreateWindow(final long serial, final boolean sendEvent, final int parent, final int window, final int x, final int y, final int width, final int height, final int borderWidth, final boolean overrideRedirect)
	{
	    super(serial, sendEvent);
	    this.parent = parent;
	    this.window = window;
	    this.position = new Point(x, y);
	    this.size = new Dimension(width, height);
	    this.borderWidth = borderWidth;
	    this.overrideRedirect = overrideRedirect;
	}
	
	public final long parent;
	public final long window;
	public final Point position;
	public final Dimension size;
	public final int borderWidth;
	public final boolean overrideRedirect;
    }
    
    
    public static class DestroyWindow extends XEvent
    {
	public DestroyWindow(final long serial, final boolean sendEvent, final int event, final int window)
	{
	    super(serial, sendEvent);
	    this.event = event;
	    this.window = window;
	}
	
	public final long event;
	public final long window;
    }
    
    
    public static class Focus extends XEvent
    {
	public static final int FOCUS_IN = 0;
	public static final int FOCUS_OUT = 1;
	
	public Focus(final long serial, final boolean sendEvent, final int window, final int mode, final int detail, final int action)
	{
	    super(serial, sendEvent);
	    this.window = window;
	    this.mode = mode;
	    this.detail = detail;
	    this.action = action;
	}
	
	public final long window;
	public final int mode;
	public final int detail;
	public final int action;
    }
    
    
    public static class Gravity extends XEvent
    {
	public Gravity(final long serial, final boolean sendEvent, final int event, final int window, final int x, final int y)
	{
	    super(serial, sendEvent);
	    this.event = event;
	    this.window = window;
	    this.x = x;
	    this.y = y;
	}
	
	public final long event;
	public final long window;
	public final int x;
	public final int y;
    }
    
    
    public static class Keymap extends XEvent
    {
	public Keymap(final long serial, final boolean sendEvent, final int window, final byte[] keyVector)
	{
	    super(serial, sendEvent);
	    this.window = window;
	    this.keyVector = keyVector;
	}
	
	public final long window;
	public final byte[] keyVector;
    }
    
    
    public static class PropertyNotify extends XEvent
    {
	public PropertyNotify(final long serial, final boolean sendEvent, final int window, final int time, final int state, final int atom)
	{
	    super(serial, sendEvent);
	    this.window = window;
	    this.time = time;
	    this.state = state;
	    this.atom = atom;
	}
	
	public final long window;
	public final int time;
	public final int state;
	public final int atom;
    }
    
    
    public static class ReparentNotify extends XEvent
    {
	public ReparentNotify(final long serial, final boolean sendEvent, final int event, final int window, final int parent, final int x, final int y, final boolean overrideRedirect)
	{
	    super(serial, sendEvent);
	    this.event = event;
	    this.window = window;
	    this.parent = parent;
	    this.x = x;
	    this.y = y;
	    this.overrideRedirect = overrideRedirect;
	}
	
	public final long event;
	public final long window;
	public final long parent;
	public final int x;
	public final int y;
	public final boolean overrideRedirect;
    }
    
    
    public static class ResizeRequest extends XEvent
    {
	public ResizeRequest(final long serial, final boolean sendEvent, final int event, final int window, final int width, final int height)
	{
	    super(serial, sendEvent);
	    this.event = event;
	    this.window = window;
	    this.width = width;
	    this.height = height;
	}
	
	public final long event;
	public final long window;
	public final int width;
	public final int height;
    }
    
    
    public static class VisibilityNotify extends XEvent
    {
	public VisibilityNotify(final long serial, final boolean sendEvent, final int window, final boolean state)
	{
	    super(serial, sendEvent);
	    this.window = window;
	    this.state = state;
	}
	
	public final long window;
	public final boolean state;
    }
    
    
    
    /**
     * Broadcast the event
     */
    void broadcast()
    {
	Blackboard.getInstance(XEvent.class).broadcastMessage(this);
    }
    
}

