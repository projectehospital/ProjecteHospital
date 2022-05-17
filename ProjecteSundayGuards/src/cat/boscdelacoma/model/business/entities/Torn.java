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
    private String desc;
    
    public Torn(String tipusTorn) {
        this.tipusTorn = tipusTorn;
    }

    public String getTipusTorn() {
        return tipusTorn;
    }

    public void setTipusTorn(String tipusTorn) {
        this.tipusTorn = tipusTorn;
    }
    
    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
