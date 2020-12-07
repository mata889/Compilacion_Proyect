/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author desconocido
 */
public class Cuadruplo {
    public String operador;
    public String argumento1;
    public String argumento2;
    public String resultado;

    public Cuadruplo(String operador, String argumento1, String argumento2, String resultado) {
        this.operador = operador;
        this.argumento1 = argumento1;
        this.argumento2 = argumento2;
        this.resultado = resultado;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getArgumento1() {
        return argumento1;
    }

    public void setArgumento1(String argumento1) {
        this.argumento1 = argumento1;
    }

    public String getArgumento2() {
        return argumento2;
    }

    public void setArgumento2(String argumento2) {
        this.argumento2 = argumento2;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    public String[] retorno_tabla(){
        String[] row = {operador, argumento1, argumento2, resultado};
        return row;
    }

    @Override
    public String toString() {
        return "Operador: " + operador + " | Arg1: " + argumento1 + " | Arg2: " + argumento2 + " | Res: " + resultado;
    }
    
    
}