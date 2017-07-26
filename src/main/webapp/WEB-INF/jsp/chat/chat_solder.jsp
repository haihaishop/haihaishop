<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<rapid:override name="head">
    <link href="/css/chat.css" rel="stylesheet">
    <title>聊天</title>
</rapid:override>
<rapid:override name="content">
    <div id="message"></div>
    <br>
    <input type="hidden" id="clientID" value="${clientId}">
    <input type="hidden" id="toID" value="${toId}">
    <input type="hidden" id="theMessage">

    <div class="container">
        <div class="row form-group">
            <div class="col-md-2 col-lg-2">
                <ul class="chat_list">

                </ul>
            </div>
            <div class="col-xs-12 col-md-offset-2 col-md-8 col-lg-8 col-lg-offset-2 " id="chat_content">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <span class="glyphicon glyphicon-comment"></span>与买家聊天
                    </div>
                    <div class="panel-body body-panel">
                        <ul class="chat" id="chat">
                        </ul>
                    </div>
                    <div class="panel-footer clearfix">
                        <textarea class="form-control" id="sendMsg" rows="3"  cols="50"></textarea>
                        <span class="col-lg-6 col-lg-offset-3 col-md-6 col-md-offset-3 col-xs-12"
                              style="margin-top: 10px">
                        <button id='sendBtn' onclick="sendMessage()" class="btn btn-warning btn-lg btn-block" id="btn-chat">发送</button>
                    </span>
                    </div>
                </div>
            </div>
        </div>
    </div>

</rapid:override>
<rapid:override name="scripts">
    <script type="text/javascript" src="/js/mqttws31.js"></script>
    <script type="text/javascript">
        var client;
        var clientID;
        var toIds =[];
        $(window).on('beforeunload', function () {
            client = null;
            window.opener.document.getElementById("flag").value = "0";
        });
        $(function ($) {
            //$('#con').bind('click',function(){
            clientID = $('#clientID').val();
            client = new Messaging.Client('127.0.0.1', 61614, clientID);
            client.onConnectionLost = function () {
            };
            //收到消息
            client.onMessageArrived = function (message) {
                var msgObj = jQuery.parseJSON(message.payloadString);
                clientID = $('#clientID').val();
                if (msgObj.to === clientID) {
                    //if(msgObj.from in toIds);
                    $('#toID').val(msgObj.from);
                    $("#chat").append('<li class="left clearfix">\n' +
                        '            <div class="chat-body clearfix">\n' +
                        '                <div class="header">\n' +
                        '                    <strong class="pull-left primary-font">'
                        +msgObj.from+
                        '</strong>\n' +
                        '                </div>\n' +
                        '                <br/><p>' +
                        msgObj.body +
                        '                </p>\n' +
                        '            </div>\n' +
                        '        </li>');
                }
            };
            //建立连接和订阅
            client.connect({
                onSuccess: function () {
                    //订阅topic
                    client.subscribe("topic");
                }
            });
            //});
        });
    </script>
    <script type="text/javascript">
        window.onunload = function () {
            client.disconnect();
        }
    </script>
    <script type="text/javascript">
        function changeMessage() {
            
        }
    </script>
    <script type="text/javascript">
        function sendMessage() {
            clientID = $('#clientID').val();
            var msg = {};
            msg.from = clientID;
            msg.to = $('#toID').val();
            msg.body = $('#sendMsg').val();
            $("#sendMsg").val("");
            message = new Messaging.Message(JSON.stringify(msg));
            message.destinationName = "topic";
            client.send(message);
            $("#chat").append('<li class="right clearfix">\n' +
                '            <div class="chat-body clearfix">\n' +
                '                <div class="header">\n' +
                '                    <strong class="pull-right primary-font">'
                +msg.from+
                '</strong>\n' +
                '                </div>\n' +
                '                <br/><p >' +
                msg.body +
                '                </p>\n' +
                '            </div>\n' +
                '        </li>');
        };
    </script>
</rapid:override>>
<%@include file="../base.jsp" %>
