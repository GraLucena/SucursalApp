package sockets;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import sucursalapp.Coordinador;
import sucursalapp.Historial;
import sucursalapp.Sucursal;
import sucursalapp.SucursalApp;

/**
 *
 * @author daniel
 */
public class Servidor implements Runnable {

    private String puertoEscucha;
    private String puetoEnvio;
    private String ipEnvio;
    private String ipsucursal = "192.168.1.22";

    public Servidor(String puertoEscucha, String puertoEnvio, String ipEnvio) {
        this.puertoEscucha = puertoEscucha;
        this.puetoEnvio = puertoEnvio;
        this.ipEnvio = ipEnvio;
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
            // Creo el servicio y escucho por un puertoEscucha args[0]
            ServerSocket servicio = new ServerSocket(Integer.valueOf(this.puertoEscucha));
            Socket socketServicio = null;

            System.out.println("Estoy escuchando ----- por el puerto " + this.puertoEscucha);
            //esperamos conexion
            boolean prueba = true;
            while (prueba) {

                socketServicio = servicio.accept();
                DataInputStream in = new DataInputStream(socketServicio.getInputStream());

                String estado = in.readUTF();
                String sender = in.readUTF();

                //Estoy recibiedo el mensaje desde Franquicia que esta arriba de nuevo.
                if (estado.equals("Estoy arriba")) {
                    
                    if (SucursalApp.sinConexion) {
                        System.out.println("esta arriba");
                        ArrayList<String> archivos = new Historial().leerHistorial();
                        //replico cada archivo que esta pendiente
                        for (int i = 0; i < archivos.size(); i++) {
                            if(sender.equals("sucursal"))
                            {
                                System.out.println("hola 1");
                                System.out.println(this.ipEnvio);
                                System.out.println(this.puetoEnvio);
                                System.out.println(ipsucursal);
                                new Thread(new Replicador(archivos.get(i), "10000", ipsucursal)).start();
                                
                            }
                            else{
                                System.out.println("hola 2");
                                System.out.println(this.ipEnvio);
                                System.out.println(this.puetoEnvio);
                                System.out.println(ipsucursal);
                                new Thread(new Replicador(archivos.get(i), this.puetoEnvio, this.ipEnvio)).start();}
                        }
                        SucursalApp.sinConexion = false;
                    }
                } else { //Estoy recibiendo un archivo xml

                    String fileName = estado;

                    //Si alguna sucursal inicio sesion.
                    if (!SucursalApp.nombresucursal.equals("")) {

                        OutputStream output = new FileOutputStream(fileName);
                        long size = in.readLong();
                        byte[] buffer = new byte[1024];
                        while (size > 0 && (bytesRead = in.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                            output.write(buffer, 0, bytesRead);
                            size -= bytesRead;
                        }
                        if (sucursalapp.SucursalApp.coordinador.equals("si")) {
                            //System.out.println("estoy en el while");
                            coordinador.trabajarComoCoordinador(fileName, sender);
                            //System.out.println("");

                        } else {

                            sucursal.trabajarComoSucursal();
                            System.out.println("no soy coordinador");

                        }
                        output.close();
                    } else { //No se que sucursal soy                        
                        System.out.println("No se que sucursal soy");
                        new Historial().escribirHistorial(fileName);

                    }
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
