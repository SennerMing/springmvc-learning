<%--
  Created by IntelliJ IDEA.
  User: liuxiangren
  Date: 2021/6/8
  Time: 11:44 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SUCCESS PAGE</title>
</head>
<body>
    欢迎欢迎，热烈欢迎
    <br>

    time:${requestScope.time}
    <br>

    map:${requestScope.names}
    <br>

    requestUser : ${requestScope.user}
    <br>

    sessionUser : ${sessionScope.user}
    <br>

    requestSchool : ${requestScope.school}
    <br>

    sessionSchool : ${sessionScope.school}
    <br>


</body>
</html>
