<?php
    /**
     * ע�����ĺ�̨php����ҳ��
     * ��Ҫ���յĲ���Ϊ�û���userName������passWord
     * ���������󷵻�1���û����ظ�����2��ע��ɹ�����3
     * **/
    include 'conn.php';
    $userName=str_replace(" ","",$_POST['userName']);//���տͻ��˷�����userName��
    $passWord=str_replace(" ","",$_POST['passWord']);//���տͻ��˷�����passWord��
    //$phone=str_replace(" ","",$_POST['phone']);//���տͻ��˷�����phone��
    //$email=str_replace(" ","",$_POST['email']);//���տͻ��˷�����email��
    //$userName="user4";
    //$passWord="123456";
    //$phone="15633333333";
    //$email="abc@126.com";
    $sql="select * from users where userName='$userName'";
    $query=mysqli_query($cn,$sql);
    $result=mysqli_fetch_array($query);
    if(is_array($result)){
        //�����Ϊ���飬���û������Ѿ����ڴ��û������򷵻�2
	   echo "2";	 
    }
    else {
	   $sql1="insert into users (userName,passWord) values ('$userName','$passWord')";
	   $query1=mysqli_query($cn,$sql1);
	   if (!$query1) 
	       //����������ע��ʧ��
	       echo "1";
       else 
           //���򷵻�3(ע��ɹ�)
           echo "3";
	 }
    mysqli_close($cn);

?>
