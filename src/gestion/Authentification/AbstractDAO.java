/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.Authentification;

/**
 *
 * @author VEK
 */
public abstract class AbstractDAO {
    public static final int MYSQL_DAO_FACTORY=0;
    public static final int SQLSERVER_DAO_FACTORY=1;
    
    public abstract DAO getArticleDAO();
    
    public abstract DAO getGestionnaireDAO();
    
    public abstract DAO getFournisseurDAO();
    
    public abstract DAO getCategorieDAO();
    
    public abstract DAO getFactureDAO();
    
    public abstract DAO getVenteDAO();
    
    public abstract DAO getAdminDAO();
    
    public static AbstractDAO getFactory(int type){
        switch(type){
            case MYSQL_DAO_FACTORY :
                return new MYSQL_DAO_Factory();
            case SQLSERVER_DAO_FACTORY :
                return new SQLSERVER_DAO_Factory();
            default :
                return null;
        }
    }
    
}
