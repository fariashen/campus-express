<?php
    /**
     * ȡ���������ܵĺ�̨php����
     * ��Ҫ���յĲ����Ƕ�����orderID
     * ȡ���ɹ�����1��ʧ�ܷ���0         
     * **/
    include 'conn.php';
    $orderID=str_replace(" ", "", $_POST['orderID']);
    $sql="update expressorder set ordflag=3 where orderID='$orderID'";
    $query=mysqli_query($cn, $sql);
    if ($query)
        echo "1";
    else 
        echo "0";