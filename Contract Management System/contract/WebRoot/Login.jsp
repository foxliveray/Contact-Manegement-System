<%@ page language="java" pageEncoding="UTF-8"%>
<!-- page import -->
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="LoginlLayout">
    <meta name="author" content="Bootstrap">

    <title>Contact Management System - Login</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
	
	<!-- header&footer styles for this template -->
	<link href="css/style.css" rel="stylesheet" media="screen" type="text/css">
	
	<script type="text/javascript">  
 	// Make the page as the parent window display
 	if(top!=self){
 		top.location.href=self.location.href;
 		}  
  	</script>
	
  </head>

  <body>
    
    <div class="header">
		<h1>
			<img src="images/logo_title.png" alt="Contract Management System" />
		</h1>
	</div>
  
    <div class="container">

      <form class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

    </div> <!-- /container -->
	
	<div class="footer">
		<ul>
			<li>
				<a target="_blank" href="#">Contract Management System</a>
			</li>
			<li>
				｜
			</li>
			<li>
				<a target="_blank" href="#">Help</a>
			</li>
		</ul>
		<p>
		    Copyright&nbsp;&copy;&nbsp;Ruanko COE&nbsp;
			<a href="http://www.ruanko.com" title="wwww.ruanko.com"
				target="_blank"><strong>www.ruanko.com</strong> </a>&nbsp;Copyright
			Reserved
		</p>
	</div>
	
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>
	
</html>
