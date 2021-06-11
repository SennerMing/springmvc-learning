<%--
  Created by IntelliJ IDEA.
  User: liuxiangren
  Date: 2021/6/10
  Time: 6:04 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

退出系统，从session中删除数据
<%
    session.removeAttribute("name");
%>

</body>
</html>
