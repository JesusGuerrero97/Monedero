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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jesus
 */
public class ModeloCompras {
    
    
    private Conexion conexion = new Conexion();
    
    public DefaultTableModel cargarDatos(){
        try
       {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
         try
        {
          ResultSet rs = s.executeQuery("SELECT compra.`Id_compra`,compra.`Total`,compra.`Num_cuenta`, cliente.`Nombre` AS Cliente, sucursal.`Nombre` AS Sucursal,compra.`Fecha`,empleado.`Nombre`\n" +
          "FROM compra INNER JOIN cliente ON compra.`Num_cuenta`= cliente.`Num_cuenta`\n" +
          "INNER JOIN sucursal ON compra.`Id_sucursal` = sucursal.`Id_sucursal`\n" +
          "INNER JOIN empleado ON compra.`id_emp` = empleado.`Id_empleado`;");
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
    
    public void llenarComboClientes(JComboBox<ClienteComboBox> comboCliente)
    {
        try
        {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         ResultSet rs=s.executeQuery("SELECT id_cliente,Nombre FROM cliente ORDER BY Nombre");
         while(rs.next())
         {
             comboCliente.addItem(new ClienteComboBox(rs.getInt("id_cliente"),rs.getString("Nombre")));
         }
         conexion.cerrarConexion(con);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public class ClienteComboBox
    {
        private int id_cliente;
        private String nombre_cli;

        public ClienteComboBox(int id_cliente, String nombre_cli) {
            this.id_cliente = id_cliente;
            this.nombre_cli = nombre_cli;
        }

        /**
         * @return the idPaciente
         */
        public int getId_cliente() {
            return id_cliente;
        }

        /**
         * @param idPaciente the idPaciente to set
         */
        public void setId_cliente(int idPaciente) {
            this.id_cliente = idPaciente;
        }

        /**
         * @return the nombrePaciente
         */
        public String getNombre_cli() {
            return nombre_cli;
        }

        /**
         * @param nombrePaciente the nombrePaciente to set
         */
        public void setNombre_cli(String nombrePaciente) {
            this.nombre_cli = nombre_cli;
        }
        
        @Override
        public String toString()
        {
            return nombre_cli;
        }
    }
    
    public void llenarComboSucursal(JComboBox<SucursalComboBox> comboSucursal)
    {
        try
        {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         ResultSet rs=s.executeQuery("SELECT id_sucursal,Nombre FROM sucursal ORDER BY Nombre");
         while(rs.next())
         {
             comboSucursal.addItem(new SucursalComboBox(rs.getInt("id_sucursal"),rs.getString("Nombre")));
         }
         conexion.cerrarConexion(con);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public class SucursalComboBox
    {
        private int id_sucursal;
        private String nombre;

        public SucursalComboBox(int id_sucursal, String  nombre) {
            this.id_sucursal = id_sucursal;
            this.nombre = nombre;
        }

        public int getId_sucursal() {
            return id_sucursal;
        }

 
        public void setId_sucursal(int id_libro) {
            this.id_sucursal = id_libro;
        }

        
        public String getNombre() {
            return nombre;
        }

        
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        
        @Override
        public String toString()
        {
            return nombre;
        }
    }
    
    public void llenarComboEmpleado(JComboBox<EmpleadoComboBox> comboParto)
    {
        try
        {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         ResultSet rs=s.executeQuery("SELECT Id_empleado,Nombre FROM empleado ORDER BY Nombre");
         while(rs.next())
         {
             comboParto.addItem(new EmpleadoComboBox(rs.getInt("Id_empleado"),rs.getString("Nombre")));
         }
         conexion.cerrarConexion(con);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public class EmpleadoComboBox
    {
        private int id_emp;
        private String nombre_emp;

        public EmpleadoComboBox(int id_emp, String nombre_emp) {
            this.id_emp = id_emp;
            this.nombre_emp = nombre_emp;
        }

        public int getId_emp() {
            return id_emp;
        }

 
        public void setId_emp(int id_emp) {
            this.id_emp = id_emp;
        }

        
        public String getNombre_emp() {
            return nombre_emp;
        }

        
        public void setNombre_emp(String nombre_emp) {
            this.nombre_emp = nombre_emp;
        }
        
        @Override
        public String toString()
        {
            return nombre_emp;
        }
    }
}
