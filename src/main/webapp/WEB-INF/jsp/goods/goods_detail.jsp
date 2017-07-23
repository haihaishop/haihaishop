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

<rapid:override name="content">
    <div class="container-fluid">
        <div class="row">
            <c:if test="${!empty goods}">
                <div class="col-md-3 col-md-offset-1">
                    <img src="${goods.picture}">
                </div>
                <div class="col-sm-6 col-sm-offset-1">
                    <h3>${goods.goods_name}</h3><br>
                    <h4>${goods.price}</h4>
                    <p>${goods.info}</p>
                    <p>${goods.views_time}</p>
                    <p>${goods.sold_number}</p>
                    <p>${goods.count}</p>
                </div>
            </c:if>
        </div>
    </div>
</rapid:override>

<rapid:override name="scripts">
</rapid:override>

<%@include file="../base.jsp" %>