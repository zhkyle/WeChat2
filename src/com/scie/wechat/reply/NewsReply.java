package com.scie.wechat.reply;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.scie.wechat.custom.NewsCQUPT;

public class NewsReply {
	public String qinqinNews() {
		String text = "";
		int index = 1;
//		ArrayList<String> newsAl = new ArrayList<String>();
		NewsCQUPT ncqupt = new NewsCQUPT();
		Map<String, String> newsMap = ncqupt.getNews();
		Set<Entry<String, String>> set = newsMap.entrySet();
		Iterator<Entry<String, String>> i = set.iterator();
		while (i.hasNext()) {
			Map.Entry<String, String> entry1 = (Map.Entry<String, String>) i
					.next();
			text = text + "<a href=\"" + entry1.getValue() + "\">" + index + ":  " + entry1.getKey() + "</a>" + "\n";
			index++;
		}
		index = 0;
		newsMap.clear();
		return text;
	}
}
