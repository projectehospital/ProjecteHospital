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
    
    private long id;
    private String nom, dni, passwd, tipusContracte;
    private short guardiesPrevistes, guardiesFetes, esCapDeUnitat;
    private LocalDate dataNaixement;
    private Rol rolTreballador;
    private Categoria categoria;
    private ArrayList<Guardia> llistaGuardiesFetes;

    public Treballador(long id, String dni, String nom, LocalDate dataNaixement, String passwd, short guardiesFetes, short guardiesPrevistes, String tipusContracte, Categoria categoria, Rol rolTreballador, short esCapDeUnitat) {
        this.id = id;
        this.nom = nom;
        this.dni = dni;
        this.passwd = passwd;
        this.tipusContracte = tipusContracte;
        this.guardiesPrevistes = guardiesPrevistes;
        this.guardiesFetes = guardiesFetes;
        this.esCapDeUnitat = esCapDeUnitat;
        this.dataNaixement = dataNaixement;
        this.rolTreballador = rolTreballador;
        this.categoria = categoria;
    }

    public Treballador(long id, String nom, String dni, String passwd, Rol rolTreballador, String tipusContracte, LocalDate dataNaixement, Categoria categoria, short esCapDeUnitat) {
        this.id = id;
        this.nom = nom;
        this.dni = dni;
        this.passwd = passwd;
        this.rolTreballador = rolTreballador;
        this.tipusContracte = tipusContracte;
        this.dataNaixement = dataNaixement;
        this.categoria = categoria;
        this.esCapDeUnitat = esCapDeUnitat;
    }
    
    public Treballador(long id, String nom, String dni, String passwd, Rol rolTreballador, String tipusContracte, LocalDate dataNaixement, Categoria categoria) {
        this.id = id;
        this.nom = nom;
        this.dni = dni;
        this.passwd = passwd;
        this.rolTreballador = rolTreballador;
        this.tipusContracte = tipusContracte;
        this.dataNaixement = dataNaixement;
        this.categoria = categoria;
    }
    
    public Treballador(){
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Rol getRolTreballador() {
        return rolTreballador;
    }

    public void setRolTreballador(Rol rolTreballador) {
        this.rolTreballador = rolTreballador;
    }

    public String getTipusContracte() {
        return tipusContracte;
    }

    public void setTipusContracte(String tipusContracte) {
        this.tipusContracte = tipusContracte;
    }

    public short getGuardiesPrevistes() {
        return guardiesPrevistes;
    }

    public void setGuardiesPrevistes(short guardiesPrevistes) {
        this.guardiesPrevistes = guardiesPrevistes;
    }

    public short getGuardiesFetes() {
        return guardiesFetes;
    }

    public void setGuardiesFetes(short guardiesFetes) {
        this.guardiesFetes = guardiesFetes;
    }

    public short getEsCapDeUnitat() {
        return esCapDeUnitat;
    }

    public void setEsCapDeUnitat(short esCapDeUnitat) {
        this.esCapDeUnitat = esCapDeUnitat;
    }

    public LocalDate getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(LocalDate dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Guardia> getLlistaGuardiesFetes() {
        return llistaGuardiesFetes;
    }

    public void setLlistaGuardiesFetes(ArrayList<Guardia> llistaGuardiesFetes) {
        this.llistaGuardiesFetes = llistaGuardiesFetes;
    }
    
    public void afegirGuardia(Guardia g){
        getLlistaGuardiesFetes().add(g);
    }
    
    public void eliminarGuardia(Guardia g){
        getLlistaGuardiesFetes().remove(g);
    }
    
    public int obtenirGuardiesFetes(ArrayList<Guardia> llistaGuardiesFetes){
        return llistaGuardiesFetes.size();
    }    
}
