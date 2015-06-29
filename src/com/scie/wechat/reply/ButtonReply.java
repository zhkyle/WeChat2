package com.scie.wechat.reply;


import com.scie.wechat.bean.UserRecorder;
import com.scie.wechat.message.resp.NewsMessage;
import com.scie.wechat.message.resp.TextMessage;
import com.scie.wechat.util.MessageUtil;

/**
 * 
 * Comments:按钮回复
 * 
 * @author Kyle
 * @date 2015年1月31日 下午4:21:34
 */
public class ButtonReply {

	private String activityMsg = "以下是最近的学院活动：\n1--running man 活动报名\n2--天使爱美丽  活动报名\n3--创意营销大赛\n0--返回";

	private String eventKey = "";
	private String fromUserName;
	private String toUserName;
	private long createTime;

	public ButtonReply(String fromUserName, String toUserName, long createTime,
			String eventKey) {
		this.eventKey = eventKey;
		this.fromUserName = fromUserName;
		this.toUserName = toUserName;
		this.createTime = createTime;
	}

	private TextMessage creatTest() {
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(createTime);
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		return textMessage;
	}

	public String textReply() {

		String msg = "";

		if (eventKey.equals("11")) {
			NewStudentReply newsReply = new NewStudentReply();
			NewsMessage nm = newsReply.news(fromUserName, toUserName,
					createTime, 2);
			msg = MessageUtil.newsMessageToXml(nm);
		} else if (eventKey.equals("12")) {
			msg = new NewsReply().qinqinNews();
			TextMessage tm = creatTest();
			tm.setContent(msg);
			msg = MessageUtil.textMessageToXml(tm);
		} else if (eventKey.equals("21")) {
			msg = "<a href='http://scietx.duapp.com/app/activity_enrolls/index.html'>加入我们，点击报名</a>";
			TextMessage tm = creatTest();
			tm.setContent(msg);
			msg = MessageUtil.textMessageToXml(tm);
		} else if (eventKey.equals("22")) {
			msg = activityMsg;
			TextMessage tm = creatTest();
			tm.setContent(msg);
			msg = MessageUtil.textMessageToXml(tm);
			UserRecorder.getStatus().put(fromUserName, 2);
		} else if (eventKey.equals("3")) {
			msg = "<a href='http://scietx.duapp.com/app/activity_enrolls/index.html'>加入我们，点击报名</a>";
			TextMessage tm = creatTest();
			tm.setContent(msg);
			msg = MessageUtil.textMessageToXml(tm);
		} 
		return msg;
	}
}
