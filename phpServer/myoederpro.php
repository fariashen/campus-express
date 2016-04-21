<?php
    include 'conn.php';
    $providerName=str_replace(" ","",$_POST['userName']);
    $sql="select * from expressorder where providerName='$providerName' order by orderID desc";
    $query=mysqli_query($cn, $sql);
    while($result=mysqli_fetch_assoc($query)){
        $data[]=$result; }
        print "{\"data\":".(json_encode($data))."}";
        mysqli_close($cn);