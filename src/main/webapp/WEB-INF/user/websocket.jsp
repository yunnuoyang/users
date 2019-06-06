<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/6/6
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Testing websockets</title>
</head>
<body>
<div>
    <input type="submit" value="Start" onclick="start()" />
</div>
<div id="messages"></div>
<script type="text/javascript">
    var webSocket =  new WebSocket('ws://localhost:8080/webSocketByTomcat/123');

    webSocket.onerror = function(event) {
        onError(event)
    };

    webSocket.onopen = function(event) {
        onOpen(event)
    };

    webSocket.onmessage = function(event) {
        onMessage(event)
    };

    function onMessage(event) {
        alert("接受到了服务器返回的数据")
        document.getElementById('messages').innerHTML
            += '<br />' + event.data;
    }

    function onOpen(event) {
        document.getElementById('messages').innerHTML
            = 'Connection established';
    }

    function onError(event) {
        alert(event.data);
        alert("error");
    }

    function start() {
        webSocket.send('hello');
        return false;
    }
</script>
</body>
</html>