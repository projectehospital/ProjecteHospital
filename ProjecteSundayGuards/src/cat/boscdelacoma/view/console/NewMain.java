/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cat.boscdelacoma.view.console;

import cat.boscdelacoma.model.business.entities.Treballador;
import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCTreballadorDAO;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.sql.SQLException;

/**
 *
 * @author Victo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DAOException, SQLException {
        Menu m = new Menu();
        var tr = new JDBCTreballadorDAO();
        // 3 administrador
        // 4 cap de unitat
        // g dispo 22 i 8 del 05
        Treballador t = tr.get(4);
      
        m.menuInicial(t);
    }
    
}
