<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>
<rapid:override name="head">
    <title>海海商城首页</title>
</rapid:override>
<rapid:override name="content">
    <c:if test="${!empty loginFailed}">
        <div id="myAlert" class="alert alert-warning">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong class="col-sm-offset-5">${loginFailed}。</strong>
        </div>
    </c:if>
    <form class="form-horizontal" role="form" action="/login_form.do" method="post">
        <div class="form-group">
            <label for="username" class="col-sm-4 control-label">用户名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="username"
                       placeholder="请输入用户名" name="username">
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
            <label for="password" class="col-sm-4 control-label"></label>
            <div >
                <input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/> 记住我

            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-4">
                <a href="/register.do"><p>没有账号，点此注册</p></a>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-4">
                <button type="submit" class="btn btn-default">登陆</button>
            </div>
        </div>
    </form>
</rapid:override>
<rapid:override name="scripts">
    <script type="text/javascript" src="/js/bootstrapValidator.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#add_form').bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields:{
                    username:{
                        group: '.col-lg-4',
                        validators: {
                            notEmpty: {
                                message: '请填写用户名'
                            },
                            stringLength: {
                                min: 6,
                                max: 20,
                                message: '用户名长度在6-20之间。请检查'
                            },
                            regexp: {
                                regexp: /^[a-zA-Z0-9_]+$/,
                                message: '用户名由字母和数字组成，请检查'
                            }
                        }
                    },
                    password: {
                        validators: {
                            notEmpty: {
                                message: '请输入密码'
                            }
                        }
                    },
                }
            });
        });
    </script>
</rapid:override>
<%@include file="../base.jsp" %>

