<?php
 include 'conn.php';
 $userName=str_replace(" ","",$_POST['userName']);//接收客户端发来的userName；
 $userName='abc';
 $sql="select password from users where userNmame='$userName'";//从usersPW查询
 $query=mysqli_query($cn,$sql);
 $result=mysqli_fetch_array($query);
 //echo $result['password'];
if(is_array($result)){
		 echo $result['passWord'];//若结果为数组则输出密码 
 }
 else return null;
 mysqli_close($cn);
 ?>
