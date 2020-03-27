<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.ServletException" %>
<%@ page import="javax.servlet.annotation.WebServlet" %>
<%@ page import="javax.servlet.http.HttpServlet" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Publisher add</title>
    </head>
    <body>
<%
PrintWriter writer=response.getWriter();
try
{
int count;
int id=Integer.parseInt(request.getParameter("wpid"));
String pubdomain=request.getParameter("publisherdomain");
int category=Integer.parseInt(request.getParameter("category"));
String pageurl=request.getParameter("pageurl");
int noofad=Integer.parseInt(request.getParameter("noofad"));
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost/projdb","root","");
PreparedStatement st=con.prepareStatement("insert into webpage values(?,?,?,?,?)");
st.setInt(1,id);
st.setString(2,pubdomain);
st.setInt(3,category);
st.setString(4,pageurl);
st.setInt(5,noofad);
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
