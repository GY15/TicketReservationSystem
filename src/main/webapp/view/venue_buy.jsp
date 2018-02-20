<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>购票</title>

    <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/jquery.seat-charts.css">
    <link rel="stylesheet" type="text/css" href="../css/seat-chart.css">
    <link href="../css/flat/green.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link href="../css/venue_plan.css" rel="stylesheet">
    <link href="../css/button.css" rel="stylesheet">
    <link href="../css/bootstrap-select.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <jsp:include page="navigation.jsp"></jsp:include>
</div>
<div class="" style="margin-top:5px;margin-left:4%;height:710px;width: 90%">
    <div class="text-center">
        <h4 class="modal-title" style="margin-top: 10px;margin-bottom: 10px">${plan.description}</h4>
    </div>
    <div style="padding-top:10px;height: 580px">
        <div class="row">
            <label class="col-md-1 col-md-offset-2 label-font" align="right">区域名称</label>
            <div class="col-md-1">
                <input type="text" id="localName" class="form-control input-style" disabled>
            </div>
            <label class="col-md-1 label-font" align="right">区域说明</label>
            <div class="col-md-2">
                <input type="text" id="localDescription" class="form-control input-style" disabled>
            </div>
            <div class="col-md-2 errorMessage"></div>
            <div class="col-md-1" style="font-size: 130%;margin-top: 4px;margin-left:115px">选座详情</div>
        </div>
        <div class="seat-panel row" style="margin-top: 5px;">
            <div class="col-md-8 seat-container col-md-offset-1"
                 style="margin-top:10px;border: 1px solid rosybrown;border-radius: 5px;">
                <div id="seat-area">
                </div>
            </div>
            <div class="col-md-3 booking-details">
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="buyTicketModal" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog" style="margin-top: 30px;height:710px;width: 40%">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times</button>
                <h4 class="modal-title" style="margin-top: 0px">现场购票</h4>
            </div>
            <div class="modal-body" style="">
                <div class="row" style="margin-bottom: 10px">
                    <label class="col-md-3 label-font" align="right">会员购票</label>
                    <div class="col-md-6">
                        <input type="text" id="email" class="form-control input-style">
                    </div>
                    <div class="col-md-1">
                        <button class="btn btn-primary discount_btn">获取折扣</button>
                    </div>
                </div>
                <div class="row" style="margin-bottom: 10px">
                    <label class="col-md-3 label-font" align="right">演出简介</label>
                    <div class="col-md-7">
                        <input type="text" class="form-control input-style" value="${plan.description}" disabled>
                    </div>
                </div>
                <div class="row" style="margin-bottom: 10px">
                    <label class="col-md-3 label-font" align="right">区域名称</label>
                    <div class="col-md-7">
                        <input type="text" id="block" class="form-control input-style" disabled>
                    </div>
                </div>
                <div class="row" style="margin-bottom: 10px">
                    <label class="col-md-3 label-font" align="right">座位编号</label>
                    <div class="col-md-7">
                        <ul id="seatLists" style="margin-top: 8px">
                        </ul>
                    </div>
                </div>
                <div class="row" style="margin-bottom: 10px">
                    <label class="col-md-3 label-font" align="right">订单价格</label>
                    <div class="col-md-offset-2 col-md-2">
                        <h4>￥<b id="sure_value"></b></h4>
                    </div>
                    <div class="col-md-1 col-md-offset-1">
                        <button class="btn btn-primary pay_btn">确认购买</button>
                    </div>
                </div>
                <div class="errorMessage col-md-offset-4"></div>
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
<script src="../js/bootstrap-select.js"></script>
<script src="../js/icheck.js"></script>

<script>
    var plan;
    var seatType;
    var planid = "";
    var block = "";
    var block_description = "";
    var is_member = false;
    $(document).ready(function () {
        plan = JSON.parse('${planJson}');
        planid = plan.planid;
        block = '${block}';
        createTicketArea();
    });
    $(".discount_btn").bind("click",function () {
        $.ajax({
            type: "post",
            async: true,
            url: "/venue/getDiscount",
            data: {
                "email": $("#email").val()
            },
            success: function (result) {
                $("#sure_value").html(($("#total").html()*result));
                $('.errorMessage').html("你的折扣为 "+ result*10 +" 折");
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 2000);
                $("#sure_value").html(($("#total").html()*result));
                $("#email").attr("disabled", true);
                is_member = true;
            },
            error: function (result) {
                $('.errorMessage').html("会员账号错误，请重新输入");
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 2000);
            }
        });
    })
    $(".pay_btn").bind("click",function () {
        var booked_seats = [];
        $("#seatLists").find(".selectedSeat").each(function () {
            booked_seats.push($(this).html());
        });
        var email = "";
        if(is_member){
            email = $("#email").val();
        }
        $.ajax({
            type: "post",
            async: true,
            url: "/venue/createVenueOrder",
            data: {
                "email" : email,
                "member": is_member,
                "planid" : planid,
                "block" : block,
                "seats" : JSON.stringify(booked_seats),
                "value" :  $("#sure_value").html()
            },
            success: function (result) {
                if(result == "fail"){
                    $('.errorMessage').html("订单提交失败，请重新购买");
                    setTimeout(function () {
                        $('.errorMessage').html(" ");
                        location.reload();
                    }, 2000);
                }else{
                    $('.errorMessage').html("成功购票，正在打印");
                    setTimeout(function () {
                        $('.errorMessage').html(" ");
                        location.reload();
                    }, 4000);
                }
            },
            error: function (result) {
                $('.errorMessage').html("订单提交失败，请重新购买");
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 2000);
            }
        });
    });

    function createTicketArea() {
        var map = [];
        for (var j = 0; j < plan.seatMaps.length; j++) {
            if (plan.seatMaps[j].block == block) {
                block_description = plan.seatMaps[j].description;
                map = plan.seatMaps[j].map;
                seatType = plan.seatMaps[j].type;
            }
        }
        $("#localName").val(block);
        $("#localDescription").val(block_description);
        $("#block").val(block);
        $('#seat-area').html(
            "<div id=\"seat-map\">\n" +
            "<div class=\"front-indicator\">Front</div>\n" +
            "</div>");
        $(".booking-details").html(
            "<ul id=\"selected-seats\"></ul>\n" +
            "<div style=\"margin-left: 50px\">Total: <b>￥<span id=\"total\">0</span></b>\n" +
            "<button class=\"btn btn-primary sure_seat\">确认座位</button>\n" +
            "</div>\n" +
            "<div id=\"legend\"></div>")
        $(".sure_seat").bind("click",  function() {
            $("#sure_value").html($("#total").html());
            $("#seatLists").html($("#selected-seats").html());
            $("#buyTicketModal").modal();
        });
        var seats = {};
        var items = [
            ['a', 'unavailable', '不可预定']
        ]
        for (var i = 0; i < seatType.length; i++) {
            var item = [];
            item.push(seatType[i].type);
            item.push('available');
            item.push(seatType[i].name);
            items.push(item);
            switch (seatType[i].type) {
                case "a":
                    seats.a = {
                        price: seatType[i].value,
                        category: seatType[i].name,
                        classes: 'a-class'
                    }
                    break;
                case "b":
                    seats.b = {
                        price: seatType[i].value,
                        category: seatType[i].name,
                        classes: 'b-class'
                    }
                    break;
                case "c":
                    seats.c = {
                        price: seatType[i].value,
                        category: seatType[i].name,
                        classes: 'c-class'
                    }
                    break;
                case "d":
                    seats.d = {
                        price: seatType[i].value,
                        category: seatType[i].name,
                        classes: 'd-class'
                    }
                    break;
                case "e":
                    seats.e = {
                        price: seatType[i].value,
                        category: seatType[i].name,
                        classes: 'e-class'
                    }
                    break;
                case "f":
                    seats.f = {
                        price: seatType[i].value,
                        category: seatType[i].name,
                        classes: 'f-class'
                    }
                    break;
                case "g":
                    seats.g = {
                        price: seatType[i].value,
                        category: seatType[i].name,
                        classes: 'g-class'
                    }
                    break;
            }
        }

        var $cart = $('#selected-seats'),
            $counter = $('#counter'),
            $total = $('#total'),
            sc = $('#seat-map').seatCharts({
                map: map,
                seats: seats,
                naming: {

                },
                legend: {
                    node: $('#legend'),
                    items: items
                },
                click: function () {
                    if (this.status() == 'available') {
                        if (sc.find('selected').length >= 6) {
                            $(".errorMessage").html("买票数不能超过6张");
                            setTimeout(function () {
                                $('.errorMessage').html(" ")
                            }, 2000);
                            return "available";
                        }
                        //let's create a new <li> which we'll add to the cart items
                        $('<li>' + this.data().category + ' Seat # <b class="selectedSeat">' + this.settings.id + '</b>: <b>￥' + this.data().price + '</b> <a href="#" class="cancel-cart-item">[取消]</a></li>')
                            .attr('id', 'cart-item-' + this.settings.id)
                            .data('seatId', this.settings.id)
                            .appendTo($cart);
                        /*
                         * Lets update the counter and total
                         *
                         * .find function will not find the current seat, because it will change its stauts only after return
                         * 'selected'. This is why we have to add 1 to the length and the current seat price to the total.
                         */
                        $counter.text(sc.find('selected').length + 1);
                        $total.text(recalculateTotal(sc) + this.data().price);
                        return 'selected';
                    } else if (this.status() == 'selected') {
                        //update the counter
                        $counter.text(sc.find('selected').length - 1);
                        //and total
                        $total.text(recalculateTotal(sc) - this.data().price);

                        //remove the item from our cart
                        $('#cart-item-' + this.settings.id).remove();

                        //seat has been vacated
                        return 'available';
                    } else if (this.status() == 'unavailable') {
                        //seat has been already booked
                        return 'unavailable';
                    } else {
                        return this.style();
                    }
                }
            });


        //this will handle "[cancel]" link clicks
        $('#selected-seats').on('click', '.cancel-cart-item', function () {
            //let's just trigger Click event on the appropriate seat, so we don't have to repeat the logic here
            sc.get($(this).parents('li:first').data('seatId')).click();
        });

        $.ajax({
            type: "post",
            async: true,
            url: "/venue/getBookedArray",
            data: {
                "planid": planid,
                "block": block
            },

            success: function (result) {
//               alert(result);
                sc.get(result).status('unavailable');
            },
            error: function (result) {
                $('.errorMessage').html("发生了未知的错误，请重试");
                setTimeout(function () {
                    $('.errorMessage').html(" ")
                }, 2000);
            }
        });


        var createCol = map.length;
        var left = 780 / 26 * ((25 - createCol) / 2);
        if (createCol > 25) {
            $("#seat-area").css("margin-left", 0 + "px");
            $(".seatCharts-container").css("width", 780 / 22 * createCol + "px");
        } else {
            $("#seat-area").css("margin-left", left + "px");
            $(".seatCharts-container").css("width", "auto");
        }

        function recalculateTotal(sc) {
            var total = 0;

            //basically find every selected seat and sum its price
            sc.find('selected').each(function () {
                total += Math.round(this.data().price * 100) / 100;
            });

            return total;
        }
    }
</script>

</body>
</html>
