<?php
 $dbhost = "localhost"; 
 $dbuser = "root"; //�ҵ��û��� 
 $dbpw = ""; //�ҵ����� 
 $dbname = "test_easyCourier"; //�ҵ�mysql���� 
 $cn = mysqli_connect($dbhost,$dbuser,$dbpw) or die(mysqli_error());
 @mysqli_select_db($cn,$dbname)or die(mysqli_error());
 mysqli_query($cn,"set names gbk");
 ?>