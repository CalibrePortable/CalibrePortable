##  图书管理系统API定义 
手机后端REST API

#### 普通用户

**登陆**：
>/normaluser/login

**请求**：
>Method:POST
userName: String
password: String

`Response`:
>{
    status:RESULT_CONSTANT 
}

---



**注册**：
>/normaluser/register 

>Method:POST
userName：String
password：String
rePassword:String 

`Response`:
>{
    status:RESULT_CONSTANT 
}

---


**扫一扫借书**：
>/normaluser/borrow

>Method：POST
_bookId：String
password：String
rePassword:String 

`Response`:
>{
    status:RESULT_CONSTANT 
}

---

**获取图书列表**：
>/booklist?page=xx

>Method:GET
page:int

`Response`:
>{
status: RESULT_CONSTANT,
books:[{
},{
    。。。
},。。。]
}