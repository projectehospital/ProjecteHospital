/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.business.entities;

/**
 *
 * @author Victo
 */
public class Torn {

    
    private String tipusTorn;
    
    public Torn(String tipusTorn) {
        this.tipusTorn = tipusTorn;
    }

    public Torn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getTipusTorn() {
        return tipusTorn;
    }

    public void setTipusTorn(String tipusTorn) {
        this.tipusTorn = tipusTorn;
    }
}
