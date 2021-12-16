

<?php

$server = "localhost";
$user = "root";
$pass = "clave";
$bd = "BDSoma";

//Creamos la conexión
$conexion = mysqli_connect($server, $user, $pass,$bd)
or die("Ha sucedido un error inexperado en la conexion de la base de datos");

//generamos la consulta

$sql = "SELECT *  FROM Discos";


mysqli_set_charset($conexion, "utf8"); //formato de datos utf8
if(!$result = mysqli_query($conexion, $sql)) die();

while($row = mysqli_fetch_array($result))
{
    $id=$row['Id'];
    $grupo=$row['Grupo'];
    $album=$row['Album'];
    $anio=$row['Anio'];

    $discos[] = array('Id'=> $id, 'Grupo'=> $grupo, 'Album'=> $album, 'Anio'=> $anio);

}

//desconectamos la base de datos
$close = mysqli_close($conexion)
or die("Ha sucedido un error inexperado en la desconexion de la base de datos");

//Creamos el JSON
$json_string = json_encode($discos);
echo $json_string;
?>
