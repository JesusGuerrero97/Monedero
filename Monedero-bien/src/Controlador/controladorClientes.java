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

import Vista.Clientes;
import Modelo.modeloClientes;
import Modelo.ModeloMenuPrincipal;
import Vista.MenuPrincipal;
/**
 *
 * @author Dania
 */
public class controladorClientes implements ActionListener, MouseListener {
    private modeloClientes modelo;
    private Clientes vista;
   
    
    public void transparenciaButton(){
        vista.btnAgregar1.setOpaque(false);
        vista.btnAgregar1.setContentAreaFilled(false);
        vista.btnAgregar1.setBorderPainted(false);
        vista.btnRegresar.setOpaque(false);
        vista.btnRegresar.setContentAreaFilled(false);
        vista.btnRegresar.setBorderPainted(false);
    }
public controladorClientes(modeloClientes modelo, Clientes vista)
{       this.modelo = modelo;
        this.vista = vista;
        this.vista.clientes.addMouseListener(this);
        vista.btnAgregar1.addActionListener(this); //Aqui
        vista.btnEditar.addActionListener(this);//Aqui
        vista.btnCancelar.addActionListener(this);//Aqui
       // vista.btnBuscar.addActionListener(this);
        vista.btnEliminar2.addActionListener(this);
        vista.btnRegresar.addActionListener(this);
        //vista.btnActualizar.addActionListener(this);
}
    public void iniciarVista() 
{
    
    vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setAlwaysOnTop( true );
        vista.setAlwaysOnTop( false );
        vista.setResizable(false);
        vista.setTitle("Clientes");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    
    
    
    
    vista.clientes.setModel(modelo.cargarDatos());
    
}
        
    @Override
    public void actionPerformed(ActionEvent e) {
       if(vista.btnAgregar1 == e.getSource()) {
            
           if(modelo.agregarCliente(Integer.parseInt(vista.txtIdCliente.getText()), vista.txtNombre.getText(), vista.txtDireccion.getText(), vista.txtTelefono.getText(), vista.txtCorreo.getText(), Integer.parseInt(vista.txtNumCuenta.getText()), Double.parseDouble(vista.txtPuntos.getText()) )){
                limpiar();
                JOptionPane.showMessageDialog(vista, "Registro insertado exitosamente");
            limpiar();
            vista.clientes.setModel(modelo.cargarDatos());
            } 
                
            
        }
        if(vista.btnEditar == e.getSource()) {
            
            if(modelo.modificarCliente(Integer.parseInt(vista.txtIdCliente.getText()), vista.txtNombre.getText(), vista.txtDireccion.getText(), vista.txtTelefono.getText(), vista.txtCorreo.getText(), Integer.parseInt(vista.txtNumCuenta.getText()), Double.parseDouble(vista.txtPuntos.getText()))){
                limpiar();
                JOptionPane.showMessageDialog(vista, "Registro editado exitosamente");
            limpiar();
            vista.clientes.setModel(modelo.cargarDatos());
                
            }
        }
        if(vista.btnCancelar == e.getSource()) {
//            if(modelo.deleteCliente(Integer.parseInt(vista.txtIdCliente.getText()))) {
//                
//            }
                limpiar();
        }
        if(vista.btnEliminar2 == e.getSource()) {
            if(modelo.deleteCliente(Integer.parseInt(vista.txtIdCliente.getText()))) {
                JOptionPane.showMessageDialog(vista, "Registro eliminado exitosamente");
                limpiar();
                vista.clientes.setModel(modelo.cargarDatos());
            }
                limpiar();
        }
//        if(vista.btnBuscar == e.getSource()){ 
//            vista.btnCancelar.setEnabled(true);
//            int id_cliente = Integer.parseInt(vista.txtIdCliente.getText());
//            vista.clientes.setModel(modelo.buscarDatos( id_cliente));          
//            JOptionPane.showMessageDialog(null, "Registro consultado exitosamente");
//        }
        if(vista.btnRegresar == e.getSource()){
            MenuPrincipal obj = new MenuPrincipal();
            ModeloMenuPrincipal modeloMenu = new ModeloMenuPrincipal();
            ControladorMenuPrincipal ControladorMenuPrincipal = new ControladorMenuPrincipal(modeloMenu,obj);
            ControladorMenuPrincipal.iniciarVista();
            vista.dispose();
        }
//        if(vista.btnActualizar == e.getSource()){
//             limpiar();
//            vista.clientes.setModel(modelo.cargarDatos());  
//               
//        }
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        if(vista.clientes== e.getSource()){
            int fila=vista.clientes.rowAtPoint(e.getPoint());
            if(fila > -1)
            {
                vista.txtIdCliente.setText(String.valueOf(vista.clientes.getValueAt(fila, 0)));
                vista.txtNombre.setText(String.valueOf(vista.clientes.getValueAt(fila, 1)));
                vista.txtDireccion.setText(String.valueOf(vista.clientes.getValueAt(fila, 2)));
                vista.txtTelefono.setText(String.valueOf(vista.clientes.getValueAt(fila, 3)));
                vista.txtCorreo.setText(String.valueOf(vista.clientes.getValueAt(fila, 4)));
            }
        }  //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent ae) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent ae) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public 
        void mouseEntered(MouseEvent ae) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent ae) {
        //To change body of generated methods, choose Tools | Templates.
    }
    public void limpiar() {
        vista.txtNombre.setText("");
        vista.txtCorreo.setText("");
        vista.txtDireccion.setText("");
        vista.txtIdCliente.setText("");
        vista.txtTelefono.setText("");
        vista.txtPuntos.setText("");
        vista.txtNumCuenta.setText("");
    }
}
