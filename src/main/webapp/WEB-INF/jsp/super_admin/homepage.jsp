<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<rapid:override name="head">
    <title>欢迎您，超级管理员</title>
</rapid:override>
<rapid:override name="content">
    <div class="container-fluid">
        <div class="jumbotron">
            <h2>超级管理员，欢迎您</h2>
            <p>请选择操作</p>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row " >
            <div class="col-lg-4">
                <h3>
                    <a href="/super_admin/add_admin" style="text-shadow: blue 2px 2px 5px; width:100%">增加管理员</a>
                </h3>

                <h3>
                    <a href="/super_admin/delete_admin" style="text-shadow: blue 2px 2px 5px; width:100%">删除管理员</a>
                </h3>
                <h3>
                <a href="/super_admin/get_admins" style="text-shadow: blue 2px 2px 5px; width:100%">查看管理员</a>
                </h3>
                <h3>
                    <a href="/super_admin/change_password" style="text-shadow: blue 2px 2px 5px; width:100%">修改密码</a>
                </h3>
            </div>
            <div class="col-lg-8">
                <rapid:block name="detail">

                </rapid:block>
            </div>
        </div>
    </div>

</rapid:override>
<%@include file="../base.jsp" %>


