var createRow, createCol;
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
    $("#localName").val("");
    $("#localDescription").val("");
    $(".seat-panel").hide();
    $('#createSeatModal').modal();
});
var seats=[];

function initSeats(seatMaps) {
    seats = seatMaps;
    $(".deleteBlock").bind("click",function () {
        var delete_block = $(this).parent().parent().find(".seat_local_name").html();
        for(var i = 0; i < seats.length;i++){
            if(seats[i].block == delete_block){
                seats.splice(i,1);
                break;
            }
        }
        $(this).parent().parent().remove();
    });
}
$('input').iCheck({
    checkboxClass: 'icheckbox_flat-green',
    radioClass: 'iradio_flat-green'
});
function saveSeat() {

    for(var i = 0; i < seats.length;i++){
        if(seats[i].block== $("#localName").val()){
            alert("区域名称不能重复");
            return false;
        }
    }

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
        "<div class=\"seat_local_description col-md-6\">" + $("#localDescription").val() + "</div>\n" +
        "<div class=\"col-md-offset-2\"><button class=\"button button-royal  button-circle button-tiny deleteBlock\"><i class=\"icon-minus\"></i></button>" +
        "</div>");
    $(".deleteBlock").unbind("click");
    $(".deleteBlock").bind("click",function () {
        var delete_block = $(this).parent().parent().find(".seat_local_name").html();
        for(var i = 0; i < seats.length;i++){
            if(seats[i].block == delete_block){
                seats.splice(i,1);
                break;
            }
        }
        alert(JSON.stringify(seats));
        $(this).parent().parent().remove();
    });
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
        $(".removeType").unbind("click")
        $(".removeType").bind("click",function () {
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

