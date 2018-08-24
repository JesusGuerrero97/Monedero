/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloCarga;
import Vista.Cargo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus
 */
public class ControladorCargo implements ActionListener {
    
    ModeloCarga modelo = new ModeloCarga();
    Cargo vista = new Cargo();
    
   public ControladorCargo(ModeloCarga modelo,Cargo vista)
    {
        this.modelo=modelo;
        this.vista=vista;
    }
   
   public void iniciarVista()
   {
       this.vista.btnAceptar.addActionListener(this);
       this.vista.btnCancelar.addActionListener(this);
       modelo.llenarComboPremios(vista.cmbPremio);
        //vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        vista.setLocationRelativeTo(null);
        
        vista.setResizable(false);
        vista.setTitle("Compras");
        
        vista.setVisible(true);
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        try
        {
            int id_premio = vista.cmbPremio.getItemAt(vista.cmbPremio.getSelectedIndex()).getId_premio();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String fecha= df.format(vista.JDate.getDate());
            if(vista.btnAceptar==e.getSource())
            {

                    if(modelo.checarPuntos(Integer.valueOf(vista.txtIdCargo.getText()),id_premio, Integer.valueOf(vista.txtNum_cue.getText()),fecha))
                    {
                        System.out.println("paso");
                        //JOptionPane.showMessageDialog(null, "");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "intenta con otro premio");
                    }

            }
            else if(vista.btnCancelar== e.getSource())
            {

            }
        }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(null, "ERROR: CAMPOS NULOS");
        }     
    }
}
