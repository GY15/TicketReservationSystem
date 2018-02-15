<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>查看计划</title>

    <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/jquery.seat-charts.css">
    <link rel="stylesheet" type="text/css" href="../css/seat-chart.css">
    <link href="../css/flat/green.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link href="../css/venue_plan.css" rel="stylesheet">
    <link href="../css/button.css" rel="stylesheet">
</head>

<body>

<div class="container">

<jsp:include page="navigation.jsp"></jsp:include>
    <div class="row">
        <div class="planBlock">
            <div class="row">
                <div class="planid" hidden>123</div>
                <h5 class="col-md-offset-1 col-md-5 plan_name" style="font-size: 130%">羞羞的铁拳</h5>
                <!--<div class="col-md-2" style="width: 100%;height:10px;z-index: 3">-->
                <!--</div>-->
            </div>
            <div class="row">
                <div class="row col-md-7 col-md-offset-1">
                    <div class="row">
                        <span class="col-md-2 col-md-offset-2 plan_font">开始时间</span><span class="col-md-8 small">123</span>
                    </div>
                    <div class="row">
                        <span class="col-md-2 col-md-offset-2 plan_font">结束时间</span><span class="col-md-8 small">124</span>
                    </div>
                </div>
                <button class="col-md-1 btn btn-sm btn-info">现场购票</button>
                <button class="col-md-offset-1 col-md-1 btn btn-sm btn-info">检票登记</button>
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

<script>

</script>

</body>
</html>
