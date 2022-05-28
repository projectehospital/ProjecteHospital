/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.business.entities;

/**
 *
 * @author Oussama
 */
public class PlantillaGuardia {
    
    private long id , places;
    private Unitat unitat;
    private Categoria categoria;
    private Torn torn;
    
    public PlantillaGuardia(long id , Unitat unitat , Categoria categoria , Torn torn , long places) {
    
    this.id = id;
    this.unitat = unitat;
    this.categoria = categoria;
    this.torn = torn;
    this.places = places;
    
    
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlaces() {
        return places;
    }

    public void setPlaces(long places) {
        this.places = places;
    }

    public Unitat getUnitat() {
        return unitat;
    }

    public void setUnitat(Unitat unitat) {
        this.unitat = unitat;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Torn getTorn() {
        return torn;
    }

    public void setTorn(Torn torn) {
        this.torn = torn;
    }
    
    
    
    
    
}
