<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<rapid:block name="head">
    <title>海海商城</title>
</rapid:block>
<rapid:block name="content">
    <form id="change_information_form" class="form-horizontal" role="form" action="/change_information.do" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group">
            <label for="username" class="col-sm-4 control-label">用户名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="username"
                       placeholder="请输入用户名" name="username" value=${user.username}>
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-4 control-label">密码</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="password"
                       placeholder="请输入密码" name="password">
            </div>
        </div>
        <div class="form-group">
            <label for="passwordAgain" class="col-sm-4 control-label">确认密码</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="passwordAgain"
                       placeholder="请确认密码" name="passwordAgain">
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-4 control-label">邮箱</label>
            <div class="col-sm-4">
                <input type="email" class="form-control" id="email"
                       placeholder="请输入邮箱" name="email">
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-4 control-label">电话</label>
            <div class="col-sm-4">
                <input type="tel" class="form-control" id="phone"
                       placeholder="请输入电话" name="phone">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <a href="/login.do"><p>已有账号，点此登陆</p></a>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-4">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </form>
</rapid:block>
<rapid:block name="scripts">

</rapid:block>
<%@include file="../base.jsp" %>
