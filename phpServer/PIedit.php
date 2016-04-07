<?php
    include 'conn.php';
    $userName=str_replace(" ","",$_POST['userName']);//接收客户端发来的userName；
    $passWord=str_replace(" ","",$_POST['passWord']);//接收客户端发来的passWord；
    $phone=str_replace(" ","",$_POST['phone']);//接收客户端发来的phone；
    $email=str_replace(" ","",$_POST['email']);//接收客户端发来的email；
    $address=str_replace(" ","",$_POST['address']);
    $stunum=str_replace(" ","",$_POST['stunum']);
    $sex=str_replace(" ","",$_POST['sex']);
    //$userName="user1";
    //$passWord="111";
    //$phone="15600000000";
    //$email="abc@126.com";
    $sql="update users set passWord='$passWord',phone='$phone',email='$email',address='$address',stunum='$stunum',sex='$sex' where userName='$userName'";
    $query=mysqli_query($cn,$sql);
    if ($query) echo "success";//若插入失败，返回失败原因(注册失败)
    else return false;//否则返回true(注册成功)	
    mysqli_close($cn);

?>