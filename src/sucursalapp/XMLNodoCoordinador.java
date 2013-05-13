package sucursalapp;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author daniel
 */
public class XMLNodoCoordinador {

    public Nodo getCoordinador() {
        try {
            SAXBuilder builder = new SAXBuilder(false);

            Document doc = builder.build("nodoCoordinador.xml");
            Element raiz = doc.getRootElement();
            Element nodo = raiz.getChild("nodo");
            Element nombre = nodo.getChild("nombre");
            Element ip = nodo.getChild("ip");
            Element puerto = nodo.getChild("puerto");            
            
            Nodo coordinador = new Nodo();
            coordinador.setIp(ip.getText());
            coordinador.setPuerto(puerto.getText());
            coordinador.setSucursal(nombre.getText());
            
            return coordinador;

        } catch (FileNotFoundException F) {
            System.out.println("Archivo XML no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
