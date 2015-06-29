package com.scie.wechat.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.scie.wechat.bean.UserRecorder;
import com.scie.wechat.message.resp.TextMessage;
import com.scie.wechat.reply.AutoReply;
import com.scie.wechat.reply.ButtonReply;
import com.scie.wechat.util.MessageUtil;

public class CoreService {

	public static String processRequest(HttpServletRequest request) {

		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			// textMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String msgContent = requestMap.get("Content");
				AutoReply ar = new AutoReply(msgContent, fromUserName,
						toUserName, new Date().getTime(), request);
				if (UserRecorder.getStatus().get(fromUserName) == null
						|| UserRecorder.getStatus().get(fromUserName) == 1)
					respMessage = ar.textReply();
				else if (UserRecorder.getStatus().get(fromUserName) == 2)
					respMessage = ar.textReply_activity();
				else if (UserRecorder.getStatus().get(fromUserName) == 3)
					respMessage = ar.textReply_activity_advancedClass(request);
				/*
				else if (UserRecorder.getStatus().get(fromUserName) == 41)
					respMessage = ar.textReply_activity_advancedClass_show();
				else if (UserRecorder.getStatus().get(fromUserName) == 42)
					respMessage = ar.textReply_activity_advancedClass_vote();
				*/
				System.out.println("respMessage--> " + respMessage);
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您的图片已收到，感谢参加！";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = new AutoReply().subscriptionReply();
					textMessage.setContent(respContent);
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");
					if (eventKey != null) {
						ButtonReply br = new ButtonReply(fromUserName,
								toUserName, new Date().getTime(), eventKey);
						respMessage = br.textReply();
					}
					System.out.println("respMessage(button)-->\n "
							+ respMessage);
				}
			}

			// textMessage.setContent(respContent);
			// respMessage = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

	/*
	*//**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	/*
	 * public static String processRequest(HttpServletRequest request) { String
	 * respMessage = null; try { // 默认返回的文本消息内容 String respContent =
	 * "请求处理异常，请稍候尝试！";
	 * 
	 * // xml请求解析 Map<String, String> requestMap =
	 * MessageUtil.parseXml(request);
	 * 
	 * // 发送方帐号（open_id） String fromUserName = requestMap.get("FromUserName");
	 * // 公众帐号 String toUserName = requestMap.get("ToUserName"); // 消息类型 String
	 * msgType = requestMap.get("MsgType");
	 * 
	 * // 回复文本消息 TextMessage textMessage = new TextMessage();
	 * textMessage.setToUserName(fromUserName);
	 * textMessage.setFromUserName(toUserName); textMessage.setCreateTime(new
	 * Date().getTime());
	 * textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT); //
	 * textMessage.setFuncFlag(0);
	 * 
	 * // 文本消息 if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { String
	 * msgContent = requestMap.get("Content"); AutoReply ar = new AutoReply();
	 * respContent = ar.textReply(msgContent); } // 图片消息 else if
	 * (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { respContent =
	 * "您发送的是图片消息！"; } // 地理位置消息 else if
	 * (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { respContent =
	 * "您发送的是地理位置消息！"; } // 链接消息 else if
	 * (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { respContent =
	 * "您发送的是链接消息！"; } // 音频消息 else if
	 * (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { respContent =
	 * "您发送的是音频消息！"; } // 事件推送 else if
	 * (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) { // 事件类型 String
	 * eventType = requestMap.get("Event"); // 订阅 if
	 * (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { respContent = new
	 * AutoReply().subscriptionReply(); } // 取消订阅 else if
	 * (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { // TODO
	 * 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息 } // 自定义菜单点击事件 else if
	 * (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) { // TODO
	 * 自定义菜单权没有开放，暂不处理该类消息 } }
	 * 
	 * textMessage.setContent(respContent); respMessage =
	 * MessageUtil.textMessageToXml(textMessage); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * return respMessage; }
	 */
}
