<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<rapid:override name="head">
    <link href="/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
    <title>海海商城</title>
</rapid:override>
<rapid:override name="content">
    <div style="margin-top: 80px;"></div>
    <p class="col-sm-5" style="color: #2aabd2;font-size: 30px;">基本信息</p></br></br></br>
    <form id="change_information_form" class="form-horizontal" role="form" action="/change_information.do"
          method="post">
        <div class="form-group">
            <label for="user_image" class="col-sm-4 control-label">头像</label>
            <div class="col-sm-4">
                <img src="${user.image}" height="200" width="160">
                <input id="user_image" type="file" multiple class="file col-sm-4 control-label" name="user_image">
            </div>
    </div>
        <input type="hidden" name="image" id="image" value="${user.image}">
        <input type="hidden" name="username" value="${user.username}">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group">
            <label class="col-sm-4 control-label">用户名</label>
            <div class="col-sm-4 control-label">
                <input  disabled class="form-control" value="${user.username}">
            </div>
        </div>
        <div class="form-group">
            <label for="nick_name" class="col-sm-4 control-label">昵称</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="nick_name"
                       name="nick_name" value="${user.nick_name}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">性别</label>
            <div for="sex" class="col-sm-1 control-label">
                <input type="radio" id="male" name="sex" value="0"
                    <c:if test="${user.sex == false}">checked</c:if> >男
            </div>
            <div for="female" class="col-sm-1 control-label">
                <input type="radio" id="female" name="sex" value="1"
                       <c:if test="${user.sex == true}">checked</c:if> >女
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-4 control-label">邮箱</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="email"
                       name="email" value="${user.email}">
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-4 control-label">电话</label>
            <div class="col-sm-4">
                <input type="tel" class="form-control" id="phone"
                       name="phone" value="${user.phone}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-4">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </form>
    <p class="col-sm-5" style="color: #2aabd2;font-size: 30px;">实名认证</p></br></br></br>
    <form id="authentication_form" class="form-horizontal" role="form" action="/authentication.do" method="post">
        <input type="hidden" id="user_name" name="user_name" value="${user.username}">
        <div class="form-group">
            <label for="name" class="col-sm-4 control-label">真实姓名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="name"
                       name="name" value="${user.name}">
            </div>
        </div>
        <div class="form-group">
            <label for="id_card" class="col-sm-4 control-label">身份证号</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="id_card"
                       name="id_card" value="${user.id_card}">
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-4">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </form>
</rapid:override>
<rapid:override name="scripts">
    <!-- piexif.min.js is only needed if you wish to resize images before upload to restore exif data.
    This must be loaded before fileinput.min.js -->
    <script src="/js/plugins/piexif.min.js" type="text/javascript"></script>
    <!-- sortable.min.js is only needed if you wish to sort / rearrange files in initial preview.
    This must be loaded before fileinput.min.js -->
    <script src="/js/plugins/sortable.min.js" type="text/javascript"></script>
    <!-- purify.min.js is only needed if you wish to purify HTML content in your preview for HTML files.
    This must be loaded before fileinput.min.js -->
    <script src="/js/plugins/purify.min.js" type="text/javascript"></script>
    <!-- the main fileinput plugin file -->
    <script src="/js/fileinput.min.js"></script>
    <!-- bootstrap.js below is needed if you wish to zoom and view file content
    in a larger detailed modal dialog -->
    <!-- optionally if you need a theme like font awesome theme you can include
    it as mentioned below -->
    <script src="/themes/fa/theme.js"></script>
    <!-- optionally if you need translation for your language then include
    locale file as mentioned below -->
    <script src="/js/locales/zh.js"></script>

    <script type="text/javascript">
        // with plugin options
        $("#user_image").fileinput({
            language: 'zh', //设置语言
            showCaption: false,//是否显示标题
            showUpload:true,//是否显示上传按钮
            dropZoneEnabled: false,//是否显示拖拽区域
            uploadUrl: '${ctx}/user_image', //上传的地址
            browseClass: "btn btn-primary", //按钮样式
            previewFileType:['jpg','png','gif','bmp','jpeg'],
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            allowedFileExtensions:['jpg','png','gif','bmp','jpeg'],//接收的文件名后缀
            maxFileCount: 1, //最大文件数量
        }).on("fileuploaded", function(e, data) {
            var res = data.response;
            $("#content").text(res.msg);
            $('#alert').modal();
            $("#image").attr("value", res.path);
        })
        ;
    </script>
</rapid:override>
<%@include file="../base.jsp" %>
