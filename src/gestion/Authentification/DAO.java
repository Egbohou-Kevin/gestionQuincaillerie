/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.Authentification;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author VEK
 */
public abstract class DAO<T> {
    protected Connection con=null;
    
    public DAO(Connection con){
        this.con=con;
    }
    public abstract T rechercher(int id );
    
    public abstract Boolean rechercherob(T obj);
    
    public abstract Boolean creer(T obj);
    
    public abstract Boolean modifier(T obj);
    
    public abstract Boolean supprimer(T obj);
    
    public abstract ArrayList<T> recuperer();
}
