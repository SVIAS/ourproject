package user;

public class Message {
public String sender;
public String receiver;
public String sendTime;
public String content;
public Message(String sendtime,String sr,String rr,String ct) {

	sendTime=sendtime;
	sender=sr;
	receiver=rr;
	content=ct;
}
}
