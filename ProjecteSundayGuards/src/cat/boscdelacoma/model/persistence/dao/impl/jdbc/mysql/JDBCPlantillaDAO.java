/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import cat.boscdelacoma.model.business.entities.Guardia;
import cat.boscdelacoma.model.business.entities.PlantillaGuardia;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oussama
 */
public class JDBCPlantillaDAO  {
    
    public List<PlantillaGuardia> getAll() throws DAOException {
        try {
            Statement query = MYSQLConnection.getInstance().getConnection().createStatement();
            ResultSet resultat = query.executeQuery("select * from plantilla_guardia");
            List<PlantillaGuardia> list = new ArrayList<>();
            JDBCCategoriaDAO c = new JDBCCategoriaDAO();
            JDBCUnitatDAO u = new JDBCUnitatDAO();
            JDBCTornDAO t = new JDBCTornDAO();
            while (resultat.next()) {
                list.add(new PlantillaGuardia(resultat.getLong("id"), u.getFromTipusUnitat(resultat.getString("tipus_unitat")), c.getFromTipusCategoria(resultat.getString("tipus_categoria")),t.getFromTipusTorn(resultat.getString("tipus_torn")), resultat.getLong("places_disponibles")));
            }
            return list;
        } catch (SQLException ex) {
            throw new DAOException();
        }

    }

    
    
}
