package com.users.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.users.model.User;
import com.users.service.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@ResponseBody
	public User registerUser(@RequestBody User user) {
		
		if (userService.registerUser(user)) {
			return user;
		}
		return null;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<User> getUsers() {
		return userService.findAll();
	}

	

}
