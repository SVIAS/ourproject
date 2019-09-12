
package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



public final class DataStream implements Runnable {
	
	private Socket clientSocket;

	
	private DataInputStream in;

	
	private DataOutputStream out;


	private String userId;

	
	private String scMessage;


	public DataStream(Socket clientSocket, String userId) {
		this.clientSocket = clientSocket;
		this.userId = userId;
		try {
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			System.out.println("创建聊天数据流失败：" + e.getMessage());
		}
	}


	@Override
	public void run() {
		try {
			while (true) {
	
				scMessage = in.readUTF();


				ChatExecute.execute(scMessage);
			}
		} catch (IOException e) {
	
			try {
				in.close();
			} catch (Exception e2) {
				System.out.println("数据输入流关闭失败：" + e.getMessage());
			}
			try {
				out.close();
			} catch (Exception e2) {
				System.out.println("数据输出流关闭失败：" + e.getMessage());
			}
			try {
				clientSocket.close();
			} catch (IOException e1) {
				System.out.println("服务器连接关闭失败：" + e.getMessage());
			}
			System.out.println("与服务端失去联系 " + e.getMessage());
		}
	}


	public void send(String message, String toId) {

		message = ( "toFriend```") + userId + "```" + toId + "```" + message;
		try {
			out.writeUTF(message);
		} catch (IOException e) {
			System.out.println("发送消息失败：" + e.getMessage());
		}
	}
}
