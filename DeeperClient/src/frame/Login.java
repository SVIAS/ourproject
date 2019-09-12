
package frame;

import frame.Signup;
import java.awt.Color;

import java.awt.Image;

import java.awt.Insets;

import java.awt.Toolkit;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.FocusEvent;

import java.awt.event.FocusListener;
import java.io.FileInputStream;
import javax.swing.*;

import java.awt.Desktop;
import java.awt.Dialog.ModalityType;
import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public  class Login extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField userId;
	private JLabel headPortrait,logo;
	private JPasswordField passwd;
	private JButton login, close, minimize;
	private JButton register;
	private JPanel upPanel, downPanel, textFiledPanel;
	private LoginListener l;
	private String username, userPasswd;

	private void init() {
	
		upPanel = new JPanel();
		upPanel.setLayout(null);
		upPanel.setBounds(0, 0, 430, 183);
		upPanel.setBackground(new Color(207,207,207));
		
        logo = new JLabel();
		ImageIcon logoIcon = new ImageIcon("./res/mainpanel/Deeper.png");
		logoIcon.setImage(logoIcon.getImage().getScaledInstance(280, 120,Image.SCALE_DEFAULT));
		logo.setIcon(logoIcon);
		logo.setBounds(70,45, 280, 120);
		
		
		close = new JButton();
		close.setMargin(new Insets(0, 0, 0, 0));
		close.setBounds(402, 0, 28, 28);
		close.setContentAreaFilled(false); 
		close.setBorderPainted(false); 
		close.setFocusPainted(false); 
		close.setToolTipText("关闭");
		close.setIcon(new ImageIcon("./res/Misc/FileManager/closebutton_normal.png"));
		close.setRolloverIcon(new ImageIcon("./res/Misc/FileManager/closebutton_hover.png"));
		close.setPressedIcon(new ImageIcon("./res/Misc/FileManager/closebutton_down.png"));
		ExitListenter closeListenter = new ExitListenter();
		close.addActionListener(closeListenter);
	
		minimize = new JButton();
		minimize.setMargin(new Insets(0, 0, 0, 0));
		minimize.setBounds(374, 0, 28, 28);
		minimize.setContentAreaFilled(false);
		minimize.setBorderPainted(false);
		minimize.setFocusPainted(false);
		minimize.setToolTipText("最小化");
		minimize.setIcon(new ImageIcon("./res/Misc/FileManager/minbutton_normal.png"));
		minimize.setRolloverIcon(new ImageIcon("./res/Misc/FileManager/minbutton_hover.png"));
		minimize.setPressedIcon(new ImageIcon("./res/Misc/FileManager/minbutton_down.png"));
		minimize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.ICONIFIED);
			}
		});

		
		downPanel = new JPanel();
		downPanel.setLayout(null);
		downPanel.setBounds(0, 184, 430, 152);
		downPanel.setBackground(new Color(255, 255, 255));
		
		headPortrait = new JLabel();
		headPortrait.setBounds(44, 11, 82, 83);
		String headPortraitPostion = "./res/DefaultHead.jpg";

		Image headPic = (new ImageIcon(headPortraitPostion)).getImage().getScaledInstance(82, 83, Image.SCALE_DEFAULT);
	
		headPortrait.setIcon(new ImageIcon(headPic));
		
		textFiledPanel = new JPanel();
		textFiledPanel.setBounds(135, 11, 195, 62);
		textFiledPanel.setLayout(null);

	
		userId = new JTextField("账号");
		userId.setBounds(0, 0, 195, 31);
		userId.setForeground(Color.GRAY);
		userId.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (userId.getText().trim().equals("")) {
					userId.setForeground(Color.GRAY);
					userId.setText("账号");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (userId.getText().trim().equals("账号")) {
					userId.setText("");
					userId.setForeground(Color.BLACK);
				}
			}
		});
	
		passwd = new JPasswordField("密码");
		passwd.setBounds(0, 31, 195, 31);
		passwd.setEchoChar((char) 0);
		passwd.setForeground(Color.GRAY);
		passwd.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (String.valueOf(passwd.getPassword()).trim().equals("")) {
					passwd.setEchoChar((char) 0);
					passwd.setForeground(Color.GRAY);
					passwd.setText("密码");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (String.valueOf(passwd.getPassword()).trim().equals("密码")) {
					passwd.setEchoChar('•');
					passwd.setForeground(Color.BLACK);
					passwd.setText("");
				}
			}
		});
	
		register = new JButton();
		register.setMargin(new Insets(0, 0, 0, 0));
		register.setBounds(10, 126, 51, 16);
		register.setContentAreaFilled(false);
		register.setBorderPainted(false);
		register.setIcon(new ImageIcon("./res/Loginpanel2/zhuce.png"));
		register.setRolloverIcon(new ImageIcon("./res/Loginpanel2/zhuce_hover.png"));
		register.setPressedIcon(new ImageIcon("./res/Loginpanel2/zhuce_press.png"));
		register.addActionListener(new SignupFrameListenter(this));

		
	
		login = new JButton("登   录");
		login.setMargin(new Insets(0, 0, 0, 0));
		login.setBounds(135, 105, 195, 31);
		login.setFont(new Font("System", Font.BOLD, 15));
		login.setFocusPainted(false);

		login.setBackground(new Color(139, 139, 131));// there will be annotation
		
		l = new LoginListener();
		l.setNow(this);
		l.setUserId(userId);
		l.setPasswd(passwd);

		userId.addActionListener(l);
		passwd.addActionListener(l);
		login.addActionListener(l);
		try {
			FileInputStream in = new FileInputStream("./Data/UserInfo.uif");
			int t;
			username = "";
			userPasswd = "";
			while ((t = in.read()) != -1) {
				if (t == '\n')
					break;
				t ^= 'I';
				username = username + (char) t;
			}
			if (!username.equals("")) {
				while ((t = in.read()) != -1) {
					if (t == '\n')
						break;
					t ^= 'P';
					userPasswd = userPasswd + (char) t;
				}
				userId.setForeground(Color.BLACK);
				userId.setText(username);
				passwd.setEchoChar('•');
				passwd.setForeground(Color.BLACK);
				passwd.setText(userPasswd);
				t = (char) in.read();
			}
			in.close();
		} catch (Exception e) {

		}
	}


	public Login() {

		setLayout(null);

		setIconImage(Toolkit.getDefaultToolkit().createImage("./res/mainpanel/logo.png"));
		setTitle("登录窗口");
		init();
		upPanel.add(close);
		upPanel.add(minimize);
		upPanel.add(logo);
		add(upPanel);
		downPanel.add(headPortrait);
		textFiledPanel.add(userId);
		textFiledPanel.add(passwd);
		downPanel.add(textFiledPanel);
		downPanel.add(register);
		downPanel.add(login);
		add(downPanel);
		LoginMousemove adapter = new LoginMousemove();
		addMouseMotionListener(adapter);
		addMouseListener(adapter);
		setSize(430, 335);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}
