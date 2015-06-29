package com.scie.wechat.reply;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.scie.wechat.bean.Student;
import com.scie.wechat.bean.UserRecorder;
import com.scie.wechat.function.impl.Apply;
import com.scie.wechat.message.resp.Article;
import com.scie.wechat.message.resp.NewsMessage;
import com.scie.wechat.message.resp.TextMessage;
import com.scie.wechat.service.VoterManager;
import com.scie.wechat.util.MessageUtil;
import com.scie.wechat.util.PropertiesTool;
import com.sice.wechat.function.Functions;

public class AutoReply {

	private String classView = "<a href=\"http://mp.weixin.qq.com/s?__biz=MjM5Nzc4NjczMg==&mid=201096557&idx=1&sn=71ff9fa35b6e577ea5aa723eca23cffe#rd\">选手风采展示</a>\n0--返回";
	private String secReply = "请输入你的姓名  学号  联系方式，以空格隔开（如：张三  2011... 1888...）";// "您可以选择以下功能:\n1--了解各个选手\n2--为他们投票\n3--查看当前票数\n0--返回";
	private String activityMsg = "1--running man 活动报名\n2--天使爱美丽  活动报名\n3--创意营销大赛\n0--返回";
	private String endMsg = "投票已结束,输入0返回";
	private String errMsg = "未知命令，请根据对应数字重新输入！";
	private String nvshen = "<a href='http://www.wxhand.com/addon/Baoming/WapBaoming/index/token/cb97f5e9755bb4604d79f1747560f987/id/7413.html'>天使爱美丽，点击报名</a>";
	private String wailian = "<a href='http://www.wxhand.com/addon/Baoming/WapBaoming/index/token/cb97f5e9755bb4604d79f1747560f987/id/7629.html'>创意营销大赛</a>";
	
	private String reqContent;

	private String fromUserName;

	private String toUserName;

	private long createTime;

	public AutoReply() {

	}

	public AutoReply(String reqContent, String fromUserName, String toUserName,
			long createTime, HttpServletRequest request) {
		super();
		this.reqContent = reqContent;
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

	private NewsMessage createNewsMessage() {
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(createTime);
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		return newsMessage;
	}

	public long getCreateTime() {
		return createTime;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public String getReqContent() {
		return reqContent;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public void setReqContent(String reqContent) {
		this.reqContent = reqContent;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String subscriptionReply() {
		String msg = "欢迎关注“重邮通信学院”！欢迎新同学！\n请根据序号回复对应数字\n1--学院介绍\n2--加入我们（招新报名）\n3--14级辅导员联系方式\n4--免费电话\n5--重邮新闻(通信学院)\n6--活动报名（running man 天使爱美丽）\n7--联系我们\n回复任意字母显示菜单";
		return msg;
	}

	// 第一级菜单
	public String textReply() {

		String msg = "";
		if (reqContent.equals("1")) {
			NewStudentReply newsReply = new NewStudentReply();
			NewsMessage nm = newsReply.news(fromUserName, toUserName,
					createTime, 2);
			msg = MessageUtil.newsMessageToXml(nm);
		} else if (reqContent.equals("9999")) {
			msg = "站酷：http://www.zcool.com.cn\n素材中国：http://www.sccnn.com\n昵图：http://www.nipic.com\n需要昵图账号的同学，请联系美宣部谢谢！";
			TextMessage tm = creatTest();
			tm.setContent(msg);
			msg = MessageUtil.textMessageToXml(tm);
		} else if (reqContent.equals("3")) {
			msg = "桂晓菁老师 62461089(办) 13996379964\n袁泉老师 62461089(办)15922589189";
			TextMessage tm = creatTest();
			tm.setContent(msg);
			msg = MessageUtil.textMessageToXml(tm);

		} else if (reqContent.equals("4")) {
			CallReply callReply = new CallReply();
			NewsMessage nm = callReply.news(fromUserName, toUserName,
					createTime);
			msg = MessageUtil.newsMessageToXml(nm);
		} else if (reqContent.equals("5")) {
			msg = new NewsReply().qinqinNews();
			TextMessage tm = creatTest();
			tm.setContent(msg);
			msg = MessageUtil.textMessageToXml(tm);
		} else if (reqContent.equals("6")) {
			msg = activityMsg;
			TextMessage tm = creatTest();
			tm.setContent(msg);
			msg = MessageUtil.textMessageToXml(tm);
			UserRecorder.getStatus().put(fromUserName, 2);

		} else if (reqContent.equals("7")) {
			msg = "如遇系统故障，数据错误等给您造成了不便，还请谅解，我们会在第一时间内修复。微信合作也请联系以下邮箱。谢谢！\n联系方式：  cqtianle@163.com";
			TextMessage tm = creatTest();
			tm.setContent(msg);
			msg = MessageUtil.textMessageToXml(tm);
		} else if (reqContent.equals("2") || reqContent.equals("招新")
				|| reqContent.equals("报名")) {
			/*
			 * NewStudentReply newsReply = new NewStudentReply(); NewsMessage nm
			 * = newsReply.news(fromUserName, toUserName, createTime, 1); msg =
			 * MessageUtil.newsMessageToXml(nm);
			 */
			msg = "<a href='http://scietx.duapp.com/app/activity_enrolls/index.html'>加入我们，点击报名</a>";
			TextMessage tm = creatTest();
			tm.setContent(msg);
			msg = MessageUtil.textMessageToXml(tm);
		} else if (reqContent.equals("天使爱美丽")) {
			msg = nvshen;
			TextMessage tm = creatTest();
			tm.setContent(msg);
			msg = MessageUtil.textMessageToXml(tm);
		} else {
			msg = this.subscriptionReply();
			TextMessage tm = creatTest();
			tm.setContent(msg);
			msg = MessageUtil.textMessageToXml(tm);
		}
		return msg;
	}

	// 第二级菜单(status==2)
	public String textReply_activity() {
		String msg = "";
		// 活动报名
		if (reqContent.equals("1")) {
			List<Article> articleList = new ArrayList<Article>();
			Functions apply = new Apply();
			apply.setUid(fromUserName);
			if (apply.isExist()) {
				Article article = new Article();
				article.setTitle("您已成功报名");
				String studentInf = apply.selectStuInf();
				article.setDescription(studentInf);
				PropertiesTool tool = new PropertiesTool();
				tool.load();
				/** 注意修改服务器 */
				article.setPicUrl("http://" + tool.getProperty("server")
						+ "/activityImg/" + fromUserName + ".png");
				article.setUrl("http://" + tool.getProperty("server")
						+ "/activityImg/" + fromUserName + ".png");

				articleList.add(article);
				NewsMessage nm = createNewsMessage();
				nm.setArticleCount(articleList.size());
				nm.setArticles(articleList);
				msg = MessageUtil.newsMessageToXml(nm);
				UserRecorder.getStatus().remove(fromUserName);
			} else {
				msg = secReply;
				TextMessage tm = creatTest();
				tm.setContent(msg);
				msg = MessageUtil.textMessageToXml(tm);
				UserRecorder.getStatus().put(fromUserName, 3);
			}
		} else if (reqContent.equals("2")) {
			msg = nvshen;
			TextMessage tm = creatTest();
			tm.setContent(msg);
			msg = MessageUtil.textMessageToXml(tm);
			UserRecorder.getStatus().remove(fromUserName);
		} else if (reqContent.equals("3")) {
			msg = wailian;
			TextMessage tm = creatTest();
			tm.setContent(msg);
			msg = MessageUtil.textMessageToXml(tm);
			UserRecorder.getStatus().remove(fromUserName);
		}else if (reqContent.equals("0")) {
			msg = this.subscriptionReply();
			TextMessage tm = creatTest();
			tm.setContent(msg);
			msg = MessageUtil.textMessageToXml(tm);
			UserRecorder.getStatus().remove(fromUserName);
		} else {
			msg = errMsg;
			TextMessage tm = creatTest();
			tm.setContent(msg);
			msg = MessageUtil.textMessageToXml(tm);
		}

		return msg;
	}

	// 第三级菜单(status==3)
	public String textReply_activity_advancedClass(HttpServletRequest request) {

		String msg = "";
		boolean sendImg = false;
		List<Article> articleList = new ArrayList<Article>();
		// 用户进行输入姓名+学号+电话
		// 进行验证
		String[] reqContents = null;
		if (reqContent != null && !reqContent.equals(""))
			reqContents = reqContent.trim().split("\\s+");
		if (reqContents != null && reqContents.length == 3) {
			// 中文验证
			Pattern namePattern = Pattern
					.compile("[\u4E00-\u9FA5\uF900-\uFA2D]");
			// 学号验证
			Pattern snumPattern = Pattern.compile("[sS2]{1}[0-9]{9}");
			// 联系方式验证
			Pattern wrongPhonePattern = Pattern.compile("[^0-9]");

			String name = reqContents[0].trim();
			String snum = reqContents[1].trim();
			String phone = reqContents[2].trim();
			Matcher nameMatcher = namePattern.matcher(name);
			// 姓名验证成功
			if (nameMatcher.find()) {

				Matcher snumMatcher = snumPattern.matcher(snum);
				// 学号验证成功
				if (snumMatcher.find()) {
					Matcher phoneMatcher = wrongPhonePattern.matcher(phone);
					// 电话验证成功
					if (!phoneMatcher.find()) {
						// 存储用户信息
						Functions apply = new Apply();
						Student student = new Student();
						student.setSname(name);
						student.setSphone(phone);
						student.setSnum(snum);
						apply.setStudent(student);
						apply.setUid(fromUserName);
						if (apply.save(request)) {// 存储成功，发送图文消息
							Article article = new Article();
							article.setTitle("恭喜你，报名成功！");
							article.setDescription("感谢您对本活动的支持╰(*°▽°*)╯\n请保存好上图的二维码，不然无法签到哦~\n请输入任意字母返回主菜单");
							PropertiesTool tool = new PropertiesTool();
							tool.load();
							/** 注意修改服务器 */
							article.setPicUrl("http://"
									+ tool.getProperty("server")
									+ "/activityImg/" + fromUserName + ".png");
							article.setUrl("http://"
									+ tool.getProperty("server")
									+ "/activityImg/" + fromUserName + ".png");

							articleList.add(article);
							sendImg = true;
						} else {
						}
						UserRecorder.getStatus().remove(fromUserName);

						// 验证不成功
					} else {
						msg = "联系方式输入有误，请重新输入全部信息\n输入0返回主菜单";

					}
				} else {
					msg = "学号输入有误，请重新输入全部信息\n输入0返回主菜单";
				}
			} else {
				msg = "姓名输入有误，请重新输入全部信息\n输入0返回主菜单";

			}

		} else if (reqContent.equals("0")) {
			msg = this.subscriptionReply();
			UserRecorder.getStatus().remove(fromUserName);
		}

		else {
			msg = errMsg + "\n输入0返回主菜单";
		}

		if (sendImg) {
			NewsMessage nm = createNewsMessage();
			nm.setArticleCount(articleList.size());
			nm.setArticles(articleList);
			msg = MessageUtil.newsMessageToXml(nm);
		} else {
			TextMessage tm = creatTest();
			tm.setContent(msg);
			msg = MessageUtil.textMessageToXml(tm);
		}

		return msg;
	}

	private String voteView() {

		VoterManager vm = new VoterManager();
		String msg = vm.voteCount();
		msg = msg + secReply;
		return msg;
	}

}
