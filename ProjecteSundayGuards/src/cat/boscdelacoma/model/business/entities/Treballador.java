/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.business.entities;

import java.util.ArrayList;

/**
 *
 * @author Victo
 */
public class Treballador {
    private String nom, dni, password;
    private int edat, guardiesPendents;
    private Rol rol;
    private Categoria categoria;
    private ArrayList<Guardia> guardiesFetes;

    public Treballador(String nom, String dni, String password, int edat, Categoria categoria) {
        this.nom = nom;
        this.dni = dni;
        this.password = password;
        this.edat = edat;
        this.categoria = categoria;
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
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public int getGuardiesPendents() {
        return guardiesPendents;
    }

    public void setGuardiesPendents(int guardiesPendents) {
        this.guardiesPendents = guardiesPendents;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Guardia> getGuardiesFetes() {
        return guardiesFetes;
    }

    public void setGuardiesFetes(ArrayList<Guardia> guardiesFetes) {
        this.guardiesFetes = guardiesFetes;
    }
    
    public void afegirGurardia(Guardia g){
        guardiesFetes.add(g);
    }
    
    public void eliminarGuardia(Guardia g){
        guardiesFetes.remove(g);
    }
    
    public int obtenirGuardiesFetes(ArrayList<Guardia> guardiesFetes){
        return guardiesFetes.size();
    }    
}
