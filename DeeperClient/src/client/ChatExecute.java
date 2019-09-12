
package client;

import java.util.HashMap;

import frame.ChatWithFriend;

import frame.MainInterface;



public final class ChatExecute {

	private static String message;

	
	private static String fromId;

	
	private static String toId;

	
	private static String type;

	
	public static void execute(String scMessage) {

		String res[] = scMessage.split("```", 5);

		if (res.length == 5) {
			type = res[1];
			fromId = res[2];
			toId = res[3];
			message = res[4];

			HashMap<String, frame.ChatWithFriend> model;

			if (type.equals("toFriend")) {

				model = MainInterface.getFriendChat();

				if (model.containsKey(fromId)) {
					model.get(fromId).addMessage(MainInterface.getFriend().get(fromId).getfName(), res[0], message,
							false);
				}
		
		}
		}
		else if (res.length == 3) {
	
			if (res[0].equals("OnlineSituation")) {
				if (MainInterface.getFriend().containsKey(res[2])) {
					MainInterface.getFriend().get(res[2]).setfOnline(res[1]);
				}
			}
		}
		else if (res.length==2) {Register.getInstance().getMain().re();}
	}
}
