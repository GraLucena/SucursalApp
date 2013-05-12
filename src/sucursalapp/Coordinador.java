/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursalapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import sockets.*;
/**
 *
 * @author Diego Alienware
 */
public class Coordinador {
    
    private Element root;
    
    public void validarCoordinador(String sucursal)
    {
        boolean esCoordinador = buscarCoordinador(sucursal);
    }
    
    
    /**
     * Permite la busqueda del coordinador en el xml de coordinador
     *
     * @param nombre
     * @return
     */
     public boolean buscarCoordinador(String nombre) {
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
                System.out.println("soy coordinador");
                return true;
            }
        }   
        return false;
    }
     
     public String buscarSucursal(String nombre) {
        SAXBuilder builder = new SAXBuilder(false);
        Document doc = null;
       try {
            doc = builder.build("registroSucursales.xml");
        } catch (JDOMException ex) {
            Logger.getLogger(Coordinador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Coordinador.class.getName()).log(Level.SEVERE, null, ex);
        }
        root = doc.getRootElement();
        Element raiz = doc.getRootElement();
        List listaInventario = raiz.getChildren("sucursal");
        for (int i = 0; i < listaInventario.size(); i++) 
        {
            Element node = (Element) listaInventario.get(i);
            //System.out.println("imprimo en buscar: " + producto + " es " + producto.equals(node.getChildText("nombre")));
            if (nombre.equals(node.getChildText("login")) == true)     
            {
                String ip = node.getChildText("ip");
                return ip;
            }
        }   
        return "not found";
    }
     
     public void trabajarComoCoordinador(String archivo) 
     {        
            File f = new File(archivo);
            if (f.exists() == true) 
            {
                replicarSucursales(archivo);
            }    
            else
                {
                    System.out.println("no existe");
                }      
         
         System.out.println("ahora voy a borrar");
//         try
//         {
//    		File file = new File("sucursal2.xml");
// 
//    		if(file.delete())
//    			System.out.println(file.getName() + " is deleted!");
//    		else
//    			System.out.println("Delete operation is failed.");
// 
//    	 }catch(Exception e)
//         {
// 
//    		e.printStackTrace();
//         }
         
         
     }
     
     public boolean validarEnvio(String username,String ip) {
        try {
            //System.out.println(username + " " + contrasena);
            SAXBuilder builder = new SAXBuilder(false);
            Document doc = builder.build("registroSucursales.xml");
            Element raiz = doc.getRootElement();
            List listaarchivos = raiz.getChildren("sucursal");
            Iterator k = listaarchivos.iterator();
            while (k.hasNext()) 
            {
                Element e = (Element) k.next();
                Element login = e.getChild("login");
                Element xmlip = e.getChild("ip");
                
                if (username.equals(login.getText())) 
                {
                    if (xmlip.getText().equals(ip))
                    {
                        return true;
                    }
                }
            }
            return false;
            
        } catch (FileNotFoundException F) {
            System.out.println("Archivo XML no encontrado");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //return Varchivo;
    }
     
     public void replicarSucursales(String xmlsucursal)
     {
         System.out.println(xmlsucursal);
         String[] sucursal;
         String spliter = "\\.";
         sucursal = xmlsucursal.split(spliter);
         System.out.println(sucursal[0]);
         String ipSucursal = buscarSucursal(sucursal[0]);
         System.out.println(ipSucursal);
         System.out.println("estoy validando el envio" + validarEnvio(sucursal[0],ipSucursal));
         if(validarEnvio(sucursal[0],ipSucursal) == true)
         {
            Replicador replicador = new Replicador(xmlsucursal, ipSucursal);
            new Thread(replicador).start();       
         }
         else
             System.out.println("mal envio");
     }
    
     
     
}
