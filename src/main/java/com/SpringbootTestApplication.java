package com;

//import java.awt.List;
import java.util.ArrayList;

//import org.apache.catalina.Context;
//import org.apache.catalina.connector.Connector;
//import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bean.BookSettings;
import com.bean.User;
import com.mq.RabbitMqSend;

//@RestController
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableEurekaServer
@EnableEurekaClient
@EnableTransactionManagement//声明式事务支持
//@ComponentScan(basePackages={"com.Repository","com.*"})
//@ComponentScan(basePackages={"com.bean","com.Dao","com.service","com.mq","com.controller"})
@ComponentScan(basePackages={"com.*"})
public class SpringbootTestApplication {
	
	@Autowired
	private BookSettings bookSettings;
	@Autowired
	private RabbitMqSend rabbitMqSend;
	
	@Value("${book.hello}")
	private String bookHello;
	
	@RequestMapping("/")
    public String home()
    {
        return "Hello World!"+bookHello;
    }
	
	@RequestMapping("/bookSet")
    public String home2()
    {
        return "Hello World bookSet!"+bookSettings.getHello();
    }

	
	@RequestMapping("/home1")
    public String home1(@RequestParam(required=false) String name)
    {
        return "Hello World!"+name;
    }
	
	@RequestMapping("/userList")
    public ModelAndView userList(Model model)
    {
		ArrayList userList=new ArrayList();
		
		User user=new User();
		user.setId(1l);
		user.setName("高翔");
		user.setAge(27);
		userList.add(user);
		
		User user1=new User();
		user1.setId(2l);
		user1.setName("JonhGX");
		user1.setAge(32);
		userList.add(user1);
		
		model.addAttribute("userList", userList);
		
//        return "user/userList";
		return new ModelAndView("user/userList", "userModel", model);
    }
	
	@RequestMapping("/sendMqMess")
    public void sendRabbitMqMessage() throws Exception
    {
		rabbitMqSend.run();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootTestApplication.class, args);
	}
	
//	@Bean
//	  public EmbeddedServletContainerFactory servletContainer(){
//	      TomcatEmbeddedServletContainerFactory tomcat=new TomcatEmbeddedServletContainerFactory(){
//	          protected void postProcessContext(Context context) {
//	              SecurityConstraint securityConstraint=new SecurityConstraint();
//	              securityConstraint.setUserConstraint("CONFIDENTIAL");//confidential
//	              SecurityCollection collection=new SecurityCollection();
//	              collection.addPattern("/*");
//	              securityConstraint.addCollection(collection);
//	              context.addConstraint(securityConstraint);
//	          }
//	      };
//	      tomcat.addAdditionalTomcatConnectors(httpConnector());
//	      return tomcat;
//	  }
//	 
//	  @Bean
//	  public Connector httpConnector(){
//	      Connector connector=new Connector("org.apache.coyote.http11.Http11NioProtocol");
//	      connector.setScheme("http");
//	      connector.setPort(8080);
//	      connector.setSecure(false);
//	      connector.setRedirectPort(8443);
//	      return connector;
//	  }

}
