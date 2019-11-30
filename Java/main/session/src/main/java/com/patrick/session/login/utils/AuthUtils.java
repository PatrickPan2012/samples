package com.patrick.session.login.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Patrick Pan
 *
 */
public class AuthUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger("sessionLogger");

	private static final Set<String> SESSION_IDS = Collections.synchronizedSet(new HashSet<>());

	private AuthUtils() {
	}

	public static void addSessionId(String sessionId) {
		LOGGER.debug("Session id: [{}]", sessionId);
		SESSION_IDS.add(sessionId);
	}

	public static boolean isSessionIdValid(String sessionId) {
		LOGGER.debug("Session id: [{}]", sessionId);
		return SESSION_IDS.contains(sessionId);
	}
}
