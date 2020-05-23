/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas.ArbolArreglos;

import ast.expresiones.Literal;
import ast.expresiones.Tipo;
import java.util.LinkedList;

/**
 *
 * @author Diego
 */
public class Arbol {
    public LinkedList<Integer> dimensiones;
    public Tipo tipo;

    public Arbol(LinkedList<Integer> dimensiones, Tipo tipo) {
        this.dimensiones = dimensiones;
        this.tipo = tipo;
    }
    
    
    public Nodon crearArreglo(int indice){
        Nodon nodo = new Nodon(Nodon.etiquetaNodo.INTERIOR, null);
        
        int d = indice - dimensiones.size();
        
        if(d<0){
            int tamano = this.dimensiones.get(indice);
            for(int i = 0; i< tamano; i++){
                nodo.addNodo(crearArreglo(indice+1));
            }
        }else if(d==0){
            Herramientas a = new Herramientas();
            Object valordefecto = a.getValorDefecto(tipo);
            nodo.setValor(valordefecto);
            nodo.setEtiqueta(Nodon.etiquetaNodo.HOJA);
        }
        
        return nodo;
    }
    
    public Nodon ObtenerNodo(int indice, Nodon nodo){
        Nodon nodon; 
        if(nodo.getEtiqueta().equals(Nodon.etiquetaNodo.INTERIOR)){
            int d = indice - dimensiones.size();
            if(d<0){
                int i = dimensiones.get(indice);
                if(i < nodo.getHijos().size()){
                nodon = ObtenerNodo(indice+1,nodo.getHijos().get(i));
                return nodon;
            }else{
                    return null;
            }
            }else if(d==0){
                return nodo;
            } 
        }else{
            return nodo;
        }
        return nodon= new Nodon(Nodon.etiquetaNodo.INTERIOR, null);
    }
}
