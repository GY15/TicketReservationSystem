<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改会员信息</title>

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
                        <label class="col-md-3  form-label" for="email">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</label>
                        <div class="col-md-9 input-group" style="margin-left: 0px;left: 15px;">
                            <input type="text" class="form-control" name="email" value="${member.email}" id="email"
                                   style="width: 210px;" disabled>
                            <button class="col-md-1 btn btn-danger" id="cancelMember" style="margin-left: 10px">注销</button>
                            <b class="col-md-1 col-md-offset-1 form-label">余额</b>
                            <b class="col-md-1  form-label">${member.balance}</b>
                            <button class=" btn btn-primary recharge" style="margin-left: -175px">充值100块</button>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  form-label" for="reg_name">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" value="${member.nickname}" name="reg_name"
                                   id="reg_name" placeholder="给自己起个名字吧">
                        </div>
                        <div class="col-md-2" style="margin-left: -50px"><button class="btn btn-primary modify-name">修改昵称</button></div>
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
                        <label class="col-md-3  form-label" for="new_password">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
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
                        <button  class="btn btn-primary col-md-2 col-md-offset-5" id="modify-password"
                                style="margin-top: 15px;margin-bottom: 15px">修改密码</button>
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
                    <label class="col-md-3  form-label">积&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分</label>
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

<div class="modal fade" id="sureModal" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog" style="margin-top: 330px;height:300px;width: 20%">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times</button>
                <h4 class="modal-title" style="margin-top: 0px">重要提示</h4>
            </div>
            <div class="modal-body" style="margin-top: 70px;margin-bottom: 100px">
                <div class="row" style="margin-bottom: 10px">
                    你的会员一旦注销后将无法恢复！
                </div>
                <div class="row" style="margin-bottom: 10px">
                    <div class="col-md-1">
                        <button class="btn btn-primary sure-btn">确认注销</button>
                    </div>
                </div>
                <div class="errorMessage col-md-offset-3"></div>
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
        var money = 100;
        $.ajax({
            type: "post",
            async: true,
            url: "/member/recharge",
            data: {
                "money": money
            },
            success: function (result) {
                window.location.reload();
            },
            error: function (result) {
                window.location.reload();
            }
        });
    });
    $("#cancelMember").bind("click",function () {
        $("#sureModal").modal();
    });
    $(".sure-btn").bind("click",function () {
        $.ajax({
            type: "post",
            async: true,
            url: "/member/cancel_member",
            data: {
            },
            success: function (result) {
                window.href="/member/";
            },
            error: function (result) {
                $('.errorMessage').html("不知道出了什么错误");
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 2000);
            }
        });
    });
    $(".modify-name").bind("click",function () {
        alert($("#reg_name").val());
        $.ajax({
            type: "post",
            async: true,
            url: "/member/modify_name",
            data: {
                "nickname":  $("#reg_name").val()
            },
            success: function (result) {
                $('.errorMessage').html("昵称修改成功");
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 2000);
            },
            error: function (result) {
                $('.errorMessage').html("不知道出了什么错误");
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 2000);
            }
        });
    });
    $("#modify-password").bind("click",function () {
        $.ajax({
            type: "post",
            async: true,
            url: "/member/modify_password",
            data: {
                "old_password":  $("#old_password").val(),
                "new_password":  $("#new_password").val(),
                "new_password2":  $("#new_password2").val(),
            },
            success: function (result) {
                if(result=="success"){
                    $('.errorMessage').html("密码修改成功");
                    setTimeout(function () {
                        $('.errorMessage').html(" ")
                        window.location.reload();
                    }, 2000);
                }else {
                    $('.errorMessage').html("密码修改失败，请重新输入");
                    setTimeout(function () {
                        $('.errorMessage').html(" ")
                        window.location.reload();
                    }, 2000);
                }
            },
            error: function (result) {
                $('.errorMessage').html("密码修改失败");
                setTimeout(function () {
                    $('.errorMessage').html(" ");
                    window.location.reload();
                }, 2000);
            }
        });
    });
</script>

</body>
</html>
