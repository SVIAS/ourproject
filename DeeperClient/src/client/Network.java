package client;



import java.awt.print.Printable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import config.AdminOpeartion;
import config.FriendOpeartion;
import config.LoginVerify;
import config.PswModify;
import config.SearchRequest;
import config.ServerInfo;
import config.SignupOpeartion;
import config.UserModify;
import config.UserInfo.Friends;
import user.User;



public final class Network {
   

	private static Object postToServer(Object obj) {
		Socket sc=null;
		try {
			sc = new Socket(ServerInfo.SERVER_IP, ServerInfo.VERIFY_PORT);
		} catch (UnknownHostException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		Object result = null;
		try {
			
			ObjectOutputStream out = new ObjectOutputStream(sc.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(sc.getInputStream());


			out.writeObject(obj);


			result = in.readObject();


			
			in.close();
			out.close();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("在与服务器验证交互中出现了异常：" + e.getMessage());
		}
		return result;
	}


	public static Object isLogin(String userId, String userPassword) {

		LoginVerify userInfo = new LoginVerify(userId, userPassword);


		return postToServer(userInfo);
	}

	
	public static User getUserInfo(String userId) {
		User userInfo = null;
		String fieldString = "getUserInfo" + userId;
		userInfo = (User) postToServer(fieldString);
		return userInfo;
	}


	@SuppressWarnings("unchecked")
	public static Vector<String> getChatRecord(String fromid, String toId) {
		String sendString = "getChatRecord```" + fromid + "```" + toId + "```" ;
		return (Vector<String>) postToServer(sendString);
	}
	public static Object changePsw(String account,String psw)
	{
		PswModify userInfo = new PswModify(account, psw);

		
		return postToServer(userInfo);}
	
	public  static Vector<Friends> search(String info,String type)
	{SearchRequest searchRequest=new SearchRequest(info, type);
	 return (Vector<Friends>) postToServer(searchRequest);}
	
	public  static Object addFriend(String fromid,String toid) 
	{FriendOpeartion friendOpeartion=new FriendOpeartion("add", fromid,toid);
	 return postToServer(friendOpeartion);}
	public  static Object deleteFriend(String fromid,String toid) 
	{FriendOpeartion friendOpeartion=new FriendOpeartion("delete", fromid,toid);

 return postToServer(friendOpeartion);}
	public static  Object banUser(String account) {AdminOpeartion adminOpeartion=new AdminOpeartion("ban", account); return postToServer(adminOpeartion);}
	public static Object normalizeUser(String account) {AdminOpeartion adminOpeartion=new AdminOpeartion("normalize", account); return postToServer(adminOpeartion);}
	public static Object deleteUser(String account ) {AdminOpeartion adminOpeartion =new AdminOpeartion("delete",account ); return postToServer(adminOpeartion);}
	public static Object userModify(String account,String name,String image,String sex) {UserModify userModify=new UserModify(account, name, image, sex);return postToServer(userModify);}
	public static Object signup(String name,String sex,String psw) {SignupOpeartion signupOpeartion=new SignupOpeartion(name, sex, psw); return postToServer(signupOpeartion);}
	public static Object admin_getInfo() {AdminOpeartion adminOpeartion=new AdminOpeartion("getInfo", "0"); return postToServer(adminOpeartion);}

}

