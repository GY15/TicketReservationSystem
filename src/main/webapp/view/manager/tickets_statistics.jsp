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
            <li><a href="/manage/venue_statistics">场馆统计</a></li>
            <li><a href="/manage/member_statistics">会员统计</a></li>
            <li class="active"><a href="/manage/tickets_statistics">公司营收</a></li>
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
                title: "日期",
                field: "settleTime",
                valign: "middle",//垂直
                formatter: dateFormatter,
                sortable: true,//是否可排序
                order: "desc"//默认排序方式
            },
            {
                title: "结算人",//标题
                field: "manager",//键名
            },
            {
                title: "场馆",
                field: "venueid",
                sortable: true,//是否可排序
                order: "desc"//默认排序方式
            },
            {
                title: "结算比例",
                field: "rate",
                sortable: true,
                order: "desc"//默认排序方式
            },
            {
                field: "profit",
                title: "公司盈利",
                sortable: true,
                order: "desc"//默认排序方式
            }];


        Date.prototype.format = function(fmt) {
            var o = {
                "M+" : this.getMonth()+1,                 //月份
                "d+" : this.getDate(),                    //日
                "h+" : this.getHours(),                   //小时
                "m+" : this.getMinutes(),                 //分
                "s+" : this.getSeconds(),                 //秒
                "q+" : Math.floor((this.getMonth()+3)/3), //季度
                "S"  : this.getMilliseconds()             //毫秒
            };
            if(/(y+)/.test(fmt)) {
                fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
            }
            for(var k in o) {
                if(new RegExp("("+ k +")").test(fmt)){
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
                }
            }
            return fmt;
        }
        function dateFormatter(value, row, index) {
            var date = new Date(value);
            return [
                date.format('yyyy-MM-dd hh:mm:ss')
            ].join('');
        }


        var datas = JSON.parse('${records}');
        initTable(columns,datas)

    </script>

</body>
</html>
