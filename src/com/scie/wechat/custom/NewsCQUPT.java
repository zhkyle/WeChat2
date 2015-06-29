package com.scie.wechat.custom;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsCQUPT {
	
	public Map<String, String> getNews() {
		Map<String, String> newsMap = new HashMap<String, String>();
		
		Document doc;
		try {
			doc = Jsoup.connect(
					"http://xwzx.cqupt.edu.cn/xwzx/news_search.php?string=%CD%A8%D0%C5&type=key_word").get();
			Elements eNews = doc.select("[width=540]");
			Iterator<Element> it = eNews.iterator();
			int i = 0;
			while (it.hasNext() && i<5) {
				Element n = it.next();
				Elements a = n.getElementsByTag("a");
				String url = a.attr("href");
				url = "http://xwzx.cqupt.edu.cn/xwzx/" + url;
				String title = n.text();
				newsMap.put(title, url);
				i++;
			}
			i = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return newsMap;
	}

}
