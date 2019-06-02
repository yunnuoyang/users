<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/6/2
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="doupdate">
    用户ID<input  name="id" value="${curUser.id}" hidden="hidden"><br>
    用户姓名 <input name="username" value="${curUser.username}"><br>
    用户密码<input name="password" value="${curUser.password}"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
