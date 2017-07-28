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
    <c:if test="${!empty pageInfo}">
    <c:if test="${pageInfo.pages != 0}">
    <c:forEach items="${goodsList}" var="goods">
        <div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 " >
            <div class="speical speical-default speical-radius" style="height: 330px">
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

        <div class="row">
            <div class="col-sm-6 col-sm-offset-4">
                <ul class="pagination">
                    <li><a href="/shop_admin/shop?page=${pageInfo.prePage}&rows=${pageInfo.pageSize}">&laquo;</a></li>
                    <c:forEach begin="0" end="${pageInfo.pages - 1}" var="pageNum">
                        <li
                                <c:if test="${pageInfo.pageNum == pageNum + 1}"> class="active" </c:if>>
                            <a href="/shop_admin/shop?page=${pageInfo.nextPage}&rows=${pageInfo.pageSize}"
                            >${pageNum+1}</a></li>
                    </c:forEach>
                    <li><a href="/shop_admin/shop?page=${pageInfo.nextPage}&rows=${pageInfo.pageSize}">&raquo;</a></li>
                </ul>
            </div>
        </div>
    </c:if>
        <c:if test="${pageInfo.pages == 0}">
            <h3>暂无数据！</h3>
        </c:if>
    </c:if>
    <c:if test="${empty pageInfo}">
        <h3>暂无数据！</h3>
    </c:if>
</div>
</rapid:override>
<rapid:override name="scripts">
</rapid:override>
<%@include file="shop_base.jsp" %>
