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
 * @author Alfonso
 */
public class modeloAbono {
    private final Conexion conexion = new Conexion();
     
     public void llenarComboTicket(JComboBox<TicketComboBox> comboTicket)
    {
        try
        {
         Connection con = conexion.abrirConexion(1);
         Statement s = con.createStatement();
         ResultSet rs=s.executeQuery("SELECT Id_ticket,Folio FROM ticket where status!=2 ORDER BY Folio");
         while(rs.next())
         {
             comboTicket.addItem(new TicketComboBox(rs.getInt("Id_ticket"),rs.getString("Folio")));
         }
         conexion.cerrarConexion(con);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public class TicketComboBox
    {
        private int id_ticket;
        private String Folio;

        public TicketComboBox(int id_ticket, String  Folio) {
            this.id_ticket = id_ticket;
            this.Folio = Folio;
        }

        public int getId_ticket() {
            return id_ticket;
        }

 
        public void setId_ticket(int id_ticket) {
            this.id_ticket = id_ticket;
        }

        
        public String getFolio() {
            return Folio;
        }

        
        public void setFolio(String Folio) {
            this.Folio = Folio;
        }
        
        @Override
        public String toString()
        {
            return Folio;
        }
    }
    
    public boolean agregarAbono(int id_abono,int id_ticket, int numcu, String Fecha)
    {   
        double puntosTicket = 0;
        String C="Abono";
         try
        {
            Connection con = conexion.abrirConexion(1);
            Statement s = con.createStatement();
            
             /*AQUI AGREGUE UN AUTOCOMMIT*/
            con.setAutoCommit(false);
            
            ResultSet rs = s.executeQuery("select Puntos from ticket where Id_ticket="+id_ticket+";");
            //System.out.println("insert into cliente(Id_cliente, Nombre, Direccion, Telefono, Correo, Num_cuenta, Puntos) values("+id_cliente+",'"+nombre+"', '"+direccion+"', '"+telefono+"', '"+correo+"', "+num_cuenta+", " +puntos+");");
            Statement s2 = con.createStatement();
           // ResultSet rs2;
            if(rs.next())
            {
                puntosTicket=Double.valueOf(rs.getString("Puntos"));
            }
            //System.out.println("Update `monedero`.`cliente` SET Puntos=(Puntos)+ ("+puntosTicket+") WHERE cliente.`Num_cuenta`="+numcu+";");
            s.executeUpdate("Update `monedero`.`cliente` SET Puntos=(Puntos)+ ("+puntosTicket+") WHERE cliente.`Num_cuenta`="+numcu+";");
            s.executeUpdate("INSERT into movimientos(Id_mov,Num_cuenta,Tipo,Fecha) values(NULL,"+numcu+",'"+C+"','"+Fecha+"');");
            s.executeUpdate("INSERT into abono(Id_abono,Id_ticket,Num_cuenta) values(NULL,"+id_ticket+","+numcu+");");
            s.executeUpdate("UPDATE ticket SET status=2 WHERE Id_ticket="+id_ticket+";");
            
            /*AQUI HAY UN COMMIT*/
            con.commit();
            conexion.cerrarConexion(con);
            return true;
//            if(puntoCli>=puntosPre){
//                JOptionPane.showMessageDialog(null, "El premio ha sido canjeado con exito");
//                //System.out.println("UPDATE cliente SET cliente.`Puntos`= (cliente.`Puntos`) - ("+puntosPre+") WHERE cliente.`Num_cuenta`="+numcu+";");
//                s.executeUpdate("UPDATE cliente SET cliente.`Puntos`= (cliente.`Puntos`) - ("+puntosPre+") WHERE cliente.`Num_cuenta`="+numcu+";");
//                
//                s.executeUpdate("INSERT into movimientos(Id_mov,Num_cuenta,Tipo,Fecha) values(NULL,"+numcu+",'"+C+"','"+Fecha+"');");
//                s.executeUpdate("INSERT into cargo(Id_cargo,Id_premio,Num_cuenta) values(NULL,"+id_premio+","+numcu+");");
                
        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
        
    
    }
}
