package com.patrick.classloader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 
 * @author Patrick Pan
 *
 */
public class ClassLoader2 extends ClassLoader {

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		return this.findClass(name);
	}

	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			byte[] bytes = getBytes(name);
			return this.defineClass(name, bytes, 0, bytes.length);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ClassNotFoundException("Exception occurs in MyClassLoader.getBytes.");
		}
	}

	private byte[] getBytes(String classFileName) throws IOException {
		int hasRead = 0;
		byte[] buffer = new byte[1024];

		try (BufferedInputStream inputStream = new BufferedInputStream(
				new FileInputStream(String.format("lib/%s.class", classFileName.replace('.', File.separatorChar))));
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			while ((hasRead = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, hasRead);
			}
			return outputStream.toByteArray();
		}
	}
}
