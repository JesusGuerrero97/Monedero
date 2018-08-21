/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.ControladorGenerarCodigo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jesus
 */
public class ModeloCompras {
    
    
    private final Conexion conexion = new Conexion();

    public boolean agregarCliente(int Id_compra, int total, int numeroCuenta, int id_sucursal,String fecha, int id_emp){
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            /*AQUI AGREGUE UN AUTOCOMMIT*/
            con.setAutoCommit(false);
            //System.out.println("insert into cliente(Id_cliente, Nombre, Direccion, Telefono, Correo, Num_cuenta, Puntos) values("+id_cliente+",'"+nombre+"', '"+direccion+"', '"+telefono+"', '"+correo+"', "+num_cuenta+", " +puntos+");");
            s.executeUpdate("insert into compra(Id_compra, Total, Num_cuenta, Id_sucursal, Fecha, id_emp) values("+Id_compra+","+total+", "+numeroCuenta+", "+id_sucursal+", '"+fecha+"', "+id_emp+");");
            //INSERT INTO `biblioteca`.`libro` (`id_libro`, `nombre`, `autor`, `editorial`, `fecha_pub`, `numpag`, `edicion`, `genero`, `id_sucursal`, `existencia`) VALUES ('30', 'porpoe', 'dngf', 'dskygfs', '1998-02-22', '234', 'efds', 'edff', '3', '15');
            /*AQUI HAY UN COMMIT*/
            con.commit();
            conexion.cerrarConexion(con);
            return true;
        } catch (SQLException e) {
            return false;
        }
        
    }
    public boolean agregarTicket(int Id_ticket,String Folio, double puntos, int id_compra,int status){
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            /*AQUI AGREGUE UN AUTOCOMMIT*/
            con.setAutoCommit(false);
            //System.out.println("insert into cliente(Id_cliente, Nombre, Direccion, Telefono, Correo, Num_cuenta, Puntos) values("+id_cliente+",'"+nombre+"', '"+direccion+"', '"+telefono+"', '"+correo+"', "+num_cuenta+", " +puntos+");");
            System.out.println("insert into ticket(Id_ticket, Folio, Puntos, Id_compra, status) values("+Id_ticket+",'"+Folio+"', "+puntos+", "+id_compra+", "+status+");");
            s.executeUpdate("insert into ticket(Id_ticket, Folio, Puntos, Id_compra, status) values("+Id_ticket+",'"+Folio+"', "+puntos+", "+id_compra+", "+status+");");
            //INSERT INTO `biblioteca`.`libro` (`id_libro`, `nombre`, `autor`, `editorial`, `fecha_pub`, `numpag`, `edicion`, `genero`, `id_sucursal`, `existencia`) VALUES ('30', 'porpoe', 'dngf', 'dskygfs', '1998-02-22', '234', 'efds', 'edff', '3', '15');
            /*AQUI HAY UN COMMIT*/
            con.commit();
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
    
    public void llenarComboSucursal(JComboBox<SucursalComboBox> comboSucursal)
    {
        try
        {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         ResultSet rs=s.executeQuery("SELECT Id_sucursal,Nombre FROM sucursal ORDER BY Nombre");
         while(rs.next())
         {
             comboSucursal.addItem(new SucursalComboBox(rs.getInt("Id_sucursal"),rs.getString("Nombre")));
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
    
    public void llenarComboEmpleado(JComboBox<EmpleadoComboBox> comboEmpleado)
    {
        try
        {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         ResultSet rs=s.executeQuery("SELECT Id_empleado,Nombre FROM empleado ORDER BY Nombre");
         while(rs.next())
         {
             comboEmpleado.addItem(new EmpleadoComboBox(rs.getInt("Id_empleado"),rs.getString("Nombre")));
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
    public String cargarCodigo()
    {
        String codigo="";
        try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            int j;
            String num="";
            String c="";
            String SQL="SELECT MAX(ticket.`Folio`) FROM ticket;";
            try{
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                if(rs.next())
                {
                    c=rs.getString(1);
                    System.out.println(c);
                }
                if(c == null)
                {
                  codigo="CD0001";
                  return codigo;
                }
                else{
                    char r1=c.charAt(2);
                    char r2=c.charAt(3);
                    char r3=c.charAt(4);
                    char r4=c.charAt(5);
                    String r="";
                    r="" +r1+r2+r3+r4;
                    System.out.println(r);
                    j=Integer.parseInt(r);
                    
                    ControladorGenerarCodigo genCod = new ControladorGenerarCodigo();
                    genCod.ControladorGenerarCodigo(j);
                    codigo= genCod.serie();
                }
            }catch(Exception e){}                
           conexion.cerrarConexion(con);
        } catch (SQLException ex) { 
            Logger.getLogger(ModeloCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return codigo;
    }
}
