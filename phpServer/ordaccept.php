<?php
    /**
     * 接受订单功能呢的后台php页面
     * 需要接收的参数为用户名userName和订单号orderID
     * 服务器错误返回0，接单成功返回1，该单已被接返回2
     * **/
    include 'conn.php';
    $courierName=str_replace(" ","",$_POST['userName']);
    $courierName=mb_convert_encoding($courierName,"UTF-8");
    $orderID=str_replace(" ","",$_POST['orderID']);
    //$courierName="aaa";
    //$orderID="3";
    $sql="select ordflag from expressorder where orderID='$orderID'";
    $query=mysqli_query($cn, $sql);
    $result=mysqli_fetch_array($query);
    //接单失败，该单已经被接了
    if ($result[0]==1) echo "2";
    else {
        $sql="update expressorder set ordflag=1,courierName='$courierName' where orderID='$orderID'";
        $query=mysqli_query($cn, $sql);
        if ($query){
            //接单成功
            echo "1";
        }
        //接单失败,系统繁忙
        else 
            echo "0";
    }
    mysqli_close($cn);