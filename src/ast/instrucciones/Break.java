/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.entorno.Entorno;

/**
 *
 * @author Diego
 */
public class Break implements Ins{
    
    boolean quiebre;
    int linea;
    
    public Break(int linea) {
        this.linea = linea;
    }
    
    
    
    @Override
    public Object ejecutar(Entorno e) {
        return new Break(linea);
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
