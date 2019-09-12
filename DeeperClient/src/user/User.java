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
	 * @Description: ��ȡ�û�ͷ������(url)
	 * @return: Image String����
	 */
	public String getImage() {
		return Image;
	}

	/**
	 * @Title: getStatus
	 * @Description: ��ȡ�û�����ǩ��
	 * @return: Status String����
	 */
	public String getStatus() {
		return Status;
	}

	

	/**
	 * @Title: getFriends
	 * @Description: ��ȡ�û������б�ID
	 * @return: friends Vector<String>����
	 */
	public Vector<Friends> getFriends() {
		return friends;
	}


}
