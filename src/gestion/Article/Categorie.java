/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.Article;

/**
 *
 * @author VEK
 */
public class Categorie {
    private int Idcat;
    private String Nomcat;
    private String Comcat;

    public Categorie() {
    }

    public Categorie(String Nomcat, String Comcat) {
        this.Nomcat = Nomcat;
        this.Comcat = Comcat;
    }

    public Categorie(int Idcat, String Nomcat, String Comcat) {
        this.Idcat = Idcat;
        this.Nomcat = Nomcat;
        this.Comcat = Comcat;
    }

    public void setComcat(String Comcat) {
        this.Comcat = Comcat;
    }

    public void setNomcat(String Nomcat) {
        this.Nomcat = Nomcat;
    }

    public String getNomcat() {
        return Nomcat;
    }

    public int getIdcat() {
        return Idcat;
    }

    public String getComcat() {
        return Comcat;
    }

    public void setIdcat(int Idcat) {
        this.Idcat = Idcat;
    }

    @Override
    public String toString() {
        return this.getNomcat(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
