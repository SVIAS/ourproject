package user;



import java.util.Vector;

import config.UserInfo;

public final class User extends UserInfo {

	
	private static final long serialVersionUID = -2844611810327524136L;

	
	public User(String account, String nickname, String userSex, 
			String Image, String status,Vector<Friends> friends
			) {
		this.Account = account;
		this.Nickname = nickname;
		
		this.Sex = userSex;
		
		this.Image = Image;
		
		this.friends = friends;
		this.Status= status;
	}

	
	public String getAccount() {
		return Account;
	}

	public String getNickname() {
		return Nickname;
	}

	

	
	public String getUserSex() {
		return Sex;
	}



	/**
	 * @Title: getImage
	 * @Description: 获取用户头像链接(url)
	 * @return: Image String对象
	 */
	public String getImage() {
		return Image;
	}

	/**
	 * @Title: getStatus
	 * @Description: 获取用户个性签名
	 * @return: Status String对象
	 */
	public String getStatus() {
		return Status;
	}

	

	/**
	 * @Title: getFriends
	 * @Description: 获取用户好友列表ID
	 * @return: friends Vector<String>对象
	 */
	public Vector<Friends> getFriends() {
		return friends;
	}


}
