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
                <div class="list-group" id="chat_list">
                </div>
            </div>
            <div class="col-xs-12  col-md-8 col-lg-8  " id="chat_content">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <span class="glyphicon glyphicon-comment"></span>与买家聊天
                    </div>
                    <div class="panel-body body-panel">
                        <ul class="chat" id="chat">
                        </ul>
                    </div>
                    <div class="panel-footer clearfix">
                        <textarea class="form-control" id="sendingMsg" rows="3" cols="50"></textarea>
                        <span class="col-lg-6 col-lg-offset-3 col-md-6 col-md-offset-3 col-xs-12"
                              style="margin-top: 10px">
                        <button id='sendBtn' onclick="sendMessage()" class="btn btn-warning btn-lg btn-block"
                                id="btn-chat">发送</button>
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
        var clientID = $('#clientID').val();
        var toIds = [];
        var currentTo;
        $(function ($) {
            //$('#con').bind('click',function(){
            client = new Messaging.Client('106.15.205.112', 61614, clientID);
            client.onConnectionLost = function () {
            };
            client.onMessageArrived = function (message) {
                var msgObj = jQuery.parseJSON(message.payloadString);
                if (msgObj.to === clientID) {
                    //是自己的消息
                   // currentTo = msgObj.from;
                    var sender = msgObj.from;
                    if(sender === currentTo){
                        saveAndShowMessage(sender, sender, msgObj, "left");
                    }
                    else{
                        saveAndHintMessage(sender, msgObj);
                    }
                }
            };
            //建立连接和订阅
            client.connect({
                onSuccess: function () {
                    //订阅topic
                    console.log("ok");
                    client.subscribe("topic");
                }
            });
            //});
        });
    </script>
    <script type="text/javascript">
        function saveAndShowMessage(sender, who, msg, side) {
            //sender是显示的发送者名字，第二个是在和谁聊天
            var chatInfo = {};
            saveMessage(who, msg, side);
            chatInfo.id=sender;
            chatInfo.msg = {};
            chatInfo.msg.side = side;
            chatInfo.msg.body = msg.body;
            addMessage(chatInfo);
        }

        window.beforeonunload = function () {
            client.disconnect();
            client = null;
        };

        function sendMessage() {
            var msg = {};
            msg.from = clientID;
            msg.to = currentTo;
            msg.body = $('#sendingMsg').val();
            if (isInArray(currentTo, toIds)) {//打开了和别人的聊天窗口（刚开始是未打开的）
                saveAndShowMessage(clientID, currentTo, msg, "right");
                var message = new Messaging.Message(JSON.stringify(msg));
                message.destinationName = "topic";
                client.send(message);
            }
                $("#sendingMsg").val("");
        }

        function isInArray(item, array) {
            for (var key in array) {
                if (item === array[key].id)
                    return true;
            }
            return false;
        }

        function getIndex(id, array) {
            for (var i in array) {
                if (id === array[i].id)
                    return i;
            }
        }

        function saveAndHintMessage(sender, msg) {
            if($("#"+sender).length === 0){//新用户
                $("#chat_list").append('' +
                    '<a onclick="changeUser(this.id)" id="'+sender+'" class="list-group-item">'+sender+'<span class="badge">新</span> </a>\n')
                saveMessage(sender, msg, "left");
            }
            else {
                if($("#"+sender).find("span").length > 0)//存在用户而且已经提示
                {
                    saveMessage(sender, msg, "left");
                }
                else {
                    $("#"+sender).append('<span class="badge">新</span>');
                    saveMessage(sender, msg, "left");
                }
            }
        }

        function saveMessage(who, msg, side) {
            if (isInArray(who, toIds)) {
                var index = getIndex(who, toIds);
                toIds[index].msg.push(
                    {side: side, body: msg.body});
                console.log("msg of " + who+" "+side);
            }
            else {
                var chat_info = {
                    id: who,
                    msg: [{
                        side: side,
                        body: msg.body
                    }]
                };
                console.log("save:" + who);
                console.log(chat_info);
                toIds.push(chat_info);
            }
        }

        function clearNew(id) {
            $("#"+id).find("span").remove();
            console.log(id);
        }

        function clearChatBox() {
            $("#chat").find("li").remove();
        }

        function addMessage(chatInfo) {
            if(chatInfo.msg.side === "left"){
                $("#chat").append('<li class="left clearfix">\n' +
                    '            <div class="chat-body clearfix">\n' +
                    '                <div class="header">\n' +
                    '                    <strong class="pull-left primary-font">'
                    + chatInfo.id +
                    '</strong>\n' +
                    '                </div>\n' +
                    '                <br/><p>' +
                    chatInfo.msg.body +
                    '                </p>\n' +
                    '            </div>\n' +
                    '        </li>');
            }
            else {
                $("#chat").append('<li class="right clearfix">\n' +
                    '            <div class="chat-body clearfix">\n' +
                    '                <div class="header">\n' +
                    '                    <strong class="pull-right primary-font">'
                    + chatInfo.id +
                    '</strong>\n' +
                    '                </div>\n' +
                    '                <br/><p >' +
                    chatInfo.msg.body +
                    '                </p>\n' +
                    '            </div>\n' +
                    '        </li>');
            }
            }

        function changeUser(id) {
            console.log(toIds);
            if(currentTo === id)
                return;
            else {
                clearNew(id);//清空消息提示
                clearChatBox();
                currentTo = id;
                var index = getIndex(id, toIds);
                var chatInfo = {};
                for (var i in toIds[index].msg){
                    if (toIds[index].msg[i].side === "left"){
                        chatInfo.id = id;
                    }
                    else {
                        chatInfo.id = clientID;
                    }
                    chatInfo.msg = toIds[index].msg[i];
                    addMessage(chatInfo);
                }
            }
        }
    </script>
</rapid:override>
<%@include file="../base.jsp" %>
