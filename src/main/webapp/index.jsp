<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title of the document</title>
</head>
<body>
<h2>Hello World!</h2>

<a href="hello">Hello World</a><br>

<a href="springmvc/testRm">Hello World</a><br>

<a href="springmvc/testRM2">Hello World</a><br>

<form action="springmvc/testRM2" method="post">
    <input type="submit" value="submit">
</form><br>


<a href="springmvc/testRM3?username=SennerMing&age=11">username=SennerMing&age=11</a><br>
<a href="springmvc/testRM3?username=SennerMing">username=SennerMing</a><br>
<a href="springmvc/testRM3?age=11">age=11</a><br>


<a href="springmvc/testRM4">testRM4</a><br>
<a href="springmvc/testRm5?name=SennerMing&age=12">testRM5</a><br>



<a href="ant/testAnt/xxxxx/abc">testAnt</a><br>

<a href="pathVariable/delete/1">testPathVariable-delete</a><br>

<!-- REST 风格开始 -->

<form action="rest/testDelete/1" method="post">
    <input type="text" name="_method" value="DELETE">
    <input type="submit" value="TEST REST DELETE">
</form><br>

<a href="rest/testGet/1">TEST REST GET</a><br>


<form action="rest/testPut/3" method="post">
    <input type="text" name="_method" value="PUT">
    <input type="submit" value="TEST REST PUT">
</form><br>

<form action="rest/testPost" method="post">
    <input type="text" name="_method" value="POST">
    <input type="submit" value="TEST REST POST">
</form><br>

<!-- REST 风格结束 -->

<!-- 测试 RequestParam -->
<a href="requestParam/testRp1?name=SennerMing&age=10">测试RequestParam</a>
<br>

<!-- 测试 RequestHeader -->
<a href="requestHeader/testRh1">测试RequestHeaders</a>
<br>


<!-- 测试 CookieValue -->
<a href="cookieValue/testCv1">测试CookieValue</a>
<br>

<!-- 测试POJO的属性值绑定 -->

<form action="pojoBind/testPb1" method="post">
    <input type="text" name="_method" value="POST">
    <input type="text" name="userName" value="SennerMing">
    <input type="text" name="password" value="123456">
    <input type="text" name="email" value="SennerMing@musician.club.com">
    <input type="text" name="age" value="18">
    <input type="text" name="address.province" value="JiangSu">
    <input type="text" name="address.city" value="NanJing">

    <input type="submit" value="测试POJO BIND">
</form><br>


<!-- 测试ServletApi -->
<a href="servletApi/testApi1">HttpServletRequest,HttpServletResponse</a>
<br>

<!-- 测试ModelAndView -->
<a href="modelAndView/testMv1">测试ModelAndView</a>
<br>

<!-- 测试Map -->
<a href="mapAndModel/testMap1">测试Map</a>
<br>


<!-- 测试Map -->
<a href="sessionAttributes/testSession1">测试SessionAttribute</a>
<br>


<!-- 测试静态资源 -->
<img src="image/imgTest.jpg" alt="测试图片" height="435" width="600"/>


<table>
    <tr>
        <td><a href="addUser.jsp">学生注册</a></td>
    </tr>
    <tr>
        <td><a href="listUsers.jsp">学生浏览</a> </td>
    </tr>
</table>
<hr>


<!-- 转发与重定向 -->
<form action="forwardRedirect/forward.do">
    姓名：<input type="text" name="userName"><br>
    性别：<input type="text" name="gender"><br>
    <input type="submit" value="forward提交">
</form>
<hr>

<!-- 转发与重定向 -->
<form action="forwardRedirect/redirect.do">
    姓名：<input type="text" name="userName"><br>
    性别：<input type="number" name="gender"><br>
    <input type="submit" value="redirect提交">
</form>

<hr>

<!-- 异常处理器测试 -->
<form action="exception/testException.do">
    姓名：<input type="text" name="userName"><br>
    性别：<input type="number" name="gender"><br>
    <input type="submit" value="exception handler">
</form>


<!-- 拦截器测试 -->
<form action="interceptor/testInterceptor.do">
    姓名：<input type="text" name="userName"><br>
    性别：<input type="number" name="gender"><br>
    <input type="submit" value="interceptor handler">
</form>

</body>
</html>
