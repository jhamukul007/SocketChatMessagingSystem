package com.messaging.service.services.schedulers;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.chat.messaging.beans.MessageBean;

import reactor.core.publisher.Flux;

@Service
public class ScheduledPuchChatService extends AbstectScheduleService implements InitializingBean {

		
	@Override
	public void afterPropertiesSet() throws Exception {
        Flux.interval(Duration.ofSeconds(4L))
        .map((n) -> new MessageBean(faker.backToTheFuture().character(), faker.backToTheFuture().quote(), 
                                        new SimpleDateFormat("HH:mm").format(new Date())))
        .subscribe(message -> template.convertAndSend("/topic/messages", message));		
	}

}
	