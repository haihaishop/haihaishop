<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<rapid:override name="head">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <title>修改活动</title>
</rapid:override>
<rapid:override name="promotion_detail">

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
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span></button>
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

    <form id="add_form" method="post" class="form-horizontal" action="/admin/edit_promotion_post" role="form">
        <div class="form-group">
            <label class="col-sm-2 control-label" for="name">请输入活动名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control col-sm-8" id="name" name="name" placeholder="请输入名称"
                       value="${promotion.name}" required>
            </div>
        </div>
        <input type="hidden" value="1" name="type">
        <div id="discount_div" class="form-group">
            <label class="col-sm-2 control-label" for="discount">请输入折扣</label>
            <div class="col-sm-4">
                <input type="number" class="form-control col-sm-8" id="discount" name="discount"
                       required
                       value="${promotion.discount}">
            </div>
        </div>

        <div class="form-group">
            <label for="from_time" class="col-sm-2 control-label">开始日期</label>
            <div class="input-group date form_datetime col-sm-4"
                 data-date="<fmt:formatDate value='${promotion.from_time}' pattern='yyyy-MM-dd HH:mm:ss' />"
                 data-date-format="dd MM yyyy - HH:ii p"
                 data-link-field="from_time">
                <input class="form-control col-sm-8" size="16" type="text"  readonly>
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
            </div>
            <input type="hidden" id="from_time" name="from_time"
                   value="<fmt:formatDate value='${promotion.from_time}' pattern='yyyy-MM-dd HH:mm:ss' />" /><br/>
        </div>
        <div class="form-group">
            <label for="from_time" class="col-sm-2 control-label">结束日期</label>
            <div class="input-group date form_datetime col-sm-4"
                 data-date="<fmt:formatDate value='${promotion.to_time}' pattern='yyyy-MM-dd HH:mm:ss' />"
                 data-date-format="dd MM yyyy - HH:ii p"
                 data-link-field="to_time">
                <input class="form-control col-sm-8" size="16" type="text"  readonly>
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
            </div>
            <input type="hidden" id="to_time" name="to_time"
                   value="<fmt:formatDate value='${promotion.to_time}' pattern='yyyy-MM-dd HH:mm:ss' />" /><br/>
        </div>

        <input type="hidden" value="1" name="is_all_site">
        <input type="hidden" value="${promotion.promotion_id}" name="promotion_id">
        <div class="form-group">
            <div class="col-sm-offset-2">
            <p>注意：修改活动会导致所有参与该活动的商品取消该活动！</p>
            </div>
        </div>
        <div class="form-group">

            <div class="col-sm-offset-3 col-sm-4">
                <button type="submit" class="btn btn-default">提交(不修改日期则使用之前的)</button>
            </div>
        </div>
    </form>
</rapid:override>
<rapid:override name="scripts">
    <script type="text/javascript" src="/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script type="text/javascript" src="/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
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

</rapid:override>>
<%@include file="promotion_manage.jsp" %>
