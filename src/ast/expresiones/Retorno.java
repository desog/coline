/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Retorno implements Exp {

    Exp expresion;
    int linea;

    public Retorno(Exp expresion, int linea) {
        this.expresion = expresion;
        this.linea = linea;
    }

    @Override
    public Object getValor(Entorno e) {
        debugger(e);
        if (expresion != null) {
            return expresion.getValor(e);
        }else{
            return null;
        }
    }

    @Override
    public Tipo getTipo(Entorno e) {
        return expresion.getTipo(e);
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
                String datos= "TABLA DE SIMBOLOS ACTUAL, (ID,VAL) \n";
                for(Simbolo s : e.tabla.values()){
                    datos+= s.getId()+ "," + s.getValor().toString()+"\n";
                }
                JOptionPane.showMessageDialog(null, datos);
            }
        }
        return 0;
    }
}
