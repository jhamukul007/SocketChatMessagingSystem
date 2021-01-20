package com.messaging.service.services.schedulers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.chat.messaging.beans.MessageBean;

@Service
public class SchedulePushMessagesService extends AbstectScheduleService {
	
	@Scheduled(fixedRate = 4000)
	public void sendMessages() {
        final String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        template.convertAndSend("/topic/pushchats", 
            new MessageBean("Chuck Norris", faker.chuckNorris().fact(), time));

	}
}
