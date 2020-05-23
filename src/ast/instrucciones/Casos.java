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
public class Casos implements Ins {

    Exp valor;
    LinkedList<Nodo> listanodo;
    int linea;

    public Casos(Exp valor, LinkedList<Nodo> listanodo, int linea) {
        this.valor = valor;
        this.listanodo = listanodo;
        this.linea = linea;
    }

    @Override
    public Object ejecutar(Entorno e) {
        debugger(e);
        for (Nodo n : listanodo) {
            if (n instanceof Ins) {
                Ins instruccion = (Ins) n;
                Object o = instruccion.ejecutar(e);
                if (n instanceof Break) {
                    if (e.get("break") != null) {
                        return n;
                    }
                }
                if (n instanceof Continue) {
                    if (e.get("continue") != null) {
                        return n;
                    } else {
                        evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, LA SENTENCIA CONTINUE SOLO PUEDE SER USADA EN CICLOS, Linea: " + n.getLinea());
                    }
                }

            } else if (n instanceof Exp) {
                Exp exp = (Exp) n;
                //Object o = exp.getValor(e);
                if (n instanceof Retorno) {
                    return n;
                }
            }
        }

        return null;
    }

    @Override
    public int getLinea() {
        return this.linea;
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
