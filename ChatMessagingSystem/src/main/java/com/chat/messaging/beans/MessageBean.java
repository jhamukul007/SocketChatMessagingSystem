package com.chat.messaging.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class MessageBean {
	private String from;
	private String text;
	private String time;
}
