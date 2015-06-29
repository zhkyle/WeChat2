package com.scie.wechat.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.scie.wechat.util.QEncodeUtil;

public class TestStringByte {
	public static void main(String[] args) {
		String str = "oAVsCuMW94MZ-wQmjfrRX96LE4ps";
		String password = "1234567890123456";
		try {
			String encryptResult = QEncodeUtil.encrypt(password, str);
			String result = QEncodeUtil.decrypt(password, encryptResult);
			System.out.println(encryptResult);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testRegex() {

		Matcher m = Pattern.compile("[sS2]{1}[0-9]{9}").matcher("S201221568");
		while (m.find()) {
			System.out.println(m.group());
		}
	}
}
