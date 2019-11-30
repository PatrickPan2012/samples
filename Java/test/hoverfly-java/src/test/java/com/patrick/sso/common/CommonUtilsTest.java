package com.patrick.sso.common;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

/**
 * 
 * @author Patrick Pan
 *
 */
public class CommonUtilsTest {

	@Test
	public void testInputStreamToString() {
		final String str = new StringBuilder("test1").append(System.lineSeparator()).append("test2").toString();
		InputStream inputStream = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
		String newStr = CommonUtils.inputStreamToString(inputStream);
		assertEquals(str, newStr);
	}
}
