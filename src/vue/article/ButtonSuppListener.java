/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.article;

import gestion.Article.Article;
import gestion.Authentification.AbstractDAO;
import gestion.Authentification.DAO;
import gestion.Vente.Vente;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author VEK
 */
public class ButtonSuppListener implements ActionListener{
    private boolean admin=false;
    private int IDGest=0;
    private int row;
    private int col;
    private JTable table;
    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
        int id=Integer.parseInt((getTable().getValueAt(getRow(),getCol()-8)).toString());
        DAO<Article> artdao=fac.getArticleDAO();
        Article art=artdao.rechercher(id);
        JOptionPane opt=new JOptionPane();
        if(opt.showConfirmDialog(null,"Voulez-vous Vraiment Supprimer l'article","Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
            artdao.supprimer(art);
        }
        admin();
    }
    
    public void admin(){
            AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
            DAO<Article> artdao=fac.getArticleDAO();
            ArrayList<ArrayList<Object>> data=new ArrayList<ArrayList<Object>>();
            for(Article art:artdao.recuperer()){
                ArrayList<Object> ligne=new ArrayList<Object>();
                ligne.add(""+art.getIdart());
                ligne.add(art.getNomart());
                ligne.add(art.getComart());
                ligne.add(""+art.getStock());
                ligne.add(""+art.getPrixart());
                ligne.add(art.getCatart().getNomcat());
                ligne.add(art.getFrs().getNomfrs());
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
