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

import javax.swing.ImageIcon;


/**
 * <p>Plugin mane class</p>
 * <p>
 *   For once I am actually going to explain the name.
 *   If you are observant you may have noticed that the
 *   GNU/Pony project is basically a big play on words.
 *   This plugin named Nightmare and the package name
 *   is rarity.nightmare. The package names in Java are
 *   in reverse DNS order, so as a normal DNS it would
 *   be nightmare.rarity, as in Nightmare Rarity the
 *   that has the quote (in ponysay):
 * </p>
 * <p>
 *   Are my ears burning?
 *   Because with how hot I look, I should be on fire!
 * </p>
 * <p>
 *   It is very hot, and is it the opposite of a nightmare,
 *   it is my dream window manager. Additionally, it is
 *   keyboard centric, which hackers should love, and
 *   hackers are, stereotypically, up and hackng during
 *   the night (so I am at the time of writing), hence
 *   night mare, which if juxtapositioned (as I think
 *   it should be because it should be just one word,
 *   but English does not work that way) is nightmare.
 * </p>
 * 
 * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class Plugin implements PluginV1
{
    /**
     * Gets the plug-in's name
     * 
     * @return  The plug-in's name
     */
    public String getName()	
    {
	return "Nightmare";
    }
    
    /**
     * Gets the plug-in's description
     * 
     * @return  The plug-in's description
     */
    public String getDescription()
    {
	return "Reference window management extension";
    }
    
    /**
     * Gets the version of the plug-in
     * 
     * @return  The version of the plug-in
     */
    public String getVersion()
    {
	return "1";
    }
    
    
    /**
     * Initialises the plug-in
     */
    public void initialise()
    {
	System.err.println("Initialising extension: Nightmare");
	WindowMapper.start();
	WindowSizer.start();
    }
    
    /**
     * Terminates the plug-in
     */
    public void terminate()
    {
	System.err.println("Terminating extension: Nightmare");
	WindowSizer.stop();
	WindowMapper.stop();
    }
    
    
    /**
     * Gets the plug-in's icon
     * 
     * @param   dimension  The width and height of the icon
     * @return             The plug-in's icon, {@code null} if vector image is missing
     */
    public ImageIcon getIcon(final int dimension)
    {
	return null;
    }
    
    /**
     * Gets the plug-in's icon, should the biggest available raster image
     * 
     * @return  The plug-in's icon, {@code null} if raster image is missing
     */
    public ImageIcon getBiggestIcon()
    {
	return null;
    }
    
    /**
     * Gets an array of all n:s such that a raster icon of size n × n exists,
     * if non-positive number is returned as an element, a vector icon designed for
     * ~n × ~n and larger exists.
     */
    public int[] getIconDimensions()
    {
	return new int[0];
    }
    
}

