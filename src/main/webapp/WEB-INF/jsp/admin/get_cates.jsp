<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>

<rapid:override name="head">
    <title>分类管理</title>
</rapid:override>
<rapid:override name="cate_detail">
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
            <c:forEach items="${cates}" var="cate">
                <div class="col-lg-4">
                <h4>${cate.cate_name}</h4>
                </div>
                <div class="col-lg-8">
                    <h4><a class="btn btn-default" href="/admin/edit_cate/${cate.cate_id}">编辑</a> </h4>
                </div>
            </c:forEach>
    </div>

</rapid:override>

<%@include file="cate_manage.jsp" %>


