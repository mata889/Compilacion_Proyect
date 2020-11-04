package main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class temp_main {
    
    Scanner leer = new Scanner(System.in);
    static ArrayList<String> errores_semanticos = new ArrayList();
    static ArrayList<Variables> tabla = new ArrayList();
    static String ambito_actual = "Start";
    static int offset = 0;
    
    public static void main (String args[]){
        /*boolean mvAl = moverArch("AnalizadorLexico.java");
        boolean mvAS = moverArch("Lexico.java");
        boolean mvSyn= moverArch("sym.java");*/
        
        // Ejecutar esto si se llegaron a hacer cambios:
        // compilar_archivos();
        
        // Ejecutar parte léxica y sintáctico:
       Nodo root = ejecutar();
        
        // Ejecutar parte semántica
       recorrido(root);
        
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
    public static Nodo ejecutar() {
        Nodo root = null;
        try {
            AnalizadorSintactico asin = new AnalizadorSintactico(new Lexico(new FileReader("src/Test/prueba.txt")));
            asin.parse();
            //arbol
            limpiar("");
            root = AnalizadorSintactico.arbol;
            escribirArchivo(print(root));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return root;
    }    
  
    // Semántica
    public static void recorrido(Nodo root){
        if (root!=null){
            Nodo body = root.getHijos().get(0); // Aquí entro al body de start.
            for (Nodo hijo : body.getHijos()) { // Se recorren los hijos del body
                     
                // Aquí se encuentran declaraciones de variables con o sin asignación
                if(hijo.getValor().equals("Proposicion")){
                    String tipo="", id="";
                    for (Nodo h : hijo.getHijos().get(0).getHijos()) { //Se toman los hijos de la declaración_simple
                        if(h.getValor().equals("Caracter")){ //En caso de que sea un caracter
                            tipo = "caracter";
                            offset += 1;
                        }else if (h.getValor().equals("Booleana")){//En caso de que sea un booleano
                            tipo = "booleano";
                            offset += 1;
                        }else if (h.getValor().equals("NUM")){//En caso de que sea un numero
                            tipo = "numero";
                            int mod = 4 - (offset % 4);
                            if (mod == 4) {
                                offset += 4;
                            } else {
                                offset += 4 + mod;
                            }
                        }else if(h.getValor().equals("ID")){ //En caso de que sea un ID se toma
                            id = h.getHijos().get(0).getValor();
                            if(!verificar_variable(id, ambito_actual)){ // Se verifica en la tabla de símbolos
                                if (hijo.getHijos().get(0).getHijos().get(3).getValor().equals("asignacion")){
                                    String valor = hijo.getHijos().get(0).getHijos().get(3).getHijos().get(0).getValor();
                                    if(tipo.equals("caracter") && valor.equals("Valores-caracter")){
                                        tabla.add(new Variables(tipo, id, ambito_actual, offset));
                                    }else if(tipo.equals("numero") && valor.equals("Valores-num")){
                                        tabla.add(new Variables(tipo, id, ambito_actual, offset));
                                    }else if(tipo.equals("booleano") && valor.equals("Valores-bool")){
                                        tabla.add(new Variables(tipo, id, ambito_actual, offset));
                                    }else{
                                        errores_semanticos.add("Error semántico: Se ha asignado un valor erróneo a la a variable "+id+"");
                                    }
                                }else if (hijo.getHijos().get(0).getHijos().get(3).getValor().equals(";")){
                                    tabla.add(new Variables(tipo, id, ambito_actual, offset));
                                }
                            }else{
                                errores_semanticos.add("Error semántico: La variable "+id+" ha sido declarada con anterioridad");
                            }
                        }
                    }
                }
                
                // En caso de que sea otra cosa
                else if(hijo.getValor().equals("")){
                
                }
                
                
            }
        }else{
            System.out.println("ERROR: Root nulo, verificar si el archivo proporcionado es correcto");
        }
        
        System.out.println("LA TABLA DE SÍMBOLOS ES: ");
        for (Variables variable : tabla) {
            System.out.println(variable.toString());
        }
        
        System.out.println("LOS ERRORES SEMÁNTICOS SON: ");
        for (String error : errores_semanticos) {
            System.out.println(error);
        }   
    }
    
    public static boolean verificar_variable(String variable, String ambito_actual) {
        boolean ret = false;
        for (int i = 0; i < tabla.size(); i++) {
            if (variable.equals(tabla.get(i).getId()) && ambito_actual.equals(tabla.get(i).getAmbito())) {
                ret = true;
            }
        }
        return ret;
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
        }catch(IOException e){

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
