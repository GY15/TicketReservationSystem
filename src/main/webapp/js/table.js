function initTable(columns,datas) {
    $('#my_table').bootstrapTable({
        columns: columns,
        data: datas,
        search: true,//是否搜索
        pagination: true,//是否分页
        pageSize: 15,//单页记录数
        pageList: [5, 10, 20, 50],//分页步进值
        sidePagination: "client",//服务端分页
        height : tableHeight(),
        searchOnEnterKey: false,//回车搜索
        showColumns: true,//列选择按钮
        buttonsAlign: "left",//按钮对齐方式
        singleSelect: true,
        // locale: "zh-CN"//中文支持,
    });

}
function tableHeight() {
    return $(window).height() - 90;
}
