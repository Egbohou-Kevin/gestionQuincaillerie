/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.vente;

import gestion.Article.Article;
import gestion.Authentification.AbstractDAO;
import gestion.Authentification.DAO;
import gestion.Vente.Panier;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;

/**
 *
 * @author VEK
 */
public class ButtonPanierListenerVente implements ActionListener{
    JSpinner Qte;
    private Panier panier=new Panier();
    private boolean admin=false;
    private int IDGest=0;
    private int row;
    private int col;
    private JTable table;
    JButton jButton_val;
    public ButtonPanierListenerVente(boolean ad,int id,JSpinner qte){
        Qte=qte;
        admin=ad;
        IDGest=id;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
        DAO<Article> artdao=fac.getArticleDAO();
        int id=Integer.parseInt((getTable().getValueAt(getRow(),getCol()-5)).toString());
        Article art=artdao.rechercher(id);
        if(Integer.parseInt(Qte.getValue().toString())>art.getStock()){
            JOptionPane opt=new JOptionPane();
            opt.showMessageDialog(null,"Quantite insuffisante l'article ne peut pas etre ajoute","Information",JOptionPane.INFORMATION_MESSAGE);
        }
        if(Integer.parseInt(Qte.getValue().toString())<=0){
            JOptionPane opt=new JOptionPane();
            opt.showMessageDialog(null,"Quantite nulle ou negative.\n l'article ne peut pas etre ajoute","Information",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            boolean statut=false;
            if(art.getStock()>=Integer.parseInt(Qte.getValue().toString())){
                for(Article art1:panier.getContenu()){
                    if(art1.getIdart()==art.getIdart())
                        statut=true;
                }
                if(statut){
                    JOptionPane opt=new JOptionPane();
                    opt.showMessageDialog(null,"L'article a ete deja ajoute","Information",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    art.setStock(art.getStock()-Integer.parseInt(Qte.getValue().toString()));
                    panier.ajouter(art);
                    panier.getQte().add(Integer.parseInt(Qte.getValue().toString()));
                    if(panier.getContenu().isEmpty())
                        jButton_val.setVisible(false);
                    else
                        jButton_val.setVisible(true);
                }
            }
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setjButton_val(JButton jButton_val) {
        this.jButton_val = jButton_val;
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

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }
    
}
