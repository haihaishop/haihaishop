<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>

<rapid:override name="head">
    <title>分类管理</title>
</rapid:override>
<rapid:override name="promotion_detail">
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
        <table class="table table-striped">
            <thead>
            <tr>
                <th>活动名称</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${promotions}" var="promotion">
                <tr>
                    <th>${promotion.name}</th>
                    <th><a href="${ctx}/admin/edit_promotion/${promotion.promotion_id}" class="button">编辑</a>
                        <a onclick="delete_promotion('${ctx}/admin/delete_promotion/${promotion.promotion_id}')">删除</a> </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</rapid:override>

<%@include file="promotion_manage.jsp" %>


