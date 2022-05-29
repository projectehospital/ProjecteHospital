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
    private String unitat;
    private String categoria;
    private String torn;
    
    public PlantillaGuardia() {}
    
    // es una plantilla per el que nomes necessitem saber el tipus i no l'objecte per aixo es pasen Strings
    
    public PlantillaGuardia(long id , String unitat , String categoria , String torn , long places) {
    
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

    public long getPlacesPlantilla() {
        return places;
    }

    public void setPlacesPlantilla(long places) {
        this.places = places;
    }

    public String getNomUnitatPlantilla() {
        return unitat;
    }

    public void setNomUnitatPlantilla(String unitat) {
        this.unitat = unitat;
    }

    public String getNomCategoriaPlantilla() {
        return categoria;
    }

    public void setNomCategoriaPlantilla(String categoria) {
        this.categoria = categoria;
    }

    public String getNomTornPlantilla() {
        return torn;
    }

    public void setNomTornPlantilla(String torn) {
        this.torn = torn;
    }
    
    
    
    
    
}
