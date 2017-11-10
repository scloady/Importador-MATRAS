/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Utilidades.Importador;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


/**
 *
 * @author Scloady
 */
public class Exec_importer extends javax.swing.JFrame {

    /**
     * Creates new form Inicio
     * @param _imp: Objeto de importacion
     */
    public Exec_importer(Utilidades.Importador _imp) {
        this.imp = _imp;
        for(int i=0;i<meses.length;i++){
            if(meses[i].contains(imp.getmesInicio())){
                indexMesInicio = i;
            }
            if(meses[i].contains(imp.getmesFin())){
                indexMesFin = i;
            }
        }
        initComponents();
        inicio();
    }
    
    private void inicio(){
        //Creamos un Thread para el lanzamiento en background
        final Thread t;
        //Inicializamos
        t = new Thread(new Runnable() {
            //Implementamos el método run()
            @Override
            public void run() {
                //Permite mostrar el valor del progreso
                jProgressBar1.setStringPainted(true);
                int x = 1;
                //Creamos el folder de trabajo
                File carpeta = new File(imp.getRuta());
                if(!carpeta.exists() || !carpeta.isDirectory()){
                    carpeta.mkdir();
                }
                //Guardamos parametros de configuracion e iniciamos el verbose
                imp.setWritter(">>>>> PARAMETROS DE CONFIGURACIÓN PARA LA IMPORTACIÓN <<<<<");
                imp.setWritter("Año de inicio: "+Integer.toString(imp.getanioInicio()));
                imp.setWritter("Año de fin: "+Integer.toString(imp.getanioFin()));
                imp.setWritter("Mes de inicio: "+imp.getmesInicio());
                imp.setWritter("Mes de inicio: "+imp.getmesFin());
                imp.setWritter("Ruta de guardado: "+imp.getRuta());
                imp.setWritter("Enlace de patron: "+imp.getUrl());
                imp.setWritter("Generación de log: "+imp.getLogs());
                if(imp.getResumen()==true){
                    imp.setWritter("Modo de uso: Resumen");
                }else{
                    imp.setWritter("Modo de uso: Completo");
                }
                imp.setWritter(">>>>> FIN DE ZONA DE PARAMETROS <<<<<");
                imp.setWritter("");
                imp.setWritter("");
                imp.setWritter("############ MODO VERBOSE ACTIVADO ############");
                imp.setWritter("");
                //Fin de importacion de log
                imp.setWritter("************** Creando carpeta de trabajo*************");
                imp.setWritter("Ruta: "+carpeta.toString());
                

                //Creamos un bucle
                for(int i=imp.getanioInicio()-2000;i<=imp.getanioFin()-2000;i++){
                    //Crear subcarpeta con el año
                    File subcarpeta = null;
                    if(System.getProperty("os.name").contains("Windows")){
                        subcarpeta = new File(imp.getRuta()+"\\"+(i+2000));
                    }else{
                        subcarpeta = new File(imp.getRuta()+"/"+(i+2000));
                    }
                    if(!subcarpeta.exists() || !subcarpeta.isDirectory()){
                        subcarpeta.mkdir();
                        imp.setWritter("************** Creando subcarpeta de trabajo*************");
                        imp.setWritter("Ruta: "+subcarpeta.toString());
                    }
                    imp.setWritter("--- IMPORTANDO A LA SUBCARPETA: "+(i+2000)+" ---");
                    for(int j=indexMesInicio;j<=indexMesFin;j++){
                        //Obtenemos los recursos de internet con el patron
                        try{
                            //System.out.println(imp.getUrl()+meses[j]+String.format("%02d", i)+".txt");
                            URL url = null;
                            if(imp.getResumen() == true){
                                url = new URL(imp.getUrl()+meses[j]+String.format("%02d", i)+"NOAAMO.txt");
                            }else{
                                if(meses[j].contains("marzo") && (i+2000) == 2007){
                                    url = new URL(imp.getUrl()+meses[j]+"%20"+String.format("%02d", i)+".txt");
                                }else{
                                    url = new URL(imp.getUrl()+meses[j]+String.format("%02d", i)+".txt");
                                }
                            }
                            imp.setWritter("Obteniendo fichero via URL. Ruta: "+url.toString());
                            URLConnection urlCon = url.openConnection();
                            imp.setWritter("Abriendo pasarela de E-S.");

                            InputStream is = urlCon.getInputStream();
                            FileOutputStream fos = null;
                            if(imp.getResumen()==true){
                                fos = new FileOutputStream(subcarpeta+"/"+meses[j]+String.format("%02d", i)+"-resumen.txt");
                            }else{
                                fos = new FileOutputStream(subcarpeta+"/"+meses[j]+String.format("%02d", i)+".txt");
                            }

                            int b = 0;
                            while (b != -1) {
                              b = is.read();
                              if (b != -1)
                                fos.write(b);
                            }

                            is.close();
                            fos.close();
                            imp.setWritter("Fichero importado. Ruta "+subcarpeta+"/"+meses[j]+String.format("%02d", i)+".txt");
                            imp.setWritter("");

                        }catch(Exception e){
                            try{
                                //System.out.println(imp.getUrl()+meses[j]+String.format("%02d", i)+".txt");
                                URL url = null;
                                if(imp.getResumen() == true){
                                    url = new URL(imp.getUrl()+meses[j]+String.format("%02d", i)+"NOAAMO.TXT");
                                }else{
                                    if(meses[j].contains("marzo") && (i+2000) == 2007){
                                        url = new URL(imp.getUrl()+meses[j]+"%20"+String.format("%02d", i)+".TXT");
                                    }else{
                                        url = new URL(imp.getUrl()+meses[j]+String.format("%02d", i)+".TXT");
                                    }
                                }   
                                imp.setWritter("Obteniendo fichero via URL. Ruta: "+url.toString());
                                URLConnection urlCon = url.openConnection();
                                imp.setWritter("Abriendo pasarela de E-S.");
                                
                                InputStream is = urlCon.getInputStream();
                                FileOutputStream fos = null;
                                if(imp.getResumen()==true){
                                    fos = new FileOutputStream(subcarpeta+"/"+meses[j]+String.format("%02d", i)+"-resumen.txt");
                                }else{
                                    fos = new FileOutputStream(subcarpeta+"/"+meses[j]+String.format("%02d", i)+".txt");
                                }
                                int b = 0;
                                while (b != -1) {
                                  b = is.read();
                                  if (b != -1)
                                    fos.write(b);
                                }

                                is.close();
                                fos.close();
                                imp.setWritter("Fichero importado. Ruta "+subcarpeta+"/"+meses[j]+String.format("%02d", i)+".txt");
                                imp.setWritter("");

                            }catch(Exception err){
                                imp.setWritter("El recurso "+meses[j]+String.format("%02d", i)+".txt"+" no ha podido ser localizado!!");
                                imp.setWritter("");
                                
                            }
                        }
                        jProgressBar1.setValue(x);
                        x++;
                    }
                    imp.setWritter("--- FIN DE IMPORTADO A LA SUBCARPETA: "+(i+2000)+" ---");
                    imp.setWritter("");
                }
                imp.setWritter(" --- --- --- IMPORTACIÓN AL EQUIPO FINALIZADA --- --- ---");
                //Pruebas de ejecución tras terminar
                Mensaje_Final abrir = new Mensaje_Final(imp);
                abrir.show(true);
                Exec_importer.this.show(false);
            }
        });
        t.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar(0,((imp.getanioFin()-imp.getanioInicio())+1)*((indexMesFin-indexMesInicio)+1));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asistente de importación MATRAS");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setText("Ejecutando el importador");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("<html>Espere mientras el asistente realiza la importación al equipo.</html>");

        jButton1.setText("MINIMIZAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        /*****************************************************/
        /*(imp.getanioFin()-imp.getanioInicio())*(indexMesFin-indexMesInicio)*/
        //jProgressBar1.setValue(1);
        /*****************************************************/

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @brief Método de acción para el botón 1 (Minimizar)
     * @param evt: Evento de acción
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @brief Funcion principal de sección de ejecución de la importación
     */
    public static void main() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Exec_importer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Exec_importer(new Utilidades.Importador()).setVisible(true);
        });
        
        
    }
    Utilidades.Importador imp = new Importador();
    int indexMesInicio = 0;
    int indexMesFin = 0;
    String[] meses = {"enero", "febrero","marzo","abril","mayo","junio","julio","agosto","septiembre","octubre","noviembre","diciembre"};
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
