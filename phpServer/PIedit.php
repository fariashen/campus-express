<?php
    include 'conn.php';
    $userName=str_replace(" ","",$_POST['userName']);//���տͻ��˷�����userName��
    $passWord=str_replace(" ","",$_POST['passWord']);//���տͻ��˷�����passWord��
    $phone=str_replace(" ","",$_POST['phone']);//���տͻ��˷�����phone��
    $email=str_replace(" ","",$_POST['email']);//���տͻ��˷�����email��
    $address=str_replace(" ","",$_POST['address']);
    $stunum=str_replace(" ","",$_POST['stunum']);
    $sex=str_replace(" ","",$_POST['sex']);
    //$userName="user1";
    //$passWord="111";
    //$phone="15600000000";
    //$email="abc@126.com";
    $sql="update users set passWord='$passWord',phone='$phone',email='$email',address='$address',stunum='$stunum',sex='$sex' where userName='$userName'";
    $query=mysqli_query($cn,$sql);
    if ($query) echo "success";//������ʧ�ܣ�����ʧ��ԭ��(ע��ʧ��)
    else return false;//���򷵻�true(ע��ɹ�)	
    mysqli_close($cn);

?>