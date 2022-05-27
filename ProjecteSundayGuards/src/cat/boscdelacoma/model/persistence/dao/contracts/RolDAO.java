/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.contracts;

import cat.boscdelacoma.model.business.entities.Rol;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;

/**
 *
 * @author Victo
 */
public interface RolDAO extends DAO<Rol>{
    //sobrecàrrega
    Rol get (String s)throws DAOException;
}
