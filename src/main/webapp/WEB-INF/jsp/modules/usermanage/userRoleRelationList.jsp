<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色分配</title>
</head>
<body>
    <table>
        <tr>
            <td>用户名</td>
            <td>用户角色</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${userInfoList}" var="userInfo">
            <tr>
                <td>${userInfo.username}</td>
                <td><c:forEach items="${userInfo.roleList}" var="role">
                        ${role.role}
                    </c:forEach>
                </td>
                <td><a href="/userRoleRelationEdit/${userInfo.username}">修改</a></td>
            </tr>
        </c:forEach>

    </table>

</body>
</html>