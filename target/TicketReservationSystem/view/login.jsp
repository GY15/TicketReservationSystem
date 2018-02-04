<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/12/28
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>login</title>
</head>
<body>
<h2>登录</h2>
<form class="form-horizontal" action="/login" method="post" role="form">
    <label>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户</label>
    <div>
        <input type="text" name='id' id="id" value ="<%=request.getAttribute("login")%>" placeholder="请输入用户名">
    </div>

    <label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
    <div>
        <input type="password" name="password" id="password" placeholder="请输入密码">
    </div>
    <input type="checkbox" name ='remember' id ='remember' value = 'on'>记住账号
    <div>
        <button type="submit">登录</button>
    </div>
    <div>总人数：<%=request.getAttribute("allCounter")%></div>
    <div>登陆人数：<%=request.getAttribute("loginCounter")%></div></form>
</body>
</html>
