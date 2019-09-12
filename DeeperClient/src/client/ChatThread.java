

package client;

import java.io.IOException;
import java.net.Socket;

import config.ServerInfo;


public final class ChatThread implements Runnable {

	private String userId;

	
	private static DataStream dataStream;

	
	public ChatThread(String userId) {
		this.userId = userId;
	}

	@Override
	public void run() {
		Socket myHost = null;
		try {

			myHost = new Socket(ServerInfo.SERVER_IP, ServerInfo.CHAT_PORT);

	
			dataStream = new DataStream(myHost, userId);
			System.out.println("���ӳɹ�");
			new Thread(dataStream).start();
		} catch (IOException e) {
			System.out.println("���������˵����ӳ���" + e.getMessage());
		}
	}


	public static DataStream getDataStream() {
		return dataStream;
	}
}
