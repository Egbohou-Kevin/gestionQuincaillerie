/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.article;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author VEK
 */
public class ButtonRenderer extends JButton implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //On écrit dans le bouton ce que contient la cellule 
       setText((value != null) ?value.toString() : ""); 
       //On retourne notre bouton 
       return this;

    }
    
}
