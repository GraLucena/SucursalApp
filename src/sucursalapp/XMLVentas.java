/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursalapp;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import ventanas.GestionInventario;
import ventanas.GestionVentas;

/**
 *
 * @author gracielalucena
 */
public class XMLVentas {
 
    public void listarVentas(GestionVentas ventana, String archivo) {
        try {
            SAXBuilder builder = new SAXBuilder(false);
            //System.out.println(usuario);
            Document doc = builder.build("registroventas.xml");
            Element raiz = doc.getRootElement();
            List listaSucursal = raiz.getChildren("venta");
            Iterator k = listaSucursal.iterator();
            while (k.hasNext()) {
                int i = 0, j = 0;
                Element e = (Element) k.next();
                Element nombre = e.getChild("nombre");
                Element cantidad = e.getChild("cantidad");
                Element costo = e.getChild("costo");
                Element fecha = e.getChild("fecha");
               
                // if (archivo.equals(user.getText())) {
                ventana.agregarfila(nombre.getText(), cantidad.getText(), costo.getText(), fecha.getText());
                //}

            }
        } catch (FileNotFoundException F) {
            System.out.println("Archivo XML no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
