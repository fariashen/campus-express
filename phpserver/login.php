<?php
    /**
     * 登录处理的php代码
     * 需要接收的参数为用户名UserName和密码PassWord
     * 密码正确返回1，错误返回2，查无此人返回3
     * **/
     //引入连接数据库文件
     include 'conn.php';
     $userName=str_replace(" ","",$_POST['UserName']);
     $passWord=str_replace(" ", "", $_POST['PassWord']);
     //$userName='user1';
     //$passWord='12';
     $sql="select passWord from users where userName='$userName'";
     $query=mysqli_query($cn,$sql);
     $result=mysqli_fetch_array($query);
     
     if(is_array($result)){
         //密码正确，输出1，否则输出2
         if ($passWord==$result['0'])
             echo "1"; 
         else 
             echo "2";
     }
     //找不到该用户输出3
     else 
         echo "3";
     mysqli_close($cn);
    
