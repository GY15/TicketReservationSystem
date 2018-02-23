<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div style="margin-top:5px;margin-left:4%;height:710px;width: 90%">
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

