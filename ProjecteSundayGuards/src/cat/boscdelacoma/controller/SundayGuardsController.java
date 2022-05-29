/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cat.boscdelacoma.controller;

import cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql.JDBCTreballadorDAO;
import cat.boscdelacoma.model.business.entities.Treballador;
import cat.boscdelacoma.model.persistence.exceptions.DAOException;
import cat.boscdelacoma.view.console.Menu;
import cat.boscdelacoma.view.console.SHA512;
import cat.boscdelacoma.view.console.testos;
import javafx.application.Platform;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Victo
 */
public class SundayGuardsController implements Initializable {

    @FXML
    private TextField inputDni;
    @FXML
    private Button btnAccedir;
    @FXML
    private Button btnCancelar;
    @FXML
    private PasswordField inputContrasenya;

    /**
     * Initializes the controller class.
     */
    private Stage stage;
   
    
    public void setStage (Stage stage){
        this.stage = stage;
        
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }    

    @FXML
    private void btnAccioAccedir(ActionEvent event) {
        
        if(espaisPlens(inputDni,inputContrasenya)){
            JDBCTreballadorDAO treballador=new JDBCTreballadorDAO();
                    
            String dni= inputDni.getText();
            String contrasenya= inputContrasenya.getText();
            inputDni=limitText(inputDni,20);
            /*/contrasenya= SHA512(contrasenya, "Perrito");/*/

                try{
                    Treballador treb=treb.get(Long.parseLong(dni), contrasenya);
                                        //s'ha d'implementar mètode per DNI i contrasenya//
                }catch(DAOException ex){
                    Alert alert= new Alert(Alert.AlertType.ERROR);
                    System.out.println("Error al obtenir treballador per mètode DNI i contrasenya"+ ex.getMessage());
                    alert.setHeaderText("Error de la base de dades");
                    alert.show();
                }      
                
                if (treballador==null){
                    
                    Alert alert= new Alert(Alert.AlertType.ERROR);
                    
                    alert.setHeaderText("Dades incorrectes");
                    alert.show();
                }
                else{
                    //enviar treballador per paràmetre al main//
                    
                    testos.main(treb);
                    
                    stage.hide();
                    
                    
                }
                
                
                
            }
            
        }

    private boolean espaisPlens(TextField inputDni, TextField contrasenya){
        
        if(inputDni.getText().equals("")||inputContrasenya.getText().equals("")){
        
            Alert alert= new Alert(Alert.AlertType.ERROR);
            
            alert.setHeaderText("Introdueix totes les dades corresponents!");
            
            alert.show();
            return false;
  
    }
        return true;

    }
    
    public static TextField limitText(TextField textField, int length){
        
        String string = textField.getText();
        
        if(string.length()>length){
            textField.setText(string.substring(0,length));
            
        }
        return textField;
    }

    @FXML
    private void btnAccioCancelar(ActionEvent event) {
        stage.hide();
        }

   
}
    


    

