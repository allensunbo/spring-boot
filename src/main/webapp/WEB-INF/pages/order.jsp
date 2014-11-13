<!DOCTYPE html>
<html>
<head>
<title>Hello WebSocket</title>
<script src="/sockjs-0.3.4.js"></script>
<script src="/stomp.js"></script>
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
		var socket = new SockJS('/order');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			setConnected(true);
			console.log('Connected: ' + frame);
			stompClient.subscribe('/topic/order', function(greeting) {
				showOrders(JSON.parse(greeting.body));
			});
		});
	}
	function disconnect() {
		stompClient.disconnect();
		setConnected(false);
		console.log("Disconnected");
	}

	function showOrders(messages) {
		var response = document.getElementById('response');
		document.getElementById('response').innerHTML="";
		for(customer in messages) {
			for(order in messages[customer]) {
				var p = document.createElement('p');
				p.style.wordWrap = 'break-word';
				p.appendChild(document.createTextNode(messages[customer][order].customerName+'|'+messages[customer][order].orderItems));
				response.appendChild(p);	
			}
			
		}
	}
</script>
</head>
<body>
	<noscript>
		<h2 style="color: #ff0000">Seems your browser doesn't support
			Javascript! Websocket relies on Javascript being enabled. Please
			enable Javascript and reload this page!</h2>
	</noscript>
	<div>
		<div>
			<button id="connect" onclick="connect();">Connect</button>
			<button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
		</div>
		<div id="conversationDiv">
			<p id="response"></p>
		</div>
	</div>
</body>
</html>