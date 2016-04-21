<?php
    include 'conn.php';
    $orderID=str_replace(" ","",$_POST['orderID']);
    $sql="select providerName,courierName,nickName,reward,addFrom,addTo,timeLimit,size,phone,kinds,remarks from expressorder where orderID='$orderID'";
    $query=mysqli_query($cn, $sql);
    $result=mysqli_fetch_assoc($query);
    while($result){
        $data[]=$result; }
        print "{\"data\":".(json_encode($data))."}";
        mysqli_close($cn);