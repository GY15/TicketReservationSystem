<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/12/28
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="order" uri="/WEB-INF/tlds/orderInfo.tld" %>
<%@ taglib prefix="login" uri="/WEB-INF/tlds/login.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <login:checkSession/>
    <title>Order</title>
</head>
<body style="text-align: center">
<div style="margin-left: 37%">
    <table border="1" style="text-align: center">
        <tbody>
        <tr>
            <th>订单编号</th>
            <th>物品名称</th>
            <th>数量</th>
            <th>价格</th>
            <th>订单时间</th>
            <th>缺货</th>
        </tr>

        <%--<c:if test="${sessionScope.userID != null}">--%>
            <order:orderInfo/>
        <%--</c:if>--%>
        </tbody>
    </table>
</div>
<form class="form-horizontal" action="/logout" method="get" role="form">
    <button type="submit">注销</button>
</form>

<div class="pages" style="margin-top:30px">
    <%=request.getAttribute("buttonField")%>
</div>
<div>当前页码<label><%=request.getAttribute("curPage")%></label></div>
<div>总人数：<%=request.getAttribute("allCounter")%></div>
<div>登陆人数：<%=request.getAttribute("loginCounter")%></div>

<script type="text/javascript">
    function gotoPage() {
        window.location.href="<%=request.getContextPath()%>/order?curPage="+event.target.id;
    }
</script>

</body>
</html>
