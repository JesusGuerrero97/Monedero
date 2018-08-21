/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
/**
 *
 * @author Dania
 */
public class modeloClientes{
    private Conexion conexion = new Conexion();
    
     public boolean agregarCliente(int id_cliente, String nombre, String direccion, String telefono, String correo, int num_cuenta, double puntos){
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            System.out.println("insert into cliente(Id_cliente, Nombre, Direccion, Telefono, Correo, Num_cuenta, Puntos) values("+id_cliente+",'"+nombre+"', '"+direccion+"', '"+telefono+"', '"+correo+"', "+num_cuenta+", " +puntos+");");
            s.executeUpdate("insert into cliente(Id_cliente, Nombre, Direccion, Telefono, Correo, Num_cuenta, Puntos) values("+id_cliente+",'"+nombre+"', '"+direccion+"', '"+telefono+"', '"+correo+"', "+num_cuenta+"," +puntos+");");
            //INSERT INTO `biblioteca`.`libro` (`id_libro`, `nombre`, `autor`, `editorial`, `fecha_pub`, `numpag`, `edicion`, `genero`, `id_sucursal`, `existencia`) VALUES ('30', 'porpoe', 'dngf', 'dskygfs', '1998-02-22', '234', 'efds', 'edff', '3', '15');
            
            conexion.cerrarConexion(con);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"ERROR:" + e.getMessage());
            //System.out.println("ERROR:" + e.getMessage());
            return false;
            
        }
        
    }
     public boolean modificarCliente(int id, String nombre, String direccion, String telefono, String correo, int num_cuenta, double puntos){
        try
        {
            Connection con = conexion .abrirConexion();
            Statement s = con.createStatement();
           
            s.executeUpdate("update monedero.cliente set Nombre = '"+nombre+"', Direccion = '"+direccion+"', Telefono = '"+telefono+"', Correo = '"+correo+"', Num_cuenta = '"+num_cuenta+ "', Puntos = '"+puntos+" where Id_cliente = "+id+";");

            conexion.cerrarConexion(con);
            return true;
        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"ERROR:" + e.getMessage());
            return false;
        } 
    }
        public boolean deleteCliente(int id){
        try
        {
            Connection con = conexion .abrirConexion();
            Statement s = con.createStatement();
            s.executeUpdate("delete from cliente where Id_cliente = "+id+"");
            conexion.cerrarConexion(con);
            return true;
        
        } catch (SQLException e) {
            return false;
        } 
    }
        public DefaultTableModel cargarDatos(){
        try
       {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
         try
        {
          ResultSet rs = s.executeQuery("SELECT * FROM cliente;");
          modelo = new DefaultTableModel();
          ResultSetMetaData rsMd = rs.getMetaData();
          int cantidadColumnas = rsMd.getColumnCount();
          for(int i = 1; i <= cantidadColumnas; i++)
          {
            modelo.addColumn(rsMd.getColumnLabel(i));
          }while(rs.next())
          {
              Object[] fila = new Object[cantidadColumnas];
              for(int i = 0; i < cantidadColumnas; i++)
              {
                  fila[i] = rs.getObject(i+1);
              }
              modelo.addRow(fila);
          }return modelo;
        }finally
         {
             conexion.cerrarConexion(con);
         }
       }catch(SQLException e)
       {
           e.printStackTrace();
       }
       return null;
    }
    
    public DefaultTableModel buscarDatos(int id){
        try
       {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
         try
        {
          ResultSet rs = s.executeQuery("SELECT * FROM cliente WHERE Id_cliente = "+id+";");
          modelo = new DefaultTableModel();
          ResultSetMetaData rsMd = rs.getMetaData();
          int cantidadColumnas = rsMd.getColumnCount();
          for(int i = 1; i <= cantidadColumnas; i++)
          {
            modelo.addColumn(rsMd.getColumnLabel(i));
          }while(rs.next())
          {
              Object[] fila = new Object[cantidadColumnas];
              for(int i = 0; i < cantidadColumnas; i++)
              {
                  fila[i] = rs.getObject(i+1);
              }
              modelo.addRow(fila);
          }return modelo;
        }finally
         {
             conexion.cerrarConexion(con);
         }
       }catch(SQLException e)
       {
           e.printStackTrace();
       }
       return null;
    }
 
}
