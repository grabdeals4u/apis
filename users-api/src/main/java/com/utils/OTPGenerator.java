package com.utils;

import java.util.Random;

public abstract class OTPGenerator {
	
	public static String generateOTP(int len) {
		// Using numeric values
		String numbers = "0123456789";
		// Using random method
		Random rndm_method = new Random();
		char[] otp = new char[len];
		for (int i = 0; i < len; i++) {
			// Use of charAt() method : to get character value
			// Use of nextInt() as it is scanning the value as int
			otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
		}
		return new String(otp);
	}
	
	public abstract boolean sendOTP();
	
	public static void main(String[] args) {
		System.out.println(generateOTP(6));
	}

}
