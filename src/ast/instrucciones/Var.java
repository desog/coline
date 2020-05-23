/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.expresiones.Exp;
import ast.expresiones.Tipo;

/**
 *
 * @author Diego
 */
public class Var {
    
    String id;
    Exp expresion;
    Tipo t;
    int dimension; 

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Exp getExpresion() {
        return expresion;
    }

    public void setExpresion(Exp expresion) {
        this.expresion = expresion;
    }

    public Tipo getT() {
        return t;
    }

    public void setT(Tipo t) {
        this.t = t;
    }
    
    
    public Var(String id, Exp expresion) {
        this.id = id;
        this.expresion = expresion;
    }

    public Var(String id, Exp expresion, int dimension) {
        this.id = id;
        this.expresion = expresion;
        this.dimension = dimension;
    }
    
    
    
    
}
