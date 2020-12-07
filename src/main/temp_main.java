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
    static int cont = 0;

    public static void main(String args[]) {

        //Ejecutar esto si se llegaron a hacer cambios:
        compilar_archivos();
        boolean mvAl = moverArch("Lexico.java");
        boolean mvAS = moverArch("AnalizadorSintactico.java");
        boolean mvSyn = moverArch("sym.java");
        //Ejecutar parte léxica y sintáctico:
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
                switch (tipo) { //Aquí valido si se le asigna el tipo correcto
                    case "entero":
                        if (valor.equals("num")) {
                            pasa = true;
                        } else if (valor.equals("id")) {
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
                        } else {
                            errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un tipo entero ");
                        }
                        break;
                    case "booleano":
                        if (valor.equals("bool")) {
                            pasa = true;
                        } else if (valor.equals("id")) {
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
                        } else {
                            errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un tipo booleano ");
                        }
                        break;
                    case "caracter":
                        if (valor.equals("letter")) {
                            pasa = true;
                        } else if (valor.equals("id")) {
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
                        } else {
                            errores_semanticos.add("Error semántico: La variable " + id + " recibe un tipo incorrecto, se esperaba un tipo caracter ");
                        }
                        break;
                    case "string":
                        if (valor.equals("string")) {
                            pasa = true;
                        } else if (valor.equals("id")) {
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
                    } else {
                        tipo = getTipoVariable(id, ambito_actual);
                        if (!(tipo.equals("entero") && value.equals("num") || tipo.equals("boolean") && value.equals("bool") || tipo.equals("caracter") && value.equals("letter") || tipo.equals("string") && value.equals("string"))) {
                            errores_semanticos.add("Error semántico: valor no permitido para la variable " + id + " en el ámbito " + ambito_actual + " se pide un tipo " + tipo);
                        }
                    }
                } else {
                    errores_semanticos.add("Error semántico: La variable " + id + " no ha sido declarada con anterioridad, ámbito " + ambito_actual);
                }
            } else if (hijo.getValor().equals("declaración array")) {
                Nodo currentNode = hijo;
                String id = currentNode.getHijos().get(0).getHijos().get(0).getValor();
                int dimension = Integer.parseInt(currentNode.getHijos().get(1).getHijos().get(0).getValor());
                String tipo = "array()";
                for (int i = 1; i < dimension; i++) {
                    tipo = "array(" + tipo + ")";
                }
                if (!verificarVariable(id, ambito_actual)) { //Verifica si ya existe el id
                    if (currentNode.getHijos().size() > 2) { //Verifica si se le asigna arreglos
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
                String id = hijo.getHijo(0).getHijo(0).getValor();
                if (verificarVariable(id, ambito_actual)) {
                    errores_semanticos.add("Error semántico: la variable " + id + " no se puede usar en el for ya fue declarada con anterioridad en el ámbito " + ambito_actual);
                } else {
                    tabla.add(new Variables("entero", id, ambito_actual + "." + (cont++) + "_for_statement", getOffset("entero")));
                }
                recorrido(hijo.getHijo(3), ambito_actual + "." + (cont++) + "_for_statement");
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
            } else if (hijo.getValor().equals("If statement") || hijo.getValor().equals("Else if")) {
                Nodo currentNode = hijo;
                // Validar los parametros
                if (currentNode.getHijos().get(1).getValor().equals("factor")) {
                    String id = currentNode.getHijos().get(1).getHijos().get(0).getHijos().get(0).getValor();
                    if (!verificarVariable(id, ambito_actual)) {
                        errores_semanticos.add("Error semántico: no existe la variable " + id + " dentro del ámbito " + ambito_actual + " al usarlo dentro del bloque de decisión if");
                    }
                } else if (currentNode.getHijos().get(1).getValor().equals("expression simple")) {

                }
                //
                if (hijo.getValor().equals("If statement")) {
                    recorrido(currentNode.getHijos().get(2), ambito_actual + "." + (cont++) + "_if_statement"); //Cuerpo del if
                } else if (hijo.getValor().equals("Else if")) {
                    recorrido(currentNode.getHijos().get(2), ambito_actual + "." + (cont++) + "_elseif_statement"); // Cuerpo del if else
                }
                if (currentNode.getHijos().get(3).getValor().equals("Else if") || currentNode.getHijos().get(3).getValor().equals("Else")) {
                    Nodo node = new Nodo();
                    node.addHijo(currentNode.getHijos().get(3));
                    recorrido(node, ambito_actual);
                }
            } else if (hijo.getValor().equals("Else")) {
                recorrido(hijo.getHijos().get(0), ambito_actual + "." + (cont++) + "_else");
            }
        }

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
