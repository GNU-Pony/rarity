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

import java.util.HashMap;


/**
 * Base class of property bases classes
 * 
 * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public abstract class PropertyBase
{
    // Hash default constructor
    
    
    
    /**
     * Map for the object's properties
     */
    protected HashMap<String, Object> properties = new HashMap<String, Object>();
    
    
    
    /**
     * This method is invoked when a property has been updated (even if the value did not change)
     * 
     * @param  property  The property
     * @param  value     The property value
     */
    protected abstract void updated(final String property, final Object value);
    
    
    /**
     * Gets a property from the object
     * 
     * @param   property  The property
     * @return            The property value as an {@link Object}
     */
    public Object get(final String property)
    {
	Object obj = this.properties.get(property);
	if ((obj == null) || (obj instanceof Lambda == false))
	    return obj;
	return ((Lambda)obj).evaluate(this);
    }
    
    /**
     * Gets a property from the object
     * 
     * @param   property  The property
     * @return            The property value as an {@link String}
     */
    public String getString(final String property)
    {
	Object obj = get(property);
	if ((obj == null) || (obj instanceof String == false))
	    return null;
	return (String)obj;
    }
    
    /**
     * Gets a property from the object
     * 
     * @param   property  The property
     * @return            The property value as an {@code int}
     */
    public int getInteger(final String property)
    {
	Object obj = this.properties.get(property);
	if ((obj == null) || (obj instanceof Integer == false))
	    return 0;
	return ((Integer)obj).intValue();
    }
    
    /**
     * Gets a property from the object
     * 
     * @param   property  The property
     * @return            The property value as a {@code boolean}
     */
    public boolean getBoolean(final String property)
    {
	Object obj = this.properties.get(property);
	if ((obj == null) || (obj instanceof Boolean == false))
	    return false;
	return ((Boolean)obj).booleanValue();
    }
    
    
    /**
     * Sets a property for the object
     * 
     * @param  property  The property
     * @param  value     The property value
     */
    public void set(final String property, final Object value)
    {
	this.properties.put(property, value);
    }
    
    /**
     * Sets a property for the object
     * 
     * @param  property  The property
     * @param  value     The property value
     */
    public void set(final String property, final int value)
    {
	this.set(property, Integer.valueOf(value));
    }
    
    /**
     * Sets a property for the object
     * 
     * @param  property  The property
     * @param  value     The property value
     */
    public void set(final String property, final boolean value)
    {
	this.set(property, Boolean.valueOf(value));
    }
    
}

