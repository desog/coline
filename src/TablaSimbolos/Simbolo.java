/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablaSimbolos;

import ast.expresiones.Tipo;
import ast.instrucciones.Modificador;
import java.util.LinkedList;

/**
 *
 * @author Diego
 */
public class Simbolo {
    

    public Simbolo(Tipo tipo, String id) {
        this.tipo = tipo;
        this.id = id;
    }
    
    public Simbolo(String id){
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
    Tipo tipo;
    String id;
    Object valor;

    public LinkedList<Modificador> getModificadores() {
        return Modificadores;
    }

    public void setModificadores(LinkedList<Modificador> Modificadores) {
        this.Modificadores = Modificadores;
    }
    LinkedList<Modificador> Modificadores;
    int dimensiones;

    /**
     * CONSTRUCTOR PARA SOLO TIPO Y ID CON MODIFICADORES, GLOBALES
     * @param tipo
     * @param id
     * @param Modificadores 
     */
    public Simbolo(Tipo tipo, String id, LinkedList<Modificador> Modificadores) {
        this.tipo = tipo;
        this.id = id;
        this.Modificadores = Modificadores;
    }
/**
 * CONSTRUCTOR PARA ARREGLOS GLOBALES SIN VALOR!!
 * @param tipo
 * @param id
 * @param Modificadores
 * @param dimensiones 
 */
    public Simbolo(Tipo tipo, String id, LinkedList<Modificador> Modificadores, int dimensiones) {
        this.tipo = tipo;
        this.id = id;
        this.Modificadores = Modificadores;
        this.dimensiones = dimensiones;
    }

    
    
    
/**
 * CONSTRUCTOR PARA ARREGLOS LOCALES
 * @param tipo
 * @param id
 * @param valor
 * @param dimensiones 
 */
    public Simbolo(Tipo tipo, String id, Object valor, int dimensiones) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
        this.dimensiones = dimensiones;
    }

    /**
     * CONSTRUCTOR PARA DECLARACIONES GLOBALES
     * @param tipo
     * @param id
     * @param valor
     * @param Modificadores 
     */
    public Simbolo(Tipo tipo, String id, Object valor, LinkedList<Modificador> Modificadores) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
        this.Modificadores = Modificadores;
    }

    /**
     * CONSTRUCTOR PARA DECLARACIONES DE ARREGLOS GLOBALES
     * @param tipo
     * @param id
     * @param valor
     * @param Modificadores
     * @param dimensiones 
     */
    public Simbolo(Tipo tipo, String id, Object valor, LinkedList<Modificador> Modificadores, int dimensiones) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
        this.Modificadores = Modificadores;
        this.dimensiones = dimensiones;
    }
    
    
    
    public Simbolo(Tipo tipo, String id, Object valor) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
    }

    public int getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(int dimensiones) {
        this.dimensiones = dimensiones;
    }
    
    
    
    
}
