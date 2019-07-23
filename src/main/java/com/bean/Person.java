package com.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.springframework.context.annotation.Lazy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author JohnGX
 *
 */
@Entity
//@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","fieldHandler"})
@Table(name = "test_Person")
public class Person implements Serializable{

	public Person() {
		super();
	}

	public Person(Long id, String username, Integer age, String address) {
		super();
		this.id = id;
		this.username = username;
		this.age = age;
		this.address = address;
	}
	public String toString() {  
        StringBuffer sb=new StringBuffer("[");  
        sb.append("Id="+getId()+",");  
        sb.append("Username="+getUsername()+",");  
        sb.append("Address="+getAddress()+",");  
        sb.append("Age="+getAge()+",");  
        return sb.toString();  
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    private String username;

//    @Column
    private Integer age;
    
    private String address;


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
}
