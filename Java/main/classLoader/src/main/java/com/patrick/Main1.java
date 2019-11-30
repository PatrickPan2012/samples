package com.patrick;

import com.patrick.classloader.ClassLoader1;
import com.patrick.obj.Object1;

/**
 * java.lang.ClassCastException: com.patrick.obj.ObjectA cannot be cast to
 * com.patrick.obj.ObjectA
 * 
 * @author Patrick Pan
 *
 */
public class Main1 {
	public static void main(String[] args) throws Exception {
		Class<?> clazz = new ClassLoader1().loadClass("com.patrick.obj.Object1");
		Object obj = clazz.newInstance();
		obj = (Object1) obj;
	}
}
