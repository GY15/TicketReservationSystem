/**
 * Created by 61990 on 2017/6/9.
 */
var today = new Date();

today.setTime(today.getTime() - 24 * 60 * 60 * 1000);
var yesterday = new Date();
yesterday.setTime(today.getTime() - 29*24 * 60 * 60 * 1000);

var endTime = today.getFullYear() + "-";

var month = today.getMonth() + 1;
var dayOfMonth = today.getDate()+2;

if (month < 10) {
    endTime += "0" + month;
} else {
    endTime += month;
}
if (dayOfMonth < 10) {
    endTime += "-0" + dayOfMonth;
} else {
    endTime += "-" + dayOfMonth;
}



// $("#datetimeStart>input").attr('value', endTime+" 08:00");
// $("#datetimeEnd>input").attr('value', endTime+" 09:00");

$("#datetimeStart").datetimepicker({
    format: 'yyyy-mm-dd hh:ii',
    language: 'zh-CN',
    autoclose: true,
    startDate: today
}).on("click", function () {
    $("#datetimeStart").datetimepicker('setEndDate', $("#datetimeEnd>input").val())
});
$("#datetimeEnd").datetimepicker({
    format: 'yyyy-mm-dd hh:ii',
    language: 'zh-CN',
    autoclose: true,
}).on("click", function () {
    $("#datetimeEnd").datetimepicker("setStartDate", $("#datetimeStart>input").val())
});
