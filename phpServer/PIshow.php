<?php
    include 'conn.php';
    $userName=str_replace(" ","",$_POST['userName']);//接收客户端发来的userName；
    //$userName="123";
    $sql="select * from users where userName='$userName'";
    $query=mysqli_query($cn, $sql);
    while($result=mysqli_fetch_assoc($query)){
        $data[]=$result; }
    print "{\"data\":".(json_encode($data))."}";
    mysqli_close($cn);
