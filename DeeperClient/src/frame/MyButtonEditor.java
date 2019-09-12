package frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MyButtonEditor extends DefaultCellEditor{

    private MyButton button;

    private MyEvent event;

    public MyButtonEditor() {
        super(new JTextField());
        button = new MyButton("ÃÌº”∫√”—");
        button.setBackground(new Color(139, 139, 131));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    
            	
                event.invoke(e);
            }

        });

    }

    public MyButtonEditor(MyEvent e) {
        this();
        this.event = e;
    }
   
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
    setClickCountToStart(2);

        button.setRow(row);
        button.setColumn(column);
        return button;
    }


}
