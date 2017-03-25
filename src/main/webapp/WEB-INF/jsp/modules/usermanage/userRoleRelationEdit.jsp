<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色分配</title>
</head>
<body>
用户：${userInfo.username}
角色列表：
<table>
    <tr>
        <td>角色名</td>
    </tr>
    <c:forEach items="${userInfo.roleList}" var="role">
        <tr>
            <td>${role.role}</td>
        </tr>
    </c:forEach>
</table>

<form method="post">
    <label>用户名</label>
    <input name="username" id="username"/>
    <label>添加角色</label>
    <input name="role" id="role"/>
    <input type="submit"/>
</form>
</body>
</html>