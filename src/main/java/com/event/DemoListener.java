package com.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 定义事件监听器
 * */
@Component
public class DemoListener implements ApplicationListener<DemoEvent>{

	@Override
	public void onApplicationEvent(DemoEvent event) {
		System.out.println("demo-listener接收到来自demo-publisher的消息:"+event.getMsg());
	}

}
