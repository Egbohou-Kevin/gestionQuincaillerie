/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.Article;

import java.sql.Date;

/**
 *
 * @author VEK
 */
public class Article {
    private int Idart;
    private String Nomart;
    private String Comart;
    private Categorie Catart;
    private float Prixart;
    private int Stock;
    private Date Dateart=new Date(0,0,0);
    private Fournisseur Frs;

    public Article() {
    }

    public Article(int Idart, String Nomart, String Comart, Categorie Catart, float Prixart, int Stock, Date Dateart, Fournisseur Frs) {
        this.Idart = Idart;
        this.Nomart = Nomart;
        this.Comart = Comart;
        this.Catart = Catart;
        this.Prixart = Prixart;
        this.Stock = Stock;
        this.Dateart = Dateart;
        this.Frs = Frs;
    }
     public Article(int Idart, String Nomart, String Comart, Categorie Catart, float Prixart, int Stock, Fournisseur Frs) {
        this.Idart = Idart;
        this.Nomart = Nomart;
        this.Comart = Comart;
        this.Catart = Catart;
        this.Prixart = Prixart;
        this.Stock = Stock;
        this.Frs = Frs;
    }

    public Article(String Nomart, String Comart, Categorie Catart, float Prixart, int Stock, Date Dateart, Fournisseur Frs) {
        this.Nomart = Nomart;
        this.Comart = Comart;
        this.Catart = Catart;
        this.Prixart = Prixart;
        this.Stock = Stock;
        this.Dateart = Dateart;
        this.Frs = Frs;
    }
    public Article(String Nomart, String Comart, Categorie Catart, float Prixart, int Stock, Fournisseur Frs) {
        this.Nomart = Nomart;
        this.Comart = Comart;
        this.Catart = Catart;
        this.Prixart = Prixart;
        this.Stock = Stock;
        this.Frs = Frs;
    }

    public String getComart() {
        return Comart;
    }

    public Date getDateart() {
        return Dateart;
    }


    public int getIdart() {
        return Idart;
    }

    

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }


    public String getNomart() {
        return Nomart;
    }

    public void setFrs(Fournisseur Frs) {
        this.Frs = Frs;
    }

    public Fournisseur getFrs() {
        return Frs;
    }

    public Categorie getCatart() {
        return Catart;
    }

    public float getPrixart() {
        return Prixart;
    }

    public void setNomart(String Nomart) {
        this.Nomart = Nomart;
    }

    public void setCatart(Categorie Catart) {
        this.Catart = Catart;
    }

    public void setPrixart(float Prixart) {
        this.Prixart = Prixart;
    }

    public void setIdart(int Idart) {
        this.Idart = Idart;
    }

    public void setComart(String Comart) {
        this.Comart = Comart;
    }

    public void setDateart(Date Dateart) {
        this.Dateart = Dateart;
    }

    @Override
    public String toString() {
        return Nomart; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
