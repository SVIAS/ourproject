package config;

import java.io.Serializable;

public class UserModify implements Serializable{

	private static final long serialVersionUID = -4532079792910341342L;
private String account;
private String name;
private String image;
private String sex;
public UserModify(String a,String n,String i,String s)
{account=a;
 name=n;
 image=i;
 sex=s;
}
public String getAccount() {return account;}
public String getName() {return name;}
public String getImage() {return image;}
public String getSex() {return sex;}
}
