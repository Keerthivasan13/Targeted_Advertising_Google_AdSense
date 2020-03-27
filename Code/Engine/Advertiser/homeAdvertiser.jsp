<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
            
            <form name="advlogin" action="advlogin.jsp" method="post" class="form" role="form">
                <div class="well"><h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Advertiser</h2></div>
                <div class="row">
                
                <div class="col-xs-6 col-md-12">
                    <input class="form-control" name="advemail" placeholder="Email id" type="text"
                        required autofocus />
                    <br />
                </div>
                <div class="col-xs-6 col-md-12">
                    <input class="form-control" name="advpassword" placeholder="Password" type="password" required />
                    <br />
                </div>
            </div>
           
            <br />
            <button class="btn btn-lg btn-primary btn-block" type="submit" value="Login">
                Log In</button>
            <br />
             </form>
            <label> Dont have an account? Register here </label>
            <form name="advreg" action="getadvinfo1.jsp"  method="post" class="form" role="form">
                <button class="btn btn-lg btn-primary btn-block" type="submit" value="Register">Register</button><br><br>
            </form>
           
        </div>
    </div>
</div>
</body>
</html>