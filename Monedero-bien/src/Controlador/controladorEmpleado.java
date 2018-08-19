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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

//import vista.Empleados;
//import modelo.ModeloMenu;
//
//import vista.MenuPrincipal;
//import modelo.ModSQLInicioSesion;
import Modelo.modeloEmpleado;
import Vista.Empleados;
import javax.swing.event.ChangeEvent;

public class controladorEmpleado implements ActionListener, MouseListener{
    private modeloEmpleado modelo;
    private Empleados vista;
   
    
    public controladorEmpleado(modeloEmpleado modelo, Empleados vista)
{       this.modelo = modelo;
        this.vista = vista;
        this.vista.empleado.addMouseListener(this);
        this.vista.btnAgregar1.addActionListener(this); //Aqui
        this.vista.btnEditar.addActionListener(this);//Aqui
        this.vista.btnCancelar.addActionListener(this);//Aqui
        //this.vista.btnBuscar1.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
}

    public void iniciarVista(){
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //vista.setAlwaysOnTop( true );
        vista.setLocationRelativeTo(null);
        //vista.setAlwaysOnTop( false );
        //vista.setVisible(true);
        vista.setResizable(false);
        vista.setTitle("Empleado");
        transparenciaButton();
        vista.empleado.setModel(modelo.cargarDatos());
        //vista.setAlwaysOnTop( false );
        vista.setVisible(true);
        //deshabilitarElementos();
    }
    
    
    public void transparenciaButton(){
        vista.btnRegresar.setOpaque(false);
        vista.btnRegresar.setContentAreaFilled(false);
        vista.btnRegresar.setBorderPainted(false);
        vista.btnAgregar1.setOpaque(false);
        vista.btnAgregar1.setContentAreaFilled(false);
        vista.btnAgregar1.setBorderPainted(false);
        vista.btnCancelar.setOpaque(false);
        vista.btnCancelar.setContentAreaFilled(false);
        vista.btnCancelar.setBorderPainted(false);
        vista.btnEliminar.setOpaque(false);
        vista.btnEliminar.setContentAreaFilled(false);
        vista.btnEliminar.setBorderPainted(false);
        vista.btnEditar.setOpaque(false);
        vista.btnEditar.setContentAreaFilled(false);
        vista.btnEditar.setBorderPainted(false);
//        vista.btnBuscar1.setOpaque(false);
//        vista.btnBuscar1.setContentAreaFilled(false);
//        vista.btnBuscar1.setBorderPainted(false);
    }
   
    
    public void actionPerformed(ActionEvent evento){
        if(vista.btnAgregar1 == evento.getSource()){
            int status=1;
            modelo.agregarSucursal(Integer.parseInt(vista.txtIdEmpleado.getText()), vista.txtNombre.getText(),vista.txtDireccion.getText(), vista.txtTelefono.getText(),Integer.parseInt(vista.txtIdSucursal.getText()));
                
            JOptionPane.showMessageDialog(vista, "Registro insertado exitosamente");
            limpiar();
            vista.empleado.setModel(modelo.cargarDatos());
        }                
        if(vista.btnCancelar == evento.getSource()){
                limpiar();
        }   
        if(vista.btnRegresar == evento.getSource()){
//            MenuPrincipal obj = new MenuPrincipal();
//            ModeloMenu modeloMenu = new ModeloMenu();
//            ControladorMenuPrincipal ControladorMenuPrincipal = new ControladorMenuPrincipal(modeloMenu,obj);
//            ControladorMenuPrincipal.iniciarVista();
//            vista.dispose();
        }
//        if(vista.btnBuscar1 == evento.getSource()){ 
//            vista.btnCancelar.setEnabled(true);
//            int idEmp = Integer.parseInt(vista.txtIdEmpleado.getText());
//            vista.empleado.setModel(modelo.buscarDatos( idEmp));          
//           // JOptionPane.showMessageDialog(null, "Registro consultado exitosamente");
//        }
        if(vista.btnEditar == evento.getSource()){
            modelo.conActualizar(Integer.parseInt(vista.txtIdEmpleado.getText()), vista.txtNombre.getText(),vista.txtDireccion.getText(),vista.txtTelefono.getText(), Integer.parseInt(vista.txtIdSucursal.getText()));
            JOptionPane.showMessageDialog(null, "Registro modificado");
            vista.empleado.setModel(modelo.cargarDatos());
            limpiar();
        }
        if(vista.btnEliminar == evento.getSource()){
            if(modelo.conEliminar(Integer.parseInt(vista.txtIdEmpleado.getText()))){
                JOptionPane.showMessageDialog(null, "Registro eliminado");
                limpiar();
                vista.empleado.setModel(modelo.cargarDatos());
            }
        }
        }


    @Override

    public void mouseClicked(MouseEvent e) { //To change body of generated methods, choose Tools | Templates.
         if(vista.empleado== e.getSource()){
            int fila=vista.empleado.rowAtPoint(e.getPoint());
            if(fila > -1)
            {
                vista.txtIdEmpleado.setText(String.valueOf(vista.empleado.getValueAt(fila, 0)));
                vista.txtNombre.setText(String.valueOf(vista.empleado.getValueAt(fila, 1)));
                vista.txtDireccion.setText(String.valueOf(vista.empleado.getValueAt(fila, 2)));
                vista.txtTelefono.setText(String.valueOf(vista.empleado.getValueAt(fila, 3)));
            }
         }
    }

    @Override
    public void mousePressed(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }
    public void limpiar() {
        vista.txtNombre.setText("");
        vista.txtDireccion.setText("");
        vista.txtIdEmpleado.setText("");
        vista.txtTelefono.setText("");
        vista.txtIdSucursal.setText("");
    }
}
