/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import java.util.LinkedList;

/**
 *
 * @author Diego
 */
public class EncabezadoMetodo {
    String identificador;
    LinkedList<Parametro> parametros;
    int dimensiones;

    public EncabezadoMetodo(String identificador, LinkedList<Parametro> parametros, int dimensiones) {
        this.identificador = identificador;
        this.parametros = parametros;
        this.dimensiones = dimensiones;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public LinkedList<Parametro> getParametros() {
        return parametros;
    }

    public void setParametros(LinkedList<Parametro> parametros) {
        this.parametros = parametros;
    }

    public int getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(int dimensiones) {
        this.dimensiones = dimensiones;
    }
    
    
    
    
}
