/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.persistence.dao.impl.jdbc.mysql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Victo
 */
public class MYSQLConnection {
    private final String FILE_CONFIG = "resources/config_osama.properties";
    
    private static MYSQLConnection instance;
    private Connection connection;
    
    private MYSQLConnection(){
        Properties prop = new Properties();
        try{
            InputStream config = new FileInputStream(FILE_CONFIG);
                prop.load(config);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");


                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
        }catch(IOException ex){
            System.out.println("No es troba el fitxer de configuració");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println("Error de connexió: " + ex.getMessage());
        }
    }
    
    public static MYSQLConnection getInstance(){
        if(instance == null){
            instance = new MYSQLConnection();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public void disconnect(){
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Error al desconnectar: " + ex.getMessage());
        }
    }
}
