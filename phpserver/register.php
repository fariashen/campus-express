<?php
    /**
     * 注册界面的后台php处理页面
     * 需要接收的参数为用户名userName和密码passWord
     * 服务器错误返回1，用户名重复返回2，注册成功返回3
     * **/
    include 'conn.php';
    $userName=str_replace(" ","",$_POST['userName']);//接收客户端发来的userName；
    $passWord=str_replace(" ","",$_POST['passWord']);//接收客户端发来的passWord；
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
        //若结果为数组，则用户表中已经存在此用户名，则返回2
	   echo "2";	 
    }
    else {
	   $sql1="insert into users (userName,passWord) values ('$userName','$passWord')";
	   $query1=mysqli_query($cn,$sql1);
	   if (!$query1) 
	       //服务器错误，注册失败
	       echo "1";
       else 
           //否则返回3(注册成功)
           echo "3";
	 }
    mysqli_close($cn);

?>
