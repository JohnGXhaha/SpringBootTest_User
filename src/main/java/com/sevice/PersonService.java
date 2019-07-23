package com.sevice;

import java.util.List;

import com.bean.Person;


public interface PersonService {
	public Person save(String name,int age, String address);
	public List findByUsername(String name);
	public Integer deleteById(Integer id);
	public Person findOne(Person person);
	public Person findOneRedis(Person person);
	public void deletePersonById(Person person);
	public void deletePersonByIdRedis(Person person);
}
