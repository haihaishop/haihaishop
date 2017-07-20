<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>

<rapid:override name="head">
    <title>查看管理员</title>
</rapid:override>
<rapid:override name="detail">
    <div class="center-block">
        <h2>管理员信息：</h2>
        <c:forEach items="${admins}" var="admin">
            <h3>管理员：${admin.username}</h3>
        </c:forEach>
    </div>
</rapid:override>
<%@include file="homepage.jsp" %>
