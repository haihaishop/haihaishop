<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<rapid:override name="head">
    <title>查看管理员</title>
</rapid:override>
<rapid:override name="detail">
    <div class="center-block">
        <h2>管理员信息：</h2>
        <div class="row">
            <div class="col-lg-4">
                <h3>管理员账号</h3>
            </div>
            <div class="col-lg-8">
                <h3>注册时间</h3>
            </div>
        </div>
        <c:forEach items="${admins}" var="admin">
            <div class="row">
                <div class="col-lg-4">
                    <h3>${admin.username}</h3>
                </div>
                <div class="col-lg-8">
                    <h3><fmt:formatDate value="${admin.create_date}" pattern="yyyy-MM-dd HH:mm:ss" /> </h3>
                </div>
            </div>
        </c:forEach>
    </div>
</rapid:override>
<%@include file="homepage.jsp" %>
