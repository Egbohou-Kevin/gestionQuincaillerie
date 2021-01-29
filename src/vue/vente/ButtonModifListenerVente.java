/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.vente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import vue.IVue;

/**
 *
 * @author VEK
 */
public class ButtonModifListenerVente implements ActionListener{
    private boolean admin=false;
    private int IDGest=0;
    private int row;
    private int col;
    private JTable table;
    public ButtonModifListenerVente(boolean ad,int id){
        admin=ad;
        IDGest=id;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
         IVue vue=new Modifier(this,table,admin,IDGest);
         vue.afficher();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public JTable getTable() {
        return table;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setTable(JTable table) {
        this.table = table;
    }
    
}
