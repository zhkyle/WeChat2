package com.scie.wechat.test;

import org.junit.Test;

import com.scie.wechat.button.Button;
import com.scie.wechat.button.CommonButton;
import com.scie.wechat.button.ComplexButton;
import com.scie.wechat.button.Menu;
import com.scie.wechat.util.AccessTokenUtil;
import com.scie.wechat.util.WeixinUtil;

/**
 * 菜单管理器类
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class MenuManager {
//	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) {
		
		String at = AccessTokenUtil.getAccessToken().get("access_token");
		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at);

			// 判断菜单创建结果
			if (0 == result)
//				log.info("菜单创建成功！");
				System.out.println("菜单创建成功！");
			else
//				log.info("菜单创建失败，错误码：" + result);
				System.out.println("菜单创建失败，错误码：" + result);
		}
	}
	

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	@Test
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("学院介绍");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("学院新闻");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn21 = new CommonButton();
		btn21.setName("招新报名");
		btn21.setType("click");
		btn21.setKey("21");

		CommonButton btn22 = new CommonButton();
		btn22.setName("活动报名");
		btn22.setType("click");
		btn22.setKey("22");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("通信魅力");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn12});

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("通信之家");
		mainBtn2.setSub_button(new CommonButton[] { btn21, btn22 });
		
		CommonButton mainBtn3 = new CommonButton();
		mainBtn3.setName("往期回顾");
		mainBtn3.setType("view");
		mainBtn3.setUrl("http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MzA4NTA0Mzg5Ng==#wechat_webview_type=1&wechat_redirect");

//		ComplexButton mainBtn3 = new ComplexButton();
//		mainBtn3.setName("往期回顾");
//		mainBtn2.setSub_button(new CommonButton[] {});

		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });
		return menu;
	}
}
