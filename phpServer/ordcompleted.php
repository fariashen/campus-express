<?php
    /**
     * 确认收货功能的后台php代码
     * 需要接收的参数为订单号orderID
     * 成功返回1，失败返回0
     * **/
    include 'conn.php';
    $orderID=str_replace(" ","",$_POST['orderID']);
    //将订单标志位置为2，表示订单完成
    $sql="update expressorder set ordflag='2' where orderID='$orderID'";
    $query=mysqli_query($cn, $sql);
    if ($query){
        $couName=mysqli_fetch_array(mysqli_query($cn, "select courierName from expressorder where orderID='$orderID'"));
        $courierName=$couName['0'];
        //查找到该订单的报酬
        $reward=mysqli_fetch_array(mysqli_query($cn,"select reward from users where orderID='$orderID'"));
        //查找到快递员的余额
        $balances=mysqli_fetch_array(mysqli_query($cn,"select balances from users where userName='$courierName'"));
        //修改快递员的余额
        $balances=$balances['0']+$reward['0'];
        $query1=mysqli_query($cn, "update users set balances='$balances'");
        //余额修改成功，订单完成
        if ($query1) 
            echo "1";
        else {
            //余额修改失败，把订单标志位重新置为1，完成订单失败
            mysqli_query($cn, "update expressorder set ordflag='1' where orderID='$orderID'");
            echo "0";
        }
    }
    //修改订单标志失败，订单未完成，稍后再试
    else 
        echo "0";
    mysqli_close($cn);