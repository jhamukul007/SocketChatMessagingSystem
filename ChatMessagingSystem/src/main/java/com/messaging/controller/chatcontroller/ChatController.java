package com.messaging.controller.chatcontroller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chat.messaging.beans.Message;
import com.chat.messaging.beans.MessageBean;

@Controller
public class ChatController {

	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public MessageBean sendMessage(final Message message) throws Exception {
		final String currentTime=new SimpleDateFormat("HH:mm:ss").format(new Date());
		return new MessageBean(message.getForm(), message.getMessageText(), currentTime);
	}
	
	@RequestMapping("/chat/status")
	public String getStatus() {
		return "Don't worry, Your chat [MS] running";
	}
}
