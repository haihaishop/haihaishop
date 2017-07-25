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
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <title>商品详情</title>
</rapid:override>

<rapid:override name="content">
    <div class="modal fade" id="successModel">
        <div class="modal-dialog">
            <div class="modal-content message_align">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">提示信息</h4>
                </div>
                <div class="modal-body">
                    <p>加入购物车成功</p>
                </div>
                <div class="modal-footer">
                    <input type="hidden" id="url"/>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <a  onclick="urlSubmit()" class="btn btn-success" data-dismiss="modal">确定</a>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <div class="container-fluid">
        <div class="row">
            <h2>商品详情</h2>
            <c:if test="${!empty goods}">
                <div class="col-md-3 col-md-offset-2">
                    <img src="${goods.picture}" alt="Loading Failed" width="200" height="300">
                </div>
                <div class="col-sm-5">
                    <h3>名称：${goods.goods_name}</h3><br>
                    <h3>价格：${goods.price}</h3>
                    <p>简介：${goods.info}</p>
                    <p>浏览次数：${goods.views_time}</p>
                    <p>已卖数量：${goods.sold_number}</p>
                    <p>库存：${goods.count}</p>
                </div>
            </c:if>
        </div>
        <div class="row">
            <div class="form-inline">
                <div class="col-md-2 col-sm-offset-5">
                    <button class="form-control" onclick="decrease()">-</button>
                    <input id="buy_num" style="width: 30%" class="form-control" value="1">
                    <button class="form-control" onclick="increase()">+</button>
                </div>
                <div class="col-md-2">
                    <button class="form-control" onclick="buy()" style="width: 100%;background-color: lightseagreen">加入购物车</button>
                </div>
            </div>
        </div>
        <h2>评价</h2>
        <div class="row"></div>
    </div>
</rapid:override>

<rapid:override name="scripts">
    <script type="text/javascript">
        function decrease() {
            if($("#buy_num")[0].value > 1){
                $("#buy_num")[0].value--;
            }
        }

        function increase() {
            if($("#buy_num")[0].value < ${goods.count}){
                $("#buy_num")[0].value++
            }
        }
        
        function buy() {
            var xmlhttp;
            var buy_count = $("#buy_num")[0].value;
            var url = "${ctx}/add_shopping_cart.do/" + buy_count + "/" + ${goods.goods_id};
            if (window.XMLHttpRequest)
            {// code for IE7+, Firefox, Chrome, Opera, Safari
                xmlhttp=new XMLHttpRequest();
            }
            else
            {// code for IE6, IE5
                xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlhttp.open("POST",url,true);
            xmlhttp.send();
            $("#successModel").modal();
        }
    </script>
</rapid:override>

<%@include file="../../base.jsp" %>