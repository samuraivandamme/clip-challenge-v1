package com.example.clip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clip.entities.User;
import com.example.clip.repository.UserRepository;
/**
 * @author Ivan
 *
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getUser(){
		return userRepository.findAll();
	}
	
	public User createUser(User user) {
		return userRepository.save(user);		
	}

}
