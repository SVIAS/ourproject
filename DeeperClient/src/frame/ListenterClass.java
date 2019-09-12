
package frame;

import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import frame.Login;
import frame.ChangeInfo;
import client.ChatThread;
import client.Network;
import client.Register;
import config.UserInfo.Friends;


class ExitListenter implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}

class ExitNowFrameListenter implements ActionListener {
	private JFrame now;
	public ExitNowFrameListenter(JFrame now) {
		this.now = now;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		now.dispose();
	}
}

class ExitNowFrameListenter2 implements ActionListener {
	private JFrame now;
	private JFrame parent;
	public ExitNowFrameListenter2(JFrame now,JFrame parent) {
		this.now = now;
		this.parent=parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.parent.setEnabled(true);
		now.dispose();
	}
}
class SignupFrameListenter implements ActionListener 
{private JFrame parent;
 public SignupFrameListenter (JFrame p)
 {parent=p;}
 public void actionPerformed(ActionEvent e) {
		this.parent.setEnabled(false);
		new Signup(parent);
	}
 }

class LoginMousemove extends MouseAdapter {
	private int offsetX, offsetY;
	private boolean isCanMove;

	public LoginMousemove() {
		isCanMove = true;
	}

	public void setCanMove(boolean isCanMove) {
		this.isCanMove = isCanMove;
	}

	public void mouseDragged(MouseEvent e) {
		// requires JDK 1.6 or above
		if (isCanMove) {
			SwingUtilities.getRoot((Component) e.getSource()).setLocation(e.getXOnScreen() - offsetX,
					e.getYOnScreen() - offsetY);
		}
	}

	public void mousePressed(MouseEvent e) {
		offsetX = e.getX();
		offsetY = e.getY();
	}
}

class RegisterListener implements ActionListener {
	JFrame now;
	JTextField name;
	String strsex;
	JPasswordField passwd;
	JButton button;
	public void setNow(JFrame now) {
		this.now = now;
	}

	public void setName(JTextField name) {
		this.name = name;
	}
	
	public void setSex(String strsex) {
		this.strsex = strsex;
		System.out.println(strsex);
	}

	public void setPasswd(JPasswordField passwd) {
		this.passwd = passwd;
	}
	public void setButton(JButton button) {
		this.button = button;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	    System.out.println(strsex);
		System.out.println(e.getSource().toString());
		
		new Thread(new Runnable() {
			@Override
			
			public void run() {
				// ��ȡ�ı�������
				if(e.getSource()==button)
				{String userNameString = name.getText().trim();
				String userSexString = strsex;
				String userPasswordString = String.valueOf(passwd.getPassword()).trim();
				
				if(userNameString.equals("��������������")){JOptionPane pane = new JOptionPane();				
				JOptionPane.showMessageDialog(pane, "����Ϊ��");
				return;}
				if(userPasswordString.equals("��������������")){JOptionPane pane = new JOptionPane();				
				JOptionPane.showMessageDialog(pane, "����Ϊ��");
				return;}
				Register register=Register.getInstance();
				String result=(String)register.Signup(userNameString, userSexString, userPasswordString);
				String psw ="admin";
				String r=(String)register.Signup("admin", userSexString, psw);
				String[] str=r.split("```");
				String account=str[1];
				JOptionPane pane = new JOptionPane();				
				JOptionPane.showMessageDialog(pane, "ע��ɹ��������˺�Ϊ��"+ account );
				
				
				now.getParent().setEnabled(true);
				now.dispose();

			}}
		}).start();
	}
}

class SaveInfoListener implements ActionListener {
	JFrame now;
	JTextField name;
	JComboBox sex;
	MainInterface parent;
	public SaveInfoListener(JFrame p)
	{parent=(MainInterface)p;}
	public void setNow(JFrame now) {
		this.now = now;
	}

	public void setName(JTextField name) {
		this.name = name;
	}
	
	public void setSex(JComboBox b) {
		this.sex = b;
	}

	/*
	 * ���� Javadoc��
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * ������水ť
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println("try login");
		new Thread(new Runnable() {
			@Override
			public void run() {
				// ��ȡ�ı�������
				String userNameString = name.getText().trim();
				String userSexString = (String) sex.getSelectedItem();
				Register register=Register.getInstance();
				Boolean result=(Boolean) Network.userModify(register.getModel().getUserid(), userNameString, "default", userSexString);
				if(result) {
				JOptionPane pane = new JOptionPane();				
				JOptionPane.showMessageDialog(pane, "�޸���Ϣ�ɹ�" );
				parent.setName(userNameString);
				now.dispose();
				}
				else {
					JOptionPane pane = new JOptionPane();				
					JOptionPane.showMessageDialog(pane, "�޸���Ϣʧ��" );
				}
			}
		}).start();
	}
}

/**   
 * @ClassName:  LoginListener
 * @Description: ��õ�¼��Ϣ������������ 
 * @author: ����ɭ 
 * @date:   2016-12-8  
 *   
 * class
 * @Copyright: 2016 www.ireson.cn Inc. All rights reserved.  
 */  
class LoginListener implements ActionListener {
	JFrame now;
	JTextField userId;
	JPasswordField passwd;
    Register register=Register.getInstance();
	public void setNow(JFrame now) {
		this.now = now;
	}

	public void setUserId(JTextField userId) {
		this.userId = userId;
	}

	public void setPasswd(JPasswordField passwd) {
		this.passwd = passwd;
	}


	/*
	 * ���� Javadoc��
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * �����¼��ť
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println("try login");
		new Thread(new Runnable() {
			@Override
			public void run() {
				// ��ȡ�ı�������
				String userIdString = userId.getText().trim();
				String userPasswordString = String.valueOf(passwd.getPassword()).trim();
				// ��֤�û��������Ƿ���ȷ
				Object isLoginSuccess= register.login(userIdString, userPasswordString);
				//Object isLoginSuccess = Network.isLogin(userIdString, userPasswordString);

				System.out.println("��ǰ��¼״̬��" + isLoginSuccess);
				if (isLoginSuccess != null) {
					String loginResult = isLoginSuccess.toString();
					if (loginResult.equals("success```normal")) {
						now.dispose();
						// �����߳̽�������˿�
						new Thread(new ChatThread(userIdString)).start();
						new MainInterface(userIdString);

					} else if (loginResult.equals("Repeat_login")) {
						JOptionPane.showMessageDialog(now, "�ظ���¼");
					} 
					else if(loginResult.equals("success```admin"))
					{
						new Administor();
					}
					else {
						JOptionPane.showMessageDialog(now, "���ĵ�½��Ϣ����", "��½ʧ��", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(now, "���������������ʧ��");
				}
			}
		}).start();
	}
}


class ChangepswListener implements ActionListener {
	JFrame now;
	JPasswordField oldpasswd;
	JPasswordField newpasswd;

	public void setNow(JFrame now) {
		this.now = now;
	}

	public void setoldPasswd(JPasswordField oldpasswd) {
		this.oldpasswd = oldpasswd;
	}
	public void setnewPasswd(JPasswordField newpasswd) {
		this.newpasswd = newpasswd;
	}
	/*
	 * ���� Javadoc��
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * ������水ť
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println("try login");
		new Thread(new Runnable() {
			@Override
			public void run() {
				// ��ȡ�ı�������
			
				String usernewPasswordString = String.valueOf(newpasswd.getPassword()).trim();
				Register register=Register.getInstance();
				// ��֤�����Ƿ���ȷ
				Boolean PswSuccess = (Boolean) register.changePsw(usernewPasswordString);
				JOptionPane pane = new JOptionPane();				
				JOptionPane.showMessageDialog(pane, "�޸�����ɹ�" );
                now.dispose();
			}
		}).start();
	}
}

class SearchListener implements ActionListener {
	SearchFriend now;
	JTextField search;
	String strcha;

	public void setNow(JFrame now) {
		this.now = (SearchFriend)now;
	}

	public void setSearchInfo(JTextField search) {
		this.search = search;
	}
	public void setSearchCha(String strcha) {
		this.strcha = strcha;
	}
    public SearchListener()
    {strcha="����";}
	/*
	 * ���� Javadoc��
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * ������Ұ�ť
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println("try login");
		new Thread(new Runnable() {
			@Override
			public void run() {
				// ��ȡ�ı�������
				String searchString = search.getText().trim();
				Register r=Register.getInstance();
				Vector<Friends> fri;
				strcha=now.strcha;
				if(strcha.equals("����"))
				{
					fri=r.findPersonByName(searchString);
					new SearchResult(fri);
				}
				else if(strcha.equals("�Ա�"))
				{
					fri=r.findPersonBySex(searchString);
					new SearchResult(fri);
				}
				else//�˺�
				{
					fri=r.findPersonByAccount(searchString);
					new SearchResult(fri);
				}
				
				if(fri.isEmpty()!=true)
				{
					System.out.println("��Ϊ��");
				}
				
				now.dispose();
				
				//����������ʾInfoUI�������µĽ���Ȼ��Ѳ��ҳ������б���ʾ����
			}
		}).start();
	}
}
/**   
 * @ClassName:  SendFriend  
 * @Description: �Ժ��ѷ�����Ϣ   
 * @author: ����ɭ 
 * @date:   2016-12-8  
 *   
 * class
 * @Copyright: 2016 www.ireson.cn Inc. All rights reserved.  
 */  
class SendFriend implements ActionListener {
	private JTextArea message;
	private String mName;
	private String fid;
	private ChatWithFriend now;
	private boolean isGroup;

	public SendFriend(String mName, String fid, boolean isGroup) {
		this.mName = mName;
		this.fid = fid;
		this.isGroup = isGroup;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ������Ϣ
		if(this.message.getText().trim().length()!=0){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			String date = df.format(new Date());
		now.addMessage(mName, date, this.message.getText(), false);
		ChatThread.getDataStream().send(this.message.getText(), fid);
		this.message.setText("");
		}
		else
			{JOptionPane.showMessageDialog(now, "������Ϣ����Ϊ�գ�����������");}
	}

	public void setMessage(JTextArea message) {
		this.message = message;
	}

	public void setNow(ChatWithFriend now) {
		this.now = now;
	}
}