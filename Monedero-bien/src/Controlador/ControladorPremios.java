
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Modelo.ModeloPremios;
import Vista.MenuPrincipal;
import Vista.Premios;
import Controlador.ControladorMenuPrincipal;
import Modelo.ModeloMenuPrincipal;

class ControladorPremios implements ActionListener, PropertyChangeListener, ChangeListener, MouseListener {
    
    private ModeloPremios modelo;
    private Premios vista;
    
    public ControladorPremios(ModeloPremios modelo, Premios vista){
        this.modelo=modelo;
        this.vista=vista;
        this.vista.tablaPremios.addMouseListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
    }
    
    public void iniciarVista(){
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setAlwaysOnTop( true );
        vista.setLocationRelativeTo(null);
        vista.setAlwaysOnTop( false );vista.dispose();
        vista.setVisible(true);
        
        vista.setResizable(false);
        vista.setTitle("PREMIOS");
        vista.tablaPremios.setModel(modelo.cargarDatos());
        
    }
    
    public void limpiarVista(){
        vista.txtIdPremio.setText("");
        vista.txtDescrip.setText("");
        vista.txtStock.setText("");
        vista.txtPuntos.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
     try
     {
        if(vista.btnAgregar == evento.getSource())
        {
            modelo.agregarPremio(Integer.parseInt(vista.txtIdPremio.getText()), vista.txtDescrip.getText(), Integer.parseInt(vista.txtStock.getText()), Integer.parseInt(vista.txtStock.getText()));
                
            JOptionPane.showMessageDialog(vista, "Se insertó el registro");
            limpiarVista();
            vista.tablaPremios.setModel(modelo.cargarDatos());
        }                
        if(vista.btnCancelar == evento.getSource()){
                limpiarVista();
        }   
        if(vista.btnRegresar == evento.getSource()){
            MenuPrincipal obj = new MenuPrincipal();
            ModeloMenuPrincipal modeloMenu = new ModeloMenuPrincipal();
            ControladorMenuPrincipal ControladorMenuPrincipal = new ControladorMenuPrincipal (modeloMenu,obj);
            ControladorMenuPrincipal.iniciarVista();
            vista.dispose();
        }
        if(vista.btnCancelar == evento.getSource()){ 
            vista.btnCancelar.setEnabled(true);
            int idPremio = Integer.parseInt(vista.txtIdPremio.getText());
            vista.tablaPremios.setModel(modelo.buscarDatos( idPremio));          
           // JOptionPane.showMessageDialog(null, "Registro consultado exitosamente");
        }
        if(vista.btnEditar == evento.getSource()){
            modelo.editarPremio(Integer.parseInt(vista.txtIdPremio.getText()), vista.txtDescrip.getText(),Integer.parseInt(vista.txtStock.getText()),Integer.parseInt(vista.txtStock.getText()));
            vista.tablaPremios.setModel(modelo.cargarDatos());
            limpiarVista();
        }
        if(vista.btnEliminar == evento.getSource()){
            if(modelo.eliminarPremio(Integer.parseInt(vista.txtIdPremio.getText()))){
                JOptionPane.showMessageDialog(null, "Registro eliminado");
                limpiarVista();
                vista.tablaPremios.setModel(modelo.cargarDatos());
            }
        }
     }catch(NullPointerException ex){
        JOptionPane.showMessageDialog(null, "ERROR: CAMPOS NULOS");
     } 
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
    }

    @Override
    public void stateChanged(ChangeEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
            if(vista.tablaPremios== e.getSource()){
            int fila=vista.tablaPremios.rowAtPoint(e.getPoint());
            if(fila > -1)
            {
                vista.txtIdPremio.setText(String.valueOf(vista.tablaPremios.getValueAt(fila, 0)));
                vista.txtDescrip.setText(String.valueOf(vista.tablaPremios.getValueAt(fila, 1)));
                vista.txtStock.setText(String.valueOf(vista.tablaPremios.getValueAt(fila, 2)));
                vista.txtPuntos.setText(String.valueOf(vista.tablaPremios.getValueAt(fila, 3)));
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
    
}
