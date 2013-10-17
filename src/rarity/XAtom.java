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
 * X11 atom class
 * 
 * @author  Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class XAtom
{
    /**
     * Constructor
     * 
     * @param  atom  The identifier of the atom
     */
    public XAtom(final int atom)
    {
	this.atom = atom;
    }
    
    /**
     * Constructor
     * 
     * @param  name    The name of the atom
     * @param  create  Whether to create the atom if it does not already exist
     */
    public XAtom(final String name, boolean create)
    {
	this.atom = internAtom(name, create ^ true);
    }
    
    
    
    /**
     * The identifier of the atom
     */
    public final int atom;
    
    
    
    
    /**
     * Gets the atom identifier associated with a specified name
     * 
     * @param   name          The name of the atom
     * @param   onlyIfExists  Whether the atom must have been created
     * @return                The atom identifier
     */
    private static native int internAtom(String name, boolean onlyIfExists);
    // return XInternAtom(display, name, onlyIfExists);
    
    
    
    /**
     * Gets the name of the atom
     * 
     * @return  The name of the atom
     */
    public String getName()
    {
	return getAtomName(this.atom);
    }
    
    /**
     * Gets the name of the atom
     * 
     * @param   atom  The atom's identifier
     * @return        The name of the atom
     */
    private static native String getAtomName(int atom);
    // return XGetAtomName(display, atom);
    
    
    
    #for atom in $(cat /usr/include/X11/Xatom.h | grep -v 'XATOM_H' | grep '^#define XA_' | cut -d ' ' -f 2); do
    #    value="$((cat /usr/include/X11/Xatom.h | grep -v XATOM_H | grep '^#define XA_' ; echo $atom) | cpp | tail -n 1)"
    #    value=$(echo "$value" | grep -o '[0-9]*')
	 public static final int <"$atom$"> = <"$value$">;
    #done
    
}

