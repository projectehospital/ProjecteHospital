/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.business.entities;

/**
 *
 * @author osama
 */
public class Rol {
    
    private long id;
    private String tipusRol;
    
    public Rol(){
    
    }
    
        
    public Rol (long id, String tipusRol){
        this.id = id;
        this.tipusRol = tipusRol;
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

    public String getTipusRol() {
        return tipusRol;
    }

    public void setTipusRol(String tipusRol) {
        this.tipusRol = tipusRol;
    }
    
}
