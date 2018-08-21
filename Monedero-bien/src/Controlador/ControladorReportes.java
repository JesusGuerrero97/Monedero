/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.ModeloMovimientos;
import Vista.Movimientos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Jesus
 */
public class ControladorReportes implements ActionListener{
    Movimientos vista = new Movimientos();
    ModeloMovimientos modelo = new ModeloMovimientos();
    public ControladorReportes(ModeloMovimientos modelo,Movimientos vista)
    {
        this.vista=vista;
        this.modelo=modelo;
    }
    public void IniciarVista(){
        vista.btnGenerar.addActionListener(this);
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.btnSaldo.addActionListener(this);
        vista.setLocationRelativeTo(null);
        modelo.llenarComboClientes(vista.cmbCliente);
        vista.setResizable(false);
        vista.setTitle("Movimientos");
        
      //  vista.compras.setModel(modelo.cargarDatos());
        vista.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       if(vista.btnGenerar == e.getSource())
       {
           System.out.println("jajaja");
           generar();
       }
       else if(vista.btnSaldo == e.getSource())
       {
           Saldo();
       }
    }
    public void generar(){
     int id_cliente = vista.cmbCliente.getItemAt(vista.cmbCliente.getSelectedIndex()).getId_cliente();
           try {
                Conexion con = new Conexion();
                Connection conn= con.abrirConexion();
                JasperReport reporte = null;
                String path = "src\\Reportes\\Movimientos.jasper";
                
                Map parametro  = new HashMap();
                parametro.put("Cliente", id_cliente);
                
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro,conn);
                
                JasperViewer view = new JasperViewer(jprint, false);
                
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                view.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorReportes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(ControladorReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void Saldo()
    {
        int id_cliente = vista.cmbCliente.getItemAt(vista.cmbCliente.getSelectedIndex()).getId_cliente();
           try {
                Conexion con = new Conexion();
                Connection conn= con.abrirConexion();
                JasperReport reporte = null;
                String path = "src\\Reportes\\ReporteSaldo.jasper";
                
                Map parametro  = new HashMap();
                parametro.put("id_cliente", id_cliente);
                
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro,conn);
                
                JasperViewer view = new JasperViewer(jprint, false);
                
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                view.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorReportes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(ControladorReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
