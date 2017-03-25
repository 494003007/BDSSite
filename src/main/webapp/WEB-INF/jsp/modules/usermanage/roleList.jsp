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
            <td>角色id</td>
            <td>角色名</td>
            <td>角色描述</td>
            <td>角色状态</td>

            <td>操作</td>
        </tr>
        <c:forEach var="role" items="${roles}">
        <tr>
            <td>${role.id}</td>
            <td>${role.role}</td>
            <td>${role.description}</td>
            <td>${role.available}</td>
            <td><a href="/AuthorizationManage/roleDelete?id=${role.id}">删除</a>
                <a href="/AuthorizationManage/roleView?id=${role.id}">查看</a>
            </td>
        </tr>
        </c:forEach>
    </table>

</body>
</html>
