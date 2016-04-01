<?php
 $dbhost = "119.29.4.159"; 
 $dbuser = "root"; //我的用户名 
 $dbpw = "cheng1006mysql"; //我的密码 
 $dbname = "easycourier"; //我的mysql库名 
 $cn = mysqli_connect($dbhost,$dbuser,$dbpw) or die(mysqli_error());
 @mysqli_select_db($cn,$dbname)or die(mysqli_error());
 mysqli_query($cn,"set names utf-8");
 ?>