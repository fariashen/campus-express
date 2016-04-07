<?php
    include 'conn.php';
    $userName=str_replace(" ","",$_POST['userName']);//接收客户端发来的userName；
    //$userName="user2";
    $sql="select * from users where userName='$userName'";
    $query=mysqli_query($cn, $sql);
    $result=mysqli_fetch_array($query);
    print(json_encode($result));
    mysqli_close($cn);
?>