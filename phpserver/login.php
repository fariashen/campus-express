<?php
 include 'conn.php';
 $userName=str_replace(" ","",$_POST['UserName']);
 $passWord=str_replace(" ", "", $_POST['PassWord']);
 //$userName='user1';
 //$passWord='12';
 $sql="select passWord from users where userName='$userName'";
 $query=mysqli_query($cn,$sql);
 $result=mysqli_fetch_array($query);
 //echo $result['password'];
 if(is_array($result)){
		 if ($passWord==$result['0'])//������ȷ�����1���������2
		 echo "1"; 
		 else echo "2";
 }
 else echo "3";//�Ҳ������û����3
 mysqli_close($cn);
?>