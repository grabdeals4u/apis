package com.utils;

import org.springframework.util.DigestUtils;

public class EncryptionUtils {
	
	public static String encryptInput(String data)
	{
		//System.out.print(data.getBytes());
		String md5Hex = DigestUtils.md5DigestAsHex(data.getBytes());
		return md5Hex;
	}
	

}
