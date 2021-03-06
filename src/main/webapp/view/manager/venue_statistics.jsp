<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>统计信息</title>

    <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link href="../../css/flat/green.css" rel="stylesheet">
    <link href="../../css/font-awesome.min.css" rel="stylesheet">
    <link href="../../css/bootstrap-table.css" rel="stylesheet">
    <link href="../../css/button.css" rel="stylesheet">
    <link href="../../css/bootstrap-select.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <jsp:include page="manager_navigation.jsp"></jsp:include>
    <div>
        <ul class="nav nav-tabs">
            <li class="active"><a href="/manage/venue_statistics">场馆统计</a></li>
            <li><a href="/manage/member_statistics">会员统计</a></li>
            <li><a href="/manage/tickets_statistics">公司营收</a></li>
        </ul>
    </div>
    <div>
        <table id="my_table" ></table>
    </div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../../js/jquery-3.2.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../../js/bootstrap.js"></script>
    <script src="../../js/format-valid.js"></script>
    <script src="../../js/icheck.js"></script>

    <!--<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>-->
    <script src="../../js/jquery.seat-charts.js"></script>
    <script src="../../js/bootstrap-select.js"></script>
    <script src="../../js/bootstrap-table-zh-CN.js"></script>
    <script src="../../js/bootstrap-table.js"></script>
    <script src="../../js/bootstrap-table-editable.js"></script>
    <script src="../../js/icheck.js"></script>
    <script src="../../js/table.js"></script>

    <script>
        var columns =[
            {
                title: "场馆id",
                field: "venueid",
                valign: "middle"//垂直
            },
            {
                title: "场馆名称",//标题
                field: "name",//键名
            },
            {
                title: "省份",
                field: "province",
                sortable: true,//是否可排序
                order: "desc"//默认排序方式
            },
            {
                title: "城市",
                field: "city",
                sortable: true,
                order: "desc"//默认排序方式
            },
            {
                field: "unliquidated",
                title: "未结算",
                titleTooltip: "场馆还有多少钱未结算",
                sortable: true,
                order: "desc"//默认排序方式
            }, {
                field: "balance",
                title: "场馆盈利",
                titleTooltip: "场馆获取的实际利润",
                sortable: true,
                order: "desc"//默认排序方式
            },{
                field: "earning",
                title: "场馆卖出",
                titleTooltip: "场馆卖出的票钱",
                sortable: true,
                order: "desc"//默认排序方式
            }];
        var datas = JSON.parse('${venues}');
        initTable(columns,datas)
    </script>

</body>
</html>
