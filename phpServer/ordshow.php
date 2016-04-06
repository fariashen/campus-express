<?php
    include 'conn.php';
    $sql="select providerName,reward,size,kinds from expressorder";
    $query=mysqli_query($cn,$sql);
    while($result=mysqli_fetch_assoc($query)){
        $data[]=$result; }
        print(json_encode($data));
?>