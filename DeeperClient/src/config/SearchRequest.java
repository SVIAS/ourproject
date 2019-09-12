package config;

import java.io.Serializable;

public class SearchRequest implements Serializable {


	private static final long serialVersionUID = 7092528421472123150L;
	private String info;
	private String type;
	public SearchRequest(String i,String t) {

		info=i;
		type=t;
	}
	public String getInfo() {return info;}
	public String getType() {return type;}
		
	
}
