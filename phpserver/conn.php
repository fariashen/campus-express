<?php
 $dbhost = "localhost"; 
 $dbuser = "root"; //我的用户名 
 $dbpw = ""; //我的密码 
 $dbname = "test_easyCourier"; //我的mysql库名 
 $cn = mysqli_connect($dbhost,$dbuser,$dbpw) or die(mysqli_error());
 @mysqli_select_db($cn,$dbname)or die(mysqli_error());
 mysqli_query($cn,"set names gbk");
 ?>