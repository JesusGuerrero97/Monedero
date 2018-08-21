 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Modelo.ModeloCompras;
import Vista.Compras;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author Jesus
 */
public class ControladorCompras implements ActionListener, MouseListener {
    ModeloCompras modelo = new ModeloCompras();
    Compras vista = new Compras();
    
    public ControladorCompras(ModeloCompras modelo,Compras vista)
    {
     this.modelo = modelo;
     this.vista = vista;
    }
    
    public void iniciarVista(){
        this.vista.btnAgregar1.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.bnRegresar.addActionListener(this);
        modelo.llenarComboEmpleado(vista.cmbEmpleado);
        modelo.llenarComboSucursal(vista.cmbSucursal);
        
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        vista.setLocationRelativeTo(null);
        
        vista.setResizable(false);
        vista.setTitle("Compras");
        
        vista.compras.setModel(modelo.cargarDatos());
        vista.setVisible(true);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
     try
     {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String fecha= df.format(vista.JDate.getDate());
        int id_empleado = vista.cmbEmpleado.getItemAt(vista.cmbEmpleado.getSelectedIndex()).getId_emp();
        int id_sucursal = vista.cmbSucursal.getItemAt(vista.cmbSucursal.getSelectedIndex()).getId_sucursal();
        
        if(vista.btnAgregar1 == e.getSource())
        {
            if(modelo.agregarCliente(Integer.parseInt(vista.txtIdCompra.getText()),Integer.parseInt(vista.txtTotal.getText()) , Integer.parseInt(vista.txtNumeroCuenta.getText()), id_sucursal, fecha, id_empleado))
            {
               String folio="CD"+modelo.cargarCodigo();
                JOptionPane.showMessageDialog(vista, "Registro insertado exitosamente");
                vista.compras.setModel(modelo.cargarDatos());
                if(modelo.agregarTicket(Integer.parseInt(vista.txtIdCompra.getText()),folio,Integer.parseInt(vista.txtTotal.getText())*.15, Integer.parseInt(vista.txtIdCompra.getText()),1))
                {JOptionPane.showMessageDialog(vista, "Ya la hiciste prro");}
                else{JOptionPane.showMessageDialog(vista, "ah valiste vrg");}
            }
            else { JOptionPane.showMessageDialog(vista, "El registro no pudo ser completado con exito"); }
        }
      }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(null, "ERROR: CAMPOS NULOS");
      }    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
     
    }

    @Override
    public void mousePressed(MouseEvent e) {
     
    }

    @Override
    public void mouseReleased(MouseEvent e) {
     
    }

    @Override
    public void mouseEntered(MouseEvent e) {
     
    }

    @Override
    public void mouseExited(MouseEvent e) {
     
    }
    
}
