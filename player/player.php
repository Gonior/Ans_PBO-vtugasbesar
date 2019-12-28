<?php

$ID = $_GET['id'];
$urlStreaming = "";
$url = "localhost";
$db = "dbtugasbesar";
$username = "root";
$password = "";

$conn = mysqli_connect($url,$username,$password,$db);

//cek koneksi

if(mysqli_connect_error()){
	echo "Koneksi database gagal : ". mysqli_connect_error();

}
$sql_query = "SELECT * FROM streaming where id_streaming=$ID";
$data = mysqli_query($conn, $sql_query);
	while($hasil = mysqli_fetch_array($data)) {
		$urlStreaming = $hasil['url_streaming'];
	}



?>

<html>
<?= $urlStreaming; ?>
	<iframe src="<?= $urlStreaming; ?>" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="100%" height="100%"></iframe>
</html>