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
        <title>Publisher log in</title>
    </head>
    <body>
<%
int count=0;
PrintWriter writer=response.getWriter();
try{
String pubemail=request.getParameter("pubemail");
String pubpassword=request.getParameter("pubpassword");
Class.forName("com.mysql.jdbc.Driver");
Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/projdb","root","");
Statement st=conn.createStatement();
ResultSet rs=st.executeQuery("select * from publisher");
while(rs.next())
{
    if(rs.getString("Admin_Email_Id").equals(pubemail))
    {   
        count=1;
        if(rs.getString("Admin_Password").equals(pubpassword))
        {
            writer.println("Log in success<br>Welcome "+ pubemail);
            out.println("<form action=\"publisheradd.jsp\"><input type=\"submit\" value=\"Add Webpage\" ></form>");
            out.println("<form action=\"publisherdel.jsp\"><input type=\"submit\" value=\"Delete Webpage \" ></form>");
        }
        else 
            writer.println("Incorrect password");
    }
}
if(count==0)
    writer.println("No such domain name registered");
}
catch(Exception e){ writer.println();}

%>
    </body>
</html>
