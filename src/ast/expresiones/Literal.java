/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;

/**
 *
 * @author Diego
 */
public class Literal implements Exp {

    public Literal(Object valor, Tipo tipo,int linea) {
        this.valor = valor;
        this.tipo = tipo;
        this.linea=linea;
    }
    Object valor;
    Tipo tipo;
    int linea;
    
    @Override
    public Object getValor(Entorno e) {
        return this.valor;
    }

    @Override
    public Tipo getTipo(Entorno e) {
        return this.tipo;
    }

    @Override
    public int getLinea() {
        return this.linea;
    }

    @Override
    public int debugger(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    
}
