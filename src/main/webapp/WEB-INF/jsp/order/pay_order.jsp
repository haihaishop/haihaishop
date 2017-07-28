<%--
  Created by IntelliJ IDEA.
  User: 18240
  Date: 2017/7/25
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page isELIgnored="false" %>

<rapid:override name="head">
    <link href="/css/main.css" rel="stylesheet">
    <title>支付订单</title>
</rapid:override>
<rapid:override name="content">
    <div class="container-fluid">
        <div class="row col-sm-offset-2">
            <h3>填写并核对订单信息</h3>
        </div>
        <div class="row col-sm-offset-2">
            <h4>收货人信息</h4><br>
            <div class="form-group">
                姓名：<input type="text" style="width: 50%" class="form-control"><br>
                联系方式：<input type="text" style="width: 50%" class="form-control">
            </div>
        </div>
        <form action="/pay_order.do" method="post">
            <input hidden id="choice" name="choice" type="number">
            <div class="form-inline">
                <h4 class="form-group col-md-offset-2">选择地址</h4>
                <button type="button" onclick="addAddress()" class="form-group col-md-offset-3" id="addAddressButton">新增地址</button>
            </div>
            <div class="row" id="choose">
                <c:forEach items="${addressList}" var="address">
                    <div id="address" class="row col-sm-offset-3">
                        <div class="row">
                            <input onclick="return choose()" type="radio" class="addressChoose" name="addressChoose" value=${address.address_id}>
                                ${address.province}${address.city}${address.country}${address.detail_address}
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div hidden id="addAddress" class="row col-sm-offset-2">
                <h4>收货人地址</h4><br>
                <label class="control-label">请选择</label><br>
                <div class="form-inline">
                    <div data-toggle="distpicker">
                        <div class="form-group">
                            <label class="sr-only" for="province">Province</label>
                            <select class="form-control" name="province" id="province"></select>
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="city">City</label>
                            <select class="form-control" id="city" name="city"></select>
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="country">District</label>
                            <select class="form-control" id="country" name="country"></select>
                        </div>
                    </div>
                </div>
                <br>
                <div class="form-group">
                    <label class="control-label">详细地址</label>
                    <input type="text" class="form-control" name="detail_address" id="detail_address" style="width: 50%"
                           placeholder="请输入详细地址">
                </div>
            </div>
            <div class="row col-sm-offset-2">
                <h4>支付方式</h4><br>
                <div class="form-inline">
                    <button type="button" class="form-control">银行卡支付</button>
                    <button type="button" class="form-control">支付宝支付</button>
                </div>
                    <%--<div class="form-group">
                        <label class="control-label" id="pay_way">银行卡</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" placeholder="请输入卡号" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">密码</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" placeholder="请输入密码" >
                        </div>
                    </div>--%>
            </div>

            <div class="row">
                <div class="form-inline">
                    <div class="col-md-2 col-sm-offset-5">
                        <h4>总价:￥<span id="sum">${total}</span></h4>
                    </div>
                    <div class="col-md-2">
                        <input type="submit" class="form-control" style="width: 100%;background-color: lightseagreen"
                               value="确认支付">
                    </div>
                </div>
            </div>
        </form>
    </div>
</rapid:override>
<rapid:override name="scripts">
    <script src="/js/distpicker.data.js"></script>
    <script src="/js/distpicker.js"></script>
    <script src="/js/main.js"></script>
    <script type="text/javascript">
        function addAddress() {
            $("#addAddress").prop("hidden",false);
            var choice = $(".addressChoose")
            for(var i=0;i<choice.length; i++){
                choice[i].checked = false
            }
            $("#choice").prop("value",null)
        }

        function choose() {
            var choice = $(".addressChoose")
            for(var i=0;i<choice.length; i++){
                if(choice[i].checked){
                    $("#choice").prop("value",parseInt(choice[i].value))
                }
            }
        }
    </script>
</rapid:override>

<%@include file="../base.jsp" %>

