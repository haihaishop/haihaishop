<%--
  Created by IntelliJ IDEA.
  User: 18240
  Date: 2017/7/27
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<rapid:override name="head">
    <link href="/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="/css/goods_show.css" media="all" rel="stylesheet" type="text/css" />
    <title>评论商品</title>
</rapid:override>
<rapid:override name="content">
    <div class="container-fluid">
        <div class="row col-sm-offset-2">
            <h2>商品详情</h2>
            <c:if test="${!empty goods}">
                <div class="col-md-3 col-md-offset-1">
                    <img src="${goods.picture}" alt="Loading Failed" width="200" height="300">
                </div>
                <div class="col-sm-4" style="overflow: hidden">
                    <h3>名称：${goods.goods_name}</h3><br>
                    <h3>价格：${goods.price}</h3>
                    <p style="word-break: break-all">简介：${goods.info}</p>
                    <p>浏览次数：${goods.views_time}</p>
                    <p>已卖数量：${goods.sold_number}</p>
                    <p>库存：${goods.count}</p>
                </div>
            </c:if>
        </div>
        <div class="row col-sm-offset-2">
            <h2>发表评论</h2>
            <form action="/submit_comment.do/${orderId}" method="post">
                <input hidden name="goods_id" value="${goods.goods_id}">
                评论：
                <textarea class="form-group" style="width: 50%;height: 100px" type="text" name="comment"></textarea><br>
                打分：
                <input id="input-21b" value="4" name="rate" type="number" class="rating" min=0 max=5 step=0.2 data-size="lg"><br>
                <input class="form-group button col-sm-offset-6" type="submit" value="提交评论">
            </form>
        </div>
        <div class="row col-sm-offset-2">
            <h2>评论列表</h2>
            <c:forEach items="${commentList}" var="comment">
                <div class="row form-inline">
                    <div class="form-group col-md-1">
                        <p>${comment.username}</p>
                    </div>
                    <div class="form-group col-md-5">
                        <p>${comment.comment}</p>
                    </div>
                    <div class="form-group col-sm-offset-1 col-md-1">
                        <p>${comment.rate}分</p>
                    </div>
                    <div class="form-group col-md-2">
                        <p><fmt:formatDate value="${comment.comment_date}" pattern="yyyy-MM-dd HH:mm:ss"/> </p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</rapid:override>
<rapid:override name="scripts">
    <script src="/js/star-rating.js" type="text/javascript"></script>
    <script>
        $(document).ready(function () {
            $(".rating-kv").rating();
        });
    </script>
</rapid:override>

<%@include file="../../base.jsp" %>
