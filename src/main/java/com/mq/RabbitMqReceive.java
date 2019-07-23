package com.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqReceive {
	
	//监听my-queue队列(目的地)发送的消息
	@RabbitListener(queues="my-queue")
	public void receiveMess(String message) {
		System.out.println("接收到my-queue的消息:"+message);
	}
}
