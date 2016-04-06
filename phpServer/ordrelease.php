<?php
    include 'conn.php';
    $providerName=str_replace(" ","",$_POST['userName']);//接收客户端发来的userName；
    $nickName=str_replace(" ","",$_POST['nickName']);
    $reward=str_replace(" ","",$_POST['reward']);
    $addFrom=str_replace(" ","",$_POST['addFrom']);
    $addTo=str_replace(" ","",$_POST['addTo']);
    $timeLimit=str_replace(" ","",$_POST['timeLimit']);
    $size=str_replace(" ","",$_POST['size']);
    $phone=str_replace(" ","",$_POST['phone']);
    $kinds=str_replace(" ","",$_POST['kinds']);
    $remarks=str_replace(" ","",$_POST['remarks']);
    $result=mysqli_fetch_array(mysqli_query($cn,"select balances from users where userName='$providerName'"));
    if ($remarks<=$result['0'])
    {   $balances=$result['0']-$remarks;
        mysqli_query($cn, "update users set balances='$balances' where userName='$providerName'");
        $sql="insert into expressorder (providerName,nickName,reward,addFrom,addTo,timeLimit,size,phone,kinds,remarks) values ('$providerName','$nickName','$reward','$addFrom','$addTo','$timeLimit','$size','$phone','$kinds','$remarks')";
        $query=mysqli_query($cn, $sql);
        if ($query) echo '1';//发单成功
        else echo '2';//系统繁忙，发单失败
        }
    else '3';//余额不足，发单失败
?>
    