package com.patrick;

import com.patrick.classloader.ClassLoader2;

/**
 * java.io.FileNotFoundException: lib/java/lang/Object.class
 * 
 * @author Patrick Pan
 *
 */
public class Main2 {
	public static void main(String[] args) throws Exception {
		new ClassLoader2().loadClass("com.patrick.obj.Object2");
	}
}
