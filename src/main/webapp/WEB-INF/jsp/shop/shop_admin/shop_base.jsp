<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>
<rapid:override name="head">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <title>商铺详情</title>
</rapid:override>
<rapid:override name="content">
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
<div class="col-sm-2 col-sm-offset-1">
    <ul class="nav nav-tabs nav-stacked">
        <li class="active"><a href="#">店铺管理</a> </li>
        <li><a href="/shop_admin/detail">修改店铺信息</a></li>
        <c:if test="${store.store_status == false}">
        <li><a href="/shop_admin/change_status/${store.store_id}">开放店铺</a> </li>
        </c:if>
        <c:if test="${store.store_status == true}">
            <li><a href="/shop_admin/change_status/${store.store_id}">关闭店铺</a> </li>
        </c:if>
    </ul>
    <hr>
    <ul class="nav nav-tabs nav-stacked">
        <li class="active"><a href="#">商品管理</a> </li>
        <li><a href="/shop_admin/${store.store_id}add_goods">增加商品</a></li>
        <li><a href="/shop_admin/${store.store_id}edit_goods">管理商品</a></li>
        <li><a href="/shop_admin/${store.store_id}shop_order">店铺订单</a></li>
    </ul>

    <hr>
    <ul class="nav nav-tabs nav-stacked">
        <li class="active"><a href="#">活动管理</a> </li>
        <li><a href="/shop_admin/${store.store_id}add_promotion">增加活动</a></li>
        <li><a href="/shop_admin/${store.store_id}edit_promotion">管理活动</a></li>
    </ul>

    <hr>
    <ul class="nav nav-tabs nav-stacked">
        <li class="active"><a href="#">聊天信息</a> </li>
        <li><a href="/shop_admin/chat">打开聊天窗口</a></li>
    </ul>

</div>
    <div class="col-sm-9">
        <rapid:block name="shop_detail">
        </rapid:block>
    </div>
</rapid:override>
<%@include file="../../base.jsp" %>

