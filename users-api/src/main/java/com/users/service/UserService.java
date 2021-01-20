package com.users.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.users.dao.CustomUserRepository;
import com.users.dao.UserRepository;
import com.users.model.OtpAuthorization;
import com.users.model.User;
import com.users.model.UserAddress;
import com.utils.EncryptionUtils;
import com.utils.OTPGenerator;
import com.utils.TokenGenerator;


@Service
@Transactional
public class UserService {

	@Autowired(required = true)
	private UserRepository userRepository;

	@Autowired(required = true)
	private CustomUserRepository customUserRepository;
	
	@Autowired
	private Environment env;

	/**
	 * 
	 * @param user
	 * @return
	 */
	public User registerUser(User user) {

		user.setToken(TokenGenerator.generateToken());

		return customUserRepository.registerUser(user);
	}
	
	/**
	 * 
	 * @return
	 */

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User getUserById(long id) {
		//System.out.print(customUserRepository.findById(id).getUserAddress().get(0));
		return customUserRepository.findById(id);
	}

	public void addAddress(Long id, UserAddress userAddress) {
		customUserRepository.addAddress(id, userAddress);
	}
	
	public List<UserAddress> getUserAddressById(long id){
		return customUserRepository.getUserAddressById(id);
	}
	
	public UserAddress getUserAddressByAddressId(long id,long address_id)
	{
		return customUserRepository.getUserAddressByAddressId(id,address_id);
	}
	
	public void generateOtpForUser(long id,OtpAuthorization otpAuthorization)
	{
		String otp=OTPGenerator.generateOTP(Integer.parseInt(env.getProperty("app.otp_length")));
		String encryptedOtp=EncryptionUtils.encryptInput(otp);
		
		
		otpAuthorization.setUserId(id);
		otpAuthorization.setOtp(encryptedOtp);
		
		Date date= new Date();
       
		long time = date.getTime();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(time);
		otpAuthorization.setTime(sqlTimestamp);
		customUserRepository.generateOtpForUser(otpAuthorization);
		
		
	}
	

}
