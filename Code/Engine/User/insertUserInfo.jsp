
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
int count;
String name=request.getParameter("name");
int age =Integer.parseInt(request.getParameter("age"));
String gender=request.getParameter("gender");
String device=request.getParameter("device");
String country=request.getParameter("country");
device="\'"+device+"\'";
country="\'"+country+"\'";
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost/maddy","root","");
PreparedStatement st=con.prepareStatement("insert into user(name,age,gender,device,country) values(?,?,?,?,?)");
st.setString(1,name);
st.setInt(2,age);
st.setString(3,gender);
st.setString(4,device);
st.setString(5,country);
count=st.executeUpdate();
writer.println("  "+count+" row inserted");
}
catch(Exception e)
{
	writer.println(e);
}
%>
</body>
</html>