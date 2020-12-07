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
    
    Nodo(){
    }
            
    Nodo(String valor,  int ref){
        this.valor = valor;
        this.idNodo = ref;
    }

    //Getters y setters
    public void setValor(String valor){
        this.valor=valor;
    }

    public String getValor(){
        return valor;
    }

    public void setID(int id){
        this.idNodo=id;
    }

    public int getID(){
        return idNodo;
    }

    public ArrayList<Nodo> getHijos(){
        return hijos;
    }

    public Nodo getHijo(int num){
        return hijos.get(num);
    }
    
    public void addHijitos(ArrayList<Nodo> n){
        hijos.addAll(n);
    }

    //FUnciones importantes
    public void addHijo(Nodo hijo){
        hijos.add(hijo);
    }

    public void addHijo(String valor, int ref){
        hijos.add(new Nodo(valor,ref));
    }

    public void print(){
        System.out.println("ID Nodo: "+idNodo+" | Valor: "+valor);
        System.out.println("Hijos: ");
        for (Nodo hijo : hijos) {
            hijo.print();
        }
    }

}
