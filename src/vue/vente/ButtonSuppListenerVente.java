/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.vente;

import gestion.Article.Article;
import gestion.Authentification.AbstractDAO;
import gestion.Authentification.DAO;
import gestion.Authentification.Gestionnaire;
import gestion.Vente.Vente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import vue.article.Button;
import vue.article.TModel;

/**
 *
 * @author VEK
 */
public class ButtonSuppListenerVente implements ActionListener{
    private boolean admin=false;
    private int IDGest=0;
    private int row;
    private int col;
    private JTable table;
    @Override
    public void actionPerformed(ActionEvent e) {
        int id=Integer.parseInt((getTable().getValueAt(getRow(),getCol()-6)).toString());
        Vente vente=new Vente(id,"",0,new Date(),new Gestionnaire());
        AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
        JOptionPane opt=new JOptionPane();
        if(opt.showConfirmDialog(null,"Voulez-vous Vraiment Supprimer la Vente","Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
            DAO<Vente> ventedao=fac.getVenteDAO();
            ventedao.supprimer(vente);
            admin();
        }
    }
    
    public void admin(){
        AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
        DAO<Vente> vtdao=fac.getVenteDAO();
        ArrayList<ArrayList<Object>> data=new ArrayList<ArrayList<Object>>();
        for(Vente vente:vtdao.recuperer()){
            ArrayList<Object> ligne=new ArrayList<Object>();
            ligne.add(""+vente.getIdvente());
            ligne.add(vente.getDesvente());
            ligne.add(vente.getMontantvente());
            ligne.add(""+vente.getDatevente());
            ligne.add(""+vente.getGest().getNomgest());
            Button bt=new Button("Modifier");
            Button bt1=new Button("Supprimer");
            Button bt2=new Button("Detail");
            ligne.add(bt);
            ligne.add(bt1);
            ligne.add(bt2);
            data.add(ligne);
        }
        TableModel mod= table.getModel();
        TModel mod1=(TModel)mod;
        mod1.setData(data);
        table.repaint();
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
