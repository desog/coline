/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import Herramientas.ArbolArreglos.Arreglo;
import ast.expresiones.Exp;
import ast.expresiones.Identificador;
import ast.expresiones.Tipo;

/**
 *
 * @author Diego
 */
public class Parametro {

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public boolean isParamfinal() {
        return paramfinal;
    }

    public void setParamfinal(boolean paramfinal) {
        this.paramfinal = paramfinal;
    }
    Tipo tipo;
    String identificador;
    boolean paramfinal;
    Arreglo arreglo;
    Object valor;

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Arreglo getArreglo() {
        return arreglo;
    }

    public void setArreglo(Arreglo arreglo) {
        this.arreglo = arreglo;
    }
    
     
    
    public Parametro(Tipo tipo, String identificador) {
        this.tipo = tipo;
        this.identificador = identificador;
    }

    public Parametro(Tipo tipo, Arreglo arreglo) {
        this.tipo = tipo;
        this.arreglo = arreglo;
    }
    
    
    
    
}
