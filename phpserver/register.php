<?php
    include 'conn.php';
    $userName=str_replace(" ","",$_POST['userName']);//���տͻ��˷�����userName��
    $passWord=str_replace(" ","",$_POST['passWord']);//���տͻ��˷�����passWord��
    //$phone=str_replace(" ","",$_POST['phone']);//���տͻ��˷�����phone��
    //$email=str_replace(" ","",$_POST['email']);//���տͻ��˷�����email��
    //$userName="user3";
    //$passWord="123456";
    //$phone="15633333333";
    //$email="abc@126.com";
    $sql="select * from users where userName='$userName'";
    $query=mysqli_query($cn,$sql);
    $result=mysqli_fetch_array($query);
    if(is_array($result)){
	echo "2";//�����Ϊ���飬���û������Ѿ����ڴ��û������򷵻�2		 
    }
    else {
	   $sql1="insert into users (userName,passWord) values ('$userName','$passWord')";
	   $query1=mysqli_query($cn,$sql1);
	   if (!$query1) echo "success";//������ʧ�ܣ�����ʧ��ԭ��(ע��ʧ��)
       else echo "3";//���򷵻�3(ע��ɹ�)
	 }
    mysqli_close($cn);

?>
