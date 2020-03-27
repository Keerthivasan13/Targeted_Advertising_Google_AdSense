
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
String email=request.getParameter("email");
String product=request.getParameter("product");
String keyword=request.getParameter("keyword");
String pcat=request.getParameter("pcat");
String plan=request.getParameter("plan");
int price=Integer.parseInt(request.getParameter("price"));
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost/projdb","root","");
PreparedStatement st=con.prepareStatement("insert into advertisementinfo(email,product,keyword,pcat,plan,price) values(?,?,?,?,?,?)");
st.setString(1,email);
st.setString(2,product);
st.setString(3,keyword);
st.setString(4,pcat);
st.setString(5,plan);
st.setInt(6,price);
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