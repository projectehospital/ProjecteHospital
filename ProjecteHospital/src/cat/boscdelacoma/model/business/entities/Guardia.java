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
    private ArrayList<Treballador> treballadors;
    private Torn torn;
    private Unitat unitat;

    public Guardia(LocalDate dia, short placesDisponibles, Torn torn, Unitat unitat) {
        this.dia = dia;
        this.placesDisponibles = placesDisponibles;
        this.torn = torn;
        this.unitat = unitat;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public short getPlacesDisponibles() {
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
}
