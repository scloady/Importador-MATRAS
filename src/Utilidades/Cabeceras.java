/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

/**
 *
 * @author Scloady
 */
public class Cabeceras {

    String cabeceraMARC1;
    String cabeceraMARC2;
    String cabeceraMARC3;
    String cabeceraResumen;
    
    /**
     * @Brief constructor por defecto
     */
    public Cabeceras() {
        cabeceraMARC1 = "Fecha,Hora,TempExt,TempMax,TempMin,HumExt,PtoRoc,VelViento,DirViento,RecViento,VelMaxViento,DirMaxViento,SensTermica,IndCalor,IndTHW,IndTHSW,Barometro,Lluvia,IntLluvia,RadSolar,EnerSolar,MaxRadSolar,IndUV,DosisUV,UVMax,GradDCalor,GradDFrio,TemInterior,HumInterior,ET,NumHoja1,TempHoja1,MuestViento,TxViento,RecepIIS,IntArc,";
        cabeceraMARC2 = "Fecha,Hora,TempExt,TempMax,TempMin,HumExt,PtoRoc,VelViento,DirViento,RecViento,VelMaxViento,DirMaxViento,SensTermica,IndCalor,IndTHW,IndTHSW,Barometro,Lluvia,IntLluvia,RadSolar,EnerSolar,MaxRadSolar,IndUV,DosisUV,UVMax,GradDCalor,GradDFrio,TemInterior,HumInterior,RocInterior,IndCalInterior,ET,NumHoja1,TempHoja1,MuestViento,TxViento,RecepIIS,IntArc,";
        cabeceraMARC3 = "Fecha,Hora,TempExt,TempMax,TempMin,HumExt,PtoRoc,VelViento,DirViento,RecViento,VelMaxViento,DirMaxViento,SensTermica,IndCalor,IndTHW,IndTHSW,Barometro,Lluvia,IntLluvia,RadSolar,EnerSolar,MaxRadSolar,IndUV,DosisUV,UVMax,GradDCalor,GradDFrio,TemInterior,HumInterior,RocInterior,IndCalInterior,ET,MuestViento,TxViento,RecepIIS,IntArc,";
        cabeceraResumen = "Dia,TempMed,TempMax,HoraTempMax,TempMin,HoraTempMin,GradDiaCalor,GradDiaFrio,Lluvia,VelVientoMed,VelVientoMax,HoraVelVientoMax,DireccViento,";
    }

    
    /**
     * @return cabecera a insertar en el fichero de importacion
     * @brief Clase parametrizada de la cabecera, devuelve cabecera into spanish
     * @param indicemes: indice del mes (0 enero, ... , 11 diciembre )
     * @param anio: anio a visualizar (5 anio 2005, 6 anio 2006, ...)
     */
    public String getCabecera(int indicemes, int anio) {
        indicemes +=1 ;
        if(anio>=5 && anio<=8){
            if (!((anio == 5 && indicemes < 7) || (anio == 8 && indicemes > 3))){
                return cabeceraMARC1;
            }
        }
        if((anio >= 8 && anio <= 10)){
            if (!((anio == 8 && indicemes < 4) || (anio == 10 && indicemes > 2))){
                return cabeceraMARC2;
            }
        }
        
        return cabeceraMARC3;
    }
    
    /**
     * @brief Método que devuelve la cabecera de resumen para insertar en el fichero de importación
     * @return Cabecera a insertar en el fichero de importación
     */
    public String getCabeceraResumen(){
        return cabeceraResumen;
    }
}
