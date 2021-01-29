/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.Vente;

import gestion.Article.Article;
import java.util.ArrayList;

/**
 *
 * @author VEK
 */
public class Panier {
    private ArrayList<Article> Contenu=new ArrayList<Article>();
    private ArrayList<Integer> Qte=new ArrayList<Integer>();

    public Panier() {
    }

    public Panier(ArrayList<Article> Contenu) {
        this.Contenu = Contenu;
    }
    
    public void ajouter(Article art){
        Contenu.add(art);
    }
    
    public void supprimer(Article art){
        Contenu.remove(art);
    }
    
    public ArrayList<Article> getContenu() {
        return Contenu;
    }

    public ArrayList<Integer> getQte() {
        return Qte;
    }

    public void setContenu(ArrayList<Article> Contenu) {
        this.Contenu = Contenu;
    }

    public void setQte(ArrayList<Integer> Qte) {
        this.Qte = Qte;
    }
    
}
