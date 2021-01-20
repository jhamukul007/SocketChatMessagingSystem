package com.messaging.service.services.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

@Service
public class AbstectScheduleService {
	
	//@Autowired
	protected Faker faker=new Faker();
	
	@Autowired
	protected SimpMessagingTemplate template;

}
