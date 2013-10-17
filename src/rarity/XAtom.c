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
#include "XAtom.h"
#include "global.h"


/**
 * Gets the atom identifier associated with a specified name
 * 
 * @param   name          The name of the atom
 * @param   onlyIfExists  Whether the atom must have been created
 * @return                The atom identifier
 */
jint Java_rarity_XAtom_internAtom(JNIEnv* env, jclass class, jstring name, jboolean onlyIfExists)
{
  (void) class;
  
  const char* c_name = (*env)->GetStringUTFChars(env, name, 0);
  return XInternAtom(display, c_name, onlyIfExists);
}


/**
 * Gets the name of the atom
 * 
 * @param   atom  The atom's identifier
 * @return        The name of the atom
 */
jstring Java_rarity_XAtom_getAtomName(JNIEnv* env, jclass class, jint atom)
{
  (void) class;
  
  char* c_name = XGetAtomName(display, atom);
  return (*env)->NewStringUTF(env, c_name);
}

