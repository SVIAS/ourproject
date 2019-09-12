package config;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public final class LoginVerify implements Serializable {

	
	private static final long serialVersionUID = -4490443980607193791L;

	
	private String userId;

	private String userPassword;


	public LoginVerify(String userId, String userPassword) {
		this.userId = userId;
		this.userPassword = getMd5(userPassword);
	}


	public String getUserId() {
		return userId;
	}

	
	public String getUserPassword() {
		return userPassword;
	}

	
	private String getMd5(String str) {
		String mdPassword = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");


			md.update(str.getBytes());


			mdPassword = new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("MD5º”√‹ ß∞‹£∫" + e.getMessage());
		}
		return mdPassword;
	}
}
