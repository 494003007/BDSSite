<%@ include file="/WEB-INF/jsp/baseFile/tagImport.jsp"%>
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
    <a href="/AuthorizationManage/roleList">返回列表</a>
    <form:form action="/AuthorizationManage/roleUpdate" modelAttribute="role" method="post">
        <input value="${role.id}" type="hidden" name="id">
        <label for="role">名称</label> <input value="${role.role}" type="text" id="role" name="role">
        <label for="description">描述</label> <input value="${role.description}" type="text" id="description" name="description">
        <label for="available">是否可用</label> <input value="${role.available}" type="text" >
        <select id="available" name="available">
            <option value="false">不可用</option>

            <option value="true">可用</option>

        </select>
        <label for="resourceType">类型</label> <input value="${role.resourceType}" type="text" id="resourceType" name="resourceType">
        <input type="submit">



    </form:form>
</body>
</html>
