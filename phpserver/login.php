<?php
    /**
     * ��¼�����php����
     * ��Ҫ���յĲ���Ϊ�û���UserName������PassWord
     * ������ȷ����1�����󷵻�2�����޴��˷���3
     * **/
     //�����������ݿ��ļ�
     include 'conn.php';
     $userName=str_replace(" ","",$_POST['UserName']);
     $passWord=str_replace(" ", "", $_POST['PassWord']);
     //$userName='user1';
     //$passWord='12';
     $sql="select passWord from users where userName='$userName'";
     $query=mysqli_query($cn,$sql);
     $result=mysqli_fetch_array($query);
     
     if(is_array($result)){
         //������ȷ�����1���������2
         if ($passWord==$result['0'])
             echo "1"; 
         else 
             echo "2";
     }
     //�Ҳ������û����3
     else 
         echo "3";
     mysqli_close($cn);
    
