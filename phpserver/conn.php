<?php
 //数据库主机地址
 $dbhost = "119.29.4.159";
 
 //数据库用户名
 $dbuser = "root";
  
 //数据库密码 
 $dbpw = "cheng1006mysql"; 
 
 //数据库名 
 $dbname = "easycourier";
 $cn = mysqli_connect($dbhost,$dbuser,$dbpw) or die(mysqli_error());
 @mysqli_select_db($cn,$dbname)or die(mysqli_error());
 mysqli_query($cn,"set names utf-8");
