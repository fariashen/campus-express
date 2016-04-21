<?php
    include 'conn.php';
    $sql="select * from expressorder where ordflag=0 order by orderID desc";
    $query=mysqli_query($cn,$sql);
    while($result=mysqli_fetch_assoc($query)){
        $data[]=$result; }
    print "{\"data\":".(json_encode($data))."}";
    mysqli_close($cn);
       
