<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>
<rapid:override name="head">
    <title>管理活动</title>
</rapid:override>
<rapid:override name="shop_detail">
    <div class="modal fade" id="delcfmModel">
        <div class="modal-dialog">
            <div class="modal-content message_align">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">提示信息</h4>
                </div>
                <div class="modal-body">
                    <p>您确认要删除吗？</p>
                </div>
                <div class="modal-footer">
                    <input type="hidden" id="url"/>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <a  onclick="urlSubmit()" class="btn btn-success" data-dismiss="modal">确定</a>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <div class="row">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>活动名称</th>
                <th>类型</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${promotions}" var="promotion">
                <tr>
                    <th>${promotion.name}</th>
                    <th>
                        <c:choose>
                            <c:when test="${promotion.type == 1}">
                                打折
                            </c:when>
                            <c:when test="${promotion.type == 2}">
                                满减
                            </c:when>
                            <c:when test="${promotion.type == 3}">
                                几减几
                            </c:when>
                        </c:choose>
                    </th>
                    <th><a href="/shop_admin/${promotion.promotion_id}_edit_promotion" class="button">编辑</a>
                    <a onclick="delete_promotion('${ctx}/shop_admin/${promotion.promotion_id}_delete_promotion')">删除</a> </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
</rapid:override>
<rapid:override name="scripts">
    <script type="text/javascript">
        function delete_promotion(url) {
            $('#url').val(url);//给会话中的隐藏属性URL赋值
            $('#delcfmModel').modal();
        }
        function urlSubmit(){
            var url=$.trim($("#url").val());//获取会话中的隐藏属性URL
            window.location.href=url;
        }
    </script>
</rapid:override>
<%@include file="shop_base.jsp" %>
