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

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;


/**
 * This class is used to enable plug-in support
 * 
 * @author   Mattias Andrée, <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 * @version  1.0
 */
public class PluginHandler
{
    /**
     * The name of the plug-in classes
     */
    private static final String PLUGIN_CLASS_NAME = "Plugin";
    
    
    
    /**
     * Constructor hiding
     */
    private PluginHandler()
    {
	/* do nothing */
    }
    
    
    
    /**
     * The plug-ins instanciated
     */
    private static Vector<PluginV1> pluginInstances = new Vector<PluginV1>();
    
    /**
     * The plug-in files, list
     */
    private static Vector<String> pluginFiles = new Vector<String>();
    
    /**
     * The plug-in files, set
     */
    private static HashSet<String> pluginHash = new HashSet<String>();
    
    /**
     * The active plug-ins
     */
    private static HashSet<PluginV1> activePlugins = new HashSet<PluginV1>();
    
    /**
     * Map from plug-in file to last modification date
     */
    private static HashMap<String, Long> pluginMDates = new HashMap<String, Long>();
    
    
    
    /**
     * Gets the count of plug-ins
     * 
     * @return  The count of plug-ins
     */
    public static int getPluginCount()
    {
	synchronized (PluginHander.class)
	{   return pluginInstances.size();
	}
    }
    
    
    /**
     * Gets a plug-in by its index
     * 
     * @param   index  The index of the plug-in
     * @return         The plug-in
     */
    public static PluginV1 getPlugin(final int index)
    {
	synchronized (PluginHander.class)
	{   return pluginInstances.get(index);
	}
    }
    
    
    /**
     * Gets whether a plug-in is activated
     *
     * @param   plugin  The index of the plug-in
     * @return          Whether the plug-in is activated
     */
    public static boolean isActive(final int plugin)
    {
	synchronized (PluginHander.class)
	{   return activePlugins.contains(pluginInstances.get(plugin));
	}
    }
    
    
    /**
     * Sets whether a plug-in is activated
     *
     * @param  plugin  The index of the plug-in
     * @param  active  Whether the plug-in should be active
     */
    public static void setActive(final int plugin, final boolean active)
    {
	synchronized (PluginHander.class)
	{   try
	    {   if (activePlugins.contains(pluginInstances.get(plugin)) ^ active)
		    if (active)  {  activePlugins.add   (pluginInstances.get(plugin));  pluginInstances.get(plugin).initialise();  }
		    else         {  activePlugins.remove(pluginInstances.get(plugin));  pluginInstances.get(plugin).terminate();   }
	    }
	    catch (final Throwable err)
	    {   System.err.println("Problem with plug-in " + (active ? "activation" : "deactivation") + ": " + err.toString());
	}   }
    }
    
    
    /**
     * Updates a plug-in, for this to work, the plug-in cannot be in used and the may but be no external references to it.
     * 
     * @param  plugin  The index of the plug-in
     */
    public static void updatePlugin(final int plugin)
    {
	synchronized (PluginHander.class)
	{   try
	    {   {   final PluginV1 _plugin = pluginInstances.get(plugin);
		    if (activePlugins.contains(_plugin))
			return;
		}
		pluginInstances.set(plugin, null);
		System.gc();
		pluginInstances.set(plugin, getPluginInstance(pluginFiles.get(plugin)));
	    }
	    catch (final Throwable err)
	    {   System.err.println("Problem with plug-in updating: " + err.toString());
	}   }
    }
    
    
    /**
     * Gets the plug-in as an instance
     *
     * @param   name       The file name of the plug-in
     * @return             The plug-in as an instance
     * @throws  Exception  If the plug-in can't be loaded
     */
    private static PluginV1 getPluginInstance(final String path) throws Exception
    {
	final URL url = (new File(path)).toURI().toURL();
	String name = path.substring(path.lastIndexOf('/') + 1);
	name = name.substring(0, name.length() - 3) + PLUGIN_CLASS_NAME;
	synchronized (PluginHander.class)
	{   try (URLClassLoader classLoader = new URLClassLoader(new URL[]{url}))
	    {
		@SuppressWarnings("unchecked")
		Class<PluginV1> klass = (Class<PluginV1>)(classLoader.loadClass(name));
		return klass.newInstance();
	}   }
    }
    
    
    /**
     * Stops all unlisted plugins, starts all newly listed plugins and restarts all updated plugins
     */
    public static void restartPlugins()
    {
	synchronized (PluginHander.class)
	{   try
	    {
		final Vector<String> newFiles = new Vector<String>();
		final HashSet<String> gotHash = new HashSet<String>();
		
		for (final String line : readFile(pluginFile).replace('\t', ' ').split('\n'))
		{   if ((line.length() == 0) || line.replace(" ", "").startsWith("#"))
			continue;
		    if (pluginHash.contains(line) == false)
			newFiles.add(line);
		    gotHash.add(line);
		}
		
		for (int i = 0, n = pluginFiles.size(); i < n; i++)
		    if (gotHash.contains(pluginFiles.get(i)) == false)
		    {
			if (activePlugins.contains(pluginInstances.get(i)))
			{   setActive(i, false);
			    activePlugins.remove(pluginInstances.get(i));
			}
			pluginHash.remove(pluginFiles.get(i));
			pluginMDates.remove(pluginFiles.get(i));
			pluginFiles.remove(i);
			pluginInstances.remove(i);
			n--;
			i--;
		    }
		    else if (isActive(i))
		    {
			long newMDate = (new File(newFile)).lastModified();
			long oldMDate = pluginMDates.get(newFile).longValue();
			if (newMDate != oldMDate)
			{
			    pluginMDates.put(newFile, Long.valueOf(newMDate));
			    updatePlugin(i);
			}
		    }
		
		int i = pluginFiles.size();
		for (final String newFile : newFiles)
		{
		    pluginHash.add(newFile);
		    pluginFiles.add(newFile);
		    pluginMDates.put(newFile, Long.valueOf((new File(newFile)).lastModified()));
		    pluginInstances.add(getPluginInstance(newFile));
		    activePlugins.add(pluginInstances.get(i));
		    setActive(i, true);
		    i++;
		}
	    }
	    catch (final Throwable err)
	    {   System.err.println("Problem with initial plug-in activation: " + err.toString());
	}   }
    }
    
    
    /**
     * Read a file as text
     * 
     * @param   file  The file
     * @return        The text in the file
     * 
     * @throws  IOException  On file reading error
     */
    private static String readFile(final String file) throws IOException
    {
	final String text;
	InputStream is = null;
	try
	{
	    is = new BufferedInputStream(new FileInputStream(new File(file)));
	    final Vector<byte[]> bufs = new Vector<byte[]>();
	    int size = 0;
	    
	    for (int av; (av = is.available()) > 0;)
	    {
		byte[] buf = new byte[av];
		av = is.read(buf, 0, av);
		if (av < buf.length)
		{
		    final byte[] nbuf = new byte[av];
		    System.arraycopy(buf, 0, nbuf, 0, av);
		    buf = nbuf;
		}
		size += av;
		bufs.add(buf);
	    }
	    
	    final byte[] full = new byte[size];
	    int ptr = 0;
	    for (final byte[] buf : bufs)
	    {
		System.arraycopy(buf, 0, full, ptr, buf.length);
		ptr += buf.length;
	    }
	    
	    text = new String(full, "UTF-8");
	}
	finally
	{   if (is != null)
		try
		{   is.close();
		}
		catch (final Throwable ignore)
		{   /* ignore */
	}       }
	return text;
    }
    
}

