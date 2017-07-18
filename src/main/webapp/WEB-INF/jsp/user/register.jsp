<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<rapid:override name="head">
    <title>海海商城首页</title>
</rapid:override>
<rapid:override name="content">
    <form class="form-horizontal" role="form" action="/user_register.do" method="post">
        <div class="form-group">
            <label for="login_name" class="col-sm-4 control-label">用户名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="login_name"
                       placeholder="请输入用户名" name="login_name">
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
            <div class="col-sm-offset-4 col-sm-4">
                <div class="checkbox">
                    <label>
                        <input type="radio" name="role" value="seller">卖家
                        <input type="radio" name="role" value="buyer">买家
                    </label>
                </div>
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
</rapid:override>
<%@include file="../base.jsp" %>

