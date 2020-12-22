package main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.CodingErrorAction;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class temp_main {

    Scanner leer = new Scanner(System.in);

    static int offset = -4;

    // Semántico
    static int cont = 0;
    static ArrayList<String> errores_semanticos = new ArrayList();
    static ArrayList<Variables> tabla = new ArrayList();
    static ArrayList<Funcion> funciones = new ArrayList();
    static ArrayList<Funcion> decfunciones = new ArrayList();
    static ArrayList<Cuadruplo> cuadruplo = new ArrayList();
    // Final
    static int cont_temp = 0, cont_etiq = 0;
    static ArrayList<String> mensajes = new ArrayList();
    static String ambito_siguiente = "";
    static String ambito_actual = "";
    //File
    static String camino = "";
    public static void main(String args[]) {

        //Ejecutar esto si se llegaron a hacer cambios:
        System.out.println(args.length);
        camino = args[0];
        compilar_archivos();
        boolean mvAl = moverArch("Lexico.java");
        boolean mvAS = moverArch("AnalizadorSintactico.java");
        boolean mvSyn = moverArch("sym.java");
        //Ejecutar parte léxica y sintáctico:
        Nodo root = ejecutar();
        // Ejecutar parte semántica
        if (root != null) {
            recorrido(root.getHijos().get(0), "Start");
            if (errores_semanticos.size() == 0) {
                // Ejecutar la parte de código intermedio
                intermedio(root.getHijo(0));
                // Ejecutar la parte de código final
                codigo_final();
            }
        } else {
            System.out.println("ROOT NULO");
        }
//
//        System.out.println(" ==== Semántico ==== ");
//        for (Variables variable : tabla) {
//            System.out.println(variable.toString());
//        }
//        for (Funcion funcion : funciones) {
//            System.out.println(funcion.toString());
//        }
//        System.out.println("\n -------------------------------------- \n");
//        System.out.println("ERRORES: ");
//        for (String error : errores_semanticos) {
//            System.out.println(error.toString());
//        }
//
//        System.out.println("\n -------------------------------------- \n");
//        System.out.println(" ==== Código intermedio ==== ");
//        for (Cuadruplo cuad : cuadruplo) {
//            System.out.println(cuad.toString());
//        }
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
            //AnalizadorSintactico asin = new AnalizadorSintactico(new Lexico(new FileReader("src/Test/prueba.txt")));
            AnalizadorSintactico asin = new AnalizadorSintactico(new Lexico(new FileReader(camino)));
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
        for (Nodo hijo : body.getHijos()) {
            if (hijo.getValor().equals("declaracion de variable")) {
                String tipo = hijo.getHijo(1).getValor();
                String id = hijo.getHijo(2).getHijo(0).getValor();
                if (!verificarVariable(id, ambito_actual)) { // Primero verificar si la variable no ha sido declarada en el cuerpo de la función
                    String[] array = ambito_actual.split("\\.");
                    String funcionActual = array[array.length - 1];
                    if (verificarParametroId(id, funcionActual) != null) { //Segundo verificar si la variable no ha sido declarada en los parámetros de la función
                        errores_semanticos.add("Error semántico: La variable " + id + " ha sido declarada con anterioridad como parámetro en la función " + funcionActual);
                    } else {
                        tabla.add(new Variables(tipo, id, ambito_actual, getOffset(tipo)));
                    }
                } else {
                    errores_semanticos.add("Error semántico: La variable " + id + " ha sido declarada con anterioridad, ámbito " + ambito_actual);
                }
            } else if (hijo.getValor().equals("declaracion y asignacion")) {
                String tipo = hijo.getHijo(1).getValor();
                String id = hijo.getHijo(2).getHijo(0).getValor();
                String valor = hijo.getHijo(3).getHijo(0).getValor();
                boolean pasa = false;
                String tipoId = "";

                boolean validFunc = true;
                String idFunc;
                String tipoFunc = "";
                if (hijo.getHijo(3).getValor().equals("llamada a funcion")) {
                    idFunc = hijo.getHijos().get(3).getHijo(0).getHijo(0).getValor(); // Obtener el ID
                    ArrayList<Variables> parametros = new ArrayList();
                    Funcion funcion;
                    int cont = 0;
                    if ((funcion = getFuncion(idFunc)) != null) { // Verifica si la función ya ha sido declarada
                        tipoFunc = funcion.getTipo();
                        for (Nodo h : hijo.getHijo(3).getHijo(1).getHijos()) {
                            cont++;
                            if (verificarVariable(h.getHijo(0).getValor(), ambito_actual)) { //Verifica Si la variable que se le está pasando existe
                                parametros.add(getVariable(h.getHijo(0).getValor(), ambito_actual)); //Sí la variable existe se agrega a este arreglo
                            } else {
                                errores_semanticos.add("Error semántico: llamado a función, la variable " + h.getHijos().get(0).getValor() + " no existe en el ámbito " + ambito_actual);
                                validFunc = false;
                            }
                        }
                        if (cont != funcion.getParams().size()) {
                            errores_semanticos.add("Error semántico: llamado de función incorrecta, se esperaban " + funcion.getParams().size() + " cantidad de parámetros en el llamado a la función " + id + " en el ámbito " + ambito_actual);
                            validFunc = false;
                        } else if (parametros.size() == funcion.getParams().size()) {
                            for (int i = 0; i < parametros.size(); i++) {
                                if (parametros.get(i).getTipo() != funcion.getParams().get(i).getTipo()) { // Verificar los tipos
                                    errores_semanticos.add("Error semántico: llamado de función incorrecta, la variable " + parametros.get(i).getId() + " es de un tipo distinto, se esperaba un tipo " + funcion.getParams().get(i).getTipo() + " en el llamado a la función " + id + " en el ámbito " + ambito_actual);
                                    validFunc = false;
                                }
                            }
                        }
                    } else {
                        errores_semanticos.add("Error semántico: llamado de función incorrecta, la función " + idFunc + " no fue declarada con anterioridad");
                        validFunc = false;
                    }
                }

                switch (tipo) { //Aquí valido si se le asigna el tipo correcto
                    case "entero":
                        if (valor.equals("num")) {
                            pasa = true;
                        } else if (hijo.getHijo(3).getValor().equals("id")) {
                            if ((tipoId = getTipoVariable(hijo.getHijo(3).getHijo(0).getValor(), ambito_actual)) != null) { // Valida si existe en el cuerpo de la función
                                if (!tipoId.equals(tipo)) {
                                    errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un entero");
                                } else {
                                    pasa = true;
                                }
                            } else {
                                String[] array = ambito_actual.split("\\.");
                                String funcionActual = array[array.length - 1];
                                if ((tipoId = verificarParametroId(id, funcionActual)) != null) { //Segundo verificar si la variable no ha sido declarada en los parámetros de la función
                                    if (!tipoId.equals(tipo)) {
                                        errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un entero");
                                    } else {
                                        pasa = true;
                                    }
                                } else {
                                    errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, la variable " + hijo.getHijo(3).getHijo(0).getValor() + " no ha sido declarada en el ámbito " + ambito_actual);
                                }
                            }
                        } else if (hijo.getHijo(3).getValor().equals("llamada a funcion")) {
                            if (validFunc) {
                                if (!tipoFunc.equals(tipo)) {
                                    errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un tipo entero ");
                                } else {
                                    pasa = true;
                                }
                            }
                        } else {
                            errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un tipo entero ");
                        }
                        break;
                    case "booleano":
                        if (valor.equals("bool")) {
                            pasa = true;
                        } else if (hijo.getHijo(3).getValor().equals("id")) {
                            if ((tipoId = getTipoVariable(hijo.getHijo(3).getHijo(0).getValor(), ambito_actual)) != null) { // Valida si existe en el cuerpo de la función
                                if (!tipoId.equals(tipo)) {
                                    errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un entero");
                                } else {
                                    pasa = true;
                                }
                            } else {
                                String[] array = ambito_actual.split("\\.");
                                String funcionActual = array[array.length - 1];
                                if ((tipoId = verificarParametroId(id, funcionActual)) != null) { //Segundo verificar si la variable no ha sido declarada en los parámetros de la función
                                    if (!tipoId.equals(tipo)) {
                                        errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un entero");
                                    } else {
                                        pasa = true;
                                    }
                                } else {
                                    errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, la variable " + hijo.getHijo(3).getHijo(0).getValor() + " no ha sido declarada en el ámbito " + ambito_actual);
                                }
                            }
                        } else if (hijo.getHijo(3).getValor().equals("llamada a funcion")) {
                            if (validFunc) {
                                if (!tipoFunc.equals(tipo)) {
                                    errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un tipo booleano");
                                } else {
                                    pasa = true;
                                }
                            }
                        } else {
                            errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un tipo booleano ");
                        }
                        break;
                    case "caracter":
                        if (valor.equals("letter")) {
                            pasa = true;
                        } else if (hijo.getHijo(3).getValor().equals("id")) {
                            if ((tipoId = getTipoVariable(hijo.getHijo(3).getHijo(0).getValor(), ambito_actual)) != null) { // Valida si existe en el cuerpo de la función
                                if (!tipoId.equals(tipo)) {
                                    errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un caracter");
                                } else {
                                    pasa = true;
                                }
                            } else {
                                String[] array = ambito_actual.split("\\.");
                                String funcionActual = array[array.length - 1];
                                if ((tipoId = verificarParametroId(id, funcionActual)) != null) { //Segundo verificar si la variable no ha sido declarada en los parámetros de la función
                                    if (!tipoId.equals(tipo)) {
                                        errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un caracter");
                                    } else {
                                        pasa = true;
                                    }
                                } else {
                                    errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, la variable " + hijo.getHijo(3).getHijo(0).getValor() + " no ha sido declarada en el ámbito " + ambito_actual);
                                }
                            }
                        } else if (hijo.getHijo(3).getValor().equals("llamada a funcion")) {
                            if (validFunc) {
                                if (!tipoFunc.equals(tipo)) {
                                    errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un tipo caracter ");
                                } else {
                                    pasa = true;
                                }
                            }
                        } else {
                            errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un tipo caracter ");
                        }
                        break;
                    case "string":
                        if (valor.equals("string")) {
                            pasa = true;
                        } else if (hijo.getHijo(3).getValor().equals("id")) {
                            if ((tipoId = getTipoVariable(hijo.getHijo(3).getHijo(0).getValor(), ambito_actual)) != null) { // Valida si existe en el cuerpo de la función
                                if (!tipoId.equals(tipo)) {
                                    errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un string");
                                } else {
                                    pasa = true;
                                }
                            } else {
                                String[] array = ambito_actual.split("\\.");
                                String funcionActual = array[array.length - 1];
                                if ((tipoId = verificarParametroId(id, funcionActual)) != null) { //Segundo verificar si la variable no ha sido declarada en los parámetros de la función
                                    if (!tipoId.equals(tipo)) {
                                        errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un string");
                                    } else {
                                        pasa = true;
                                    }
                                } else {
                                    errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, la variable " + hijo.getHijo(3).getHijo(0).getValor() + " no ha sido declarada en el ámbito " + ambito_actual);
                                }
                            }
                        } else if (hijo.getHijo(3).getValor().equals("llamada a funcion")) {
                            if (validFunc) {
                                if (!tipoFunc.equals(tipo)) {
                                    errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un tipo string ");
                                } else {
                                    pasa = true;
                                }
                            }
                        } else {
                            errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un tipo string ");
                        }
                        break;
                    default:

                }
                if (pasa) {
                    if (!verificarVariable(id, ambito_actual)) {
                        String[] array = ambito_actual.split("\\.");
                        String funcionActual = array[array.length - 1];
                        if (verificarParametroId(id, funcionActual) != null) { //Segundo verificar si la variable no ha sido declarada en los parámetros de la función
                            errores_semanticos.add("Error semántico: La variable " + id + " ha sido declarada con anterioridad como parámetro en la función " + funcionActual);
                        } else {
                            if (tipo.equals("string")) {
                                tabla.add(new Variables(tipo, id, ambito_actual, getOffset(tipo, hijo.getHijo(3).getHijo(0).getHijo(0).getValor())));
                            } else {
                                tabla.add(new Variables(tipo, id, ambito_actual, getOffset(tipo)));
                            }
                        }
                    } else {
                        errores_semanticos.add("Error semántico: La variable " + id + " ha sido declarada con anterioridad, ámbito " + ambito_actual);
                    }
                }
            } else if (hijo.getValor().equals("declaracion y asignacion expresión")) {
                String tipo = hijo.getHijo(1).getValor();
                for (int i = 0; i < hijo.getHijos().size(); i++) {
                    if (hijo.getHijo(i).getValor().equals("id")) {
                        if (!verificarVariable(hijo.getHijo(i).getHijo(0).getValor(), ambito_actual)) { //se revisa si el id ha sido invocado
                            //String id = getTipoVariable(hijo.getHijo(i).getHijo(0).getValor(), ambito_actual);
                            String id = hijo.getHijo(i).getHijo(0).getValor();
                            tabla.add(new Variables(tipo, id, ambito_actual, getOffset(tipo)));
                        }
                    } else if (hijo.getHijo(i).getValor().equals("expresión")) {//algunas veces se encuentran variables en expresiones se debe revisar eso
                        String tipoId;
                        Boolean pasa;
                        for (int j = 0; j < hijo.getHijo(i).getHijos().size(); j++) {
                            Nodo nodo_actual = hijo.getHijo(i).getHijo(j);
                            if (nodo_actual.getValor().equals("expresión")) {
                                for (int k = 0; k < nodo_actual.getHijos().size(); k++) {
                                    if (nodo_actual.getHijo(k).getValor().equals("id")) {
                                        String id_exp;
                                        if (verificarVariable(nodo_actual.getHijo(k).getValor(), ambito_actual)) {
                                            
                                            id_exp = nodo_actual.getHijo(k).getHijo(0).getValor();
                                            
                                            errores_semanticos.add("Error semántico: La variable " + id_exp + " no ha sido declarada con anterioridad, ámbito " + ambito_actual);
                                            //System.out.print("no existe la variable"+nodo_actual.getHijo(k).getHijo(0).getValor());
                                        }
                                    }
                                }

                            } else if (nodo_actual.getValor().equals("id")) {
                                String valor = nodo_actual.getHijo(0).getValor();
                                System.out.println("Entra a la A");
                                if ((tipoId = getTipoVariable(valor, ambito_actual)) != null) { // Valida si existe en el cuerpo de la función
                                    if (!tipoId.equals(tipo)) {
                                        errores_semanticos.add("Error semántico: La variable " + valor + " recibe un tipo incorrecto, se esperaba un entero");
                                    } else {
                                        pasa = true;
                                    }
                                } else {
                                    String[] array = ambito_actual.split("\\.");
                                    String funcionActual = array[array.length - 1];
                                    if ((tipoId = verificarParametroId(valor, funcionActual)) != null) { //Segundo verificar si la variable no ha sido declarada en los parámetros de la función
                                        if (!tipoId.equals(tipo)) {
                                            errores_semanticos.add("Error semántico: La variable " + valor + " recibe un tipo incorrecto, se esperaba un entero");
                                        } else {
                                            pasa = true;
                                        }
                                    } else {
                                        errores_semanticos.add("Error semántico: La variable " + valor + " recibe un tipo incorrecto, la variable " + hijo.getHijo(3).getHijo(0).getValor() + " no ha sido declarada en el ámbito " + ambito_actual);
                                    }
                                }

                            }
                            Nodo presente = hijo.getHijo(i).getHijo(j);
                            while (presente.getValor().equals(":=") || presente.getValor().equals("<:") || presente.getValor().equals(":>") || presente.getValor().equals("~") || presente.getValor().equals(">=") || presente.getValor().equals("<=") || presente.getValor().equals("||") || presente.getValor().equals("=/=")) {
                                Nodo id_while = presente.getHijo(0).getHijo(0);
                                if (!verificarVariable(id_while.getValor(), ambito_actual)) {
                                    
                                    errores_semanticos.add("Error semántico: La variable " + id_while.getValor() + " no ha sido declarada con anterioridad, ámbito " + ambito_actual);
                                } else {
                                    System.out.print("existe la variabale" + id_while.getValor());
                                }
                                presente = presente.getHijo(1);
                            }
                        }
                        //String porAhora=RecursivaExpresion(temp,temp.getValor()); esto es para intermedio
                    }
                }
            }else if (hijo.getValor().equals("expresión")){
                String tipo = hijo.getHijo(1).getValor();
                System.out.println("este tipo esta: "+tipo);
                for (int i = 0; i < hijo.getHijos().size(); i++) {
                    if (hijo.getHijo(i).getValor().equals("id")) {
                        if (!verificarVariable(hijo.getHijo(i).getHijo(0).getValor(), ambito_actual)) { //se revisa si el id ha sido invocado
                            //String id = getTipoVariable(hijo.getHijo(i).getHijo(0).getValor(), ambito_actual);
                            String id = hijo.getHijo(i).getHijo(0).getValor();
                            tabla.add(new Variables(tipo, id, ambito_actual, getOffset(tipo)));
                        }
                    } else if (hijo.getHijo(i).getValor().equals("expresión")) {   //algunas veces se encuentran variables en expresiones se debe revisar eso
                        String tipoId;
                        Boolean pasa;
                        for (int j = 0; j < hijo.getHijo(i).getHijos().size(); j++) {
                            Nodo nodo_actual = hijo.getHijo(i).getHijo(j);
                            if (nodo_actual.getValor().equals("expresión")) {
                                for (int k = 0; k < nodo_actual.getHijos().size(); k++) {
                                    if (nodo_actual.getHijo(k).getValor().equals("id")) {
                                        String id_exp;
                                        if (!verificarVariable(nodo_actual.getHijo(k).getValor(), ambito_actual)) {
                                            System.out.println("esta aqui");
                                            id_exp = nodo_actual.getHijo(k).getHijo(0).getValor();
                                            
                                            errores_semanticos.add("Error semántico: La variable " + id_exp + " no ha sido declarada con anterioridad, ámbito " + ambito_actual);
                                            //System.out.print("no existe la variable"+nodo_actual.getHijo(k).getHijo(0).getValor());
                                        }
                                    }
                                }

                            } else if (nodo_actual.getValor().equals("id")) {
                                String valor = nodo_actual.getHijo(0).getValor();
                                System.out.println("Entra a la A");
                                if ((tipoId = getTipoVariable(valor, ambito_actual)) != null) { // Valida si existe en el cuerpo de la función
                                    if (!tipoId.equals(tipo)) {
                                        errores_semanticos.add("Error semántico: La variable " + valor + " recibe un tipo incorrecto, se esperaba un entero");
                                    } else {
                                        pasa = true;
                                    }
                                } else {
                                    String[] array = ambito_actual.split("\\.");
                                    String funcionActual = array[array.length - 1];
                                    if ((tipoId = verificarParametroId(valor, funcionActual)) != null) { //Segundo verificar si la variable no ha sido declarada en los parámetros de la función
                                        if (!tipoId.equals(tipo)) {
                                            errores_semanticos.add("Error semántico: La variable " + valor + " recibe un tipo incorrecto, se esperaba un entero");
                                        } else {
                                            pasa = true;
                                        }
                                    } else {
                                        errores_semanticos.add("Error semántico: La variable " + valor + " recibe un tipo incorrecto, la variable " + hijo.getHijo(3).getHijo(0).getValor() + " no ha sido declarada en el ámbito " + ambito_actual);
                                    }
                                }

                            }
                            Nodo presente = hijo.getHijo(i).getHijo(j);
                            while (presente.getValor().equals(":=") || presente.getValor().equals("<:") || presente.getValor().equals(":>") || presente.getValor().equals("~") || presente.getValor().equals(">=") || presente.getValor().equals("<=") || presente.getValor().equals("||") || presente.getValor().equals("=/=")|| presente.getValor().equals("&&")) {
                                Nodo id_while = presente.getHijo(0).getHijo(0);
                                System.out.println(id_while.getValor());
                                if (!verificarVariable(id_while.getValor(), ambito_actual)) {
                                    System.out.println("esta aqui");
                                    errores_semanticos.add("Error semántico: La variable " + id_while.getValor() + " no ha sido declarada con anterioridad, ámbito " + ambito_actual);
                                } else {
                                    System.out.print("existe la variabale" + id_while.getValor());
                                }
                                presente = presente.getHijo(1);
                            }
                        }
                        //String porAhora=RecursivaExpresion(temp,temp.getValor()); esto es para intermedio
                    }
                }
                
            } else if (hijo.getValor().equals("asignacion")) {
                String id = hijo.getHijo(0).getHijo(0).getValor(), value = hijo.getHijo(1).getHijo(0).getValor(), valor = hijo.getHijo(1).getValor(), tipo, tipoId;
                if (verificarVariable(id, ambito_actual)) {
                    if (valor.equals("id")) {
                        tipo = getTipoVariable(id, ambito_actual);
                        if ((tipoId = getTipoVariable(value, ambito_actual)) != null) { // Valida si existe en el cuerpo de la función
                            if (!tipoId.equals(tipo)) {
                                errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un " + tipo);
                            }
                        } else {
                            String[] array = ambito_actual.split("\\.");
                            String funcionActual = array[array.length - 1];
                            if ((tipoId = verificarParametroId(value, funcionActual)) != null) { //Segundo verificar si la variable no ha sido declarada en los parámetros de la función
                                if (!tipoId.equals(tipo)) {
                                    errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un " + tipo);
                                }
                            } else {
                                errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, la variable " + value + " no ha sido declarada en el ámbito " + ambito_actual);
                            }
                        }
                    } else if (valor.equals("llamada a funcion")) {
                        boolean validFunc = true;
                        String idFunc;
                        tipo = getTipoVariable(id, ambito_actual);
                        idFunc = hijo.getHijo(1).getHijo(0).getHijo(0).getValor(); // Obtener el ID
                        ArrayList<Variables> parametros = new ArrayList();
                        Funcion funcion;
                        int cont = 0;
                        if ((funcion = getFuncion(idFunc)) != null) { // Verifica si la función ya ha sido declarada
                            for (Nodo h : hijo.getHijo(1).getHijo(1).getHijos()) {
                                cont++;
                                if (verificarVariable(h.getHijo(0).getValor(), ambito_actual)) { //Verifica Si la variable que se le está pasando existe
                                    parametros.add(getVariable(h.getHijo(0).getValor(), ambito_actual)); //Sí la variable existe se agrega a este arreglo
                                } else {
                                    errores_semanticos.add("Error semántico: llamado a función, la variable " + h.getHijos().get(0).getValor() + " no existe en el ámbito " + ambito_actual);
                                    validFunc = false;
                                }
                            }
                            if (cont != funcion.getParams().size()) {
                                errores_semanticos.add("Error semántico: llamado de función incorrecta, se esperaban " + funcion.getParams().size() + " cantidad de parámetros en el llamado a la función " + id + " en el ámbito " + ambito_actual);
                                validFunc = false;
                            } else if (parametros.size() == funcion.getParams().size()) {
                                for (int i = 0; i < parametros.size(); i++) {
                                    if (parametros.get(i).getTipo() != funcion.getParams().get(i).getTipo()) { // Verificar los tipos
                                        errores_semanticos.add("Error semántico: llamado de función incorrecta, la variable " + parametros.get(i).getId() + " es de un tipo distinto, se esperaba un tipo " + funcion.getParams().get(i).getTipo() + " en el llamado a la función " + id + " en el ámbito " + ambito_actual);
                                        validFunc = false;
                                    }
                                }
                            }
                            if (validFunc) {
                                if (!tipo.equals(funcion.getTipo())) {
                                    errores_semanticos.add("Error semántico: llamado de función incorrecta, la función " + funcion.getId() + " es de un tipo distinto, se esperaba un tipo " + tipo);
                                }
                            }
                        } else {
                            errores_semanticos.add("Error semántico: llamado de función incorrecta, la función " + idFunc + " no fue declarada con anterioridad");
                            validFunc = false;
                        }
                    } else {
                        tipo = getTipoVariable(id, ambito_actual);
                        if (!(tipo.equals("entero") && value.equals("num") || tipo.equals("boolean") && value.equals("bool") || tipo.equals("caracter") && value.equals("letter") || tipo.equals("string") && value.equals("string"))) {
                            errores_semanticos.add("Error semántico: valor no permitido para la variable " + id + " en el ámbito " + ambito_actual + " se pide un tipo " + tipo);
                        }
                    }
                } else {
                    errores_semanticos.add("Error semántico: La variable " + id + " no ha sido declarada con anterioridad, ámbito " + ambito_actual);
                }
            } else if (hijo.getValor().equals("asignación expresión")) {
                /*AQUÍ*/
            } else if (hijo.getValor().equals("declaración array")) {
                Nodo currentNode = hijo;
                String id = currentNode.getHijos().get(0).getHijos().get(0).getValor();
                int dimension = Integer.parseInt(currentNode.getHijos().get(1).getHijos().get(0).getValor());
                String tipo = "array()";
                for (int i = 1; i < dimension; i++) {
                    tipo = "array(" + tipo + ")";
                }
                if (!verificarVariable(id, ambito_actual)) { //Verifica si ya existe el id
                    if (currentNode.getHijos().size() == 3) { //Verifica si se le asigna arreglos
                        if (dimension == 1) {
                            if (currentNode.getHijos().get(2).getValor().equals("valores")) {
                                tabla.add(new Variables(tipo, id, ambito_actual, getOffset(tipo, currentNode.getHijos().get(2))));
                            } else {
                                errores_semanticos.add("Error semántico: Se esperaban arreglos de " + dimension + " dimensiones en la variable " + id);
                            }
                        } else if (dimension == 2) {
                            if (currentNode.getHijos().get(2).getValor().equals("dos dimensiones")) {
                                tabla.add(new Variables(tipo, id, ambito_actual, getOffset(tipo, currentNode.getHijos().get(2))));
                            } else {
                                errores_semanticos.add("Error semántico: Se esperaban arreglos de " + dimension + " dimensiones en la variable " + id);
                            }
                        } else {
                            errores_semanticos.add("Error semántico: No se permiten arreglos de " + dimension + " dimensiones en la variable " + id);
                        }
                    } else {
                        // RECORDAR: CALCULAR EL OFFSET
                        tabla.add(new Variables(tipo, id, ambito_actual, 0));
                    }
                } else {
                    errores_semanticos.add("Error semántico: La variable " + id + " ha sido declarada con anterioridad dentro del ámbito " + ambito_actual);
                }
            } else if (hijo.getValor().equals("declaración de funcion")) {
                String tipo = "", id = "";
                ArrayList<Variables> parametros = new ArrayList();  //Aquí se almacenarán los parámetros de la función.
                boolean permitido = true; // Indica si la función es permitida para agregarse a la tabla o no.
                id = hijo.getHijos().get(2).getHijos().get(0).getValor();   //Se obtiene el Id
                if (!verificarFuncion(id)) {
                    tipo = hijo.getHijos().get(1).getValor();
                    // Validación de sus parámetros
                    String tipoParam = "", idParam = ""; // Tipo y ID del parámetro
                    for (Nodo h : hijo.getHijos().get(3).getHijos()) { // Se obtiene el tipo del parametro
                        if (!h.getValor().equals("Vacio")) {
                            tipoParam = h.getHijos().get(0).getValor();
                            idParam = h.getHijos().get(1).getValor();
                            if (verificarVariable(idParam, ambito_actual)) {
                                errores_semanticos.add("Error semántico: no se permite el id " + idParam + " como parámetro para la función " + id);
                                permitido = false;
                            } else {
                                if (!verificarParametroId(parametros, idParam)) { // Se verifica si el id del parámetro ya existía dentro de la función
                                    Variables param = new Variables(tipoParam, idParam, id, getOffset(tipoParam));  //Se crea la variable con el tipo y id del parámetro
                                    parametros.add(param);
                                } else {
                                    errores_semanticos.add("Error semántico: La variable " + idParam + " ya es utilizada en otro parámetro en la declaración de la función " + id);
                                    permitido = false;
                                    break;
                                }
                            }
                        }
                    }
                    if (permitido) {
                        funciones.add(new Funcion(tipo, id, parametros));
                        // Ahora el cuerpo de la función
                        recorrido(hijo.getHijos().get(4), ambito_actual + "." + id);
                    }
                } else {
                    errores_semanticos.add("Error semántico: La función " + id + " ya fue definida con anterioridad");
                }
            } else if (hijo.getValor().equals("llamada a funcion")) {
                String id = hijo.getHijos().get(0).getHijos().get(0).getValor(); // Obtener el ID
                ArrayList<Variables> parametros = new ArrayList();
                Funcion funcion;
                int cont = 0;
                if ((funcion = getFuncion(id)) != null) { // Verifica si la función ya ha sido declarada
                    for (Nodo h : hijo.getHijo(1).getHijos()) {
                        cont++;
                        if (verificarVariable(h.getHijo(0).getValor(), ambito_actual)) { //Verifica Si la variable que se le está pasando existe
                            parametros.add(getVariable(h.getHijo(0).getValor(), ambito_actual)); //Sí la variable existe se agrega a este arreglo
                        } else {
                            errores_semanticos.add("Error semántico: La variable " + h.getHijos().get(0).getValor() + " no existe en el ámbito " + ambito_actual);
                        }
                    }
                    if (cont != funcion.getParams().size()) {
                        errores_semanticos.add("Error semántico: llamado de función incorrecta, se esperaban " + funcion.getParams().size() + " cantidad de parámetros en el llamado a la función " + id + " en el ámbito " + ambito_actual);
                    } else if (parametros.size() == funcion.getParams().size()) {
                        for (int i = 0; i < parametros.size(); i++) {
                            if (parametros.get(i).getTipo() != funcion.getParams().get(i).getTipo()) { // Verificar los tipos
                                errores_semanticos.add("Error semántico: llamado de función incorrecta, la variable " + parametros.get(i).getId() + " es de un tipo distinto, se esperaba un tipo " + funcion.getParams().get(i).getTipo() + " en el llamado a la función " + id + " en el ámbito " + ambito_actual);
                            }
                        }
                    }
                } else {
                    errores_semanticos.add("Error semántico: llamado de función incorrecta, la función " + id + " no fue declarada con anterioridad");
                }
            } else if (hijo.getValor().equals("throw") || hijo.getValor().equals("throwdown")) {
                String valor = hijo.getHijo(0).getValor();
                if (valor.equals("id")) {
                    String id = hijo.getHijo(0).getHijo(0).getValor();
                    if (!verificarVariable(id, ambito_actual)) { // Primero verificar si la variable no ha sido declarada en el cuerpo de la función
                        String[] array = ambito_actual.split("\\.");
                        String funcionActual = array[array.length - 1];
                        if (verificarParametroId(id, funcionActual) == null) { //Segundo verificar si la variable no ha sido declarada en los parámetros de la función
                            errores_semanticos.add("Error semántico: La variable " + id + " no ha sido declarada con anterioridad, ambito " + ambito_actual + " al usarlo en el método throw");
                        }
                    }
                } else if (valor.equals("string")) {
                    mensajes.add(hijo.getHijo(0).getHijo(0).getValor());
                }
            } else if (hijo.getValor().equals("catch")) {
                String id = hijo.getHijo(0).getHijo(0).getValor();
                if (!verificarVariable(id, ambito_actual)) { // Primero verificar si la variable no ha sido declarada en el cuerpo de la función
                    String[] array = ambito_actual.split("\\.");
                    String funcionActual = array[array.length - 1];
                    if (verificarParametroId(id, funcionActual) == null) { //Segundo verificar si la variable no ha sido declarada en los parámetros de la función
                        errores_semanticos.add("Error semántico: La variable " + id + " no ha sido declarada con anterioridad, ambito " + ambito_actual + " al usarlo en el método catch");
                    }
                }
            } else if (hijo.getValor().equals("reply")) {
                String id = hijo.getHijo(0).getHijo(0).getValor(), tipo;
                String[] arrays = ambito_actual.split("\\.");
                if ((tipo = getTipoVariable(id, arrays[arrays.length - 1])) != null) { // Verifica si existe en el cuerpo
                    if (!tipo.equals(getTipoFuncion(arrays[arrays.length - 1]))) { // Verifica si los tipos concuerdan
                        errores_semanticos.add("Error semántico: la variable " + id + " no es del tipo indicado para ser retornado en la función " + ambito_actual);
                    }
                } else if ((tipo = verificarParametroId(id, arrays[arrays.length - 1])) != null) { // Verificar si existe en los parámetros
                    if (!tipo.equals(getTipoFuncion(arrays[arrays.length - 1]))) { // Verifica si los tipos concuerdan
                        errores_semanticos.add("Error semántico: la variable " + id + " no es del tipo indicado para ser retornado en la función " + ambito_actual);
                    }
                } else {
                    errores_semanticos.add("Error semántico: la variable " + id + " no existe dentro del ámbito " + ambito_actual);
                }
            } else if (hijo.getValor().equals("declaración ciclo for")) {
                String id = hijo.getHijo(0).getHijo(0).getHijo(0).getValor();
                String ambito = ambito_actual + "." + (cont++) + "_for_statement";
                if (verificarVariable(id, ambito_actual)) {
                    errores_semanticos.add("Error semántico: la variable " + id + " no se puede usar en el for ya fue declarada con anterioridad en el ámbito " + ambito_actual);
                } else {
                    tabla.add(new Variables("entero", id, ambito, getOffset("entero")));
                }
                recorrido(hijo.getHijo(1), ambito);
            } else if (hijo.getValor().equals("declaración bloque switch")) {
                ArrayList<String> arreglo = new ArrayList();
                String id = hijo.getHijo(1).getHijo(0).getValor(), tipo, valor;
                if ((tipo = getTipoVariable(id, ambito_actual)) == null) {
                    errores_semanticos.add("Error semántico: la variable " + id + " no se encuentra dentro del ámbito " + ambito_actual + " al usarlo en un bloque Switch Case");
                } else {
                    arreglo.add(id);
                    for (int i = 0; i < hijo.getHijo(2).getHijos().size(); i++) {
                        if (hijo.getHijo(2).getHijo(i).getValor().equals("Case")) {
                            if (hijo.getHijo(2).getHijo(i + 1).getValor().equals("id")) { // Verifica si lo que hay después de case es un ID, en caso de ser un ID hay que validar si existe y el tipo de la variable.
                                String temp_id = hijo.getHijo(2).getHijo(i + 1).getHijo(0).getValor();
                                if (!arreglo.contains(temp_id)) { //Comprueba si la variable ya fue utilizada
                                    if (verificarVariable(temp_id, ambito_actual)) {
                                        if (!tipo.equals(getTipoVariable(temp_id, ambito_actual))) {
                                            errores_semanticos.add("Error semántico: la variable " + temp_id + " es de un tipo no válido, se esperaba una variable de un tipo " + tipo + " dentro del bloque Switch case en el ámbito " + ambito_actual);
                                        } else {
                                            arreglo.add(temp_id);
                                            recorrido(hijo.getHijo(2).getHijo(i + 2), ambito_actual + "." + (cont++) + "_switchCase_statement-case" + temp_id);
                                        }
                                    } else {
                                        errores_semanticos.add("Error semántico: la variable " + temp_id + " no se encuentra dentro del ámbito " + ambito_actual + " al usarlo en un case dentro del bloque Switch Case");
                                    }
                                } else {
                                    errores_semanticos.add("Error semántico: la variable " + temp_id + " ya ha sido utilizada dentro del bloque Switch Case en el ámbito " + ambito_actual);
                                }
                            } else if ((hijo.getHijo(2).getHijo(i + 1).getValor().equals("num") && tipo.equals("entero")) || (hijo.getHijo(2).getHijo(i + 1).getValor().equals("letter") && tipo.equals("caracter"))) {
                                if (!arreglo.contains(valor = hijo.getHijo(2).getHijo(i + 1).getHijos().get(0).getValor())) { // Comprueba si el valor ya fue utilizado
                                    arreglo.add(valor);
                                    recorrido(hijo.getHijo(2).getHijo(i + 2), ambito_actual + "." + (cont++) + "_switchCase_statement-case" + valor);
                                } else {
                                    errores_semanticos.add("Error semántico: ya se utiliza el valor " + valor + " dentro del bloque switch case en el ámbito " + ambito_actual);
                                }
                            } else {
                                errores_semanticos.add("Error semántico: se esperan casos de tipo " + tipo + " dentro del bloque Switch Case en el ámbito " + ambito_actual);
                            }
                        }
                    }
                }
            } else if (hijo.getValor().equals("declaración if")) {
                Nodo currentNode = hijo, expresion = hijo.getHijo(1);
                for (int i = 0; i < hijo.getHijos().size(); i++) {
                    if (hijo.getHijo(i).getValor().equals("expresión")) {
                        String tipoId;
                        Boolean pasa;
                        for (int j = 0; j < hijo.getHijo(i).getHijos().size(); j++) {
                            Nodo nodo_actual = hijo.getHijo(i).getHijo(j);
                            if (nodo_actual.getValor().equals("expresión")) {
                                for (int k = 0; k < nodo_actual.getHijos().size(); k++) {
                                    if (nodo_actual.getHijo(k).getValor().equals("id")) {
                                        String id_exp;
                                        if (verificarVariable(nodo_actual.getHijo(k).getValor(), ambito_actual)) {
                                            id_exp = nodo_actual.getHijo(k).getHijo(0).getValor();
                                            
                                            errores_semanticos.add("Error semántico: La variable " + id_exp + " no ha sido declarada con anterioridad, ámbito " + ambito_actual);
                                            //System.out.print("no existe la variable"+nodo_actual.getHijo(k).getHijo(0).getValor());
                                        }
                                    }
                                }
                            } else if (nodo_actual.getValor().equals("id")) {
                                String valor = nodo_actual.getHijo(0).getValor();
                                System.out.println("Entra a la A");
                                if (!verificarVariable(valor, ambito_actual)) {

                                    errores_semanticos.add("Error semántico: La variable " + valor + " no ha sido declarada con anterioridad, ámbito " + ambito_actual);
                                } else {
                                    System.out.print("existe la variabale" + valor);
                                }

                            }
                            Nodo presente = hijo.getHijo(i).getHijo(j);
                            while (presente.getValor().equals(":=") || presente.getValor().equals("<:") || presente.getValor().equals(":>") || presente.getValor().equals("~") || presente.getValor().equals(">=") || presente.getValor().equals("<=") || presente.getValor().equals("||") || presente.getValor().equals("=/=")|| presente.getValor().equals("&&")) {
                                Nodo id_while = presente.getHijo(0).getHijo(0);
                                if (!verificarVariable(id_while.getValor(), ambito_actual) && !isNumeric(id_while.getValor())) {
                                    
                                    errores_semanticos.add("Error semántico: La variable " + id_while.getValor() + " no ha sido declarada con anterioridad, ámbito " + ambito_actual);
                                } else {
                                    System.out.print("existe la variabale" + id_while.getValor());
                                }
                                presente = presente.getHijo(1);
                            }
                        }
                    }
                }

                recorrido(currentNode.getHijo(2), ambito_actual + "." + (cont++) + "_if_statement"); //Cuerpo del if
                if (currentNode.getHijos().size() == 4) { //Verifico si tiene un else o un else if
                    Nodo temp = new Nodo();
                    temp.addHijo(currentNode.getHijo(3));

                    recorrido(temp, ambito_actual); //Mando el else if o else CON EL MÍSMO ÁMBITO
                }

            } else if (hijo.getValor().equals("else if")) {

                Nodo currentNode = hijo, expresion = hijo.getHijo(1);
                for (int i = 0; i < hijo.getHijos().size(); i++) {
                    if (hijo.getHijo(i).getValor().equals("expresión")) {
                        String tipoId;
                        Boolean pasa;
                        for (int j = 0; j < hijo.getHijo(i).getHijos().size(); j++) {
                            Nodo nodo_actual = hijo.getHijo(i).getHijo(j);
                            if (nodo_actual.getValor().equals("expresión")) {
                                for (int k = 0; k < nodo_actual.getHijos().size(); k++) {
                                    if (nodo_actual.getHijo(k).getValor().equals("id")) {
                                        String id_exp;
                                        if (!verificarVariable(nodo_actual.getHijo(k).getValor(), ambito_actual)) {
                                            id_exp = nodo_actual.getHijo(k).getHijo(0).getValor();
                                            errores_semanticos.add("Error semántico: La variable " + id_exp + " no ha sido declarada con anterioridad, ámbito " + ambito_actual);
                                            //System.out.print("no existe la variable"+nodo_actual.getHijo(k).getHijo(0).getValor());
                                        }
                                    }
                                }
                            } else if (nodo_actual.getValor().equals("id")) {
                                String valor = nodo_actual.getHijo(0).getValor();
                                System.out.println("Entra a la A");
                                if (!verificarVariable(valor, ambito_actual)) {

                                    errores_semanticos.add("Error semántico: La variable " + valor + " no ha sido declarada con anterioridad, ámbito " + ambito_actual);
                                } else {
                                    System.out.print("existe la variabale" + valor);
                                }

                            }
                            Nodo presente = hijo.getHijo(i).getHijo(j);
                            while (presente.getValor().equals(":=") || presente.getValor().equals("<:") || presente.getValor().equals(":>") || presente.getValor().equals("~") || presente.getValor().equals(">=") || presente.getValor().equals("<=") || presente.getValor().equals("||") || presente.getValor().equals("=/=")|| presente.getValor().equals("&&")) {
                                Nodo id_while = presente.getHijo(0).getHijo(0);
                                
                                if (!verificarVariable(id_while.getValor(), ambito_actual) && !isNumeric(id_while.getValor())) {
                                    
                                    errores_semanticos.add("Error semántico: La variable " + id_while.getValor() + " no ha sido declarada con anterioridad, ámbito " + ambito_actual);
                                } else {
                                    System.out.print("existe la variabale" + id_while.getValor());
                                }
                                presente = presente.getHijo(1);
                            }
                        }
                    }
                }

                recorrido(currentNode.getHijo(2), ambito_actual + "." + (cont++) + "_elseif_statement"); //Cuerpo del if
                if (currentNode.getHijos().size() == 4) { //Verifico si tiene un else o un else if
                    Nodo temp = new Nodo();
                    temp.addHijo(currentNode.getHijo(3));
                    recorrido(currentNode.getHijo(3), ambito_actual); //Mando el else if o else CON EL MÍSMO ÁMBITO
                }
            } else if (hijo.getValor().equals("else")) {
                Nodo currentNode = hijo;
                recorrido(currentNode.getHijo(0), ambito_actual + "." + (cont++) + "_else");
            } else if (hijo.getValor().equals("declaración ciclo while")) {
                Nodo currentNode = hijo, expresion = hijo.getHijo(1);
                for (int i = 0; i < hijo.getHijos().size(); i++) {
                    if (hijo.getHijo(i).getValor().equals("expresión")) {
                        String tipoId;
                        Boolean pasa;
                        for (int j = 0; j < hijo.getHijo(i).getHijos().size(); j++) {
                            Nodo nodo_actual = hijo.getHijo(i).getHijo(j);
                            if (nodo_actual.getValor().equals("expresión")) {
                                for (int k = 0; k < nodo_actual.getHijos().size(); k++) {
                                    if (nodo_actual.getHijo(k).getValor().equals("id")) {
                                        String id_exp;
                                        if (verificarVariable(nodo_actual.getHijo(k).getValor(), ambito_actual)) {
                                            id_exp = nodo_actual.getHijo(k).getHijo(0).getValor();
                                            errores_semanticos.add("Error semántico: La variable " + id_exp + " no ha sido declarada con anterioridad, ámbito " + ambito_actual);
                                            //System.out.print("no existe la variable"+nodo_actual.getHijo(k).getHijo(0).getValor());
                                        }
                                    }
                                }
                            } else if (nodo_actual.getValor().equals("id")) {
                                String valor = nodo_actual.getHijo(0).getValor();
                                System.out.println("Entra a la A");
                                if (!verificarVariable(valor, ambito_actual)) {

                                    errores_semanticos.add("Error semántico: La variable " + valor + " no ha sido declarada con anterioridad, ámbito " + ambito_actual);
                                } else {
                                    System.out.print("existe la variabale" + valor);
                                }

                            }
                            Nodo presente = hijo.getHijo(i).getHijo(j);
                            while (presente.getValor().equals(":=") || presente.getValor().equals("<:") || presente.getValor().equals(":>") || presente.getValor().equals("~") || presente.getValor().equals(">=") || presente.getValor().equals("<=") || presente.getValor().equals("||") || presente.getValor().equals("=/=")|| presente.getValor().equals("&&")) {
                                Nodo id_while = presente.getHijo(0).getHijo(0);
                                System.out.println(isNumeric(id_while.getValor()));
                                if (!verificarVariable(id_while.getValor(), ambito_actual) ) {

                                    errores_semanticos.add("Error semántico: La variable " + id_while.getValor() + " no ha sido declarada con anterioridad, ámbito " + ambito_actual);
                                } else {
                                    System.out.print("existe la variabale" + id_while.getValor());
                                }
                                presente = presente.getHijo(1);
                            }
                        }
                    }
                }
                recorrido(hijo.getHijo(2), ambito_actual + "." + (cont++) + "_while_statement"); // Cuerpo del while
            }
        }
    }
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static String RecursivaExpresion(Nodo n, String signo) {
        int data1 = 0;
        String dat1 = "";
        String dat2 = "";
        String temporal = "";
        String temp = "";
        System.out.println("Aqui estamos en la funcion===================================================================");
        for (int i = 0; i < n.getHijos().size(); i++) {
            System.out.println("El valor de este ciclo for es: " + n.getHijo(i).getValor());
            if (n.getHijo(i).getValor().equals("expresión")) {
                System.out.println("Ya no se que mas poner para estos prints: " + n.getHijo(i).getValor());
                for (int j = 0; j < n.getHijo(i).getHijos().size(); j++) {
                    switch (n.getHijo(i).getHijo(j).getValor()) {
                        case "+":
                            signo = "+";
                            break;
                        case "-":
                            signo = "-";
                            break;
                        case "/":
                            signo = "/";
                            break;
                        case "*":
                            signo = "*";
                            break;
                    }
                }
                temporal = RecursivaExpresion(n.getHijo(i), signo);
                dat2 = temporal;
            } else if (n.getHijo(i).getValor().equals("num")) {
                dat1 = n.getHijo(i).getHijo(0).getValor();
                for (int j = 0; j < n.getHijos().size(); j++) {
                    Nodo buscar_signo = n.getHijo(j);
                    System.out.println("Objeto del switch:" + buscar_signo.getValor());
                    switch (buscar_signo.getValor()) {
                        case "+":
                            System.out.println("Entre en +");
                        case "-":
                            System.out.println("Entre en -");
                        case "/":
                            System.out.println("Entre en /");
                        case "*":
                            System.out.println("Entre en *");
                            for (int k = 0; k < buscar_signo.getHijos().size(); k++) {
                                if (buscar_signo.getHijo(k).getValor().equals("num")) {
                                    dat2 = buscar_signo.getHijo(k).getHijo(0).getValor();
                                    System.out.println("Este es el valor del dat2:" + dat2);
                                }
                            }

                    }
                }
                System.out.println("Salio del for y sigue en el else if");
                temp = generarTemp();
                cuadruplo.add(new Cuadruplo(signo, dat1, dat2, temp));
                return temp;
            }
            if (n.getHijo(i).getValor().equals("+") || n.getHijo(i).getValor().equals("-") || n.getHijo(i).getValor().equals("*") || n.getHijo(i).getValor().equals("/")) {
                dat1 = n.getHijo(i).getHijo(0).getHijo(0).getValor();
                String genTempSalida = generarTemp();
                cuadruplo.add(new Cuadruplo(n.getHijo(i).getValor(), dat1, dat2, genTempSalida));
            }

        }

        int data2 = 0;
        int retVal = 0;
        return temp;
    }

    public static int getOffset(String tipo, Nodo valores) {
        if (tipo.equals("array()")) {
            offset += valores.getHijos().size() * 4;
            return offset;
        } else if (tipo.equals("array(array())")) {
            int size1 = valores.getHijos().get(0).getHijos().size();
            int size2 = valores.getHijos().get(1).getHijos().size();
            offset += (size1 + size2) * 4;
            return offset;
        }
        return 0;
    }

    // Asigna el offset dependiendo del tipo
    public static int getOffset(String tipo) {
        switch (tipo) { //Aquí valido si se le asigna el tipo correcto
            case "entero":
                offset += 4;
                break;
            case "booleano":
                offset += 1;
                break;
            case "caracter":
                offset += 1;
                break;
            case "string":
                offset += 4;
                break;
            default:
        }
        return offset;
    }

    public static int getOffset(String tipo, String cadena) {
        switch (tipo) { //Aquí valido si se le asigna el tipo correcto
            case "string":
                offset += cadena.length();
                break;
            default:
        }
        return offset;
    }

    // Retorna el tipo que retorna una función con su id
    public static String getTipoFuncion(String funcion) {
        for (Funcion fun : funciones) {
            if (fun.getId().equals(funcion)) {
                return fun.getTipo();
            }
        }
        return null;
    }

    // Verifica si el id de la variable ya existe en el o los ámbitos especificados.
    public static boolean verificarVariable(String variable, String ambito_actual) {
        String value;
        for (int i = 0; i < tabla.size(); i++) {
            if (variable.equals(tabla.get(i).getId()) && ambito_actual.contains(tabla.get(i).getAmbito())) {
                return true;
            }
        }
        return false;
    }

    // Toma la variable dependiendo del ID y del ámbito
    public static Variables getVariable(String variable, String ambito_actual) {
        for (int i = 0; i < tabla.size(); i++) {
            if (variable.equals(tabla.get(i).getId()) && ambito_actual.contains(tabla.get(i).getAmbito())) {
                return tabla.get(i);
            }
        }
        return null;
    }

    // Verifica si la función ya ha sido creada
    public static boolean verificarFuncion(String idFuncion) {
        for (Funcion funcion : funciones) {
            if (funcion.getId().equals(idFuncion)) {
                return true;
            }
        }
        return false;
    }

    // Verifica si la función ya fue creado y retorna la función que se pide
    public static Funcion getFuncion(String idFuncion) {
        for (Funcion funcion : funciones) {
            if (funcion.getId().equals(idFuncion)) {
                return funcion;
            }
        }
        return null;
    }

    // Busca el tipo de la variable con su ID y su ámbito
    public static String getTipoVariable(String id, String ambito) {
        for (Variables variable : tabla) {
            // ambito.contains(variable.getAmbito())
            if (variable.getId().equals(id) && variable.getAmbito().contains(ambito) || variable.getId().equals(id) && ambito.contains(variable.getAmbito())) {
                return variable.getTipo();
            }
        }
        return null;
    }

    // Verifica si el id se encuentra en la lista de parámetros de la función espec
    public static boolean verificarParametroId(ArrayList<Variables> parametros, String id) {
        for (Variables parametro : parametros) {
            if (parametro.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static String verificarParametroId(String idparam, String funcion) {
        for (Funcion fun : funciones) {
            if (fun.getId().equals(funcion)) {
                for (Variables var : fun.getParams()) {
                    if (var.getId().equals(idparam)) {
                        return var.getTipo();
                    }
                }
            }
        }
        return null;
    }

    // ===================================================
    // ===================================================
    // ===================================================
    // ===================================================
    // ============== Código intermedio ==================
    // ===================================================
    public static String generarTemp() {
        cont_temp++;
        return "#t" + cont_temp;
    }

    public static String nuevaEtiqueta() {
        cont_etiq++;
        return "etiq" + cont_etiq;
    }

    public static void intermedio(Nodo body) {
        String id, valor;
        Nodo node = body;
        if (node != null) {
            System.out.println("Aqui entro al switch correcto");
            System.out.println(node.getValor());
            switch (node.getValor()) {
                case "declaración de funcion":
                    id = node.getHijo(2).getHijo(0).getValor();
                    cuadruplo.add(new Cuadruplo("Func", id, "", ""));
                    for (Nodo n : node.getHijo(4).getHijos()) {
                        intermedio(n);
                    }
                    cuadruplo.add(new Cuadruplo("End", "", "", ""));
                    break;
                case "llamada a funcion":
                    String id_param;
                    id = node.getHijo(0).getHijo(0).getValor();
                    Nodo parametros = node.getHijo(1);
                    for (Nodo parametro : parametros.getHijos()) {
                        id_param = parametro.getHijo(0).getValor();
                        cuadruplo.add(new Cuadruplo("Param", id_param, "", ""));
                    }
                    cuadruplo.add(new Cuadruplo("Call", id, "", ""));
                    break;
                case "body":
                    node.setSiguiente(nuevaEtiqueta());
                    for (Nodo hijo : node.getHijos()) {
                        intermedio(hijo);
                        if (hijo.getValor().equals("declaración ciclo for") || hijo.getValor().equals("declaración ciclo while")) {
                            cuadruplo.add(new Cuadruplo("ETIQ", node.getSiguiente(), "", ""));
                            node.setSiguiente(nuevaEtiqueta());
                        }
                    }
                    break;
                case "declaración if":
                        String id_principal ="";
                        String temporal_prin="";
                        String temporalTemp1="";
                        String temporalTemp2="";
                        String temporalTemp3="";
                        for(int i=0;i<node.getHijos().size();i++){
                            if(node.getHijo(i).getValor().equals("expresión")){
                                for(int j=0;j<node.getHijo(i).getHijos().size();j++){
                                    Nodo presente= node.getHijo(i).getHijo(j);
                                    if(presente.getValor().equals("id")){
                                        id_principal=node.getHijo(i).getHijo(j).getHijo(0).getValor();
                                        
                                    }else if(presente.getValor().equals("num")){
                                        id_principal=node.getHijo(i).getHijo(j).getHijo(0).getValor();
                                    }while(presente.getValor().equals(":=") || presente.getValor().equals("<:") || presente.getValor().equals(":>") || presente.getValor().equals("~") || presente.getValor().equals(">=") || presente.getValor().equals("<=") || presente.getValor().equals("||") || presente.getValor().equals("=/=")|| presente.getValor().equals("&&")){
                                        String signo = presente.getValor();
                                        temporalTemp2=generarTemp();
                                        String numerito = presente.getHijo(0).getHijo(0).getValor();
                                        cuadruplo.add(new Cuadruplo(signo,numerito,temporalTemp3,temporalTemp2));
                                        presente=presente.getHijo(1);
                                        temporalTemp3=temporalTemp2;
                                        temporalTemp1=temporalTemp2;
                                    }
                                }
                            }
                        }
                        cuadruplo.add(new Cuadruplo("=",temporalTemp1,"",id_principal));
                        node.getHijo(1).setVerdadero(nuevaEtiqueta());
                        node.getHijo(1).setFalso(node.padre.getSiguiente());
                        intermedio(node.getHijo(1));
                        cuadruplo.add(new Cuadruplo("ETIQ", node.getHijo(1).getVerdadero(), "", ""));
                        intermedio(node.getHijo(2));
                    
                    break;
                case "declaración ciclo while":
                    node.setComienzo(nuevaEtiqueta());
                    node.getHijo(1).setVerdadero(nuevaEtiqueta());
                    node.getHijo(1).setFalso(node.padre.getSiguiente());
                    cuadruplo.add(new Cuadruplo("ETIQ", node.getComienzo(), "", ""));
                    intermedio(node.getHijo(1));
                    cuadruplo.add(new Cuadruplo("ETIQ", node.getHijo(1).getVerdadero(), "", ""));
                    intermedio(node.getHijo(2));
                    cuadruplo.add(new Cuadruplo("GOTO", node.getComienzo(), "", ""));
                    break;
                case "expresión for":
                    String etiquetaVerdadera = nuevaEtiqueta();
                    node.padre.setVerdadero(etiquetaVerdadera);
                    node.padre.setFalso(node.padre.padre.getSiguiente());
                    // LA ASIGNACIÓN X = 0
                    id = node.getHijo(0).getHijo(0).getValor();
                    valor = node.getHijo(1).getHijo(0).getValor();
                    cuadruplo.add(new Cuadruplo("=", valor, "", id));
                    // === 
                    cuadruplo.add(new Cuadruplo("ETIQ", node.padre.getComienzo(), "", ""));
                    // LA CONDICIÓN X < Z
                    String num = node.getHijo(2).getHijo(0).getValor();
                    cuadruplo.add(new Cuadruplo("IF<", id, num, node.padre.getVerdadero()));
                    cuadruplo.add(new Cuadruplo("GOTO", node.padre.getFalso(), "", ""));
                    // ===
                    String etiqueta = nuevaEtiqueta();
                    cuadruplo.add(new Cuadruplo("ETIQ", etiqueta, "", ""));
                    // LA SUMA  X = X + 1
                    String temp = generarTemp();
                    cuadruplo.add(new Cuadruplo("+", id, "1", temp));
                    cuadruplo.add(new Cuadruplo("=", temp, "", id));
                    // ===
                    cuadruplo.add(new Cuadruplo("GOTO", node.padre.getComienzo(), "", ""));
                    cuadruplo.add(new Cuadruplo("ETIQ", node.padre.getVerdadero(), "", ""));
                    intermedio(node.padre.getHijo(1));
                    cuadruplo.add(new Cuadruplo("GOTO", etiqueta, "", ""));
                    break;
                case "declaración ciclo for":
                    node.setComienzo(nuevaEtiqueta());
                    intermedio(node.getHijo(0));
                    break;
                case "declaracion y asignacion":
                    System.out.println("Esta entrando en el incorrecto");
                    id = node.getHijo(2).getHijo(0).getValor();
                    valor = node.getHijo(3).getHijo(0).getHijo(0).getValor();
                    cuadruplo.add(new Cuadruplo("=", valor, "", id));
                    break;
//=============================================================================================================================================================================================================INICIO DE LA MUERTE
                case "declaracion y asignacion expresión":
                    String tempTemporal_prin = " ";
                    String tempTemporal1 = "";
                    String tempTemporal2 = "";
                    String tempTemporal3 = "";
                    String tempTemporal4 = "";
                    id_principal = node.getHijo(2).getHijo(0).getValor();
                    for (int i = 0; i < node.getHijo(3).getHijos().size(); i++) {
                        id = node.getHijo(3).getHijo(i).getValor();
                        System.out.println(id);
                        if (id.equals("expresión")) {
                            for (int j = 0; j < node.getHijo(3).getHijo(i).getHijos().size(); j++) {
                                String id2 = node.getHijo(3).getHijo(i).getHijo(j).getValor();
                                System.out.println(id2);
                                if (id2.equals("expresión")) {
                                    for (int k = 0; k < node.getHijo(3).getHijo(i).getHijo(j).getHijos().size(); k++) {
                                        String id3 = node.getHijo(3).getHijo(i).getHijo(j).getHijo(k).getValor();
                                        System.out.println(id3);
                                        if (id3.equals("expresión")) {
                                            for (int l = 0; l < node.getHijo(3).getHijo(i).getHijo(j).getHijo(k).getHijos().size(); l++) {
                                                String id4 = node.getHijo(3).getHijo(i).getHijo(j).getHijo(k).getHijo(l).getValor();
                                                System.out.println(id4);
                                                if (id4.equals("num")) {
                                                    System.out.println("Ya es el num4");
                                                } else if (id4.equals("id")) {
                                                    System.out.println("Ya llegamos al id4");
                                                } else if (id4.equals("+") || id.equals("-") || id.equals("*") || id.equals("/")) {
                                                    System.out.println("Ya estamos en simbolos4");
                                                }
                                            }
                                        } else if (id3.equals("num")) {
                                            System.out.println("Ya es el num3");
                                        } else if (id3.equals("id")) {
                                            System.out.println("Ya llegamos al id3");
                                        } else if (id3.equals("+") || id.equals("-") || id.equals("*") || id.equals("/")) {
                                            System.out.println("Ya estamos en simbolos3");
                                        }
                                    }
                                } else if (id2.equals("num")) {
                                    System.out.println("Ya es el num2");
                                    String numero = node.getHijo(3).getHijo(i).getHijo(j).getHijo(0).getValor();
                                    String nuevo2 = node.getHijo(3).getHijo(i).getHijo(1).getValor();
                                    String nuevo3 = node.getHijo(3).getHijo(i).getHijo(2).getValor();
                                    if (nuevo2.equals("+") || nuevo2.equals("-") || nuevo2.equals("*") || nuevo2.equals("/")) {
                                        System.out.println("A ver que tiene esto: " + nuevo2);
                                        String signo = nuevo2;
                                        String numero2 = node.getHijo(3).getHijo(i).getHijo(1).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    } else if (nuevo3.equals("+") || nuevo3.equals("-") || nuevo3.equals("*") || nuevo3.equals("/")) {
                                        System.out.println("A ver que tiene esto: " + nuevo3);
                                        String signo = nuevo3;
                                        String numero2 = node.getHijo(3).getHijo(i).getHijo(2).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    }
                                } else if (id2.equals("id")) {
                                    System.out.println("Ya llegamos al id2");
                                    String numero = node.getHijo(3).getHijo(i).getHijo(j).getHijo(0).getValor();
                                    String nuevo2 = node.getHijo(3).getHijo(i).getHijo(1).getValor();
                                    String nuevo3 = node.getHijo(3).getHijo(i).getHijo(2).getValor();
                                    if (nuevo2.equals("+") || nuevo2.equals("-") || nuevo2.equals("*") || nuevo2.equals("/")) {
                                        System.out.println("A ver que tiene esto: " + nuevo2);
                                        String signo = nuevo2;
                                        String numero2 = node.getHijo(3).getHijo(i).getHijo(1).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    } else if (nuevo3.equals("+") || nuevo3.equals("-") || nuevo3.equals("*") || nuevo3.equals("/")) {
                                        System.out.println("A ver que tiene esto: " + nuevo3);
                                        String signo = nuevo3;
                                        String numero2 = node.getHijo(3).getHijo(i).getHijo(2).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    }
                                }
                                
                            }
                        } else if (id.equals("num")) {
                            
                        } else if (id.equals("id")) {
                            System.out.println("Ya llegamos al id");
                        } else if (id.equals("+") || id.equals("-") || id.equals("*") || id.equals("/")) {
                            
                            for(int r=0;r<node.getHijo(3).getHijo(i).getHijos().size();r++){
                                String signo_for= node.getHijo(3).getHijo(i).getHijo(r).getValor();
                                if(signo_for.equals("expresión")){                  //si se encuentra una expresion adentro de los signos
                                    String numero = node.getHijo(3).getHijo(i).getHijo(r).getHijo(0).getHijo(0).getValor();
                                    String signo =  node.getHijo(3).getHijo(i).getHijo(r).getHijo(1).getValor();
                                    String numero2 =  node.getHijo(3).getHijo(i).getHijo(r).getHijo(1).getHijo(0).getValor();
                                    tempTemporal4=generarTemp();
                                    cuadruplo.add(new Cuadruplo(signo,numero,numero2,tempTemporal4));
                                } else if(signo_for.equals("+") || signo_for.equals("-") || signo_for.equals("*") || signo_for.equals("/")){ //signos despues de la expresion
                                    if(node.getHijo(3).getHijo(i).getHijo(r).getHijo(1).getHijos().size() == 1){   //se mira si hay signos adentro de los signos
                                    String numero = node.getHijo(3).getHijo(i).getHijo(r).getHijo(0).getHijo(0).getValor();
                                    String signo =  node.getHijo(3).getHijo(i).getHijo(r).getHijo(1).getValor();
                                    tempTemporal3 = generarTemp();
                                    cuadruplo.add(new Cuadruplo(signo,numero,tempTemporal4,tempTemporal3));
                                    tempTemporal4 = tempTemporal3;
                                    
                                }
                            }
                        }
                        }
                        if(id.equals("+") || id.equals("-") || id.equals("*") || id.equals("/")){
                            String signo =id;
                            String numero_sig=node.getHijo(3).getHijo(i).getHijo(0).getHijo(0).getValor();
                            tempTemporal1 = generarTemp();
                            cuadruplo.add(new Cuadruplo(signo,tempTemporal2,numero_sig,tempTemporal1));

                        }

                    }
                    tempTemporal_prin= generarTemp();
                    cuadruplo.add(new Cuadruplo("=",tempTemporal1,"",id_principal));
                    
                    break;
                case "expresión":                                                                           // EXPRESIONES SEMANTICO
                    tempTemporal_prin = " ";
                    tempTemporal1 = "";
                    tempTemporal2 = "";
                    tempTemporal3 = "";
                    tempTemporal4 = "";
                    
                    for (int i = 0; i < node.getHijos().size(); i++) {
                        id = node.getHijo(i).getValor();
                        //System.out.println(id);
                        
                        if (id.equals("expresión")) {                                       //expresiones
                            for (int j = 0; j < node.getHijo(i).getHijos().size(); j++) {
                                String id2 = node.getHijo(i).getHijo(j).getValor();
                                System.out.println(id2);
                                if (id2.equals("expresión")) {                          //expresiones de 1 grado
                                    for (int k = 0; k < node.getHijo(3).getHijo(i).getHijo(j).getHijos().size(); k++) {
                                        String id3 = node.getHijo(3).getHijo(i).getHijo(j).getHijo(k).getValor();
                                        System.out.println(id3);
                                        if (id3.equals("expresión")) {                          //expresiones de 2 grado
                                            for (int l = 0; l < node.getHijo(3).getHijo(i).getHijo(j).getHijo(k).getHijos().size(); l++) {
                                                String id4 = node.getHijo(3).getHijo(i).getHijo(j).getHijo(k).getHijo(l).getValor();
                                                System.out.println(id4);
                                                if (id4.equals("num")) {
                                                    System.out.println("Ya es el num4");
                                                } else if (id4.equals("id")) {
                                                    System.out.println("Ya llegamos al id4");
                                                } else if (id4.equals("+") || id.equals("-") || id.equals("*") || id.equals("/")) {
                                                    System.out.println("Ya estamos en simbolos4");
                                                }
                                            }
                                        } else if (id3.equals("num")) {
                                            System.out.println("Ya es el num3");
                                        } else if (id3.equals("id")) {
                                            System.out.println("Ya llegamos al id3");
                                        } else if (id3.equals("+") || id.equals("-") || id.equals("*") || id.equals("/")) {
                                            System.out.println("Ya estamos en simbolos3");
                                        }
                                    }
                                } else if (id2.equals("num")) {             //se encuentra num en segundo grado
                                    System.out.println("Ya es el num2");
                                    String numero = node.getHijo(3).getHijo(i).getHijo(j).getHijo(0).getValor();
                                    String nuevo2 = node.getHijo(3).getHijo(i).getHijo(1).getValor();
                                    String nuevo3 = node.getHijo(3).getHijo(i).getHijo(2).getValor();
                                    if (nuevo2.equals("+") || nuevo2.equals("-") || nuevo2.equals("*") || nuevo2.equals("/")) {
                                        System.out.println("A ver que tiene esto: " + nuevo2);
                                        String signo = nuevo2;
                                        String numero2 = node.getHijo(3).getHijo(i).getHijo(1).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    } else if (nuevo3.equals("+") || nuevo3.equals("-") || nuevo3.equals("*") || nuevo3.equals("/")) {
                                        System.out.println("A ver que tiene esto: " + nuevo3);
                                        String signo = nuevo3;
                                        String numero2 = node.getHijo(3).getHijo(i).getHijo(2).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    }
                                } else if (id2.equals("id")) {          //se encuentra id en segundo grado
                                    System.out.println("Ya llegamos al id2");
                                    String numero = node.getHijo(i).getHijo(0).getHijo(0).getValor();
                                    System.out.println("Ya llegamos al id2"+numero);
                                    String nuevo2 = node.getHijo(i).getHijo(1).getValor();
                                    String nuevo3 = node.getHijo(i).getHijo(2).getValor();
                                    if (nuevo2.equals("+") || nuevo2.equals("-") || nuevo2.equals("*") || nuevo2.equals("/")) {
                                        System.out.println("A ver que tiene esto: " + nuevo2);
                                        String signo = nuevo2;
                                        String numero2 = node.getHijo(i).getHijo(1).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    } else if (nuevo3.equals("+") || nuevo3.equals("-") || nuevo3.equals("*") || nuevo3.equals("/")) {
                                        System.out.println("A ver que tiene esto: " + nuevo3);
                                        String signo = nuevo3;
                                        String numero2 = node.getHijo(i).getHijo(2).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    }else if(nuevo2.equals(":=") || nuevo2.equals("<:") || nuevo2.equals(":>") || nuevo2.equals("~") || nuevo2.equals(">=") || nuevo2.equals("<=") || nuevo2.equals("||") || nuevo2.equals("=/=")|| nuevo2.equals("&&")){
                                        Nodo presente=node.getHijo(i).getHijo(1);
                                        String signo ="";
                                        while(presente.getValor().equals(":=") || presente.getValor().equals("<:") || presente.getValor().equals(":>") || presente.getValor().equals("~") || presente.getValor().equals(">=") || presente.getValor().equals("<=") || presente.getValor().equals("||") || presente.getValor().equals("=/=")|| presente.getValor().equals("&&")){
                                            signo = presente.getValor();
                                            String numerin = presente.getHijo(0).getHijo(0).getValor();
                                            tempTemporal3=generarTemp();
                                            cuadruplo.add(new Cuadruplo(signo,numerin,"",tempTemporal3));
                                            presente=presente.getHijo(1);
                                            tempTemporal2=tempTemporal3;
                                        }
                                        System.out.println("A ver que tiene esto: " + nuevo2);
                                        signo = nuevo2;
                                        String numero2 = node.getHijo(i).getHijo(1).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    }else if(nuevo3.equals(":=") || nuevo3.equals("<:") || nuevo3.equals(":>") || nuevo3.equals("~") || nuevo3.equals(">=") || nuevo3.equals("<=") || nuevo3.equals("||") || nuevo3.equals("=/=")|| nuevo3.equals("&&")){
                                        System.out.println("A ver que tiene esto: " + nuevo2);
                                        String signo = nuevo3;
                                        String numero2 = node.getHijo(i).getHijo(2).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    }
                                }
                                
                            }
                        } else if (id.equals("num")) {
                                    String numero = node.getHijo(i).getHijo(0).getValor();
                                    System.out.println("Ya llegamos al id1"+numero);
                                    String nuevo2 = node.getHijo(1).getValor();
                                    String nuevo3 = node.getHijo(2).getValor();
                                    if (nuevo2.equals("+") || nuevo2.equals("-") || nuevo2.equals("*") || nuevo2.equals("/")) {
                                        System.out.println("A ver que tiene esto: " + nuevo2);
                                        String signo = nuevo2;
                                        String numero2 = node.getHijo(i).getHijo(1).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    } else if (nuevo3.equals("+") || nuevo3.equals("-") || nuevo3.equals("*") || nuevo3.equals("/")) {
                                        System.out.println("A ver que tiene esto: " + nuevo3);
                                        String signo = nuevo3;
                                        String numero2 = node.getHijo(2).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    }else if(nuevo2.equals(":=") || nuevo2.equals("<:") || nuevo2.equals(":>") || nuevo2.equals("~") || nuevo2.equals(">=") || nuevo2.equals("<=") || nuevo2.equals("||") || nuevo2.equals("=/=")|| nuevo2.equals("&&")){
                                        Nodo presente=node.getHijo(1);
                                        String signo ="";
                                        while(presente.getValor().equals(":=") || presente.getValor().equals("<:") || presente.getValor().equals(":>") || presente.getValor().equals("~") || presente.getValor().equals(">=") || presente.getValor().equals("<=") || presente.getValor().equals("||") || presente.getValor().equals("=/=")|| presente.getValor().equals("&&")){
                                            signo = presente.getValor();
                                            String numerin = presente.getHijo(0).getHijo(0).getValor();
                                            tempTemporal3=generarTemp();
                                            cuadruplo.add(new Cuadruplo(signo,numerin,"",tempTemporal3));
                                            presente=presente.getHijo(1);
                                            tempTemporal2=tempTemporal3;
                                        }
                                        System.out.println("A ver que tiene esto: " + nuevo2);
                                        signo = nuevo2;
                                        String numero2 = node.getHijo(1).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    }else if(nuevo3.equals(":=") || nuevo3.equals("<:") || nuevo3.equals(":>") || nuevo3.equals("~") || nuevo3.equals(">=") || nuevo3.equals("<=") || nuevo3.equals("||") || nuevo3.equals("=/=")|| nuevo3.equals("&&")){
                                        System.out.println("A ver que tiene esto: " + nuevo2);
                                        String signo = nuevo3;
                                        String numero2 = node.getHijo(2).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    }
                        } else if (id.equals("id")) {
                                    
                                    String numero = node.getHijo(i).getHijo(0).getValor();
                                    System.out.println("Ya llegamos al id1"+numero);
                                    String nuevo2 = node.getHijo(1).getValor();
                                    String nuevo3 = node.getHijo(2).getValor();
                                    if (nuevo2.equals("+") || nuevo2.equals("-") || nuevo2.equals("*") || nuevo2.equals("/")) {
                                        System.out.println("A ver que tiene esto: " + nuevo2);
                                        String signo = nuevo2;
                                        String numero2 = node.getHijo(i).getHijo(1).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    } else if (nuevo3.equals("+") || nuevo3.equals("-") || nuevo3.equals("*") || nuevo3.equals("/")) {
                                        System.out.println("A ver que tiene esto: " + nuevo3);
                                        String signo = nuevo3;
                                        String numero2 = node.getHijo(2).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    }else if(nuevo2.equals(":=") || nuevo2.equals("<:") || nuevo2.equals(":>") || nuevo2.equals("~") || nuevo2.equals(">=") || nuevo2.equals("<=") || nuevo2.equals("||") || nuevo2.equals("=/=")|| nuevo2.equals("&&")){
                                        Nodo presente=node.getHijo(1);
                                        String signo ="";
                                        while(presente.getValor().equals(":=") || presente.getValor().equals("<:") || presente.getValor().equals(":>") || presente.getValor().equals("~") || presente.getValor().equals(">=") || presente.getValor().equals("<=") || presente.getValor().equals("||") || presente.getValor().equals("=/=")|| presente.getValor().equals("&&")){
                                            signo = presente.getValor();
                                            String numerin = presente.getHijo(0).getHijo(0).getValor();
                                            tempTemporal3=generarTemp();
                                            cuadruplo.add(new Cuadruplo(signo,numerin,"",tempTemporal3));
                                            presente=presente.getHijo(1);
                                            tempTemporal2=tempTemporal3;
                                        }
                                        System.out.println("A ver que tiene esto: " + nuevo2);
                                        signo = nuevo2;
                                        String numero2 = node.getHijo(1).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    }else if(nuevo3.equals(":=") || nuevo3.equals("<:") || nuevo3.equals(":>") || nuevo3.equals("~") || nuevo3.equals(">=") || nuevo3.equals("<=") || nuevo3.equals("||") || nuevo3.equals("=/=")|| nuevo3.equals("&&")){
                                        System.out.println("A ver que tiene esto: " + nuevo2);
                                        String signo = nuevo3;
                                        String numero2 = node.getHijo(2).getHijo(0).getHijo(0).getValor();
                                        tempTemporal2 = generarTemp();
                                        cuadruplo.add(new Cuadruplo(signo, numero, numero2, tempTemporal2));
                                    }
                        } else if (id.equals("+") || id.equals("-") || id.equals("*") || id.equals("/")) {
                            
                            for(int r=0;r<node.getHijo(3).getHijo(i).getHijos().size();r++){
                                String signo_for= node.getHijo(3).getHijo(i).getHijo(r).getValor();
                                if(signo_for.equals("expresión")){                  //si se encuentra una expresion adentro de los signos
                                    String numero = node.getHijo(i).getHijo(r).getHijo(0).getHijo(0).getValor();
                                    String signo =  node.getHijo(i).getHijo(r).getHijo(1).getValor();
                                    String numero2 =  node.getHijo(i).getHijo(r).getHijo(1).getHijo(0).getValor();
                                    tempTemporal4=generarTemp();
                                    cuadruplo.add(new Cuadruplo(signo,numero,numero2,tempTemporal4));
                                } else if(signo_for.equals("+") || signo_for.equals("-") || signo_for.equals("*") || signo_for.equals("/")){ //signos despues de la expresion
                                    if(node.getHijo(3).getHijo(i).getHijo(r).getHijo(1).getHijos().size() == 1){   //se mira si hay signos adentro de los signos
                                    String numero = node.getHijo(i).getHijo(r).getHijo(0).getHijo(0).getValor();
                                    String signo =  node.getHijo(i).getHijo(r).getHijo(1).getValor();
                                    tempTemporal3 = generarTemp();
                                    cuadruplo.add(new Cuadruplo(signo,numero,tempTemporal4,tempTemporal3));
                                    tempTemporal4 = tempTemporal3;
                                    
                                }
                            }
                        }
                        }
                        if(id.equals("+") || id.equals("-") || id.equals("*") || id.equals("/")){
                            String signo =id;
                            String numero_sig=node.getHijo(i).getHijo(0).getHijo(0).getValor();
                            tempTemporal1 = generarTemp();
                            cuadruplo.add(new Cuadruplo(signo,tempTemporal2,numero_sig,tempTemporal1));

                        }

                    }
                    tempTemporal_prin= generarTemp();
                    cuadruplo.add(new Cuadruplo("=",tempTemporal1,"",""));
                    
                    break;
                case "asignacion":
                    valor="";
                    id="";
                    for (int i=0;i<node.getHijos().size();i++){
                        if(node.getHijo(i).getValor().equals("id")){
                            id = node.getHijo(i).getHijo(0).getValor();
                        }else if(node.getHijo(i).getValor().equals("valor")){
                            valor = node.getHijo(i).getHijo(0).getHijo(0).getValor();
                        }
                        cuadruplo.add(new Cuadruplo("=", valor, "", id));
                       
                    }
                    break;
                case "asignación expresión":

                    break;
                case "expresion":
                    System.out.println("FALTA UNA EXPRESIÓN AAAAH");
                    break;

                case "catch":
                    id = node.getHijo(0).getHijo(0).getValor();
                    cuadruplo.add(new Cuadruplo("Catch", "", "", id));
                    break;
                case "throw":
                    id = node.getHijo(0).getHijo(0).getValor();
                    cuadruplo.add(new Cuadruplo("Throw", id, "", ""));
                    break;
                case "throwdown":
                    id = node.getHijo(0).getHijo(0).getValor();
                    cuadruplo.add(new Cuadruplo("Throwdown", id, "", ""));
                    break;
                case "reply":
                    id = node.getHijo(0).getHijo(0).getValor();
                    cuadruplo.add(new Cuadruplo("Ret", id, "", ""));
                    break;
                default:
            }
        }

    }

    // ===================================================
    // ===================================================
    // ===================================================
    // ===================================================
    // ===================================================
    // ===================================================
    // ===================================================
    // ================= Código final ====================
    // ===================================================
    public static void codigo_final() {
        ArrayList<Temporal> temporales = new ArrayList();
        ArrayList<Temporal> parametros = new ArrayList();
        int arg = 0;
        int contA = 0;
        int contP = 0;
        for (int i = 0; i < 10; i++) {
            temporales.add(new Temporal(i, "", false));
        }
        String codigoFinal = "";
        codigoFinal += ".data\n";
        for (int i = 0; i < tabla.size(); i++) {
            if (tabla.get(i).getAmbito().equals("Start")) {
                if (tabla.get(i).getTipo().equals("caracter")) {
                    codigoFinal += "_" + tabla.get(i).getId() + ":      .byte 0\n";
                } else {
                    codigoFinal += "_" + tabla.get(i).getId() + ":      .word 0\n";
                }
            }
        }
        // Crea los mensajes
        for (int i = 0; i < mensajes.size(); i++) {
            codigoFinal += "_msg" + ((i) + 1) + ":     .asciiz \"" + mensajes.get(i) + "\"\n";
        }
        codigoFinal += "   .text\n"
                + "   .globl main\n";

        // Lo demás
        for (Cuadruplo currentCuadruplo : cuadruplo) {
            switch (currentCuadruplo.getOperador()) {
                case "Func":
                    ambito_siguiente = currentCuadruplo.getArgumento1();
                    parametros.clear();
                    if (currentCuadruplo.getArgumento1().equals("main")) {
                        codigoFinal += "main:\n";
                        codigoFinal += "       move $fp, $sp\n";
                    } else {
                        int paramCount = getParamCount();
                        int temporal = 0;
                        int pila = 0;
                        codigoFinal += "_" + currentCuadruplo.getArgumento1() + ":\n";
                        for (Variables variable : tabla) {
                            if (variable.getAmbito().equals(ambito_siguiente)) {
                                if (temporal >= 4 && temporal < paramCount) {
                                    if (variable.getTipo().equals("caracter")) {
                                        pila++;
                                        codigoFinal += "        lb $s" + temporal + ", -" + pila + "($sp)\n";
                                    } else {
                                        pila += 4;
                                        codigoFinal += "        lw $s" + temporal + ", -" + pila + "($sp)\n";
                                    }
                                }
                                temporal++;
                            }
                        }
                        codigoFinal += "       sw $fp, -4($sp)\n";
                        codigoFinal += "       sw $ra, -8($sp)\n";
                        // Parámetros
                        String tempOffset = "0";
                        int cont = 0;

                        Funcion f = getFuncion(ambito_siguiente);
                        for (Variables param : f.getParams()) {
                            if (cont < paramCount) {
                                if (param.getTipo().equals("caracter")) {
                                    codigoFinal += "       sb $s" + cont + ", -" + (Integer.parseInt(param.getOffset()) + 12) + "($sp)\n";
                                } else {
                                    codigoFinal += "       sw $s" + cont + ", -" + (Integer.parseInt(param.getOffset()) + 12) + "($sp)\n";
                                }
                                parametros.add(new Temporal(cont, param.getId(), true, param.getTipo()));
                                cont++;
                            }
                            tempOffset = param.getOffset();
                        }
                        codigoFinal += "       move $fp, $sp\n";
                        codigoFinal += "       sub $sp, $sp, " + tempOffset + "\n";
                        for (int i = 0; i < paramCount; i++) {
                            if (i < 4) {
                                codigoFinal += "       move $s" + i + ", $a" + i + "\n";
                            }
                        }
                    }
                    break;
                case "Param":
                    if (currentCuadruplo.getArgumento1().contains("'") || currentCuadruplo.getArgumento1().matches("[0-9]+")) {
                        if (arg >= 4) {
                            int temp = 0;
                            for (int i = 0; i < temporales.size(); i++) {
                                if (!temporales.get(i).isVivo()) {
                                    temp = i;
                                    break;
                                }
                            }
                            codigoFinal += "       li $t" + temp + ", " + currentCuadruplo.getArgumento1() + "\n";
                            if (currentCuadruplo.getArgumento1().contains("'")) {
                                contA += 1;
                                codigoFinal += "       sb $t" + temp + ", -" + contA + "($sp)\n";
                            } else {
                                contA += 4;
                                codigoFinal += "       sw $t" + temp + ", -" + contA + "($sp)\n";
                            }
                        } else {
                            codigoFinal += "       li $a" + arg + ", " + currentCuadruplo.getArgumento1() + "\n";
                        }
                    } else {
                        if (isLocalVariable(currentCuadruplo.getArgumento1())) {
                            int par = getParamIndex(parametros, currentCuadruplo.getArgumento1());
                            if (isParameter(currentCuadruplo.getArgumento1(), codigoFinal)) {
                                if (arg >= 4) {
                                    if (parametros.get(par).getTipo().equals("caracter")) {
                                        contA += 1;
                                        codigoFinal += "       sb $s" + par + ", -" + contA + "($sp)\n";
                                    } else {
                                        contA += 4;
                                        codigoFinal += "       sw $s" + par + ", -" + contA + "($sp)\n";
                                    }
                                } else {
                                    codigoFinal += "       move $a" + arg + ", $s" + par + "($sp)\n";
                                }
                            } else {
                                int temp = 0;
                                for (int i = 0; i < temporales.size(); i++) {
                                    temp = i;
                                    break;
                                }
                                if (isCharacter(currentCuadruplo.getArgumento1())) {
                                    if (arg >= 4) {
                                        contA += 1;
                                        codigoFinal += "       lb $t" + temp + ", -" + getOffsetVariable(currentCuadruplo.getArgumento1()) + "($fp)\n";
                                        codigoFinal += "       sb $t" + temp + ", -" + contA + "($sp)\n";
                                    } else {
                                        codigoFinal += "       lb $a" + arg + ", -" + getOffsetVariable(currentCuadruplo.getArgumento1()) + "($fp)\n";
                                    }
                                } else {
                                    if (arg >= 4) {
                                        contA += 4;
                                        codigoFinal += "       lw $t" + temp + ", -" + getOffsetVariable(currentCuadruplo.getArgumento1()) + "($fp)\n";
                                        codigoFinal += "       sw $t" + temp + ", -" + contA + "($sp)\n";
                                    } else {
                                        codigoFinal += "       lw $a" + arg + ", -" + getOffsetVariable(currentCuadruplo.getArgumento1()) + "($fp)\n";
                                    }
                                }
                            }
                        } else {
                            if (arg >= 4) {
                                int t = 0;
                                for (int i = 0; i < temporales.size(); i++) {
                                    if (!temporales.get(i).isVivo()) {
                                        t = i;
                                        break;
                                    }
                                }
                                if (isCharacter(currentCuadruplo.getArgumento1())) {
                                    contA += 1;
                                    codigoFinal += "       lb $t" + t + ", _" + currentCuadruplo.getArgumento1() + "\n";
                                    codigoFinal += "       sb $t" + t + ", -" + contA + "($sp)\n";
                                } else {
                                    contA += 4;
                                    codigoFinal += "       lw $t" + t + ", _" + currentCuadruplo.getArgumento1() + "\n";
                                    codigoFinal += "       sw $t" + t + ", -" + contA + "($sp)\n";
                                }
                            } else {
                                codigoFinal += "       lw $a" + arg + ", _" + currentCuadruplo.getArgumento1() + "\n";
                            }
                        }
                    }
                    arg++;
                    break;
                case "Ret":
                    if (ambito_siguiente.equals("main")) {
                        if (currentCuadruplo.getArgumento1().contains("'") || currentCuadruplo.getArgumento1().matches("[0-9]+")) {
                            codigoFinal += "       li $v0, " + currentCuadruplo.getArgumento1() + "\n";
                        } else if (currentCuadruplo.getArgumento1().contains("#t")) {
                            int tempRet = 0;
                            for (int i = 0; i < temporales.size(); i++) {
                                if (temporales.get(i).isVivo() && temporales.get(i).getActivado().equals(currentCuadruplo.getArgumento1())) {
                                    tempRet = i;
                                }
                            }
                            temporales.get(tempRet).setVivo(false);
                            temporales.get(tempRet).setActivado("");
                            codigoFinal += "       move $v0, $t" + tempRet + "\n";
                        }
                    } else {
                        if (isLocalVariable(currentCuadruplo.getArgumento1())) {
                            if (isParameter(currentCuadruplo.getArgumento1(), codigoFinal)) {
                                codigoFinal += "        move $v0, $s" + getParamIndex(parametros, currentCuadruplo.getArgumento1()) + "\n";
                            } else {
                                if (isCharacterFunc(currentCuadruplo.getArgumento1())) {
                                    codigoFinal += "       lb $v0, -" + getOffsetVariable(currentCuadruplo.getArgumento1()) + "($fp)\n";
                                } else {
                                    codigoFinal += "       lw $v0, -" + getOffsetVariable(currentCuadruplo.getArgumento1()) + "($fp)\n";
                                }
                            }
                        } else {
                            codigoFinal += "       lw $v0, _" + currentCuadruplo.getArgumento1() + "\n";
                        }
                        codigoFinal += "       b _fin_" + ambito_siguiente + "\n";
                    }
                    break;
                case "End":
                    if (ambito_siguiente.equals("main")) {
                        codigoFinal += "       li $v0, 10\n";
                        codigoFinal += "       syscall\n";
                    } else {
                        int cont = 0;
                        int contParam = getParamCount();
                        codigoFinal += "_fin_" + ambito_siguiente + ":\n"
                                + "       move $fp, $fp\n"
                                + "       lw $fp, -4($sp)\n"
                                + "       lw $ra, -8($sp)\n";
                        for (Variables variable : tabla) {
                            if (cont < contParam) {
                                if (variable.getAmbito().equals("caracter")) {
                                    codigoFinal += "       lb $s" + cont + ", -" + variable.getOffset() + "($sp)\n";
                                } else {
                                    codigoFinal += "       lw $s" + cont + ", -" + variable.getOffset() + "($sp)\n";
                                }
                                cont++;
                            }
                        }
                        codigoFinal += "       jr $ra\n";
                    }
                    break;
                case "Call":
                    int cont = 0;
                    for (int i = 0; i < parametros.size(); i++) {
                        if (parametros.get(i).isVivo()) {
                            cont += 4;
                            codigoFinal += "     sw $t" + i + ",-" + cont + "($sp)\n";
                        }
                    }
                    if (cont > 0) {
                        codigoFinal += "       sub $sp, $sp, " + cont + "\n";
                    }
                    codigoFinal += "       jal _" + currentCuadruplo.getArgumento1() + "\n";
                    for (int i = parametros.size() - 1; i >= 0; i--) {
                        if (parametros.get(i).isVivo()) {
                            codigoFinal += "       lw $t" + i + ",0($sp)\n"
                                    + "       add $sp,$sp,4\n";
                        }
                    }
                    arg = 0;
                    contA = 0;
                    break;
                case "ETIQ":
                    codigoFinal += "_" + currentCuadruplo.getArgumento1() + ":\n";
                    break;
                case "GOTO":
                    codigoFinal += "       b _" + currentCuadruplo.getArgumento1() + "\n";
                    break;
                case "=":
                    String num = "[0-9]+";
                    boolean pass = false;
                    int asig = 0;
                    int temp = 0;
                    for (int i = 0; i < 10; i++) {
                        if (temporales.get(i).isVivo() && temporales.get(i).getActivado().equals(currentCuadruplo.getArgumento1())) {
                            asig = i;
                            pass = true;
                        }
                    }
                    for (int i = 0; i < 10; i++) {
                        if (!temporales.get(i).isVivo()) {
                            temp = i;
                            break;
                        }
                    }
                    if (pass) {
                        if (isLocalVariable(currentCuadruplo.getResultado())) {
                            if (isParameter(currentCuadruplo.getResultado(), ambito_siguiente)) {
                                int par = getParamIndex(parametros, currentCuadruplo.getResultado());
                                codigoFinal += "       move $s" + par + ", $t" + asig + "\n";
                            } else {
                                if (isCharacterFunc(currentCuadruplo.getResultado())) {
                                    codigoFinal += "       sb $t" + asig + ", -" + getOffsetVariable(currentCuadruplo.getResultado()) + "($fp)\n";
                                } else {
                                    codigoFinal += "       sw $t" + asig + ", -" + getOffsetVariable(currentCuadruplo.getResultado()) + "($fp)\n";
                                }
                            }
                        } else {
                            if (isCharacterFunc(currentCuadruplo.getResultado())) {
                                codigoFinal += "       sb $t" + asig + ", _" + currentCuadruplo.getResultado() + "\n";
                            } else {
                                codigoFinal += "       sw $t" + asig + ", _" + currentCuadruplo.getResultado() + "\n";
                            }
                        }
                        temporales.get(asig).setVivo(false);
                        temporales.get(asig).setActivado("");
                    } else if (currentCuadruplo.getArgumento1().matches(num)) {
                        codigoFinal += "       li $t" + temp + ", " + currentCuadruplo.getArgumento1() + "\n";
                        if (isLocalVariable(currentCuadruplo.getResultado())) {
                            if (isParameter(currentCuadruplo.getResultado(), ambito_siguiente)) {
                                int par = getParamIndex(parametros, currentCuadruplo.getResultado());
                                codigoFinal += "       move $s" + par + ", $t" + temp + "\n";
                            } else {
                                codigoFinal += "       sw $t" + temp + ", -" + getOffsetVariable(currentCuadruplo.getResultado()) + "($fp)\n";
                            }
                        } else {
                            codigoFinal += "       sw $t" + temp + ", _" + currentCuadruplo.getResultado() + "\n";
                        }
                    } else if (currentCuadruplo.getArgumento1().contains("'")) {
                        codigoFinal += "       li $t" + temp + ", " + currentCuadruplo.getArgumento1() + "\n";
                        if (isLocalVariable(currentCuadruplo.getResultado())) {
                            if (isParameter(currentCuadruplo.getResultado(), ambito_siguiente)) {
                                int par = getParamIndex(parametros, currentCuadruplo.getResultado());
                                codigoFinal += "       move $s" + par + ", $t" + temp + "\n";
                            } else {
                                codigoFinal += "       sb $t" + temp + ", -" + getOffsetVariable(currentCuadruplo.getResultado()) + "($fp)\n";
                            }
                        } else {
                            codigoFinal += "       sb $t" + temp + ", _" + currentCuadruplo.getResultado() + "\n";
                        }
                    } else if (currentCuadruplo.getArgumento1().equals("Ret")) {
                        codigoFinal += "       move $t" + temp + ", $v0\n";
                        temporales.get(temp).setVivo(true);
                        temporales.get(temp).setActivado(currentCuadruplo.getResultado());
                    } else {

                        if (isLocalVariable(currentCuadruplo.getResultado())) {
                            if (isParameter(currentCuadruplo.getResultado(), ambito_siguiente)) {
                                int par = getParamIndex(parametros, currentCuadruplo.getResultado());
                                codigoFinal += "       move $s" + par + ", $t" + temp + "\n";
                            }
                        } else {

                        }
                    }
                    break;
            }
        }
        System.out.println(codigoFinal);
    }

    public static boolean isLocalVariable(String var) {
        for (Variables variable : tabla) {
            if (variable.getId().equals(var) && variable.getAmbito().equals(ambito_siguiente)) {
                return true;
            }
        }
        return false;
    }

    // Retorna la cantidad de parámetros que tiene la función en cuestión
    public static int getParamCount() {
        for (int i = 0; i < funciones.size(); i++) {
            if (funciones.get(i).getId().equals(ambito_siguiente)) {
                return funciones.get(i).getParams().size();
            }
        }
        return 0;
    }

    public static boolean isParameter(String variable, String ambito) {
        ArrayList<Variables> params = new ArrayList();
        for (Funcion funcion : funciones) {
            if (funcion.getId().equals(ambito)) {
                params = funcion.getParams();
            }
        }
        for (Variables param : params) {
            if (param.getId().equals(variable)) {
                return true;
            }
        }
        return false;
    }

    public static int getParamIndex(ArrayList<Temporal> parameters, String value) {
        for (int i = 0; i < parameters.size(); i++) {
            if (parameters.get(i).getActivado().equals(value)) {
                return i;
            }
        }
        return 0;
    }

    public static boolean isCharacterFunc(String value) {
        int j = 0;
        boolean retorno = false;
        for (Variables variable : tabla) {
            if (variable.getId().equals(value) && variable.getAmbito().equals(ambito_actual)) {
                if (variable.getTipo().equals("caracter")) {
                    retorno = true;
                    j++;
                }
            }
        }
        if (j == 0) {
            for (Variables variable : tabla) {
                if (variable.getId().equals(value) && variable.getAmbito().equals("Start")) {
                    if (variable.getTipo().equals("char")) {
                        retorno = true;
                        j++;
                    }
                }
            }
        }
        return retorno;
    }

    public static boolean isCharacter(String value) {
        for (Variables variable : tabla) {
            if (variable.getId().equals(value) && variable.getAmbito().equals("Start")) {
                if (variable.getTipo().equals("caracter")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getOffsetVariable(String variable) {
        for (Variables var : tabla) {
            if (variable.equals(var.getId()) && ambito_siguiente.equals(var.getAmbito())) {
                return var.getOffset();
            }
        }
        return "0";
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
