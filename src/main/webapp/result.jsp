<%--
  Created by IntelliJ IDEA.
  User: liuxiangren
  Date: 2021/6/10
  Time: 10:19 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>外部结果通知</title>
</head>
<body>
   注册结果通知：${tips}
   注册结果通知姓名：${userName}
   注册结果通知性别：${gender}

   注册结果通知姓名 request.getParameter("姓名")：<%=request.getParameter("userName")%>
   注册结果通知性别 request.getParameter("性别")：<%=request.getParameter("gender")%>>
</body>
</html>
