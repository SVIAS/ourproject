package user;

import java.util.Vector;

import config.UserInfo.Friends;

public class FriendsList {
private Vector<Friends> friends=null;
public FriendsList(Vector<Friends> f) {friends=f;
}
public void setFriends(Vector<Friends> f) {friends=f;}
public Vector<Friends> getFriends() {return friends;}
}
