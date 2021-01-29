/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.horloge;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author VEK
 */
public class Action extends Thread {
    private Horloge horloge;
    public Action(Horloge h) {
        horloge=h;
    }
    @Override
    public void run() {
        while(true){
            Calendar cal = new GregorianCalendar();
            horloge.setHeure(cal.get(Calendar.HOUR));
            horloge.setMinute(cal.get(Calendar.MINUTE));
            horloge.setSeconde(cal.get(Calendar.SECOND));
            horloge.updateObserver();
            try{
                Thread.sleep(1000);
            }
            catch(Exception e){
                
            }
        }
    }
    
}
