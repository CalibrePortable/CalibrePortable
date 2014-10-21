##  图书管理系统API定义 
手机后端REST API

#### 普通用户

1.登
>/normalUser/login

**请求**
>Method:POST

**参数**
>userName: String
password: String

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
>userName：String
password：String
rePassword:String 

`Response`
>{
    status:RESULT_CONSTANT 
}

---


3.扫一扫借书
>/normalUser/borrow

**请求**

>Method：POST

**参数**
>_bookId：String
password：String
rePassword:String 

`Response`
>{
    status:RESULT_CONSTANT 
}

---

4.获取图书列表
>/booklist?page=xx

**请求**
>Method:GET

**参数**
>page:int

`Response`
>{
status: RESULT_CONSTANT,
books:[{
},{
    。。。
},。。。]
}


5.图书搜索
>/search?keyword=xxx

>Method:GET

**参数**
>keyword:String 

`Response`
>{
    status:RESULT_CONSTANT 
}


---


6.已借阅
>normalUser/return?bookId=xxx&&userName=xxx&&password&&=xxx

**请求**
>Method:GET

**参数**
>bookId:String
userName:String
password:String


`Response`
>{
status: RESULT_CONSTANT,
books:[{
},{
    。。。
},。。。]
}



####图书管理员

1.登陆

>/Administrator/login

**请求**
Method:GET

**参数**
>userName: String
password: String

`Response`
>{
    status:RESULT_CONSTANT 
}

---


2.图书列表
>/booklist?page=xx

请求
>Method：GET

参数
>page:int

`Response`
>{
status: RESULT_CONSTANT,
books:[{
},{
    。。。
},。。。]
}


3.修改图书信息
>/booklist/book/update

请求
>POST

参数
>图书属性（还没有想好）
userName：String
password：String


`Response `
>{
    status:RESULT_CONSTANT
}

---

4.归还图书

>/administrator/returnConfirm?bookId=xxx&&userName=xxx&&password=xxx

请求
>

参数
>


---


5.添加图书
>/administrator/addBook

请求
>POST

参数
>图书属性
userName：userName
password:password

---

6.删除图书

>/administrator/deleteBook?bookId=xxx&&userName&&password=xxx

请求
>POST

参数
>bookId:
userName：userName
password:password

---




























