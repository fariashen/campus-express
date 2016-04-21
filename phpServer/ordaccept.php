<?php
    include 'conn.php';
    $courierName=str_replace(" ","",$_POST['userName']);
    $orderID=str_replace(" ","",$_POST['orderID']);
    $sql="select ordflag from expressorder where orderID='$orderID'";
    $query=mysqli_query($cn, $sql);
    $result=mysqli_fetch_array($query);
    if ($result[0]==1) echo "2";//接单失败，该单已经被接了
    else {
        $sql="update expressorder set ordflag='1',courierName='$courierName'";
        $query=mysqli_query($cn, $sql);
        if ($query){
            echo "1";//接单成功
        }
        else echo "0";//接单失败,系统繁忙
    }
    mysqli_close($cn);