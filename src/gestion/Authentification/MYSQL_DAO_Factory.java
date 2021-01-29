/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.Authentification;

import java.sql.Connection;

/**
 *
 * @author VEK
 */
public class MYSQL_DAO_Factory extends AbstractDAO{
    protected static final Connection con=Connexion.getConnection();

    @Override
    public DAO getArticleDAO() {
        return new DAOArticle(con);
    }

    @Override
    public DAO getGestionnaireDAO() {
        return new DAOGestionnaire(con);
    }

    @Override
    public DAO getFournisseurDAO() {
        return new DAOFournisseur(con);
    }

    @Override
    public DAO getCategorieDAO() {
        return new DAOCategorie(con);
    }

    @Override
    public DAO getFactureDAO() {
       return new DAOFacture(con);
    }

    @Override
    public DAO getVenteDAO() {
        return new DAOVente(con);
    }

    @Override
    public DAO getAdminDAO() {
        return new DAOAdmin(con);
    }
   
}
