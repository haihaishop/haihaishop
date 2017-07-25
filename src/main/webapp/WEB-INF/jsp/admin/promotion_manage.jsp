<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>

<rapid:override name="head">
    <title>分类管理</title>
</rapid:override>
<rapid:override name="detail">
    <div class="row">
        <div class="col-lg-2">

            <ul class="nav nav-tabs nav-stacked">
                <li><a href="/admin/add_promotion">增加活动</a> </li>
                <li><a href="/admin/get_promotions">查看/编辑活动</a> </li>
            </ul>
        </div>
        <div class="col-lg-10">
            <rapid:block name="promotion_detail"/>
        </div>
    </div>
</rapid:override>

<%@include file="homepage.jsp" %>


