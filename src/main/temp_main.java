package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class temp_main {
    Scanner leer = new Scanner(System.in);
    
    public static void main (String args[]){
        compilar_archivos();
        boolean mvAl = moverArch("AnalizadorLexico.java");
        boolean mvAS = moverArch("Lexico.java");
        boolean mvSyn= moverArch("sym.java");
        
        // Ejecutar esto si se llegaron a hacer cambios:
        compilar_archivos();
        
        // Ejecutar parte léxica y sintáctico:
        //ejecutar();
        
        // Ejecutar parte semántica
        
    }

    public static void compilar_archivos(){
        String archLexico="";
        String archSintactico = "";
        
        System.out.println("\n se esta procesando el archivo default \n");
        archLexico="./src/main/Lexico.flex";
        archSintactico = "./src/main/Sintactico.cup";
        
        String[] alexico= {archLexico};
        String[] asintactico = {"-parser","AnalizadorSintactico", archSintactico};
        jflex.Main.main(alexico);
        try{
            java_cup.Main.main(asintactico);
        }catch(Exception ex){
            System.out.println(""+ex);
        };
        System.out.println("Generados Correctamente....."); 
    }
    
     public static boolean moverArch(String archNombre){
        boolean efectuado =false;
        File arch = new File(archNombre);
        if (arch.exists()) {
            System.out.println("\n Moviendo "+arch + "....");
            Path currentRelativePath = Paths.get("");
            String nuevoDir = currentRelativePath.toAbsolutePath().toString()
                    + File.separator 
                    + "src" 
                    + File.separator
                    + "main"
                    + File.separator
                    + arch.getName();
            File archViejo =new File(nuevoDir);
            archViejo.delete();
            if(arch.renameTo(new File(nuevoDir))){
                System.out.println("\n*** Generado"+ archNombre+"***\n");
            }else{
                System.out.println("\n*** no se movio"+ archNombre+"***\n");
            }
        }else {
            System.out.println("\n*** no existe el codigo***\n");
        }
        return efectuado;
    }
     
    // Parte léxica y sintáctica 
    public static void ejecutar() {
        System.out.println("*****Inicio Ejecuación*****");
        try {
            AnalizadorSintactico asin = new AnalizadorSintactico(new Lexico(new FileReader("src/Test/temporal.txt")));

            Object result = asin.parse().value;
            System.out.println("*****Resultados Finales******");
            
            //arbol
            System.out.println("*****Imprimiendo arbol******");
            limpiar("");
            Nodo root = AnalizadorSintactico.arbol;
            escribirArchivo(print(root));
            try{
                System.out.println("*****Arbol terminado******");
            }catch(Exception ex){
                System.out.println("*****Ha ocurrido un error******");
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    String msn1 = "";
    
  
    // Semántica
    public static void recorrido(){
        /*ArrayList<String> Lista = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            try {
                JSONObject json = jsonArray.getJSONObject(i);
                //Aquí se obtiene el dato y es guardado en una lista
                Lista.add(json.getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/
    }
    
    
    
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
     
}
