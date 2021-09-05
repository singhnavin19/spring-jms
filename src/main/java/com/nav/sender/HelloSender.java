package com.nav.sender;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nav.config.JmsConfig;
import com.nav.model.HelloMessage;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HelloSender {

	private final JmsTemplate jmsTemplate;
	private final ObjectMapper objectMapper;

//	@Scheduled(fixedRate = 2000)
//	public void sendMessage() {
//		System.out.println("sending mesg...");
//
//		HelloMessage message = HelloMessage.builder().uid(UUID.randomUUID()).message("hello world").build();
//		this.jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);
//
//		System.out.println("message sent");
//	}

	@Scheduled(fixedRate = 2000)
	public void sendAndReceiveMessage() throws JMSException {
		System.out.println("sending mesg...");
		HelloMessage message = HelloMessage.builder().uid(UUID.randomUUID()).message("hello").build();

		Message reciveMsg = this.jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RECEIVE_QUEUE, new MessageCreator() {
			Message helloMsg = null;

			@Override
			public Message createMessage(Session session) throws JMSException {
				try {

					this.helloMsg = session
							.createTextMessage(HelloSender.this.objectMapper.writeValueAsString(message));
					this.helloMsg.setStringProperty("_type", "com.nav.model.HelloMessage");

					System.out.println("sending hello");

				} catch (Exception e) {

				}
				return this.helloMsg;
			}
		});

		System.out.println(reciveMsg.getBody(String.class));
	}

}
