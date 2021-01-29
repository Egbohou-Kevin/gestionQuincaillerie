/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.horloge;

import java.util.ArrayList;

/**
 *
 * @author VEK
 */
public class Horloge implements HorlogeObservable {
    private Action ac;
    private ArrayList<HorlogeObserver> observateur;
    private int minute;
    private int heure;
    private int seconde;

    public Horloge() {
        ac=new Action(this);
        observateur=new ArrayList<HorlogeObserver>();
        ac=new Action(this);
        this.minute = minute;
        this.heure = heure;
        this.seconde = seconde;
    }
    
    public void demarrer(){
        ac.start();
    }
    
    public int getMinute() {
        return minute;
    }

    public int getHeure() {
        return heure;
    }

    public int getSeconde() {
        return seconde;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public void setSeconde(int seconde) {
        this.seconde = seconde;
    }

    @Override
    public void addObserver(HorlogeObserver ob) {
        observateur.add(ob);
    }

    @Override
    public void removeObserver(HorlogeObserver ob) {
        observateur.remove(ob);
    }

    public void updateObserver() {
        for(HorlogeObserver obs:observateur){
            obs.update(this);
        }
    }
    
    
}
