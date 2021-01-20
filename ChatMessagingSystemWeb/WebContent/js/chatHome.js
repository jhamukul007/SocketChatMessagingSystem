var stompClient = null;

function connect(){
	var socket=new SockJS('http://localhost:8082/chat');
	stompClient=Stomp.over(socket);
	stompClient.connect({}, function(frame) {
	          setConnected(true);
	          console.log('Connected: ' + frame);
	          alert("Connected");
	          stompClient.subscribe('http://localhost:8082/topic/messages', function(messageOutput) {

	          		showChats(JSON.parse(messageOutput.body));
	         	});
	});
}

function setConnected(connected){
	if(connected && connected ==='true'){
		$( "#connect" ).prop( "disabled", true );
		$( "#disconnect" ).prop("disabled", false);
		$("#user_conversation_div").show();
		$("#user_chat_messages").val("");
	}
	else{
		$( "#disconnect" ).prop("disabled", true);
		$("#user_conversation_div").hide();
	}
}

function disconnect(){
	if(stompClient!=null){
		stompClient.disconnect();
	}
	setConnected(false);
	alert("Disconnected");
} 

function sendChat(){
	var chatFrom=$("#from_name").val();
	var chatData=$("#chat_text").val();

	if(!chatData || !chatFrom){
		alert("Can't send message");
		return;
	}
	var params=JSON.stringify({'from' : chatFrom,'messageText' : chatData});

	stompClient.send("http://localhost:8200/app/chat",{},params);
}

function showChats(messageOutput){
	var response = document.getElementById('user_chat_messages');
	var p = document.createElement('p');
	p.style.wordWrap = 'break-word';
	p.appendChild(document.createTextNode(messageOutput.from + ": " + messageOutput.text + " (" + messageOutput.time + ")"));
	response.appendChild(p);
}