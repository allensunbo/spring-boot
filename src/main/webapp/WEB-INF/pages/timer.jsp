<!DOCTYPE html>
<html>
<head>
<title>Hello WebSocket</title>
<script src="sockjs-0.3.4.js"></script>
<script src="stomp.js"></script>
<script type="text/javascript">
	var stompClient = null;
	function setConnected(connected) {
		document.getElementById('connect').disabled = connected;
		document.getElementById('disconnect').disabled = !connected;
		document.getElementById('conversationDiv').style.visibility = connected ? 'visible'
				: 'hidden';
		document.getElementById('response').innerHTML = '';
	}
	function connect() {
		var socket = new SockJS('/hello');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			setConnected(true);
			console.log('Connected: ' + frame);
			stompClient.subscribe('/topic/greetings', function(greeting) {
				showGreeting(JSON.parse(greeting.body).content);
			});
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
		stompClient.send("/app/hello", {}, JSON.stringify({
			'name' : name
		}))}, 2000);
	}
	function showGreeting(message) {
		var response = document.getElementById('response');
		var p = document.createElement('p');
		p.style.wordWrap = 'break-word';
		p.appendChild(document.createTextNode(message));
		response.appendChild(p);
	}
</script>
</head>
<body>
	<div>
		<div>
			<button id="connect" onclick="connect();">Connect</button>
			<button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
		</div>
		<div id="conversationDiv">
			<button id="send" onclick="send();">Send</button>
		</div>
	</div>
</body>
</html>