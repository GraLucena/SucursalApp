/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursalapp;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

/**
 *
 * @author Diego Alienware
 */
public class InventarioFranquicia {
 
public void CrearInventario()
{
    int cont = 0;
    SAXBuilder builder = new SAXBuilder();
    //XMLInventario inventario = new XMLInventario();
    File xmlFile = new File("listaProductos.xml");
    
    try {
 
		Document document = (Document) builder.build(xmlFile);
		Element rootNode = document.getRootElement();
		List list = rootNode.getChildren("producto");
 
		for (int i = 0; i < list.size(); i++) 
                {
 
		   Element node = (Element) list.get(i);
 
		   node.getChildText("nombre");
		   node.getChildText("descripcion");
		   node.getChildText("costo");
		   node.getChildText("status");
                   String a = node.getChildText("status");
                    
                    
                    if(node.getChildText("status").equals("Activo") == true)
                    {
                        if(cont == 1){
                         //inventario.agregarProductoInventario(node.getChildText("nombre"), node.getChildText("descripcion"), node.getChildText("costo"));
                        }else
                        {
                         //inventario.crearProductoInventario(node.getChildText("nombre"), node.getChildText("descripcion"), node.getChildText("costo"));
                         cont = 1;
                        } 
                         
                    }
                 }
 
	  } catch (IOException io) {
		System.out.println(io.getMessage());
	  } catch (JDOMException jdomex) {
		System.out.println(jdomex.getMessage());
	  }
    
}



}