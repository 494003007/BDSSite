<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: D
  Date: 2017/2/20
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="/AuthorizationManage/permissionList">返回列表</a>
    <c:form action="/AuthorizationManage/permissionUpdate" modelAttribute="permission" method="post">
        <input value="${permission.id}" type="hidden" name="id">
        <label for="name">名称</label> <input value="${permission.name}" type="text" id="name" name="name">
        <label for="url">url</label> <input value="${permission.url}" type="text" id="url" name="url">
        <label for="permission">权限</label> <input value="${permission.permission}" type="text" id="permission" name="permission">
        <label for="resourceType">类型</label> <input value="${permission.resourceType}" type="text" id="resourceType" name="resourceType">
        <input type="submit">



    </c:form>
</body>
</html>
