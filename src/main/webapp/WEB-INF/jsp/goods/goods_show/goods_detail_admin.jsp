<%--
  Created by IntelliJ IDEA.
  User: 18240
  Date: 2017/7/23
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page isELIgnored="false" %>

<rapid:override name="head">
    <title>商品详情</title>
</rapid:override>

<rapid:override name="shop_detail">
    <div class="container-fluid">
        <div class="row">
            <h2>商品详情</h2>
            <c:if test="${!empty goods}">
                <div class="col-md-3 col-md-offset-2">
                    <img src="${goods.picture}" alt="Loading Failed" width="200" height="300">
                </div>
                <div class="col-sm-5" style="overflow: hidden">
                    <h3>名称：${goods.goods_name}</h3><br>
                    <h3>价格：${goods.price}</h3>
                    <p style="word-break: break-all">简介：${goods.info}</p>
                    <p>浏览次数：${goods.views_time}</p>
                    <p>已卖数量：${goods.sold_number}</p>
                    <p>库存：${goods.count}</p>
                </div>
            </c:if>
        </div>
        <h2>评价</h2>
        <div class="row"></div>
    </div>
</rapid:override>

<rapid:override name="scripts">
</rapid:override>

<%@include file="../../shop/shop_admin/shop_base.jsp" %>