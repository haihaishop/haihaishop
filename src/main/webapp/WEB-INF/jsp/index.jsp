<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<rapid:override name="head">
    <title>海海商城首页</title>
</rapid:override>
<rapid:override name="content">
    <h1>你好，世界！</h1>
    <a href="/register.do"><h3>Click Me to Register</h3></a>
    <a href="/login.do"><h3>Click Me to Login</h3></a>
    <a href="/information.do"><h3>Click Me to See Information</h3></a>
    <a href="/buyer_home_page.do"><h3>Click Me to Go To Buy Things</h3></a>
</rapid:override>
<%@include file="base.jsp" %>

