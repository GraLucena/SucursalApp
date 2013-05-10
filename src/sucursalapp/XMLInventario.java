/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursalapp;

import ventanas.GestionInventario;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.JDOMParseException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Diego Alienware
 */
public class XMLInventario {
    
    public boolean crearProductoInventario(String nombre, String descripcion, String costo) {
        Element elProducto, elNombre, laDescripcion, elPrecio, laCantidad;
        SAXBuilder builder = new SAXBuilder();
        try{ 
            Element root = new Element("root");
            Document doc = new Document(root);
            doc.setRootElement(root);
            
            Element producto = new Element("producto");
            // Creamos una nueva etiqueta
            elProducto = new Element("producto");
            elNombre = new Element("nombre");
            laDescripcion = new Element("descripcion");
            elPrecio = new Element("costo");
            laCantidad = new Element("cantidad");

            root.addContent(elProducto);
            elProducto.addContent(elNombre);
            elNombre.addContent(nombre);
            elProducto.addContent(laDescripcion);
            laDescripcion.addContent(descripcion);
            elProducto.addContent(elPrecio);
            elPrecio.addContent(costo);
            elProducto.addContent(laCantidad);
            laCantidad.addContent("20");

                Format format = Format.getPrettyFormat();
                /* Se genera un flujo de salida de datos XML */
                XMLOutputter out = new XMLOutputter(format);
                /* Se asocia el flujo de salida con el archivo donde se guardaran los datos */
                FileOutputStream file = new FileOutputStream("inventarioProductos.xml");
                /* Se manda el documento generado hacia el archivo XML */
                out.output(doc, file);
                System.out.println("Guardado crear");
                /* Se limpia el buffer ocupado por el objeto file y se manda a cerrar el archivo */
                file.flush();
                file.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        return true;
    }
    
    public void listarInventario(GestionInventario ventana, String archivo) {
        try {
            SAXBuilder builder = new SAXBuilder(false);
            //System.out.println(usuario);
            Document doc = builder.build("inventarioProductos.xml");
            Element raiz = doc.getRootElement();
            List listaSucursal = raiz.getChildren("producto");
            Iterator k = listaSucursal.iterator();
            while (k.hasNext()) {
                int i = 0, j = 0;
                Element e = (Element) k.next();
                Element nombre = e.getChild("nombre");
                Element costo = e.getChild("costo");
                Element cantidad = e.getChild("cantidad");
                // if (archivo.equals(user.getText())) {
                ventana.agregarfila(nombre.getText(), costo.getText(), cantidad.getText());
                //}

            }
        } catch (FileNotFoundException F) {
            System.out.println("Archivo XML no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean agregarProductoInventario(String nombre, String descripcion, String costo) {
        Document doc;
        Element root, elProducto, elNombre, laDescripcion, elPrecio, laCantidad;
        SAXBuilder builder = new SAXBuilder();
        try {
            doc = builder.build("inventarioProductos.xml");
            root = doc.getRootElement();
            // Creamos una nueva etiqueta
            elProducto = new Element("producto");
            elNombre = new Element("nombre");
            laDescripcion = new Element("descripcion");
            elPrecio = new Element("costo");
            laCantidad = new Element("cantidad");

            root.addContent(elProducto);
            elProducto.addContent(elNombre);
            elNombre.addContent(nombre);
            elProducto.addContent(laDescripcion);
            laDescripcion.addContent(descripcion);
            elProducto.addContent(elPrecio);
            elPrecio.addContent(costo);
            elProducto.addContent(laCantidad);
            laCantidad.addContent("20");

            try {
                Format format = Format.getPrettyFormat();
                /* Se genera un flujo de salida de datos XML */
                XMLOutputter out = new XMLOutputter(format);
                /* Se asocia el flujo de salida con el archivo donde se guardaran los datos */
                FileOutputStream file = new FileOutputStream("inventarioProductos.xml");
                /* Se manda el documento generado hacia el archivo XML */
                out.output(doc, file);
                /* Se limpia el buffer ocupado por el objeto file y se manda a cerrar el archivo */
                file.flush();
                file.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (JDOMParseException e) {
            System.out.println("Error loading XML file - The file is empty1");
            e.printStackTrace();
        } catch (JDOMException e) {
            System.out.println("Error loading XML file - The file is empty2");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error loading XML file - The file is empty3");
            e.printStackTrace();
        }

        return true;
    }
    
}
