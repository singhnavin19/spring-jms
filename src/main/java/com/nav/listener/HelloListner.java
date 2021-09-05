package com.nav.listener;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.nav.config.JmsConfig;
import com.nav.model.HelloMessage;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HelloListner {

	private final JmsTemplate JmsTemplate;

//	@JmsListener(destination = JmsConfig.MY_QUEUE)
//	public void listen(@Payload HelloMessage helloMessage, @Headers MessageHeaders headers, Message message) {
//		System.out.println("I got one message");
//		System.out.println(helloMessage);
//	}

	@JmsListener(destination = JmsConfig.MY_SEND_RECEIVE_QUEUE)
	public void listen(@Payload HelloMessage helloMessage, @Headers MessageHeaders headers, Message message)
			throws JmsException, JMSException {
		System.out.println("I got one message");

		HelloMessage payload = HelloMessage.builder().uid(UUID.randomUUID()).message("world").build();

		this.JmsTemplate.convertAndSend(message.getJMSReplyTo(), payload);

	}

}
