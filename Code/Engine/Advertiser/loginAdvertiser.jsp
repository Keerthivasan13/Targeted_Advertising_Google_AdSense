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
        <title>Advertiser log in</title>
    </head>
    <body>
<%
int advid=0;
PrintWriter writer=response.getWriter();
int count=0;
try{
String email=request.getParameter("advemail");
String password=request.getParameter("advpassword");
Class.forName("com.mysql.jdbc.Driver");
Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/projdb","root","");
Statement st=conn.createStatement();
ResultSet rs=st.executeQuery("select * from advertiser");
while(rs.next())
{
    if(rs.getString("Subscriber_Mail_Id").equals(email))
    {    
        count=1;
        if(rs.getString("Subscriber_Password").equals(password))
        {
            writer.println("Log in success<br>Welcome "+email);
            advid=rs.getInt("id");
            request.getSession();
            
            session.setAttribute("id",advid);
            out.println("<form action=\"advertiseradd.jsp\"><input type=\"submit\" value=\"Add Advertisement\" ></form>");
            out.println("<form action=\"advertiserdel.jsp\"><input type=\"submit\" value=\"Delete Advertisement \" ></form>");
        }
        else 
            out.println("Incorrect password");
    }
}
if(count==0)
    out.println("No such email registered . . . ");
}
catch(Exception e){ writer.println(e);}
%>
<input type="text" name="advid" id="adid">
<script>
    var varid=<% out.print(advid);%>
    document.getElementById('adid').value=varid;
    document.write(varid);
</script>

    </body>
</html>