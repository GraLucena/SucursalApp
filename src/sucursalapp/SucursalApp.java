/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursalapp;

import ventanas.MenuFranquicia;
import sockets.Servidor;

/**
 *
 * @author daniel
 */
public class SucursalApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MenuFranquicia menu = new MenuFranquicia();
        menu.setVisible(true);

        Servidor servidor = new Servidor(args[0]);
        new Thread(servidor).start();
    }
}
