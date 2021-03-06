package main;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
//nodos de los arboles

public class Nodo {

    public String valor;
    public int idNodo;
    public ArrayList<Nodo> hijos = new ArrayList<>();
    public Nodo padre;

    //Intermedio
    public String siguiente;
    public String comienzo;
    public String verdadero;
    public String falso;

    Nodo() {
    }

    Nodo(String valor, int ref) {
        this.valor = valor;
        this.idNodo = ref;
    }

    //Getters y setters
    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setID(int id) {
        this.idNodo = id;
    }

    public int getID() {
        return idNodo;
    }

    public ArrayList<Nodo> getHijos() {
        return hijos;
    }

    public Nodo getHijo(int num) {
        return hijos.get(num);
    }

    public void addHijitos(ArrayList<Nodo> n) {
        for (Nodo nodo : n) {
            nodo.setPadre(this);
        }
        hijos.addAll(n);
    }

    //FUnciones importantes
    public void addHijo(Nodo hijo) {
        hijo.setPadre(this);
        hijos.add(hijo);
    }

    public void addHijo(String valor, int ref) {
        Nodo node = new Nodo(valor, ref);
        node.setPadre(this);
        hijos.add(node);
    }

    public void setPadre(Nodo n) {
        this.padre = n;
    }

    // Intermedio 
    public void setComienzo(String comienzo) {
        this.comienzo = comienzo;
    }

    public String getComienzo() {
        return this.comienzo;
    }

    public void setSiguiente(String etiq) {
        this.siguiente = etiq;
    }

    public String getSiguiente() {
        return this.siguiente;
    }

    public void setVerdadero(String verdadero) {
        this.verdadero = verdadero;
    }

    public String getVerdadero() {
        return verdadero;
    }

    public void setFalso(String falso) {
        this.falso = falso;
    }

    public String getFalso() {
        return falso;
    }

    // 
    public void print() {
        System.out.println("ID Nodo: " + idNodo + " | Valor: " + valor);
        System.out.println("Hijos: ");
        for (Nodo hijo : hijos) {
            hijo.print();
        }
    }

}
