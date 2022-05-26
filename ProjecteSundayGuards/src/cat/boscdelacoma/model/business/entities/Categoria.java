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
    private String tipusCategoria;
    private String descripcio;

    public Categoria() {
    }

    public Categoria(String tipusCategoria) {
        this.tipusCategoria = tipusCategoria;
    }

    public String getTipusCategoria() {
        return tipusCategoria;
    }

    public void setTipusCategoria(String tipusCategoria) {
        this.tipusCategoria = tipusCategoria;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }
    
}
