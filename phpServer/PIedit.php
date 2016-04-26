<?php
    /**
     * 个人信息修改页的后台php代码
     * 需要接收的参数为用户名和修改后的个人信息
     * 修改成功返回1，失败返回0
     * **/
    include 'conn.php';
    //接收客户端发来的各种信息
    $userName=str_replace(" ","",$_POST['userName']);
    $userName=mb_convert_encoding($userName,"UTF-8");
    $passWord=str_replace(" ","",$_POST['passWord']);
    $phone=str_replace(" ","",$_POST['phone']);
    //$email=str_replace(" ","",$_POST['email']);
    $address=str_replace(" ","",$_POST['address']);
    //$stunum=str_replace(" ","",$_POST['stunum']);
    $sex=str_replace(" ","",$_POST['sex']);
    /*$userName="user1";
    $passWord="111";
    $phone="15600000000";
    $email="abc@126.com";*/
    $sql="update users set passWord='$passWord',phone='$phone',address='$address',sex='$sex' where userName='$userName'";
    $query=mysqli_query($cn,$sql);
    //若修改成功，返回1
    if ($query){ 
        echo "1";
    }
    //否则返回0
    else {
        echo "0";
    }
    mysqli_close($cn);

?>