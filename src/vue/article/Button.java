/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.article;

import javax.swing.JButton;

/**
 *
 * @author VEK
 */
public class Button extends JButton{
    public Button(String txt){
        super(txt);
    }
    public Button(){
        
    }

    @Override
    public String toString() {
        return this.getText();
    }
}
