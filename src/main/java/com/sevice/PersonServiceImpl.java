package com.sevice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Dao.PersonRepository;
import com.bean.Person;
@Service
public class PersonServiceImpl implements PersonService{
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate redisTemplate;
//	@Resource(name="stringRedisTemplate")
//	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
//		this.stringRedisTemplate = stringRedisTemplate;
//	}

	@Override
	public List findByUsername(String name) {
		return personRepository.findAll();
	}

	@Override
	public Integer deleteById(Integer id) {
		return personRepository.deleteById(id);
	}

	@Override
	//缓存支持:@CachePut新增或更新
	@CachePut(value="people",key="#person.id")
//	@Transactional(rollbackFor=(IllegalArgumentException.class))
	public Person save(String name,int age, String address) {
		Person newPerson=new Person(null, name, age, address);
		Person newPerson1=personRepository.save(newPerson);
		if("高翔".equals(name)) {
			throw new IllegalArgumentException("高翔已存在,回滚!");
		}
		return newPerson1;
	}

	@Override
	//缓存支持:@Cacheable无缓存则查库并将数据放入缓存,有缓存则抓缓存
	@Cacheable(value="people",key="#person.id")
	public Person findOne(Person person) {
		Person p=personRepository.getOne(person.getId());
		if(p==null) {
			return null;
		}
		return p;
	}

	@Override
	//缓存支持:@CacheEvict删除key为person的id的缓存
	@CacheEvict(value="people",key="#person.id")
	public void deletePersonById(Person person) {
		personRepository.deleteById(person.getId());
	}
	
	
	@Override
	//redis测试
	@Transactional
	public Person findOneRedis(Person person) {
		Person p=(Person) redisTemplate.opsForValue().get("people"+person.getId());
//		System.out.println(p.toString());
		if(p==null) {
			System.out.println("redis没有此people记录");
			 p=personRepository.getOne(person.getId());
		}else {
			System.out.println("抓取到对应的redis缓存");
			return p;
		}
		
		if(p!=null) {
			redisTemplate.opsForValue().set("people"+person.getId(),p);
			System.out.println("录入redis");
		}
		else{
			return null;
		}
		return p;
	}
	
	public void deletePersonByIdRedis(Person person) {
		personRepository.deleteById(person.getId());
		redisTemplate.delete("people"+person.getId());
	}
	
}
