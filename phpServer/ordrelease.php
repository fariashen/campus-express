<?php
    /**
    * ��������ҳ�ĺ�̨php����
    * ��Ҫ���յĲ���Ϊ�����Ļ�����Ϣ
    * �����ɹ�����1�����������󷵻�2�����㷵��3
    * **/
    include 'conn.php';
    //���տͻ��˷�����userName��
    $providerName=str_replace(" ","",$_POST['userName']);
    //��userNameת��Ϊ�����ݿ�һ�µ�utf-8����
    $providerName=mb_convert_encoding($providerName,"UTF-8");
    $nickName=str_replace(" ","",$_POST['nickName']);
    $reward=str_replace(" ","",$_POST['reward']);
    $addFrom=str_replace(" ","",$_POST['addFrom']);
    $addTo=str_replace(" ","",$_POST['addTo']);
    $timeLimit=str_replace(" ","",$_POST['timeLimit']);
    $size=str_replace(" ","",$_POST['size']);
    $phone=str_replace(" ","",$_POST['phone']);
    $kinds=str_replace(" ","",$_POST['kinds']);
    $remarks=str_replace(" ","",$_POST['remarks']);
    $result=mysqli_fetch_array(mysqli_query($cn,"select balances from users where userName='$providerName'"));
    $balance=$result['0'];
    //echo $balance;
    //����С�ڵ������ʱ�Ž��з�������
    if ($reward<=$balance)
    {   
        //�޸��û������
        $balances=$balance-$reward;
        mysqli_query($cn, "update users set balances='$balances' where userName='$providerName'");
        //��������Ϣ���뵽����
        $sql="insert into expressorder (providerName,nickName,reward,addFrom,addTo,timeLimit,size,phone,kinds,remarks) values ('$providerName','$nickName','$reward','$addFrom','$addTo','$timeLimit','$size','$phone','$kinds','$remarks')";
        $query=mysqli_query($cn, $sql);
        if ($query) 
              //����ɹ��������ɹ�
              echo "1";
                
            else {
                //����ʧ�ܣ������ظ���ԭ��״̬������ʧ��
                mysqli_query($cn, "update users set balances='$balance' where userName='$providerName'");//����ʧ�ܣ�����ԭ������ǰ��ֵ
                echo "2";
            }
        
        }
        else {
            //���㣬����ʧ��
            echo'3';
        }
        mysqli_close($cn);

    