<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>

<rapid:override name="head">
    <link href="/css/bootstrapValidator.min.css" rel="stylesheet">
    <title>注册</title>
</rapid:override>
<rapid:override name="content">
    <c:if test="${!empty hasUser}">
        <div id="myAlert" class="alert alert-warning">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong class="col-sm-offset-5">${hasUser}。</strong>
        </div>
    </c:if>
    <form id="add_user_form" class="form-horizontal" role="form" action="/user_register.do" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
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
</rapid:override>
<rapid:override name="scripts">
    <script type="text/javascript" src="/js/bootstrapValidator.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#add_user_form').bootstrapValidator({
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
                            },
                            identical: {
                                field: 'passwordAgain',
                                message: '两次密码输入不一致'
                            }
                        }
                    },
                    passwordAgain: {
                        validators: {
                            notEmpty: {
                                message: '请再次输入密码'
                            },
                            identical: {
                                field: 'password',
                                message: '两次密码输入不一致'
                            }
                        }
                    },
                    email: {
                        validators: {
                            notEmpty: {
                                message: '邮件不能为空'
                            },
                            emailAddress: {
                                message: '请输入正确的邮件地址如：123@163.com'
                            }
                        }
                    },
                    phone: {
                        message: 'The phone is not valid',
                        validators: {
                            notEmpty: {
                                message: '手机号码不能为空'
                            },
                            stringLength: {
                                min: 11,
                                max: 11,
                                message: '请输入11位手机号码'
                            },
                            regexp: {
                                regexp: /^1[3|5|8]{1}[0-9]{9}$/,
                                message: '请输入正确的手机号码'
                            }
                        }
                    },
                    role:{
                        validators:{
                            notEmpty:{
                                message:'未选择类型'
                            }
                        }
                    }
                }
            });
        });
    </script>
</rapid:override>
<%@include file="../base.jsp" %>

