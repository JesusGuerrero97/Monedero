/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.modeloAbono;
import Vista.Abono;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Alfonso
 */
public class controladorAbono implements ActionListener {
    modeloAbono modelo = new modeloAbono();
    Abono vista = new Abono();
    
    public controladorAbono(modeloAbono modelo,Abono vista)
    {
        this.modelo= modelo;
        this.vista= vista;
    }
    public void iniciarVista()
   {
       this.vista.btnAceptar.addActionListener(this);
       this.vista.btnCancelar.addActionListener(this);
       modelo.llenarComboTicket(vista.cmbFolio);
        //vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        vista.setLocationRelativeTo(null);
        
        vista.setResizable(false);
        vista.setTitle("Abono");
        
        vista.setVisible(true);
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id_ticket = vista.cmbFolio.getItemAt(vista.cmbFolio.getSelectedIndex()).getId_ticket();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String fecha= df.format(vista.jDate.getDate());
        if(vista.btnAceptar == e.getSource())
        {
            if(modelo.agregarAbono(Integer.valueOf(vista.txtId_abono.getText()), id_ticket, Integer.valueOf(vista.txtNum_Cuenta.getText()), fecha))
            {
                JOptionPane.showMessageDialog(vista, "Se agrego correctamente el abono");
            }
            else
            {
                JOptionPane.showMessageDialog(vista, "No se pudo agregar el abono correctamente");
            }
        }
    }
}
