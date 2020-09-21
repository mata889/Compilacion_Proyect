
package compila_flex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ejecuta {
    Scanner leer = new Scanner(System.in);
    
    public static void main (String args[]){
        ejecuta ejecutar=new ejecuta();
        ejecutar.archivo();
        ejecutar.ejecutar_analizador();
    }

    public void archivo() {
        String ruta = "archivo_texto.txt";
        File archivo = new File(ruta);
        BufferedWriter bw = null;
        
        if (archivo.exists()) {

                System.out.print("Reading");
            
        } else {
            System.out.print("nel");
        }
    }
    
     public void ejecutar_analizador() {
        System.out.println("*****Inicio Ejecuación*****");
        try {
            AnalizadorSIntactico asin = new AnalizadorSIntactico(
                    new proyecto(new FileReader("src/compila_flex/lenguaje.txt")));

            Object result = asin.parse().value;
            System.out.println("*****Resultados Finales******");
            
            //arbol
            System.out.println("*****Imprimiendo arbol******");
            limpiar("");
            Nodo root = AnalizadorSIntactico.arbol;
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
    
    public void escribirArchivo(String v){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try{
            fichero = new FileWriter("src/grafico.dot",true);
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
    public String print(Nodo padre ) {
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
    public void limpiar(String v){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try{
            fichero = new FileWriter("src/grafico.dot");
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
