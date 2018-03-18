<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2018/3/6
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="createSeatModal" tabindex="-1" role="dialog" aria-labelledby=""
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
                <div class="seat-panel row" style="margin-top: 5px" hidden>
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

                        <button class="button button-primary button-circle button-tiny" id="addType" style="margin-left: 40px; margin-top: 20px"
                                    ><i class="icon-plus"></i></button>
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
