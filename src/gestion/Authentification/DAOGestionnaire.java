/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.Authentification;

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
public class DAOGestionnaire extends DAO<Gestionnaire> {
    public DAOGestionnaire(Connection con){
        super(con);
    }

    @Override
    public Gestionnaire rechercher(int id) {
        Gestionnaire gest=new Gestionnaire();
        try{
            Statement requete=con.createStatement();
            ResultSet resultat= requete.executeQuery("SELECT * FROM Gestionnaire where idgest= "+id);
            if(resultat.first()){
                gest= new Gestionnaire(id,resultat.getString("nomgest"),resultat.getString("prenomgest"),resultat.getString("sexegest"),resultat.getString("adressegest"),resultat.getString("telgest"),resultat.getString("username"),resultat.getString("password"));
            }
            requete.close();
            resultat.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return gest;
    }
    
    public Boolean rechercherob(Gestionnaire gest){
        Boolean statut=false;
        try{
            Statement requete=con.createStatement();
            ResultSet resultat= requete.executeQuery("SELECT * FROM gestionnaire where username= "+"\""+gest.getUsername()+"\" AND password="+"\""+gest.getPassword()+"\"");
            if(resultat.first())
                statut=true;
        }
        catch(Exception e){
        
        }
        return statut;
    }
    
    @Override
    public Boolean creer(Gestionnaire gest) {
        int statut=0;
        try{
            Statement requete=con.createStatement();
            statut = requete.executeUpdate("INSERT INTO gestionnaire(nomgest,prenomgest,sexegest,adressegest,telgest,username,password) VALUES( " + "\""+ gest.getNomgest()+"\" ,"+ 
                    "\""+ gest.getPrenomgest()+"\" ,"+"\""+ gest.getSexe()+"\" ,"+"\""+gest.getAdressegest()+"\" ,"
            +"\""+gest.getTelgest()+"\" ,"+"\""+gest.getUsername()+"\" ,"+"\""+gest.getPassword()+"\")");
        }
        catch(Exception e){
            System.out.println(e);
        }
        if(statut==1)
            return true;
        return false;
    }

    @Override
    public Boolean modifier(Gestionnaire gest) {
       int statut=0;
        try{
            Statement requete=con.createStatement();
            statut = requete.executeUpdate("UPDATE gestionnaire SET nomgest= " + "\""+ gest.getNomgest()+"\" ,"+"prenomgest= "+
                    "\""+ gest.getPrenomgest()+"\" ,"+"sexegest= "+"\""+ gest.getSexe()+"\" ,"+ "adressegest= "+ "\""+gest.getAdressegest()+"\" ,"
            + "telgest= "+ "\""+gest.getTelgest()+"\" ,"+ "username= "+ "\""+gest.getUsername()+"\" ,"+ "password ="+ "\""+gest.getPassword()+"\"" + " WHERE idgest="+gest.getIdgest());
        }
        catch(Exception e){
            System.out.println(e);
        }
        if(statut==1)
            return true;
        return false;
    }

    @Override
    public Boolean supprimer(Gestionnaire gest) {
       int statut=0;
       try{
           Statement requete=con.createStatement();
           statut=requete.executeUpdate("DELETE FROM gestionnaire WHERE idgest="+gest.getIdgest());
       }
       catch(Exception e){
            JOptionPane opt=new JOptionPane();
            opt.showMessageDialog(null,"Le gestionnaire ne  peut pas etre supprimé car il est toujours lié à certaines ventes.","Information",JOptionPane.INFORMATION_MESSAGE);
       }
       if(statut==1)
            return true;
       return false;
    }

    @Override
    public ArrayList<Gestionnaire> recuperer() {
        ArrayList<Gestionnaire> tab=new ArrayList<Gestionnaire>();
        try{
            Statement requete=con.createStatement();
            ResultSet resultat= requete.executeQuery("SELECT * FROM gestionnaire  ");
            while(resultat.next()){
                tab.add(new Gestionnaire(resultat.getInt("idgest"),resultat.getString("nomgest"),resultat.getString("prenomgest"),resultat.getString("sexegest"),resultat.getString("adressegest"),resultat.getString("telgest"),resultat.getString("username"),resultat.getString("password")));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return tab;
    }
    
}
