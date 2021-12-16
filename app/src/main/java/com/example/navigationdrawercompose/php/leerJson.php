

<?php

$server = "localhost";
$user = "root";
$pass = "clave";
$bd = "BDSoma";

//Creamos la conexión
$conexion = mysqli_connect($server, $user, $pass,$bd)
or die("Ha sucedido un error inexperado en la conexion de la base de datos");

$data = file_get_contents("generarJson.php");
$items = json_decode($data, true);

foreach ($items as $value) {
   $id = $value['Id']
   $grupo = $value['Grupo']
   $album = $value['Album']
   $anio = $value['Anio']

   print($id,$grupo,$album$anio);
}

?>