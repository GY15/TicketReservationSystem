<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>审核场馆</title>

    <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link href="../../css/flat/green.css" rel="stylesheet">
    <link rel="stylesheet" href="../../css/font-awesome.min.css">
    <link href="../../css/manager/manager-verify.css" rel="stylesheet">
    <link href="../../css/button.css" rel="stylesheet">
    <link href="../../css/bootstrap-select.css" rel="stylesheet">
    <link href="../../css/bootstrap-table.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <jsp:include page="manager_navigation.jsp"></jsp:include>
    <div class="row">
        <table id="my_table" ></table>

    </div>

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
<script src="../../js/icheck.js"></script>
<script src="../../js/table.js"></script>

<script src="../../js/bootstrap-table-zh-CN.js"></script>
<script src="../../js/bootstrap-table.js"></script>
<script src="../../js/bootstrap-table-editable.js"></script>
<script>
    window.verify = {'click #verify': function (e, value, row, index) {
        $.ajax({
            type: "post",
            async: true,
            url: "/manage/verify_venue",
            data: {
                "venueid": row.venueid
            },
            success: function (result) {
                window.location.reload();
            },
            error: function (result) {
                window.location.reload();
            }
        });
    }};
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
        },    {
            title: "地址",
            field: "location",
        },
        {
            field: 'operate',
            title: '通过',
            width: '80px',
            events: verify,
            formatter: operateFormatter1
        }];

    function operateFormatter1(value, row, index) {
        return [
            '<button class="btn btn-sm btn-primary" id="verify" type="button">审核</button>'
        ].join('');
    }




    var datas = JSON.parse('${venues}');
    initTable(columns,datas);

</script>

</body>
</html>
