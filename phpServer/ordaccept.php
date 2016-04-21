<?php
    include 'conn.php';
    $courierName=str_replace(" ","",$_POST['userName']);
    $orderID=str_replace(" ","",$_POST['orderID']);
    /*8$courierName="test";
    $orderID="2";*/
    $sql="select ordflag from expressorder where orderID='$orderID'";
    $query=mysqli_query($cn, $sql);
    $result=mysqli_fetch_array($query);
    //接单失败，该单已经被接了
    if ($result[0]==1) echo "2";
    else {
        $sql="update expressorder set ordflag='1',courierName='$courierName' where orderID='$orderID'";
        $query=mysqli_query($cn, $sql);
        if ($query){
            //接单成功
            echo "1";
        }
        //接单失败,系统繁忙
        else echo "0";
    }
    mysqli_close($cn);