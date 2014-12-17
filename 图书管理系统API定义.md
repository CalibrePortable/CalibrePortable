##  图书管理系统API定义 
手机后端REST API

当前域名：http://www.flappyant.com/book/

#### 公共
1.修改密码
>API.php/public/passChange

**请求**

>Method:POST

**用例**

>12108413
>12108413
>12345
>12345

**参数**

>userId:String(获取)
>>oldPass:String
>>>newPass:String
>>>>renewPass:String(填写)

`Response`
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



2.点赞
>API.php/public/like/:bookKind/:userId/:password

**请求**

>Method:GET

**用例**

>API.php/public/like/12/12108413/12108413

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



3.书本总数(网页端分页需要)
>API.php/public/bookSum/:IsAdmin/:type/:userId

**请求**

>Method:GET

**用例**

>API.php/public/bookSum/0/1/12108413
(0/0 0/1 1/0 1/1都已支持)

**参数**

>IsAdmin:String(0为用户1为管理员)
>>type:String(0为search 1为查看已借出)
>>>userId:String(获取)

`Response`
>{
    "sum":COUNT
}

---



4.返回密码(图书购买系统需要)
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



5.显示图书详细(不需外部访问)
>API.php/public/detail/:bookKind

**请求**

>Method:GET

**用例**

>API.php/public/detail/47

**参数**

>bookK(大写)ind:String(获取)

`Response`
>{
    "book_detail": {
        "book_name":书本名称 ,
        "book_author": 书本作者,
        "book_pub": 书本版次,
        "book_type": 书本类型,
        "book_edit": 书本出版社,
        "book_price": 书本价格,
        "book_pic": 图书图片,
        "book_link": 图书url,
        "book_info": 图书简介,
        "favour": 点赞数
    },
    "book_list": [
        {
            "book_id": 书本id,
            "book_status": 书本状态,
			"user_name":借阅人,(未实现),
			"created_at":借阅日期(未实现)
        },
        {
             ...
        },
        ...
    ]
}

---



6.最近添加的图书(可以detail)(无翻页)
>API.php/public/recentAdd/:userId

**请求**

>Method:GET

**用例**

>API.php/public/recentAdd/12108413

**参数**

>userId:String(获取)


`Response`
>[
{"book_kind":书本kind,
"book_detail_url":书本详细url,
"book_name":书本名称,
"book_author":书本作者,
"book_status":书本状态,
"favour":点赞数,
"book_pic":图书图片,
"isLike":是否被赞},
{
    ...
},...
}]

---



7.获取批次信息
>API.php/public/batch

**请求**

>Method:GET

**用例**

>API.php/public/batch

**参数**

`Response`
>{
    "batches":["0","1",...]
}

---



#### 普通用户

1.登录
>API.php/normal/login

**请求**

>Method:POST

**用例**

>12108413
>12108413

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

>API.php/normal/borrow/1/12108413/12108413

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



4.图书搜索/获取图书列表(可以detail)
(有两种表 bookbasic【id】 booklist【id,book_kind】)
>API.php/normal/search/:userId/:type/page=:page/:keyword

**请求**

>Method:GET

**用例**

>API.php/normal/search/12108413/1/page=1/php

>API.php/normal/search/12108413/5/page=1/all

**参数**

>userId:String
>>type:int(12345)对应(书名 id 作者 种类 全部)
>>>page:int(若page=0则不分页)
>>>>keyword:String (获取)

`Response`
>[
{"book_kind":书本kind,
"book_detail_url":书本详细url,
"book_name":书本名称,
"book_author":书本作者,
"book_status":书本状态,
"favour":点赞数,
"book_pic":图书图片,
"isLike":是否被赞},
{
    ...
},...
}]

---



5.已借阅(可以detail)(有翻页)
>API.php/normal/showRe/:userId/:password/page=:page

**请求**

>Method:GET

**用例**

>API.php/normal/showRe/12108413/12108413/page=1

**参数**

>userId:String
>>password:String
>>>page:int(获取)


`Response`
>[
{"cir_id":借阅活动id,
"book_kind":书本kind,
"book_detail_url":书本详细url,
"book_name":书本名称,
"book_author":书本作者,
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
>12108238

**参数**

>userId: String
>>password: String(填写)

`Response`
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



2.图书搜索/获取图书列表(可以detail)
>API.php/admin/search/:userId/:type/page=:page/:keyword

**请求**

Method:GET

**用例**

>API.php/admin/search/12108238/1/page=1/php

>API.php/admin/search/12108238/5/page=1/all

**参数**

>userId:String
>>type:int(12345)对应(书名 id 作者 种类 全部)
>>>page:int(若page=0则不分页)
>>>>keyword:String (获取)

`Response`
>[
{"book_kind":书本kind,
"book_detail_url":书本详细url,
"book_name":书本名称,
"book_author":书本作者,
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
>API.php/admin/confirm/:bookId/:userId/:password 

***请求***

>Method:GET

***用例***

>API.php/admin/confirm/47/12108238/12108238

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



4.根据批次返回该批次的图书id
>API.php/admin/getId/:actId/:userId/:password

***请求***

>Method:GET

***用例***

>API.php/admin/getId/0/12108238/12108238

***参数***

>actId:String
>>userId:String
>>>password:String(获取)

`Response `
>[
{"book_id":书本id,
"book_name":书本名称,
"boou_info":书本介绍},
{
	...
},...
}]

---



5.更新图书资料(显示在搜索出的列表中 移动端直接调取摄像头 网页端弹出输入框)
>API.php/admin/renew/:bookId/:bookIsbn/:userId/:password

***请求***

>Method:GET

***用例***

>API.php/admin/renew/47/9787111358732/12108238/12108238

***参数***

>bookId:String
>>bookIsbn:String
>>>userId:String
>>>>password:String(获取)

`Response `
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



6.添加图书(仅用于捐赠的图书添加，购买的图书通过购买系统添加)
>API.php/admin/add/:bookIsbn/:bookType/:userId/:password

***请求***

>Method:GET

***用例***

>API.php/admin/add/9787111358732/移动端/12108238/12108238

***参数***

>bookIsbn:String
>>bookType:String
>>>userId：String
>>>>password:String(获取)

`Response `
>{
    "status":RESULT_CONSTANT,
	"info":"成功/错误原因"
}

---



7.删除图书(在已借出或已超期中可以删除)

>API.php/admin/delete/:bookId/:userId/:password

***请求***

>GET

***用例***

>API.php/admin/delete/100/12108238/12108238

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



8.查看已经借出的图书(无法detail)(无法点赞)(有翻页)
>API.php/admin/showRe/:userId/:password/page=:page

***请求***

>Method:GET

***用例***

API.php/admin/showRe/12108238/12108238/page=1

***参数***

>userId:String
>>password:String
>>>page:int(获取)

`Response`
>[
{"cir_id":借阅活动id,
"book_id":书本id,
"book_name":书本名称,
"boou_author":作者,
"book_status":书本状态,
"user_name":借阅人,
"favour":点赞数,
"book_pic":图书图片,
"created_at":借阅时间,
"return_at":剩余天数},
{
    ...
},...
}]

---



