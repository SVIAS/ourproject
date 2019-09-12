
package frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.*;

import client.Network;
import client.Register;
import config.UserInfo.Friends;
import sun.applet.Main;
import user.User;


public final class MainInterface extends JFrame implements ActionListener {

	
	private static final long serialVersionUID = 1L;
	private JPanel upPanel, downPanel, friendPane, groupPane;
	private JButton minimize, close, tradesButton, peopelButtonExtend, groupButtonExtend,searchButton,changeInfoButton,changepswButton;
	private JLabel logo ,  nameLabel;
	private JButton headPortrait;
	private Box nameBox;
	private JRadioButtonMenuItem items;
	private JTextField tradesTextField;
	private ButtonGroup peopelOrGroup;
	private JRadioButton peopelButton, groupButton;
	private User userInfo;
	private JScrollPane friendScrollPane;
	private ButtonGroup friendButtonGroup, groupButtonGroup;
	private Register register;
	private MainInterface mainInterface;
	private int delete;
	JTextArea textArea=new JTextArea();
	JPopupMenu  pMenu=new JPopupMenu(); 
	
	private static HashMap<String, FriendModel> friend;
	private static HashMap<String, GroupModel> group;
	private static HashMap<String, ChatWithFriend> withFriend;
	private static HashMap<String, ChatWithFriend> withGroup;

	static {
		friend = new HashMap<String, FriendModel>();
		group = new HashMap<String, GroupModel>();
		withFriend = new HashMap<String, ChatWithFriend>();
		withGroup = new HashMap<String, ChatWithFriend>();
	}
    public void setName(String n)
    {userInfo=new User(userInfo.getAccount(),n,userInfo.getUserSex(),userInfo.getImage(),"online", userInfo.getFriends());
     register.getModel().setUser(userInfo);
     nameLabel.setText(n);}
	public MainInterface(String userId) {

		System.out.println("init");
		
		mainInterface=this;
		
		register=Register.getInstance();
		register.setMain(mainInterface);
		items=new JRadioButtonMenuItem("删除好友");
		pMenu.add(items);
		ButtonGroup bg=new ButtonGroup(); 
		bg.add(items);

		userInfo = Network.getUserInfo(userId);
		register.getModel().setUser(userInfo);

		System.out.println("----------- 个人信息 --------------");
		System.out.println("ID：" + userInfo.getAccount());
		System.out.println("昵称：" + userInfo.getNickname());
		System.out.println("Email：sss" );
		System.out.println("性别：" + userInfo.getUserSex());
		System.out.println("生日：sss" );
		System.out.println("头像：" + userInfo.getImage());
		System.out.println("个性签名：sss" );
		System.out.println("注册时间：sss" );
		System.out.print("好友列表 : ");
		for (int i = 0; i < userInfo.getFriends().size(); i++)
			System.out.print(userInfo.getFriends().get(i).getNickname() + " ");
		
		System.out.println("\n----------- END --------------");


		setIconImage(Toolkit.getDefaultToolkit().createImage("./res/mainpanel/logo.png"));
		setTitle("主界面 --" + userInfo.getNickname() + "在线");
		init();
		setLayout(null);
		upPanel.add(close);
		upPanel.add(minimize);
        upPanel.add(logo);
		upPanel.add(headPortrait);
		upPanel.add(nameBox);
		upPanel.add(tradesButton);
		upPanel.add(tradesTextField);
		upPanel.add(searchButton);
		upPanel.add(changeInfoButton);
		upPanel.add(changepswButton);
		downPanel.add(peopelButtonExtend);
		downPanel.add(peopelButton);
		downPanel.add(groupButton);
		downPanel.add(groupButtonExtend);
		downPanel.add(friendScrollPane);
		add(upPanel);
		add(downPanel);
		add(textArea,BorderLayout.CENTER);
		add(pMenu); 

		LoginMousemove adapter = new LoginMousemove();
		addMouseMotionListener(adapter);
		addMouseListener(adapter);
		setSize(288, 573);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setLocationRelativeTo(null);
	
		setVisible(true);
	}

	private void init() {
		register=Register.getInstance();
		
		upPanel = new JPanel();
		upPanel.setLayout(null);
		upPanel.setBounds(0, 0, 288, 140);
		upPanel.setBackground(new Color(207, 207, 207));
	
		close = new JButton("");
		close.setMargin(new Insets(0, 0, 0, 0));
		close.setBounds(261, 0, 25, 27);
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
		minimize.setBounds(235, 0, 25, 27);
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
		
        logo = new JLabel();
		ImageIcon logoIcon = new ImageIcon("./res/mainpanel/Deeper.png");
		logoIcon.setImage(logoIcon.getImage().getScaledInstance(105,45,Image.SCALE_DEFAULT));
		logo.setIcon(logoIcon);
		logo.setBounds(0,0, 105, 45);
		
		headPortrait = new JButton();
		headPortrait.setBounds(10, 43, 61, 60);
		headPortrait.setVisible(true);
		Image headPic = (GetAvatar.getAvatarImage(userInfo.getAccount(), "./Data/Avatar/User/",
				userInfo.getImage())).getImage().getScaledInstance(61, 60, Image.SCALE_DEFAULT);
		headPortrait.setIcon(new ImageIcon(headPic));
		headPortrait.addActionListener(this);
		
		nameBox = Box.createHorizontalBox(); 
		nameBox.setBounds(77, 43, 158, 17);
		String username = userInfo.getNickname(); 

		nameLabel = new JLabel(username);
		nameLabel.setForeground(Color.WHITE);
		nameBox.add(nameLabel);
		
		searchButton = new JButton();
		searchButton.setMargin(new Insets(0, 0, 0, 0));
		searchButton.setBounds(10, 115, 51, 16);
		searchButton.setContentAreaFilled(false);
		searchButton.setBorderPainted(false);
		searchButton.setIcon(new ImageIcon("./res/mainpanel/search.png"));
		searchButton.setRolloverIcon(new ImageIcon("./res/mainpanel/search_hover.png"));
		searchButton.setPressedIcon(new ImageIcon("./res/mainpanel/search_press.png"));
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg) {
		
				new SearchFriend();
			}
		});
	
		changeInfoButton = new JButton();
		changeInfoButton.setMargin(new Insets(0, 0, 0, 0));
		changeInfoButton.setBounds(225, 95, 51, 16);
		changeInfoButton.setContentAreaFilled(false);
		changeInfoButton.setBorderPainted(false);
		changeInfoButton.setIcon(new ImageIcon("./res/mainpanel/changeInfo.png"));
		changeInfoButton.setRolloverIcon(new ImageIcon("./res/mainpanel/changeInfo_hover.png"));
		changeInfoButton.setPressedIcon(new ImageIcon("./res/mainpanel/changeInfo_press.png"));
		JFrame p= this;
		changeInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg) {
		
				new ChangeInfo(p);
			}
		});
	
		changepswButton = new JButton();
		changepswButton.setMargin(new Insets(0, 0, 0, 0));
		changepswButton.setBounds(225, 115, 51, 16);
		changepswButton.setContentAreaFilled(false);
		changepswButton.setBorderPainted(false);
		changepswButton.setIcon(new ImageIcon("./res/mainpanel/changepsw.png"));
		changepswButton.setRolloverIcon(new ImageIcon("./res/mainpanel/changepsw_hover.png"));
		changepswButton.setPressedIcon(new ImageIcon("./res/mainpanel/changepsw_press.png"));
		changepswButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg) {
			
				new Changepsw();
			}
		});

		
		tradesButton = new JButton();
		tradesButton.setHorizontalAlignment(SwingConstants.LEFT);
		tradesButton.setMargin(new Insets(0, 0, 0, 0));
		tradesButton.setBounds(77, 64, 137, 19);
		tradesButton.setContentAreaFilled(false);
		tradesButton.setBorderPainted(false);
		tradesButton.setRolloverIcon(new ImageIcon("./res/MainInterface/ContactFilter_splitter.png"));
		String trades = "个性签名";

		if (trades.equals("")) {
			trades = "编辑个性签名";
		}
		tradesButton.setText(trades);
		tradesButton.setToolTipText(trades);
		tradesTextField = new JTextField();
		tradesTextField.setBounds(77, 64, 137, 19);
		tradesTextField.setVisible(false);
		tradesButton.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				tradesButton.setVisible(false);
				tradesTextField.setVisible(true);
				if (tradesButton.getText().equals("编辑个性签名")) {
					tradesTextField.setText("");
				} else {
					tradesTextField.setText(tradesButton.getText());
				}
				tradesTextField.requestFocus();
			}
		});
		tradesTextField.addFocusListener(new FocusListener() { 

			@Override
			public void focusLost(FocusEvent e) {
				tradesTextField.setVisible(false);
				if (tradesTextField.getText().equals("")) {
					tradesButton.setText("编辑个性签名");
				} else {
					tradesButton.setText(tradesTextField.getText());
					System.out.println("无此功能");
				
					
				}
				

				tradesButton.setVisible(true);
			}

			@Override
			public void focusGained(FocusEvent e) {

			}
		});
		tradesTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tradesTextField.setVisible(false);
				if (tradesTextField.getText().equals("")) {
					tradesButton.setText("编辑个性签名");
				} else {
					tradesButton.setText(tradesTextField.getText());
				}
		

				tradesButton.setVisible(true);
			}
		});
		
		downPanel = new JPanel();
		downPanel.setLayout(null);
		downPanel.setBounds(0, 140, 288, 432);
		
		peopelButtonExtend = new JButton();
		peopelButtonExtend.setBounds(0, 0, 48, 36);
		peopelButtonExtend.setContentAreaFilled(false);
		peopelButtonExtend.setFocusPainted(false);
		peopelButtonExtend.setBorderPainted(false);
		peopelButtonExtend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				peopelButton.setSelected(true);
				peopelButton.requestFocus();
			}
		});
		peopelButton = new JRadioButton();
		peopelButton.setBounds(48, 0, 96, 36);
		peopelButton.setHorizontalTextPosition(SwingConstants.CENTER);
		peopelButton.setIcon(new ImageIcon("./res/mainpanel/icon_contacts_normal.png"));
		peopelButton.setRolloverIcon(new ImageIcon("./res/mainpanel/icon_contacts_hover.png"));
		peopelButton.setSelectedIcon(new ImageIcon("./res/mainpanel/icon_contacts_selected.png"));
		peopelButton.setSelected(true);
		peopelButton.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				friendScrollPane.setViewportView(friendPane);

			}
		});
		groupButtonExtend = new JButton();
		groupButtonExtend.setBounds(144, 0, 48, 36);
		groupButtonExtend.setContentAreaFilled(false);
		groupButtonExtend.setFocusPainted(false);
		groupButtonExtend.setBorderPainted(false);
		groupButtonExtend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				groupButton.setSelected(true);
				groupButton.requestFocus();
			}
		});
		groupButton = new JRadioButton();
		groupButton.setBounds(192, 0, 96, 36);
		groupButton.setIcon(new ImageIcon("./res/mainpanel/icon_group_normal.png"));
		groupButton.setRolloverIcon(new ImageIcon("./res/mainpanel/icon_group_hover.png"));
		groupButton.setSelectedIcon(new ImageIcon("./res/mainpanel/icon_group_selected.png"));
		groupButton.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

			}

			@Override
			public void focusGained(FocusEvent e) {
				friendScrollPane.setViewportView(groupPane);
			}
		});
		peopelOrGroup = new ButtonGroup();
		peopelOrGroup.add(peopelButton);
		peopelOrGroup.add(groupButton);
	
		
		
		int friendsNumber = userInfo.getFriends().size();
		friendPane = new JPanel();
		friendPane.setLayout(null);
		friendPane.setBounds(0, 0, 288, friendsNumber * 51);
		friendPane.setPreferredSize(new Dimension(270, friendsNumber * 51));
		friendButtonGroup = new ButtonGroup();
		
		for (int i = 0; i < friendsNumber; i++) {
			Friends userFriend = userInfo.getFriends().get(i);
			String fAvatar = userFriend.getImage(), fName = userFriend.getNickname(), fTrades = "notrade",
					fid = userFriend.getAccount(), fOnline = userFriend.getStatus();
			friend.put(fid, new FriendModel(fAvatar, fName, fTrades, fid, fOnline));
			friend.get(fid).setBounds(0, i * 51, 288, 51);
			friend.get(fid).addMouseListener(new MouseListener() {
			
				@Override
				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseExited(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					int i = e.getButton();
					
						
						
					if (e.getClickCount() == 2 && i==MouseEvent.BUTTON1) {
						withFriend.put(fid, new ChatWithFriend(userInfo.getAccount(), userInfo.getNickname(), fid,
								fAvatar, fName, fTrades, false));
						
					}
					
					else if(i == MouseEvent.BUTTON3)
					{	
						delete=0;
						pMenu.show(e.getComponent(),e.getX(),e.getY());
					}
				}
			});
			items.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(e.getSource()==items&&delete==0) 
					{
			            int n = JOptionPane.showConfirmDialog(null, "确认删除好友吗?", "确认对话框", JOptionPane.YES_NO_OPTION); 
			            if (n == JOptionPane.YES_OPTION) { 
			            	register.deleteFriend(fid);
			            	System.out.print("OK");
			            	++delete;
			            	re();
			            }

					}
				}

			});
			friendPane.add(friend.get(fid));
			friendButtonGroup.add(friend.get(fid));
		}
		
		int groupsNumber = 0;
		groupPane = new JPanel();
		groupPane.setLayout(null);
		groupPane.setBounds(0, 0, 288, groupsNumber * 51);
		groupPane.setPreferredSize(new Dimension(270, groupsNumber * 51));
		groupButtonGroup = new ButtonGroup();

		friendScrollPane = new JScrollPane(friendPane);

		friendScrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());

		friendScrollPane.getVerticalScrollBar().setUnitIncrement(20);
		friendScrollPane.setBounds(0, 36, 288, 395);
	}

	public static HashMap<String, FriendModel> getFriend() {
		return friend;
	};

	public static HashMap<String, GroupModel> getGroup() {
		return group;
	}

	public static HashMap<String, ChatWithFriend> getFriendChat() {
		return withFriend;
	}

	public static HashMap<String, ChatWithFriend> getGroupChat() {
		return withGroup;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jfc.showDialog(new JLabel(), "选择");
		File file = jfc.getSelectedFile();
		if (file.isDirectory()) {
			System.out.println("文件夹:" + file.getAbsolutePath());
		} else if (file.isFile()) {
			System.out.println("文件:" + file.getAbsolutePath());
		}
		System.out.println(jfc.getSelectedFile().getName());
	}
	public void re()
	{userInfo = Network.getUserInfo(userInfo.getAccount());
	register.getModel().setUser(userInfo);
	friendPane.removeAll();
	friend.clear();
	
	int friendsNumber = userInfo.getFriends().size();
	
	for (int i = 0; i < friendsNumber; i++) {
		Friends userFriend = userInfo.getFriends().get(i);
		String fAvatar = userFriend.getImage(), fName = userFriend.getNickname(), fTrades = "notrade",
				fid = userFriend.getAccount(), fOnline = userFriend.getStatus();
		friend.put(fid, new FriendModel(fAvatar, fName, fTrades, fid, fOnline));
		friend.get(fid).setBounds(0, i * 51, 288, 51);
		friend.get(fid).addMouseListener(new MouseListener() {
		
			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int i = e.getButton();
			
				
				if (e.getClickCount() == 2 && i==MouseEvent.BUTTON1) {
					withFriend.put(fid, new ChatWithFriend(userInfo.getAccount(), userInfo.getNickname(), fid,
							fAvatar, fName, fTrades, false));
				}
				
				else if(i == MouseEvent.BUTTON3)
				{delete=0;
					pMenu.show(e.getComponent(),e.getX(),e.getY());
				}
			}
		});
		items.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==items&&delete==0) 
				{
		            int n = JOptionPane.showConfirmDialog(null, "确认删除好友吗?", "确认对话框", JOptionPane.YES_NO_OPTION); 
		            if (n == JOptionPane.YES_OPTION) { 
		            	register.deleteFriend(fid);
		            	System.out.print("OK");
		            	++delete;
		            	re();
		            }

				}
			}

		});friendPane.add(friend.get(fid));
		friendButtonGroup.add(friend.get(fid));
	}
	friendPane.invalidate();
	friendPane.repaint();
	friendPane.validate();
	friendPane.setVisible(true);
	
}
	}