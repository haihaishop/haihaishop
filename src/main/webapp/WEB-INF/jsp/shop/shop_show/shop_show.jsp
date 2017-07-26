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
    <title>店铺详情</title>
</rapid:override>

<rapid:override name="content">
    <div class="container-fluid">
        <div class="row">
            <h1 class="text-center">${store.store_name}</h1>
            <div class="col-md-2 col-sm-offset-1">
                <h3 class="col-sm-offset-0">商品分类</h3>
                <c:forEach items="${cateList}" var="cate">
                    <a href="/shop/${store.store_id}/${cate.cate_id}" class="list-group-item ">${cate.cate_name}</a>
                </c:forEach>
            </div>
            <div class="col-md-6">
                <div class="col-md-offset-1">
                    <form action="/shop/${store.store_id}/search" role="form">
                        <div class="input-group" style="width: 500px">
                            <input type="text" class="form-control"  name="word" placeholder="耐克运动鞋" required>
                            <span class="input-group-btn">
                        <button class="btn btn-default" type="submit">搜索</button>
                    </span>
                        </div><!-- /input-group -->
                    </form>
                </div><!-- /.col-lg-6 -->
                <br>
                <div class="row">
                    <c:if test="${!empty pageInfo}">
                    <c:if test="${pageInfo.pages != 0}">
                    <c:forEach items="${goodsList}" var="goods">
                        <div class="col-xs-12 col-sm-4 col-md-3 col-lg-3 " >
                            <div class="speical speical-default speical-radius" style="height: 330px">
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
                                        <div >
                                            <img class="img-responsive" style="height: 180px;width: 150px" src="${pageContext.request.contextPath}${goods.picture}">
                                        </div>
                                        <div>￥${goods.price}</div>
                                        <div>${goods.views_time}人浏览过</div>
                                        <div class="text-left">
                                            售出：<c:if test="${empty goods.sold_number}">0</c:if><c:if test="${!empty goods.sold_number}">${goods.sold_number}</c:if>件</div>
                                        <div class="text-left">库存：${goods.count}</div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="row">
                    <div class="col-sm-6 col-sm-offset-4">
                        <ul class="pagination">
                            <c:if test="${!empty cate_id}">
                                <li><a href="/buyer_home_page.do/${cate_id}?page=${pageInfo.prePage}&rows=${pageInfo.pageSize}">&laquo;</a></li>
                                <c:forEach begin="0" end="${pageInfo.pages - 1}" var="pageNum">
                                    <li
                                            <c:if test="${pageInfo.pageNum == pageNum + 1}"> class="active" </c:if>>
                                        <a href="/buyer_home_page.do/${cate_id}?page=${pageInfo.nextPage}&rows=${pageInfo.pageSize}"
                                        >${pageNum+1}</a></li>
                                </c:forEach>
                                <li><a href="/buyer_home_page.do/${cate_id}?page=${pageInfo.nextPage}&rows=${pageInfo.pageSize}">&raquo;</a></li>
                            </c:if>
                            <c:if test="${empty cate_id}">
                                <li><a href="/buyer_home_page.do?page=${pageInfo.prePage}&rows=${pageInfo.pageSize}">&laquo;</a></li>
                                <c:forEach begin="0" end="${pageInfo.pages - 1}" var="pageNum">
                                    <li
                                            <c:if test="${pageInfo.pageNum == pageNum + 1}"> class="active" </c:if>>
                                        <a href="/buyer_home_page.do?page=${pageInfo.nextPage}&rows=${pageInfo.pageSize}"
                                        >${pageNum+1}</a></li>
                                </c:forEach>
                                <li><a href="/buyer_home_page.do?page=${pageInfo.nextPage}&rows=${pageInfo.pageSize}">&raquo;</a></li>
                            </c:if>
                        </ul>
                    </div>
                </div>
                </c:if>
                <c:if test="${pageInfo.pages == 0}"><h3>暂无数据！</h3>
                </c:if>
                </c:if>
                <c:if test="${empty pageInfo}">
                    <h3>暂无数据！</h3>
                </c:if>

            </div>
            <div class="col-md-2">
                <div class="row">
                    <h2 class="text-center">卖家信息</h2>
                    <div class="col-sm-4 col-sm-offset-4">
                        <strong><h4>${store.store_name} </h4></strong>
                        <p class="text-left">掌柜：
                            <c:if test="${empty solder.nick_name}">
                                ${solder.username}
                            </c:if>
                            <c:if test="${!empty solder.nick_name}">
                                ${solder.nick_name}
                            </c:if>
                        </p>
                        <p>和我联系</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</rapid:override>
<rapid:override name="scripts">
</rapid:override>

<%@include file="../../base.jsp" %>

