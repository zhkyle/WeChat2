package com.scie.wechat.message.resp;

/**
 * ÒôÆµÏûÏ¢
 * 
 * @author tianle
 * @date 2014-08-06
 */
public class VoiceMessage extends BaseMessage {
	// Ã½ÌåID
	private String MediaId;
	

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

}
