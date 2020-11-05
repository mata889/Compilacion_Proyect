/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class Funcion {

    public String tipo;
    public String id;
    public ArrayList<Variables> params;

    public Funcion(String tipo, String id) {
        this.tipo = tipo;
        this.id = id;
        this.params = new ArrayList();
    }

    public Funcion(String tipo, String id, ArrayList<Variables> v) {
        this.tipo = tipo;
        this.id = id;
        this.params = v;
    }

    public void agregar_params(ArrayList<Variables> v) {
        this.params = v;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getId() {
        return this.id;
    }

    public ArrayList<Variables> getParams() {
        return this.params;
    }

    public String toString() {
        String dec = "Tipo: " + tipo + " | id: " + id + " \n" + "    Parámetros:";
        for (Variables param : params) {
            dec+="\n"+"        "+param.toString();
        }
        return dec;
    }
}
