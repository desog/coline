/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas.ArbolArreglos;

import ast.entorno.Entorno;
import ast.expresiones.Exp;
import ast.expresiones.Tipo;
import java.util.LinkedList;

/**
 *
 * @author Diego
 */
public class Nodon implements Exp{

    @Override
    public int debugger(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public enum etiquetaNodo{
        HOJA,INTERIOR
    }
    
    public Nodon padre;
    public LinkedList<Nodon> hijos;
    public Object valor;
    public etiquetaNodo etiqueta;
    public Tipo tipo;

    public Nodon getPadre() {
        return padre;
    }

    public void setPadre(Nodon padre) {
        this.padre = padre;
    }

    public LinkedList<Nodon> getHijos() {
        return hijos;
    }

    public void setHijos(LinkedList<Nodon> hijos) {
        this.hijos = hijos;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public etiquetaNodo getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(etiquetaNodo etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
    
    
    public Nodon(LinkedList<Nodon> hijos, etiquetaNodo etiqueta){
        this.hijos = hijos;
        this.etiqueta = etiqueta;
        this.hijos = hijos;
    }
    
    public Nodon(etiquetaNodo etiqueta, Exp valor){
        this.etiqueta = etiqueta;
        this.valor = valor;
        this.hijos = new LinkedList<Nodon>();
    }
    
 @Override
    public Object getValor(Entorno e) {
        return this;
    }

    @Override
    public Tipo getTipo(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLinea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addNodo(Nodon u){
        u.padre=this;
        this.hijos.add(u);
    }

}
