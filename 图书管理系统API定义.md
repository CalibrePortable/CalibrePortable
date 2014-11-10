##  图书管理系统API定义 
手机后端REST API

当前域名：book.duanpengfei.com/

#### 普通用户

1.登录
>API.php/normalUser/login

**请求**

>Method:POST

**用例**

>12108413
>12345

**参数**

>userId: int
>>password: String(填写)

`Response`
>[{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}]

---



2.注册
>API.php/normalUser/register 

**请求**

>Method:POST

**用例**

>12108414
>黄可庆

**参数**

>userId：int
>>userName：String
>>>password：String
>>>>rePassword:String (填写)

`Response`
>[{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}]

---



3.扫一扫借书
>API.php/normalUser/borrow/:bookId/:userId/:password 

**请求**

>Method：GET

**用例**

>API.php/normalUser/borrow/1/12108413/12345

**参数**

>bookId：int
>>userId：int
>>>password：String(获取)

`Response`
>[{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}]

---



4.图书搜索/获取图书列表
>API.php/search/:type/page=:page/:keyword

**请求**

>Method:GET

**用例**

>API.php/search/1/page=1/php

>API.php/search/5/page=1/all

**参数**

>type:int(12345)对应(书名 出版社 作者 种类 全部)
>>page:int
>>>keyword:String (获取)

`Response`
>[
{"book_name":书本名称,
"book_author":书本作者,
"book_type":书本类型,
"book_info":书本出版社,
"book_price:"书本价格,
"book_status":书本状态,
"favour":点赞数,
"book_pic":图书图片},
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

>userId:int
>>password:String(获取)


`Response`
>[
{"book_name":书本名称,
"book_author":书本作者,
"book_type":书本类型,
"book_info":书本出版社,
"book_price:"书本价格,
"book_status":书本状态,
"favour":点赞数,
"book_pic":图书图片},
{
    ...
},...
}]



####图书管理员

1.登陆
>API.php/Administrator/login

**请求**

Method:POST

**用例**

>12108238
>123

**参数**

>userId: int
>>password: String(填写)

`Response`
>[{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}]

---



2.图书搜索/获取图书列表
>API.php/searchA/:type/page=:page/:keyword

**请求**

Method:GET

**用例**

>API.php/searchA/1/page=1/php

>API.php/searchA/5/page=1/all

**参数**

>type:int(12345)对应(书名 出版社 作者 种类 全部)
>>page:int
>>>keyword:String (获取)

`Response`
>[
{"book_name":书本名称,
"book_author":书本作者,
"book_type":书本类型,
"book_info":书本出版社,
"book_price:"书本价格,
"book_status":书本状态,
"favour":点赞数,
"book_pic":图书图片},
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
>>>>>>userId:int
>>>>>>>bookId:int
>>>>>>>>password:String(获取)

`Response `
>[{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}]

---



4.归还图书
>API.php/administrator/returnConfirm/:bookId/:userId/:password 

***请求***

>Method:GET

***用例***

>API.php/administrator/returnConfirm/47/12108238/123

***参数***

>bookId:int
>>userId:int
>>>password:String(获取)

`Response `
>[{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}]

---



5.添加图书
>API.php/administrator/addBook/:userId/:password

***请求***

>Method:POST

***用例***

>API.php/administrator/addBook/12108238/123

***参数***

>bookName:String
>>bookAuthor:String
>>>bookType:String
>>>>actId:int
>>>>>bookInfo:String
>>>>>>bookPrice:int(填写)
>>>>>>>userId：int
>>>>>>>>password:String(获取)

`Response `
>[{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}]

---



6.删除图书

>API.php/administrator/deleteBook/:bookId/:userId/:password

***请求***

>GET

***用例***

>API.php/administrator/deleteBook/100/12108238/123

***参数***

>bookId:int
>>userId：int
>>>password:String(获取)

`Response `
>[{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}]

---



7.查看已经借出的图书
>API.php/administrator/return/:userId/:password

***请求***

>Method:GET

***用例***

API.php/administrator/return/12108238/123

***参数***

>userId:int
>>password:String(获取)

`Response`
>[
{"book_name":书本名称,
"book_author":书本作者,
"book_type":书本类型,
"book_info":书本信息,
"book_price:"书本价格,
"book_status":书本状态,
"favour":点赞数,
"book_pic":图书图片},
{
    ...
},...
}]
