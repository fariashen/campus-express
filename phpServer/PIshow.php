<?php
    /**
     * �ͻ��˸�����Ϣ��ʾҳ�ĺ�̨php����
     * ��Ҫ���յĲ���Ϊ���û���userName
     * ���ؽ��Ϊ������Ϣ��json��**/
    include 'conn.php';
    //���տͻ��˷�����userName��
    $userName=str_replace(" ","",$_POST['userName']);
    $userName=mb_convert_encoding($userName,"UTF-8");
    //$userName="123";
    $sql="select * from users where userName='$userName'";
    $query=mysqli_query($cn, $sql);
    while($result=mysqli_fetch_assoc($query)){
        $data[]=$result; }
    print "{\"data\":".(json_encode($data))."}";
    mysqli_close($cn);
