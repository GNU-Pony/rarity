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

import java.util.Vector;


/**
 * Window instances in the environment
 * 
 * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class Window extends PropertyBase
{
    /**
     * The window's width
     */
    public static final String WIDTH = "width";
    
    /**
     * The window's height
     */
    public static final String HEIGHT = "height";
    
    /**
     * The window's offset from the left edge of the primary screen
     */
    public static final String LEFT = "left";
    
    /**
     * The window's offset from the upper edge of the primary screen
     */
    public static final String TOP = "top";
    
    /**
     * Whether the window is visible
     */
    public static final String VISIBLE = "visible";
    
    
    /**
     * Replace value
     */
    #value=$((cat /usr/include/X11/X.h | grep PropModeReplace ; echo PropModeReplace) | cpp | tail -n 1 | sed -e 's:L::g')
    public static final int MODE_REPLACE = <"$value$">;
    
    /**
     * Prepend to value
     */
    #value=$((cat /usr/include/X11/X.h | grep PropModePrepend ; echo PropModePrepend) | cpp | tail -n 1 | sed -e 's:L::g')
    public static final int MODE_PREPEND = <"$value$">;
    
    /**
     * Append to value
     */
    #value=$((cat /usr/include/X11/X.h | grep PropModeAppend ; echo PropModeAppend) | cpp | tail -n 1 | sed -e 's:L::g')
    public static final int MODE_APPEND = <"$value$">;
    
    
    /**
     * Special atom used to accept any property type
     */
    #value=$((cat /usr/include/X11/X.h | grep AnyPropertyType ; echo AnyPropertyType) | cpp | tail -n 1 | sed -e 's:L::g')
    private static final int ANY_PROPERTY_TYPE = <"$value$">;
    
    
    
    /**
     * Constructor
     * 
     * @param  left     The left X-axis position of the window
     * @param  top      The top Y-axis position of the window
     * @param  width    The window's width
     * @param  height   The window's height
     * @parma  pointer  Pointer to the window object in native code
     */
    protected Window(final int left, final int top, final int width, final int height, final long pointer)
    {
	this.set(LEFT, left);
	this.set(TOP, top);
	this.set(WIDTH, width);
	this.set(HEIGHT, height);
	this.set(VISIBLE, true);
	this.pointer = pointer;
    }
    
    
    
    /**
     * All windows in index order
     */
    static Vector<Window> windows = new Vector<Window>();
    
    /**
     * Pointer to the window object in native code
     */
    public final long pointer;
    
    
    
    /**
     * Blackboard message for {@link Window} property updates
     * 
     * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
     */
    public static class PropertyMessage implements Blackboard.BlackboardMessage
    {
	/**
	 * Constructor
	 * 
	 * @param  window    The updated window
	 * @param  property  The updated property
	 */
	protected PropertyMessage(final Window window, final String property)
	{
	    this.window = window;
	    this.property = property;
	    int i = 0;
	    for (final Window win : Window.windows)
		if (win == window)
		    break;
		else
		    i++;
	    this.index = i;
	}
	
	
	
	/**
	 * The updated window
	 */
	public final Window window;
	
	/**
	 * The index of the updated window
	 */
	public final int index;
	
	/**
	 * The updated property
	 */
	public final String property;
	
    }
    
    
    /**
     * Blackboard message for {@link Window} existance updates
     * 
     * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
     */
    public static class ExistanceMessage implements Blackboard.BlackboardMessage
    {
	/**
	 * A new window has been added
	 */
	public static final int ADDED = 0;
	
	/**
	 * A window is being removed
	 */
	public static final int REMOVING = 1;
	
	/**
	 * A window has been removed
	 */
	public static final int REMOVED = 2;
	
	
	
	/**
	 * Constructor
	 * 
	 * @param  action  Either {@link #ADDED}, {@link #REMOVING} or {@link #REMOVED}
	 * @param  index   The index of the window in question
	 */
	protected ExistanceMessage(final int action, final int index)
	{
	    this.action = action;
	    this.index = index;
	}
	
	
	
	/**
	 * Either {@link #ADDED}, {@link #REMOVING} or {@link #REMOVED}
	 */
	public final int action;
	
	/**
	 * The index of the window in question
	 */
	public final int index;
	
    }
    
    
    
    /**
     * Gets a window by its index
     * 
     * @param   index  The window's index
     * @return         The window
     */
    public static Window getWindow(final int index)
    {
	synchronized (windows)
	{   return windows.get(index);
	}
    }
    
    /**
     * Gets the number of windows in the environment
     * 
     * @return  The number of windows in the environment
     */
    public static int getWindowCount()
    {
	synchronized (windows)
	{   return windows.size();
	}
    }
    
    /**
     * Gets a window by its address
     * 
     * @param   address  The window's address
     * @return           The window, {@code null} if it does not mapped
     */
    public static Window getByAddress(final long address)
    {
	/* We are going to assume that there are not so many windows that a mash map should be used */
	synchronized (windows)
	{   for (final Window window : windows)
		if (window.pointer == address)
		    return window;
	    return null;
	}
    }
    
    /**
     * Unlist a window
     * 
     * @param  window  The windo to unlist
     */
    public static void removeWindow(final Window window)
    {
	synchronized (windows)
	{   int index = 0;
	    for (final Window canidate : windows)
		if (canidate == window)
		    break;
		else
		    index++;
	    if (index == windows.size())
		return;
	    final Blackboard blackboard = Blackboard.getInstance(Window.class);
	    blackboard.broadcastMessage(new Window.ExistanceMessage(Window.ExistanceMessage.REMOVING, index));
	    windows.remove(index);
	    blackboard.broadcastMessage(new Window.ExistanceMessage(Window.ExistanceMessage.REMOVED, index));
	}
    }
    
    
    /**
     * {@inheritDoc}
     */
    protected void updated(final String property, final Object value)
    {
	Blackboard.getInstance(Window.class).broadcastMessage(new Window.PropertyMessage(this, property));
    }
    
    
    /**
     * Flush position changes to the display
     */
    public void updatePosition()
    {
	xMoveWindow(this.pointer, getInteger(LEFT), getInteger(TOP));
    }
    
    /**
     * Flush size changes to the display
     */
    public void updateSize()
    {
	xResizeWindow(this.pointer, getInteger(WIDTH), getInteger(HEIGHT));
    }
    
    /**
     * Flush position and size changes to the display
     */
    public void updateArea()
    {
	xMoveResizeWindow(this.pointer, getInteger(LEFT), getInteger(TOP), getInteger(WIDTH), getInteger(HEIGHT));
    }
    
    
    /**
     * Flush position changes to the display
     * 
     * @param  address  The memory address of the window
     * @param  x        The left X-axis position of the window
     * @param  y        The top Y-axis position of the window
     */
    private static native void xMoveWindow(long address, int x, int y);
    
    /**
     * Flush size changes to the display
     * 
     * @param  address  The memory address of the window
     * @param  width    The width of the window
     * @param  height   The height of the window
     */
    private static native void xResizeWindow(long address, int width, int height);
    
    /**
     * Flush position and size changes to the display
     * 
     * @param  address  The memory address of the window
     * @param  x        The left X-axis position of the window
     * @param  y        The top Y-axis position of the window
     * @param  width    The width of the window
     * @param  height   The height of the window
     */
    private static native void xMoveResizeWindow(long address, int x, int y, int width, int height);
    
    
    /**
     * Deletes a property only if the property was defined on the window
     * 
     * @param  property  The property
     */
    public void deleteProperty(final XAtom property)
    {
	xDeleteProperty(this.pointer, property.atom);
    }
    
    /**
     * Transfers the value associeted with atom number <i>i</i> to atom
     * number (<i>i</i> + <tt>positions</tt>) mod |<tt>properties</tt>|
     * for all <i>i</i> &lt; |<tt>properties</tt>|. Where atom number
     * <i>i</i> is {@code properties[i]}.
     * 
     * @param  properties  Array of properties that are to be rotated
     * @param  positions   The rotation amount
     */
    public void rotateProperties(final XAtom[] properties, final int positions)
    {
	final int[] properties_ = new int[properties.length];
	for (int i = 0, n = properties.length; i < n; i++)
	    properties_[i] = properties[i].atom;
	
	xRotateWindowProperties(this.pointer, properties_, positions);
    }
    
    /**
     * Sets a property on the window
     * 
     * @param  property  The property
     * @param  type      The property's type
     * @param  mode      Either {@link #MODE_REPLACE}, {@link #MODE_PREPEND} or {@link #MODE_APPEND}
     * @param  data      The value, or the value prependix or appendix
     */
    public void setProperty(final XAtom property, final XAtom type, final int mode, final byte[] data)
    {
	xChangeProperty(this.pointer, property.atom, type.atom, 8, mode, data);
    }
    
    /**
     * Sets a property on the window
     * 
     * @param  property  The property
     * @param  type      The property's type
     * @param  mode      Either {@link #MODE_REPLACE}, {@link #MODE_PREPEND} or {@link #MODE_APPEND}
     * @param  data      The value, or the value prependix or appendix
     */
    public void setProperty(final XAtom property, final XAtom type, final int mode, final String data)
    {
	final byte[] data_;
	try
	{   data_ = data.getBytes("UTF-8");
	}
	catch (final Throwable err)
	{   System.err.println("Unable to encode to UTF-8");
	    Rarity.abort();
	    return;
	}
	xChangeProperty(this.pointer, property.atom, type.atom, 8, mode, data_);
    }
    
    /**
     * Sets a property on the window
     * 
     * @param  property  The property
     * @param  type      The property's type
     * @param  mode      Either {@link #MODE_REPLACE}, {@link #MODE_PREPEND} or {@link #MODE_APPEND}
     * @param  data      The value, or the value prependix or appendix
     */
    public void setProperty(final XAtom property, final XAtom type, final int mode, final short[] data)
    {
	final byte[] data_ = new byte[data.length * 2];
	for (int i = 0, n = data.length; i < n; i++)
	{   data_[(i << 1) | 0] = (byte)((data[i] >>> (0 * 8)) & 255);
	    data_[(i << 1) | 1] = (byte)((data[i] >>> (1 * 8)) & 255);
	}
	
	xChangeProperty(this.pointer, property.atom, type.atom, 16, mode, data_);
    }
    
    /**
     * Sets a property on the window
     * 
     * @param  property  The property
     * @param  type      The property's type
     * @param  mode      Either {@link #MODE_REPLACE}, {@link #MODE_PREPEND} or {@link #MODE_APPEND}
     * @param  data      The value, or the value prependix or appendix
     */
    public void setProperty(final XAtom property, final XAtom type, final int mode, final int[] data)
    {
	final byte[] data_ = new byte[data.length * 4];
	for (int i = 0, n = data.length; i < n; i++)
	{   data_[(i << 2) | 0] = (byte)((data[i] >>> (0 * 8)) & 255);
	    data_[(i << 2) | 1] = (byte)((data[i] >>> (1 * 8)) & 255);
	    data_[(i << 2) | 2] = (byte)((data[i] >>> (2 * 8)) & 255);
	    data_[(i << 2) | 3] = (byte)((data[i] >>> (3 * 8)) & 255);
	}
	
	xChangeProperty(this.pointer, property.atom, type.atom, 32, mode, data_);
    }
    
    /**
     * Gets a list of all properties on the window
     * 
     * @return  All properties on the window, a zero-length array if none were found, never {@code null}
     */
    public XAtom[] listProperties()
    {
	final int[] atoms_ = xListProperties(this.pointer);
	final XAtom[] atoms = new XAtom[atoms_.length];
	
	for (int i = 0, n = atoms.length; i < n; i++)
	    atoms[i] = new XAtom(atoms_[i]);
	
	return atoms;
    }
    
    /**
     * Return type for {@link Window#getProperty}
     */
    public static class PropertyInformation
    {
	// Has default constructor
	
	
	/**
	 * Format, 8 for {@code char} array, 16 for {@code short} array, 32 for {@code int}
	 */
	public int format;
	
	/**
	 * Property type
	 */
	public XAtom type;
	
	/**
	 * Property value
	 */
	public byte[] value;
	
	
	
	/**
	 * Returns the value as a {@code byte[]}
	 * 
	 * @return  The value as a {@code byte[]}
	 */
	public byte[] getByteArray()
	{
	    return this.value;
	}
	
	/**
	 * Returns the value as a {@code short[]}
	 * 
	 * @return  The value as a {@code short[]}
	 */
	public short[] getShortArray()
	{
	    final short[] rc = new short[this.value.length >> 1];
	    for (int i = 0, n = this.value.length >> 1; i < n; i++)
		rc[i] = (short)(((this.value[(i << 1) | 0] & 255) << 0)
			      | ((this.value[(i << 1) | 1] & 255) << 8));
	    return rc;
	}
	
	/**
	 * Returns the value as an {@code int[]}
	 * 
	 * @return  The value as an {@code int[]}
	 */
	public int[] getIntArray()
	{
	    final int[] rc = new int[this.value.length >> 2];
	    for (int i = 0, n = this.value.length >> 2; i < n; i++)
		rc[i] = ((this.value[(i << 2) | 0] & 255) <<  0)
		      | ((this.value[(i << 2) | 1] & 255) <<  8)
		      | ((this.value[(i << 2) | 2] & 255) << 16)
		      | ((this.value[(i << 2) | 3] & 255) << 24);
	    return rc;
	}
	
	/**
	 * Returns the value as a {@link String}
	 * 
	 * @return  The value as a {@link String}
	 */
	public String getString()
	{
	    String rc;
	    try
	    {   rc = new String(this.value, "UTF-8");
	    }
	    catch (final Throwable err)
	    {   System.err.println("Unable to decode UTF-8");
		Rarity.abort();
		return null;
	    }
	    if (rc.indexOf('\0') >= 0)
		rc = rc.substring(0, rc.indexOf('\0'));
	    return rc;
	}
    }
    
    /**
     * Reads a property on the window
     * 
     * @param   property     The property
     * @param   requestType  The atom identifier associated with the property type or {@code null} for any type
     * @return               The property value and information about it
     */
    public PropertyInformation getProperty(final XAtom property, final XAtom requestType)
    {
	final PropertyInformation rc = new PropertyInformation();
	final int requestType_ = requestType == null ? ANY_PROPERTY_TYPE : requestType.atom;
	final byte[] raw =  xGetWindowProperty(this.pointer, property.atom, requestType_);
	
	rc.format = raw[0] & 255;
	rc.type = new XAtom(((raw[1] & 255) << 24) | ((raw[2] & 255) << 16) | ((raw[3] & 255) << 8) | (raw[4] & 255));
	System.arraycopy(raw, 5, rc.value = new byte[raw.length - 5], 0, raw.length - 5);
	
	return rc;
    }
    
    
    /**
     * Deletes a property only if the property was defined on a window
     * 
     * @param  address   The memory address of the window
     * @param  property  The property
     */
    private static native void xDeleteProperty(long address, int property);
    
    /**
     * Transfers the value associeted with atom number <i>i</i> to atom
     * number (<i>i</i> + <tt>positions</tt>) mod |<tt>properties</tt>|
     * for all <i>i</i> &lt; |<tt>properties</tt>|. Where atom number
     * <i>i</i> is {@code properties[i]}.
     * 
     * @param  address     The memory address of the window
     * @param  properties  Array of properties that are to be rotated
     * @param  positions   The rotation amount
     */
    private static native void xRotateWindowProperties(long address, int[] properties, int positions);
    
    /**
     * Sets a property on a window
     * 
     * @param  address   The memory address of the window
     * @param  property  The property
     * @param  type      The property's type
     * @param  format    8 for {@code char} array, 16 for {@code short} array, 32 for {@code int} array stored as an C {@code long int} array
     * @param  mode      Either {@link #MODE_REPLACE}, {@link #MODE_PREPEND} or {@link #MODE_APPEND}
     * @param  data      The value, or the value prependix or appendix, as a byte array
     */
    private static native void xChangeProperty(long address, int property, int type, int format, int mode, byte[] data);
    
    /**
     * Gets a list of all properties on a window
     * 
     * @param   address  The memory address of the window
     * @return           All properties on the window, a zero-length array if none were found, never {@code null}
     */
    private static native int[] xListProperties(long address);
    
    /**
     * Gets or deletes a property on a window
     * 
     * @param   address      The memory address of the window
     * @param   property     The property
     * @param   requestType  Atom identifier associated with the property type
     * @return               Format(1 byte) || Type(4 bytes) || Value
     */
    private static native byte[] xGetWindowProperty(long address, int property, int requestType);
    
}

