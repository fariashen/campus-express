<?php
    include 'conn.php';
    $userName=str_replace(" ","",$_POST['userName']);//���տͻ��˷�����userName��
    $passWord=str_replace(" ","",$_POST['passWord']);//���տͻ��˷�����passWord��
    $phone=str_replace(" ","",$_POST['phone']);//���տͻ��˷�����phone��
    $email=str_replace(" ","",$_POST['email']);//���տͻ��˷�����email��
    $sql="update users set userName='$userName',passWord='$passWord',phone='$phone',email='$email'";
    $query=mysqli_query($cn,$sql);
    if ($query) echo "success";//������ʧ�ܣ�����ʧ��ԭ��(ע��ʧ��)
    else return false;//���򷵻�true(ע��ɹ�)	
    mysqli_close($cn);

?>