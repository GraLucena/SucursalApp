/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursalapp;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Diego Alienware
 */
public class XMLSucursal {
    
    
    
    public boolean comprobarUsuarios(String username,String contrasena) {
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
                Element password = e.getChild("password");
                
                if (username.equals(login.getText())) 
                {
                    if (password.getText().equals(contrasena))
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
    
    public ArrayList<String> buscarIpsSucursales(String nombreArchivo){
        ArrayList<String> ips = new ArrayList<String>();
        try {            
            //System.out.println(username + " " + contrasena);
            SAXBuilder builder = new SAXBuilder(false);
            Document doc = builder.build(nombreArchivo);
            Element raiz = doc.getRootElement();
            List listaarchivos = raiz.getChildren("sucursal");
            Iterator k = listaarchivos.iterator();
            while (k.hasNext()) 
            {
                Element e = (Element) k.next();
                Element ip = e.getChild("ip");

                ips.add(ip.getText());
            }
           
            
        } catch (FileNotFoundException F) {
            System.out.println("Archivo XML no encontrado");
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return ips;
    }
}
