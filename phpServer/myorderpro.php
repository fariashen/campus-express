<?php
    /**
     * �ҵ��ѷ�����ҳ��̨phpҳ��
     * ��Ҫ���յĲ���Ϊ�û���userName
     * ���ض�����ϸ��Ϣjson��
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