package com.patrick;

import com.patrick.classloader.ClassLoader1;

/**
 * java.lang.LinkageError: loader (instance of
 * com/patrick/classloader/ClassLoader1): attempted duplicate class definition
 * for name: "com/patrick/obj/Object1"
 * 
 * @author Patrick Pan
 *
 */
public class Main3 {
	public static void main(String[] args) throws Exception {
		ClassLoader1 classLoader = new ClassLoader1();
		classLoader.loadClass("com.patrick.obj.Object1");
		classLoader.loadClass("com.patrick.obj.Object1");
	}
}
