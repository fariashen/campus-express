<?php
 $dbhost = "119.29.4.159"; 
 $dbuser = "root"; //�ҵ��û��� 
 $dbpw = "cheng1006mysql"; //�ҵ����� 
 $dbname = "easycourier"; //�ҵ�mysql���� 
 $cn = mysqli_connect($dbhost,$dbuser,$dbpw) or die(mysqli_error());
 @mysqli_select_db($cn,$dbname)or die(mysqli_error());
 mysqli_query($cn,"set names utf-8");
 ?>