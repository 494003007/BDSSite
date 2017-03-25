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
            <td>用户ID</td>
            <td>用户名</td>
            <td>状态</td>
            <td>操作</td>
        </tr>
        <c:forEach var="user" items="${userInfo}">
        <tr>
            <td>${user.uid}</td>
            <td>${user.name}</td>
            <td>${user.state}</td>
            <td><a href="/user/view?uid=${user.uid}">查看</a></td>
        </tr>
        </c:forEach>
    </table>

</body>
</html>
