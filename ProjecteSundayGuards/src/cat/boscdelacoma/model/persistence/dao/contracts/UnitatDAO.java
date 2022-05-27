/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.contracts;

import cat.boscdelacoma.model.business.entities.Unitat;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;

/**
 *
 * @author osama
 */
public interface UnitatDAO extends DAO<Unitat>{
    
    //sobrecàrrega
    Unitat get (String s)throws DAOException;
    
}
