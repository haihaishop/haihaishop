<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: 18240
  Date: 2017/7/23
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<rapid:override name="head">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <title>商品详情</title>
</rapid:override>

<rapid:override name="content">
    <div class="modal fade" id="successModel">
        <div class="modal-dialog">
            <div class="modal-content message_align">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title">提示信息</h4>
                </div>
                <div class="modal-body">
                    <p>加入购物车成功</p>
                </div>
                <div class="modal-footer">
                    <input type="hidden" id="url"/>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <a onclick="urlSubmit()" class="btn btn-success" data-dismiss="modal">确定</a>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-offset-1 col-md-8">
                <div class="row">
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
                            <c:if test="${!empty promotion}">
                                <c:set var="nowDate" value="<%=new Date()%>" />
                                <c:if test="${promotion.from_time.time < nowDate.time}">
                                    <c:if test="${promotion.to_time.time > nowDate.time}">
                                        <c:if test="${promotion.type == 1}">
                                            <p>活动：${promotion.name} ${promotion.discount}折折扣！</p>
                                        </c:if>
                                        <c:if test="${promotion.type == 2}">
                                            <p>活动：${promotion.name} 满${promotion.full}减${promotion.cut}！</p>
                                        </c:if>
                                        <c:if test="${promotion.type == 3}">
                                            <p>活动：${promotion.name} 买${promotion.buy}送${promotion.give}</p>
                                        </c:if>
                                    </c:if>
                                </c:if>
                            </c:if>
                            <p>已卖数量：${goods.sold_number}</p>
                            <p>库存：${goods.count}</p>
                        </div>
                    </c:if>
                </div>
                <div class="row">
                    <div class="form-inline  col-sm-offset-4">
                        <div class="col-md-3">
                            <button class="form-control" onclick="decrease()">-</button>
                            <input id="buy_num" style="width: 30%" class="form-control" value="1">
                            <button class="form-control" onclick="increase()">+</button>
                        </div>
                        <div class="col-md-2">
                            <button class="form-control" onclick="buy()"
                                    style="width: 200%;background-color: lightseagreen">加入购物车
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-3">
                <div class="row">
                    <h2 >卖家信息</h2>
                    <div class="col-md-4">
                        <br/>
                        <strong><h4>${store.store_name} <a href="/shop/${store.store_id}">进入店铺</a></h4></strong>
                        <p class="text-left">掌柜：
                            <c:if test="${empty solder.nick_name}">
                                ${solder.username}
                            </c:if>
                            <c:if test="${!empty solder.nick_name}">
                                ${solder.nick_name}
                            </c:if>
                        </p>
                        <p><a href="<%= request.getContextPath()%>/chat?toId=${solder.username}"> 和我联系</a></p>
                    </div>
                </div>
            </div>
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
                        <p><fmt:formatDate value="${comment.comment_date}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</rapid:override>

<rapid:override name="scripts">
    <script type="text/javascript">
        function decrease() {
            if ($("#buy_num")[0].value > 1) {
                $("#buy_num")[0].value--;
            }
        }

        function increase() {
            if ($("#buy_num")[0].value < ${goods.count}) {
                $("#buy_num")[0].value++
            }
        }

        function buy() {
            var xmlhttp;
            var buy_count = $("#buy_num")[0].value;
            var url = "<%= request.getContextPath()%>/add_shopping_cart.do/" + buy_count + "/" + ${goods.goods_id};
            if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
                xmlhttp = new XMLHttpRequest();
            }
            else {// code for IE6, IE5
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlhttp.open("POST", url, true);
            xmlhttp.send();
            $("#successModel").modal();
        }
    </script>
</rapid:override>

<%@include file="../../base.jsp" %>