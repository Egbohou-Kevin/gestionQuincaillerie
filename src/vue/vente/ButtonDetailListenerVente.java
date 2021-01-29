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
public class ButtonDetailListenerVente implements ActionListener {
    private boolean admin=false;
    private int IDGest=0;
    private int row;
    private int col;
    private JTable table;
    @Override
    public void actionPerformed(ActionEvent e) {
        int id=Integer.parseInt((getTable().getValueAt(getRow(),getCol()-7)).toString());
        IVue vue=new Detail(table, admin, IDGest, id);
        vue.afficher();
    }

    public boolean isAdmin() {
        return admin;
    }

    public int getIDGest() {
        return IDGest;
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

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setIDGest(int IDGest) {
        this.IDGest = IDGest;
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
