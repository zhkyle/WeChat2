package com.scie.wechat.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scie.wechat.service.CoreService;
import com.scie.wechat.util.SignUtil;

public class MyScie extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private static String token = "scie";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

        // 获取请求参数
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostring = req.getParameter("echostr");
       
        try {
			if (SignUtil.checkSignature(signature, timestamp, nonce)) { //将加密的结果与请求参数中的signature比对，如果相同，原样返回echostr参数内容
			    OutputStream os = resp.getOutputStream();
			    BufferedWriter resBr = new BufferedWriter(new OutputStreamWriter(os));
			    resBr.write(echostring);
			    resBr.flush();
			    resBr.close();
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
		req.setCharacterEncoding("UTF-8");  
		resp.setCharacterEncoding("UTF-8");  
  
        // 调用核心业务类接收消息、处理消息  
        String respMessage = CoreService.processRequest(req);  
          
        // 响应消息  
        PrintWriter out = resp.getWriter();  
        out.print(respMessage);  
        out.close();  
//		super.doPost(req, resp);
	}
}
