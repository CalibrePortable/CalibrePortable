##  图书管理系统API定义 
手机后端REST API
b
当前域名：http://www.flappyant.com/book/

#### 公共
1.修改密码
>API.php/public/passChange/:userId

**请求**

>Method:POST

**用例**

>API.php/public/passChange/12108413
>12108413
>12345
>12345

**参数**

>userId:String(获取)
>>oldPass:String
>>>newPass:String
>>>>renewPass:String（填写）

`Response`
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



2.点赞(bookbasic)
>API.php/public/getLike/:bookKind/:userId/:password

**请求**

>Method:GET

**用例**

>12
>12108413
>12108413

**参数**

>bookKind:String
>>userId:String
>>>password:String(获取)

`Response`
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



3.书本总数(booklist)
>API.php/public/bookSum/:flag/:type

**请求**

>Method:GET

**用例**

>API.php/public/bookSum/0/1

**参数**

>flag:String
>0为用户1为管理员
>>type:String(获取)
>>0为search 1为查看已借出

`Response`
>{
    "sum":COUNT
}

---



4.返回密码
>API.php/public/password/:userId

**请求**

>Method:GET

**用例**

>API.php/public/password/12108413

**参数**

>userId:String(获取)

`Response`
>{
    "password":PASSWORD
}

---



*5.显示图书详细（未实现）(book_kind->booklist)*
>API.php/public/detail/:bookKind/:userId/:password

**请求**

>Method:GET

**用例**

>API.php/public/detail/47

**参数**

>bookK(大写)ind:String
>>userId:String
>>>password:String(获取)

`Response`
>[
{
"book_name":书本名称,
"book_author":书本作者,
"book_pub":书本版次,
"book_type":书本类型,
"book_edit":书本出版社,
"book_price":书本价格,
"book_pic":图书图片,
"book_link":图书url,
"book_info":图书简介
"favour":点赞数,
"isLike":是否被赞},
{
"id":书本id(booklist),
"book_status":书本状态,
"user_name":借阅人,
"created_at":借阅日期
},
{
...
},...
}]

---



*6.最近添加的图书（未实现）(id)*(仿书本列表实现)
>API.php/public/recentAdd/:userId/:password

**请求**

>Method:GET

**用例**

>API.php/public/recentAdd/12108413/12108413

**参数**

>userId:String
>>password:String(获取)


`Response`
>[
{"book_kind":书本kind,
"book_name":书本名称,
"book_status":书本状态,
"favour":点赞数,
"book_pic":图书图片,
"isLike":是否被赞},
{
    ...
},...
}]
#### 普通用户

1.登录
>API.php/normal/login

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
>API.php/normal/register 

**请求**

>Method:POST

**用例**

>12108414
>黄可庆
>123
>123

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
>API.php/normal/borrow/:bookId/:userId/:password

**请求**

>Method：GET

**用例**

>API.php/normal/borrow/1/12108413/12345

**参数**

>bookId：String(booklist)
>>userId：String
>>>password：String(获取)

`Response`
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



4.图书搜索/获取图书列表
(有两种表 bookbasic【id】 booklist【id,book_kind】)
>API.php/normal/search/:userId/:type/page=:page/:keyword

**请求**

>Method:GET

**用例**

>API.php/normal/search/12108413/1/page=1/php

>API.php/normal/search/12108413/5/page=1/all

**参数**

>userId:String
>>type:int(12345)对应(书名 出版社 作者 种类 全部)
>>>page:String（若page=not则不分页）
>>>>keyword:String (获取)

`Response`
>[
{"book_kind":书本kind,
"book_name":书本名称,
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
>API.php/normal/showRe/:userId/:password

**请求**

>Method:GET

**用例**

>API.php/normal/showRe/12108413/12345

**参数**

>userId:String
>>password:String(获取)


`Response`
>[
{
"book_kind":书本kind,
"book_name":书本名称,
"book_status":书本状态,
"favour":点赞数,
"book_pic":图书图片,
"isLike":是否被赞
"created_at":借阅时间,
"return_at":剩余天数},
{
    ...
},...
}]

---



####图书管理员

1.登陆
>API.php/admin/login

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
>API.php/admin/search/:userId/:type/page=:page/:keyword

**请求**

Method:GET

**用例**

>API.php/admin/search/12108238/1/page=1/php

>API.php/admin/search/12108238/5/page=1/all

**参数**

>userId:String
>>type:int(12345)对应(书名 出版社 作者 种类 全部)
>>>page:String（若page=not则不分页）
>>>>keyword:String (获取)

`Response`
>[
{"book_kind":书本kind,
"book_name":书本名称,
"book_status":书本状态,
"favour":点赞数,
"book_pic":图书图片,
"isLike":是否被赞},
{
    ...
},...
}]

---



3.归还图书
>API.php/admin/return/:bookId/:userId/:password 

***请求***

>Method:GET

***用例***

>API.php/admin/return/47/12108238/123

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



4.修改图书信息
>API.php/admin/update/:bookKind/:userId/:password

***请求***

>Method:POST

***用例***

>API.php/admin/update/12108238/100/123

***参数***

>bookName:String
>>bookAuthor:String
>>>bookPub:String
>>>>bookType:String
>>>>bookEdit:String
>>>>>bookPrice:String
>>>>>>bookStatus:String
>>>>>>>bookPic:String
>>>>>>>>bookLink:String
>>>>>>>>>bookInfo:String(填写)
>>>>>>>>>>bookKind:String
>>>>>>>>>>>userId:String
>>>>>>>>>>>>password:String(获取)

`Response `
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



5.添加图书(仅用于捐赠的图书添加，购买的图书通过购买系统添加)
>API.php/admin/add/:bookIsbn/:userId/:password

***请求***

>Method:GET

***用例***

>API.php/admin/add/100/9787111358732/移动端/12108238/123

***参数***

>bookIsbn:String
>>bookId:String
>>>bookType:String
>>>>userId：String
>>>>>password:String(获取)

`Response `
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



6.删除图书

>API.php/admin/delete/:bookId/:userId/:password

***请求***

>GET

***用例***

>API.php/admin/delete/100/12108238/123

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
>API.php/admin/showRe/:userId/:password/page=:page

***请求***

>Method:GET

***用例***

API.php/admin/showRe/12108238/123/page=1

***参数***

>userId:String
>>password:String
>>>page:int(获取)

`Response`
>[
{"book_kind":书本kind,
"book_name":书本名称,
"book_status":书本状态,
"user_name":借阅人,
"favour":点赞数,
"book_pic":图书图片,
"isLike":是否被赞,
"created_at":借阅时间,
"return_at":剩余天数},
{
    ...
},...
}]

---



*8.查看已超期的图书(未实现)(id)*
>API.php/admin/showOut/:userId/:password

***请求***

>Method:GET

***用例***

API.php/admin/showOut/12108238/123

***参数***

>userId:String
>>password:String
>>>page:int(获取)

`Response`
>[
{"book_kind":书本kind,
"book_name":书本名称,
"book_status":书本状态,
"user_name":借阅人,
"favour":点赞数,
"book_pic":图书图片,
"isLike":是否被赞,
"created_at":借阅时间,
"return_at"剩余天数:},
{
    ...
},...
}]
