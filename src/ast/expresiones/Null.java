/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.entorno.Entorno;

/**
 *
 * @author Diego
 */
public class Null implements Exp{

    @Override
    public Object getValor(Entorno e) {
        return "nulo";
    }

    @Override
    public Tipo getTipo(Entorno e) {
        return new Tipo("nulo");
    }

    @Override
    public int getLinea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int debugger(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
