/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.article;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author VEK
 */
public class ButtonEditor extends DefaultCellEditor {
    protected JButton button; 
    private ButtonListener bListener = new ButtonListener();
    public ButtonEditor(JCheckBox box){
        super(box);
        button = new JButton(); 
        button.setOpaque(true);
        button.addActionListener(bListener);

    }
    @Override
    public Component  getTableCellEditorComponent(JTable table, Object value,boolean isSelected, int row, int column){
        bListener.setRow(row);
        bListener.setCol(column); 
        bListener.setTable(table);
        button.setText( (value == null) ? "" : value.toString() ); 
        return button;
    }
}
