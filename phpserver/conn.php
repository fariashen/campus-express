<?php
 //���ݿ�������ַ
 $dbhost = "119.29.4.159";
 
 //���ݿ��û���
 $dbuser = "root";
  
 //���ݿ����� 
 $dbpw = "cheng1006mysql"; 
 
 //���ݿ��� 
 $dbname = "easycourier";
 $cn = mysqli_connect($dbhost,$dbuser,$dbpw) or die(mysqli_error());
 @mysqli_select_db($cn,$dbname)or die(mysqli_error());
 mysqli_query($cn,"set names utf-8");
