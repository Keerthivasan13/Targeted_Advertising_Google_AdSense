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
<title>Details</title>
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
        document.getElementById("demo").innerHTML =values;
    });
    $("body").on("click", ".remove", function () {
        $(this).closest("div").remove();
    });
});
function GetTextBox(value) {
    return '<input name = "DynamicTextBox" type="text" value = "' + value + '" />&nbsp;' +
            '<input type="button" value="Remove" class="remove" />'
}
</script>
</head>
<body>
<form action="insertadvinfo.jsp">  
    
<pre>       *****     Enter advertiser details      *****       </pre>
Company E-mail Id : <input type="text" name="email" required/><br/><br/>
Password: <input type="password" name="password" required/><br/><br/>
Product Name : <input type="text" name="product" required/><br/><br/>
Keywords : 
 <input id="btnAdd" type="button" value="Add" />
 <div id="TextBoxContainer">
   
</div>
<br/><br/>
Primary Category : <select name="pcat">
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
</select><br/><br/>
Plan :<select name="plan">
    <option value="normal" id="1">Normal</option>
    <option value="premium" id="2">Premium</option>
</select><br><br>
Cost:<input type="number" name="cost"/>(In Rupees)<br/><br/>
<input id="btnnGet" type="submit" value="register"/> 
  
<input id="btnGet" type="button" value="check keywords" /> <!-- our reference  -->
<p id="demo"></p> <!-- our reference  -->
</form>
</body>
</html>