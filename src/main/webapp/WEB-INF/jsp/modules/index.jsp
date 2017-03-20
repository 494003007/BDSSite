<%@ page import="com.recordme.modules.common.Tool" %><%--
  Created by IntelliJ IDEA.
  User: D
  Date: 2017/2/23
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
    Hello! ${Tool.getUser().getName()}

    <a href="/login">登陆</a>
    <a href="user/list">用户管理</a>
    <a href="AuthorizationManage/permissionList">权限管理</a>
    <a href="AuthorizationManage/roleList">角色管理</a>

</body>
</html>
