<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色分配</title>
</head>
<body>
<c:forEach items="${userInfoList}" var="userInfo">
    <p> ${userInfo.username}
        <c:forEach items="${userInfo.roleList}" var="role">
        ${role.role}

    </c:forEach></p>
    <a href="/userRoleRelationEdit/${userInfo.username}">修改</a>
</c:forEach>
</body>
</html>