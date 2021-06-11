<%--
  Created by IntelliJ IDEA.
  User: liuxiangren
  Date: 2021/6/10
  Time: 10:26 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/user/register.do" method="post">
    姓名：<input type="text" name="userName"/><br>
    性别：<input type="text" name="gender"/><br>
    电子邮箱：<input type="text" name="email"/><br>
    <input type="submit" value="注册个学生">
</form>

</body>
</html>
