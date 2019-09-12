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
import java.awt.event.ItemListener;
import java.io.FileInputStream;
import javax.swing.*;
import client.Network;
import client.Register;
import user.User;

import java.awt.Desktop;
import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
public class ChangeInfo extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField name;
	private JComboBox sex;
	private Register register;
	private JLabel logo;
	private JButton saveInfo , close, minimize;
	private JPanel upPanel, downPanel, textFiledPanel;
	private SaveInfoListener sI;
	private String strsex ;
	public String nowname;
	private JFrame parent;
	private void init() {
	  
		register =Register.getInstance();
		
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

		
	
		nowname = register.getModel().getUsername();
		name = new JTextField("昵称："+nowname);
		name.setBounds(0, 0, 195, 31);
		name.setForeground(Color.BLACK);
		name.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (name.getText().trim().equals("")) {
					name.setForeground(Color.BLACK);
					name.setText("昵称："+nowname);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (name.getText().trim().equals("昵称："+nowname)) {
					name.setText("");
					name.setForeground(Color.BLACK);
				}
			}
		});
		
		
		String str1[]= {"男","女"};
		sex = new JComboBox(str1);
		sex.setBounds(0, 31, 195, 31);
		sex.addItemListener(new ItemListener() {				
			@Override
			public void itemStateChanged(ItemEvent e) {
	
				if(e.getStateChange()== ItemEvent.SELECTED)
				{
					if(e.getItem().equals("男")){
                              strsex = "男";
					}
					else if(e.getItem().equals("女")) {
						      strsex = "女";
					}
			};
		}
	});

		

		saveInfo = new JButton("保存");
		saveInfo.setMargin(new Insets(0, 0, 0, 0));
		saveInfo.setBounds(135, 105, 195, 31);
		saveInfo.setFont(new Font("System", Font.BOLD, 15));
		saveInfo.setFocusPainted(false);
	
		saveInfo.setBackground(new Color(139, 139, 131));
		
		
		
		sI = new SaveInfoListener(parent);
		sI.setNow(this);
		sI.setName(name);
		sI.setSex(sex);
		name.addActionListener(sI);
		saveInfo.addActionListener(sI);
	}

	
	public ChangeInfo(JFrame p) {

		parent=p;
		setLayout(null);
	
		setIconImage(Toolkit.getDefaultToolkit().createImage("./res/mainpanel/logo.png"));
		setTitle("修改窗口");
		init();
		upPanel.add(close);
		upPanel.add(minimize);
		upPanel.add(logo);
		add(upPanel);
		textFiledPanel.add(name);
		textFiledPanel.add(sex);
		downPanel.add(textFiledPanel);
		downPanel.add(saveInfo);
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
