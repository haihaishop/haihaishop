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
        <form action="/admin/add_cate_post" method="post" class="form-inline" role="form">
            <div class="form-group">
                <label class="sr-only" for="cate_name">类别名称</label>
                <input type="text" class="form-control" id="cate_name" name="cate_name" placeholder="请输入名称" required>
            </div>
            <button type="submit" class="btn btn-default">提交</button>
        </form><br/>
        <ul>
    <c:forEach items="${cates}" var="cate">
        <h4><li class="tab"><strong>${cate.cate_name}</strong></li></h4>
    </c:forEach>
        </ul>
    </div>

</rapid:override>

<%@include file="cate_manage.jsp" %>


