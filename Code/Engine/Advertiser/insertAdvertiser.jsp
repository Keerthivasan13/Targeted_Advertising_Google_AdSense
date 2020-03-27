
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
<body><%
PrintWriter writer=response.getWriter();
try
{
int count=0;
String submail=request.getParameter("email");
String compname=request.getParameter("compname");
String ceo=request.getParameter("ceo");
int compage=Integer.parseInt(request.getParameter("compage"));
String location=request.getParameter("location");
String password=request.getParameter("password");
int plan=Integer.parseInt(request.getParameter("plan"));
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost/projdb","root","");


PreparedStatement st=con.prepareStatement("insert into advertiser(Company_Name,CEO_Name,Company_Age,Company_Location,Subscriber_Mail_Id,Subscriber_Password,Premium_Membership) values(?,?,?,?,?,?,?)");
st.setString(1,compname);
st.setString(2,ceo);
st.setInt(3,compage);
st.setString(4,location);
st.setString(5,submail);
st.setString(6,password);
st.setInt(7,plan);
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