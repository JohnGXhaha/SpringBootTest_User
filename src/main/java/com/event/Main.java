package com.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(EventConfig.class);
		DemoPublisher demoPublisher=context.getBean(DemoPublisher.class);
		demoPublisher.publish("哈哈哈哈呸");
		
		context.close();
	}
}
