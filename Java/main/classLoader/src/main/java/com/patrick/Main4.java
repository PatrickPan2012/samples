package com.patrick;

import java.lang.reflect.Method;

import com.patrick.classloader.ClassLoader1;
import com.patrick.obj.Object2;

/**
 * java.lang.ClassCastException: com.patrick.obj.Object2 cannot be cast to
 * com.patrick.obj.Object2
 * 
 * @author Patrick Pan
 *
 */
public class Main4 {
	public static void main(String[] args) throws Exception {
		Class<?> clazz = new ClassLoader1().loadClass("com.patrick.obj.Object1");
		Object object = clazz.newInstance();
		Method newObject2 = clazz.getDeclaredMethod("newObject2");
		object = (Object2) newObject2.invoke(object);
	}
}
