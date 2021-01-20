package com.users.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;

import com.users.model.OtpAuthorization;
import com.users.model.User;
import com.users.model.UserAddress;
import com.utils.Constants;
import com.utils.RestClient;

@Repository
@Transactional
public class CustomUserRepositoryImpl implements CustomUserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		entityManager.persist(user);
		return user;
	}

	@Override
	public void addAddress(Long id, UserAddress userAddress) {
		User user = entityManager.find(User.class, id);

		System.out.println(userAddress);
		List<UserAddress> addressList = user.getUserAddress();
		addressList.add(userAddress);
		for (UserAddress item : addressList) {
			System.out.println(item.getAddress1());
		}
		user.setUserAddress(addressList);
		entityManager.persist(userAddress);

	}

	@Override
	public User findById(long id) {
		User user = entityManager.find(User.class, id);
		return user;
	}

	@Override
	public List<UserAddress> getUserAddressById(long id) {

		User user = entityManager.find(User.class, id);
		List<UserAddress> useraddress = user.getUserAddress();
		// TODO Auto-generated method stub
		return useraddress;
	}

	@Override
	public void generateOtpForUser(OtpAuthorization otpauthorization) {
		// TODO Auto-generated method stub
		entityManager.persist(otpauthorization);
	}

	@Override
	public UserAddress getUserAddressByAddressId(long id, long address_id) {
		// TODO Auto-generated method stub
		User user=entityManager.find(User.class, id);
		List<UserAddress> addressList=user.getUserAddress();
		UserAddress uAddress=new UserAddress();
		for(UserAddress item: addressList)
		{
			if(item.getId()==address_id)
			{
				uAddress=item;
			}
		}
		return uAddress;
	}

}