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
    
    /**
     * @brief Constructor de la clase importador
     */
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
    
    /**
     * @brief Método que modifica un anio de inicio
     * @param _anioInicio: anio de inicio a insertar
     */
    public void setanioInicio(int _anioInicio){
        anioInicio = _anioInicio;
    }
    
    /**
     * @brief Método que modifica el atributo booleano de resumen
     * @param _resumen: atributo booleano que verifica si se va a almacenar el resumen de la estacion
     */
    public void setResumen(boolean _resumen){
        resumen = _resumen;
    }
    
    /**
     * @brief Método que obtiene el atributo de resumen
     * @return abributo de resumen
     */
    public boolean getResumen(){
        return resumen;
    }
    
    /**
     * @brief Método que modifica el campo de url
     * @param _url: url a modificar
     */
    public void setUrl(String _url){
        url = _url;
    }
    
    /**
     * @brief Método que modifica el campo de anio de fin
     * @param _anioFin: anio de fin a modificar
     */
    public void setanioFin(int _anioFin){
        anioFin = _anioFin;
    }
    
    /**
     * @brief Método que obtiene el anio de incio
     * @return anio de inicio de la importación
     */
    public int getanioInicio(){
        return anioInicio;
    }
    
    /**
     * @brief Método que obtiene el anio de fin
     * @return anio de fin de la importación
     */
    public int getanioFin(){
        return anioFin;
    }
    
    /**
     * @brief Método que modifica el mes de inicio de la importación
     * @param _mesInicio: mes de inicio de la importación
     */
    public void setmesInicio(String _mesInicio){
        mesInicio = _mesInicio;
    }
    
    /**
     * @brief Método que modifica el mes de fin de la importación
     * @param _mesFin: mes de fin de la importación
     */
    public void setmesFin(String _mesFin){
        mesFin = _mesFin;
    }
    
    /**
     * @brief Método que devuelve el mes de comienzo de la importación
     * @return mes de inicio de la importación
     */
    public String getmesInicio(){
        return mesInicio;
    }
    
    /**
     * @brief Método que devuelve el mes de fin de la importación
     * @return mes de fin de la importación
     */
    public String getmesFin(){
        return mesFin;
    }
    
    /**
     * @brief Método que setea la ruta en la que vamos a guardar la importación
     * @param _ruta: ruta a usar
     */
    public void setRuta(String _ruta){
        ruta = _ruta;
    }
    
    /**
     * @brief Método que obtiene la ruta en la que vamos a guardar la importación
     * @return ruta almacenada
     */
    public String getRuta(){
        return ruta;
    }
    
    /**
     * @brief Método que modifica el booleano de importación true-> si guarda, false en caso contrario
     * @param _log: booleano de log
     */
    public void setLogs(boolean _log){
        log = _log;
    }
    
    /**
     * @brief Método que obtiene el booleano de log
     * @return resultado de ese atributo
     */
    public boolean getLogs(){
        return log;
    }
    
    /**
     * @brief Método que obtiene la url de acceso de la importación
     * @return url de acceso
     */
    public String getUrl(){
        return url;
    }
    
    /**
     * @brief Método que se encarga de almacenar en un fichero el estado de las operaciones del asistente
     * @param data: datos a memorizar en el fichero
     */
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
