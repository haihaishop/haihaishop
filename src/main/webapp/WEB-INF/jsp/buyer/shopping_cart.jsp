<%--
  Created by IntelliJ IDEA.
  User: 18240
  Date: 2017/7/24
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<rapid:override name="head">
    <title>购物车</title>
</rapid:override>
<rapid:override name="content">
    <c:if test="${!empty failed}">
        <div id="myAlert" class="alert alert-warning">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong class="col-sm-offset-5">${failed}。</strong>
        </div>
    </c:if>
    <div class="container-fluid">
        <form action="/place_order.do">
            <table class="table col-md-offset-1" style="width: 80%">
                <thead>
                <tr>
                    <th><input type="checkbox" onclick="return chooseAll()">全选</th>
                    <th>商品</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>总计</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orderGoodsList}" var="orderGoods">
                    <input hidden class="buy_number" value=${orderGoods.order.buy_number}>
                    <input hidden class="unit_price" value=${orderGoods.order.unit_price}>
                    <input hidden class="order_id" value=${orderGoods.order.order_form_id}>
                    <tr>
                        <td>
                            <input onclick="return chooseGoods()" type="checkbox" name="orderId[]" class="choose"
                                   value="${orderGoods.order.order_form_id}">
                        </td>
                        <td style="overflow: hidden">
                            <img src="${orderGoods.goods.picture}" style="width: 100px;height: 150px"
                                 class="form-group col-md-2"
                                 alt="图片加载失败">
                            <div style="height: 150px">
                                <h4>${orderGoods.goods.goods_name}</h4>
                                <p style="word-break: break-all;">${orderGoods.goods.info}</p>
                            </div>
                        </td>
                        <td>
                            <strong>￥${orderGoods.order.unit_price}</strong>
                        </td>
                        <td>
                            <p>${orderGoods.order.buy_number}</p>
                        </td>
                        <td>
                            <strong>￥${orderGoods.order.buy_number * orderGoods.order.unit_price}</strong>
                        </td>
                        <td>
                            <a href="/delete_order/${orderGoods.order.order_form_id}">
                                <button type="button" class="btn-default">删除</button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="row">
                <div class="form-inline">
                    <div class="col-md-2 col-sm-offset-5">
                        <h4>总价:￥<span id="sum">0</span></h4>
                    </div>
                    <div class="col-md-2">
                        <input type="submit" class="form-control"
                               style="width: 100%;background-color: lightseagreen"
                               value="下单">
                    </div>
                </div>
            </div>
        </form>
    </div>
</rapid:override>
<rapid:override name="scripts">
    <script type="text/javascript">
        function chooseGoods() {
            var sum = 0;
            var choose = $(".choose");
            var price = $(".unit_price");
            var number = $(".buy_number");
            for (var i = 0; i < choose.length; i++) {
                if (choose[i].checked) {
                    sum += parseFloat(price[i].value) * parseFloat(number[i].value)
                }
            }
            $("#sum")[0].innerHTML = sum
        }

        function chooseAll() {
            alert(1)
            var choose = $(".choose")
            if (this.checked) {
                for (var i = 0; i < choose.length; i++) {
                    choose[i].checked = true
                }
            } else {
                for (var i = 0; i < choose.length; i++) {
                    choose[i].checked = false
                }
            }
            chooseGoods()
        }
    </script>
</rapid:override>
<%@include file="../base.jsp" %>