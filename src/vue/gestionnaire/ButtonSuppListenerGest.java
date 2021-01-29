/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.gestionnaire;

import gestion.Authentification.AbstractDAO;
import gestion.Authentification.DAO;
import gestion.Authentification.Gestionnaire;
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
public class ButtonSuppListenerGest implements ActionListener{
    private boolean admin=false;
    private int IDGest=0;
    private int row;
    private int col;
    private JTable table;
    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
        int id=Integer.parseInt((getTable().getValueAt(getRow(),getCol()-6)).toString());
        DAO<Gestionnaire> gestdao=fac.getGestionnaireDAO();
        Gestionnaire gest=gestdao.rechercher(id);
        JOptionPane opt=new JOptionPane();
        if(opt.showConfirmDialog(null,"Voulez-vous Vraiment Supprimer le fournisseur","Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
            gestdao.supprimer(gest);
            admin();
        }
    }
    public void admin(){
            AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
            DAO<Gestionnaire> gestdao=fac.getGestionnaireDAO();
            ArrayList<ArrayList<Object>> data=new ArrayList<ArrayList<Object>>();
            for(Gestionnaire gest:gestdao.recuperer()){
                ArrayList<Object> ligne=new ArrayList<Object>();
                ligne.add(""+gest.getIdgest());
                ligne.add(gest.getNomgest());
                ligne.add(gest.getPrenomgest());
                ligne.add(gest.getSexe());
                ligne.add(gest.getAdressegest());
                ligne.add(gest.getTelgest());
                Button bt1=new Button("Supprimer");
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
