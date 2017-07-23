<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>
<rapid:override name="head">
    <link href="/css/goods_show.css" media="all" rel="stylesheet" type="text/css" />
    <link href="/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
    <title>店铺详情</title>
</rapid:override>
<rapid:override name="shop_detail">
    <div class="row">
        <c:forEach items="${goodsList}" var="goods">
            <div class="col-xs-12 col-sm-4 col-md-3 col-lg-2" href="#">
                <div class="speical speical-default speical-radius" >
                    <a href="#">
                        <div class="shape">
                            <div class="shape-text">
                                极品
                            </div>
                        </div>
                        <div class="speical-content">
                            <h3 class="text-special-default">
                                    ${goods.goods_name}
                            </h3>
                            <p>
                                <img class="img-responsive img-rounded" src="${goods.picture}">
                            </p>
                            <p>
                                ￥${goods.price}元
                            </p>
                            <span ><p class="text-left">售出：<c:if test="${empty goods.sold_number}">0</c:if><c:if test="${!empty goods.sold_number}">${goods.sold_number}</c:if>件</p> </span>
                            <span ><p class="text-right">库存：${goods.count}</p> </span>
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="/shop_admin/goods_edit/${goods.goods_id}">点击编辑</a>
                                </div>
                                <div class="col-sm-6">
                                    <a href="/shop_admin/goods_delete/${goods.goods_id}">点击删除</a>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </c:forEach>
    </div>
</rapid:override>
<rapid:override name="scripts">
</rapid:override>>
<%@include file="shop_base.jsp" %>
