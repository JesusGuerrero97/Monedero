/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus
 */
public class ModeloMovimientos {
     
    private Conexion conexion = new Conexion();

     
     public void llenarComboClientes(JComboBox<ClienteComboBox> comboCliente)
    {
        try
        {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         ResultSet rs=s.executeQuery("SELECT Id_cliente,Nombre FROM cliente ORDER BY Nombre");
         while(rs.next())
         {
             comboCliente.addItem(new ClienteComboBox(rs.getInt("Id_cliente"),rs.getString("Nombre")));
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
        private String nombre;

        public ClienteComboBox(int id_cliente, String  nombre) {
            this.id_cliente = id_cliente;
            this.nombre = nombre;
        }

        public int getId_cliente() {
            return id_cliente;
        }

 
        public void setId_cliente(int id_cliente) {
            this.id_cliente = id_cliente;
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
}
