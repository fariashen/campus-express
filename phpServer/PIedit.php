<?php
    /**
     * ������Ϣ�޸�ҳ�ĺ�̨php����
     * ��Ҫ���յĲ���Ϊ�û������޸ĺ�ĸ�����Ϣ
     * �޸ĳɹ�����1��ʧ�ܷ���0
     * **/
    include 'conn.php';
    //���տͻ��˷����ĸ�����Ϣ
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
    //���޸ĳɹ�������1
    if ($query){ 
        echo "1";
    }
    //���򷵻�0
    else {
        echo "0";
    }
    mysqli_close($cn);

?>