/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import ast.expresiones.Exp;
import ast.expresiones.Tipo;
import ast.instrucciones.Constructor;
import ast.instrucciones.Declaracion;
import ast.instrucciones.Metodo;
import java.util.LinkedList;

/**
 *
 * @author Diego
 */
public class Objeto implements Exp{
    String idPadre;
    LinkedList<Constructor> constructores;
    LinkedList<Metodo> Metodos;
    LinkedList<Simbolo> Declaraciones;
    Entorno ent;

    public Objeto(String idPadre,Entorno entorno) {
        this.idPadre = idPadre;
  
        this.ent = entorno;
    }

    @Override
    public Object getValor(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
