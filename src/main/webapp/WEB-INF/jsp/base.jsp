<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <!-- Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <rapid:block name="head">
        <title>海海商城</title>
    </rapid:block>

</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <!-- The mobile navbar-toggle button can be safely removed since you do not need it in a non-responsive implementation -->
            <a class="navbar-brand" href="/">海海商城</a>
        </div>
        <!-- Note that the .navbar-collapse and .collapse classes have been removed from the #navbar -->
        <div id="navbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/">主页</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${empty SPRING_SECURITY_CONTEXT}">
                    <li><a href="/register.do">注册</a></li>
                    <li><a href="/login.do">登陆</a></li>
                </c:if>
                <c:if test="${!empty SPRING_SECURITY_CONTEXT}">
                    <li><a href="/information.do">${SPRING_SECURITY_CONTEXT.authentication.name}</a></li>
                    <c:if test="${user.has_store == true}">
                        <li><a href="/shop_admin/shop">我的店铺</a></li>
                    </c:if>
                    <c:if test="${user.has_store == false}">
                        <li><a href="/shop_admin/create">免费开店</a></li>
                    </c:if>
                    <li><a href="/logout.do">注销</a></li>
                </c:if>
                <c:if test="${!empty SPRING_SECURITY_CONTEXT}">
                <li><a href="/shopping_cart.do">购物车</a></li>
                    <li><a href="/order_information.do">我的订单</a></li>
                </c:if>
                <li><a href="#">联系客服</a></li>
                <li><a href="#">反馈建议</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<div>
<div class="page-header">
</div>
    <div class="row">
        <rapid:block name="content">
        </rapid:block>
    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<rapid:block name="scripts">
</rapid:block>
</body>
</html>