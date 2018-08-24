/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class modeloEmpleado {
    private int vId;
    private String vNom;
    private String vDir;
    private String vTel;
    private int vIdSuc;
    
    private Conexion conexion = new Conexion();
    
    public DefaultTableModel cargarDatos(){
        try
       {
         Connection con = conexion.abrirConexion(1);
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
         try
        {
          ResultSet rs = s.executeQuery("SELECT Id_empleado, Nombre, Direccion, Telefono, Id_Sucursal FROM empleado;");
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
    
    public DefaultTableModel buscarDatos(int idEmpleado){
        try
       {
         Connection con = conexion.abrirConexion(1);
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
         try
        {
          ResultSet rs = s.executeQuery("SELECT Id_empleado, Nombre, Direcion, Telefono, Id_Sucursal FROM empleado WHERE Id_empleado = "+idEmpleado+" ;");
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
    
    public void agregarSucursal( int vId, String vNom, String vDir, String vTel, int vIdSuc)
    {
        try
        {
            Connection con = conexion.abrirConexion(1);
             /*AQUI AGREGUE UN AUTOCOMMIT*/
            con.setAutoCommit(false);
            String query  = "INSERT INTO empleado( Id_empleado, Nombre, Direccion, Telefono, Id_Sucursal) values (?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,vId);
            preparedStatement.setString(2,vNom);
            preparedStatement.setString(3,vDir);
            preparedStatement.setString(4,vTel);
            preparedStatement.setInt(5,vIdSuc);
            preparedStatement.executeUpdate();
            
            /*AQUI HAY UN COMMIT*/
            con.commit();
            
            conexion.cerrarConexion(con);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public boolean conActualizar( int vId, String vNom, String vDir, String vTel, int vIdSuc)
    { 
        try
        {
            Connection con = conexion .abrirConexion(1);
            Statement s = con.createStatement();
            
             /*AQUI AGREGUE UN AUTOCOMMIT*/
            con.setAutoCommit(false);
            
            System.out.println("UPDATE empleado SET Nombre ='"+vNom+"', Direccion = '"+vDir+"', Telefono = '"+vTel+"', Id_Sucursal = "+vIdSuc+" WHERE Id_empleado = "+vId+";");
            s.executeUpdate("UPDATE empleado SET Nombre ='"+vNom+"', Direccion = '"+vDir+"', Telefono = '"+vTel+"', Id_Sucursal = "+vIdSuc+" WHERE Id_empleado = "+vId+";");

            /*AQUI HAY UN COMMIT*/
            con.commit();
            
            conexion.cerrarConexion(con);
            return true;
        
        } catch (SQLException e) {
            return false;
        } 
    }
    public boolean conEliminar( int idEmpleado)
    { 
        try
        {
            Connection con = conexion.abrirConexion(1);
            Statement s = con.createStatement();
            
            /*AQUI AGREGUE UN AUTOCOMMIT*/
            con.setAutoCommit(false);
            
            s.executeUpdate("delete from empleado where Id_empleado="+idEmpleado+"") ;
            
            /*AQUI HAY UN COMMIT*/
            con.commit();
            conexion.cerrarConexion(con);
                return true;
            
        } catch (SQLException e) 
        {
            return false;
        }
        
    }
    
}
