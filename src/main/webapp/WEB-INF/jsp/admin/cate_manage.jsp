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
            <h4><a href="/admin/add_cate">增加分类</a> </h4>
            <h4><a href="/admin/delete_cate">删除分类</a> </h4>
            <h4><a href="/admin/get_cates">查看/编辑分类</a> </h4>

        </div>
        <div class="col-lg-10">
            <rapid:block name="cate_detail"/>
        </div>
    </div>
</rapid:override>

<%@include file="homepage.jsp" %>


