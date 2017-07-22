<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<rapid:block name="head">
    <title>海海商城</title>
</rapid:block>
<rapid:block name="content">
    <div style="margin-top: 80px;"></div>
    <p class="col-sm-5" style="color: #2aabd2;font-size: 30px;">基本信息</p></br></br></br>
    <form id="change_information_form" class="form-horizontal" role="form" action="/change_information.do"
          method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group">
            <label class="col-sm-4 control-label">用户名</label>
            <div class="col-sm-1 control-label">
                <input  readonly  unselectable="on"  id="username" name="username" value="${user.username}">
            </div>
        </div>
        <div class="form-group">
            <label for="nick_name" class="col-sm-4 control-label">昵称</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="nick_name"
                       name="nick_name" value="${user.nick_name}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">性别</label>
            <div for="sex" class="col-sm-1 control-label">
                <input type="radio" id="male" name="sex" value="0">男
            </div>
            <div for="female" class="col-sm-1 control-label">
                <input type="radio" id="female" name="sex" value="1">女
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-4 control-label">邮箱</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="email"
                       name="email" value="${user.email}">
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-4 control-label">电话</label>
            <div class="col-sm-4">
                <input type="tel" class="form-control" id="phone"
                       name="phone" value="${user.phone}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-4">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </form>
    <p class="col-sm-5" style="color: #2aabd2;font-size: 30px;">实名认证</p></br></br></br>
    <form id="authentication_form" class="form-horizontal" role="form" action="/authentication.do" method="post">
        <input type="hidden" id="user_name" name="user_name" value="${user.username}">
        <div class="form-group">
            <label for="name" class="col-sm-4 control-label">真实姓名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="name"
                       name="name" value="${user.name}">
            </div>
        </div>
        <div class="form-group">
            <label for="id_card" class="col-sm-4 control-label">身份证号</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="id_card"
                       name="id_card" value="${user.id_card}">
            </div>
        </div>
        <div class="form-group">
            <label for="image" class="col-sm-4 control-label">头像</label></br>
            <div class="col-sm-8">
                <img width="150" height="200"
                     src="/images/temp.jpg" alt="图片显示失败"></br>
                <button id="upload_image_button" onblur="saveImageSrc()">上传</button>
                <input type="hidden" id="image" name="image">
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
<script type="text/javascript">
function saveImageSrc() {
    var imageSrc=$("img")[0].src;
    $("#image")[0].value=imageSrc;
    alert($("#image")[0].value);
}
</script>
</rapid:block>
<%@include file="../base.jsp" %>
