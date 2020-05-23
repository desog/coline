/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import ast.expresiones.Exp;
import ast.expresiones.Identificador;
import ast.expresiones.Tipo;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Asignacion implements Ins {

    Identificador id;
    Exp val;
    int linea;

    public Asignacion(Identificador id, Exp val, int linea) {
        this.id = id;
        this.val = val;
        this.linea = linea;
    }

    @Override
    public Object ejecutar(Entorno e) {
        debugger(e);
        Object objs = id.getValor(e);
        Simbolo s = e.get(id.getVal());
        if (s != null) {
            Tipo t = val.getTipo(e);
            Tipo tid = id.getTipo(e);
            if (tid.tr == null && t.tr == null) {
                if (t.tp.equals(tid.tp)) {
                    if (s.getModificadores() != null) {
                        if (VerificarFinal(s.getModificadores())) {
                            evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE ASIGNAR UN VALOR A LA VARIABLE: " + id.getVal() + " DEBIDO A QUE TIENE UN MODIFICADOR FINAL , Linea: " + linea);
                        } else {
                            s.setValor(val.getValor(e));
                            String toString = val.getValor(e).toString();
                            e.agregar(id.getVal(), s);
                        }
                    } else {
                        Object o = val.getValor(e);
                        s.setValor(val.getValor(e));
                 
                        e.agregar(id.getVal(), s);
                    }
                } else {
                    evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE ASIGNAR UN TIPO: " + t.tp.toString() + " A UN TIPO: " + tid.tp.toString() + ", Linea: " + linea);
                }
            } else if (tid.esString() && t.tr!=null) {
                if(t.tr.equals("nulo")){
                if (s.getModificadores() != null) {
                    if (VerificarFinal(s.getModificadores())) {
                        evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE ASIGNAR UN VALOR A LA VARIABLE: " + id.getVal() + " DEBIDO A QUE TIENE UN MODIFICADOR FINAL , Linea: " + linea);
                    } else {
                        s.setValor(val.getValor(e));
                        e.agregar(id.getVal(), s);
                    }
                } 
                }else {
                    s.setValor(val.getValor(e));
                    e.agregar(id.getVal(), s);
                }
            } else if (tid.tr != null && t.tr != null) {
                if (t.tr.equals("nulo")) {
                    if (s.getModificadores() != null) {
                        if (VerificarFinal(s.getModificadores())) {
                            evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE ASIGNAR UN VALOR A LA VARIABLE: " + id.getVal() + " DEBIDO A QUE TIENE UN MODIFICADOR FINAL , Linea: " + linea);
                        } else {
                            s.setValor(val.getValor(e));
                            e.agregar(id.getVal(), s);
                        }
                    } else {
                        s.setValor(val.getValor(e));
                        e.agregar(id.getVal(), s);
                    }
                } else if (tid.tr.equals(t.tr)) {
                    if (s.getModificadores() != null) {
                        if (VerificarFinal(s.getModificadores())) {
                            evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE ASIGNAR UN VALOR A LA VARIABLE: " + id.getVal() + " DEBIDO A QUE TIENE UN MODIFICADOR FINAL , Linea: " + linea);
                        } else {
                            s.setValor(val.getValor(e));
                            e.agregar(id.getVal(), s);
                        }
                    } else {
                        s.setValor(val.getValor(e));
                        e.agregar(id.getVal(), s);
                    }
                } else {
                    evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE ASIGNAR UN TIPO: " + t.tr.toString() + " A UN TIPO: " + tid.tr.toString() + ", Linea: " + linea);
                }
            }

        }
        return null;
    }

    @Override
    public int getLinea() {
        return this.linea;
    }

    public boolean VerificarFinal(java.util.LinkedList<Modificador> lista) {
        for (Modificador m : lista) {
            if (m.getM() != null) {
                if (m.getM().equals(Modificador.Modificadores.FINAL)) {
                    return true;
                }
            }
        }
        return false;
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
