/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monedero.bien;

import Controlador.ControladorLogin;
import Controlador.ControladorMenuPrincipal;
import Modelo.ModSQLInicioSesion;
import Modelo.ModeloMenuPrincipal;
import Vista.Login;
import Vista.MenuPrincipal;

/**
 *
 * @author Jesus
 * 
 */
public class MonederoBien {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ModSQLInicioSesion modelo = new ModSQLInicioSesion();
        Login vista = new Login();
        ControladorLogin control = new ControladorLogin(modelo, vista);
        control.iniciarVista();
       
//       ModeloMenuPrincipal modelo = new ModeloMenuPrincipal();
//        MenuPrincipal vista = new MenuPrincipal();
//        ControladorMenuPrincipal control = new ControladorMenuPrincipal(modelo, vista);
//        control.iniciarVista();
    }
    
}
