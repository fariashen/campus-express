<?php
    /**
     * ȷ���ջ����ܵĺ�̨php����
     * ��Ҫ���յĲ���Ϊ������orderID
     * �ɹ�����1��ʧ�ܷ���2
     * **/
    include 'conn.php';
    $orderID=str_replace(" ","",$_POST['orderID']);
    //$orderID="1";
    //��������־λ��Ϊ2����ʾ�������
    $sql="update expressorder set ordflag=2 where orderID='$orderID'";
    $query=mysqli_query($cn, $sql);
    if ($query){
        $couName=mysqli_fetch_array(mysqli_query($cn, "select courierName from expressorder where orderID='$orderID'"));
        $courierName=$couName['0'];
        //���ҵ��ö����ı���
        $reward=mysqli_fetch_array(mysqli_query($cn,"select reward from expressorder where orderID='$orderID'"));
        //���ҵ����Ա�����
        $balances=mysqli_fetch_array(mysqli_query($cn,"select balances from users where userName='$courierName'"));
        //�޸Ŀ��Ա�����
        $balances=$balances['0']+$reward['0'];
        $query1=mysqli_query($cn, "update users set balances='$balances' where userName='$courierName'");
        //����޸ĳɹ����������
        if ($query1) {
            echo "1";
        }
        else {
            //����޸�ʧ�ܣ��Ѷ�����־λ������Ϊ1����ɶ���ʧ��
            mysqli_query($cn, "update expressorder set ordflag='1' where orderID='$orderID'");
            echo "2";
        }
    }
    //�޸Ķ�����־ʧ�ܣ�����δ��ɣ��Ժ�����
    else {
        echo "2";
    }
    mysqli_close($cn);