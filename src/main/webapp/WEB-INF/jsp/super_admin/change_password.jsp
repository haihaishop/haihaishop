<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>

<rapid:override name="head">
    <link href="/css/bootstrapValidator.min.css" rel="stylesheet">
    <title>增加管理员</title>
</rapid:override>
<rapid:override name="detail">
    <c:if test="${!empty warning}">
        <div id="myAlert" class="alert alert-warning">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong>警告！</strong>${warning}。
        </div>
    </c:if>
    <c:if test="${!empty success}">
        <div id="myAlert" class="alert alert-success">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong>恭喜！</strong>${success}。
        </div>
    </c:if>
    <div class="center-block">
        <h3>请编辑</h3>
    </div>
    <form id="add_form" method="post" action="/super_admin/change_password_post" role="form">
        <div class="form-group">
            <label class="col-sm-4 control-label" for="oldPassword">请输入旧密码</label>
            <input type="password" class="form-control" id="oldPassword" name="oldPassword"  required>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label" for="password">请输入新密码</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label" for="password">请再次输入新密码</label>
            <input type="password" class="form-control" id="password1" name="password1" required>
        </div>

        <button type="submit" class="btn btn-default" id="submit_button">提交</button>
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
                    oldPassword:{
                        group: '.col-lg-4',
                        validators: {
                            notEmpty: {
                                message: '请填写用原密码'
                            }
                        }
                    },
                    password: {
                        validators: {
                            notEmpty: {
                                message: '请输入密码'
                            },
                            identical: {
                                field: 'password1',
                                message: '两次密码输入不一致'
                            }
                        }
                    },
                    password1: {
                        validators: {
                            notEmpty: {
                                message: '请再次输入密码'
                            },
                            identical: {
                                field: 'password',
                                message: '两次密码输入不一致'
                            }
                        }
                    }
                }
            });
        });
    </script>
</rapid:override>
<%@include file="homepage.jsp" %>


