/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursalapp;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Diego Alienware
 */
public class Coordinador {
    
    private Element root;
    
    public void validarCoordinador(String sucursal)
    {
        boolean esCoordinador = buscar(sucursal);
    }
    
    
    /**
     * Permite la busqueda del coordinador en el xml de coordinador
     *
     * @param nombre
     * @return
     */
     public boolean buscar(String nombre) {
        SAXBuilder builder = new SAXBuilder(false);
        Document doc = null;
       try {
            doc = builder.build("nodoCoordinador.xml");
        } catch (JDOMException ex) {
            Logger.getLogger(Coordinador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Coordinador.class.getName()).log(Level.SEVERE, null, ex);
        }
        root = doc.getRootElement();
        Element raiz = doc.getRootElement();
        List listaInventario = raiz.getChildren("nodo");
        for (int i = 0; i < listaInventario.size(); i++) 
        {
            Element node = (Element) listaInventario.get(i);
            //System.out.println("imprimo en buscar: " + producto + " es " + producto.equals(node.getChildText("nombre")));
            if (nombre.equals(node.getChildText("nombre")) == true)     
            {
                SucursalApp.coordinador = "si";
                return true;
            }
        }   
        return false;
    }
    
}
