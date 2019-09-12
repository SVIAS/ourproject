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
import java.awt.Desktop;
import java.awt.Dialog.ModalityType;
import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
public  class SearchFriend extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField searchInfo;
	private JComboBox select;
	private JLabel logo;
	private JButton close, minimize;
	private JButton search;
	private JPanel upPanel,downPanel, textFiledPanel;
	private SearchListener s;
	public String strcha;
	
	
	private void init() {
		
		upPanel = new JPanel();
		upPanel.setLayout(null);
		upPanel.setBounds(0, 0, 430, 183);
		upPanel.setBackground(new Color(207,207,207));
		
        logo = new JLabel();
		ImageIcon logoIcon = new ImageIcon("./res/mainpanel/Deeper.png");
		logoIcon.setImage(logoIcon.getImage().getScaledInstance(70, 30,Image.SCALE_DEFAULT));
		logo.setIcon(logoIcon);
		logo.setBounds(0,5, 70, 30);
		
		
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
		
		
        String str1[] = {"通过名字查找","通过性别查找", "通过账号查找"}; 
        select = new JComboBox(str1);
		select.setBounds(135,95, 195, 31);
		select.addItemListener(new ItemListener() {				
				@Override
				public void itemStateChanged(ItemEvent e) {
					
					if(e.getStateChange()== ItemEvent.SELECTED)
					{
						if(e.getItem().equals("通过名字查找")){
							strcha = "名字";

						}
						else if(e.getItem().equals("通过性别查找")) {
							strcha = "性别";

						}
						else if(e.getItem().equals("通过账号查找")) {
							strcha = "账号";
							
					    }
				};
			}
		});
		
		
		downPanel = new JPanel();
		downPanel.setLayout(null);
		downPanel.setBounds(0, 184, 430, 152);
		downPanel.setBackground(new Color(255, 255, 255));
		
		textFiledPanel = new JPanel();
		textFiledPanel.setBounds(135, 35, 195, 31);
		textFiledPanel.setLayout(null);

		
		searchInfo = new JTextField("请输入要查找好友的信息");
		searchInfo.setBounds(0, 0, 195, 31);
		searchInfo.setForeground(Color.GRAY);
		searchInfo.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (searchInfo.getText().trim().equals("")) {
					searchInfo.setForeground(Color.GRAY);
					searchInfo.setText("请输入要查找好友的信息");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (searchInfo.getText().trim().equals("请输入要查找好友的信息")) {
					searchInfo.setText("");
					searchInfo.setForeground(Color.BLACK);
				}
			}
		});
		
		
		
		search = new JButton("查 找");
		search.setMargin(new Insets(0, 0, 0, 0));
		search.setBounds(135, 105, 195, 31);
		search.setFont(new Font("System", Font.BOLD, 15));
		search.setFocusPainted(false);
		
		search.setBackground(new Color(139, 139, 131));// there will be annotation
		
		s = new SearchListener();
		s.setNow(this);
		s.setSearchInfo(searchInfo);
        s.setSearchCha(strcha);

		searchInfo.addActionListener(s);
		search.addActionListener(s);
	}



	public SearchFriend() {
		
		setLayout(null);
		
		setIconImage(Toolkit.getDefaultToolkit().createImage("./res/mainpanel/logo.png"));
		setTitle("查找窗口");
		init();
		upPanel.add(close);
		upPanel.add(minimize);
		upPanel.add(logo);
		upPanel.add(select);
		add(upPanel);
		textFiledPanel.add(searchInfo);
		downPanel.add(textFiledPanel);
		downPanel.add(search);
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

