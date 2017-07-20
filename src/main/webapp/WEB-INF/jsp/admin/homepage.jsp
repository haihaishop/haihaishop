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
                <div class="col-lg-2">
            <h3>
                    <a href="/admin/cate_manage" style="text-shadow: blue 2px 2px 5px; width:100%">分类管理</a>
                </h3>

                <h3>
                    <a href="/admin/promote_manage" style="text-shadow: blue 2px 2px 5px; width:100%">活动管理</a>
                </h3>

                <h3>
                    <a href="/admin/change_password" style="text-shadow: blue 2px 2px 5px; width:100%">修改密码</a>
                </h3>
                </div>
                <div class="col-lg-10">
                    <rapid:block name="detail">
                    </rapid:block>
                </div>

        </div>
    </div>

</rapid:override>
<%@include file="../base.jsp" %>


