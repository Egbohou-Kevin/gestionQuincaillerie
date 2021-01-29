/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.article;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author VEK
 */
public class TModel extends AbstractTableModel{
    
    private ArrayList<ArrayList<Object>> data; 
    private ArrayList<String> title;
//Constructeur
    public TModel(ArrayList<ArrayList<Object>> data, ArrayList<String> title){ 
        this.data = data;
        this.title = title; 
    }

    public ArrayList<ArrayList<Object>> getData() {
        return data;
    }

    public ArrayList<String> getTitle() {
        return title;
    }

    public void setData(ArrayList<ArrayList<Object>> data) {
        this.data = data;
    }

    public void setTitle(ArrayList<String> title) {
        this.title = title;
    }

    
    @Override
    public int getRowCount() {
        return this.data.size();
    }

    @Override
    public int getColumnCount() {
        return this.title.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        return this.data.get(row).get(col);

    }
    public String getColumnName(int col) {
        return this.title.get(col);
    }

    @Override
    public boolean isCellEditable(int row, int col){
        if(getValueAt(row, col) instanceof JButton){
            return true;
        }
        return false;
    }
}
