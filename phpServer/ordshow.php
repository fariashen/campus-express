<?php
    /**
     * 客户端主页订单展示页的后台php代码
     * 返回数据库中所有可接订单信息的json串
     * **/
    include 'conn.php';
    $sql="select * from expressorder where ordflag=0 order by orderID desc";
    $query=mysqli_query($cn,$sql);
    while($result=mysqli_fetch_assoc($query)){
        $data[]=$result; }
    print "{\"data\":".(json_encode($data))."}";
    mysqli_close($cn);
       
