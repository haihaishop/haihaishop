<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>
<rapid:override name="head">
    <link href="/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
    <title>增加商品</title>
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

    <div  class="row">
        <div class="col-sm-8">
            <img src="${goods.picture}" class="img-responsive center-block" height="200" width="200">
        </div>
    </div>
    <form id="create_shop_form" enctype="multipart/form-data" class="form-horizontal" role="form" action="/shop_admin/edit_goods_post" method="post">
        <div class="form-group">
            <label for="goods_logo" class="col-sm-2 control-label">商品图片</label>
            <div class="col-sm-4">
                <input id="goods_logo" type="file" multiple class="file col-sm-4 control-label" name="goods_image">
            </div>
        </div>
        <input type="hidden" name="picture" id="logo" value="${goods.picture}">
        <div class="form-group">
            <label for="goods_name" class="col-sm-2 control-label">商品名称</label>
            <div class="col-sm-4">
                <input type="text" class="col-sm-8 form-control" name="goods_name"
                       id="goods_name" value="${goods.goods_name}" required>
            </div>
        </div>
        <div class="form-group">
            <label for="goods_info" class="col-sm-2 control-label">商品简介</label>
            <div class="col-sm-4">
                <textarea class="form-control col-sm-8" rows="3" name="info"
                          id="goods_info"  required>${goods.info}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2">商品分类</label>
            <div class="col-sm-4">
                <c:forEach items="${cates}" var="cate">
                    <label class="checkbox-inline">
                        <input type="checkbox" name="cate[]" value="${cate.cate_id}"
                        <c:forEach  items="${goodsCates}" var="goodsCate">
                            <c:if test="${goodsCate.cate_id == cate.cate_id}">
                                checked
                            </c:if>
                        </c:forEach> > ${cate.cate_name}
                    </label>
                </c:forEach>
            </div>
        </div>
        <div class="form-group">
            <label for="goods_count" class="col-sm-2 control-label">商品库存（个、件、斤..）</label>
            <div class="col-sm-4">
                <input type="text" class="col-sm-8 form-control" name="count"
                       id="goods_count" value="${goods.count}" required>
            </div>
        </div>
        <div class="form-group">
            <label for="goods_price" class="col-sm-2 control-label">商品单价</label>
            <div class="col-sm-4">
                <input type="text" class="col-sm-8 form-control" name="price"
                       id="goods_price" value="${goods.price}" required>
            </div>
        </div>
        <div class="form-group">
            <label for="goods_count" class="col-sm-2 control-label">商品优惠（请在下面选择）</label>
            <div class="col-sm-4">
                <select class="form-control col-sm-8" id="type" name="promotion_id">
                    <option value="">没有优惠</option>
                    <c:forEach items="${promotions}" var="promotion">
                        <option value="${promotion.promotion_id}"
                        <c:if test="${goods.promotion_id==promotion.promotion_id}">selected</c:if>
                        > ${promotion.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <input type="hidden" name="goods_id" value="${goods.goods_id}">
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
        $("#goods_logo").fileinput({
            language: 'zh', //设置语言
            showCaption: false,//是否显示标题
            showUpload:true,//是否显示上传按钮
            dropZoneEnabled: false,//是否显示拖拽区域
            uploadUrl: '${ctx}/shop_admin/goodsImage', //上传的地址
            browseClass: "btn btn-primary", //按钮样式
            previewFileType:['jpg','png','gif','bmp','jpeg'],
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            allowedFileExtensions:['jpg','png','gif','bmp','jpeg'],//接收的文件名后缀
            maxFileCount: 1, //最大文件数量
            required:true
        }).on("fileuploaded", function(e, data) {
            var res = data.response;
            $("#content").text(res.msg);
            $('#alert').modal();
            $("#logo").attr("value", res.path);
        })
        ;

    </script>

</rapid:override>
<%@include file="../../shop/shop_admin/shop_base.jsp" %>
