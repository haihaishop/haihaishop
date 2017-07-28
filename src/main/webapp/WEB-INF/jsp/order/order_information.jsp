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
        <table class="table col-md-offset-1" style="width: 80%">
            <thead>
            <tr>
                <th>订单号</th>
                <th>商品</th>
                <th>地址</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orderGoodsList}" var="orderGoods">
                <input hidden class="buy_number" value=${orderGoods.order.buy_number}>
                <input hidden class="unit_price" value=${orderGoods.order.unit_price}>
                <input hidden class="order_id" value=${orderGoods.order.order_form_id}>
                <tr>
                    <td style="top: 65px">
                        <p class="form-group"><strong>${orderGoods.order.order_form_id}</strong></p>
                    </td>
                    <td>
                        <img src="${orderGoods.goods.picture}" style="width: 100px;height: 150px"
                             class="form-group col-md-4"
                             alt="图片加载失败">
                        <div style="height: 150px;overflow:hidden;" class="form-group col-md-8">
                            <h4>${orderGoods.goods.goods_name}</h4>
                            <p style="word-break: break-all;">${orderGoods.goods.info}</p>
                        </div>
                    </td>
                    <td style="top: 65px">
                        <p>${orderGoods.address.province}${orderGoods.address.city}${orderGoods.address.country}${orderGoods.address.detail_address}</p>
                    </td>
                    <c:if test="${orderGoods.order.shipping_state == 1}">
                        <td style="top: 65px">
                            <p>未支付</p>
                        </td>
                        <td style="top: 65px">
                            <a href="/goToPay.do/${orderGoods.order.order_form_id}">
                                <button class="btn-danger">去支付</button>
                            </a>
                        </td>
                    </c:if>
                    <c:if test="${orderGoods.order.shipping_state == 2}">
                        <td style="top: 30px">
                            <p>待发货</p>
                        </td>
                        <td style="top: 30px">
                            <a href="<%= request.getContextPath()%>/chat?toId=${orderGoods.seller.username}">
                                <button class="btn">联系商家</button>
                            </a>
                        </td>
                    </c:if>
                    <c:if test="${orderGoods.order.shipping_state == 3}">
                        <td style="top: 30px">
                            <p>发货中</p>
                        </td>
                        <td style="top: 30px">
                            <a href="/confirm_receipt.do/${orderGoods.order.order_form_id}">
                                <button class="btn">确认收货</button>
                            </a>
                        </td>
                    </c:if>
                    <c:if test="${orderGoods.order.shipping_state == 4}">
                        <td style="top: 30px">
                            <p>已收货</p>
                        </td>
                        <td style="top: 30px">
                            <a href="/comment_goods.do/${orderGoods.order.order_form_id}">
                                <button class="btn">去评价</button>
                            </a>
                        </td>
                    </c:if>
                    <c:if test="${orderGoods.order.shipping_state == 5}">
                        <td style="top: 30px">
                            <p>已评价</p>
                        </td>
                        <th style="top: 30px">
                            <a href="/goods_detail.do/${orderGoods.goods.goods_id}">
                                <button class="btn">查看详情</button>
                            </a>
                        </th>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</rapid:override>
<rapid:override name="scripts">
</rapid:override>

<%@include file="../base.jsp" %>