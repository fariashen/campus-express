<?php
    include 'conn.php';
    //���տͻ��˷����ĸ�����Ϣ
    $userName=str_replace(" ","",$_POST['userName']);
    $passWord=str_replace(" ","",$_POST['passWord']);
    $phone=str_replace(" ","",$_POST['phone']);
    $email=str_replace(" ","",$_POST['email']);
    $address=str_replace(" ","",$_POST['address']);
    $stunum=str_replace(" ","",$_POST['stunum']);
    $sex=str_replace(" ","",$_POST['sex']);
    /*$userName="user1";
    $passWord="111";
    $phone="15600000000";
    $email="abc@126.com";*/
    $sql="update users set passWord='$passWord',phone='$phone',email='$email',address='$address',stunum='$stunum',sex='$sex' where userName='$userName'";
    $query=mysqli_query($cn,$sql);
    //���޸ĳɹ�������success
    if ($query) 
        echo "success";
    //���򷵻�false
    else 
        return false;
    mysqli_close($cn);

?>