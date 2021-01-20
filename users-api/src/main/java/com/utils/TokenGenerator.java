package com.utils;

import java.util.UUID;

public class TokenGenerator {
	
	public static String generateToken() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

}
