<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<rapid:override name="head">
    <title>聊天</title>
</rapid:override>
<rapid:override name="content">
    <div id="message"></div><br>
    <input type="hidden" id="clientID" value="${clientId}">
    <input type="hidden" id="toID" value="${toId}">
    <textarea id="sendMsg" rows="3" cols="50">请输入信息</textarea>
    <button id='sendBtn'>发送</button>
</rapid:override>
<rapid:override name="scripts">
    <script type="text/javascript" src="/js/mqttws31.js"></script>
    <script type="text/javascript">
        var client;
        var clientID;
        $(window).on('beforeunload',function(){
            client=null;
            window.opener.document.getElementById("flag").value="0";
        });
        $(function($) {
            //$('#con').bind('click',function(){
            clientID=$('#clientID').val();
            client = new Messaging.Client('127.0.0.1',61614,clientID);
            client.onConnectionLost = function(){
                $('#message').append('连接已断开');
            };
            //收到消息
            client.onMessageArrived = function(message){
                var msgObj=jQuery.parseJSON(message.payloadString);
                clientID=$('#clientID').val();
                if (msgObj.to===clientID){
                    $('#toID').val(msgObj.from);
                    $('#message').append("<font color=red>"+msgObj.from+":"+msgObj.body+"</font></br>");
                }
            };
            //建立连接和订阅
            client.connect({onSuccess:function(){
                $('#message').append('连接成功,可以聊天了！'+"</br>");
                //订阅topic
                client.subscribe("topic");
            }});
            //});
            $('#sendBtn').bind('click',function(){
                clientID=$('#clientID').val();
                var msg={};
                msg.from=clientID;
                msg.to=$('#toID').val();
                console.log(msg.to);
                msg.body=$('#sendMsg').val();
                message = new Messaging.Message(JSON.stringify(msg));
                message.destinationName = "topic";
                client.send(message);
                $('#message').append(msg.from+":"+msg.body+"</br>");
            });

        });
    </script>
</rapid:override>>
<%@include file="../base.jsp" %>
