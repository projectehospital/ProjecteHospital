/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.business.entities;

/**
 *
 * @author Victo
 */
public class Unitat {
    
    private String tipusUnitat;

    public Unitat(String tipusUnitat) {
        this.tipusUnitat = tipusUnitat;
    }
    
    /**
     * @return the tipusUnitat
     */
    public String getTipusUnitat() {
        return tipusUnitat;
    }

    /**
     * @param tipusUnitat the tipusUnitat to set
     */
    public void setTipusUnitat(String tipusUnitat) {
        this.tipusUnitat = tipusUnitat;
    }
}
