<%--
  Created by IntelliJ IDEA.
  User: 18240
  Date: 2017/7/25
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page isELIgnored="false" %>
<rapid:override name="head">
    <link href="/css/goods_show.css" media="all" rel="stylesheet" type="text/css" />
    <title>查看订单</title>
</rapid:override>
<rapid:override name="content">
    <div class="container-fluid">
        <div class="row" style="background-color: gray">
            <div class="col-md-1">订单号</div>
            <div class="col-md-4">商品</div>
            <div class="col-md-1">地址</div>
            <div class="col-md-1">总计</div>
            <div class="col-md-1">操作</div>
        </div>
    </div>
</rapid:override>
<rapid:override name="scripts">
</rapid:override>

<%@include file="../base.jsp" %>