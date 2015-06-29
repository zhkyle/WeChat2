package com.scie.wechat.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignUtil {

	private static String token = "scie";

	public static boolean checkSignature(String signature, String timestamp,
			String nonce) throws NoSuchAlgorithmException {

		boolean check;
		// 对请求参数和自己的token进行排序，并连接排序后的结果为一个字符串
		String[] strSet = new String[] { token, timestamp, nonce };
		java.util.Arrays.sort(strSet);
		String total = "";
		for (String string : strSet) {
			total = total + string;
		}
		// SHA-1加密实例
		MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
		sha1.update(total.getBytes());
		byte[] codedBytes = sha1.digest();
		// 将加密后的字节数组转换成字符串。参见http://hi.baidu.com/aotori/item/c94813c4f15caa60f6c95d4a
		String codedString = new BigInteger(1, codedBytes).toString(16);
		check = codedString.equals(signature);
		return check;
	}
}
