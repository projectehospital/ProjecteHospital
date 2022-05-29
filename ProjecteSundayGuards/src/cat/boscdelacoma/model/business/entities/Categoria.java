/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.business.entities;

/**
 *
 * @author Victo
 */
public class Categoria {
    
    private long id;
    private String tipusCategoria;
    
      public Categoria() {
    }
    

    public Categoria(long id, String tipusCategoria) {
        this.id = id;
        this.tipusCategoria = tipusCategoria;
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

    public String getTipusCategoria() {
        return tipusCategoria;
    }

    public void setTipusCategoria(String tipusCategoria) {
        this.tipusCategoria = tipusCategoria;
    }
    
}
