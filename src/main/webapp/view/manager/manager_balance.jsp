<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>场馆结算</title>

    <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link href="../../css/flat/green.css" rel="stylesheet">
    <link href="../../css/font-awesome.min.css" rel="stylesheet">
    <link href="../../css/manager/manager-balance.css" rel="stylesheet">
    <link href="../../css/button.css" rel="stylesheet">
    <link href="../../css/bootstrap-select.css" rel="stylesheet">
    <link href="../../css/bootstrap-table.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <jsp:include page="manager_navigation.jsp"></jsp:include>
    <div class="row">
        <div class="col-md-offset-8"><h4>本经理余额：${manager.balance}</h4></div>
        <table id="my_table" ></table>
        <%--<c:choose>--%>
            <%--<c:when test="${venues!=null}">--%>
                <%--<c:forEach items="${venues}" var="venue" varStatus="vs">--%>
                    <%--<div class="venueBlock">--%>
                        <%--<div class="row">--%>
                            <%--<div type="text" class="col-md-2 col-md-offset-1">账号:<span class="venueid">${venue.venueid}</span></div>--%>
                            <%--<div type="text" class="col-md-2 venueid">场馆名称:${venue.name}</div>--%>
                            <%--<div class="col-md-4">地址:${venue.province}&nbsp;${venue.city}&nbsp;${venue.location}</div>--%>
                            <%--<div class="col-md-2">待结算:<span class="unliquidated">${venue.unliquidated}</span></div>--%>
                        <%--</div>--%>
                        <%--<div class="row">--%>
                            <%--<div class=" col-md-offset-7 col-md-1" style="margin-top: 10px" align="right">结算比例</div><div class="col-md-1"><input class="form-control rate"/></div>--%>
                            <%--<button class=" btn btn-sm btn-info settle-btn"--%>
                                    <%--style="margin-top: 5px">结算--%>
                            <%--</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</c:forEach>--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>
            <%--</c:otherwise>--%>
        <%--</c:choose>--%>
    </div>

</div>

<div class="modal fade" id="settleModal" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog" style="margin-top: 40px;height:300px;width: 25%">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times</button>
                <h4 class="modal-title" style="margin-top: 0px">场馆结算</h4>
            </div>
            <div class="modal-body" style="margin-top: 70px;margin-bottom: 100px">
                <div class="row">
                    <div class="col-md-4 col-md-offset-1">场馆:<span id="venueid"></span></div>
                </div>
                <div class="row  " style="margin-top: 25px">
                    <div class="col-md-5 col-md-offset-1">可结算金额：<span id="money"></span></span></div>
                </div>
                <div class="row  " style="margin-top: 20px;margin-bottom: 10px">
                    <div class="col-md-offset-1 col-md-3 form-label-sm" style="color: black">结算比例:</div>
                    <div class="col-md-3"><input type="text" class="form-control" id="rate"></div>
                </div>
                <div class="row" style="margin-bottom: 10px">
                    <div class="col-md-1 col-md-offset-4">
                        <button class="btn btn-primary settle-btn">确认结算</button>
                    </div>
                </div>
                <div class="errorMessage col-md-offset-3"></div>
            </div>
        </div>
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

<script src="../../js/bootstrap-table-zh-CN.js"></script>
<script src="../../js/bootstrap-table.js"></script>
<script src="../../js/bootstrap-table-editable.js"></script>
<script src="../../js/table.js"></script>

<script>
//    window.settle_balance = {'click #settle': function (e, value, row, index) {
//        alert(123)
//    }};
//    var columns =[
//        {
//            title: "场馆id",
//            field: "venueid",
//            valign: "middle"//垂直
//        },
//        {
//            title: "场馆名称",//标题
//            field: "name",//键名
//        },
//        {
//            title: "省份",
//            field: "province",
//            sortable: true,
//            sortable: true,//是否可排序
//            order: "desc"//默认排序方式
//        },,
//        {
//            title: "城市",
//            field: "city",
//            sortable: true,
//            order: "desc"//默认排序方式
//        },    {
//            title: "地址",
//            field: "location",
//        },
//        {
//            field: "unliquidated",
//            title: "未结算",
//            titleTooltip: "场馆还有多少钱未结算",
//            sortable: true,
//            order: "desc"//默认排序方式
//        },
////        {
////            field: 'rate',
////            title: '结算比例',
////            width: '120px',
////            titleTooltip: "付给场馆的金额比例（0-1）",
////            formatter: inputFormatter
////        },
//       ];
//
//    function operateFormatter(value, row, index) {
//        return [
//            '<button class="btn btn-sm btn-primary" id="settle" type="button">结算</button>',
//        ].join('');
//    };
//    function inputFormatter(value, row, index) {
//        return [
//            '<text class="form-control" class="rate">1</text>',
//        ].join('');
//    }



    <%--var datas = JSON.parse('${venues}');--%>
//    initTable(columns,datas);

    $(".settle-btn").bind("click", function () {
        var rate =$("#rate").val();
        if(rate > 0 && rate <= 1){
            $.ajax({
                type: "post",
                async: true,
                url: "/manage/settle_balance",
                data: {
                    "venueid": $("#venueid").html(),
                    "rate": $("#rate").val()
                },
                success: function (result) {
                    window.location.reload();
                },
                error: function (result) {
                    window.location.reload();
                }
            });
        }else{
            alert("结算比例的范围在0-1之间")
        }
    });
    window.settle_balance = {'click #settle': function (e, value, row, index) {
        $("#settleModal").modal();
        $("#money").html(row.unliquidated);
        $("#venueid").html(row.venueid);
        $("#rate").val(1);
    }};

var columns =[
    {
        title: "场馆id",
        field: "venueid",
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
            field: "unliquidated",
            title: "未结算",
            titleTooltip: "场馆还有多少钱未结算",
            sortable: true,
            order: "desc"//默认排序方式
        },
    {
        field: 'operate',
        title: '操作',
        width: '80px',
        events: settle_balance,
        formatter: operateFormatter
    }];

    function operateFormatter(value, row, index) {
        return [
            '<button class="btn btn-sm btn-primary" id="settle" type="button">结算</button>',
        ].join('');
    };

    var datas = JSON.parse('${venues}');
    initTable(columns,datas);

</script>

</body>
</html>
