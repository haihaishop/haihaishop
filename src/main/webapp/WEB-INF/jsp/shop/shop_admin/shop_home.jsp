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
        <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2">
            <div class="speical speical-default speical-radius" >
                <a href="/shop_admin/goods_detail/${goods.goods_id}">
                        <%--<div class="shape">--%>
                        <%--<div class="shape-text">--%>
                        <%--极品--%>
                        <%--</div>--%>
                        <%--</div>--%>
                    <div class="speical-content">
                        <h4 class="text-special-default">
                                ${goods.goods_name}
                        </h4>
                        <p>
                            <img class="img-responsive" style="height: 200px" src="${goods.picture}">
                        </p>
                        <div class="row">
                            <div class="col-sm-6">
                                <p>￥${goods.price}</p>
                            </div>
                            <div class="col-sm-6">
                                <p>${goods.views_time}人浏览过</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6"><p class="text-left">售出：<c:if test="${empty goods.sold_number}">0</c:if><c:if test="${!empty goods.sold_number}">${goods.sold_number}</c:if>件</p> </div>
                            <div class="col-sm-6"><p class="text-left">库存：${goods.count}</p> </div>
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
