<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>

<rapid:override name="head">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <title>分类管理</title>
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
    <div class="row">
        <form id="add_form" method="post" class="form-horizontal" action="/admin/add_promotion_post" role="form">
            <div class="form-group">
                <label class="col-sm-2 control-label" for="name">请输入活动名</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control col-sm-8" id="name" name="name" placeholder="请输入名称" required>
                </div>
            </div>
            <input type="hidden" value="1" name="type">
            <div id="discount_div" class="form-group" >
                <label class="col-sm-2 control-label" for="discount">请输入折扣</label>
                <div class="col-sm-4">
                    <input type="number" class="form-control col-sm-8" id="discount" name="discount" required>
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
            <input type="hidden" value="1" name="is_all_site">
            <div class="col-sm-offset-3 col-sm-4">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </form>
    </div>

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
</rapid:override>
<%@include file="promotion_manage.jsp" %>


