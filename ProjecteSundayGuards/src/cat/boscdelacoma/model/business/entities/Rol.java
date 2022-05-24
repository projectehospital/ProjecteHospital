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
    
    private String tipusRol;
    
        
    public Rol (String tipusRol){
    
        this.tipusRol = tipusRol;
    }

    public String getTipusRol() {
        return tipusRol;
    }

    public void setTipusRol(String tipusRol) {
        this.tipusRol = tipusRol;
    }
    
}
