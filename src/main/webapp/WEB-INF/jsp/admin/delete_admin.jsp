<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>

<rapid:override name="head">
    <title>删除管理员</title>
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
    <div class="row">
        <div class="center-block">
            <h2>管理员信息：</h2>
            <c:forEach items="${admins}" var="admin">
                <div class="col-lg-8">
                    <h3>管理员：${admin.login_name}</h3>
                </div>
                <div class="col-lg-4">
                    <h3><a href="/super_admin/delete_admin/${admin.login_name}">删除</a></h3>
                </div>
            </c:forEach>
        </div>
    </div>
</rapid:override>
<%@include file="homepage.jsp" %>
