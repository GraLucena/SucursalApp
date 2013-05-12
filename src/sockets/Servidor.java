package sockets;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import sucursalapp.Coordinador;
import sucursalapp.Sucursal;
import sucursalapp.SucursalApp;

/**
 *
 * @author daniel
 */
public class Servidor implements Runnable {

    private String puerto;

    public Servidor(String puerto) {
        this.puerto = puerto;
    }

    @Override

    public void run() {
        this.recibir();
    }

    public void recibir() {
        Coordinador coordinador = new Coordinador();
        Sucursal sucursal = new Sucursal();

        try {
            int bytesRead;
            // Creo el servicio y escucho por un puerto args[0]
            ServerSocket servicio = new ServerSocket(Integer.valueOf(this.puerto));
            Socket socketServicio = null;

            System.out.println("Estoy escuchando ----- por el puerto " + this.puerto);
            //esperamos conexion
            boolean prueba = true;
            while (prueba) {

                socketServicio = servicio.accept();
                DataInputStream in = new DataInputStream(socketServicio.getInputStream());

                String estado = in.readUTF();
                
                if (estado.equals("Estoy arriba")) {
                    if (SucursalApp.sinConexion) {
                        System.out.println("REPLICA LO PENDIENTE");

                        //ArrayList<String> archivos = new Historial().leerHistorial();
                        //replico cada archivo que esta pendiente
                        //for (int i = 0; i < archivos.size(); i++) {
                        //  new Thread(new Replicador(archivos.get(i))).start();
                        //}
                    }
                } else {
                    String fileName = estado;

                    OutputStream output = new FileOutputStream(fileName);
                    long size = in.readLong();
                    byte[] buffer = new byte[1024];
                    while (size > 0 && (bytesRead = in.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                        output.write(buffer, 0, bytesRead);

                        size -= bytesRead;
                    }
                    if (sucursalapp.SucursalApp.coordinador.equals("si")) {
                        System.out.println("estoy en el while");
                        coordinador.trabajarComoCoordinador(fileName);
                        System.out.println("");
                        
                    } else {
                        sucursal.trabajarComoSucursal();
                        System.out.println("no soy coordinador");
                    }
                    output.close();
                }

                socketServicio.close();
            }

            servicio.close();
            System.out.println("Me apague");
        } catch (IOException ex) {
            System.out.println("Algo se da√±o:");
            ex.printStackTrace();
        }
    }
}
