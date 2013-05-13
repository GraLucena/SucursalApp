package sucursalapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class Historial {

    private ArrayList<String> historial;
    
    public Historial(){
        historial = new ArrayList<String>();
    }

    public void escribirHistorial(String nombreArchivo) {
        File f;
        boolean existe;

        if (!existeAccion(nombreArchivo)) {

            if (!new File("historial.txt").exists()) {
                f = new File("historial.txt");
                existe = false;
            } else {
                existe = true;
            }

            try {

                FileWriter w = new FileWriter("historial.txt", existe);
                BufferedWriter bw = new BufferedWriter(w);
                PrintWriter wr = new PrintWriter(bw);
                wr.println(nombreArchivo);

                wr.close();
                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<String> leerHistorial() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File("historial.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                //System.out.println(linea);
                historial.add(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return historial;
    }

    public boolean existeAccion(String nombre) {
        boolean flag = false;
        ArrayList<String> arreglo = leerHistorial();
        if (arreglo.contains(nombre)) {
            flag = true;
        }
        return flag;
    }
}
