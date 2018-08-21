/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus
 */
public class ModeloCarga {
     private final Conexion conexion = new Conexion();
     
     public void llenarComboPremios(JComboBox<PremiosComboBox> comboPremios)
    {
        try
        {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         ResultSet rs=s.executeQuery("SELECT Id_premio,Descripcion FROM premios ORDER BY Descripcion");
         while(rs.next())
         {
             comboPremios.addItem(new PremiosComboBox(rs.getInt("Id_premio"),rs.getString("Descripcion")));
         }
         conexion.cerrarConexion(con);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public class PremiosComboBox
    {
        private int id_premio;
        private String descripcion;

        public PremiosComboBox(int id_premio, String  descripcion) {
            this.id_premio = id_premio;
            this.descripcion = descripcion;
        }

        public int getId_premio() {
            return id_premio;
        }

 
        public void setId_premio(int id_premio) {
            this.id_premio = id_premio;
        }

        
        public String getDescripcion() {
            return descripcion;
        }

        
        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
        
        @Override
        public String toString()
        {
            return descripcion;
        }
    }
    
    public boolean checarPuntos(int id_cargo,int id_premio, int numcu, String Fecha)
    {   
        int puntoCli = 0;
        int puntosPre=0;
        String C="Cargo";
         try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select Puntos from cliente where Num_cuenta="+numcu+";");
            //System.out.println("insert into cliente(Id_cliente, Nombre, Direccion, Telefono, Correo, Num_cuenta, Puntos) values("+id_cliente+",'"+nombre+"', '"+direccion+"', '"+telefono+"', '"+correo+"', "+num_cuenta+", " +puntos+");");
            Statement s2 = con.createStatement();
            ResultSet rs2;
            if(rs.next())
            {
                puntoCli=Integer.valueOf(rs.getString("Puntos"));
            }
            rs2 = s.executeQuery("select Puntos from premios where Id_premio="+id_premio+";");
            
            if(rs2.next())
            {
                puntosPre=Integer.valueOf(rs2.getString(1));
            }
            
            if(puntoCli<puntosPre)
            {
             JOptionPane.showMessageDialog(null, "Tus puntos no son suficientes");
             conexion.cerrarConexion(con);
             return false;
             
            }
            else if(puntoCli>=puntosPre){
                JOptionPane.showMessageDialog(null, "El premio ha sido canjeado con exito");
                //System.out.println("UPDATE cliente SET cliente.`Puntos`= (cliente.`Puntos`) - ("+puntosPre+") WHERE cliente.`Num_cuenta`="+numcu+";");
                s.executeUpdate("UPDATE cliente SET cliente.`Puntos`= (cliente.`Puntos`) - ("+puntosPre+") WHERE cliente.`Num_cuenta`="+numcu+";");
                
                s.executeUpdate("INSERT into movimientos(Id_mov,Num_cuenta,Tipo,Fecha) values(NULL,"+numcu+",'"+C+"','"+Fecha+"');");
                s.executeUpdate("INSERT into cargo(Id_cargo,Id_premio,Num_cuenta) values(NULL,"+id_premio+","+numcu+");");
                conexion.cerrarConexion(con);
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
        
    
    }
}
