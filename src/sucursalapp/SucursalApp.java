/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursalapp;

import sockets.Replicador;
import ventanas.LoginSucursal;
import sockets.Servidor;

/**
 *
 * @author daniel
 */
public class SucursalApp {

    public static String coordinador = "no";
    public static boolean sinConexion = false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LoginSucursal menu = new LoginSucursal();
        menu.setVisible(true);

        Servidor servidor = new Servidor(args[0]);
        new Thread(servidor).start();
        
        Replicador replicador = new Replicador("Estoy arriba", args[1], args[2]);
        new Thread(replicador).start();
    }
}
