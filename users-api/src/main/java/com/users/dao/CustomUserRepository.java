package com.users.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.users.model.OtpAuthorization;
import com.users.model.User;
import com.users.model.UserAddress;

public interface CustomUserRepository {
	
	public User registerUser(User user);
	public void addAddress(Long id, UserAddress userAddress);
	public User findById(long id);
	public List<UserAddress> getUserAddressById(long id);
	public UserAddress getUserAddressByAddressId(long id,long address_id);
	public void generateOtpForUser(OtpAuthorization otpauthorization);

}
