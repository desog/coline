/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import TablaSimbolos.Simbolo;
import ast.Nodo;
import ast.entorno.Entorno;
import ast.expresiones.Exp;
import java.util.LinkedList;

/**
 *
 * @author Diego
 */
public class Bloque implements Ins{

    
    LinkedList<Nodo> hijos;
    int linea; 

    public Bloque(LinkedList<Nodo> hijos, int linea) {
        this.hijos = hijos;
        this.linea = linea;
    }
    
    
    @Override
    public Object ejecutar(Entorno e) {
        Entorno local = new Entorno(e);
            for (Nodo nodo : hijos) {
            if(nodo instanceof Constructor){
                Constructor cons = (Constructor) nodo;
                e.agregar(cons.getId()+"|constructor", cons);
               
            }else if(nodo instanceof Metodo) {
                Metodo metodo = (Metodo) nodo;
                e.agregar(metodo.getId()+"|metodo", metodo);
            }else if(nodo instanceof Ins){
                
                Object result = ((Ins)nodo).ejecutar(local);
                if(result!=null){
                    return result;
                }
            }else if(nodo instanceof Exp){
                    return ((Exp)nodo).getValor(local);
                }
        }
        return e;
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
