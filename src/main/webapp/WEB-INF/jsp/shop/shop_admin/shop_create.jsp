<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>
<rapid:override name="head">
    <link href="/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
    <title>创建商铺</title>
</rapid:override>
<rapid:override name="shop_detail">

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
    <div class="modal fade" id="alert">
        <div class="modal-dialog">
            <div class="modal-content message_align">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">提示信息</h4>
                </div>
                <div class="modal-body">
                    <p id="content"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <form id="create_shop_form" enctype="multipart/form-data" class="form-horizontal" role="form" action="/shop_admin/create_shop_post" method="post">
        <div class="form-group">
            <label for="shop_logo" class="col-sm-2 control-label">商铺图片</label>
            <div class="col-sm-4">
            <input id="shop_logo" type="file" multiple class="file col-sm-4 control-label" name="shop_image">
            </div>
        </div>
        <input type="hidden" name="image" id="logo" value="${store.image}">
        <div class="form-group">
            <label for="store_name" class="col-sm-2 control-label">商铺名称</label>
            <div class="col-sm-4">
            <input type="text" class="col-sm-8 form-control" name="store_name"
                   value="${store.store_name}" id="store_name" required>
            </div>
        </div>
        <div class="form-group">
            <label for="store_info" class="col-sm-2 control-label">商铺简介</label>
            <div class="col-sm-4">
                <textarea class="form-control col-sm-4" rows="3" name="store_info"
                          id="store_info"  required>${store.store_info}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="shop_logo" class="col-sm-4 control-label">是否立即开通</label>
            <div class="col-sm-2">
                <input id="store_status" type="checkbox"  class="form-control "
                       name="store_status" value="1">
                <input type="hidden" value="0" name="_store_status"/>

            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-4">
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
            $("#shop_logo").fileinput({
                language: 'zh', //设置语言
                showCaption: false,//是否显示标题
                showUpload:true,//是否显示上传按钮
                dropZoneEnabled: false,//是否显示拖拽区域
                uploadUrl: '${ctx}/shop_admin/shopImage', //上传的地址
                browseClass: "btn btn-primary", //按钮样式
                previewFileType:['jpg','png','gif','bmp','jpeg'],
                previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
                allowedFileExtensions:['jpg','png','gif','bmp','jpeg'],//接收的文件名后缀
                maxFileCount: 1, //最大文件数量
            }).on("fileuploaded", function(e, data) {
            var res = data.response;
            $("#content").text(res.msg);
            $('#alert').modal();
            $("#logo").attr("value", res.path);
            })
            ;

    </script>

</rapid:override>>
<%@include file="shop_base.jsp" %>
