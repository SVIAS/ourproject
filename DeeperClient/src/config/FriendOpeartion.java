package config;

import java.io.Serializable;

public class FriendOpeartion implements Serializable{

	private static final long serialVersionUID = -7849730528148849378L;
private String type;
private String fromid;
private String toid;
public FriendOpeartion(String t,String f,String to) {

	type=t;
	fromid=f;
	toid=to;
}
public String getType() {return type;}
public String getFromid() {return fromid;}
public String getToid() {return toid;}

}
