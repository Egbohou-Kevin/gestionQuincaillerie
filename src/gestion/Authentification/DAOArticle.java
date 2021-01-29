/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.Authentification;

import gestion.Article.Article;
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
public class DAOArticle extends DAO<Article>{
    
    public DAOArticle(Connection con){
        super(con);
    }
    @Override
    public Article rechercher(int id) {
        Article art=null;
        try{
            Statement requete=con.createStatement();
            ResultSet resultat= requete.executeQuery("SELECT * FROM article where idart= "+id);
            if(resultat.first()){
                art= new Article(id,resultat.getString("nomart"),resultat.getString("comart"),new DAOCategorie(con).rechercher(resultat.getInt("idcat")),resultat.getFloat("prixart"),resultat.getInt("stock"),resultat.getDate("dateart"),new DAOFournisseur(con).rechercher(resultat.getInt("idfrs")));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return art;
    }

    @Override
    public Boolean rechercherob(Article obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean creer(Article art) {
        int statut=0;
        try{
            Statement requete=con.createStatement();
            statut = requete.executeUpdate("INSERT INTO article(nomart,comart,stock,dateart,prixart,idcat,idfrs) VALUES( " + "\""+ art.getNomart()+"\" ,"+ 
                    "\""+ art.getComart()+"\" ,"+ art.getStock()+","+"\""+art.getDateart()+"\" ,"
            +art.getPrixart()+","+art.getCatart().getIdcat()+","+art.getFrs().getIdfrs()+")");
        }
        catch(Exception e){
            System.out.println(e);
        }
        if(statut==1)
            return true;
        return false;
    }

    @Override
    public Boolean modifier(Article art) {
        int statut=0;
        try{
            Statement requete=con.createStatement();
            statut = requete.executeUpdate("UPDATE article SET nomart=" + "\""+ art.getNomart()+"\" ,"+"comart="+
                    "\""+ art.getComart()+"\" ,"+ "stock="+ art.getStock()+","+ "dateart="+ "\""+art.getDateart()+"\" ,"
            +"prixart="+art.getPrixart()+","+"idcat="+art.getCatart().getIdcat()+","+"idfrs="+art.getFrs().getIdfrs()+" WHERE idart="+art.getIdart());
        }
        catch(Exception e){
            System.out.println(e);
        }
        if(statut==1)
            return true;
        return false;
    }
    @Override
    public Boolean supprimer(Article art) {
        int statut=0;
       try{
           Statement requete=con.createStatement();
           statut=requete.executeUpdate("DELETE FROM article WHERE idart="+art.getIdart());
       }
       catch(SQLException e){
            JOptionPane opt=new JOptionPane();
            opt.showMessageDialog(null,"L'article ne  peut pas etre supprimé car il fait partie de certaines ventes.\nSa suppression doit etre précédée par la suppression des ventes auquelles il appartient.","Information",JOptionPane.INFORMATION_MESSAGE);
       }
       if(statut==1)
            return true;
       return false;
    }

    @Override
    public ArrayList<Article> recuperer() {
        ArrayList<Article> tab=new ArrayList<Article>();
        try{
            Statement requete=con.createStatement();
            ResultSet resultat= requete.executeQuery("SELECT * FROM article ORDER BY nomart ASC");
            while(resultat.next()){
                tab.add(new Article(resultat.getInt("idart"),resultat.getString("nomart"),resultat.getString("comart"),new DAOCategorie(con).rechercher(resultat.getInt("idcat")),resultat.getFloat("prixart"),resultat.getInt("stock"),resultat.getDate("dateart"),new DAOFournisseur(con).rechercher(resultat.getInt("idfrs"))));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return tab;
    }
    
}
