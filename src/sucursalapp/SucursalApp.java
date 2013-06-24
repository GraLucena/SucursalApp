/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursalapp;

import sockets.Conexion;
import sockets.Replicador;
import ventanas.LoginSucursal;
import sockets.Servidor;

/**
 *
 * @author daniel
 */
public class SucursalApp {

    public static String coordinador = "no";
    public static String nombresucursal = "";
    public static boolean sinConexion = false;
    public static String puertoEnvio = "";
    public static String puertoIp = "";
    public static boolean tengoInternet = false;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        puertoEnvio = args[1];
        puertoIp = args[2];
        
        LoginSucursal menu = new LoginSucursal();
        menu.setVisible(true);

        Servidor servidor = new Servidor(args[0], args[1], args[2]);
        new Thread(servidor).start();
        
        new Thread(new Conexion()).start();
        
    }
}
