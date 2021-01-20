var stompClient = null;

function connect(){
	var socket=new SockJS('http://localhost:8082/chatwithbuddy');
	stompClient=Stomp.over(socket);
	stompClient.connect({}, function(frame) {

	          setConnected(true);
	          console.log('	: ' + frame);
	          alert("Connected");
	          stompClient.subscribe('http://localhost:8082/topic/pushchats', function(messageOutput) {

	          		showChats(JSON.parse(messageOutput.body));
	         	});
	});
}

function setConnected(connected){
	if(connected && connected ==='true'){
		$( "#connect_bot" ).prop( "disabled", true );
		$( "#disconnect_bot" ).prop("disabled", false);
		$("#user_conversation_div_bot").show();
		$("#user_chat_messages_bot").val("");
	}
	else{
		$( "#disconnect_bot" ).prop("disabled", true);
		$("#user_conversation_div_bot").hide();
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
	var chatFrom=$("#from_name_bot").val();
	var chatData=$("#chat_text_bot").val();

	if(!chatData || !chatFrom){
		alert("Can't send message");
		return;
	}
	var params=JSON.stringify({'from' : chatFrom,'messageText' : chatData});

	stompClient.send("http://localhost:8200/app/chatwithbuddy",{},params);
}

function showChats(messageOutput){
	var response = document.getElementById('user_chat_messages_bot');
	var p = document.createElement('p');
	p.style.wordWrap = 'break-word';
	p.appendChild(document.createTextNode(messageOutput.from + ": " + messageOutput.text + " (" + messageOutput.time + ")"));
	response.appendChild(p);
}