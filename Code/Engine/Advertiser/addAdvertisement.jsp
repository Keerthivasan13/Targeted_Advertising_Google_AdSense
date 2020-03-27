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
        <title>DashBoard-Advertiser</title>
           
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="newcss.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript">
 $(function () {
    $("#btnAdd").bind("click", function () {
        var div = $("<div />");
        div.html(GetTextBox(""));
        $("#TextBoxContainer").append(div);
    });
    $("#btnGet").bind("click", function () {
        var values = "";
        $("input[name=DynamicTextBox]").each(function () {
            values += $(this).val() + "+";
        });
        alert(values);
        document.getElementById("key").value =values;
        return values;
    });
    $("body").on("click", ".remove", function () {
        $(this).closest("div").remove();
    });
});
function GetTextBox(value) {
    return '<div class="col-xs-6 col-md-10">'+'<input name = "DynamicTextBox" class="form-control" placeholder="enter a keyword" type="text" value = "' + value + '" />&nbsp;'+
            '<button type="button" value="x" class="btn btn-primary btn-md btn-danger remove" /> Remove </button>'+'</div>'+'<br />'
}
</script>
    </head>
    <body>
        <div class="jumbotron"> <center><h1>Add Advertisement</h1></center></div>
        <div class="container">
        <div class="row">
           
            <div class="col-xs-12 col-sm-12 col-md-4 well well-sm" style="margin-left: 30%;">
                 
<form  name="advertisementadd" action="insertadvert.jsp" method="post" class="form" role="form">  
    
<div class="well"><h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Add advertisement</h2></div>
 <div class="row" style="">
      <div class="col-xs-6 col-md-12">
                    <input class="form-control" name="adname" placeholder="Advertisement Name" type="text"
                        required autofocus />
                </div>
    
     <div class="col-xs-6 col-md-12">
                    <input class="form-control" name="location" placeholder="Location" type="text"
                        required autofocus />
                </div>
     <div class="col-xs-6 col-md-12">
                    <input class="form-control" name="cpm" placeholder="Cost per millenium" type="number"
                        required autofocus />
                </div>
     <div class="col-xs-6 col-md-12">
                    <input class="form-control" name="cpc" placeholder="Cost per  clicks" type="number"
                        required autofocus />
                </div>
          <div class="col-xs-6 col-md-12">
                    <input class="form-control" id="key" name="key" type="text" placeholder="Complete Keyword"
                        autofocus />
                </div>
 
    
      <div class="col-xs-6 col-md-12">
     <label>Keywords : </label> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
     <button id="btnAdd" type="button" class="btn btn-primary btn-sm" value="Add" />Add keyword </button>

 <div id="TextBoxContainer">
   
</div>
      </div>
<br/><br/>
<br />
 <div class="col-xs-6 col-md-12">
     <label> Primary Category </label> <select class="form-control" name="pcat">
<%
    Class.forName("com.mysql.jdbc.Driver");
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost/projdb","root","");
 
    Statement st=con.createStatement();
    ResultSet rs=st.executeQuery("select id,name from adcategory where Parent_Id=0");
    while(rs.next())
    {
        out.print("<option name='"+String.valueOf(rs.getInt("id"))+"'>"+rs.getString("name")+"</option>");
    }
 %>
</select><br/>
 </div>

<button class="btn btn-md btn-primary btn-block" id="btnGet" type="button" value="register"/>Finalize keyword </button>
 <button class="btn btn-lg btn-primary btn-block"  type="submit" value="register"/>Submit </button>
<!--<input id="btnGet" type="button" value="check keywords" />-->
<!--<p id="demo"></p>-->
</form>
            </div>
            </div>
    </div>
        
     
    </body>
</html>
