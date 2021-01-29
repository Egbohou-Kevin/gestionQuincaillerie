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
public class Admin {
    private int Idadmin=0;
    private String Username;
    private String Password;

    public Admin(int id,String Username, String Password) {
        Idadmin=id;
        this.Username = Username;
        this.Password = Password;
    }

    public Admin(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
    }

    public Admin() {
    }

    public int getIdadmin() {
        return Idadmin;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setIdadmin(int Idadmin) {
        this.Idadmin = Idadmin;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
}
