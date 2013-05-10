/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursalapp;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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

        try {
            int bytesRead;
            // Creo el servicio y escucho por un puerto args[0]
            ServerSocket servicio = new ServerSocket(Integer.valueOf(args[0]));
            Socket socketServicio = null;

            System.out.println("Estoy escuchando ----- por el puerto " + args[0]);
            //esperamos conexion
            boolean prueba = true;
            while (prueba) {

                socketServicio = servicio.accept();

                DataInputStream in = new DataInputStream(socketServicio.getInputStream());
                String fileName = in.readUTF();
                OutputStream output = new FileOutputStream(fileName);
                long size = in.readLong();
                byte[] buffer = new byte[1024];
                while (size > 0 && (bytesRead = in.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                    output.write(buffer, 0, bytesRead);
                    size -= bytesRead;
                }

                output.close();

                socketServicio.close();
            }

            servicio.close();
            System.out.println("Me apague");
        } catch (IOException ex) {
            System.out.println("Algo se daño:");
            ex.printStackTrace();
        }
    }
}
