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

    static int offset = 0;

    // Semántico
    static ArrayList<String> errores_semanticos = new ArrayList();
    static ArrayList<Variables> tabla = new ArrayList();
    static ArrayList<Funcion> funciones = new ArrayList();
    static ArrayList<Funcion> decfunciones = new ArrayList();

    public static void main(String args[]) {
        /*boolean mvAl = moverArch("AnalizadorLexico.java");
        boolean mvAS = moverArch("Lexico.java");
        boolean mvSyn= moverArch("sym.java");*/

        // Ejecutar esto si se llegaron a hacer cambios:
        // compilar_archivos();
        // Ejecutar parte léxica y sintáctico:
        Nodo root = ejecutar();

        // Ejecutar parte semántica
        if (root != null) {
            recorrido(root.getHijos().get(0), "Start");
        } else {
            System.out.println("ROOT NULO");
        }

        System.out.println("LA TABLA DE SÍMBOLOS ES: ");
        for (Variables variable : tabla) {
            System.out.println(variable.toString());
        }
        System.out.println("LA TABLA DE SÍMBOLOS DE FUNCIONES ES: ");
        for (Funcion funcion : funciones) {
            System.out.println(funcion.toString());
        }
        System.out.println("\n -------------------------------------- \n");
        System.out.println("LOS ERRORES SEMÁNTICOS SON: ");
        for (String error : errores_semanticos) {
            System.out.println(error);
        }
    }

    public static void compilar_archivos() {
        String archLexico = "";
        String archSintactico = "";

        System.out.println("\n se esta procesando el archivo default \n");
        archLexico = "./src/main/Lexico.flex";
        archSintactico = "./src/main/Sintactico.cup";

        String[] alexico = {archLexico};
        String[] asintactico = {"-parser", "AnalizadorSintactico", archSintactico};
        jflex.Main.main(alexico);
        try {
            java_cup.Main.main(asintactico);
        } catch (Exception ex) {
            System.out.println("" + ex);
        };
        System.out.println("Generados Correctamente.....");
    }

    public static boolean moverArch(String archNombre) {
        boolean efectuado = false;
        File arch = new File(archNombre);
        if (arch.exists()) {
            System.out.println("\n Moviendo " + arch + "....");
            Path currentRelativePath = Paths.get("");
            String nuevoDir = currentRelativePath.toAbsolutePath().toString()
                    + File.separator
                    + "src"
                    + File.separator
                    + "main"
                    + File.separator
                    + arch.getName();
            File archViejo = new File(nuevoDir);
            archViejo.delete();
            if (arch.renameTo(new File(nuevoDir))) {
                System.out.println("\n*** Generado" + archNombre + "***\n");
            } else {
                System.out.println("\n*** no se movio" + archNombre + "***\n");
            }
        } else {
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

    // ===================================================
    // =================== Semántica =====================
    // ===================================================
    public static void recorrido(Nodo body, String ambito_actual) {
        for (Nodo hijo : body.getHijos()) { // Se recorren los hijos del body, nodo que contiene todo.

            // Aquí se encuentran las siguientes validaciones semánticas:
            // - Declaraciones de variables sin asignación | int x;
            // - Declaraciones de variables con asignación | int x = 23;
            // - Asignaciones solamente | x = 123;
            if (hijo.getValor().equals("Proposicion")) {
                if (hijo.getHijos().get(0).getValor().equals("Declaracion Simple")) {
                    String tipo = "", id = "";
                    for (Nodo h : hijo.getHijos().get(0).getHijos()) { //Se toman los hijos de la declaración_simple
                        if (h.getValor().equals("Caracter")) { //En caso de que sea un caracter
                            tipo = "caracter";
                            offset += 1;
                        } else if (h.getValor().equals("Boolena")) {//En caso de que sea un booleano
                            tipo = "booleano";
                            offset += 1;
                        } else if (h.getValor().equals("NUM")) {//En caso de que sea un numero
                            tipo = "entero";
                            int mod = 4 - (offset % 4);
                            if (mod == 4) {
                                offset += 4;
                            } else {
                                offset += 4 + mod;
                            }
                        } else if (h.getValor().equals("ID")) { //En caso de que sea un ID se toma
                            id = h.getHijos().get(0).getValor();
                            if (!verificarVariable(id, ambito_actual)) { // Se verifica si el id está repetido en la tabla de símbolos
                                if (hijo.getHijos().get(0).getHijos().get(3).getValor().equals("asignacion")) {
                                    String valor = hijo.getHijos().get(0).getHijos().get(3).getHijos().get(0).getValor();
                                    if (tipo.equals("caracter") && valor.equals("Valores-caracter")) {
                                        tabla.add(new Variables(tipo, id, ambito_actual, offset));
                                    } else if (tipo.equals("entero") && valor.equals("Valores-num")) {
                                        tabla.add(new Variables(tipo, id, ambito_actual, offset));
                                    } else if (tipo.equals("booleano") && valor.equals("Valores-bool")) {
                                        tabla.add(new Variables(tipo, id, ambito_actual, offset));
                                    } else {
                                        errores_semanticos.add("Error semántico: Se ha asignado un valor erróneo a la a variable " + id + " se esperaba un " + tipo);
                                    }
                                } else if (hijo.getHijos().get(0).getHijos().get(3).getValor().equals(";")) {
                                    tabla.add(new Variables(tipo, id, ambito_actual, offset));
                                }
                            } else {
                                errores_semanticos.add("Error semántico: La variable " + id + " ha sido declarada con anterioridad dentro del ámbito " + ambito_actual);
                            }
                        }
                    }
                } else if (hijo.getHijos().get(0).getValor().equals("ID")) { // En caso de que sea solamente una asignacion
                    String tipo = "", id = "", valor = "";
                    id = hijo.getHijos().get(0).getHijos().get(0).getValor(); // Tomo el id
                    valor = hijo.getHijos().get(1).getValor(); // Tomo el valor
                    if ((tipo = buscarTipoVariable(id, ambito_actual)) != null) { //Si el id existe y los tipos concuerdan
                        if (valor.equals("=")){
                            valor = hijo.getHijos().get(2).getValor();
                        }
                        if (tipo.equals("caracter") && valor.equals("Valores-caracter")) {
                            // RECORDAR: NO SE AGREGA A LA TABLA DE VALORES PORQUE YA DEBERÍA DE ESTAR AGREGADO (Esto solo es asignación)
                        } else if (tipo.equals("entero") && valor.equals("Valores-num")) {
                            // RECORDAR: NO SE AGREGA A LA TABLA DE VALORES PORQUE YA DEBERÍA DE ESTAR AGREGADO (Esto solo es asignación)
                        } else if (tipo.equals("booleano") && valor.equals("Valores-bool")) {
                            // RECORDAR: NO SE AGREGA A LA TABLA DE VALORES PORQUE YA DEBERÍA DE ESTAR AGREGADO (Esto solo es asignación)
                        } else {
                            errores_semanticos.add("Error semántico: Se ha asignado un valor erróneo a la variable " + id + " se esperaba un " + tipo);
                        }
                    } else {
                        errores_semanticos.add("Error semántico: La variable " + id + " no existe dentro del ámbito " + ambito_actual);
                    }
                }
            } // Aquí se encuentran las siguientes validaciones semánticas:
            // - Arrays de 1 o 2 dimensiones
            else if (hijo.getValor().equals("Declara Array")) {
                Nodo currentNode = hijo;
                String id = currentNode.getHijos().get(0).getHijos().get(0).getValor();
                int dimension = Integer.parseInt(currentNode.getHijos().get(1).getHijos().get(0).getValor());
                String tipo = "array()";
                for (int i = 1; i < dimension; i++) {
                    tipo = "array(" + tipo + ")";
                }
                if (!verificarVariable(id, ambito_actual)) { //Verifica si ya existe el id
                    if (currentNode.getHijos().size() > 2) { //Verifica si se le asigna arreglos
                        if (currentNode.getHijos().get(2).getValor().equals("Valores") && dimension == 1) { //Verifica si se le asigna la cantidad de arreglos con respecto a la dimensión establecida
                            // RECORDAR: CALCULAR EL OFFSET
                            tabla.add(new Variables(tipo, id, ambito_actual, 0));
                        } else if (currentNode.getHijos().get(2).getValor().equals("bracket-segunda dimension") && dimension == 2) {
                            // RECORDAR: CALCULAR EL OFFSET
                            tabla.add(new Variables(tipo, id, ambito_actual, 0));
                        } else {
                            errores_semanticos.add("Error semántico: Se esperaba un arreglo de " + dimension + " dimensiones en la variable " + id);
                        }
                    } else {
                        // RECORDAR: CALCULAR EL OFFSET
                        tabla.add(new Variables(tipo, id, ambito_actual, 0));
                    }
                } else {
                    errores_semanticos.add("Error semántico: La variable " + id + " ha sido declarada con anterioridad dentro del ámbito " + ambito_actual);
                }
            } // Aquí se encuentran las siguientes validaciones semánticas:
            // - Declaración de funciones parámetro y cuerpo
            else if (hijo.getValor().equals("Funciones")) {
                String tipo = "", id = "";  // Tipo y ID de la función.
                ArrayList<Variables> parametros = new ArrayList();  //Aquí se almacenarán los parámetros de la función.
                boolean permitido = true; // Indica si la función es permitida para agregarse a la tabla o no.
                id = hijo.getHijos().get(2).getHijos().get(0).getValor();   //Se obtiene el Id
                if (!verificarFuncion(id)) {
                    if (hijo.getHijos().get(1).getValor().equals("NUM")) {   //Se valida que tipo es la función
                        tipo = "entero";
                    } else if (hijo.getHijos().get(1).getValor().equals("Caracter")) {
                        tipo = "caracter";
                    } else if (hijo.getHijos().get(1).getValor().equals("Boolena")) {
                        tipo = "booleano";
                    } else if (hijo.getHijos().get(1).getValor().equals("String")) {
                        tipo = "cadena";
                    }
                    // Validación de sus parámetros
                    String tipoParam = "", idParam = ""; // Tipo y ID del parámetro
                    for (Nodo h : hijo.getHijos().get(3).getHijos()) { // Se obtiene el tipo del parametro
                        if (h.getValor().equals("Caracter")) {
                            tipoParam = "caracter";
                        } else if (h.getValor().equals("Boolena")) {
                            tipoParam = "booleano";
                        } else if (h.getValor().equals("NUM")) {
                            tipoParam = "entero";
                        } else if (h.getValor().equals("String")) {
                            tipoParam = "cadena";
                        } else if (h.getValor().equals("ID")) {
                            idParam = h.getHijos().get(0).getValor(); // Se obtiene el id del parametro
                            Variables param = new Variables(tipoParam, idParam, id, 0);  //Se crea la variable con el tipo y id del parámetro
                            // RECORDAR: CALCULAR EL OFFSET
                            if (!verificarParametroId(parametros, idParam)) { // Se verifica si el id del parámetro ya existía dentro de la función
                                parametros.add(param);
                            } else {
                                errores_semanticos.add("Error semántico: La variable " + idParam + " ya es utilizada en otro parámetro en la declaración de la función " + id);
                                permitido = false;
                                break;
                            }
                        }
                    }
                    if (permitido) {
                        funciones.add(new Funcion(tipo, id, parametros));
                        // Ahora el cuerpo de la función
                        recorrido(hijo.getHijos().get(4), ambito_actual+"."+id);
                    }
                }else{
                    errores_semanticos.add("Error semántico: La función "+id+" fue definida con anterioridad");
                }
            } // Aquí se encuentran las siguientes validaciones semánticas:
            // - Llamada de funciones con parámetro.
            else if (hijo.getValor().equals("Llamada de funciones")) {
                String id = hijo.getHijos().get(0).getHijos().get(0).getValor(); // Obtener el ID
                if(verificarLlamado(id)){ // Verifica si la función ya ha sido declarada
                    for (Nodo h : hijo.getHijos().get(1).getHijos()) { //Verifica los argumentos
                        if(verificarVariable(h.getHijos().get(0).getValor(), ambito_actual)){ //Verifica Si la variable existe
                            if(false){ // Verifica si el tipo es el indicado
                            
                            }else{
                                errores_semanticos.add("Error semántico: La variable "+h.getValor()+" no existe dentro del ámbito "+ambito_actual);
                            }
                        }else{
                            errores_semanticos.add("Error semántico: La variable "+h.getHijos().get(0).getValor()+" no existe dentro del ámbito "+ambito_actual);
                        }
                    }
                }else{
                    errores_semanticos.add("Error semántico: La función "+id+" no ha sido declarada");
                }
            }
        }

    }

    // Verifica si existe el id en los ámbitos especificados
    public static boolean verificarVariable(String variable, String ambito_actual) {
        for (int i = 0; i < tabla.size(); i++) {
            if (variable.equals(tabla.get(i).getId()) && ambito_actual.contains(tabla.get(i).getAmbito())){
                return true;
            }
        }
        return false;
    }
    
    // Verifica si la función ya ha sido creada
    public static boolean verificarFuncion(String idFuncion) {
        for (Funcion funcion : funciones) {
            if(funcion.getId().equals(idFuncion)){
                return true;
            }
        }
        return false;
    }

    // Verifica si la función ya fue creado y se le pasan los parámetros correctos
    public static boolean verificarLlamado(String idFuncion) {
        for (Funcion funcion : funciones) {
            if(funcion.getId().equals(idFuncion)){
                return true;
            }
        }
        return false;
    }
    
    // Busca el tipo de la variable con su ID y su ámbito
    public static String buscarTipoVariable(String id, String ambito) {
        for (Variables variable : tabla) {
            if (variable.getId().equals(id) && ambito.contains(variable.getAmbito())) {
                return variable.getTipo();
            }
        }
        return null;
    }

    // Verifica si el id se encuentra en la lista de parámetros añadida
    public static boolean verificarParametroId(ArrayList<Variables> parametros, String id) {
        for (Variables parametro : parametros) {
            if (parametro.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    // ===================================================
    // ===================================================
    // ===================================================
    public static void escribirArchivo(String v) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src/AST/AST.dot", true);
            pw = new PrintWriter(fichero);
            pw.print("digraph {\n");
            pw.print(v);
            pw.print("\n}");
        } catch (IOException e) {

        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e) {

            }
        }
    }

    public static String print(Nodo padre) {
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

    public static void limpiar(String v) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src/AST/AST.dot");
            pw = new PrintWriter(fichero);
            pw.print(v);
        } catch (Exception e) {

        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e) {

            }
        }
    }

}
