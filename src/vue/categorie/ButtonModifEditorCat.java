/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.categorie;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import vue.vente.ButtonModifListenerVente;

/**
 *
 * @author VEK
 */
public class ButtonModifEditorCat extends DefaultCellEditor {
    protected JButton button; 
    private boolean admin=false;
    private int IDGest=0;
    private ButtonModifListenerCat bListener;
    public ButtonModifEditorCat(JCheckBox box,boolean ad,int id){
        super(box);
        admin=ad;
        IDGest=id;
        button = new JButton(); 
        button.setOpaque(true);
        bListener =new ButtonModifListenerCat(admin,IDGest);
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
