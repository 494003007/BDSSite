<%--
  Created by IntelliJ IDEA.
  User: D
  Date: 2017/2/15
  Time: 1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
</head>
<body>
    <form method="post">
        <label for="username">用户名</label>
        <input id="username" name="username" type="text">

        <label for="password">密码</label>
        <input id="password" name="password" type="password">

        <input type="submit" >
        <a href="/register">注册</a>
        ${msg}
    </form>
</body>
</html>
