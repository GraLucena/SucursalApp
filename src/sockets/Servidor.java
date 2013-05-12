package sockets;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author daniel
 */
public class Servidor implements Runnable{
    
    private String puerto;
    
    public Servidor(String puerto){
        this.puerto = puerto;
    }
    
    @Override
    public void run(){
        this.recibir();
    }
    
    public void recibir(){
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
                System.out.println("alguien se conecto");
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
