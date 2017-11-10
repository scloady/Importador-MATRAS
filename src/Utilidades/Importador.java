/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Scloady
 */
public class Importador {
    boolean resumen;
    int anioInicio;
    String mesInicio;
    int anioFin;
    String mesFin;
    String ruta;
    boolean log;
    String url = "http://www.ujaen.es/dep/fisica/estacion/Datos%20dia%20a%20dia/";
    
    public Importador(){
        resumen = false;
        anioInicio = anioFin = 2005;
        mesInicio = mesFin = "enero";
        if(System.getProperty("os.name").contains("Windows")){
            ruta = System.getProperty("user.home")+"\\Desktop\\IMPORTADOR\\";
        }else{
            ruta = System.getProperty("user.home")+"/Escritorio/IMPORTADOR/";
        }
        log = false;
    }
    
    public void setanioInicio(int _anioInicio){
        anioInicio = _anioInicio;
    }
    
    public void setResumen(boolean _resumen){
        resumen = _resumen;
    }
    
    public boolean getResumen(){
        return resumen;
    }
    
    public void setUrl(String _url){
        url = _url;
    }
    
    public void setanioFin(int _anioFin){
        anioFin = _anioFin;
    }
    
    public int getanioInicio(){
        return anioInicio;
    }
    
    public int getanioFin(){
        return anioFin;
    }
    
    public void setmesInicio(String _mesInicio){
        mesInicio = _mesInicio;
    }
    
    public void setmesFin(String _mesFin){
        mesFin = _mesFin;
    }
    
    public String getmesInicio(){
        return mesInicio;
    }
    
    public String getmesFin(){
        return mesFin;
    }
    
    public void setRuta(String _ruta){
        ruta = _ruta;
    }
    
    public String getRuta(){
        return ruta;
    }
    
    public void setLogs(boolean _log){
        log = _log;
    }
    
    public boolean getLogs(){
        return log;
    }
    
    public String getUrl(){
        return url;
    }
    
    public void setWritter(String data){
        if(getLogs() == true){
            //BufferedWriter fichero = null;
            String linea;
            try (
                FileWriter fichero = new FileWriter(getRuta()+"/MATRAS_LOG.txt", true)) {
                linea = data;
                //Escribimos
                fichero.write(linea+"\n");
                //Cerramos el fichero
            }catch (IOException ex) {
                System.out.println("\nNo se encontro el archivo de datos para escribir en él!!!");
            }
        }
    }
    
    
}
