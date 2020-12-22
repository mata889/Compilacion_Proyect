package main;



import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class GUI extends javax.swing.JFrame {
    
    final JFileChooser fc = new JFileChooser();
    File fl;
    
     public GUI() {
        initComponents();
        fc.setCurrentDirectory(new File("./"));
        fc.setFileFilter(new FileNameExtensionFilter("Text", "txt"));
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jb_ejecutar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ta_Codigo = new javax.swing.JTextArea();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_output = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tf_Cuadrupla = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tf_TablaSimbolo = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        tf_ruta = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jb_file = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compilador");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Actions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 14))); // NOI18N

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Ejecutar Codigo");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jb_ejecutar.setBackground(new java.awt.Color(255, 255, 255));
        jb_ejecutar.setText("Ejecutar Archivo");
        jb_ejecutar.setEnabled(false);
        jb_ejecutar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_ejecutarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jb_ejecutar, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jb_ejecutar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Escribir codigo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 14))); // NOI18N

        ta_Codigo.setColumns(20);
        ta_Codigo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        ta_Codigo.setRows(5);
        jScrollPane2.setViewportView(ta_Codigo);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Output", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 14))); // NOI18N

        ta_output.setEditable(false);
        ta_output.setColumns(20);
        ta_output.setRows(5);
        jScrollPane1.setViewportView(ta_output);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Output", jPanel8);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tf_Cuadrupla.setEditable(false);
        tf_Cuadrupla.setColumns(20);
        tf_Cuadrupla.setLineWrap(true);
        tf_Cuadrupla.setRows(5);
        jScrollPane5.setViewportView(tf_Cuadrupla);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Cuadruplos", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        tf_TablaSimbolo.setColumns(20);
        tf_TablaSimbolo.setRows(5);
        jScrollPane3.setViewportView(tf_TablaSimbolo);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tabla de Simbolos", jPanel7);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccion de archivo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 14))); // NOI18N

        tf_ruta.setEditable(false);

        jLabel1.setText("Archivo");

        jb_file.setBackground(new java.awt.Color(255, 255, 255));
        jb_file.setText("SeleccionarArchivo");
        jb_file.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb_fileMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(tf_ruta)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jb_file)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jb_file)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    // ----------------------------------------------------------------
    // Botones del GUI
    // ----------------------------------------------------------------
    
    // Botón que selecciona un archivo que contenga el código a compilar
    private void jb_fileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_fileMouseClicked
        // Boton para seleccionar el archivo---------------------------------------------------------------------
        // Consigue el archivo
        temp_main tm = new temp_main();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
             fl = fc.getSelectedFile();
        try {
          // What to do with the file, e.g. display it in a TextArea
          this.ta_Codigo.read( new FileReader( fl.getAbsolutePath() ), null );
          this.tf_ruta.setText(fl.getAbsolutePath());
          String[] arguments = new String[] {""};
          arguments[0] = fl.getAbsolutePath();
          tm.main(arguments);
          ArrayList<Cuadruplo> cuadruplo = tm.cuadruplo;
          ArrayList<Variables> tabla = tm.tabla;
          String Simbolos = "";
          String Cuadruplos = "";
          for (Variables variable : tabla) {
            Simbolos += variable.toString() + "\n";
          }
          for (Cuadruplo cuad : cuadruplo) {
            Cuadruplos += cuad.toString() + "\n";
          }
          tf_Cuadrupla.setText(Cuadruplos);
          tf_TablaSimbolo.setText(Simbolos);
        } catch (Exception ex) {
          //System.out.println("problem accessing file"+file.getAbsolutePath());
        }
        } else {
            System.out.println("Cancelado.");
        }  
        
    }//GEN-LAST:event_jb_fileMouseClicked

    // Botón que ejecuta los pasos de compilación del archivo
    private void jb_ejecutarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb_ejecutarMouseClicked
        try{
            // Parte léxica y sintáctica:
            AnalizadorSintactico p = new AnalizadorSintactico(new Lexico(new FileReader(fl)));
            p.parse();
            if((Lexico.errores == 0)){ // && (AnalizadorSIntactico.syntacticErrors ==0)
                Nodo root = AnalizadorSintactico.arbol;
                limpiar("");
                escribirArchivo(print(root));
                ta_output.setText("Funciono");
            }else{
                 ta_output.setText("No funciono");
            }
            
            // Parte semántica:
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jb_ejecutarMouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        try{
            PrintWriter tempora=new PrintWriter("temporal.txt");
            tempora.println(ta_Codigo.getText());
            tempora.close();
            System.out.println("NO EXPLOTO");
        }catch(Exception e){
            ta_output.setText("No funciono. Si fue un error irrecuperable porfavor cerrar y abrir el visual otra vez");
            System.out.println("EXPLOTO");
        }
        temp_main tm2 = new temp_main();  
        String[] arguments = new String[] {""};
        arguments[0] = "temporal.txt";
        System.out.println("LLEGO HASTA AQUI");
        tm2.main(arguments);
        ArrayList<Cuadruplo> cuadruplo = tm2.cuadruplo;
        ArrayList<Variables> tabla = tm2.tabla;
        String Simbolos = "";
        String Cuadruplos = "";
        for (Variables variable : tabla) {
          Simbolos += variable.toString() + "\n";
        }
        for (Cuadruplo cuad : cuadruplo) {
          Cuadruplos += cuad.toString() + "\n";
        }
        tf_Cuadrupla.setText(Cuadruplos);
        tf_TablaSimbolo.setText(Simbolos);
//        try{
//            PrintWriter tempora=new PrintWriter("temporal.txt");
//            tempora.println(ta_Codigo.getText());
//            tempora.close();
//            
//             // Parte léxica y sintáctica:
//            AnalizadorSintactico p = new AnalizadorSintactico(new Lexico(new FileReader("temporal.txt")));
//            p.parse();
//            if((Lexico.errores == 0)){ // && (AnalizadorSIntactico.syntacticErrors ==0)
//                Nodo root = AnalizadorSintactico.arbol;
//                limpiar("");
//                escribirArchivo(print(root));
//                ta_output.setText("Funciono");
//            }else{
//                 ta_output.setText("No funciono. Si fue un error irecuperable porfavor cerrar y abrir el visual otra vez");
//            }
//            
//            // Parte semántica:
//            
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_jButton3MouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        //lenar tabla
    }//GEN-LAST:event_jTabbedPane1MouseClicked


    // ----------------------------------------------------------------
    // Funciones para la SEMÁNTICA del compilador.
    // ----------------------------------------------------------------
    
    
    
    // ---------------------------------------------------------------
    // Funciones secundarias para la creación del AST
    // ---------------------------------------------------------------
    
    
    // Esta función crea el archivo necesario para agregar el AST.
    public static void limpiar(String v){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try{
            fichero = new FileWriter("src/AST/AST.dot");
            pw = new PrintWriter(fichero);
            pw.print(v);
        }catch(Exception e){

        }finally{
            try{
                if(null!=fichero){
                    fichero.close();
                }
            }catch(Exception e){
                
            }
        }
    }
     
    // Esta función toma el nodo padre que contiene la estructura del árbol
    // y genera el formato al árbol.
    public static String print(Nodo padre ) {
        String pad = "";
        String cadena = "";
        for (Nodo hijo : padre.getHijos()) {
            if (hijo.valor != null) {
                cadena += "\"" + padre.getID() + "," + padre.getValor() + "\" -> \"" + hijo.idNodo + "," + hijo.valor + "\";";
                cadena += "\n";
                cadena += print(hijo);
            }
        }
        return cadena;
    }
    
    // Escribe la cadena proporcionada dentro de un archivo previamente creado.
    public static void escribirArchivo(String v){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try{
            fichero = new FileWriter("src/AST/AST.dot",true);
            pw = new PrintWriter(fichero);
            pw.print("digraph {\n");
            pw.print(v);
            pw.print("\n}");
        }catch(Exception e){
        }finally{
            try{
                if(null != fichero){
                    fichero.close();
                }
            }catch(Exception e){

            }
        }
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    
        public static void revision(Nodo nd){
        Nodo raiz = nd;
        if(raiz != null){
            String tipo = "";
            String id = "";
            for (Nodo hijo : raiz.getHijos()) {
                if(hijo.getValor().equals("var")){
                    tipo = hijo.getHijos().get(1).getValor();  
                }else if(hijo.getValor().equals("ID")){
                    id += hijo.getHijos().get(0).getValor();
                    if( verificarV(id) ){
                        //this.errores_semanticos.add("Error Semántico: La variable '" + id + "' ha sido declarada más de una vez");
                    }else{
                        if(tipo.equals("char")){
                            //this.offset += 1;
                        }else{
                            //int mod = 4 - (this.offset %4);
//                            if (mod == 4) {
//                                this.offset += 4;
//                            } else {
//                                this.offset += 4 + mod;
//                            }
                        }
                        //this.tabla.add(new Variable(tipo, id, this.ambito_actual, this.offset));
                    }
                    id = "";
                }    
            }
        }else if(nd.getValor().equals("Funcion")){
            String tipo_f = "";
            String id_f = "";
            //this.offset = 0;
            for (Nodo hijo : nd.getHijos()) { //450

            }
        }
    }
    //No se que es tabla aqui
    public static boolean verificarV(String variable){
        boolean ver = false;
        for (int i = 0; i < 0; i++) {

        }
        return ver;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jb_ejecutar;
    private javax.swing.JButton jb_file;
    private javax.swing.JTextArea ta_Codigo;
    private javax.swing.JTextArea ta_output;
    private javax.swing.JTextArea tf_Cuadrupla;
    private javax.swing.JTextArea tf_TablaSimbolo;
    private javax.swing.JTextField tf_ruta;
    // End of variables declaration//GEN-END:variables
}
