/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.Article;

import gestion.Authentification.AbstractDAO;
import gestion.Authentification.DAO;
import java.util.ArrayList;

/**
 *
 * @author VEK
 */
public class Test {
    public static void main(String[] args){
        /*AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
        DAO<Article> artdao=fac.getArticleDAO();
        for(Article art:artdao.recuperer()){
            System.out.println(art.getNomart());
        }*/
        //DAO<Categorie> catdao=fac.getCategorieDAO();
        //DAO<Fournisseur> frsdao=fac.getFournisseurDAO();
        //DAO<Gestionnaire> gestdao=fac.getGestionnaireDAO();
        //DAO<Vente> ventedao=fac.getVenteDAO();
        //Vente vente=ventedao.rechercher(8);
        //Gestionnaire gest=gestdao.rechercher(1);
        /*Fournisseur frs=frsdao.rechercher(1);
        Categorie cat=catdao.rechercher(1);
        Article art=artdao.rechercher(2);
        art.setStock(30);
        /*ArrayList<Article> arart=new ArrayList<Article>();
        ArrayList<Integer> arqte=new ArrayList<Integer>();
        arart.add(art);
        arqte.add(5);*/
        //Vente vente=new Vente("hjkll",2000,new java.sql.Date(2020-1900,6-1,23),gest,arart,arqte);
        //Article art=new Article("pince","pince a sertille",cat,200,40,new java.sql.Date(2020-1900,8-1,24),frs);
       //System.out.println(gestdao.rechercherob(gest));
    }
}
