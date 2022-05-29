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
    
    private long id;
    private String tipusUnitat;
    
    
    public Unitat() {
    }
    

    public Unitat(long id, String tipusUnitat) {
        this.id = id;
        this.tipusUnitat = tipusUnitat;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
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
