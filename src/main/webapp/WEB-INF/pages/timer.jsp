<!DOCTYPE html>
<html>
<head>
<title>Timer</title>
<script src="/sockjs-0.3.4.js"></script>
<script src="/stomp.js"></script>
<script type="text/javascript">
	var stompClient = null;
	function setConnected(connected) {
		document.getElementById('connect').disabled = connected;
		document.getElementById('disconnect').disabled = !connected;
		document.getElementById('conversationDiv').style.visibility = connected ? 'visible'
				: 'hidden';
	}
	function connect() {
		var socket = new SockJS('/order');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			setConnected(true);
			console.log('Connected: ' + frame);
		});
	}
	function disconnect() {
		stompClient.disconnect();
		setConnected(false);
		console.log("Disconnected");
	}
	function send() {
		setInterval(function(){
		var name = +new Date();	
		stompClient.send("/ws/order", {}, JSON.stringify({
			'name' : name
		}))}, 2000);
	}

</script>
</head>
<body>
	<div>
		<p>get order info every 2 seconds</p>
		<div>
			<button id="connect" onclick="connect();">Connect</button>
			<button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
		</div>
		<div id="conversationDiv">
			<button id="send" onclick="send();">Kick Off Update</button>
		</div>
	</div>
</body>
</html>