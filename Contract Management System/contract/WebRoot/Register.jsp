<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>

<html lang="en" class="no-js">



    <head>



        <meta charset="utf-8">

        <title>Register</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <meta name="description" content="">

        <meta name="author" content="">



        <!-- CSS -->

        <link rel="stylesheet" href="assets/css/reset.css">

        <link rel="stylesheet" href="assets/css/supersized.css">

        <link rel="stylesheet" href="assets/css/style.css">



        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->

        <!--[if lt IE 9]>

            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>

        <![endif]-->
		<script type="text/javascript">
        function check(){
          var name = document.getElementById('username');
          var password = document.getElementById('password');
          var password2 = document.getElementById('password2');
          if (name.value == ""){
            alert("用户名不能为空！");
            name.focus();
            return false;
          }
          if (password.value == ""){
            alert("密码不能为空！");
            password.focus();
            return false;
          }
          if (password2.value != password.value){
            alert("重复输入的密码需要保持一致！");
            password2.focus();
            return false;
          }
        }
    </script>


    </head>



    <body>



        <div class="page-container">

            <h1>Register</h1>

            <form action="Register" method="post">

                <input type="text" name="username" class="username" id="username" placeholder="Username">

                <input type="password" name="password" class="password" id="password" placeholder="Password">

                <input type="password" name="password2" class="password" id="password2" placeholder="Repeat password">

                <button type="submit" onclick="check()">Submit</button>

                <div class="error"><span>+</span></div>

            </form>
			<a href="Login.jsp" style="color:#fff; text-decoration:none "><button type="submit">Back to login</button></a>

            <div class="connect">

                <p>Contract Management System</p>
                <!--
                <p>

                    <a class="facebook" href=""></a>

                    <a class="twitter" href=""></a>

                </p>
                -->



            </div>

            <div class="info">
            <p>Copyright © 疯狂的拖延症  Copyright Reserved</p>
        </div>





        <!-- Javascript -->

        <script src="assets/js/jquery-1.8.2.min.js"></script>

        <script src="assets/js/supersized.3.2.7.min.js"></script>

        <script src="assets/js/supersized-init.js"></script>

        <script src="assets/js/scripts.js"></script>



    </body>



</html>
