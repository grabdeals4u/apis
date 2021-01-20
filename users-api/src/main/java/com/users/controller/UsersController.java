package com.users.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.users.model.OtpAuthorization;
import com.users.model.User;
import com.users.model.UserAddress;
import com.users.service.UserService;
import com.utils.Constants;
import com.utils.RestClient;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@ResponseBody

	public User registerUser(@RequestBody User user, HttpServletRequest req) {
		try {
			System.out.println(user.getEmail());
			User registeredUser =  userService.registerUser(user);
			if(registeredUser != null) {
				RestClient restclient = new RestClient();
				String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/users/" +  user.getId() + "/generate_otp";
				System.out.println(url);
				restclient.setUrl(url);
				restclient.setMethod(HttpMethod.POST);
				OtpAuthorization otpAuthorization = new OtpAuthorization();
				otpAuthorization.setPurpose(Constants.OTPPurpose.REGISTER);
				restclient.setData(otpAuthorization);
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				restclient.setHeaders(headers);
				restclient.invoke();
			}
			return registeredUser;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<User> getUsers() {
		return userService.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable long id) {

		return userService.getUserById(id);
	}

	@RequestMapping(value = "/{id}/address", method = RequestMethod.POST)
	public String addAddress(@PathVariable long id, @RequestBody UserAddress userAddress) {
		try {
			userService.addAddress(id, userAddress);
			return "Address added for user id: " + id;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/{id}/address", method = RequestMethod.GET)
	public List<UserAddress> getUserAddressById(@PathVariable long id) {
		try {
			return userService.getUserAddressById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/{id}/address/{address_id}", method = RequestMethod.GET)
	public UserAddress getUserAddressByAddressId(@PathVariable long id,@PathVariable long address_id) {
		try {
			return userService.getUserAddressByAddressId(id,address_id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/{id}/generate_otp", method = RequestMethod.POST)
	public void generateOtpForUser(@PathVariable long id, @RequestBody OtpAuthorization otpAuthorization) {

		try {
			userService.generateOtpForUser(id, otpAuthorization);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
