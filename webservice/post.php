<html>
<head>
  <meta charset="utf-8">

</head>
  <body>
<?php
if($_POST){
    //echo post_to_url("http://localhost/webservice/book/test.php/normalUser/register", $_POST);
} else{
 ?>
ADD RECORD.
<form action="http://localhost/webservice/book/test.php/administrator/deleteBook/101/12108238/123" method="post">
<input type="text" name="bookname" placeholder="bookname" /><br>
<input type="text" name="bookauthor" placeholder="bookauthor" /><br>
<input type="text" name="booktype" placeholder="booktype" /><br>
<input type="text" name="actid" placeholder="actid" /><br>
<input type="text" name="bookinfo" placeholder="bookinfo" /><br>
<input type="text" name="bookprice" placeholder="bookprice" /><br>
<input type="text" name="bookstatus" placeholder="bookstatus" /><br>
<input type="hidden" name="_METHOD" value="POST" />
<input type="submit" value="A D D" />
</form>
<?php 
}

/*function post_curl($_url, $_data) {
   $mfields = '';
   foreach($_data as $key => $val) { 
      $mfields .= $key . '=' . $val . '&amp;'; 
   }
   rtrim($mfields, '&amp;');
   $pst = curl_init();

   curl_setopt($pst, CURLOPT_URL, $_url);
   curl_setopt($pst, CURLOPT_POST, count($_data));
   curl_setopt($pst, CURLOPT_POSTFIELDS, $mfields);
   curl_setopt($pst, CURLOPT_RETURNTRANSFER, 1);

   $res = curl_exec($pst);

   curl_close($pst);
   return $res;
}*/

?>
</body>
</html>