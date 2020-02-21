<?php

    //database configuration
    $host       = "localhost";
    $user       = "u0731275";
    $pass       = "Q3!4rVBT";
    $database   = "efendiapp";

    $connect = new mysqli($host, $user, $pass, $database);

    if (!$connect) {
        die ("connection failed: " . mysqli_connect_error());
    } else {
        $connect->set_charset('utf8');
    }

?>