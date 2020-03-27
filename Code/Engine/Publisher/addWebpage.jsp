<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Get Webpage details</title>
    </head>
    <body>
        <h2>Enter webpage details</h2>
        <form action="publisheradd1.jsp">
            <input type="text" name="wpid" placeholder="Webpage id"><br>
            <input type="text" name="publisherdomain" placeholder="Publisher domain"><br>            
            <input type="text" name="category" placeholder="category"><br>
            <input type="text" name="pageurl" placeholder="Page URL"><br>
            <input type="text" name="noofad" placeholder="No of ads"><br>
            <input type="submit" name="Add webpage">
        </form>
    </body>
</html>
