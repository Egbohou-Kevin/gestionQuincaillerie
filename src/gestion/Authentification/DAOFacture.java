/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.Authentification;

import gestion.Vente.Facture;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author VEK
 */
public class DAOFacture extends DAO<Facture>{
    public DAOFacture(Connection con){
        super(con);
    }

    @Override
    public Facture rechercher(int id) {
        Facture fac=null;
        try{
            Statement requete=con.createStatement();
            ResultSet resultat= requete.executeQuery("SELECT * FROM facture where idfact= "+id);
            if(resultat.first()){
                fac= new Facture(id,new DAOVente(con).rechercher(resultat.getInt("idvente")));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return fac;
    }

    @Override
    public Boolean rechercherob(Facture obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean creer(Facture fac) {
        int statut=0;
        try{
            Statement requete=con.createStatement();
            statut = requete.executeUpdate("INSERT INTO facture(desfact,datefact,idgest,idvente) VALUES( " + "\""+ fac.getDesfact()+"\" ,"+ 
                    "\""+ fac.getDatefact()+"\" ,"+fac.getGestfac().getIdgest()+","+fac.getVentefact().getIdvente()+")");
        }
        catch(SQLException e){
            System.out.println(e);
        }
        if(statut==1)
            return true;
        return false;
    }

    @Override
    public Boolean modifier(Facture fac) {
        int statut=0;
        try{
            Statement requete=con.createStatement();
            statut = requete.executeUpdate("UPDATE facture SET desfact=" + "\""+ fac.getDesfact()+"\" ,"+"datefact="+
                    "\""+ fac.getDatefact()+"\" ,"+"idgest="+fac.getGestfac().getIdgest()+","+"idvente="+fac.getVentefact().getIdvente()+" WHERE idfact="+fac.getIdfact());
        }
        catch(SQLException e){
            System.out.println(e);
        }
        if(statut==1)
            return true;
        return false;
    }

    @Override
    public Boolean supprimer(Facture fac) {
       int statut=0;
       try{
           Statement requete=con.createStatement();
           statut=requete.executeUpdate("DELETE FROM facture WHERE idfact="+fac.getIdfact());
       }
       catch(SQLException e){
           System.out.println(e);
       }
       if(statut==1)
            return true;
       return false;
    }

    @Override
    public ArrayList<Facture> recuperer() {
        ArrayList<Facture> tab=new ArrayList<Facture>();
        try{
            Statement requete=con.createStatement();
            ResultSet resultat= requete.executeQuery("SELECT * FROM facture  ");
            while(resultat.next()){
                tab.add(new Facture(resultat.getInt("idfact"),new DAOVente(con).rechercher(resultat.getInt("idvente"))));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return tab;
    }
}
