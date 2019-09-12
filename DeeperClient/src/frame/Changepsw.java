package frame;
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
import client.Network;
import client.Register;
import java.awt.Desktop;
import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Changepsw extends JFrame {
	private JLabel logo;
	private JPasswordField oldpasswd,newpasswd;
	private JButton savepsw, close, minimize;
	private JPanel upPanel, downPanel, textFiledPanel;
	private ChangepswListener sp;
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
		ExitNowFrameListenter closeListenter = new ExitNowFrameListenter(this);
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

		
		
		
		textFiledPanel = new JPanel();
		textFiledPanel.setBounds(135, 11, 195, 62);
		textFiledPanel.setLayout(null);

	
		oldpasswd = new JPasswordField("请输入您的旧密码");
		oldpasswd.setBounds(0, 0, 195, 31);
		oldpasswd.setEchoChar((char) 0);
		oldpasswd.setForeground(Color.GRAY);
		oldpasswd.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (String.valueOf(oldpasswd.getPassword()).trim().equals("")) {
					oldpasswd.setEchoChar((char) 0);
					oldpasswd.setForeground(Color.GRAY);
					oldpasswd.setText("请输入您的旧密码");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (String.valueOf(oldpasswd.getPassword()).trim().equals("请输入您的旧密码")) {
					oldpasswd.setEchoChar('•');
					oldpasswd.setForeground(Color.BLACK);
					oldpasswd.setText("");
				}
			}
		});
		
		newpasswd = new JPasswordField("请输入您的新密码");
		newpasswd.setBounds(0, 31, 195, 31);
		newpasswd.setEchoChar((char) 0);
		newpasswd.setForeground(Color.GRAY);
		newpasswd.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (String.valueOf(oldpasswd.getPassword()).trim().equals("")) {
					newpasswd.setEchoChar((char) 0);
					newpasswd.setForeground(Color.GRAY);
					newpasswd.setText("请输入您的新密码");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (String.valueOf(newpasswd.getPassword()).trim().equals("请输入您的新密码")) {
					newpasswd.setEchoChar('•');
					newpasswd.setForeground(Color.BLACK);
					newpasswd.setText("");
				}
			}
		});
		
		
		/*
		 * Button save
		 */
		savepsw = new JButton("保存");
		savepsw.setMargin(new Insets(0, 0, 0, 0));
		savepsw.setBounds(135, 105, 195, 31);
		savepsw.setFont(new Font("System", Font.BOLD, 15));
		savepsw.setFocusPainted(false);
		// login.setBorderPainted(false);
		savepsw.setBackground(new Color(139, 139, 131));// there will be annotation
		
		
		/*
		 * init actionListener
		 */
		sp = new ChangepswListener();
		sp.setNow(this);
		sp.setoldPasswd(oldpasswd);
		sp.setnewPasswd(newpasswd);
		oldpasswd.addActionListener(sp);
		newpasswd.addActionListener(sp);
		savepsw.addActionListener(sp);
	}

	
	public Changepsw() {
		// System.out.println("123456");
		setLayout(null);
		// 更改显示的小图标
		setIconImage(Toolkit.getDefaultToolkit().createImage("./res/mainpanel/logo.png"));
		setTitle("修改窗口");
		init();
		upPanel.add(close);
		upPanel.add(minimize);
		upPanel.add(logo);
		add(upPanel);
		textFiledPanel.add(newpasswd);
		textFiledPanel.add(oldpasswd);
		downPanel.add(textFiledPanel);
		downPanel.add(savepsw);
		add(downPanel);
		LoginMousemove adapter = new LoginMousemove();
		addMouseMotionListener(adapter);
		addMouseListener(adapter);
		setSize(430, 335);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
		// setExtendedState(JFrame.MAXIMIZED_BOTH); //set Jframe size？
		setUndecorated(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

}
