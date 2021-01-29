/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.vente;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JTable;

/**
 *
 * @author VEK
 */
public class ButtonPanierEditorVente extends DefaultCellEditor{
    JSpinner Qte;
    protected JButton button; 
    private boolean admin=false;
    private int IDGest=0;
    private ButtonPanierListenerVente bListener;
    public ButtonPanierEditorVente(JCheckBox box,boolean ad,int id,JSpinner qte){
        super(box);
        Qte=qte;
        admin=ad;
        IDGest=id;
        button = new JButton(); 
        button.setOpaque(true);
        bListener =new ButtonPanierListenerVente(admin,IDGest,Qte);
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

    public ButtonPanierListenerVente getbListener() {
        return bListener;
    }
}
