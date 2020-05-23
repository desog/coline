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
import ast.expresiones.Tipo;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class For implements Ins {

    Ins AsigDecla;
    Exp condicion;
    Exp actualizacion;
    LinkedList<Nodo> listanodos;
    int linea;

    public For(Ins AsigDecla, Exp condicion, Exp actualizacion, LinkedList<Nodo> listanodos, int linea) {
        this.AsigDecla = AsigDecla;
        this.condicion = condicion;
        this.actualizacion = actualizacion;
        this.listanodos = listanodos;
        this.linea = linea;
    }

    @Override
    public Object ejecutar(Entorno e) {
        debugger(e);
        Entorno local = new Entorno(e);
        Simbolo s = new Simbolo(new Tipo(Tipo.Tipos.BOOLEAN), "break");
        local.agregar("break", s);
        local.agregar("continue", new Simbolo(new Tipo(Tipo.Tipos.BOOLEAN), "continue"));
        AsigDecla.ejecutar(local);
        if (condicion.getTipo(local).esbooleano()) {
            while ((boolean) condicion.getValor(local) == true) {
                for (Nodo n : listanodos) {
                    if (n instanceof Ins) {
                        Ins inst = (Ins) n;
                        Object o = inst.ejecutar(local);
                        if (o instanceof Break) {
                            return null;
                        }
                        if (o instanceof Continue) {
                            break;
                        }
                    } else if (n instanceof Exp) {
                        Exp exp = (Exp) n;
                        //Object o = exp.getValor(local);
                        if (n instanceof Retorno) {
                            return n;
                        }
                    }
                }
                actualizacion.getValor(local);
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
