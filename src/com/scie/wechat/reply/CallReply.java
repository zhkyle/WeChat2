package com.scie.wechat.reply;

import java.util.ArrayList;
import java.util.List;

import com.scie.wechat.message.resp.Article;
import com.scie.wechat.message.resp.NewsMessage;
import com.scie.wechat.util.MessageUtil;

public class CallReply {

	public NewsMessage news(String fromUserName, String toUserName,
			long createTime) {
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setFromUserName(toUserName);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setCreateTime(createTime);
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		Article a = new Article();
		List<Article> articleList = new ArrayList<Article>();
		a.setTitle("免费电话");
		a.setDescription("网络电话，初次使用赠送5元话费");
		a.setUrl(setUrl(fromUserName, toUserName));
		articleList.add(a);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);

		return newsMessage;
	}
	
	private String setUrl(String fromUserName, String toUserName) {
		String str = null;
		str="http://3g.inbai.com/interface/signin/signin.jsp?agentid=948045&token=handcjlu&fromuser="
		+ fromUserName + "&wecha_id=" + toUserName + "#wechat_webview_type=1";
		return str;
	}
	
	
	
	
	
}




