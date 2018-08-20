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
import javax.swing.JFrame;
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
        vista.pack();
        this.vista.btnAgregar1.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
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
