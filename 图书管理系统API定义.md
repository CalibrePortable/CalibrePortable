##  图书管理系统API定义 
手机后端REST API

当前域名：http://www.flappyant.com/book/

#### 公共
1.修改密码
>API.php/passChange/:userId/:oldPass/:newPass/:renewPass

**请求**

>Method:GET

**用例**

>12108413
>12345

**参数**

>userId:String
>>oldPass:String
>>>newPass:String
>>>>renewPass:String(获取)

`Response`
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



2.点赞
>API.php/like/:bookId/:userId/:password

**请求**

>Method:GET

**用例**

>12
>12108413
>12345

**参数**

>bookId:String
>>userId:String
>>>password:String(获取)

`Response`
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



3.书本总数
>API.php/bookSum/:flag

**请求**

>Method:GET

**用例**

>API.php/bookSum/0

**参数**

>flag:String(获取)
>0为用户1为管理员

`Response`
>{
    "sum":COUNT
}

#### 普通用户

1.登录
>API.php/normalUser/login

**请求**

>Method:POST

**用例**

>12108413
>12345

**参数**

>userId: String
>>password: String(填写)

`Response`
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



2.注册
>API.php/normalUser/register 

**请求**

>Method:POST

**用例**

>12108414
>黄可庆

**参数**

>userId：String
>>userName：String
>>>password：String
>>>>rePassword:String (填写)

`Response`
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



3.扫一扫借书
>API.php/normalUser/borrow/:bookId/:userId/:password 

**请求**

>Method：GET

**用例**

>API.php/normalUser/borrow/1/12108413/12345

**参数**

>bookId：String
>>userId：String
>>>password：String(获取)

`Response`
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



4.图书搜索/获取图书列表
>API.php/search/:userId/:type/page=:page/:keyword

**请求**

>Method:GET

**用例**

>API.php/search/12108413/1/page=1/php

>API.php/search/12108413/5/page=1/all

**参数**

>userId:String
>>type:int(12345)对应(书名 出版社 作者 种类 全部)
>>>page:int
>>>>keyword:String (获取)

`Response`
>[
{"book_id":书本id,
"book_name":书本名称,
"book_author":书本作者,
"book_type":书本类型,
"book_info":书本出版社,
"book_price":书本价格,
"book_status":书本状态,
"favour":点赞数,
"book_pic":图书图片,
"isLike":是否被赞},
{
    ...
},...
}]


---


5.已借阅
>API.php/normalUser/return/:userId/:password

**请求**

>Method:GET

**用例**

>API.php/normalUser/return/12108413/12345

**参数**

>userId:String
>>password:String(获取)


`Response`
>[
{"book_id":书本id,
"book_name":书本名称,
"book_author":书本作者,
"book_type":书本类型,
"book_info":书本出版社,
"book_price":书本价格,
"book_status":书本状态,
"created_at":借阅时间,
"return_at":剩余天数,
"favour":点赞数,
"book_pic":图书图片,
"isLike":是否被赞},
{
    ...
},...
}]



####图书管理员

1.登陆
>API.php/administrator/login

**请求**

Method:POST

**用例**

>12108238
>123

**参数**

>userId: String
>>password: String(填写)

`Response`
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



2.图书搜索/获取图书列表
>API.php/searchA/:userId/:type/page=:page/:keyword

**请求**

Method:GET

**用例**

>API.php/searchA/12108238/1/page=1/php

>API.php/searchA/12108238/5/page=1/all

**参数**

>userId:String
>>type:int(12345)对应(书名 出版社 作者 种类 全部)
>>>page:int
>>>>keyword:String (获取)

`Response`
>[
{"book_id":书本id,
"book_name":书本名称,
"book_author":书本作者,
"book_type":书本类型,
"book_info":书本出版社,
"book_price":书本价格,
"book_status":书本状态,
"favour":点赞数,
"book_pic":图书图片,
"isLike":是否被赞},
{
    ...
},...
}]

---



3.修改图书信息
>API.php/booklist/book/update/:userId/:bookId/:password

***请求***

>Method:POST

***用例***

>API.php/booklist/book/update/12108238/100/123

***参数***

>bookName:String
>>bookAuthor:String
>>>bookType:String
>>>>bookInfo:String
>>>>>bookStatus:String(填写)
>>>>>>userId:String
>>>>>>>bookId:String
>>>>>>>>password:String(获取)

`Response `
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



4.归还图书
>API.php/administrator/returnConfirm/:bookId/:userId/:password 

***请求***

>Method:GET

***用例***

>API.php/administrator/returnConfirm/47/12108238/123

***参数***

>bookId:String
>>userId:String
>>>password:String(获取)

`Response `
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



5.添加图书(仅用于捐赠的图书添加，购买的图书通过购买系统添加)
>API.php/administrator/addBook/:userId/:password

***请求***

>Method:POST

***用例***

>API.php/administrator/addBook/12108238/123

***参数***

>bookName:String
>>bookAuthor:String
>>>bookType:String
>>>>actId:String
>>>>>bookInfo:String
>>>>>>bookPrice:String
>>>>>>>bookPic:String(填写)
>>>>>>>>userId：String
>>>>>>>>>password:String(获取)

`Response `
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



6.删除图书

>API.php/administrator/deleteBook/:bookId/:userId/:password

***请求***

>GET

***用例***

>API.php/administrator/deleteBook/100/12108238/123

***参数***

>bookId:String
>>userId：String
>>>password:String(获取)

`Response `
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



7.查看已经借出的图书
>API.php/administrator/return/:userId/:password/page=:page

***请求***

>Method:GET

***用例***

API.php/administrator/return/12108238/123/page=1

***参数***

>userId:String
>>password:String
>>>page:int(获取)

`Response`
>[
{"book_id":书本id,
"book_name":书本名称,
"book_author":书本作者,
"book_type":书本类型,
"book_info":书本信息,
"book_price":书本价格,
"book_status":书本状态,
"user_name":借阅人,
"created_at":借阅时间,
"return_at"剩余天数:,
"favour":点赞数,
"book_pic":图书图片,
"isLike":是否被赞},
{
    ...
},...
}]
