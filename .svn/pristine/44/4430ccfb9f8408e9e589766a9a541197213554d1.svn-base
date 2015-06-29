package com.scie.wechat.reply;

import java.util.ArrayList;
import java.util.List;

import com.scie.wechat.message.resp.Article;
import com.scie.wechat.message.resp.NewsMessage;
import com.scie.wechat.util.MessageUtil;

public class NewStudentReply {

	public NewsMessage news(String fromUserName, String toUserName,
			long createTime,int type) {
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setFromUserName(toUserName);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setCreateTime(createTime);
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		Article a = new Article();
		List<Article> articleList = new ArrayList<Article>();
		if (type==1) {
			a.setTitle("新生入学须知");
			a.setDescription("通信与信息工程学院2014级新生入学须知");
			a.setUrl(setUrl(1));
			a.setPicUrl("http://www.cqupt.edu.cn/xxgk/images//1198543330.jpg");
			articleList.add(a);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
		} else if(type==2) {
			a.setTitle("学院介绍");
			a.setDescription("欢迎来到通信与信息工程学院");
			a.setUrl(setUrl(2));
			a.setPicUrl("http://pic.newssc.org/0/12/59/42/12594250_627519.jpg");
			articleList.add(a);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
		}

		return newsMessage;
	}
	
	private String setUrl(int urlType) {
		String str = null;
		if (urlType==1) {
			str="http://scietx.duapp.com/newStudent.html";
		} else if (urlType==2) {
			str="http://scietx.duapp.com/introduction.html";
		}
		return str;
	}
	
}




