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
    <title>Advertiser Registration</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="newcss.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
    
    
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-4 well well-sm">
<form  name="advregister" action="insertadvinfo.jsp" method="post" class="form" role="form">  
    
<div class="well"><h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Advertiser Details</h2></div>
 <div class="row">
      <div class="col-xs-6 col-md-12">
                    <input class="form-control" name="email" placeholder="Subscriber Email id" type="text"
                        required autofocus />
                </div>
     <div class="col-xs-6 col-md-12">
                    <input class="form-control" name="compname" placeholder="Company Name" type="text"
                        required autofocus />
                </div>
     <div class="col-xs-6 col-md-12">
                    <input class="form-control" name="ceo" placeholder="CEO Name" type="text"
                        required autofocus />
                </div>
     <div class="col-xs-6 col-md-12">
                    <input class="form-control" name="compage" placeholder="Company Age" type="number"
                        required autofocus />
                </div>
     <div class="col-xs-6 col-md-12">
                    <input class="form-control" name="location" placeholder="Company Location" type="text"
                        required autofocus />
                </div>
 <div class="col-xs-6 col-md-12">
                    <input class="form-control" name="password" placeholder="Password" type="password"
                        required autofocus />
                </div>
 <div class="col-xs-6 col-md-12">
                    <input class="form-control" name="product" placeholder="Product Name" type="text"
                        required autofocus />
                </div>
    
<br/><br/>
<br />
 <div class="col-xs-6 col-md-12">
<label> Plan </label> <select class="form-control" name="plan">
    <option value="1" id="1">Normal</option>
    <option value="2" id="2">Premium</option>
</select><br>
 </div>
 <div class="col-xs-6 col-md-12">
<input class="form-control" type="number" name="cost" placeholder="Enter cost" required autofocus/><br/><br/>
 </div>
<button class="btn btn-lg btn-primary btn-block" id="btnnGet" type="submit" value="register"/>Register </button>
  
</form>
            </div>
        </div>
    </div>
</body>
</html>