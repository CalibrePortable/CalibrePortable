##  图书管理系统API定义 
手机后端REST API

#### 普通用户

1.登陆
>/normalUser/login

**请求**：
>Method:POST

**参数**
>userName: String
password: String

`Response`:
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

`Response`:
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

`Response`:
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

`Response`:
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

`Response`:
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


`Response`:
>{
status: RESULT_CONSTANT,
books:[{
},{
    。。。
},。。。]
}















