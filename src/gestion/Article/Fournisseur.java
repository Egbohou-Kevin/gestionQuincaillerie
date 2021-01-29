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
public class Fournisseur {
    private int Idfrs=0;
    private String Nomfrs="";
    private String Adressfrs="";
    private String Mailfrs="";
    private String Telfrs="";

    public Fournisseur(int id,String nom,String adr,String mail,String tel) {
        Idfrs=id;
        Nomfrs=nom;
        Adressfrs=adr;
        Mailfrs=mail;
        Telfrs=tel;
    }

    public Fournisseur(String nom,String adr,String mail,String tel) {
        Nomfrs=nom;
        Adressfrs=adr;
        Mailfrs=mail;
        Telfrs=tel;
    }

    public void setIdfrs(int Idfrs) {
        this.Idfrs = Idfrs;
    }

    public void setNomfrs(String Nomfrs) {
        this.Nomfrs = Nomfrs;
    }

    public void setAdressfrs(String Adressfrs) {
        this.Adressfrs = Adressfrs;
    }

    public void setMailfrs(String Mailfrs) {
        this.Mailfrs = Mailfrs;
    }

    public void setTelfrs(String Telfrs) {
        this.Telfrs = Telfrs;
    }

    public int getIdfrs() {
        return Idfrs;
    }

    public String getNomfrs() {
        return Nomfrs;
    }

    public String getAdressfrs() {
        return Adressfrs;
    }

    public String getMailfrs() {
        return Mailfrs;
    }

    public String getTelfrs() {
        return Telfrs;
    }

    @Override
    public String toString() {
        return this.getNomfrs(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
