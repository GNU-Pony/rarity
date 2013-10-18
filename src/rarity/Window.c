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
#include "Window.h"
#include "global.h"



/**
 * Flush position changes to the display
 * 
 * @param  address  The memory address of the window
 * @param  x        The left X-axis position of the window
 * @param  y        The top Y-axis position of the window
 */
void Java_rarity_Window_xMoveWindow(JNIEnv* env, jclass class, jlong address, jint x, jint y)
{
  (void) env;
  (void) class;
  
  XMoveWindow(display, (Window)(long)address, x, y);
  
  // TODO: XMoveWindow can generate a BadWindow error.
}


/**
 * Flush size changes to the display
 * 
 * @param  address  The memory address of the window
 * @param  width    The width of the window
 * @param  height   The height of the window
 */
void Java_rarity_Window_xResizeWindow(JNIEnv* env, jclass class, jlong address, jint width, jint height)
{
  (void) env;
  (void) class;
  
  XResizeWindow(display, (Window)(long)address, width, height);
  
  // TODO: XResizeWindow can generate BadValue and BadWindow errors.
}


/**
 * Flush position and size changes to the display
 * 
 * @param  address  The memory address of the window
 * @param  x        The left X-axis position of the window
 * @param  y        The top Y-axis position of the window
 * @param  width    The width of the window
 * @param  height   The height of the window
 */
void Java_rarity_Window_xMoveResizeWindow(JNIEnv* env, jclass class, jlong address, jint x, jint y, jint width, jint height)
{
  (void) env;
  (void) class;
  
  XMoveResizeWindow(display, (Window)(long)address, x, y, width, height);
  
  // TODO: XMoveResizeWindow can generate BadValue and BadWindow errors.
}



/**
 * Deletes a property only if the property was defined on a window
 * 
 * @param  address   The memory address of the window
 * @param  property  The property
 */
void Java_rarity_Window_xDeleteProperty(JNIEnv* env, jclass class, jlong address, jint property)
{
  (void) env;
  (void) class;
  
  (void) XDeleteProperty(display, (Window)(long)address, (Atom)property);
  
  // TODO: BadAtom BadWindow
}


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
void Java_rarity_Window_xRotateWindowProperties(JNIEnv* env, jclass class, jlong address, jintArray properties, jint positions)
{
  int i, n = (*env)->GetArrayLength(env, properties);
  jint* array = (*env)->GetIntArrayElements(env, properties, NULL);
  Atom* properties_ = malloc(n * sizeof(Atom));
  
  (void) class;
  
  for (i = 0; i < n; i++)
    *(properties_ + i) = (Atom)*(array + i);
  
  (*env)->ReleaseIntArrayElements(env, properties, array, JNI_ABORT);
  
  (void) XRotateWindowProperties(display, (Window)(long)address, properties_, n, positions);
  
  free(properties_);
  
  // TODO: BadAtom BadMatch BadWindow (malloc)
}


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
void Java_rarity_Window_xChangeProperty(JNIEnv* env, jclass class, jlong address, jint property, jint type, jint format, jint mode, jbyteArray data)
{
  int i, n = (*env)->GetArrayLength(env, data);
  jbyte* array = (*env)->GetByteArrayElements(env, data, NULL);
  
  (void) class;
  
  #define __(M, A, S)  ((((int)*(array + ((i << M) | A))) & 255) << S)
  
  if (format == 8)
    {
      char* data_ = malloc(n * sizeof(char));
      for (i = 0; i < n; i++)
	*(data_ + i) = *(array + i);
      (void) XChangeProperty(display, (Window)(long)address, (Atom)property, (Atom)type, format, mode, (unsigned char*)data_, n);
      free(data_);
    }
  else if (format == 16)
    {
      short* data_ = malloc(n * sizeof(short));
      for (i = 0; i < n; i++)
	*(data_ + i) = (short)(__(1, 0, 0) | __(1, 1, 8));
      (void) XChangeProperty(display, (Window)(long)address, (Atom)property, (Atom)type, format, mode, (unsigned char*)data_, n);
      free(data_);
    }
  else if (format == 32)
    {
      long* data_ = malloc(n * sizeof(long));
      for (i = 0; i < n; i++)
	*(data_ + i) = (long)(__(2, 0, 0) | __(2, 1, 8) | __(2, 2, 16) | __(2, 3, 24));
      (void) XChangeProperty(display, (Window)(long)address, (Atom)property, (Atom)type, format, mode, (unsigned char*)data_, n);
      free(data_);
    }
  
  #undef __
  
  (*env)->ReleaseByteArrayElements(env, data, array, JNI_ABORT);
  
  // TODO: BadAlloc BadAtom BadMatch BadValue BadWindow (malloc) (malloc) (malloc)
}


/**
 * Gets a list of all properties on a window
 * 
 * @param   address  The memory address of the window
 * @return           All properties on the window, a zero-length array if none were found, never {@code null}
 */
jintArray Java_rarity_Window_xListProperties(JNIEnv* env, jclass class, jlong address)
{
  (void) env;
  (void) class;
  
  int i, n;
  Atom* properties = XListProperties(display, (Window)(long)address, &n);
  if (properties == NULL)
    n = 0;
  jintArray rc = (*env)->NewIntArray(env, n);
  int* data = malloc(n * sizeof(int));
  
  for (i = 0; i < n; i++)
    *(data + i) = (int)*(properties + i);
  
  (*env)->SetIntArrayRegion(env, rc, 0, n, data);
  
  XFree(properties);
  free(data);
  return rc;
  
  // TODO: BadWindow (malloc)
}


/**
 * Gets or deletes a property on a window
 * 
 * @param   address      The memory address of the window
 * @param   property     The property
 * @param   requestType  Atom identifier associated with the property type
 * @return               Format(1 byte) || Type(4 bytes) || Value
 */
jbyteArray Java_rarity_Window_xGetWindowProperty(JNIEnv* env, jclass class, jlong address, jint property, jint requestType)
{
  Atom type;
  int format, j, k;
  unsigned long i, n, m;
  unsigned char* data;
  
  int skip = 0;
  long len;
  jbyte* raw;
  jbyteArray rc;
  
  (void) class;
  
  #define d  display
  #define w  (Window)(long)address
  #define p  (Atom)property
  #define t  (Atom)requestType
  
  XGetWindowProperty(d, w, p, 0, 1, 0, t, &type, &format, &n, &m, &data);
  XFree(data);
  
  len = (n * format / 8 + m) / 4;
  raw = malloc((5 + len * 4) * sizeof(jbyte));
  XGetWindowProperty(d, w, p, 0, len, 0, t, &type, &format, &n, &m, &data);
  
  #undef t
  #undef p
  #undef w
  #undef d
  
  *raw = format;
  *(raw + 1) = (((int)type) >> 24) & 255;
  *(raw + 1) = (((int)type) >> 16) & 255;
  *(raw + 1) = (((int)type) >>  8) & 255;
  *(raw + 1) = (((int)type) >>  0) & 255;
  raw += 5;
  
  if ((format == 32) && (format != sizeof(long) * 8))
    skip = sizeof(long) - format / 8;
  
  m = format / 8;
  for (i = j = k = 0; i < n; i++, k += skip)
    for (; j < format; j++)
      *(raw + j) = *(data + k++);
  
  XFree(data);
  raw -= 5;
  
  rc = (*env)->NewByteArray(env, n);
  (*env)->SetByteArrayRegion(env, rc, 0, 5 + j, raw);
  
  return rc;
  
  // TODO: BadAtom BadValue BadWindow (malloc)
}
