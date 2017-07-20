
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<rapid:block name="head">
    <title>海海商城</title>
</rapid:block>
<rapid:block name="content">
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span4">
            </div>
            <div class="span4">
                <h3>账户信息</h3>
            </div>
            <div class="span4">
            </div>
        </div>
        <div class="row-fluid">
            <div class="col-xs-3 span3">
            </div>
            <div class="col-xs-3 span3">
                <p>登录名：${user.username}</p>
                <p>昵称：${user.nick_name}</p>
                <p>邮箱：${user.email}</p>
                <p>电话：${user.phone}</p>
            </div>
            <div class="col-xs-3 span3">
                <p>真实姓名：${user.name}</p>
                <p>注册时间：<fmt:formatDate value="${user.create_date}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                <p>性别：${user.sex}</p>
                <button class="btn btn-large" type="button">完善信息</button>
            </div>
            <div class="col-xs-3 span3">
            </div>
        </div>
    </div>
</rapid:block>
<rapid:block name="scripts">

</rapid:block>
<%@include file="../base.jsp" %>
