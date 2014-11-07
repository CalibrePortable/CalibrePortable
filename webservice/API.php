<?php
	require './Slim/Slim.php';
	\Slim\Slim::registerAutoloader();
	//常量定义
	//, JSON_UNESCAPED_UNICODE可让json转为明码
	define("pagesize", 15, true);
	$app = new \Slim\Slim();

	/*//POST举例
	$app->post('/users/:id',function ($xid){
		global $app;
	    $req = $app->request(); // Getting parameter with names
	    $paramName = $req->params('name'); // Getting parameter with names
	    $paramEmail = $req->params('email'); // Getting parameter with names
	    $sql = "INSERT INTO restAPI (`name`,`email`,`ip`) VALUES (:name, :email, :ip)";
	    try {
	        $dbCon = getConnection();
	        $stmt = $dbCon->prepare($sql);  
	        $stmt->bindParam("name", $paramName);
	        $stmt->bindParam("email", $paramEmail);
	        $stmt->bindParam("ip", $_SERVER['REMOTE_ADDR']);
	        $stmt->execute();
	        //$user->id = $dbCon->lastInsertId();
	        $dbCon = null;
	        //echo json_encode($user); 
	    } 
	    catch(PDOException $e) {
	        echo '{"error":{"text":'. $e->getMessage() .'}}'; 
	    }
	    echo $xid;
	    found();
	});  */

	//pdo方法
		/*$sql = "INSERT INTO test (user_id,user_name,user_password) VALUES (:user_id, :user_name, :user_password)";
	    try {
	        $dbCon = getConnection();
	        $stmt = $dbCon->prepare($sql);  
	        $stmt->bindParam("user_id", $xuserId);
	        $stmt->bindParam("user_name",$xuserName);
	        $stmt->bindParam("user_password", $xpassword);
	        $stmt->execute();
	        //echo $xuserId,$xuserName,$xpassword;

	        //$user->id = $dbCon->lastInsertId();
	        $dbCon = null;
	        //echo json_encode($user); 
	    } 
	    catch(PDOException $e) {
	        echo '{"error":{"text":'. $e->getMessage() .'}}'; 
	    }*/

	//（已验证）POST登录http://localhost/webservice/book/API.php/normalUser/login   12108413/12345
	$app->post('/normalUser/login', function () {
		require 'conn.php';
		global $app;//网页中输入框name为userid、password
		$req = $app->request(); 
	    $xuserId = $req->params('userId'); 
	    $xpassword = $req->params('password'); 
		verify($xuserId,$xpassword)?found():error('verify_error');
		mysql_close($con);
	});

	//（已验证）POST注册http://localhost/webservice/book/API.php/normalUser/register   12108413/黄可庆/0/0
	$app->post('/normalUser/register', function () {
		require 'conn.php';
		global $app;//
		$req = $app->request(); 
	    $xuserId = $req->params('userId'); 
	    $xuserName=$_POST['userName']; //由于Slim中post不支持中文值得使用原始$_POST方法
	    $xpassword = $req->params('password');
	    $xrepassword = $req->params('rePassword');
		if($xpassword!=$xrepassword) 
			error('password_error');//确定password是否等于repassword
		else{
			if(verify($xuserId,'')){//确定user_id是否重复，重复返回0
				error('userid_error');
			}
			else{//未找到且注册成功返回1
				$sql="insert into user (user_id,user_name,user_password) values ($xuserId,'$xuserName','$xpassword')";
				//echo $sql;
				$query = mysql_query($sql);
				$query?found():error('sql_error');
			}
			mysql_close($con);
		}
	});

	//（已验证）GET扫一扫借书http://localhost/webservice/book/API.php/normalUser/borrow/1/12108413/12345
	$app->get('/normalUser/borrow/:bookId/:userId/:password', function ($xbookId,$xuserId,$xpassword) {
		require 'conn.php';
		/*global $app;
		$req = $app->request(); */
	    //$xpassword = $req->params('password'); 
		if(book_verify($xbookId)){//先确认书的状态book_status 1为已被借
			error('bookverify_error');
		}
		else{//再验证扫描人密码成功则借取
			verify($xuserId,$xpassword)?swap($xbookId,$xuserId):error('verify_error');
		}
		mysql_close($con);
		
	});
	
	//（已验证）GET图书搜索http://localhost/webservice/book/API.php/search/1/page=1/php
	//（已验证）GET获取图书列表http://localhost/webservice/book/API.php/search/5/page=1/all
	$app->get('/search/:type/page=:page/:keyword', function ($xtype,$xpage,$xkeyword) {
		if($xtype=='5')
			$xkeyword='';//~~~~~必须要传$xkeyword不然无法访问，将input中空值自动变为all
		$page_size=pagesize;
		$offset=($xpage-1)*$page_size;
		require 'conn.php';
		switch ($xtype) {
			case '1'://书名
				$xtype='book_name';
				break;
			case '2'://出版社
				$xtype='book_info';
				break;
			case '3'://作者
				$xtype='book_author';
				break;
			case '4'://种类
				$xtype='book_type';
				break;
			case '5'://全部
				$xtype='';
				break;
			default:
				error('url_error');
				break;
		}
		search(0,$xtype,$page_size,$offset,$xkeyword);
		mysql_close($con);
	});

	//（已验证）GET已借阅http://localhost/webservice/book/API.php/normalUser/return/12108413/12345
	$app->get('/normalUser/return/:userId/:password', function ($xuserId,$xpassword) {
		require 'conn.php';
		//先验证再输出
		if(!verify($xuserId,$xpassword)){
			error('verify_error');
		}
		else{
			$sql="SELECT book_name, book_author, book_type, book_info, book_price, book_status, favour, book_pic FROM bookcirculate cir, bookbasic ba, bookdetail de WHERE cir.book_id = ba.id AND cir.user_id = $xuserId AND de.book_id = ba.id";
			//echo $sql;
			$query = mysql_query($sql);
			$response = array();
			if(!$query) {
				error('sql_error');
			}
			else {
				$i = 0;
				while($res = mysql_fetch_array($query)) {
					$response[$i] = array(  'book_name'=>$res['book_name'],
											'book_author'=>$res['book_author'],
											'book_type'=>$res['book_type'],
											'book_info'=>$res['book_info'],
											'book_price'=>$res['book_price'],
											'book_status'=>$res['book_status'],
											'favour'=>$res['favour'],
											'book_pic'=>$res['book_pic']);			  
					$i++;
				}
				$response = json_encode($response);
				//found();
				echo $response;
			}
		}
		
		mysql_close($con);
	});

	//（已验证）POST登录http://localhost/webservice/book/API.php/Administrator/login   12108238/123
	$app->post('/Administrator/login', function () {
		require 'conn.php';
		global $app;
		$req = $app->request(); 
	    $xuserId = $req->params('userid'); 
	    $xpassword = $req->params('password');
		rank_verify($xuserId,$xpassword)?found():error('rankverify_error');
		mysql_close($con);
	});

	//（已验证）GET图书搜索http://localhost/webservice/book/API.php/searchA/1/page=1/php
	//（已验证）GET获取图书列表http://localhost/webservice/book/API.php/searchA/5/page=1/all
	$app->get('/searchA/:type/page=:page/:keyword', function ($xtype,$xpage,$xkeyword) {
		if($xtype=='5')
			$xkeyword='';//必须要传$xkeyword不然无法访问，将input中空值自动变为all
		$page_size=pagesize;
		$offset=($xpage-1)*$page_size;
		require 'conn.php';
		switch ($xtype) {
			case '1'://书名
				$xtype='book_name';
				break;
			case '2'://出版社
				$xtype='book_info';
				break;
			case '3'://作者
				$xtype='book_author';
				break;
			case '4'://种类
				$xtype='book_type';
				break;
			case '5'://全部
				$xtype='';
				break;
			default:
				error('url_error');
				break;
		}
		search(1,$xtype,$page_size,$offset,$xkeyword);
		mysql_close($con);
	});

	//（已验证）POST修改图书信息http://localhost/webservice/book/API.php/booklist/book/update/12108238/100
	$app->post('/booklist/book/update/:userId/:bookId/:password', function ($xuserId,$xbookId,$xpassword) {
		require 'conn.php';
		global $app;
		$req = $app->request(); 
	    //$xpassword = $req->params('password');
	    $book_name = $_POST['bookname'];
	    $book_author = $_POST['bookauthor'];
	    $book_type= $_POST['booktype'];
	    $book_info= $_POST['bookinfo'];
	    $book_status = $_POST['bookstatus'];
	    /*echo $xpassword,'<br/>';
	    echo $book_name;
	    echo $book_author;
	    echo $book_type;
	    echo $book_info;
	    echo $book_status;*/
		rank_verify($xuserId,$xpassword)?update($xbookId,$book_name,$book_author,$book_type,$book_info,$book_status):error('rankverify_error');
		mysql_close($con);
	});

	//（已验证）GET归还图书http://localhost/webservice/book/API.php/administrator/returnConfirm/47/12108238/123
	$app->get('/administrator/returnConfirm/:bookId/:userId/:password', function ($xbookId,$xuserId,$xpassword) {
		require 'conn.php';
		rank_verify($xuserId,$xpassword)?confirm($xbookId):error('rankverify_error');
		mysql_close($con);
	});

	//（已验证）POST添加图书http://localhost/webservice/book/API.php/administrator/addBook/12108238/123
	$app->post('/administrator/addBook/:userId/:password', function ($xuserId,$xpassword) {
		require 'conn.php';
		global $app;
		$req = $app->request(); 
	    $book_name = $_POST['bookname'];
	    $book_author = $_POST['bookauthor'];
	    $book_type = $_POST['booktype'];
	    $act_id = $req->params('actid');
	    $book_info= $_POST['bookinfo'];
	    $book_price = $req->params('bookprice');
	    //$book_status = $_POST['bookstatus'];
		rank_verify($xuserId,$xpassword)?add($book_name,$book_author,$book_type,$act_id,$book_info,$book_price):error('rankverify_error');
		mysql_close($con);
	});

	//（已验证）GET删除图书http://localhost/webservice/book/API.php/administrator/deleteBook/100/12108238/123
	$app->get('/administrator/deleteBook/:bookId/:userId/:password', function ($xbookId,$xuserId,$xpassword) {
		require 'conn.php';
		/*global $app;
		$req = $app->request(); */
		//$xuserId = $req->params('userid');
	    //$xpassword = $req->params('password');
		rank_verify($xuserId,$xpassword)?del($xbookId):error('rankverify_error');
		mysql_close($con);
	});

	//（已验证）GET查看已经借出的图书http://localhost/webservice/book/API.php/administrator/return/12108238/123
	$app->get('/administrator/return/:userId/:password', function ($xuserId,$xpassword) {
		require 'conn.php';
		//先验证再输出
		if(!rank_verify($xuserId,$xpassword)){
			error('rankverify_error');
		}
		else{
			$sql="SELECT book_name, book_author, book_type, book_info, book_price, book_status, favour, book_pic FROM bookbasic ba,bookdetail de WHERE ba.id=de.book_id and book_status='已被借'";
			//echo $sql;
			$query = mysql_query($sql);
			$response = array();
			if(!$query) {
				error('sql_error');
			}
			else {
				$i = 0;
				while($res = mysql_fetch_array($query)) {
					$response[$i] = array(  'book_name'=>$res['book_name'],
											'book_author'=>$res['book_author'],
											'book_type'=>$res['book_type'],
											'book_info'=>$res['book_info'],
											'book_price'=>$res['book_price'],
											'book_status'=>$res['book_status'],
											'favour'=>$res['favour'],
											'book_pic'=>$res['book_pic']);			  
					$i++;
				}
				
				$response = json_encode($response);
				//found();
				echo $response;
			}
		}
		mysql_close($con);
	});

	//程序入口
	$app->run();

	//以下为所建函数
	//用于搜索与获取图书列表
	function search($flag,$xtype,$page_size,$offset,$xkeyword){
		$where="where book_status <>'' ";
		$sql="select book_name,book_author,book_type,book_info,book_price,book_status,favour,
	book_pic from bookbasic basic join bookdetail detail on basic.id=detail.book_id ";
		$turn=" LIMIT $page_size OFFSET $offset ";
		if(!$flag){//1管理员0用户
			$where="where book_status not in ('未购买' , '未入库') ";//0
		}
		$xkeyword==''?
		$sql=$sql.$where://true则为获取图书列表
		$sql=$sql.$where." and $xtype like '%$xkeyword%' ";//false则为搜索
		$sql=$sql.$turn;
		//echo $sql;
		$query = mysql_query($sql);
		$response = array();
		if(!$query) {
			error('sql_error');
		}
		else {
			$i = 0;
			while($res = mysql_fetch_array($query)) {
				$response[$i] = array(  'book_name'=>$res['book_name'],
										'book_author'=>$res['book_author'],
										'book_type'=>$res['book_type'],
										'book_info'=>$res['book_info'],
										'book_price'=>$res['book_price'],
										'book_status'=>$res['book_status'],
										'favour'=>$res['favour'],
										'book_pic'=>$res['book_pic']);			  
				$i++;
			}
			
			$response = json_encode($response);
			//found();
			echo $response;
		}
	}

	//完成书的return
	function confirm($bookId){
		if(book_verify($bookId)){
			$sql="update bookbasic set book_status='未被借' where id=$bookId";
			$query = mysql_query($sql);
			//echo $sql;
			if(!$query) {
				error('sql_error');
			}
			else {
				$updated_at = date('Y-m-d');
				$sql="update bookcirculate set updated_at='$updated_at' where book_id=$bookId and updated_at='0000-00-00'";
				$query = mysql_query($sql);
				//echo $sql;
				if(!$query) {
					error('sql_error');
				}
				else {
					found();
				}
			}
		}
		else error('bookverify_error');//
	}

	//完成扫一扫借书
	function swap($bookId,$userId){
		$updated_at = date('Y-m-d');
		$sql="update bookbasic set book_status='已被借' where id=$bookId";
		$query = mysql_query($sql);
		//echo $sql;
		if(!$query) {
			error('sql_error');
		}
		else {
			$sql="insert bookcirculate (book_id,user_id,created_at) values ($bookId,$userId,'$updated_at')";
			$query = mysql_query($sql);
			//echo $sql;
			!$query?error('sql_error'):found();
		}
	}

	//更新图书数据
	function update($bookId,$book_name,$book_author,$book_type,$book_info,$book_status){
		$sql="update bookbasic set book_name='$book_name',book_author='$book_author',book_type='$book_type',book_info='$book_info',book_status='$book_status' where id=$bookId";
		$query = mysql_query($sql);
		//echo $sql;
		//$response = array();
		if(!$query) {
			error('sql_error');
		}
		else {
			found();
		}
	}

	//添加图书数据
	function add($book_name,$book_author,$book_type,$act_id,$book_info,$book_price){
		$sql="insert bookbasic (book_name,book_author,book_type,act_id,book_info,book_price) values ('$book_name','$book_author','$book_type',$act_id,'$book_info',$book_price)";
		$query = mysql_query($sql);
		//echo $sql;
		//$response = array();
		if(!$query) {
			error('sql_error');
		}
		else {
			found();
		}
	}

	//删除图书数据
	function del($bookId){
		$sql="delete from bookbasic where id=$bookId";
		$query = mysql_query($sql);
		//echo $sql;
		//$response = array();
		if(!$query) {
			error('sql_error');
		}
		else {
			found();
		}
	}

	//确认书的状态是否为"已被借"
	function book_verify($bookId){
		$sql="select book_status from bookbasic where id=$bookId";
		$query = mysql_query($sql);
		//echo $sql;
		$response = array();
		if(!$query) {
			error('sql_error');
		}
		else {
			$res = mysql_fetch_array($query);
			if($res['book_status']=="已被借"){//找到则为重复返回1				
				return 1;
			}
			else{
				return 0;
			}
		}
	}

	//确认用户权限
	function rank_verify($userId,$password){
		$sql="select count(*) from user where user_id=$userId and user_rank='图书管理'";
		$query = mysql_query($sql);
		//echo $sql;
		$response = array();
		if(!$query) {
			error('sql_error');
		}
		else {
			$res = mysql_fetch_array($query);
			if($res['count(*)']!=0){//找到则为重复返回1				
				if(verify($userId,$password)){
					return 1;
				}
			}
			else{
				return 0;
			}
		}
	}

	//1.确认用户名是否存在2.验证用户登录
	function verify($userId,$password){
		$password==''?
		$sql="select count(*) from user where user_id=$userId":
		$sql="select count(*) from user where user_id=$userId and user_password=$password";
		$query = mysql_query($sql);
		//echo $sql;
		$response = array();
		if(!$query) {
			error('sql_error');
		}
		else {
			$res = mysql_fetch_array($query);
			if($res['count(*)']!=0){//找到则为重复返回1				
				return 1;
			}
			else{
				return 0;
			}
		}
	}

	function error($type){
		switch ($type) {
			case 'rankverify_error':
				$info="当前用户没有足够权限";
				break;
			case 'bookverify_error':
				$info="书在数据库中状态出错";
				break;
			case 'url_error':
				$info="post中url错误";
				break;
			case 'verify_error':
				$info="后台提交的用户名密码错误";
				break;
			case 'password_error':
				$info="两次输入的密码不一致";
				break;
			case 'userid_error':
				$info="用户id重复";
				break;
			case 'sql_error':
				$info="sql语句编写出错";
				break;
			
			default:
				$info="未知错误";
				break;
		}
		$response[0]= array('status'=>"0",'info'=>$info);
		$response = json_encode($response);
		echo $response;
	}
	
	//返回json1
	function found(){
		$response[0]= array('status'=>"1",'info'=>"成功");
		$response = json_encode($response);
		echo $response;
	}
	/*function getConnection() {
    try {
        $db_username = "root";
        $db_password = "";
        $conn = new PDO('mysql:host=localhost;dbname=book', $db_username, $db_password);
        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    } catch(PDOException $e) {
        echo 'ERROR: ' . $e->getMessage();
    }
    return $conn;
	}*/
?>