

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.business.entities;

import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCGuardiaDAO;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
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
    private ArrayList<Treballador> llistaTreballadors; 
    private Torn torn;
    private Unitat unitat;
    private Categoria categoria;
    
    
        public Guardia() {
    }
        public Guardia(Guardia g) {
            
            this.id = g.getId();
            this.dia = g.getDia();
            this.placesDisponibles = g.getPlacesDisponibles();
            this.torn = g.getTorn();
            this.unitat = g.getUnitat();
            this.categoria = g.getCategoria();
            
            
    }
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

    public ArrayList<Treballador> getLlistaTreballadors() {
        this.setLlistaTreballadors(id);
        return llistaTreballadors;
    }

    public void setLlistaTreballadors(long idGuardia) {
        
        try {
        
             JDBCGuardiaDAO guard = new JDBCGuardiaDAO();
             this.llistaTreballadors = guard.obtenirLlistaTreballadors(idGuardia);

        } catch(DAOException ex) {
            System.out.println("Error al obtenir llista de treballdors: " + ex.getMessage());
       
        }
       
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
