<?php
    /**
    * 发布订单页的后台php代码
    * 需要接收的参数为订单的基本信息
    * 发单成功返回1，服务器错误返回2，余额不足返回3
    * **/
    include 'conn.php';
    //接收客户端发来的userName；
    $providerName=str_replace(" ","",$_POST['userName']);
    //将userName转换为与数据库一致的utf-8编码
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
    //报酬小于等于余额时才进行发单操作
    if ($reward<=$balance)
    {   
        //修改用户的余额
        $balances=$balance-$reward;
        mysqli_query($cn, "update users set balances='$balances' where userName='$providerName'");
        //将订单信息插入到数据
        $sql="insert into expressorder (providerName,nickName,reward,addFrom,addTo,timeLimit,size,phone,kinds,remarks) values ('$providerName','$nickName','$reward','$addFrom','$addTo','$timeLimit','$size','$phone','$kinds','$remarks')";
        $query=mysqli_query($cn, $sql);
        if ($query) 
              //插入成功，发单成功
              echo "1";
                
            else {
                //插入失败，将余额回复到原来状态，发单失败
                mysqli_query($cn, "update users set balances='$balance' where userName='$providerName'");//发单失败，把余额还原到发单前的值
                echo "2";
            }
        
        }
        else {
            //余额不足，发单失败
            echo'3';
        }
        mysqli_close($cn);

    