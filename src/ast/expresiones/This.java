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
public class This implements Exp {

    public This() {
    }

    @Override
    public Object getValor(Entorno e) {
        
        return ObtenerPadre(e);
    }
    
    public Object ObtenerPadre(Entorno e){
        if(e.getPadre()!=null){
            if(e.getNombrePadre()!=null){
                return e;
            }
            return ObtenerPadre(e.getPadre());
        }
            return e;
        
    }

    @Override
    public Tipo getTipo(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
