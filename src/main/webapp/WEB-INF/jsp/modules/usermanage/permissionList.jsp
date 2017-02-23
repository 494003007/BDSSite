<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: D
  Date: 2017/2/20
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <tr>
            <td>权限id</td>
            <td>权限名</td>
            <td>权限字符串</td>
            <td>链接</td>
            <td>类型</td>
            <td>操作</td>
        </tr>
        <c:forEach var="permission" items="${permissions}">
        <tr>
            <td>${permission.id}</td>
            <td>${permission.name}</td>
            <td>${permission.permission}</td>
            <td>${permission.url}</td>
            <td>${permission.resourceType}</td>
            <td><a href="/AuthorizationManage/permissionDelete?id=${permission.id}">删除</a></td>
            <td><a href="/AuthorizationManage/permissionView?id=${permission.id}">查看</a></td>
        </tr>
        </c:forEach>
    </table>

</body>
</html>
