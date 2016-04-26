<?php
    /**
     * 我的已发订单页后台php页面
     * 需要接收的参数为用户名userName
     * 返回订单详细信息json串
     * **/
    include 'conn.php';
    $providerName=str_replace(" ","",$_POST['userName']);
    $providerName=mb_convert_encoding($providerName,"UTF-8");
    //$providerName="test";
    $sql="select * from expressorder where providerName='$providerName' order by orderID desc";
    $query=mysqli_query($cn, $sql);
    while($result=mysqli_fetch_assoc($query)){
        $data[]=$result; }
        print "{\"data\":".(json_encode($data))."}";
    mysqli_close($cn);