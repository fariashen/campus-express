<?php
    include 'conn.php';
    $providerName=iconv("UTF-8","GBK//IGNORE",str_replace(" ","",$_POST['userName']));//���տͻ��˷�����userName��
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
    if ($reward<=$balance)//����С�ڵ������ʱ�Ž��з�������
    {   
        $balances=$balance-$reward;
        mysqli_query($cn, "update users set balances='$balances' where userName='$providerName'");
        $sql="insert into expressorder (providerName,nickName,reward,addFrom,addTo,timeLimit,size,phone,kinds,remarks) values ('$providerName','$nickName','$reward','$addFrom','$addTo','$timeLimit','$size','$phone','$kinds','$remarks')";
        $query=mysqli_query($cn, $sql);
        if ($query) 
              echo "1";//�����ɹ�
                
            else {
                mysqli_query($cn, "update users set balances='$balance' where userName='$providerName'");//����ʧ�ܣ�����ԭ������ǰ��ֵ
                echo "2";//ϵͳ��æ������ʧ��
            }
        
        }
        else {
            echo'3';//���㣬����ʧ��
        }
        mysqli_close($cn);

    