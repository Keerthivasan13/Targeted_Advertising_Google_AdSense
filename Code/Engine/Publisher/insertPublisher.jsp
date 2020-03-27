
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.ServletException" %>
<%@ page import="javax.servlet.annotation.WebServlet" %>
<%@ page import="javax.servlet.http.HttpServlet" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert</title>
</head>
<body>
<%
PrintWriter writer=response.getWriter();
try
{
int count=0;
String domainname=request.getParameter("domainname");
String password=request.getParameter("password");
int age=Integer.parseInt(request.getParameter("age"));
String adminmail=request.getParameter("adminmail");
String websitetype=request.getParameter("websitetype");
int accno=Integer.parseInt(request.getParameter("accno"));
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost/projdb","root","");



PreparedStatement st=con.prepareStatement("insert into publisher(Domain,Website_Age,Admin_Email_Id,Admin_Password,Account_No) values(?,?,?,?,?)");
st.setString(1,domainname);
//st.setString(2,websitetype);
st.setInt(2,age);
st.setString(3,adminmail);
st.setString(4,password);
st.setInt(5,accno);
count=st.executeUpdate();
writer.println("  "+count+" row inserted");

}
catch(Exception e)
{
	writer.println("Exception is : "+e);
}
%>
</body>
</html>