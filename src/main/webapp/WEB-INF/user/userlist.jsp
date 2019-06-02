<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/6/1
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>用户id</th>
        <th>用户名称</th>
        <th>用户密码</th>
        <th>操作</th>
        <th><a href="insert">一键添加张三</a></th>
    </tr>
    <c:forEach items="${userlist}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td><a href="del?id=${user.id}">删除此用户</a></td>
            <td><a href="modify?id=${user.id}">根据id修改此用户</a></td>

        </tr>
    </c:forEach>

</table>

</body>
</html>
