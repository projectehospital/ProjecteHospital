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
    private String nom, cognom, edat, dni, password, rol;
    private int nGuardies;
    private ArrayList<Guardia> guardiesFetes;

    public Treballador(String nom, String cognom, String edat, String dni, String password) {
        this.nom = nom;
        this.cognom = cognom;
        this.edat = edat;
        this.dni = dni;
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getEdat() {
        return edat;
    }

    public void setEdat(String edat) {
        this.edat = edat;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getnGuardies() {
        return nGuardies;
    }

    public void setnGuardies(int nGuardies) {
        this.nGuardies = nGuardies;
    }

    public ArrayList<Guardia> getGuardiesFetes() {
        return guardiesFetes;
    }

    public void setGuardiesFetes(ArrayList<Guardia> guardiesFetes) {
        this.guardiesFetes = guardiesFetes;
    }
}
