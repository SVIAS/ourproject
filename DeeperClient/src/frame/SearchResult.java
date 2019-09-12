
package frame;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Image;

import java.awt.Insets;

import java.awt.Toolkit;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.FocusEvent;

import java.awt.event.FocusListener;
import java.io.FileInputStream;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import config.UserInfo.Friends;
import client.Network;
import client.Register;
import config.UserInfo.Friends;
import user.User;
import java.awt.Desktop;
import java.awt.Dialog.ModalityType;
import java.awt.Font;


public  class SearchResult extends JFrame {
	private JButton  close, minimize;
	private JLabel logo;
	private JPanel upPanel,downPanel;
	private Vector<Friends> frilist;
	private JTable table;
	private Register register;
	
	private void init() {
		register =Register.getInstance();
		upPanel = new JPanel();
		upPanel.setLayout(null);
		upPanel.setBounds(0, 0, 430, 35);
		upPanel.setBackground(new Color(207,207,207));
		
		
		logo = new JLabel();
		ImageIcon logoIcon = new ImageIcon("./res/mainpanel/Deeper.png");
		logoIcon.setImage(logoIcon.getImage().getScaledInstance(70, 30,Image.SCALE_DEFAULT));
		logo.setIcon(logoIcon);
		logo.setBounds(0,5, 70, 30);
		 MyEvent e = new MyEvent() {
	            @Override
	            public void invoke(ActionEvent e) {

	            }

	        };

	       
		Vector col=new Vector();
		col.add("账号");
		col.add("昵称");
		col.add("性别");
		col.add(" ");
		Vector data2 =new Vector();
		if(!frilist.isEmpty())
		{for(int i=0;i<frilist.size();++i)
			{Vector row=new Vector();
			  row.add(frilist.get(i).getAccount());
			  row.add(frilist.get(i).getNickname());
			  row.add(frilist.get(i).getSex());
			  
			  data2.add(row);
			 }}
		DefaultTableModel model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
               
                    return false;
                }
            
        };

	 table = new JTable(model);
	 table.addMouseListener(new java.awt.event.MouseAdapter() {
     	public void mouseClicked(java.awt.event.MouseEvent e) {
     	if(table.getValueAt(table.getSelectedRow(),0)!=null)
     	{
     	if(table.getSelectedColumn()==3)
     	{
     		System.out.println("添加好友");
            int n = JOptionPane.showConfirmDialog(null, "确认添加好友吗?", "确认对话框", JOptionPane.YES_NO_OPTION); 
            if (n == JOptionPane.YES_OPTION) { 
            	String Toid= (String) table.getValueAt(table.getSelectedRow(), 0);
            	register.addFriend(register.getModel().getUserid(), Toid);
            	register.getMain().re();
            }
     	}
     	}
     	table.changeSelection(table.getSelectedRow(), 0, false, false);
     	repaint();
     	}
     	});
		model.setDataVector(data2, col); 

        MyButtonEditor editor = new MyButtonEditor(e);

	        table.getColumnModel().getColumn(3).setCellEditor(
	               editor);

	        table.getColumnModel().getColumn(3).setCellRenderer(
	                new MyButtonRender());

	        table.setRowSelectionAllowed(false);
		
		TableColumn column = null; 
		int colunms = table.getColumnCount();  
        for(int i = 0; i < colunms; i++)  
        {  
            column = table.getColumnModel().getColumn(i);  
     
            column.setPreferredWidth(100);  
        }  
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

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
		downPanel.setBounds(0, 36, 430, 300);
		downPanel.setBackground(new Color(255, 255, 255));

	}
	
	public SearchResult(Vector<Friends> fri) {
	
		frilist=fri;
		setLayout(null);

		setIconImage(Toolkit.getDefaultToolkit().createImage("./res/mainpanel/logo.png"));
		setTitle("结果窗口");
		init();
		upPanel.add(close);
		upPanel.add(minimize);
		upPanel.add(logo);
		add(upPanel);
		JScrollPane jscrollpane = new JScrollPane();
		jscrollpane.setBounds(0, 36, 430, 300);
		jscrollpane.setViewportView(table);
		table.setRowHeight(35); 
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();    
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class,r);

		downPanel.add(jscrollpane);
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
