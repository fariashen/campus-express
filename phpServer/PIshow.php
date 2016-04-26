<?php
    /**
     * 客户端个人信息显示页的后台php代码
     * 需要接收的参数为用用户名userName
     * 返回结果为个人信息的json串**/
    include 'conn.php';
    //接收客户端发来的userName；
    $userName=str_replace(" ","",$_POST['userName']);
    $userName=mb_convert_encoding($userName,"UTF-8");
    //$userName="123";
    $sql="select * from users where userName='$userName'";
    $query=mysqli_query($cn, $sql);
    while($result=mysqli_fetch_assoc($query)){
        $data[]=$result; }
    print "{\"data\":".(json_encode($data))."}";
    mysqli_close($cn);
