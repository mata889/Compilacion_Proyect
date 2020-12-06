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
        /*compilar_archivos();
        boolean mvAl = moverArch("Lexico.java");
        boolean mvAS = moverArch("AnalizadorSintactico.java");
        boolean mvSyn= moverArch("sym.java");*/
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
                                    } else if (tipo.equals("booleano") && valor.equals("Valores-boolean")) {
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
                    if ((tipo = getTipoVariable(id, ambito_actual)) != null) { //Si el id existe y los tipos concuerdan
                        if (valor.equals("=")) {
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
                } else if (hijo.getHijos().get(0).getValor().equals("while")) {
                    Nodo currentNode = hijo.getHijos().get(1).getHijos().get(0); // Factor
                    if (currentNode.getValor().equals("factor")) {
                        String id = currentNode.getHijos().get(0).getHijos().get(0).getValor();
                        if (!verificarVariable(id, ambito_actual)) {
                            errores_semanticos.add("Error semántico: La variable " + id + " no existe dentro del ámbito " + ambito_actual);
                        }
                    } else {
                        for (Nodo node : currentNode.getHijos()) {
                            if (node.getValor() == "ID") {
                                String cadena = node.getHijos().get(0).getValor();
                                if (!verificarVariable(cadena, ambito_actual)) {
                                    errores_semanticos.add("Error semántico: La variable " + cadena + " no existe dentro del ámbito " + ambito_actual + " al usarse dentro del while");
                                }
                                //tabla.add(new Variables(node.getHijos().get(0).getValor(), id, ambito_actual, 0));
                            } else if (node.getValor() == "factor") {
                                String cadena = node.getHijos().get(0).getHijos().get(0).getValor();
                                if (!verificarVariable(cadena, ambito_actual)) {
                                    errores_semanticos.add("Error semántico: La variable " + cadena + " no existe dentro del ámbito " + ambito_actual + " al usarse dentro del while");
                                }
                            }
                        }
                    }
                    recorrido(hijo.getHijos().get(2), ambito_actual + "." +(cont++)+"_while_statement");
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
                        if (dimension == 1) {
                            if (currentNode.getHijos().get(2).getValor().equals("Valores")) {
                                // RECORDAR: CALCULAR EL OFFSET
                                tabla.add(new Variables(tipo, id, ambito_actual, 0));
                            } else {
                                errores_semanticos.add("Error semántico: Se esperaban arreglos de " + dimension + " dimensiones en la variable " + id);
                            }
                        } else if (dimension == 2) {
                            if (currentNode.getHijos().get(2).getValor().equals("bracket-segunda dimension")) {
                                // RECORDAR: CALCULAR EL OFFSET
                                tabla.add(new Variables(tipo, id, ambito_actual, 0));
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
                        recorrido(hijo.getHijos().get(4), ambito_actual+ "."+(cont++)+"_"+id);
                    }
                } else {
                    errores_semanticos.add("Error semántico: La función " + id + " fue definida con anterioridad");
                }
            } // Aquí se encuentran las siguientes validaciones semánticas:
            // - Llamada de funciones con parámetro.
            else if (hijo.getValor().equals("Llamada de funciones")) {
                String id = hijo.getHijos().get(0).getHijos().get(0).getValor(); // Obtener el ID
                ArrayList<Variables> parametros = new ArrayList();
                Funcion funcion;
                if ((funcion = getFuncion(id)) != null) { // Verifica si la función ya ha sido declarada
                    for (Nodo h : hijo.getHijos().get(1).getHijos()) { //toma todos los argumentos
                        if (verificarVariable(h.getHijos().get(0).getValor(), ambito_actual)) { //Verifica Si la variable que se le está pasando existe
                            parametros.add(getVariable(h.getHijos().get(0).getValor(), ambito_actual)); //Sí la variable existe se agrega a este arreglo
                        } else {
                            errores_semanticos.add("Error semántico: La variable " + h.getHijos().get(0).getValor() + " no existe dentro del ámbito " + ambito_actual);
                        }
                    }
                    // Verifica si los parámetros que se pasan son del tipo correcto y del tamaño correcto
                    for (int i = 0; i < funcion.getParams().size(); i++) {
                        if (!funcion.getParams().get(i).getTipo().equals(parametros.get(i).getTipo())) {
                            errores_semanticos.add("Error semántico: Se esperaba un tipo de variable " + funcion.getParams().get(i).getTipo() + " pero se le da un " + parametros.get(i).getTipo() + "al llamado de la función " + id);
                        }
                    }
                } else {
                    errores_semanticos.add("Error semántico: llamado de función incorrecta, la función " + id + " no fue declarada con anterioridad");
                }
            }// Aquí se encuentran las siguientes validaciones semánticas:
            // - Declaraciones de ciclo FOR
            else if (hijo.getValor().equals("CicloFor")) {
                String id = hijo.getHijos().get(0).getHijos().get(0).getHijos().get(0).getValor();
                if (verificarVariable(id, ambito_actual)) {
                    errores_semanticos.add("Error semántico: la variable " + id + " no se puede usar en el for ya fue declarada con anterioridad en el ámbito " + ambito_actual);
                }else{
                    offset += 4;
                    tabla.add(new Variables("entero", id, ambito_actual+ "."+(cont++)+"_for_statement", offset));
                }
                recorrido(hijo.getHijos().get(0).getHijos().get(3), ambito_actual+ "."+(cont++)+"_for_statement");
            }// Aquí se encuentran las siguientes validaciones semánticas:
            // - Declaraciones de Switch case
            else if (hijo.getValor().equals("Bloque Switch")) {
                ArrayList<String> arreglo = new ArrayList();
                String id = hijo.getHijos().get(1).getHijos().get(0).getValor(), tipo, valor;
                if ((tipo = getTipoVariable(id, ambito_actual)) == null) {
                    errores_semanticos.add("Error semántico: la variable " + id + " no se encuentra dentro del ámbito " + ambito_actual + " al usarlo en un bloque Switch Case");
                } else {
                    arreglo.add(id);
                    for (int i = 0; i < hijo.getHijos().size(); i++) {
                        if (hijo.getHijos().get(i).getValor().equals("Case")) {
                            if (hijo.getHijos().get(i + 1).getValor().equals("Id")) { // Verifica si lo que hay después de case es un ID, en caso de ser un ID hay que validar si existe y el tipo de la variable.
                                String temp_id = hijo.getHijos().get(i + 1).getHijos().get(0).getValor();
                                if (!arreglo.contains(temp_id)) { //Comprueba si la variable ya fue utilizada
                                    if (verificarVariable(temp_id, ambito_actual)) {
                                        if (!tipo.equals(getTipoVariable(temp_id, ambito_actual))) {
                                            errores_semanticos.add("Error semántico: la variable " + temp_id + " es de un tipo no válido, se esperaba una variable de un tipo " + tipo + " dentro del bloque Switch case en el ámbito " + ambito_actual);
                                        } else {
                                            arreglo.add(temp_id);
                                            recorrido(hijo.getHijos().get(i + 2), ambito_actual+ "."+(cont++)+"_switchCase_statement-case" + temp_id);
                                        }
                                    } else {
                                        errores_semanticos.add("Error semántico: la variable " + temp_id + " no se encuentra dentro del ámbito " + ambito_actual + " al usarlo en un case dentro del bloque Switch Case");
                                    }
                                } else {
                                    errores_semanticos.add("Error semántico: la variable " + temp_id + " ya ha sido utilizada dentro del bloque Switch Case en el ámbito " + ambito_actual);
                                }
                            } else if ((hijo.getHijos().get(i + 1).getValor().equals("Valores-num") && tipo.equals("entero")) || (hijo.getHijos().get(i + 1).getValor().equals("Valores-caracter") && tipo.equals("caracter"))) {
                                if (!arreglo.contains(valor = hijo.getHijos().get(i + 1).getHijos().get(0).getValor())) { // Comprueba si el valor ya fue utilizado
                                    arreglo.add(valor);
                                    recorrido(hijo.getHijos().get(i + 2), ambito_actual+ "."+(cont++)+"_switchCase_statement-case" + valor);
                                } else {
                                    errores_semanticos.add("Error semántico: ya se utiliza el valor " + valor + " dentro del bloque switch case en el ámbito " + ambito_actual);
                                }
                            } else {
                                errores_semanticos.add("Error semántico: se esperan casos de tipo " + tipo + " dentro del bloque Switch Case en el ámbito " + ambito_actual);
                            }
                            // RECORDAR: TERMINAR PARA BOOLEANOS 
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
                    recorrido(currentNode.getHijos().get(2), ambito_actual+ "."+(cont++)+"_if_statement"); //Cuerpo del if
                } else if (hijo.getValor().equals("Else if")) {
                    recorrido(currentNode.getHijos().get(2), ambito_actual+ "."+(cont++)+"_elseif_statement"); // Cuerpo del if else
                }
                if (currentNode.getHijos().get(3).getValor().equals("Else if") || currentNode.getHijos().get(3).getValor().equals("Else")) {
                    Nodo node = new Nodo();
                    node.addHijo(currentNode.getHijos().get(3));
                    recorrido(node, ambito_actual);
                }
            } else if (hijo.getValor().equals("Else")) {
                recorrido(hijo.getHijos().get(0), ambito_actual+ "."+(cont++)+"_else");
            }
        }

    }

    // Verifica si el id de la variable ya existe en el o los ámbitos especificados.
    public static boolean verificarVariable(String variable, String ambito_actual) {
        String value;
        for (int i = 0; i < tabla.size(); i++) {
            /*if ( ambito_actual.contains((value="if_statement").toString()) || 
                 ambito_actual.contains((value="elseif_statement").toString()) || 
                 ambito_actual.contains((value="else").toString()) || 
                 ambito_actual.contains((value="while_statement").toString()) || 
                 ambito_actual.contains((value="for_statement").toString()) ||
                 ambito_actual.contains((value="switchCase_statement-case").toString())) {
                String ambito = ambito_actual.replace("."+value, "");
                if (variable.equals(tabla.get(i).getId()) && ambito.contains(tabla.get(i).getAmbito())) {
                    return true;
                }
            }else{*/
                if (variable.equals(tabla.get(i).getId()) && ambito_actual.contains(tabla.get(i).getAmbito())) {
                    return true;
                }
            //}
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
            if (variable.getId().equals(id) && ambito.contains(variable.getAmbito())) {
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
