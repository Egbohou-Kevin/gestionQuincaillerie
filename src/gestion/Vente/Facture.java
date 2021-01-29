/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.Vente;

import gestion.Authentification.Gestionnaire;
import java.util.Date;

/**
 *
 * @author VEK
 */
public class Facture {
    private int Idfact;
    private String Desfact;
    private Date Datefact;
    private Gestionnaire Gestfac;
    private Vente Ventefact;

    public Facture() {
    }

    public Facture(int Idfact,Vente Ventefact) {
        this.Idfact = Idfact;
        this.Desfact = Ventefact.getDesvente();
        this.Datefact = Ventefact.getDatevente();
        this.Gestfac = Ventefact.getGest();
        this.Ventefact = Ventefact;
    }

    public Facture(Vente Ventefact) {
        this.Desfact = Ventefact.getDesvente();
        this.Datefact = Ventefact.getDatevente();
        this.Gestfac =Ventefact.getGest();
        this.Ventefact = Ventefact;
    }

    public int getIdfact() {
        return Idfact;
    }

    public String getDesfact() {
        return Desfact;
    }

    public Date getDatefact() {
        return Datefact;
    }

    public Gestionnaire getGestfac() {
        return Gestfac;
    }

    public Vente getVentefact() {
        return Ventefact;
    }

    public void setIdfact(int Idfact) {
        this.Idfact = Idfact;
    }

    public void setDesfact(String Desfact) {
        this.Desfact = Desfact;
    }

    public void setDatefact(Date Datefact) {
        this.Datefact = Datefact;
    }

    public void setGestfac(Gestionnaire Gestfac) {
        this.Gestfac = Gestfac;
    }

    public void setVentefact(Vente Ventefact) {
        this.Ventefact = Ventefact;
    }
}
