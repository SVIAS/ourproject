package client;
import java.util.Vector;
import java.util.function.Predicate;

import client.Network;
import config.SignupOpeartion;
import config.UserInfo.Friends;
import frame.MainInterface;
import sun.applet.Main;
import user.MessageRecord;
import user.User;
public class Model {

private User user =null;
private MainInterface mainInterface;
public Object login(String account,String psw) {
	String result=(String) Network.isLogin(account, psw);
	System.out.print(result);
	if(result.equals("success```normal")) user=Network.getUserInfo(account);
	return result;
}
public void setMain(MainInterface m) {mainInterface=m;}
public MainInterface getMain() {return mainInterface;}
public void setUser(User u) {user =u;}
public Object userModify(String name,String image,String sex) {return Network.userModify(user.getAccount(), name, image, sex);}
public Vector<Friends> search (String info,String type){return Network.search(info, type);}
public Object addFriend(String fromid,String toid) {return Network.addFriend(fromid, toid);}
public Object deleteFriend(String fromid,String toid) {return Network.deleteFriend(fromid, toid);}
public Object changePsw(String psw) {return Network.changePsw(user.getAccount(), psw);}
public Object deleteUser(String account) {return Network.deleteUser(account);}
public Object banUser(String account) {return Network.banUser(account);}
public Object normalizeUser(String account) {return Network.normalizeUser(account);}
public Object getUserInfo(String account) {return Network.getUserInfo(account);}
public String getUserid() {return user.getAccount();}
public String getUsername() {return user.getNickname();}
public Object Signup(String name,String sex,String psw) { return Network.signup(name,sex,psw);}
}
