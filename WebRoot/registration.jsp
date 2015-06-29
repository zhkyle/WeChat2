<%@ page language="java" import="java.util.*,com.scie.wechat.bean.*,com.scie.wechat.util.HibernateSessionFactory,org.hibernate.Session" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>报名信息查看</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    报名情况 <br>
    <center>
    	<table border="1">
    		<tr>
    			<td width="50">用户id</td>
    			<td width="80">姓名</td>
    			<td width="100">学号</td>
    			<td width="80">班级</td>
    			<td width="150">手机</td>
    			<td>部门</td>    			
    		</tr>
    <%
    Session se = HibernateSessionFactory.getSession();
    String hsql = "from Student";
    List<Student> students = se.createQuery(hsql).list();
    	for(int i=0; i<students.size(); i++) {
    		Student ub = students.get(i);
    		out.println("<tr>");
    		out.println("<td>"+ub.getSid()+"</td>");
    		out.println("<td>"+ub.getSname()+"</td>");
    		out.println("<td>"+ub.getSnum()+"</td>");
    		out.println("<td>"+ub.getSclass()+"</td>");
    		out.println("<td>"+ub.getSphone()+"</td>");
    		out.println("<td>"+ub.getSdep()+"</td>");
    		out.println("</tr>");
    	}
    	se.flush();
    	HibernateSessionFactory.closeSession();
    
     %>
    
    </table>
    
    </center>
  </body>
</html>
