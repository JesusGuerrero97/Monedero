
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

public class ModeloPremios {
    private int vIdPrem;
    private String vDes;
    private int vPuntos;
    private int vStock;
    
    private Conexion conexion = new Conexion();
    
    public DefaultTableModel cargarDatos(){
        try
       {
         Connection con = conexion.abrirConexion(1);
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
         try
        {
          ResultSet rs = s.executeQuery("SELECT * FROM premios ");
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
         Connection con = conexion.abrirConexion(1);
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
         try
        {
          ResultSet rs = s.executeQuery("SELECT Id_premio, Descripcion, Stock, Puntos, Id_sucursal FROM premios WHERE Id_premio = "+idPremio+" ;");
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
    
    public void agregarPremio( int vIdPrem, String vDes, int vPuntos, int vStock, int id_sucursal)
    {
        try
        {
            //Para abrir una conxion a la BD
            Connection con = conexion.abrirConexion(1);
            
            /*AQUI AGREGUE UN AUTOCOMMIT*/
            con.setAutoCommit(false);
            
            //Para Ejecutar la consulta
            //Statement s = con.createStatement();
            //JOptionPane.showMessageDialog(null, vConFecha+"---"+vConHora+"---"+vConTipo+"---"+vConNombre+"---"+vConPeso);
            String query  = "INSERT INTO premios(Id_premio, Descripcion, Stock, Puntos, Id_sucursal) values (?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,vIdPrem);
            preparedStatement.setString(2,vDes);
            preparedStatement.setInt(3,vPuntos);
            preparedStatement.setInt(4,vStock);
            preparedStatement.setInt(5,id_sucursal);
            preparedStatement.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Registro agregado");
            /*AQUI HAY UN COMMIT*/
            con.commit();
            conexion.cerrarConexion(con);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public boolean editarPremio( int vIdPrem, String vDes, double vPuntos, int vStock, int id_sucursal)
    {
        try
        {
            Connection con = conexion .abrirConexion(1);
            Statement s = con.createStatement();
           
            /*AQUI AGREGUE UN AUTOCOMMIT*/
            con.setAutoCommit(false);
            
            System.out.println("UPDATE premios SET Descripcion = '"+vDes+"', Puntos = '"+vPuntos+"', Stock = '"+vStock+"', Id_sucursal "+id_sucursal+" WHERE Id_premio = "+vIdPrem+";");
            s.executeUpdate("UPDATE premios SET Descripcion ='"+vDes+"', Puntos = "+vPuntos+", Stock = "+vStock+", Id_sucursal="+id_sucursal+" where Id_premio="+vIdPrem+";");
            
            /*AQUI HAY UN COMMIT*/
            con.commit();
            conexion.cerrarConexion(con);
            return true;
        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        } 
    }
    
    public boolean eliminarPremio( int idPremio)
    { 
        try
        {
            Connection con = conexion.abrirConexion(1);
            Statement s = con.createStatement();
           
            /*AQUI AGREGUE UN AUTOCOMMIT*/
            con.setAutoCommit(false);
            
            s.executeUpdate("delete from premios where Id_premio="+idPremio+";") ;
            
            /*AQUI HAY UN COMMIT*/
            con.commit();
            
            conexion.cerrarConexion(con);
            return true;
                    
        } catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
        
    }
    
    public void llenarComboSucursal(JComboBox<SucursalComboBox> comboSucursal)
    {
        try
        {
         Connection con = conexion.abrirConexion(1);
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
}
