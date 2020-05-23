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
import ast.expresiones.Retorno;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Sentencias implements Ins {
    LinkedList<Nodo> hijos;
    int linea; 

    public Sentencias(LinkedList<Nodo> hijos, int linea) {
        this.hijos = hijos;
        this.linea = linea;
    }

    
    
    @Override
    public Object ejecutar(Entorno e) {
        debugger(e);
        Entorno local = new Entorno(e);
        Object valor = null;
            for (Nodo nodo : hijos) {
            if(nodo instanceof Ins){
                
          
                 valor = ((Ins)nodo).ejecutar(local);
                if(valor instanceof Retorno){
                    Retorno ret = (Retorno)valor;
                    Object o = ret.getValor(local);
                    return o;
                }
            }else if(nodo instanceof Exp){
                    Object o = ((Exp)nodo).getValor(local);
                    if(nodo instanceof Retorno){
                        return o;
                    }
                    
                }
        }
        return valor;
    }

    @Override
    public int getLinea() {
        return linea;
    }

    @Override
    public int debugger(Entorno e) {
        int lineadebug = evaluacion1junio.Evaluacion1Junio.LineaDebug;
        if(lineadebug!=0){
            if(lineadebug == linea){
                String datos= "";
                for(Simbolo s : e.tabla.values()){
                    datos+= s.getId()+ "," + s.getValor().toString();
                }
                JOptionPane.showMessageDialog(null, datos);
            }
        }
        return 0;
    }
}
