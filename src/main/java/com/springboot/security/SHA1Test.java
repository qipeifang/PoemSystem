package com.springboot.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

 
public class SHA1Test {
	 final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
 
    public  String toHexString(String pwd) throws NoSuchAlgorithmException {	
    	MessageDigest messageDigest  = MessageDigest.getInstance("SHA1");        
		messageDigest.update(pwd.getBytes());
		byte[] data = messageDigest.digest();
        char[] chars = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            chars[i * 2] = HEX_DIGITS[(data[i] >> 4) & 0xf];
            chars[i * 2 + 1] = HEX_DIGITS[data[i] & 0xf];
        }
		System.out.print("ת����ļ�������Ϊ��"+new String(chars).toLowerCase());
        return new String(chars).toLowerCase();
        
    }

	
}

 
    
   