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

/**
 *
 * @author VEK
 */
public class DAOAdmin extends DAO<Admin> {
    
    public DAOAdmin(Connection con){
        super(con);
    }

    @Override
    public Admin rechercher(int id) {
        Admin admin=null;
        try{
            Statement requete=con.createStatement();
            ResultSet resultat= requete.executeQuery("SELECT * FROM Admin where idadmin= "+id);
            if(resultat.first()){
                admin= new Admin(id,resultat.getString("username"),resultat.getString("password"));
            }
            requete.close();
            resultat.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return admin;
    }
    
    public Boolean rechercherob(Admin admin){
        Boolean statut=false;
        try{
            Statement requete=con.createStatement();
            ResultSet resultat= requete.executeQuery("SELECT * FROM admin where username= "+"\""+admin.getUsername()+"\" AND password="+"\""+admin.getPassword()+"\"");
            if(resultat.first())
                statut=true;
        }
        catch(Exception e){
        
        }
        return statut;
    }
    @Override
    public Boolean creer(Admin admin) {
        return false;
    }

    @Override
    public Boolean modifier(Admin admin) {
        boolean statut=true;
        try{
            Statement requete=con.createStatement();
            requete.executeUpdate("UPDATE admin SET username= "+"\""+admin.getUsername()+"\","+"password="+"\""+admin.getPassword()+"\" WHERE idadmin="+admin.getIdadmin());
        }
        catch(Exception e){
            statut=false;
        }
        return statut;
    }

    @Override
    public Boolean supprimer(Admin admin) {
        return false;
    }

    @Override
    public ArrayList<Admin> recuperer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
