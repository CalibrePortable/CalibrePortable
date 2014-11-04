##  图书管理系统API定义 
手机后端REST API

#### 普通用户

1.登录
>/normalUser/login

**请求**
>Method:POST

**参数**
>userId: int
password: String(填写)

`Response`
>{
    status:RESULT_CONSTANT 
}

---



2.注册
>/normalUser/register 

**请求**
>Method:POST

**参数**
>userId：int
userName：String
password：String
rePassword:String (填写)

`Response`
>{
    status:RESULT_CONSTANT 
}

---


3.扫一扫借书
>/normalUser/borrow/:bookId/:userId/:password 

**请求**

>Method：POST

**参数**
>bookId：int
userId：int(获取)
password：String

`Response`
>{
    status:RESULT_CONSTANT 
}

---



5.图书搜索
>/search/:type/page=:page/:keyword

>Method:GET

**参数**
>type:int(12345)对应(书名出版社作者种类全部)
page:int
keyword:String (获取)

`Response`
>{
status: RESULT_CONSTANT,
books:[
{"book_name":书本名称,
"book_author":书本作者,
"book_type":书本类型,
"book_info":书本信息,
"book_price:"书本价格,
"book_status":书本状态,
"favour":点赞数},
{
    ...
},...]
}


---


6.已借阅
>normalUser/return/:userId/:password

**请求**
>Method:GET

**参数**
>userId:int
password:String(获取)


`Response`
>{
status: RESULT_CONSTANT,
books:[
{"book_name":书本名称,
"book_author":书本作者,
"book_type":书本类型,
"book_info":书本信息,
"book_price:"书本价格，
"book_status":书本状态,
"favour":点赞数
},{
    ...
},...]
}



####图书管理员

1.登陆

>/Administrator/login

**请求**
Method:POST

**参数**
>userId: int
password: String(填写)

`Response`
>{
    status:RESULT_CONSTANT 
}

---


2.图书列表(列表与搜索功能合并) 
>/searchA/:type/page=:page/:keyword

请求
>Method：GET

参数
>type:int(12345)对应(书名出版社作者种类全部)
page:int
keyword:String (获取)

`Response`
>{
status: RESULT_CONSTANT,
books:[
{"book_name":书本名称,
"book_author":书本作者,
"book_type":书本类型,
"book_info":书本信息,
"book_price:"书本价格，
"book_status":书本状态,
"favour":点赞数
},{
    ...
},...]
}


3.修改图书信息
>/booklist/book/update/:userId/:bookId 

请求
>POST

参数
>password：String
 book_name:String
 book_author:String
 book_type:String
 book_info:String
 book_status:String(填写)
 

`Response `
>{
    status:RESULT_CONSTANT
}

---

4.归还图书

>/administrator/returnConfirm/:bookId/:userId/:password 

请求
>GET

参数
>bookId:int
userId:int(获取)
password:String


---

5.添加图书
>/administrator/addBook/:userId/:password

请求
>POST

参数
>图书属性
userId：int
password:String(获取)

---

6.删除图书

>/administrator/deleteBook/:bookId/:userId/:password

请求
>POST

参数
>bookId:int
userId：int(获取)
password:String

---


7.查看已经借出的图书
>administrator/return/:userId/:password

**请求**
>Method:GET

**参数**
>userId:String
password:String(获取)


`Response`
>{
status: RESULT_CONSTANT,
books:[
{"book_name":书本名称,
"book_author":书本作者,
"book_type":书本类型,
"book_info":书本信息,
"book_price:"书本价格，
"book_status":书本状态,
"favour":点赞数
},{
    ...
},...]
}
