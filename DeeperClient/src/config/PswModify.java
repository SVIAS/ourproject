package config;


import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public final class PswModify implements Serializable {

	
	private static final long serialVersionUID = -8374512264642420545L;

	

	private String userId;


	private String userPassword;


	public PswModify(String userId, String userPassword) {
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

