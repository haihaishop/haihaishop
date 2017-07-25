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
        <div class="row col-sm-offset-2">
            <h4>收货人地址</h4><br>
            <label class="control-label">请选择</label><br>
            <div class="form-inline">
                <div data-toggle="distpicker">
                    <div class="form-group">
                        <label class="sr-only" for="province">Province</label>
                        <select class="form-control" id="province"></select>
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="city">City</label>
                        <select class="form-control" id="city"></select>
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="district">District</label>
                        <select class="form-control" id="district"></select>
                    </div>
                </div>
            </div><br>
            <div class="form-group">
                <label class="control-label">详细地址</label>
                <input type="text" class="form-control" id="detail_address" style="width: 50%" placeholder="请输入详细地址">
            </div>
        </div>
        <div class="row col-sm-offset-2">
            <h4>支付方式</h4><br>
            <div class="form-inline">
                <button class="form-control">银行卡支付</button>
                <button class="form-control">支付宝支付</button>
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
                    <a href="/order_information.do">
                        <button class="form-control" style="width: 100%;background-color: lightseagreen">确认支付</button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</rapid:override>
<rapid:override name="scripts">
    <script src="/js/distpicker.data.js"></script>
    <script src="/js/distpicker.js"></script>
    <script src="/js/main.js"></script>
</rapid:override>

<%@include file="../base.jsp" %>

