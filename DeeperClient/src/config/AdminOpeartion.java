package config;

import java.io.Serializable;

public class AdminOpeartion implements Serializable{

	private static final long serialVersionUID = 4890825530101329210L;
private String type;
private String account;
public AdminOpeartion(String ty,String ac) {type=ty;account=ac;}
public String getType() {return type;}
public String getAccount() {return account;}
}
