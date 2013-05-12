/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursalapp;

import ventanas.LoginSucursal;
import sockets.*;



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
    }
}
