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

import client.Register;
import config.UserInfo.Friends;

import java.awt.Desktop;
import java.awt.Dialog.ModalityType;
import java.awt.Font;
public class Administor extends JFrame {
	private JButton  close, minimize,Block, Unblock,Delete;
	private JLabel logo;
	private JPanel upPanel,downPanel;
	private JTable table;
	private Register register;
private void init() {
		register=Register.getInstance();
		Vector<String> data =(Vector) register.admin_getInfo();
		Vector col=new Vector();
		col.add("账号");
		col.add("状态");
		Vector info =new Vector();
		if(data.size()!=0)
		{for(int i=0;i<data.size();++i)
			{String[] str= data.get(i).split("```");

			Vector row =new Vector();
			row.add(str[0]);
			row.add(str[1]);
			
			info.add(row);
			}
		}
		upPanel = new JPanel();
		upPanel.setLayout(null);
		upPanel.setBounds(0, 0, 630, 35);
		upPanel.setBackground(new Color(207,207,207));
		
		Block = new JButton("封 号");
		Block.setMargin(new Insets(0, 0, 0, 0));
		Block.setBounds(430, 24, 195, 31);
		Block.setVisible(true);
		Block.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String tem = (String) table.getValueAt(table.getSelectedRow(), 0);
				register.banUser(tem);
				JOptionPane pane = new JOptionPane();				
				JOptionPane.showMessageDialog(pane, "封号成功" );
				table.setValueAt("ban",table.getSelectedRow(), 1);
			}
		});
		
		Unblock = new JButton("解 封");
		Unblock.setMargin(new Insets(0, 0, 0, 0));
		Unblock.setBounds(430, 72, 195, 31);
		Unblock.setVisible(true);
		Unblock.addActionListener(new ActionListener() {
			
		public void actionPerformed(ActionEvent e) {
			String tem = (String) table.getValueAt(table.getSelectedRow(), 0);
			register.normalizeUser(tem);
			JOptionPane pane = new JOptionPane();				
			JOptionPane.showMessageDialog(pane, "解封成功" );
			table.setValueAt("normal",table.getSelectedRow(), 1);
		}
	});
		
		Delete =new JButton("删 除");
		Delete.setMargin(new Insets(0, 0, 0, 0));
		Delete.setBounds(430, 120, 195, 31);
		Delete.setVisible(true);
		Delete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String tem = (String) table.getValueAt(table.getSelectedRow(), 0);
			register.deleteUser(tem);
			JOptionPane pane = new JOptionPane();				
			JOptionPane.showMessageDialog(pane, "删除成功" );
			table.setValueAt("Deleted",table.getSelectedRow(), 1);
		}
	});
		
		logo = new JLabel();
		ImageIcon logoIcon = new ImageIcon("./res/mainpanel/Deeper.png");
		logoIcon.setImage(logoIcon.getImage().getScaledInstance(70, 30,Image.SCALE_DEFAULT));
		logo.setIcon(logoIcon);
		logo.setBounds(0,5, 70, 30);
		
		close = new JButton();
		close.setMargin(new Insets(0, 0, 0, 0));
		close.setBounds(602, 0, 28, 28);
		close.setContentAreaFilled(false); 
		close.setBorderPainted(false); 
		close.setFocusPainted(false); 
		close.setToolTipText("关闭");
		close.setIcon(new ImageIcon("./res/Misc/FileManager/closebutton_normal.png"));
		close.setRolloverIcon(new ImageIcon("./res/Misc/FileManager/closebutton_hover.png"));
		close.setPressedIcon(new ImageIcon("./res/Misc/FileManager/closebutton_down.png"));
		ExitNowFrameListenter closeListenter = new ExitNowFrameListenter(this);
		close.addActionListener(closeListenter);
		//table
		DefaultTableModel model =new DefaultTableModel() {@Override 
			public boolean isCellEditable(int row,int column)
			{return false;}
		};
		
		model.setDataVector(info, col);
		table =new JTable(model);
		
	
		minimize = new JButton();
		minimize.setMargin(new Insets(0, 0, 0, 0));
		minimize.setBounds(574, 0, 28, 28);
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
		downPanel.setBounds(0, 36, 630, 500);
		downPanel.setBackground(new Color(255, 255, 255));
}
public Administor() {

	setLayout(null);

	setIconImage(Toolkit.getDefaultToolkit().createImage("./res/mainpanel/logo.png"));
	setTitle("结果窗口");
	init();
	upPanel.add(close);
	upPanel.add(minimize);
	upPanel.add(logo);
	add(upPanel);
	
	JScrollPane jscrollpane = new JScrollPane();
	jscrollpane.setBounds(0, 0, 400, 500);
	jscrollpane.setViewportView(table);
	table.setRowHeight(35); 
	DefaultTableCellRenderer r = new DefaultTableCellRenderer();    
	r.setHorizontalAlignment(JLabel.CENTER);   
	table.setDefaultRenderer(Object.class,r);

	downPanel.add(jscrollpane);
	downPanel.add(Block);
	downPanel.add(Unblock);
	downPanel.add(Delete);
	add(downPanel);
	LoginMousemove adapter = new LoginMousemove();
	addMouseMotionListener(adapter);
	addMouseListener(adapter);
	setSize(630, 535);

	setUndecorated(true);
	
	setLocationRelativeTo(null);
	setResizable(false);
	setVisible(true);
	
}

}
