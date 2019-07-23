package com.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSend implements CommandLineRunner{
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	//一定要定义队列,否则无法启动
	@Bean
	public Queue wiselyQueue() {
		return new Queue("my-queue");
	}
	
	//向my-queue队列发送消息
	@Override
	public void run(String... args) throws Exception {
		rabbitTemplate.convertAndSend("my-queue","来自rabbitMQ的问候");
	}

}
