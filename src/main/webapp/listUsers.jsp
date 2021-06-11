<%--
  Created by IntelliJ IDEA.
  User: liuxiangren
  Date: 2021/6/10
  Time: 10:48 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8"><meta charset="UTF-8">
    <title>Title</title>

    <script type="text/javascript" src="static/jquery-3.2.1.min.js"></script>

    <script type="text/javascript">
        $(function(){
          $('#btnLoader').click(function(){
              var uid = $('#uid').val();
              $.ajax({
                  url:"user/getUserById.do?id="+uid,
                  // url:"user/getUserById.do",
                  type:"post",
                  // contentType: "application/json;charset=UFT-8",
                  // data:{"id":uid},
                  dataType:"json",
                  success:function (response){
                      alert(response);
                      $('#info').html("");
                      $('#info').append("<tr>" +
                          "<td>"+response.id+"</td>" +
                          "<td>"+response.userName+"</td>"+
                          "<td>"+response.gender+"</td>"+
                          "<td>"+response.email+"</td>"+"</tr>")
                  },
              })


          })

        })

    </script>
</head>
<body>

    <div align="center">
        <input id="uid" type="number" value="1">
        <table>
            <thead>
                <tr>
                    <td>编号</td>
                    <td>姓名</td>
                    <td>性别</td>
                    <td>邮箱</td>
                </tr>
            </thead>

            <tbody id="info">


            </tbody>

        </table>
        <input type="button" id="btnLoader" value="加载">

    </div>


</body>
</html>
