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
 * Plug-ins must implement this interface or a newer version.<br/>
 * Public nullary (default) construct.<br/>
 * Implemention class should be named {@code Plugin}.
 * 
 * @author   Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 * @version  1.0
 * @since    {@link PluginHandler} version 1.0
 */
public interface PluginV1
{
    /**
     * Mini icon size: 8 × 8
     */
    public static final int MINI = 8;
    
    /**
     * Small icon size: 16 × 16
     */
    public static final int SMALL = 16;
    
    /**
     * Medium icon size: 24 × 24
     */
    public static final int MEDIUM = 24;
    
    /**
     * Large icon size: 32 × 32
     */
    public static final int LARGE = 32;
    
    /**
     * Huge icon size: 48 × 48
     */
    public static final int HUGE = 48;
    
    
    
    /**
     * Gets the plug-in's name
     * 
     * @return  The plug-in's name
     */
    public abstract String getName();
    
    /**
     * Gets the plug-in's description
     * 
     * @return  The plug-in's description
     */
    public abstract String getDescription();
    
    /**
     * Gets the version of the plug-in
     * 
     * @return  The version of the plug-in
     */
    public abstract String getVersion();
    
    
    /**
     * Initialises the plug-in
     */
    public abstract void initialise();
    
    /**
     * Terminates the plug-in
     */
    public abstract void terminate();
    
    
    /**
     * Gets the plug-in's icon
     * 
     * @param   dimension  The width and height of the icon
     * @return             The plug-in's icon, {@code null} if vector image is missing
     */
    public abstract ImageIcon getIcon(final int dimension);
    
    /**
     * Gets the plug-in's icon, should the biggest available raster image
     * 
     * @return  The plug-in's icon, {@code null} if raster image is missing
     */
    public abstract ImageIcon getBiggestIcon();
    
    /**
     * Gets an array of all n:s such that a raster icon of size n × n exists,
     * if non-positive number is returned as an element, a vector icon designed for
     * ~n × ~n and larger exists.
     */
    public abstract int[] getIconDimensions();
    
}

