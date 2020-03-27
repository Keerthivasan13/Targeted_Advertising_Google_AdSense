<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log in</title>
    </head>
    <body>
        <p>Advertiser log in & register</p>
        <form name="advlogin" action="advlogin.jsp">
            Email:<input type="text" name="advemail"><br><br>
            Password:<input type="text" name="advpassword"><br><br>  
            <input type="submit" value="login"><br><br>
        </form>
        <form name="advreg" action="getadvinfo.jsp">
            <input type="submit" value="Register"><br><br>
        </form>
        <p>Publisher log in & register</p>
        <form name="publogin" action="publogin.jsp">
            Email:<input type="text" name="pubemail"><br><br>
            Password:<input type="text" name="pubpassword"><br><br>
            <input type="submit" value="Login"><br><br>
        </form>
        <form name="pubreg" action="getpubinfo.jsp">
            <input type="submit" value="Register"><br><br>
        </form>
        </body>
</html>
