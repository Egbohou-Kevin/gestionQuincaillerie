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
public class Gestionnaire {
    private int Idgest=0;
    private String Nomgest="";
    private String Prenomgest="";
    private String Sexe="";
    private String Adressegest="";
    private String Telgest="";
    private String Username="gest";
    private String Password="gest";

    public Gestionnaire(String user,String pass) {
        Username=user;
        Password=pass;
    }

    public Gestionnaire() {
    }

    public Gestionnaire(int Idgest, String Nomgest, String Prenomgest, String Sexe, String Adressegest, String Telgest, String Username, String Password) {
        this.Idgest = Idgest;
        this.Nomgest = Nomgest;
        this.Prenomgest = Prenomgest;
        this.Sexe = Sexe;
        this.Adressegest = Adressegest;
        this.Telgest = Telgest;
        this.Username = Username;
        this.Password = Password;
    }
    
    public Gestionnaire(String Nomgest, String Prenomgest, String Sexe, String Adressegest, String Telgest) {
        this.Nomgest = Nomgest;
        this.Prenomgest = Prenomgest;
        this.Sexe = Sexe;
        this.Adressegest = Adressegest;
        this.Telgest = Telgest;
        this.Username = Username;
        this.Password = Password;
    }
    public Gestionnaire(int Idgest, String Nomgest, String Prenomgest, String Sexe, String Adressegest, String Telgest) {
        this.Idgest = Idgest;
        this.Nomgest = Nomgest;
        this.Prenomgest = Prenomgest;
        this.Sexe = Sexe;
        this.Adressegest = Adressegest;
        this.Telgest = Telgest;
        this.Username = Username;
        this.Password = Password;
    }

    public void setIdgest(int Idgest) {
        this.Idgest = Idgest;
    }
    

    public void setNomgest(String Nomgest) {
        this.Nomgest = Nomgest;
    }

    public void setPrenomgest(String Prenomgest) {
        this.Prenomgest = Prenomgest;
    }

    public void setSexe(String Sexe) {
        this.Sexe = Sexe;
    }

    public void setAdressegest(String Adressegest) {
        this.Adressegest = Adressegest;
    }

    public void setTelgest(String Telgest) {
        this.Telgest = Telgest;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getIdgest() {
        return Idgest;
    }

    public String getNomgest() {
        return Nomgest;
    }

    public String getPrenomgest() {
        return Prenomgest;
    }

    public String getSexe() {
        return Sexe;
    }

    public String getAdressegest() {
        return Adressegest;
    }

    public String getTelgest() {
        return Telgest;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    @Override
    public String toString() {
        return this.getNomgest(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
