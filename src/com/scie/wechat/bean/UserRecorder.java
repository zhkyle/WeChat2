package com.scie.wechat.bean;

import java.util.HashMap;
import java.util.Map;

public class UserRecorder {
	private static UserRecorder instance = null;
	private static Map<String, Integer> status = new HashMap<String, Integer>();

	private UserRecorder() {
	}

	static UserRecorder getInstance() {
		if (instance == null) {
			instance = new UserRecorder();

		}
		return instance;
	}

	public static Map<String, Integer> getStatus() {
		return status;
	}

	public static void setStatus(Map<String, Integer> status) {
		UserRecorder.status = status;
	}
	


}
