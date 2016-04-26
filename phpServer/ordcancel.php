<?php
    /**
     * 取消订单功能的后台php代码
     * 需要接收的参数是订单号orderID
     * 取消成功返回1，失败返回0         
     * **/
    include 'conn.php';
    $orderID=str_replace(" ", "", $_POST['orderID']);
    $sql="update expressorder set ordflag=3 where orderID='$orderID'";
    $query=mysqli_query($cn, $sql);
    if ($query)
        echo "1";
    else 
        echo "0";