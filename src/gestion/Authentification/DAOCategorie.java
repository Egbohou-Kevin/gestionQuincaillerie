/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.Authentification;

import gestion.Article.Categorie;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author VEK
 */
public class DAOCategorie extends DAO<Categorie> {
    public DAOCategorie(Connection con){
        super(con);
    }

    @Override
    public Categorie rechercher(int id) {
        Categorie cat=null;
        try{
            Statement requete=con.createStatement();
            ResultSet resultat= requete.executeQuery("SELECT * FROM categorie where idcat= "+id);
            if(resultat.first()){
                cat= new Categorie(id,resultat.getString("nomcat"),resultat.getString("comcat"));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return cat;
    }

    @Override
    public Boolean rechercherob(Categorie obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean creer(Categorie cat) {
        int statut=0;
        try{
            Statement requete=con.createStatement();
            statut = requete.executeUpdate("INSERT INTO categorie(nomcat,comcat) VALUES("+"\""+cat.getNomcat()+"\" ,"+ 
                    "\""+ cat.getComcat()+"\")");
        }
        catch(Exception e){
            System.out.println(e);
        }
        if(statut==1)
            return true;
        return false;
    }

    @Override
    public Boolean modifier(Categorie cat) {
        int statut=0;
        try{
            Statement requete=con.createStatement();
            statut = requete.executeUpdate("UPDATE categorie SET nomcat=" + "\""+ cat.getNomcat()+"\" ,"+ "comcat=" +
                    "\""+ cat.getComcat()+"\" WHERE idcat="+cat.getIdcat());
        }
        catch(Exception e){
            System.out.println(e);
        }
        if(statut==1)
            return true;
        return false;
    }

    @Override
    public Boolean supprimer(Categorie cat) {
       int statut=0;
       try{
           Statement requete=con.createStatement();
           statut=requete.executeUpdate("DELETE FROM categorie WHERE idcat="+cat.getIdcat());
       }
       catch(Exception e){
           //System.out.println(e);
            JOptionPane opt=new JOptionPane();
            opt.showMessageDialog(null,"La categorie ne  peut pas etre supprimée car elle contient des articles.\n Veuillez svp changer de categorie à ces dernières.","Information",JOptionPane.INFORMATION_MESSAGE);
       }
       if(statut==1)
            return true;
       return false;
    }

    @Override
    public ArrayList<Categorie> recuperer() {
        ArrayList<Categorie> tab=new ArrayList<Categorie>();
        try{
            Statement requete=con.createStatement();
            ResultSet resultat= requete.executeQuery("SELECT * FROM categorie  ");
            while(resultat.next()){
                tab.add(new Categorie(resultat.getInt("idcat"),resultat.getString("nomcat"),resultat.getString("comcat")));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return tab;
    }
}
