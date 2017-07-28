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
    <c:if test="${!empty hasUser}">
        <div id="myAlert" class="alert alert-warning">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong>警告！</strong>${hasUser}。
        </div>
    </c:if>
    <c:if test="${!empty regSuccess}">
        <div id="myAlert" class="alert alert-success">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong>恭喜！</strong>${regSuccess}。
        </div>
    </c:if>
    <div class="center-block">
        <h3>请编辑信息</h3>
    </div>
    <form id="add_form" method="post" action="/super_admin/add_user_post" role="form">
            <div class="form-group">
                <label class="col-sm-4 control-label" for="username">请输入用户名</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="请输入名称" required>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="password">请输入密码</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
        <div class="form-group">
            <label class="col-sm-4 control-label" for="password">请再次输入密码</label>
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


