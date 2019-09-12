package config;
import java.io.Serializable;
import java.util.Vector;


public class UserInfo implements Serializable {
	


	private static final long serialVersionUID = 5194190758805041164L;

	
	protected String Account;

	
	protected String Nickname;


	protected String Sex;

	
	protected String Image;
	protected String Status;
	
	protected Vector<Friends> friends = new Vector<Friends>();


	
	public static class Friends implements Serializable {

		

		private String Account;
		private String Nickname;
		private String Sex;
		private String Image;
		private String status;

	
		public Friends(String account, String nickname, String sex, String image, String status) {
			this.Account = account;
			this.Nickname= nickname;
			this.Sex = sex;
			this.Image= image;
			this.status = status;
		}

		
		public String getAccount() {
			return Account;
		}

		
		public String getNickname() {
			return Nickname;
		}

		
		public String getImage() {
			return Image;
		}

		public String getSex() {
			return Sex;
		}

	
		public String getStatus() {
			return status;
		}
	}
}