/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.categorie;

import gestion.Article.Categorie;
import gestion.Authentification.AbstractDAO;
import gestion.Authentification.DAO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import vue.article.Button;
import vue.article.TModel;

/**
 *
 * @author VEK
 */
public class ButtonSuppListenerCat implements ActionListener {
    private boolean admin=false;
    private int IDGest=0;
    private int row;
    private int col;
    private JTable table;
    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
        int id=Integer.parseInt((getTable().getValueAt(getRow(),getCol()-4)).toString());
        DAO<Categorie> catdao=fac.getCategorieDAO();
        Categorie cat=catdao.rechercher(id);
        JOptionPane opt=new JOptionPane();
        if(opt.showConfirmDialog(null,"Voulez-vous Vraiment Supprimer la categorie","Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
            catdao.supprimer(cat);
            admin();
        }
    }
    public void admin(){
            AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
            DAO<Categorie> catdao=fac.getCategorieDAO();
            ArrayList<ArrayList<Object>> data=new ArrayList<ArrayList<Object>>();
            for(Categorie cat:catdao.recuperer()){
                ArrayList<Object> ligne=new ArrayList<Object>();
                ligne.add(""+cat.getIdcat());
                ligne.add(cat.getNomcat());
                ligne.add(cat.getComcat());
                Button bt=new Button("Modifier");
                Button bt1=new Button("Supprimer");
                bt.setBackground(Color.green);
                ligne.add(bt);
                ligne.add(bt1);
                data.add(ligne);
            }
            TableModel mod= table.getModel();
            TModel mod1=(TModel)mod;
            mod1.setData(data);
            table.repaint();
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
