package config;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignupOpeartion implements Serializable{

	private static final long serialVersionUID = 8645753925377534563L;
private String Name;
private String Sex;
private String Psw;
public SignupOpeartion(String name,String sex,String psw)
{Name=name;
 Sex=sex;
 Psw=getMd5(psw);}
public String getName() {return Name;}
public String getSex() {return Sex;}
public String getPsw() {return Psw;}
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
