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
    
    private LocalDate dia;
    private short placesDisponibles;
    private ArrayList<Treballador> treballador;
    private ArrayList<Torn> torn;
    private ArrayList<Unitat> unitat;

    public Guardia(LocalDate dia, short placesDisponibles, ArrayList<Treballador> treballador, ArrayList<Torn> torn, ArrayList<Unitat> unitat) {
        this.dia = dia;
        this.placesDisponibles = placesDisponibles;
        this.treballador = treballador;
        this.torn = torn;
        this.unitat = unitat;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }
    
    /**
     * @return the placesDisponibles
     */
    public short getPlacesDisponibles() {
        return placesDisponibles;
    }

    /**
     * @param placesDisponibles the placesDisponibles to set
     */
    public void setPlacesDisponibles(short placesDisponibles) {
        this.placesDisponibles = placesDisponibles;
    }

    public ArrayList<Treballador> getTreballador() {
        return treballador;
    }

    public void setTreballador(ArrayList<Treballador> treballador) {
        this.treballador = treballador;
    }

    public ArrayList<Torn> getTorn() {
        return torn;
    }

    public void setTorn(ArrayList<Torn> torn) {
        this.torn = torn;
    }

    public ArrayList<Unitat> getUnitat() {
        return unitat;
    }

    public void setUnitat(ArrayList<Unitat> unitat) {
        this.unitat = unitat;
    }
}
