/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.article;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import vue.IVue;

/**
 *
 * @author VEK
 */
public class ButtonListener implements ActionListener{
    private int row;
    private int col;
    private JTable table;

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
    @Override
    public void actionPerformed(ActionEvent e) {
         IVue vue=new Modification(this,table);
         vue.afficher();
    }
    
}
