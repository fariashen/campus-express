<?php
    /**
     * ���ܶ��������صĺ�̨phpҳ��
     * ��Ҫ���յĲ���Ϊ�û���userName�Ͷ�����orderID
     * ���������󷵻�0���ӵ��ɹ�����1���õ��ѱ��ӷ���2
     * **/
    include 'conn.php';
    $courierName=str_replace(" ","",$_POST['userName']);
    $courierName=mb_convert_encoding($courierName,"UTF-8");
    $orderID=str_replace(" ","",$_POST['orderID']);
    //$courierName="aaa";
    //$orderID="3";
    $sql="select ordflag from expressorder where orderID='$orderID'";
    $query=mysqli_query($cn, $sql);
    $result=mysqli_fetch_array($query);
    //�ӵ�ʧ�ܣ��õ��Ѿ�������
    if ($result[0]==1) echo "2";
    else {
        $sql="update expressorder set ordflag=1,courierName='$courierName' where orderID='$orderID'";
        $query=mysqli_query($cn, $sql);
        if ($query){
            //�ӵ��ɹ�
            echo "1";
        }
        //�ӵ�ʧ��,ϵͳ��æ
        else 
            echo "0";
    }
    mysqli_close($cn);