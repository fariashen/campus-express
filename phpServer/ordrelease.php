<?php
    include 'conn.php';
    $providerName=iconv("UTF-8","GBK//IGNORE",str_replace(" ","",$_POST['userName']));//接收客户端发来的userName；
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
    if ($reward<=$balance)//报酬小于等于余额时才进行发单操作
    {   
        $balances=$balance-$reward;
        mysqli_query($cn, "update users set balances='$balances' where userName='$providerName'");
        $sql="insert into expressorder (providerName,nickName,reward,addFrom,addTo,timeLimit,size,phone,kinds,remarks) values ('$providerName','$nickName','$reward','$addFrom','$addTo','$timeLimit','$size','$phone','$kinds','$remarks')";
        $query=mysqli_query($cn, $sql);
        if ($query) 
              echo "1";//发单成功
                
            else {
                mysqli_query($cn, "update users set balances='$balance' where userName='$providerName'");//发单失败，把余额还原到发单前的值
                echo "2";//系统繁忙，发单失败
            }
        
        }
        else {
            echo'3';//余额不足，发单失败
        }
        mysqli_close($cn);

    