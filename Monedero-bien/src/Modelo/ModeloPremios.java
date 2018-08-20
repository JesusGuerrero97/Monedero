
package Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ModeloPremios {
    private int vIdPrem;
    private String vDes;
    private int vPuntos;
    private int vStock;
    
    private Conexion conexion = new Conexion();
    
    public DefaultTableModel cargarDatos(){
        try
       {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
         try
        {
          ResultSet rs = s.executeQuery("SELECT Id_premio, Descripcion, Stock, Puntos FROM premios ");
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
    
    public DefaultTableModel buscarDatos(int idPremio){
        try
       {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
         try
        {
          ResultSet rs = s.executeQuery("SELECT Id_premio, Descripcion, Stock, Puntos FROM premios WHERE Id_premio = "+idPremio+" ;");
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
    
    public void agregarPremio( int vIdPrem, String vDes, int vPuntos, int vStock)
    {
        try
        {
            //Para abrir una conxion a la BD
            Connection con = conexion.abrirConexion();
            //Para Ejecutar la consulta
            //Statement s = con.createStatement();
            //JOptionPane.showMessageDialog(null, vConFecha+"---"+vConHora+"---"+vConTipo+"---"+vConNombre+"---"+vConPeso);
            String query  = "INSERT INTO premios(Id_premio, Descripcion, Stock, Puntos) values (?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,vIdPrem);
            preparedStatement.setString(2,vDes);
            preparedStatement.setInt(3,vPuntos);
            preparedStatement.setInt(4,vStock);
            preparedStatement.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Registro agregado");
            conexion.cerrarConexion(con);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public boolean editarPremio( int vIdPrem, String vDes, int vPuntos, int vStock)
    {
        try
        {
            Connection con = conexion .abrirConexion();
            Statement s = con.createStatement();
            System.out.println("UPDATE premios SET Id_premio ='"+vIdPrem+"', descripcion = '"+vDes+"', Puntos = '"+vPuntos+"', Stock = '"+vStock+"' WHERE Id_premio = "+vIdPrem+";");
            s.executeUpdate("UPDATE premios SET Id_premio ='"+vIdPrem+"', descripcion = '"+vDes+"', Puntos = '"+vPuntos+"', Stock = '"+vStock+"' WHERE Id_premio = "+vIdPrem+";");

            conexion.cerrarConexion(con);
            return true;
        
        } catch (SQLException e) {
            return false;
        } 
    }
    
    public boolean eliminarPremio( int idPremio)
    { 
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            s.executeUpdate("delete from premios where Id_premio="+idPremio+";") ;
                conexion.cerrarConexion(con);
                return true;
                    
        } catch (SQLException e) 
        {
            return false;
        }
        
    }
}
