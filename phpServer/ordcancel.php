<?php
    /**
     * ȡ���������ܵĺ�̨php����
     * ��Ҫ���յĲ����Ƕ�����orderID
     * ȡ���ɹ�����0��ʧ�ܷ���2         
     * **/
    include 'conn.php';
    $orderID=str_replace(" ", "", $_POST['orderID']);
    $sql="update expressorder set ordflag=3 where orderID='$orderID'";
    $query=mysqli_query($cn, $sql);
    if ($query){
        $proName=mysqli_fetch_array(mysqli_query($cn, "select providerName from expressorder where orderID='$orderID'"));
        $providerName=$proName['0'];
        //���ҵ��ö����ı���
        $reward=mysqli_fetch_array(mysqli_query($cn,"select reward from expressorder where orderID='$orderID'"));
        //���ҵ������û������
        $balances=mysqli_fetch_array(mysqli_query($cn,"select balances from users where userName='$providerName'"));
        //�޸ķ����û������
        $balances=$balances['0']+$reward['0'];
        $query1=mysqli_query($cn, "update users set balances='$balances' where userName='$providerName'");
        //����޸ĳɹ����������
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