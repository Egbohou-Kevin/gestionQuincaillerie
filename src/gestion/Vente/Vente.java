/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.Vente;

import gestion.Article.Article;
import gestion.Authentification.Gestionnaire;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author VEK
 */
public class Vente {
    private int Idvente;
    private String Desvente;
    private float Montantvente;
    private Date Datevente;
    private Gestionnaire gest;
    private ArrayList<Article> Articlevente;
    private ArrayList<Integer> Qtevendu;

    public Vente() {
    }

    public Vente(int Idvente, String Desvente, float Montantvente, Date Datevente, Gestionnaire gest, ArrayList<Article> Articlevente,ArrayList<Integer> qte) {
        this.Idvente = Idvente;
        this.Desvente = Desvente;
        this.Montantvente = Montantvente;
        this.Datevente = Datevente;
        this.gest = gest;
        this.Articlevente = Articlevente;
        Qtevendu=qte;
    }

    public Vente(int Idvente, String Desvente, float Montantvente, Date Datevente, Gestionnaire gest) {
        this.Idvente = Idvente;
        this.Desvente = Desvente;
        this.Montantvente = Montantvente;
        this.Datevente = Datevente;
        this.gest = gest;
    }

    public Vente(String Desvente, float Montantvente, Date Datevente, Gestionnaire gest, ArrayList<Article> Articlevente, ArrayList<Integer> Qtevendu) {
        this.Desvente = Desvente;
        this.Montantvente = Montantvente;
        this.Datevente = Datevente;
        this.gest = gest;
        this.Articlevente = Articlevente;
        this.Qtevendu = Qtevendu;
    }

    public String getDesvente() {
        return Desvente;
    }

    public float getMontantvente() {
        return Montantvente;
    }

    public Date getDatevente() {
        return Datevente;
    }

    public int getIdvente() {
        return Idvente;
    }

    public Gestionnaire getGest() {
        return gest;
    }

    public ArrayList<Article> getArticlevente() {
        return Articlevente;
    }

    public ArrayList<Integer> getQtevendu() {
        return Qtevendu;
    }

    public void setDesvente(String Desvente) {
        this.Desvente = Desvente;
    }

    public void setMontantvente(float Montantvente) {
        this.Montantvente = Montantvente;
    }

    public void setDatevente(Date Datevente) {
        this.Datevente = Datevente;
    }

    public void setArticlevente(ArrayList<Article> Articlevente) {
        this.Articlevente = Articlevente;
    }

    public void setIdvente(int Idvente) {
        this.Idvente = Idvente;
    }

    public void setGest(Gestionnaire gest) {
        this.gest = gest;
    }

    public void setQtevendu(ArrayList<Integer> Qtevendu) {
        this.Qtevendu = Qtevendu;
    }
}
