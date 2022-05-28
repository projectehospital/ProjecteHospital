/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.business.entities;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Victo
 */
public class Guardia {
    
    private long id;
    private LocalDate dia;
    private long placesDisponibles;
    private ArrayList<Treballador> treballadors;
    private Torn torn;
    private Unitat unitat;
    private Categoria categoria;
    
    public Guardia(long id, LocalDate dia,Unitat unitat, Torn torn, Categoria categoria, long placesDisponibles) {
        this.id = id;
        this.dia = dia;
        this.unitat = unitat;
        this.torn = torn;
        this.categoria = categoria;
        this.placesDisponibles = placesDisponibles;
    }
    
    public Guardia( LocalDate dia,Unitat unitat, Torn torn, Categoria categoria, long placesDisponibles) {
        this.id = id;
        this.dia = dia;
        this.unitat = unitat;
        this.torn = torn;
        this.categoria = categoria;
        this.placesDisponibles = placesDisponibles;
    }
    
     public Guardia( LocalDate dia,Unitat unitat, Torn torn, Categoria categoria) {
        this.id = id;
        this.dia = dia;
        this.unitat = unitat;
        this.torn = torn;
        this.categoria = categoria;
        this.placesDisponibles = placesDisponibles;
    }

    public Guardia() {
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public long getPlacesDisponibles() {
        return placesDisponibles;
    }

    public void setPlacesDisponibles(short placesDisponibles) {
        this.placesDisponibles = placesDisponibles;
    }

    public ArrayList<Treballador> getTreballadors() {
        return treballadors;
    }

    public void setTreballadors(ArrayList<Treballador> treballadors) {
        this.treballadors = treballadors;
    }
    
    public void inscriureTreballador(Treballador t){
        treballadors.add(t);
    }
    
    public void eliminarTreballador(Treballador t){
        treballadors.remove(t);
    }

    public Torn getTorn() {
        return torn;
    }

    public void setTorn(Torn torn) {
        this.torn = torn;
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
}
