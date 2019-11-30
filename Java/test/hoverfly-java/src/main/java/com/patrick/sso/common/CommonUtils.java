package com.patrick.sso.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * 
 * @author Patrick Pan
 *
 */
public class CommonUtils {

	private CommonUtils() {
	}

	public static String inputStreamToString(InputStream inputStream) {
		return new BufferedReader(new InputStreamReader(inputStream)).lines()
				.collect(Collectors.joining(System.lineSeparator()));
	}
}
