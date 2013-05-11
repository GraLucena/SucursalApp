package sockets;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

/**
 *
 * @author daniel
 */
public class Replicador implements Runnable {

    private Socket cliente;
    private int puerto;
    private String ip;
    private String nombreArchivo;

//    public Replicador(String nombreArchivo) {
//        XMLNodoCoordinador nodoCoord = new XMLNodoCoordinador();
//        Nodo coordinador = nodoCoord.getCoordinador();
//        this.puerto = Integer.parseInt(coordinador.getPuerto());
//        this.ip = coordinador.getIp();
//        this.nombreArchivo = nombreArchivo;
//    }
    public Replicador(String nombreArchivo, String puerto, String ip) {
        this.puerto = Integer.parseInt(puerto);
        this.ip = ip;
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public void run() {
        if (!this.nombreArchivo.equals("Estoy arriba")) {
            this.enviarXML();
        } else {
            this.enviarSignal();
        }

    }

    public void enviarXML() {
        try {

            this.cliente = new Socket(this.ip, this.puerto);

            // enviar archivo  
            File myFile = new File(nombreArchivo);
            byte[] mybytearray = new byte[(int) myFile.length()];

            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);

            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(mybytearray, 0, mybytearray.length);

            OutputStream os = this.cliente.getOutputStream();

            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(myFile.getName());
            dos.writeLong(mybytearray.length);
            dos.write(mybytearray, 0, mybytearray.length);
            dos.flush();

            this.cliente.close();

        } catch (ConnectException ce) {
            System.out.println("No se encuentra el HOST");
//            SucursalApp.sinConexion = true;
//            new Historial().escribirHistorial(nombreArchivo);

        } catch (FileNotFoundException nf) {
            System.out.println("No se ha encontrado el archivo");
            nf.printStackTrace();

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void enviarSignal() {
        try {

            this.cliente = new Socket(this.ip, this.puerto);

            OutputStream os = this.cliente.getOutputStream();

            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF("Estoy arriba");
            dos.flush();

            this.cliente.close();

        } catch (ConnectException ce) {
            System.out.println("No se encuentra el HOST");
//            SucursalApp.sinConexion = true;
//            new Historial().escribirHistorial(nombreArchivo);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
