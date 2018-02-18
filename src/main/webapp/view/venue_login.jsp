<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2018/2/6
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录、注册</title>

    <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/jquery.seat-charts.css">
    <link rel="stylesheet" type="text/css" href="../css/seat-chart.css">
    <link href="../css/flat/green.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link href="../css/home.css" rel="stylesheet">
    <link href="../css/button.css" rel="stylesheet">
</head>

<body>

<div class="banner-holder">
    <div class="banner-image-holder" style="z-index: 1">
        <div role="banner">
            <img alt="Background" src="../img/home.jpg">
            <div class="fh5co-overlay"></div>
        </div>
    </div>


    <div class="jumbotron banner-desc col-md-4 col-md-offset-4 loginPanel" style="z-index: 10">
        <div class="container text-center">
            <h1>淘 票</h1>
            <div style="margin-top: 80px">
                <form class="form-horizontal" action="/venue/login" method="POST" role="form">
                    <div class="form-group" style="margin-top: 50px">
                        <label class="col-md-3 col-md-offset-1 control-label" for="login_id"
                               style="font-size: 18px;color:whitesmoke">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" name='login_id' id="login_id" value=""
                                   placeholder="请输入账号">
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 40px">
                        <label class="col-md-3 col-md-offset-1 control-label" for="login_password"
                               style="font-size: 18px;color:whitesmoke">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" name="login_password" id="login_password"
                                   placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="col-md-4 col-md-offset-8" style="color: whitesmoke">
                        <label id="rem-password">
                            <input type="checkbox" name="remember" id="remember" class="remember">记住密码
                        </label>
                    </div>

                    <button type="submit" class="btn btn-primary col-md-6 col-md-offset-3" style="margin-top: 30px"
                            onclick="">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录
                    </button>
                </form>
                <a class="regBt col-md-2">注 册</a>
            </div>
            <div class="col-md-12 errorMessage">
            </div>
            <a class="loginBt" style="position: absolute;right:20px;bottom: 10px">会员登录</a>
        </div>

    </div>
    <div class="jumbotron banner-desc2 col-md-6 col-md-offset-3 registerPanel" style="z-index: 10" hidden>
        <div class="container text-center">
            <h2>淘 票</h2>
            <div>
                <div class="form-horizontal">

                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  control-label" for="reg_venue"
                               style="font-size: 18px;color:whitesmoke">帐&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
                        <div class="col-md-3 input-group" style="margin-left: 0px;left: 15px;">
                            <input type="text" class="form-control" name="reg_venue" id="reg_venue"
                                   placeholder="点击获取帐号" style="width: 210px;" disabled>
                            <span class="input-group-addon button-valid ">  <i class="icon-download-alt"></i></span>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  control-label" for="reg_name"
                               style="font-size: 18px;color:whitesmoke">场馆名称</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" name="reg_name" id="reg_name"
                                   placeholder="输入场馆名称">
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  control-label" for="reg_password"
                               style="font-size: 18px;color:whitesmoke">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="reg_password" name="reg_password"
                                   placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  control-label" for="reg_password2"
                               style="font-size: 18px;color:whitesmoke">确认密码</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" id="reg_password2" name="reg_password2"
                                   placeholder="请再次输入密码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  control-label"
                               style="font-size: 18px;color:whitesmoke">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</label>
                        <div class="col-md-2">
                            <input type="text" class="form-control" id="reg_province" name="reg_province"
                                   placeholder="省份">
                        </div>
                        <div class="col-md-2">
                            <input type="text" class="form-control" id="reg_city" name="reg_city"
                                   placeholder="市">
                        </div>
                        <div class="col-md-5">
                            <input type="text" class="form-control" id="reg_location" name="reg_location"
                                   placeholder="详细地址">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-md-3  control-label" for="reg_password2"
                               style="font-size: 18px;color:whitesmoke">座位情况</label>
                        <div class="col-md-8" style="margin-left: 15px">
                            <div class='seat_set'>
                                <!--<div class="row seat_general">-->
                                <!--<div class="seat_local_name col-md-2">A区</div>-->
                                <!--<div class="seat_local_description">位于场馆右侧</div>-->
                                <!--</div>-->
                            </div>
                            <button class="button button-primary button-circle button-small" style="margin: 10px"
                                    id="add_seat_btn"><i class="icon-plus"></i></button>

                        </div>
                    </div>
                    <button type="button" class="btn btn-primary col-md-6 col-md-offset-3 submit-btn"
                            style="margin-top: 30px">
                        申&nbsp;&nbsp;&nbsp;请&nbsp;&nbsp;&nbsp;注&nbsp;&nbsp;&nbsp;册
                    </button>
                </div>

                <a class="loginBt col-md-3">返回登录</a>
            </div>
            <div class="col-md-12 errorMessage">
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="createSeatModal" tabindex="-1" role="dialog" aria-labelledby="loginLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="margin-top: 20px;height:692px;width: 90%">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" style="margin-top: 0px">创建座位</h4>
            </div>
            <div class="modal-body" style="height: 560px">
                <div class="row">
                    <label class="col-md-1 col-md-offset-1 label-font" align="right">区域名称</label>
                    <div class="col-md-1">
                        <input type="text" id="localName" class="form-control input-style">
                    </div>
                    <label class="col-md-1 label-font" align="right">区域说明</label>
                    <div class="col-md-2">
                        <input type="text" id="localDescription" class="form-control input-style">
                    </div>
                    <!--</div>-->
                    <div class="row col-md-5">
                        <label class="col-md-3  label-font" align="right" style="margin-left: 0px;margin-right: -10px">最大行数</label>
                        <div class="col-md-2">
                            <input type="text" id="rowNum" class="form-control input-style" value="10">
                        </div>
                        <label class="col-md-3 label-font" align="right" style="margin-left: -10px;margin-right: -20px">最大列数</label>
                        <div class="col-md-2">
                            <input type="text" id="colNum" class="form-control input-style" value="10">
                        </div>

                        <button class=" btn btn-primary create-btn" style="margin-left: 30px">生成座位</button>
                    </div>
                </div>
                <div class="seat-panel row" style="margin-top: 5px;display: none">
                    <div class="col-md-8 seat-container col-md-offset-1">
                        <div id="seat-area">
                        </div>
                    </div>
                    <div class="set-panel col-md-2" style="margin-left: 20px">
                        <h4 style="margin-top: 50px;margin-left: 30px">座位编辑</h4>
                        <div class="seat-choose">
                            <input type="radio" name="iCheck" value="_">删除座位
                        </div>
                        <div class="seat-choose">
                            <input type="radio" class="col-md-2 theType" name="iCheck" value='a' checked>
                            <input type="text" style="width: 100px" class="typeName" value="普通座位">
                        </div>
                        <div id="seat_type">

                        </div>
                        <button id="addType" style="margin-left: 40px;margin-top: 20px"><i class="icon-plus"></i>
                        </button>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="login-btn-group">
                    <button type="button" class="btn btn-primary" onclick="saveSeat()">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
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
    $(document).ready(function () {
        var message = "<%=session.getAttribute("errorMessage")%>";
        if(message=="1"){
            $('.errorMessage').html("账号或者密码错误");
            setTimeout(function () {
                $('.errorMessage').html(" ")
            }, 2000);
        }
    });
    var createRow, createCol;
    $('.loginBt').click(function () {
        $('.loginPanel').slideDown();
        $('.registerPanel').slideUp("fast");
    });
    $('.regBt').click(function () {
        $('.loginPanel').slideUp();
        $('.registerPanel').slideDown("fast");
    });
    $('.remember').iCheck({
        checkboxClass: 'icheckbox_flat-green',
        radioClass: 'iradio_flat-green'
    });

    $('.create-btn').on("click", function () {
        map = [];
        createRow = $("#rowNum").val();
        createCol = $("#colNum").val();

        var line = '';
        var i = 0, j = 0;
        while (i < createCol) {
            line += 'a'
            i++;
        }

        while (j < createRow) {
            map.push(line);
            j++
        }
        create(map);
        $('.seat-panel').show();
    });

    $('#add_seat_btn').bind("click", function () {
        $('#createSeatModal').modal();
    });
    var seats=[];
    function saveSeat() {
        var seat_info = {};
        seat_info.blockid = $('#reg_venue').val() +"_"+ $("#localName").val();
        seat_info.venueid = $('#reg_venue').val();
        seat_info.block = $("#localName").val();
        seat_info.description = $("#localDescription").val();
        seat_info.map = map;
        var seat_type = [];
        $('.typeName').each(function () {
            var seat = {}
            seat.type = $(this).parent().find('.theType').eq(0).val();
            seat.name = $(this).val();
            seat.value= 0.00;
            seat_type.push(seat);
        });
        seat_info.type = seat_type;

        $('.seat_set').append("  <div class=\"row seat_general\">\n" +
            "<div class=\"seat_local_name col-md-2\">" + $("#localName").val() + "</div>\n" +
            "<div class=\"seat_local_description\">" + $("#localDescription").val() + "</div>\n" +
            "</div>")
        seats.push(seat_info);
        $('#createSeatModal').modal("hide");
    }
    var seat_kind = ['g', 'f', 'e', 'd', 'c', 'b']
    $('#addType').bind("click", function () {
        var type = seat_kind.pop();
        if (type) {
            $('#seat_type').append("<div class=\"seat-choose\">\n" +
                "                                <input type=\"radio\" class=\"col-md-2 theType\" name=\"iCheck\"  value='" + type + "'>\n" +
                "                                <input type=\"text\" class='typeName' style=\"width: 100px\" value=\"\">\n" +
                "                                <button >  <i class=\"removeType icon-minus\" \"></i></button>\n" +
                "                            </div>")
            $('input').iCheck({
                checkboxClass: 'icheckbox_flat-green',
                radioClass: 'iradio_flat-green'
            });
            $(".removeType").click(function () {
                var deleteType = $(this).parent().parent().find('.theType').eq(0).val();
                seat_kind.push(deleteType);
                $(this).parent().parent().remove();
                var mapTemp = [];
                for (var i = 0; i < createRow; i++) {
                    mapTemp.push(map[i].replaceAll(deleteType, 'a'));
                }
                map = mapTemp;
                create(map);
            });
        }
    });

    var map = [];
    var sc;


    $('input').iCheck({
        checkboxClass: 'icheckbox_flat-green',
        radioClass: 'iradio_flat-green'
    });

    function create(theMap) {
        $('#seat-area').html("<div id=\"seat-map\">\n" +
            "            <div class=\"front-indicator\">Front</div>\n" +
            "        </div>")
        sc = $('#seat-map').seatCharts({
            map: theMap,
            seats: {
                a: {
                    classes: 'a-class', //your custom CSS class
                },
                b: {
                    classes: 'b-class', //your custom CSS class
                },
                c: {
                    classes: 'c-class', //your custom CSS class
                },
                d: {
                    classes: 'd-class', //your custom CSS class
                },
                e: {
                    classes: 'e-class', //your custom CSS class
                },
                f: {
                    classes: 'f-class', //your custom CSS clas
                },
                g: {
                    classes: 'g-class', //your custom CSS class
                }
            },

            click: function () {
                if (this.status() == 'available') {
                    var newType = "a";
                    $("input[name='iCheck']:radio").each(function () {
                        if (true == $(this).is(':checked')) {
                            newType = $(this).val();
                        }
                    });
                    var locate = this.settings.id;
                    var theRow = locate.split("_")[0] - 1;
                    var theCol = locate.split("_")[1] - 1;
                    var mapTemp = [];
                    for (var i = 0; i < createRow; i++) {
                        if (theRow == i) {
                            mapTemp.push(map[i].substr(0, theCol) + newType + map[i].substr(theCol + 1, createCol));
                        } else {
                            mapTemp.push(map[i]);
                        }
                    }
                    map = mapTemp;
                    create(map);
                    return 'available';
                }
            },

        });
        var left = 780 / 26 * ((25 - createCol) / 2);
        if (createCol > 25) {
            $("#seat-area").css("margin-left", 0 + "px");
            $(".seatCharts-container").css("width", 780 / 22 * createCol + "px");
        } else {
            $("#seat-area").css("margin-left", left + "px");
            $(".seatCharts-container").css("width", "auto");
        }
        $('.seatCharts-space').bind("click", function () {
            var newType = "a";
            var mapTemp = [];
            $("input[name='iCheck']:radio").each(function () {
                if (true == $(this).is(':checked')) {
                    newType = $(this).val();
                }
            });
            var theRow = $(this).html();
            for (var i = 0; i < createRow; i++) {
                if (theRow == (i + 1)) {
                    var temp = '';
                    for (var j = 0; j < createCol; j++) {
//                        if (map[i].charAt(j) == '_') {
//                            temp += "_";
//                        } else {
                            temp += newType;
//                        }
                    }
                    mapTemp.push(temp);
                } else {
                    mapTemp.push(map[i]);
                }
            }
            map = mapTemp;
            create(map);
        })
    }

    String.prototype.replaceAll = function (FindText, RepText) {
        regExp = new RegExp(FindText, "g");
        return this.replace(regExp, RepText);
    }

    $('.button-valid').bind("click", function valid() {
        $('.button-valid').html("<i class=\"icon-spinner icon-spin\"></i>");
        $('.button-valid').unbind("click")
        $.ajax({
            type: "post",
            async: true,
            url: "/venue/valid",

            success: function (result) {
                $('#reg_venue').val(result);
                $('.button-valid').html("<i class=\"icon-download-alt\"></i>");
                $('.button-valid').bind("click", valid);
            },
            error: function (result) {
                $('.button-valid').html("<i class=\"icon-download-alt\"></i>");
                $('.button-valid').bind("click", valid);
                alert("发生了未知的错误，刷新后重试");
            }
        });
    });

    $(".submit-btn").bind("click", function () {
        var ss =JSON.stringify(seats);
        alert(ss);
        var s2 = JSON.parse(ss);
        $.ajax({
            type: "post",
            async: true,
            url: "/venue/register_venue",
            data: {
                "venueid": $('#reg_venue').val(),
                "password": $('#reg_password').val(),
                "name": $('#reg_name').val(),
                "province": $('#reg_province').val(),
                "city": $('#reg_city').val(),
                "location": $('#reg_location').val(),
                "seat_info": JSON.stringify(seats)
            },

            success: function (result) {
                $(".registerPanel").hide();
                $(".loginPanel").show();
                $('.errorMessage').html("注册成功，账号"+  $('#reg_venue').val());
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 4000);
            },
            error: function (result) {
                $('.errorMessage').html("注册失败，请重新检查注册信息");
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 2000);
            }
        });
    })

</script>

</body>
</html>
