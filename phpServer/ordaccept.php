<?php
    include 'conn.php';
    $courierName=str_replace(" ","",$_POST['userName']);
    $orderID=str_replace(" ","",$_POST['orderID']);
    /*8$courierName="test";
    $orderID="2";*/
    $sql="select ordflag from expressorder where orderID='$orderID'";
    $query=mysqli_query($cn, $sql);
    $result=mysqli_fetch_array($query);
    //�ӵ�ʧ�ܣ��õ��Ѿ�������
    if ($result[0]==1) echo "2";
    else {
        $sql="update expressorder set ordflag='1',courierName='$courierName' where orderID='$orderID'";
        $query=mysqli_query($cn, $sql);
        if ($query){
            //�ӵ��ɹ�
            echo "1";
        }
        //�ӵ�ʧ��,ϵͳ��æ
        else echo "0";
    }
    mysqli_close($cn);