package com.scie.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scie.wechat.recruitment.RecruitmentStudent;

public class addStudent extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");  
		response.setCharacterEncoding("UTF-8");
		String snum = request.getParameter("snum");
		String sname = request.getParameter("sname");
		String sclass = request.getParameter("sclass");
		String sphone = request.getParameter("sphone");
		String sdep = request.getParameter("sdep");
		if(new RecruitmentStudent().recruit(snum, sname, sclass, sphone, sdep)) {
			response.sendRedirect("suc.jsp");
		}else {
			PrintWriter out = response.getWriter();
			out.print("服务正忙，请重试");
			out.flush();
			out.close();
		}
	}

}
