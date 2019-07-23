package com.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Dao.PersonRepository;
import com.bean.Person;
import com.sevice.PersonService;

@RestController
public class PersonController {
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	private PersonService personService;
	
	@Value("${server.port}")
	private String port;
	
//	public PersonRepository getPersonRepository() {
//		return personRepository;
//	}
//	@Resource(name="PersonRepository1")
//	public void setPersonRepository(PersonRepository personRepository) {
//		this.personRepository = personRepository;
//	}


	@RequestMapping("/savePerson")
	public Person save(String name,int age,String address) {
		Person newPerson=new Person(null, name, age, address);
		return personRepository.save(newPerson);
	}
	
	
//	@RequestMapping("/savePersonWithRollBack")
//	//指定异常回滚事物
//	@Transactional(rollbackFor=(IllegalArgumentException.class))
//	//如抛出指定异常不回滚事物
//	//@Transactional(noRollbackFor=(IllegalArgumentException.class))
//	public Person saveWithRollBack(String name,int age,String address) {
//		Person newPerson=new Person(null, name, age, address);
//		Person newPerson1=personRepository.save(newPerson);
//		if("高翔".equals(name)) {
//			throw new IllegalArgumentException("高翔已存在,回滚!");
//		}
//		return newPerson1;
//	}
	
	
	@RequestMapping("/savePersonWithRollBack")
	//指定异常回滚事物
//	@Transactional(rollbackFor=(IllegalArgumentException.class))
	//如抛出指定异常不回滚事物
	//@Transactional(noRollbackFor=(IllegalArgumentException.class))
	public Person saveWithRollBack(String name,int age,String address) {
		Person newPerson=personService.save(name, age, address);
		if("高翔".equals(name)) {
			throw new IllegalArgumentException("高翔已存在,回滚!");
		}
		return newPerson;
	}
	
	
	@RequestMapping("/getPersonList")
	@Transactional
	public List getpersonList() {
//		Person newPerson=new Person(null, name, age, address);
		System.out.println(port);
		return personRepository.findAll();
	}
	
	@RequestMapping("/findPersonbyName")
	public List findbyName(String name) {
		return personRepository.findByUsername(name);
	}
	
	@RequestMapping("/delPerson")
	public Integer findbyName(Integer id) {
		return personRepository.deleteById(id);
	}
	
	@RequestMapping("/sortPerson")
	public List sortPerson(String col) {
		return personRepository.findAll(new Sort(Direction.DESC,col));
	}
	
	//page页码:从零开始  size:每页显示条数
	@RequestMapping("/pagePerson")
	public Page<Person> pagePerson(int page,int size) {
		return personRepository.findAll(new PageRequest(page, size));
	}
	
	//查询缓存测试
	@RequestMapping("/cachefindOne")
	public Person findOne(Person person) {
		return personService.findOne(person);
	}
	
	//查询缓存测试
	@RequestMapping("/cacheDeleteOne")
	public void deleteOne(Person person) {
		 personService.deletePersonById(person);
	}
	
	
	//查询缓存测试
	@RequestMapping("/redisfindOne")
	public Person findOneRedis(Person person) {
		return personService.findOneRedis(person);
	}
	
	//查询缓存测试
	@RequestMapping("/redisDeleteOne")
	public void deleteOneRedis(Person person) {
		 personService.deletePersonByIdRedis(person);
	}
}
