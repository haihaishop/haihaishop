<%--
  Created by IntelliJ IDEA.
  User: 18240
  Date: 2017/7/20
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page isELIgnored="false" %>

<rapid:override name="head">
    <link href="/css/goods_show.css" media="all" rel="stylesheet" type="text/css" />
    <title>海海商城</title>
</rapid:override>

<rapid:override name="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-2 col-sm-offset-1">
                <h3 class="col-sm-offset-0">商品分类</h3>
                <c:forEach items="${cateList}" var="cate">
                    <a href="/buyer_home_page.do/${cate.cate_id}" class="list-group-item ">${cate.cate_name}</a>
                </c:forEach>
            </div>
            <div class="col-md-6">
                <div class="col-md-offset-1">
                    <div class="input-group" style="width: 500px">
                        <input type="text" class="form-control">
                        <span class="input-group-btn">
                        <button class="btn btn-default" type="button">搜索</button>
                    </span>
                    </div><!-- /input-group -->
                </div><!-- /.col-lg-6 -->
                <br>
                <div class="row">
                <c:forEach items="${goodsList}" var="goods">
                    <div class="col-xs-12 col-sm-4 col-md-3 col-lg-3">
                        <div class="speical speical-default speical-radius" >
                            <a href="/goods_detail.do/${goods.goods_id}">
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
                <div class="row">
                    <div class="col-sm-6 col-sm-offset-6">
                        <ul class="pagination">
                            <li><a href="/buyer_home_page.do/${cate_id}">&laquo;</a></li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li><a href="#">&raquo;</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <h3>销量排行</h3>
                <table class="table-striped" cellpadding="10">
                    <thead>
                    <tr>
                        <th>店铺</th>
                        <th>销量</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Tanmay</td>
                        <td>Bangalore</td>
                    </tr>
                    <tr>
                        <td>Sachin</td>
                        <td>Mumbai</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</rapid:override>
<rapid:override name="scripts">
</rapid:override>

<%@include file="../base.jsp" %>

