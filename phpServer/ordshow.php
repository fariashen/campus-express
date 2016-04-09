<?php
    include 'conn.php';
    $sql="select providerName,reward,size,kinds,timeLimit from expressorder order by orderID desc";
    $query=mysqli_query($cn,$sql);
    while($result=mysqli_fetch_assoc($query)){
        $data[]=$result; }
    print "{\"data\":".(json_encode($data))."}";
    mysqli_close($cn);
       
?>