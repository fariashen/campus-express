<?php
 include 'conn.php';
 $userName=str_replace(" ","",$_POST['userName']);//���տͻ��˷�����userName��
 $userName='abc';
 $sql="select password from users where userNmame='$userName'";//��usersPW��ѯ
 $query=mysqli_query($cn,$sql);
 $result=mysqli_fetch_array($query);
 //echo $result['password'];
if(is_array($result)){
		 echo $result['passWord'];//�����Ϊ������������� 
 }
 else return null;
 mysqli_close($cn);
 ?>
