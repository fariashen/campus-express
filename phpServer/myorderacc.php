<?php
    /**
     * �ҵ��ѽӶ���ҳ��̨phpҳ��
     * ��Ҫ���յĲ���Ϊ�û���userName
     * ���ض�����ϸ��Ϣjson��
     * **/
    include 'conn.php';
    $courierName=str_replace(" ","",$_POST['userName']);
    $courierName=mb_convert_encoding($courierName,"UTF-8");
    //$courierName="aaa";
    $sql="select * from expressorder where courierName='$courierName' order by orderID desc";
    $query=mysqli_query($cn, $sql);
    while($result=mysqli_fetch_assoc($query)){
        $data[]=$result; }
        print "{\"data\":".(json_encode($data))."}";
    mysqli_close($cn);