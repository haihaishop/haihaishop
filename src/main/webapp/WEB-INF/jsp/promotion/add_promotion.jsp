<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>
<rapid:override name="head">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <title>增加活动</title>
</rapid:override>
<rapid:override name="shop_detail">

    <c:if test="${!empty warning}">
        <div id="myAlert" class="alert alert-warning">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong>警告！</strong>${warning}。
        </div>
    </c:if>
    <c:if test="${!empty success}">
        <div id="myAlert" class="alert alert-success">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong>恭喜！</strong>${success}。
        </div>
    </c:if>
    <div class="modal fade" id="alert">
        <div class="modal-dialog">
            <div class="modal-content message_align">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">提示信息</h4>
                </div>
                <div class="modal-body">
                    <p id="content"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <form id="add_form" method="post" class="form-horizontal" action="/shop_admin/add_promotion_post" role="form">
        <div class="form-group">
            <label class="col-sm-2 control-label" for="name">请输入活动名</label>
            <div class="col-sm-4">
            <input type="text" class="form-control col-sm-8" id="name" name="name" placeholder="请输入名称" required>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="type">请选择活动类型</label>
            <div class="col-sm-4">
            <select class="form-control col-sm-8" id="type" name="type">
                <option value="1" selected>打折</option>
                <option value="2" >满减</option>
                <option value="3" >几免几</option>
            </select>
            </div>
        </div>
        <div id="discount_div" class="form-group" >
            <label class="col-sm-2 control-label" for="discount">请输入折扣</label>
            <div class="col-sm-4">
            <input type="number" class="form-control col-sm-8" id="discount" name="discount" required>
            </div>
        </div>
        <div id="full_div" class="form-group" style="display: none">
            <label class="col-sm-2 control-label" for="full">请输入满足价格（满几）</label>
            <div class="col-sm-4">
            <input type="number" class="form-control col-sm-8" id="full" name="full" >
            </div>
        </div>
        <div id="cut_div" class="form-group" style="display: none">
            <label class="col-sm-2 control-label" for="cut">请输入减少价格（减几）</label>
            <div class="col-sm-4">
            <input type="number" class="form-control col-sm-8" id="cut" name="cut" >
            </div>
        </div>
        <div id="buy_div" class="form-group" style="display: none">
            <label class="col-sm-2 control-label" for="buy">请输入满足数量（满几）</label>
            <div class="col-sm-4">
            <input type="number" class="form-control col-sm-8" id="buy" name="buy" >
            </div>
        </div>
        <div id="give_div" class="form-group" style="display: none">
            <label class="col-sm-2 control-label" for="give">请输入减少数量（免几）</label>
            <div class="col-sm-4">
            <input type="number" class="form-control col-sm-8" id="give" name="give" >
            </div>
        </div>

        <div class="form-group">
            <label for="from_time" class="col-sm-2 control-label">开始日期</label>
            <div class="input-group date form_datetime col-sm-4"  data-date-format="dd MM yyyy - HH:ii p" data-link-field="from_time">
                <input class="form-control col-sm-8" size="16" type="text" value="" readonly>
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
            </div>
            <input type="hidden" id="from_time" name="from_time" value="" /><br/>
        </div>
        <div class="form-group">
            <label for="from_time" class="col-sm-2 control-label">结束日期</label>
            <div class="input-group date form_datetime col-sm-4"  data-date-format="dd MM yyyy - HH:ii p" data-link-field="to_time">
                <input class="form-control col-sm-8" size="16" type="text" value="" readonly>
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
            </div>
            <input type="hidden" id="to_time" name="to_time" value="" /><br/>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2">参与活动的商品</label>
            <div class="col-sm-4">
                <c:forEach items="${allGoods}" var="goods">
                    <label class="checkbox">
                        <input type="checkbox" name="goods[]" value="${goods.goods_id}">${goods.goods_name}
                    </label>
                </c:forEach>
            </div>
        </div>
        <input type="hidden" value="${store_id}" name="store_id">
        <input type="hidden" value="0" name="is_all_site">
        <div class="col-sm-offset-3 col-sm-4">
            <button type="submit" class="btn btn-default">提交</button>
        </div>
    </form>
</rapid:override>
<rapid:override name="scripts">
    <script type="text/javascript" src="/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script type="text/javascript" src="/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <script type="text/javascript">
        $("#type").change(function() {
            var value = $(this).children('option:selected').val();
            console.log(value);
            switch (value){
                case "1":
                    $("#discount_div")[0].style.display = "block";
                    $("#discount").attr("required", true);
                    $("#full_div")[0].style.display = "none";
                    $("#full").attr("required", false);
                    $("#cut_div")[0].style.display = "none";
                    $("#cut").attr("required", false);
                    $("#buy_div")[0].style.display = "none";
                    $("#buy").attr("required", false);
                    $("#give_div")[0].style.display = "none";
                    $("#give").attr("required", false);
                    break;
                case "2":
                    $("#discount_div")[0].style.display = "none";
                    $("#discount").attr("required", false);
                    $("#full_div")[0].style.display = "block";
                    $("#full").attr("required", true);
                    $("#cut_div")[0].style.display = "block";
                    $("#cut").attr("required", true);
                    $("#buy_div")[0].style.display = "none";
                    $("#buy").attr("required", false);
                    $("#give_div")[0].style.display = "none";
                    $("#give").attr("required", false);
                    break;
                case "3":
                    $("#discount_div")[0].style.display = "none";
                    $("#discount").attr("required", false);
                    $("#full_div")[0].style.display = "none";
                    $("#full").attr("required", false);
                    $("#cut_div")[0].style.display = "none";
                    $("#cut").attr("required", false);
                    $("#buy_div")[0].style.display = "block";
                    $("#buy").attr("required", true);
                    $("#give_div")[0].style.display = "block";
                    $("#give").attr("required", true);
                    break;
            }
        });
    </script>
    <script type="text/javascript">
    $(".form_datetime").datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1
    });
    </script>

</rapid:override>
<%@include file="../shop/shop_admin/shop_base.jsp" %>
