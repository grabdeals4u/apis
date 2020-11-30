package com.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.dao.UserRepository;
import com.users.model.User;

@Service
public class UserService {
	
	@Autowired(required = true)
	UserRepository userRepository;
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public boolean registerUser(User user) {
		return userRepository.save(user) != null;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<User> findAll(){
		return userRepository.findAll();
	}

}
