<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改场馆信息</title>

    <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../../css/jquery.seat-charts.css">
    <link rel="stylesheet" type="text/css" href="../../css/seat-chart.css">
    <link href="../../css/flat/green.css" rel="stylesheet">
    <link rel="stylesheet" href="../../css/font-awesome.min.css">
    <link href="../../css/venue.css" rel="stylesheet">
    <link href="../../css/bootstrap-table.css" rel="stylesheet">
    <link href="../../css/button.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <jsp:include page="venue_navigation.jsp"></jsp:include>
    <div class="row jumbotron banner-desc2 ">
        <div class="text-center">

            <div>
                <div class="form-horizontal">

                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label" for="reg_venue">帐&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
                        <div class="col-md-9 input-group" style="margin-left: 0px;left: 15px;">
                            <input type="text" class="form-control" name="reg_venue" value="${venue.venueid}"
                                   id="reg_venue" style="width: 210px;" disabled>
                            <b class="col-md-1 col-md-offset-2 form-label">营收</b> <b
                                class="col-md-1  form-label">${venue.balance}</b>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label" for="reg_name">场馆名称</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" value="${venue.name}" name="reg_name" id="reg_name"
                                   placeholder="输入场馆名称">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="password" name="password"
                                   value="${venue.password}"
                                   placeholder="请输入密码">
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</label>
                        <div class="col-md-1">
                            <input type="text" class="form-control" id="reg_province" value="${venue.province}"
                                   name="reg_province"
                                   placeholder="省份">
                        </div>
                        <div class="col-md-1">
                            <input type="text" class="form-control" id="reg_city" value="${venue.city}" name="reg_city"
                                   placeholder="市">
                        </div>
                        <div class="col-md-5">
                            <input type="text" class="form-control" id="reg_location" value="${venue.location}"
                                   name="reg_location"
                                   placeholder="详细地址">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label">座位情况</label>
                        <div class="col-md-8" style="margin-left: 15px">
                            <div class='seat_set'>
                                <c:choose>
                                    <c:when test="${seatMaps!=null}">
                                        <c:forEach items="${seatMaps}" var="seatMap" varStatus="vs">

                                            <div class="row seat_general">
                                                <div class="seat_local_name col-md-2">${seatMap.block}</div>
                                                <div class="seat_local_description col-md-6">
                                                        ${seatMap.description}
                                                </div>
                                                <div class="col-md-offset-2">
                                                    <button class="button button-royal  button-circle button-tiny deleteBlock">
                                                        <i class="icon-minus"></i></button>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:when>
                                </c:choose>
                            </div>
                            <button class="button button-primary button-circle button-small"
                                    style="margin: 10px"
                                    id="add_seat_btn"><i class="icon-plus"></i></button>
                        </div>
                        <button class="btn btn-primary col-md-4 col-md-offset-4 submit-btn" style="margin-top: 30px">
                            申&nbsp;&nbsp;&nbsp;请&nbsp;&nbsp;&nbsp;修&nbsp;&nbsp;&nbsp;改
                        </button>
                    </div>
                </div>
                <div class="col-md-12 errorMessage">
                </div>
            </div>
        </div>
    </div>

    <div class="row jumbotron banner-desc2" style="margin-top: 40px">
        <div class="row">
            <h3 class="col-md-offset-2">场馆统计信息</h3>
        </div>
        <div class="row col-md-offset-2 col-md-9">
            <table id="my_table"></table>
        </div>
    </div>

    <jsp:include page="venue_modify.jsp"></jsp:include>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../../js/jquery-3.2.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../../js/bootstrap.js"></script>
    <script src="../../js/format-valid.js"></script>
    <script src="../../js/icheck.js"></script>

    <!--<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>-->
    <script src="../../js/jquery.seat-charts.js"></script>
    <script src="../../js/icheck.js"></script>
    <script src="../../js/seat-create.js"></script>

    <script src="../../js/bootstrap-table-zh-CN.js"></script>
    <script src="../../js/bootstrap-table.js"></script>
    <script src="../../js/bootstrap-table-editable.js"></script>
    <script>
        $(document).ready(function () {
            var seatMaps = JSON.parse('${seatMapsJson}');
            initSeats(seatMaps);
            var datas = JSON.parse('${records}');
            var columns = [
                {
                    title: "交易时间",
                    field: "handleTime",
                    sortable: true,//是否可排序
                    order: "desc"//默认排序方式
                },
                {
                    title: "场馆",
                    field: "venueid",
                    sortable: true,//是否可排序
                    order: "desc"//默认排序方式
                },
                {
                    title: "类型",
                    field: "type",
                    sortable: true,
                    order: "desc"//默认排序方式
                },
                {
                    field: "value",
                    title: "交易金额",
                    sortable: true,
                    order: "desc"//默认排序方式
                }];
            $('#my_table').bootstrapTable({
                columns: columns,
                data: datas,
                search: true,//是否搜索
                pagination: true,//是否分页
                pageSize: 15,//单页记录数
                pageList: [5, 10, 20, 50],//分页步进值
                sidePagination: "client",//服务端分页
                height: 500,
                searchOnEnterKey: false,//回车搜索
                showColumns: true,//列选择按钮
                buttonsAlign: "left",//按钮对齐方式
                singleSelect: true,
                // locale: "zh-CN"//中文支持,
            });
        });

        $(".submit-btn").bind("click", function () {
            $.ajax({
                type: "post",
                async: true,
                url: "/venue/modify_info",
                data: {
                    "password": $('#password').val(),
                    "name": $('#reg_name').val(),
                    "province": $('#reg_province').val(),
                    "city": $('#reg_city').val(),
                    "location": $('#reg_location').val(),
                    "seat_info": JSON.stringify(seats)
                },

                success: function (result) {
                    $('.errorMessage').html("修改成功");
                    setTimeout(function () {
                        $('.errorMessage').html(" ")
                        window.location.reload();
                    }, 4000);
                },
                error: function (result) {
                    $('.errorMessage').html("修改失败，请重新填写");
                    setTimeout(function () {
                        $('.errorMessage').html(" ")
                        window.location.reload();
                    }, 2000);
                }
            });
        })
    </script>

</body>
</html>
