package com.Dao;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bean.Person;

//@Repository("PersonRepository1")
public interface PersonRepository extends JpaRepository<Person, Long>{
	public List findByUsername(String name);
	//开启事务支持
	@Transactional(value=TxType.REQUIRED)
	public Integer deleteById(Integer id);
}
