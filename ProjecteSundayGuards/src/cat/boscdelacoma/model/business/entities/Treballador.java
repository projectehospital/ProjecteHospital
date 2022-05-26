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
public class Treballador {
    
    private long id, esCapDeUnitat;
    private String nom, dni, passwd, rol;
    private int guardiesPrevistes;
    private LocalDate dataNaixament;
    private Categoria categoria;
    private ArrayList<Guardia> llistaGuardiesFetes;

    public Treballador(long id, String nom, String dni, String passwd, LocalDate dataNaixament, Categoria categoria, long esCapDeUnitat) {
        this.id = id;
        this.nom = nom;
        this.dni = dni;
        this.passwd = passwd;
        this.dataNaixament = dataNaixament;
        this.categoria = categoria;
        this.esCapDeUnitat = esCapDeUnitat;
    }
    
    public Treballador(long id, String nom, String dni, String passwd, LocalDate dataNaixament, Categoria categoria) {
        this.id = id;
        this.nom = nom;
        this.dni = dni;
        this.passwd = passwd;
        this.dataNaixament = dataNaixament;
        this.categoria = categoria;
    }
    
    public Treballador(){
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
    /**
     * @return the esCapDeUnitat
     */
    public long getEsCapDeUnitat() {
        return esCapDeUnitat;
    }

    /**
     * @param esCapDeUnitat the esCapDeUnitat to set
     */
    public void setEsCapDeUnitat(long esCapDeUnitat) {
        this.esCapDeUnitat = esCapDeUnitat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return passwd;
    }

    public void setPassword(String passwd) {
        this.passwd = passwd;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public LocalDate getEdat() {
        return dataNaixament;
    }

    public void setEdat(LocalDate dataNaixament) {
        this.dataNaixament = dataNaixament;
    }

    public int getGuardiesPendents() {
        return guardiesPrevistes;
    }

    public void setGuardiesPendents(int guardiesPrevistes) {
        this.guardiesPrevistes = guardiesPrevistes;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Guardia> getGuardiesFetes() {
        return llistaGuardiesFetes;
    }

    public void setGuardiesFetes(ArrayList<Guardia> llistaGuardiesFetes) {
        this.llistaGuardiesFetes = llistaGuardiesFetes;
    }
    
    public void afegirGuardia(Guardia g){
        getGuardiesFetes().add(g);
    }
    
    public void eliminarGuardia(Guardia g){
        getGuardiesFetes().remove(g);
    }
    
    public int obtenirGuardiesFetes(ArrayList<Guardia> llistaGuardiesFetes){
        return llistaGuardiesFetes.size();
    }    
}
