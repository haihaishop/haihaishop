<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<rapid:override name="head">
    <title>欢迎您，管理员</title>
</rapid:override>
<rapid:override name="content">
    <div class="container-fluid">
        <div class="jumbotron">
            <h2>管理员，欢迎您</h2>
            <p>请选择操作</p>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row " >
            <rapid:block name="detail">
            <h3>
                    <a href="/super_admin/add_admin" style="text-shadow: blue 2px 2px 5px; width:100%">分类管理</a>
                </h3>

                <h3>
                    <a href="/super_admin/delete_admin" style="text-shadow: blue 2px 2px 5px; width:100%">活动管理</a>
                </h3>

                <h3>
                    <a href="/super_admin/change_password" style="text-shadow: blue 2px 2px 5px; width:100%">修改密码</a>
                </h3>
                </rapid:block>
        </div>
    </div>

</rapid:override>
<%@include file="../base.jsp" %>


