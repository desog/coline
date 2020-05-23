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
public class ToInt implements Exp{
    Exp expresion;
    int linea;

    public ToInt(Exp expresion, int linea) {
        this.expresion = expresion;
        this.linea = linea;
    }

    @Override
    public Object getValor(Entorno e) {
        debugger(e);
        if (expresion.getTipo(e).tp != null) {
            if (expresion.getTipo(e).esString()) {
                return Integer.valueOf(expresion.getValor(e).toString());
            }else{
                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, EL CASTEO toDouble() ACEPTA COMO ARGUMENTO UNA EXPRESION DE TIPO STRING, Linea: "+ linea);
            }
        }else{
            evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, EL CASTEO toDouble() ACEPTA COMO ARGUMENTO UNA EXPRESION DE TIPO STRING, Linea: "+ linea);
        }
        return null;
    }

    @Override
    public Tipo getTipo(Entorno e) {
        return new Tipo(Tipo.Tipos.INT);
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
