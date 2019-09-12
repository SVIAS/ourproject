package client;

import java.util.Vector;

import javax.swing.JOptionPane;

import config.FriendOpeartion;
import config.UserInfo.Friends;
import frame.MainInterface;
import sun.applet.Main;
import user.User;


public class Register {
	private Model model;
	private static  Register INSTANCE =new Register();
	private MainInterface mainInterface;
	public void setMain(MainInterface m) {mainInterface=m;}
	public MainInterface getMain() {return mainInterface;}
	private Register() {model=new Model();}
	
	public static Register getInstance() {return INSTANCE;}
	public Object admin_getInfo() {return Network.admin_getInfo();}
	public String login(String account,String psw)
	{
		return (String)model.login(account, psw);
		
	}
	public Model getModel() {return model;}
	public User getBasicInfo(String account)
	{
		return (User)model.getUserInfo(account);
	}
	
	public Vector<Friends> findPersonByAccount(String account)
	{

		Vector<Friends> fri =(Vector<Friends>) model.search(account, "Account");
		return fri;

	}
	
	public Vector<Friends> findPersonByName(String Name)
	{
	
		Vector<Friends> fri = model.search(Name, "Name");
		return fri;

	}
	
	public Vector<Friends> findPersonBySex(String Sex)
	{
	
		Vector<Friends> fri = model.search(Sex,"Sex");
		return fri;
	
	}
	
	public void addFriend(String user1,String user2)
	{
		model.addFriend(user1, user2);
	}
	
	public void deleteFriend(String FriendID)
	{
		model.deleteFriend(model.getUserid(), FriendID);
	}
	
	public void deleteUser(String userID)
	{
		model.deleteUser(userID);


	}
	
	public void banUser(String userID)
	{
		model.banUser(userID);
	}
	
	public void normalizeUser(String userID)
	{
		model.normalizeUser(userID);
	}
	
	public Object changePsw(String psw)
	{
	
		
		return model.changePsw(psw);
	
	}
	
	public Object userModify(String Name,String Image,String Sex)
	{
		return model.userModify(Name, Image, Sex);

	}
	
	public Object Signup(String name,String sex,String psw)
	{
		return model.Signup(name,sex,psw);

	}
	
	
}
