<?php
$con = mysql_connect("localhost", "root", "");
		if(!$con) {
			die("连接数据库出错");
		}
		
		mysql_query('SET NAMES utf8');
		mysql_select_db("book", $con);
?>