/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conectionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gilmar
 */
public class ConnectionPostgres {
    
    private String driver;
    private String url;
    private String usuario;
    private String senha;
    
    public ConnectionPostgres(){
        this.driver = "org.postgresql.Driver";
        this.url = "jdbc:postgresql://localhost:5432/PontoEletronico";
        this.usuario = "postgres";
        this.senha = "qw190787";
        
      }
    
    public Connection getconection() throws SQLException{
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return con;
    }
    
}
