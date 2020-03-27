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
<title>Publisher Registration</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="newcss.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
    <div class="container">
	<div class="row">
        <div class="col-xs-12 col-sm-12 col-md-4 well well-sm">
<form name="pubregister" method="post" class="form" role="form" action="insertpubinfo.jsp">  
    <div class="well"><h2><center>Publisher Details</center></h2></div>   
<div class="row">
     <div class="col-xs-6 col-md-12">
 <input class="form-control" type="text" name="domainname" placeholder="Enter Domain Name" required autofocus/><br/>
     </div>
     <div class="col-xs-6 col-md-12"> 
 <input class="form-control" type="number" name="age" placeholder="Website Age" required autofocus/><br/>
     </div>
     <div class="col-xs-6 col-md-12">
 <input class="form-control" type="text" name="adminmail" placeholder="Enter Admin email" required autofocus/><br/>
     </div>
     <div class="col-xs-6 col-md-12">
<input class="form-control" type="password" placeholder="Enter password" name="password" required autofocus/><br/>
     </div>
     <div class="col-xs-6 col-md-12">
 <input class="form-control" type="number" name="accno" placeholder="Enter Account number" required autofocus/><br/>
     </div>
      <div class="col-xs-6 col-md-12">
     <label> Primary Category </label> <select class="form-control" name="pcat">
<%
    Class.forName("com.mysql.jdbc.Driver");
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost/projdb","root","");
    Statement st=con.createStatement();
    ResultSet rs=st.executeQuery("select Distinct(Primary_Category) from pagekeyword order by Primary_Category");
    while(rs.next())
    {
        out.print("<option name='"+String.valueOf(rs.getString("Primary_category"))+"'>"+rs.getString("Primary_category")+"</option>");
    }
 %>
</select><br/>
 </div>
</div>
 <button class="btn btn-lg btn-primary btn-block" type="submit" value="register"/>Register</button>
</form>  
        </div>
        </div>
    </div>
</body>
</html>