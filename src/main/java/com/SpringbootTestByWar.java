package com;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * war包部署的启动服务类
 * */
public class SpringbootTestByWar extends SpringBootServletInitializer{
	protected SpringApplicationBuilder configure(SpringApplicationBuilder config) {
		return config.sources(SpringbootTestApplication.class);
		
	}
}
