<?php
	//header("Content-type:text/html;charset=utf-8"); 
	include 'conn.php';
	$providerName="╡лн╟Ёг";
	$providerName=mb_convert_encoding($providerName,"UTF-8","GBK");
	$result=mysqli_fetch_array(mysqli_query($cn,"select balances from users where userName='$providerName'"));
    echo $result['0'];
	echo "hello";