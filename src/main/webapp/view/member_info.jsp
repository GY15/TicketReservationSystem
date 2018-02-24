<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改会员信息</title>

    <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/jquery.seat-charts.css">
    <link rel="stylesheet" type="text/css" href="../css/seat-chart.css">
    <link href="../css/flat/green.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link href="../css/member.css" rel="stylesheet">
    <link href="../css/button.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <jsp:include page="member_navigation.jsp"></jsp:include>
    <div class="row jumbotron banner-desc2 ">
        <div class="text-center">

            <div>
                <div class="form-horizontal">

                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label" for="email">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</label>
                        <div class="col-md-9 input-group" style="margin-left: 0px;left: 15px;">
                            <input type="text" class="form-control" name="email" value="${member.email}" id="email"
                                   style="width: 210px;" disabled>
                            <button class="col-md-1 btn btn-danger" style="margin-left: 10px">注销</button>
                            <b class="col-md-1 col-md-offset-1 form-label">余额</b>
                            <b class="col-md-1  form-label">${member.balance}</b>
                            <button class=" col-md-1 btn btn-primary recharge" style="margin-left: 15px">充值100块</button>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label" for="reg_name">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" value="${member.nickname}" name="reg_name"
                                   id="reg_name"
                                   placeholder="给自己起个名字吧">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label" for="old_password">旧&nbsp;&nbsp密&nbsp;&nbsp;码</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" value="${venue.password}" id="old_password"
                                   name="old_password"
                                   placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label" for="new_password">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="new_password" name="new_password"
                                   placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label" for="new_password2">确认密码</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="new_password2" name="new_password2"
                                   placeholder="请再次输入密码">
                        </div>
                    </div>
                    <div class="row">
                        <button type="submit" class="btn btn-primary col-md-2 col-md-offset-5"
                                style="margin-top: 15px;margin-bottom: 15px">
                            修&nbsp;&nbsp;&nbsp;&nbsp;改
                        </button>
                    </div>
                </div>
            </div>
            <div class="col-md-12 errorMessage">
            </div>
        </div>
    </div>
    <div class="row jumbotron banner-desc2 ">
        <div class="text-center">
            <div class="form-horizontal">
                <div class="form-group" style="margin-top: 20px">
                    <label class="col-md-3  form-label">积&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分</label>
                    <b class="col-md-1  form-label">${member.grade}</b>
                    <button class="col-md-1 btn btn-primary" style="margin-left: 50px">换优惠券</button>
                    <label class="col-md-3  form-label">等&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级</label>
                    <b class="col-md-1  form-label">${member.rank}</b>
                </div>
                <div class="form-group" style="margin-top: 20px">
                    <label class="col-md-3  form-label">我的优惠券</label>
                    <div class="col-md-4" style="margin-top: 5px">
                        <c:choose>
                            <c:when test="${coupons!=null}">
                                <c:forEach items="${coupons}" var="coupon" varStatus="vs">
                                    <div class="couponBlock row">
                                        <div class="col-md-offset-1 col-md-12" style="text-align: left">
                                        <c:if test="${coupon.type==1}">
                                            <b>满&nbsp;&nbsp;${coupon.minimum}&nbsp;&nbsp;减&nbsp;&nbsp;${coupon.discount}元</b>
                                        </c:if>
                                        <c:if test="${coupon.type==2}">
                                            <b>满&nbsp;&nbsp;${coupon.minimum}&nbsp;&nbsp;打&nbsp;&nbsp;${coupon.discount}折</b>
                                        </c:if>
                                    </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <div> 你可以去换张劵</div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>

            <div class="col-md-12 errorMessage2">
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
    $(".recharge").bind("click",function () {
        $.ajax({
            type: "post",
            async: true,
            url: "/member/recharge",
            data: {
                "money": 100
            },
            success: function (result) {
                window.location.reload();
            },
            error: function (result) {
                $('.errorMessage').html("你的订单号有误");
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 3000);
            }
        });
    });
</script>

</body>
</html>
