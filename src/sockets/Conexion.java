/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class Conexion implements Runnable {

    String ip = "192.168.1.3";
    int port = 11000;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
                chequearConexion();
            } catch (InterruptedException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean chequearConexion() {

        try {
            URL url = new URL("http://www.google.com");
            HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
            // trying to retrieve data from the source. If offline, this line will fail:
            Object objData = urlConnect.getContent();

        } 
        catch (MalformedURLException ex) {
            Logger.getLogger(Replicador.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NO TENGO INTERNET");
            sucursalapp.SucursalApp.tengoInternet = false;
        } 
        
        catch (java.net.UnknownHostException r){
            System.out.println("NO HAY INTERNET");
        }
        
        catch (java.net.NoRouteToHostException nr) {
            System.out.println("NO HAY INTERNET");
        }
        
        catch (IOException ex) {
            Logger.getLogger(Replicador.class.getName()).log(Level.SEVERE, null, ex);
        } 

        sucursalapp.SucursalApp.tengoInternet = true;
        enviarSignal();
        return true;

    }

    public void enviarSignal() {
        try {

            Socket cliente = new Socket(this.ip, this.port);

            OutputStream os = cliente.getOutputStream();

            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF("Estoy arriba");
            dos.writeUTF("sucursal");
            dos.flush();

            cliente.close();


        } catch (NoRouteToHostException nr) {
            System.out.println("No se encuentra el HOST " + this.ip + " " + this.port);

        } catch (ConnectException ce) {
            System.out.println("No se encuentra el HOST " + this.ip + " " + this.port);
        } catch (java.net.SocketException nr) {
            System.out.println("No se encuentra el HOST " + this.ip + " " + this.port);
        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}
