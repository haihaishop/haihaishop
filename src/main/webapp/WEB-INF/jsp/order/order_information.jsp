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
    <link href="/css/goods_show.css" media="all" rel="stylesheet" type="text/css"/>
    <title>查看订单</title>
</rapid:override>
<rapid:override name="content">
    <div class="container-fluid">
        <div class="row col-md-offset-2">
            <div class="col-md-1">订单号</div>
            <div class="col-md-4">商品</div>
            <div class="col-md-2">地址</div>
            <div class="col-md-1">状态</div>
            <div class="col-md-1">操作</div>
        </div>
        <c:forEach items="${orderGoodsList}" var="orderGoods">
            <input hidden class="buy_number" value=${orderGoods.order.buy_number}>
            <input hidden class="unit_price" value=${orderGoods.order.unit_price}>
            <input hidden class="order_id" value=${orderGoods.order.order_form_id}>
            <div class="row form-inline col-md-offset-2">
                <div class="col-md-1" style="top: 65px">
                    <p class="form-group"><strong>${orderGoods.order.order_form_id}</strong></p>
                </div>
                <div class="col-md-4" style="overflow:hidden;">
                    <img src="${orderGoods.goods.picture}" style="width: 100px;height: 150px" class="form-group col-md-2"
                         alt="图片加载失败">
                    <div style="height: 150px">
                        <h4>${orderGoods.goods.goods_name}</h4>
                        <p style="word-break: break-all;">${orderGoods.goods.info}</p>
                    </div>
                </div>
                <div class="col-md-2" style="top: 65px">
                    <p class="form-group">
                            ${orderGoods.address.province}${orderGoods.address.city}${orderGoods.address.country}${orderGoods.address.detail_address}
                    </p>
                </div>
                <c:if test="${orderGoods.order.shipping_state == 1}">
                    <div class="col-md-1" style="top: 65px">
                        <p>未支付</p>
                    </div>
                    <div class="col-md-1" style="top: 65px">
                        <button class="btn-danger">去支付</button>
                    </div>
                </c:if>
                <c:if test="${orderGoods.order.shipping_state == 2}">
                    <div class="col-md-1" style="top: 30px">
                        <p>待发货</p>
                    </div>
                    <div class="col-md-1" style="top: 30px">
                        <button class="btn">联系商家</button>
                    </div>
                </c:if>
                <c:if test="${orderGoods.order.shipping_state == 3}">
                    <div class="col-md-1" style="top: 30px">
                        <p>发货中</p>
                    </div>
                    <div class="col-md-1" style="top: 30px">
                        <a href="/confirm_receipt.do/${orderGoods.order.order_form_id}"><button class="btn">确认收货</button></a>
                    </div>
                </c:if>
                <c:if test="${orderGoods.order.shipping_state == 4}">
                    <div class="col-md-1" style="top: 30px">
                        <p>已收货</p>
                    </div>
                    <div class="col-md-1" style="top: 30px">
                        <a href="/comment_goods.do/${orderGoods.goods.goods_id}"><button class="btn">去评价</button></a>
                    </div>
                </c:if>
                <c:if test="${orderGoods.order.shipping_state == 5}">
                    <div class="col-md-1" style="top: 30px">
                        <p>已评价</p>
                    </div>
                    <div class="col-md-1" style="top: 30px">
                        <a href="/comment_goods.do/${orderGoods.goods.goods_id}"><button class="btn">查看详情</button></a>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </div>
</rapid:override>
<rapid:override name="scripts">
</rapid:override>

<%@include file="../base.jsp" %>