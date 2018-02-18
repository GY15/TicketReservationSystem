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
    <link href="../css/bootstrap-select.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <jsp:include page="navigation.jsp"></jsp:include>
    <div class="row">
        <c:choose>
            <c:when test="${plans!=null}">
                <c:forEach items="${plans}" var="plan" varStatus="vs">
                    <div class="planBlock">
                        <div class="row">
                            <div class="planid" hidden>${plan.planid}</div>
                            <h5 class="col-md-offset-1 col-md-1 plan_name" style="font-size: 130%">${plan.type}</h5>
                            <h5 class="col-md-5 plan_name"
                                style="font-size: 90%;margin-top: 16px">${plan.description}</h5>
                            <h5   style="font-size: 90%;margin-top: 16px">${plan.seatMaps[0].type[0].value}左右</h5>
                        </div>
                        <div class="row">
                            <div class="row col-md-5 col-md-offset-1">
                                <div class="row">
                                    <span class="col-md-2 col-md-offset-2 plan_font">开始时间</span><span
                                        class="col-md-8 small">${plan.startTime}</span>
                                </div>
                                <div class="row">
                                    <span class="col-md-2 col-md-offset-2 plan_font">结束时间</span><span
                                        class="col-md-8 small">${plan.endTime}</span>
                                </div>
                            </div>
                            <div class="row col-md-6">
                                <div class="col-md-5">
                                    <span class="col-md-4" style="margin-top: 10px;margin-right: -20px">区域 :</span>
                                    <select data-actions-box="true" data-size="10" style="width: 300px"
                                            class="selectpicker show-tick col-md-8">
                                        <c:forEach items="${plan.seatMaps}" var="seat" varStatus="vs">
                                            <option>${seat.block}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button class=" btn btn-sm btn-info buy_ticket"
                                        style="margin-left: -25px;margin-top: 5px">现场购票
                                </button>
                                <button class=" btn btn-sm btn-info col-md-offset-1 check_ticket"
                                        style="margin-top: 5px">检票登记
                                </button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div> 你可以去船舰一个任务</div>
            </c:otherwise>
        </c:choose>
    </div>

</div>

<div class="modal fade" id="buyTicketModal" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog" style="margin-top: 30px;height:710px;width: 90%">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times</button>
                <h4 class="modal-title" style="margin-top: 0px">现场购票</h4>
            </div>
            <div class="modal-body" style="height: 580px">
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
                    <div class="col-md-1" style="font-size: 130%;margin-top: 4px;margin-left:100px">选座详情</div>
                </div>
                <div class="seat-panel row" style="margin-top: 5px;">
                    <div class="col-md-8 seat-container col-md-offset-1">
                        <div id="seat-area">
                        </div>
                    </div>
                    <div class="col-md-3 booking-details">
                        <ul id="selected-seats"></ul>
                        <div style="margin-left: 50px">Total: <b>$<span id="total">0</span></b>
                        <button class="btn btn-primary">确认购买</button>
                        </div>
                        <div id="legend"></div>
                    </div>
                </div>
            </div>
            <%--<div class="modal-footer">--%>
            <%--<div class="login-btn-group">--%>
            <%--<button type="button" class="btn btn-primary" onclick="saveSeat()">保存</button>--%>
            <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
            <%--</div>--%>
            <%--</div>--%>
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
    var plans;
    $(document).ready(function () {
        $('.selectpicker').selectpicker();
        plans = JSON.parse('${plansJson}');
    });
    $(".check_ticket").bind("click", function () {

    });
    var seatType;
    $(".buy_ticket").bind("click", function () {
        var planid = $(this).parent().parent().parent().find(".planid").html();
        var block = $(this).parent().find(".selectpicker").eq(0).val();
        var block_description = "";
        var map = [];
        for (var i = 0; i < plans.length; i++) {
            if (plans[i].planid == planid) {
                for (var j = 0; j < plans[i].seatMaps.length; j++) {
                    if (plans[i].seatMaps[j].block == block) {
                        block_description = plans[i].seatMaps[j].description;
                        map = plans[i].seatMaps[j].map;
                        seatType = plans[i].seatMaps[j].type;
                    }
                }
            }
        }
        $("#buyTicketModal").modal();
        $("#localName").val(block);
        $("#localDescription").val(block_description);
        create(map);
    });

    function create(theMap) {
        $('#seat-area').html(
            "<div id=\"seat-map\">\n" +
            "<div class=\"front-indicator\">Front</div>\n" +
            "</div>");
        $(".booking-details").html(
            "<ul id=\"selected-seats\"></ul>\n" +
            "<div style=\"margin-left: 50px\">Total: <b>$<span id=\"total\">0</span></b>\n" +
            "<button class=\"btn btn-primary\">确认购买</button>\n" +
            "</div>\n" +
            "<div id=\"legend\"></div>")
        var seats = {};
        var items  = [
            [ 'a', 'unavailable', '不可预定']
        ]
        for(var i = 0; i < seatType.length; i++ ){
            var item = [];
            item.push(seatType[i].type);
            item.push('available');
            item.push(seatType[i].name);
            items.push(item);
            switch (seatType[i].type){
                case "a":
                    seats.a = {
                        price   : seatType[i].value,
                        category: seatType[i].name,
                        classes: 'a-class'
                    }
                    break;
                case "b":
                    seats.b = {
                        price   : seatType[i].value,
                        category: seatType[i].name,
                        classes: 'b-class'
                    }
                    break;
                case "c":
                    seats.c = {
                        price   : seatType[i].value,
                        category: seatType[i].name,
                        classes: 'c-class'
                    }
                    break;
                case "d":
                    seats.d = {
                        price   : seatType[i].value,
                        category: seatType[i].name,
                        classes: 'd-class'
                    }
                    break;
                case "e":
                    seats.e = {
                        price   : seatType[i].value,
                        category: seatType[i].name,
                        classes: 'e-class'
                    }
                    break;
                case "f":
                    seats.f = {
                        price   : seatType[i].value,
                        category: seatType[i].name,
                        classes: 'f-class'
                    }
                    break;
                case "g":
                    seats.g = {
                        price   : seatType[i].value,
                        category: seatType[i].name,
                        classes: 'g-class'
                    }
                    break;
            }
        }
//        alert(JSON.stringify(seats));
        var firstSeatLabel = 1;
        var $cart = $('#selected-seats'),
            $counter = $('#counter'),
            $total = $('#total'),
            sc = $('#seat-map').seatCharts({
                map: theMap,
                seats: seats,
                naming: {
//                    top: false,
//                    getLabel: function (character, row, column) {
//                        return firstSeatLabel++;
//                    },
                },
                legend: {
                    node: $('#legend'),
                    items: items
                },
                click: function () {
                    if (this.status() == 'available') {
                        if(sc.find('selected').length>=6){
                            $(".errorMessage").html("买票数不能超过6张");
                            setTimeout(function () {
                                $('.errorMessage').html(" ")
                            }, 2000);
                            return "available";
                        }
                        //let's create a new <li> which we'll add to the cart items
                        $('<li>' + this.data().category + ' Seat # ' + this.settings.id + ': <b>$' + this.data().price + '</b> <a href="#" class="cancel-cart-item">[取消]</a></li>')
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

        sc.get(['1_2', '4_1', '7_1', '7_2']).status('unavailable');

        var createCol = theMap.length;
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
                total += this.data().price;
            });

            return total;
        }
    }
</script>

</body>
</html>
