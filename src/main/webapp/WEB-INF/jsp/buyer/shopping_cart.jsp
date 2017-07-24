<%--
  Created by IntelliJ IDEA.
  User: 18240
  Date: 2017/7/24
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page isELIgnored="false" %>
<rapid:override name="head">
    <title>购物车</title>
</rapid:override>
<rapid:override name="content">
    <div class="container-fluid">
    <div class="row" style="background-color: gray">
        <div class="col-md-1 col-md-offset-1">
            <input type="checkbox">全选
        </div>
        <div class="col-md-4">商品</div>
        <div class="col-md-1">单价</div>
        <div class="col-md-1">数量</div>
        <div class="col-md-1">总计</div>
        <div class="col-md-1">操作</div>
    </div>
    <c:forEach items="${orderGoodsList}" var="orderGoods">
        <div class="row form-inline">
            <div class="col-md-1 col-md-offset-1" style="top: 65px">
                <input type="checkbox">
            </div>
            <div class="col-md-4">
                <img src="${orderGoods.goods.picture}" style="width: 100px;height: 150px" class="form-group"
                     alt="图片加载失败">
                <div class="form-group">
                    <h4>${orderGoods.goods.goods_name}</h4>
                    <p>${orderGoods.goods.info}</p>
                </div>
            </div>
            <div class="col-md-1" style="top: 65px">
                <p class="form-group" class="unit_price"><strong>￥${orderGoods.order.unit_price}</strong></p>
            </div>
            <div class="col-md-1" style="top: 65px">
                <p class="buy_number">${orderGoods.order.buy_number}</p>
            </div>
            <div class="col-md-1" style="top: 65px">
                <strong>￥${orderGoods.order.buy_number * orderGoods.order.unit_price}</strong>
            </div>
            <div class="col-md-1" style="top: 65px">
                <button class="button">删除</button>
            </div>
        </div>
    </c:forEach>
    <div class="row">
        <div class="form-inline">
            <div class="col-md-2 col-sm-offset-5">
                <h4>总价:￥<span id="sum"></span></h4>
            </div>
            <div class="col-md-2">
                <a href="/pay_order.do">
                    <button class="form-control" style="width: 100%;background-color: lightseagreen">去支付</button>
                </a>
            </div>
        </div>
    </div>
</rapid:override>
<rapid:override name="scripts">
</rapid:override>
<%@include file="../base.jsp" %>