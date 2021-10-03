package com.example.clip.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
/**
 * @author Ivan
 *
 */
@Entity
@Table(name = "user")
@Data
public class User {
	
	public User(String userName, String userLastName) {
		super();
		this.userName = userName;
		this.userLastName = userLastName;
	}
	
	public User() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="username")	
	private String userName;	

	@Column(name="userlastname")
	private String userLastName;
	
	
}
