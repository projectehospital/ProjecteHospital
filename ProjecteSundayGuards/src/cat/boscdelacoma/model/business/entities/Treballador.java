/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.business.entities;

import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCGuardiaDAO;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCTreballadorDAO;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Victo
 */
public class Treballador {
    
    private long id , esCapDeUnitat = 0;
    // en cas que un treballador no sigui cap de unitat el valor sera zero
    private String nom, dni, passwd, tipusContracte;
    private long guardiesPrevistes, guardiesFetes;
    private LocalDate dataNaixement;
    private Rol rolTreballador;
    private Categoria categoria;
    private ArrayList<Guardia> llistaGuardiesFetes; // possible hashmap

    
    
    
     public Treballador(){
    }
     
      public Treballador(Treballador treballador){
          // contructor de copia de treballador copia tot menys array de guardies
        this.id = treballador.getId();
        this.nom = treballador.getNom();
        this.dni = treballador.getDni();
        this.passwd = treballador.getPasswd();
        this.tipusContracte = treballador.getTipusContracte();
        this.guardiesPrevistes = treballador.getGuardiesPrevistes();
        this.guardiesFetes = treballador.getGuardiesFetes();
        this.dataNaixement = treballador.getDataNaixement();
        this.rolTreballador = treballador.getRolTreballador();
        this.categoria = getCategoriaTreballador();
        this.esCapDeUnitat = treballador.getEsCapDeUnitat();
    }
    
    public Treballador(long id, String dni, String nom, LocalDate dataNaixement, String passwd, long guardiesFetes, long guardiesPrevistes, String tipusContracte, Categoria categoria, Rol rolTreballador, long esCapDeUnitat) {
       
       // treballador amb guardies previstes i fetes 
        
        this.id = id;
        this.nom = nom;
        this.dni = dni;
        this.passwd = passwd;
        this.tipusContracte = tipusContracte;
        this.guardiesPrevistes = guardiesPrevistes;
        this.guardiesFetes = guardiesFetes;
        this.dataNaixement = dataNaixement;
        this.rolTreballador = rolTreballador;
        this.categoria = categoria;
        this.esCapDeUnitat = esCapDeUnitat;
    }
    
      public Treballador( String dni, String nom, LocalDate dataNaixement, String passwd, long guardiesFetes, long guardiesPrevistes, String tipusContracte, Categoria categoria, Rol rolTreballador, long esCapDeUnitat) {
       
       // treballador amb guardies previstes i fetes sense id
        

        this.nom = nom;
        this.dni = dni;
        this.passwd = passwd;
        this.tipusContracte = tipusContracte;
        this.guardiesPrevistes = guardiesPrevistes;
        this.guardiesFetes = guardiesFetes;
        this.dataNaixement = dataNaixement;
        this.rolTreballador = rolTreballador;
        this.categoria = categoria;
        this.esCapDeUnitat = esCapDeUnitat;
    }

    public Treballador(long id, String dni, String nom, LocalDate dataNaixement ,String passwd, String tipusContracte , Categoria categoria, Rol rolTreballador, long esCapDeUnitat) {
        
        // treballador que no s'especifica les guardies fetes
        this.id = id;
        this.nom = nom;
        this.dni = dni;
        this.passwd = passwd;
        this.guardiesPrevistes = 0;
        this.guardiesFetes = 0;
        this.rolTreballador = rolTreballador;
        this.tipusContracte = tipusContracte;
        this.dataNaixement = dataNaixement;
        this.categoria = categoria;
        this.esCapDeUnitat = esCapDeUnitat;
    }
    
    public Treballador(String dni , String passwd){
        
        this.dni = dni;
        this.passwd = passwd;
                                
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

    public long getGuardiesPrevistes() {
        return guardiesPrevistes;
    }

    public void setGuardiesPrevistes(long guardiesPrevistes) {
        this.guardiesPrevistes = guardiesPrevistes;
    }

    public long getGuardiesFetes() {
        return guardiesFetes;
    }

    public void setGuardiesFetes(long guardiesFetes) {
        this.guardiesFetes = guardiesFetes;
    }

    public long getEsCapDeUnitat() {
        // si no ho es retorna 0
        return esCapDeUnitat;
    }

    public void setEsCapDeUnitat ( long esCapDeUnitat) {
        this.esCapDeUnitat = esCapDeUnitat;
    }

    public LocalDate getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(LocalDate dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public Categoria getCategoriaTreballador() {
        return categoria;
    }

    public void setCategoriaTreballador(Categoria categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Guardia> getLlistaGuardiesFetes() {
        this.setLlistaGuardiesFetes(id);
        return llistaGuardiesFetes;
    }

    public void setLlistaGuardiesFetes(long idTreballador) {
        try{
            
        JDBCTreballadorDAO treb = new JDBCTreballadorDAO();
        treb.obtenirLlistaGuardies(idTreballador);
        
        } catch(DAOException e) {
            System.out.println("Error al obtenir llista guardies de treballador:" + e.getMessage());
        }
        
    }
    
    public void apuntarseAGuardia(long idGuardia) throws DAOException {
        
        try {
        JDBCTreballadorDAO t = new JDBCTreballadorDAO();
        
        JDBCGuardiaDAO g = new JDBCGuardiaDAO();
        
            if (g.get(idGuardia) !=null) {
                t.reservarGuardia(this.id, idGuardia);
                this.setLlistaGuardiesFetes(idGuardia);
            } else {
                System.out.println("La guardia l'id de la guardia no existeix");
            }
        
        }catch(DAOException e){
            System.out.println("Error al apuntar-se a la guardia" + e.getMessage());
            throw new DAOException();
        }
    }
    
    public void desapuntarseDeGuardia(long idGuardia) throws DAOException {
        
        try {
        JDBCTreballadorDAO t = new JDBCTreballadorDAO();
        
         JDBCGuardiaDAO g = new JDBCGuardiaDAO();
        // comprovem que la guardia existeix
            if (g.get(idGuardia) !=null) {
                t.cancelarGuardia(this.id , idGuardia);
                this.setLlistaGuardiesFetes(idGuardia);
            } else {
                System.out.println("La guardia l'id de la guardia no existeix");
            }
        
        t.cancelarGuardia(this.id , idGuardia);
        }catch(DAOException e){
            System.out.println("Error al desapuntar-se de la guardia" + e.getMessage());
            throw new DAOException();
        }
    }

    public int obtenirGuardiesFetes(ArrayList<Guardia> llistaGuardiesFetes){
        return llistaGuardiesFetes.size();
    }    
}
