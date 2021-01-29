/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.Authentification;

import gestion.Article.Article;
import gestion.Vente.Vente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author VEK
 */
public class DAOVente extends DAO<Vente> {
    public DAOVente(Connection con){
        super(con);
    }

    @Override
    public Vente rechercher(int id) {
        Vente vente=null;
        ArrayList<Article> articlevente=new ArrayList<Article>();
        ArrayList<Integer> qte=new ArrayList<Integer>();
        try{
            Statement requete=con.createStatement();
            Statement req=con.createStatement();
            ResultSet result=req.executeQuery("SELECT * FROM vente_article WHERE idvente= "+id);
            while(result.next()){
                articlevente.add(new DAOArticle(con).rechercher(result.getInt("idart")));
                qte.add(result.getInt("qtevendu"));
            }
            ResultSet resultat= requete.executeQuery("SELECT * FROM vente where idvente= "+id);
            if(resultat.first()){
                vente= new Vente(id,resultat.getString("desvente"),resultat.getFloat("montantvente"),resultat.getDate("datevente"),new DAOGestionnaire(con).rechercher(resultat.getInt("idgest")),articlevente,qte);
            }
            req.close();
            requete.close();
            result.close();
            resultat.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return vente;
    }

    @Override
    public Boolean rechercherob(Vente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean creer(Vente vente) {
        int statut=0;
        int qtev=0;
        int idvente=0;
        Article artv=null;
        try{
            Statement requete=con.createStatement();
            Statement req=con.createStatement();
            Statement re=con.createStatement();//pour recuperer le dernier id
            statut = requete.executeUpdate("INSERT INTO vente(desvente,montantvente,datevente,idgest) VALUES( " + "\""+ vente.getDesvente()+"\" ,"+ 
                     vente.getMontantvente()+","+"\""+vente.getDatevente()+"\","+vente.getGest().getIdgest()+")");
            ResultSet res=re.executeQuery("SELECT * FROM vente");
            res.last();
            idvente=res.getInt("idvente");
            for(int i=0;i<vente.getArticlevente().size();i++){
                qtev=vente.getQtevendu().get(i);
                artv=vente.getArticlevente().get(i);
                statut=req.executeUpdate("INSERT INTO vente_article(idvente,idart,qtevendu) VALUES( "+idvente+","+artv.getIdart()+","+qtev+")");
            }
            req.close();
            requete.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        if(statut==1)
            return true;
        return false;
    }

    @Override
    public Boolean modifier(Vente vente) {
        int statut=0;
        int i=0;
        int qtev=0;
        Article artv=null;
        try{
            Statement requete=con.createStatement();
            Statement req=con.createStatement();
            statut = requete.executeUpdate("UPDATE vente SET desvente=" + "\""+ vente.getDesvente()+"\" ,"+"montantvente="+ 
                     vente.getMontantvente()+","+"datevente="+"\""+vente.getDatevente()+"\","+"idgest="+vente.getGest().getIdgest()+" WHERE idvente="+vente.getIdvente());
            statut=req.executeUpdate("DELETE FROM vente_article WHERE idvente="+vente.getIdvente());//On supprime toutes les entrees pour en creer dautres
            /*for(i=0;i<vente.getQtevendu().size();i++){
                    qtev=vente.getQtevendu().get(i);
                    artv=vente.getArticlevente().get(i);
                    statut=req.executeUpdate("INSERT INTO vente_article VALUES( "+vente.getIdvente()+","+artv.getIdart()+","+qtev+")");
                }
            req.close();*/
            requete.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        if(statut==1)
            return true;
        return false;
    }

    @Override
    public Boolean supprimer(Vente vente) {
        int statut=0;
       try{
           Statement requete=con.createStatement();
           statut=requete.executeUpdate("DELETE FROM vente WHERE idvente="+vente.getIdvente());
           requete.close();
       }
       catch(Exception e){
           System.out.println(e);
       }
       if(statut==1)
            return true;
       return false;
    }

    @Override
    public ArrayList<Vente> recuperer() {
        AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
        DAO<Article> artdao=fac.getArticleDAO();
        ArrayList<Vente> tab=new ArrayList<Vente>();
        try{
            ArrayList<Article> arttab=null;
            ArrayList<Integer> qtetab=null;
            Statement requete=con.createStatement();
            Statement req=con.createStatement();
            ResultSet resultat= requete.executeQuery("SELECT * FROM vente  ");
            while(resultat.next()){
                ResultSet res=req.executeQuery("SELECT * FROM vente_article WHERE idvente="+resultat.getInt("idvente"));
                arttab=new ArrayList<Article>();
                qtetab=new ArrayList<Integer>();
                while(res.next()){
                    arttab.add(artdao.rechercher(res.getInt("idart")));
                    qtetab.add(res.getInt("qtevendu"));
                }
                //System.out.println(arttab.size());
                tab.add(new Vente(resultat.getInt("idvente"),resultat.getString("desvente"),resultat.getFloat("montantvente"),resultat.getDate("datevente"),new DAOGestionnaire(con).rechercher(resultat.getInt("idgest")),arttab,qtetab));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return tab;
    }
}
