package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    public Connection abrirConexion() throws SQLException{
        Connection con;
        try {
            //Inicializar la conexión a la base de datos
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection("jdbc:mysql://localhost/monedero", "root", ""); //Motor, dirección, puerto, nombre
            System.out.println("Conexión realizada");
        }catch(SQLException e) {
            System.out.print("No se pudo realizar la conexión\n");
            con = null;
        }
        return con;
    }
    
    public void cerrarConexion(Connection con) {
        try {
            if(!con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
