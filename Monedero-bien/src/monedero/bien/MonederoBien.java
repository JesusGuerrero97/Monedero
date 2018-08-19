/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monedero.bien;
import Vista.Empleados;
import Modelo.modeloEmpleado;
import Controlador.controladorEmpleado;


/**
 *
 * @author Jesus
 */
public class MonederoBien {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        modeloEmpleado modelo = new modeloEmpleado();
        Empleados vista = new Empleados();
        controladorEmpleado control = new controladorEmpleado(modelo, vista);
        control.iniciarVista();
    }
    
}
