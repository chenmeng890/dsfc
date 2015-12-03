/**
 * 
 */
package net.easipay.dsfc;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import net.easipay.dsfc.ws.WsCharset;
import net.easipay.dsfc.ws.WsLogger;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class ClassUtils
{

    /**
     * 从包package中获取所有的Class
     * 
     * @param pack
     * @return
     */
    public static Set<Class<?>> getClasses(String packageName)
    {

	Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
	boolean recursive = true;
	String packageDirName = packageName.replace('.', '/');
	Enumeration<URL> dirs;
	try {
	    dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
	    while (dirs.hasMoreElements()) {
		URL url = dirs.nextElement();
		String protocol = url.getProtocol();
		if ("file".equals(protocol)) {
		    String filePath = URLDecoder.decode(url.getFile(), WsCharset.CHARST_UTF8);
		    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
		}
		else {
		    JarFile jar;
		    try {
			jar = ((JarURLConnection) url.openConnection()).getJarFile();
			Enumeration<JarEntry> entries = jar.entries();
			while (entries.hasMoreElements()) {
			    JarEntry entry = entries.nextElement();
			    String name = entry.getName();
			    if (name.charAt(0) == '/') {
				name = name.substring(1);
			    }
			    if (name.startsWith(packageDirName)) {
				int idx = name.lastIndexOf('/');
				if (idx != -1) {
				    packageName = name.substring(0, idx).replace('/', '.');
				}
				if ((idx != -1) || recursive) {
				    if (name.endsWith(".class") && !entry.isDirectory()) {
					String className = name.substring(packageName.length() + 1, name.length() - 6);
					try {
					    classes.add(Class.forName(packageName + '.' + className));
					} catch ( ClassNotFoundException e ) {
					    WsLogger.error(e);
					}
				    }
				}
			    }
			}
		    } catch ( IOException e ) {
			WsLogger.error(e);
		    }
		}
	    }
	} catch ( IOException e ) {
	    WsLogger.error(e);
	}

	return classes;
    }

    /**
     * 以文件的形式来获取包下的所有Class
     * 
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes)
    {
	File dir = new File(packagePath);
	if (!dir.exists() || !dir.isDirectory()) {
	    return;
	}
	File[] dirfiles = dir.listFiles(new FileFilter() {
	    public boolean accept(File file)
	    {
		return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
	    }
	});
	for (File file : dirfiles) {
	    if (file.isDirectory()) {
		findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
	    }
	    else {
		String className = file.getName().substring(0, file.getName().length() - 6);
		try {
		    classes.add(Class.forName(packageName + '.' + className));
		} catch ( ClassNotFoundException e ) {
		    WsLogger.error(e);
		}
	    }
	}
    }
}
