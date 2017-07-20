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
    <form action="/admin/edit_cate_post" method="post" class="form-inline" role="form">
        <div class="form-group">
            <label class="sr-only" for="cate_name">类别名称</label>
            <input type="text" class="form-control" id="cate_name" name="cate_name" placeholder="请输入名称" required
            value="${cateToEdit.cate_name}">
        </div>
        <input value="${cateToEdit.cate_id}" type="hidden" name="cate_id">
        <button type="submit" class="btn btn-default">提交</button>
    </form><br/>
    <div class="row">
        <c:forEach items="${cates}" var="cate">
            <div class="col-lg-4">
                <h4>${cate.cate_name}</h4>
            </div>
            <div class="col-lg-8">
                <h4><a href="admin/edit_cate/${cate.cate_id}">编辑</a> </h4>
            </div>
        </c:forEach>
    </div>

</rapid:override>

<%@include file="cate_manage.jsp" %>


