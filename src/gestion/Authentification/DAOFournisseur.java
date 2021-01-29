/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.Authentification;

import gestion.Article.Fournisseur;
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
public class DAOFournisseur extends DAO<Fournisseur> {
    public DAOFournisseur(Connection con){
        super(con);
    }

    @Override
    public Fournisseur rechercher(int id) {
        Fournisseur frs=null;
        try{
            Statement requete=con.createStatement();
            ResultSet resultat= requete.executeQuery("SELECT * FROM Fournisseur where idfrs= "+id);
            if(resultat.first()){
                frs= new Fournisseur(id,resultat.getString("nomfrs"),resultat.getString("adressefrs"),resultat.getString("mailfrs"),resultat.getString("telfrs"));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return frs;
    }

    @Override
    public Boolean rechercherob(Fournisseur obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean creer(Fournisseur frs) {
        int statut=0;
        try{
            Statement requete=con.createStatement();
            statut = requete.executeUpdate("INSERT INTO fournisseur(nomfrs,mailfrs,telfrs,adressefrs) VALUES( " + "\""+ frs.getNomfrs()+"\" ,"+ 
                    "\""+ frs.getMailfrs()+"\" ,"+"\""+frs.getTelfrs()+"\" ,"
            +"\""+frs.getAdressfrs()+"\")");
        }
        catch(Exception e){
            System.out.println(e);
        }
        if(statut==1)
            return true;
        return false;
    }

    @Override
    public Boolean modifier(Fournisseur frs) {
        int statut=0;
        try{
            Statement requete=con.createStatement();
            statut = requete.executeUpdate("UPDATE fournisseur SET nomfrs= " + "\""+ frs.getNomfrs()+"\" ,"
                    +"mailfrs="+ "\""+ frs.getMailfrs()+"\" ,"+ "telfrs="+"\""+frs.getTelfrs()+"\" ,"+"adressefrs="
            +"\""+frs.getAdressfrs()+"\" WHERE idfrs="+frs.getIdfrs());
        }
        catch(Exception e){
            System.out.println(e);
        }
        if(statut==1)
            return true;
        return false;
    }

    @Override
    public Boolean supprimer(Fournisseur frs) {
       int statut=0;
       try{
           Statement requete=con.createStatement();
           statut=requete.executeUpdate("DELETE FROM fournisseur WHERE idfrs="+frs.getIdfrs());
       }
       catch(Exception e){
            JOptionPane opt=new JOptionPane();
            opt.showMessageDialog(null,"Le fournisseur ne  peut pas etre supprimé car il continue de fournir certains articles.\nVeuillez svp changer de fournisseur à ces articles.","Information",JOptionPane.INFORMATION_MESSAGE);
       }
       if(statut==1)
            return true;
       return false;
    }

    @Override
    public ArrayList<Fournisseur> recuperer() {
        ArrayList<Fournisseur> tab=new ArrayList<Fournisseur>();
        try{
            Statement requete=con.createStatement();
            ResultSet resultat= requete.executeQuery("SELECT * FROM fournisseur  ");
            while(resultat.next()){
                tab.add(new Fournisseur(resultat.getInt("idfrs"),resultat.getString("nomfrs"),resultat.getString("adressefrs"),resultat.getString("mailfrs"),resultat.getString("telfrs")));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return tab;
    }
    
}
