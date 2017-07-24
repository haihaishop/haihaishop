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
                <c:forEach items="${goodsList}" var="goods">
                    <a href="/goods_detail.do/${goods.goods_id}" class="list-group-item">${goods.goods_name}</a>
                </c:forEach>
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

