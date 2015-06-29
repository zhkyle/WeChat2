package com.scie.wechat.message.resp;

/**
 * 
 * @author tianle
 * @date 2014-08-06
 */
public class VideoMessage extends BaseMessage {
	private String MediaId;
	private String Title;
	private String Description;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

}
