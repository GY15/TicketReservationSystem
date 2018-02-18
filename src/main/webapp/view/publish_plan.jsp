<%@ page contentType="text/html;charset=UTF-8" import="web.model.SeatMapObj" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>发布计划</title>

    <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/jquery.seat-charts.css">
    <link rel="stylesheet" type="text/css" href="../css/seat-chart.css">
    <link href="../css/flat/green.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link href="../css/venue_plan.css" rel="stylesheet">
    <link href="../css/button.css" rel="stylesheet">
    <link href="../css/bootstrap-datetimepicker.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <jsp:include page="navigation.jsp"></jsp:include>
    <div class="row jumbotron banner-desc2 ">
        <div class="text-center">

            <div>
                <div class="form-horizontal">

                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label">开始时间</label>
                        <div class='input-group date col-md-7' id='datetimeStart' style="width:170px">
                            <input id="startDate" type='text' style="margin-left: 15px;width: 170px"
                                   class="form-control"/>
                            <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label">结束时间</label>
                        <div class='input-group date col-md-7' id='datetimeEnd' style="width:170px">
                            <input id="endDate" type='text' style="margin-left: 15px;width: 170px"
                                   class="form-control"/>
                            <span class="input-group-addon">
                                  <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label">类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="plan_type" name="plan_type"
                                   placeholder="输入类型">
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label" for="plan_description">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="plan_description" name="plan_description"
                                   placeholder="输入演出描述">
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label">座位价格</label>
                        <div class="col-md-8" style="margin-left: 15px">
                            <c:choose>
                                <c:when test="${seatMaps!=null}">
                                    <c:forEach items="${seatMaps}" var="seatMap" varStatus="vs">
                                        <div class="seat-block"
                                             style="background-color:rgb(230,230,230);color: indigo;margin-bottom: 10px;text-align: left;">
                                            <div class="row">
                                                <div class="col-md-offset-1">区域: <span class="col-md-offset-1">${seatMap.block}</span></div>
                                            </div>
                                            <c:forEach items="${seatMap.type}" var="seatType" varStatus="vs">
                                                <div class='seat_set'style="margin-top:10px;margin-bottom: 3px">
                                                    <div class="form-group row">
                                                        <div class="seat-char" hidden>${seatType.type}</div>
                                                        <label class="col-md-2 col-md-offset-2 form-label-sm">${seatType.name}</label>
                                                        <div class="col-md-4">
                                                            <input type="text" class="form-control seat_value"
                                                                   placeholder="输入座位价格" value="${seatType.value}">
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                    <button class="btn btn-primary col-md-4 col-md-offset-4" id="publish_btn" style="margin-top: 30px">
                        发&nbsp;&nbsp;&nbsp;布&nbsp;&nbsp;&nbsp;计&nbsp;&nbsp;&nbsp;划
                    </button>
                </div>
            </div>
            <div class="col-md-12 errorMessage">
            </div>
        </div>
    </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>
<script src="../js/format-valid.js"></script>
<script src="../js/icheck.js"></script>

<!--<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>-->
<script src="../js/jquery.seat-charts.js"></script>
<script src="../js/icheck.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/dbDatePicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>

<script>
    var maps = "";
    $(document).ready(function () {
        maps = JSON.parse('${seatMapsJson}');
    });
    $("#publish_btn").bind("click", function () {
        var seat_value = [];
        $('.seat_value').each(function () {
            seat_value.push($(this).val());
        });
        var k = 0;
        var seatNum = 0;
        for(var i= 0;i<maps.length;i++) {
            var map = maps[i].map;
            for(var q = 0 ; q < map.length; q++){
                for(var p = 0 ; p< map[q].length;p++) {
                    if (map[q].charAt(p)!="_"){
                        seatNum++;
                    }
                }
            }
            for(var j = 0; j<maps[i].type.length;j++){
                maps[i].type[j].value = seat_value[k];
                k++;
            }
        }
        $.ajax({
            type: "post",
            async: true,
            url: "/venue/publish",
            data: {
                "startTime":$("#startDate").val(),
                "endTime": $('#endDate').val(),
                "type": $('#plan_type').val(),
                "description": $('#plan_description').val(),
                "seat_info": JSON.stringify(maps),
                "seat_num":seatNum
            },

            success: function (result) {
                location.href = "/venue/my_plan"
            },
            error: function (result) {
                $('.errorMessage').html("座位信息错误，请检查后重新输入");
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 2000);
            }
        });
    })
    String.prototype.replaceAll = function (FindText, RepText) {
        regExp = new RegExp(FindText, "g");
        return this.replace(regExp, RepText);
    }
</script>

</body>
</html>
