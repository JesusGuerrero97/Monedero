/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monedero.bien;

import Controlador.ControladorLogin;
import Modelo.ModSQLInicioSesion;
import Vista.Login;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus
 */
public class MonederoBien {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try{
        ModSQLInicioSesion modelo = new ModSQLInicioSesion();
        Login vista = new Login();
        ControladorLogin control = new ControladorLogin(modelo, vista);
        control.iniciarVista();
       }catch(NullPointerException e)
       {
           JOptionPane.showMessageDialog(null,e) ;
       }
    }
    
}
