package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import statico.PoseElValor;

public class Conexion {
    
   public Connection abrirConexion(int sucu) throws SQLException
    {
        Connection con;
        
        if(PoseElValor.name != "")
        {
            try
            {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                System.out.println("IP intentada global: "+PoseElValor.name);
                con = DriverManager.getConnection(
                
                "jdbc:mysql://"+PoseElValor.name+":3306/monedero","usuario0","123456");
                //"jdbc:mysql://localhost:3306/monedero","root","");
            }
            catch(SQLException e1)
            {
                System.out.println("No se pudo abrir la conexión... intentando otra conexion");
                PoseElValor.name = "";
                //llama la otra funcion de conexion iguala con a lo que resulte
                con = abrirConexionIPS(sucu);
            }
        }
        else
        {
            //llama la otra funcion de conexion iguala con a lo que resulte
            con = abrirConexionIPS(sucu);
        }
        return con;
    }
    public Connection abrirConexionIPS(int sucu) throws SQLException
    {
        
        Connection con;
        //para conectarnos a nuestra base de datos
        
        String ipA = "";
        String ipB = "";
        String ipC = "";
        
        if(sucu == 1)
        {
            ipA = "192.168.43.24";//ip priorizada
            ipB = "192.168.43.88";
            ipC = "192.168.43.89";
        }
        if(sucu == 2)
        {
            ipA = "192.168.43.88";//ip priorizada
            ipB = "192.168.43.24";
            ipC = "192.168.43.89";
        }
        if(sucu == 3)
        {
            ipA = "192.168.43.89";//ip priorizada
            ipB = "192.168.43.24";
            ipC = "192.168.43.88";
        }
        
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("IP intentada:"+ipA);
            con = DriverManager.getConnection(
            "jdbc:mysql://"+ipA+":3306/monedero","usuario0","123456");//si fuera host no local ps pones la ip,, despues de los : es el puerto            
            //"jdbc:mysql://localhost:3306/monedero","root","");
            PoseElValor.name = ipA;
        }
        catch(SQLException e1)
        {
            System.out.println("No se pudo abrir la conexión... intentando otra conexion");
            PoseElValor.name = "";
            con = null;
            
            try
            {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                System.out.println("IP intentada:"+ipB);
                con = DriverManager.getConnection(
                "jdbc:mysql://"+ipB+":3306/monedero","usuario0","123456");//si fuera host no local ps pones la ip,, despues de los : es el puerto
                PoseElValor.name = ipB;
            }
            catch(SQLException e2)
            {
                System.out.println("No se pudo abrir la conexión... intentando otra conexion");
                PoseElValor.name = "";
                con = null;
                try
                {
                    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                    System.out.println("IP intentada:"+ipC);
                    con = DriverManager.getConnection(
                    "jdbc:mysql://"+ipC+":3306/monedero","usuario0","123456");//si fuera host no local ps pones la ip,, despues de los : es el puerto
                    PoseElValor.name = ipC;
                }
                catch(SQLException e3)
                {
                    System.out.println("No se pudo abrir la conexión");
                    PoseElValor.name = "";
                    con = abrirConexionIPS(sucu);
                }
            }
        }
        return con;
    }
    public void cerrarConexion (Connection c) throws SQLException{
        try{
            if(!c.isClosed()){
                c.close();
            }
        }
        catch(SQLException e){
            System.out.println("Error al cerrar la conexion: tal vez ya esta cerrada o algo, o no ha sido creado tal vez");
        }
    }
}