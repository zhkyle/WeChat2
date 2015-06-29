package com.scie.wechat.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 
 * Comments: 获得accesstoken，在AccessTokenServlet中初始化调用
 * 
 * @author Kyle
 * @date 2015年1月30日 下午3:04:28
 */
public class AccessTokenUtil {

	public static Map<String, String> accessMap = new HashMap<String, String>();

	public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
	public static final String APP_ID = "wxb4dc5505db247fb5";
	public static final String SECRET = "fc985aec656403890ec1669e4cf1073c";
	
//	public static final String APP_ID = "wx075b6b099bd84af9";
//	public static final String SECRET = "23b7e4f4ec20b8e7d4dfe41ee4f250c4";

	public static String accessToken;

	public static Map<String, String> accessToken() {
		Timer timer = new Timer();
		TimerTask tTask = new TimerTask() {
			@Override
			public void run() {
				accessMap = getAccessToken();
				printAccessToken();
			}
		};
		accessMap = getAccessToken();
		printAccessToken();
		long expiresIn = Long.parseLong(accessMap.get("expires_in"));
		// 延迟0秒，过expire后每隔一段时间执行
		timer.schedule(tTask, 0, expiresIn * 1000);
		return accessMap;
	}

	public static Map<String, String> getAccessToken() {

		String turl = String.format(
				"%s?grant_type=client_credential&appid=%s&secret=%s",
				GET_TOKEN_URL, APP_ID, SECRET);
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(turl);
		try {
			HttpResponse res = client.execute(get);
			String responseContent = null; // 响应内容
			HttpEntity entity = res.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
			JSONObject json = JSONObject.fromObject(responseContent);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				if (json.get("errcode") != null) {// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
				} else {// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
					accessMap.put("access_token",
							json.getString("access_token"));
					accessMap.put("expires_in", json.getString("expires_in"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接 ,释放资源
			client.getConnectionManager().shutdown();
			return accessMap;
		}
	}

	private static void printAccessToken() {
		System.out.println("access_token: "
				+ AccessTokenUtil.accessMap.get("access_token"));
		System.out.println("expires_in: "
				+ AccessTokenUtil.accessMap.get("expires_in"));
	}

}
