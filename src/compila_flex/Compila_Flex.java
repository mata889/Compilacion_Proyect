
package compila_flex;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Compila_Flex {

  
    public static void main(String[] args) {
        Compila_Flex compilar= new Compila_Flex();
        compilar.compilar_archivos();
        boolean mvAl = moverArch("proyecto.java");
        boolean mvAS = moverArch("AnalizadorSIntactico.java");
        boolean mvSyn= moverArch("sym.java");
    }
    
    public void compilar_archivos(){
        String archLexico="";
        String archSintactico = "";
        
        System.out.println("\n se esta procesando el archivo default \n");
        archLexico="./src/compila_flex/lexema.flex";
        archSintactico = "./src/compila_flex/partidor.cup";
        
        String[] alexico= {archLexico};
        String[] asintactico = {"-parser","AnalizadorSIntactico", archSintactico};
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
                    + File.separator + "src" +File.separator
                    + "compila_flex"+ File.separator + arch.getName();
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
}
