<?php
    include 'conn.php';
    $orderID=str_replace(" ","",$_POST['orderID']);
    $sql="update expressorder set ordflag='2' where orderID='$orderID'";
    $query=mysqli_query($cn, $sql);
    if ($query){
        $couName=mysqli_fetch_array(mysqli_query($cn, "select courierName from expressorder where orderID='$orderID'"));
        $courierName=$couName['0'];
        $reward=mysqli_fetch_array(mysqli_query($cn,"select reward from users where orderID='$orderID'"));
        $balances=mysqli_fetch_array(mysqli_query($cn,"select balances from users where userName='$courierName'"));
        $balances=$balances['0']+$reward['0'];
        $query1=mysqli_query($cn, "updata users set balances='$balances'");
        if ($query1) echo "1";//�������
        else {
            mysqli_query($cn, "update expressorder set ordflag='1' where orderID='$orderID'");
            echo "0";//�޸����ʧ�ܣ�����δ��ɣ��Ժ�����
        }
    }
    else echo "0";//�޸Ķ�����־ʧ�ܣ�����δ��ɣ��Ժ�����
    mysqli_close($cn);