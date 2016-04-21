<?php
    include 'conn.php';
    $userName=mb_convert_encoding(str_replace(" ","",$_POST['userName']),'utf8','gbk');//接收客户端发来的userName；
    $passWord=mb_convert_encoding(str_replace(" ","",$_POST['passWord']),'UTF-8','GBK');//接收客户端发来的passWord；
    //$phone=str_replace(" ","",$_POST['phone']);//接收客户端发来的phone；
    //$email=str_replace(" ","",$_POST['email']);//接收客户端发来的email；
    //$userName="user4";
    //$passWord="123456";
    //$phone="15633333333";
    //$email="abc@126.com";
    $sql="select * from users where userName='$userName'";
    $query=mysqli_query($cn,$sql);
    $result=mysqli_fetch_array($query);
    if(is_array($result)){
	echo "2";//若结果为数组，则用户表中已经存在此用户名，则返回2		 
    }
    else {
	   $sql1="insert into users (userName,passWord) values ('$userName','$passWord')";
	   $query1=mysqli_query($cn,$sql1);
	   if (!$query1) echo "1";//若插入失败，返回失败原因(注册失败)
       else echo "3";//否则返回3(注册成功)
	 }
    mysqli_close($cn);

?>
