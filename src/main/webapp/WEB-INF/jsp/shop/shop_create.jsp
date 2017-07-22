<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<rapid:override name="head">
    <link href="/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
    <title>创建商铺</title>
</rapid:override>
<rapid:override name="content">
    <form id="create_shop_form" class="form-horizontal" role="form" action="/create_shop_post.do" method="post">

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
    <script src="path/to/themes/fa/theme.js"></script>
    <!-- optionally if you need translation for your language then include
    locale file as mentioned below -->
    <script src="path/to/js/locales/<lang>.js"></script>
</rapid:override>>
<%@include file="../base.jsp" %>

