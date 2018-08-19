
package Controlador;
import Modelo.*;
import Vista.*;
import Controlador.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JFrame;


public class ControladorMenuPrincipal implements ActionListener {
    ModeloMenuPrincipal modelo = new ModeloMenuPrincipal();
    MenuPrincipal menu = new MenuPrincipal();

    public ControladorMenuPrincipal(ModeloMenuPrincipal modelo, MenuPrincipal menu)
    {
        this.modelo = modelo;
        this.menu = menu;
    }
    
    ControladorMenuPrincipal(){
    
    }
    public void iniciarVista()
    {
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setAlwaysOnTop( true );
        menu.setAlwaysOnTop( false );
        menu.setResizable(false);
        menu.setTitle("Menu Principal");
        menu.pack();
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
        //AdmMenu.nombreEmpleado.setText("ID: "+this.idEmp);
        this.menu.btnCliente.addActionListener(this);
        this.menu.btnEmpleado.addActionListener(this);
        this.menu.btnMovimientos.addActionListener(this);
        this.menu.btnPremios.addActionListener(this);
        this.menu.btnCompras.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.menu.btnPremios == e.getSource())
        {   
            ModeloPremios modeloPremios = new ModeloPremios();
            Premios vistaPremios = new Premios();
            ControladorPremios controladorPremios = new ControladorPremios(modeloPremios,vistaPremios); 
            controladorPremios.iniciarVista();
            menu.dispose();
        }
        if (this.menu.btnEmpleado == e.getSource()) {
            modeloEmpleado modeloEmpleado = new modeloEmpleado();
            Empleados vistaEmpleado = new Empleados();
            controladorEmpleado controladorEmpleado = new controladorEmpleado(modeloEmpleado,vistaEmpleado); 
            controladorEmpleado.iniciarVista();
            menu.dispose();
        }
        if(this.menu.btnCliente == e.getSource())
        {   
            
        }
        if(this.menu.btnCompras == e.getSource())
        {   
            
        }
        if(this.menu.btnMovimientos == e.getSource())
        {   
            
        }
    }
    
}
