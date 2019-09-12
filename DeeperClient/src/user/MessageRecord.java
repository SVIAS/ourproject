package user;

import java.util.Vector;

public class MessageRecord {
private Vector<Message> Msg;
public MessageRecord(Vector<String> m) {
	decoder(m);
}
public void decoder(Vector<String> m)
{
	
	for (int i = 0; i < m.size(); i++) {
		
		String res[] = m.get(i).split("```", 4);
		Msg.add(new Message(res[0], res[1], res[2], res[3]));
	
	}
	}
}
