<%--
  Created by IntelliJ IDEA.
  User: 18240
  Date: 2017/7/27
  Time: 8:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>
<rapid:override name="head">
    <title>增加商品</title>
</rapid:override>
<rapid:override name="shop_detail">
    <div class="container-fluid">
        <h3>订单详情</h3><br>
        <div class="row" style="background-color: #2aabd2">
            <div class="col-md-1">订单号</div>
            <div class="col-md-3">商品</div>
            <div class="col-md-1">买家</div>
            <div class="col-md-1">数量</div>
            <div class="col-md-2">地址</div>
            <div class="col-md-1">状态</div>
            <div class="col-md-1">操作</div>
        </div><br>
        <c:forEach items="${orderGoodsList}" var="orderGoods">
            <div class="row form-inline">
                <div class="col-md-1" style="top: 65px">
                    <p class="form-group"><strong>${orderGoods.order.order_form_id}</strong></p>
                </div>
                <div class="col-md-3"  style="overflow:hidden;">
                    <img src="${orderGoods.goods.picture}" style="width: 100px;height: 150px" class="form-group col-md-2"
                         alt="图片加载失败">
                    <div class="form-group" style="height: 150px">
                        <h4>${orderGoods.goods.goods_name}</h4>
                        <p style="word-break: break-all;">${orderGoods.goods.info}</p>
                    </div>
                </div>
                <div class="col-md-1" style="top: 50px">
                    <p class="form-group"><strong>${orderGoods.buyer.username}</strong></p>
                </div>
                <div class="col-md-1" style="top:50px">
                    <p class="form-group"><strong>${orderGoods.order.buy_number}</strong></p>
                </div>
                <div class="col-md-2" style="top: 40px">
                    <p class="form-group">
                            ${orderGoods.address.province}${orderGoods.address.city}${orderGoods.address.country}${orderGoods.address.detail_address}
                    </p>
                </div>
                <c:if test="${orderGoods.order.shipping_state == 2}">
                    <div class="col-md-1" style="top: 50px">
                        <p>待发货</p>
                    </div>
                    <div class="col-md-1" style="top: 30px">
                        <a href="/shop_admin/${orderGoods.goods.store_id}send_goods/${orderGoods.order.order_form_id}"><button class="btn">点击发货</button></a>
                    </div>
                </c:if>
                <c:if test="${orderGoods.order.shipping_state == 3}">
                    <div class="col-md-1" style="top: 50px">
                        <p>未收货</p>
                    </div>
                    <div class="col-md-1" style="top: 30px">
                        <button class="btn">查看发货状态</button>
                    </div>
                </c:if>
                <c:if test="${orderGoods.order.shipping_state == 4}">
                    <div class="col-md-1" style="top: 50px">
                        <p>已收货</p>
                    </div>
                    <div class="col-md-1" style="top: 30px">
                        <button class="btn">联系买家</button>
                    </div>
                </c:if>
                <c:if test="${orderGoods.order.shipping_state == 5}">
                    <div class="col-md-1" style="top: 50px">
                        <p>已评价</p>
                    </div>
                    <div class="col-md-1" style="top: 30px">
                        <button class="btn">查看评价</button>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </div>
</rapid:override>
<rapid:override name="scripts">

</rapid:override>
<%@include file="../../shop/shop_admin/shop_base.jsp" %>
