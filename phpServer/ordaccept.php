<?php
    include 'conn.php';
    $courierName=str_replace(" ","",$_POST['userName']);
    $orderID=str_replace(" ","",$_POST['orderID']);
    $sql="select ordflag from expressorder where orderID='$orderID'";
    $query=mysqli_query($cn, $sql);
    $result=mysqli_fetch_array($query);
    if ($result[0]==1) echo "2";//�ӵ�ʧ�ܣ��õ��Ѿ�������
    else {
        $sql="update expressorder set ordflag='1',courierName='$courierName'";
        $query=mysqli_query($cn, $sql);
        if ($query){
            echo "1";//�ӵ��ɹ�
        }
        else echo "0";//�ӵ�ʧ��,ϵͳ��æ
    }
    mysqli_close($cn);