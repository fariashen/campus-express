<?php
    /**
     * 取消订单功能的后台php代码
     * 需要接收的参数是订单号orderID
     * 取消成功返回0，失败返回2         
     * **/
    include 'conn.php';
    $orderID=str_replace(" ", "", $_POST['orderID']);
    $sql="update expressorder set ordflag=3 where orderID='$orderID'";
    $query=mysqli_query($cn, $sql);
    if ($query){
        $proName=mysqli_fetch_array(mysqli_query($cn, "select providerName from expressorder where orderID='$orderID'"));
        $providerName=$proName['0'];
        //查找到该订单的报酬
        $reward=mysqli_fetch_array(mysqli_query($cn,"select reward from expressorder where orderID='$orderID'"));
        //查找到发单用户的余额
        $balances=mysqli_fetch_array(mysqli_query($cn,"select balances from users where userName='$providerName'"));
        //修改发单用户的余额
        $balances=$balances['0']+$reward['0'];
        $query1=mysqli_query($cn, "update users set balances='$balances' where userName='$providerName'");
        //余额修改成功，订单完成
        if ($query1){
            echo "0";
        }
        else {
            echo "2";
        }
    }
    else { 
        echo "2";
    }