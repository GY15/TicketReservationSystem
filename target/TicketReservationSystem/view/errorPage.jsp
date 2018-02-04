<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/12/28
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div style="height: 120px;text-align: center">
    <div>
        <h1>sorry,<%=request.getAttribute("errorMessage")%></h1>
    </div>
        <form class="form-horizontal" action="/logout" method="get" role="form">
            <button type="submit">注销</button>
        </form>
</div>


</body>
</html>
