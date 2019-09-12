package frame;

import java.awt.event.ActionEvent;

import javax.swing.JTable;

public abstract class MyEvent {
	private JTable table;
	public abstract void invoke(ActionEvent e);

}
