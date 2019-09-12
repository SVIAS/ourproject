package frame;
import java.awt.Color;

import java.awt.Image;

import java.awt.Insets;

import java.awt.Toolkit;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.FocusEvent;

import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.io.FileInputStream;
import javax.swing.*;
import java.awt.event.ItemListener;
import java.awt.Desktop;
import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public  class Signup extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField name;
	private JLabel logo;
	private JComboBox sex;
	private JPasswordField passwd;
	private JButton imediateregister, close, minimize;
	private JPanel upPanel, downPanel, textFiledPanel;
	private RegisterListener r;
	private String strsex;
	private JFrame parent;
	public JFrame getParent() {return parent;}
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
		ExitNowFrameListenter2 closeListenter = new ExitNowFrameListenter2(this,parent);
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

		
		name = new JTextField("请输入您的名字");
		name.setBounds(0, 0, 195, 20);
		name.setForeground(Color.GRAY);
		name.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (name.getText().trim().equals("")) {
					name.setForeground(Color.GRAY);
					name.setText("请输入您的名字");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (name.getText().trim().equals("请输入您的名字")) {
					name.setText("");
					name.setForeground(Color.BLACK);
				}
			}
		});
		
		
		String str1[]= {"男","女"};
		sex = new JComboBox(str1);
		sex.setBounds(0, 21, 195, 20);
		sex.addItemListener(new ItemListener() {				
			@Override
			public void itemStateChanged(ItemEvent e) {
		
				if(e.getStateChange()== ItemEvent.SELECTED)
				{
					if(e.getItem().equals("男")){
                              strsex = "男";
                              r.setSex("男");
					}
					else if(e.getItem().equals("女")) {
						      strsex = "女";
						      r.setSex("女");
					}
			};
		}
	});


		
		
		/*
		 * PasswordField passwd
		 */
		passwd = new JPasswordField("请输入您的密码");
		passwd.setBounds(0, 42, 195, 20);
		passwd.setEchoChar((char) 0);
		passwd.setForeground(Color.GRAY);
		passwd.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (String.valueOf(passwd.getPassword()).trim().equals("")) {
					passwd.setEchoChar((char) 0);
					passwd.setForeground(Color.GRAY);
					passwd.setText("请输入您的密码");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (String.valueOf(passwd.getPassword()).trim().equals("请输入您的密码")) {
					passwd.setEchoChar('•');
					passwd.setForeground(Color.BLACK);
					passwd.setText("");
				}
			}
		});

		/*
		 * Button register
		 */
		imediateregister = new JButton("立即注册");
		imediateregister.setMargin(new Insets(0, 0, 0, 0));
		imediateregister.setBounds(135, 105, 195, 31);
		imediateregister.setFont(new Font("System", Font.BOLD, 15));
		imediateregister.setFocusPainted(false);
		// login.setBorderPainted(false);
		imediateregister.setBackground(new Color(139, 139, 131));// there will be annotation
		
		
		/*
		 * init actionListener
		 */
		strsex="男";
		r = new RegisterListener();
		r.setNow(this);
		r.setName(name);
		r.setSex(strsex);
		r.setPasswd(passwd);
		r.setButton(imediateregister);
		name.addActionListener(r);
		sex.addActionListener(r);
		passwd.addActionListener(r);
		imediateregister.addActionListener(r);
	}

	
	public Signup(JFrame parent) {
		// System.out.println("123456");
		this.parent=parent;
		parent.setEnabled(false);
		setLayout(null);
		// 更改显示的小图标
		setIconImage(Toolkit.getDefaultToolkit().createImage("./res/mainpanel/logo.png"));
		setTitle("注册窗口");
		init();
		upPanel.add(close);
		upPanel.add(minimize);
		upPanel.add(logo);
		add(upPanel);
		textFiledPanel.add(name);
		textFiledPanel.add(sex);
		textFiledPanel.add(passwd);
		downPanel.add(textFiledPanel);
		downPanel.add(imediateregister);
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
