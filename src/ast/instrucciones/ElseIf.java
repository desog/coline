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
public class ElseIf implements Ins {

    Exp condicion;
    LinkedList<Nodo> listanodo;
    LinkedList<Nodo> listaelseif;
    int linea;

    public ElseIf(Exp condicion, LinkedList<Nodo> listanodo, LinkedList<Nodo> listaelseif, int linea) {
        this.condicion = condicion;
        this.listanodo = listanodo;
        this.listaelseif = listaelseif;
        this.linea = linea;
    }

    @Override
    public Object ejecutar(Entorno e) {
        debugger(e);
        Entorno local = new Entorno(e);

        if (condicion.getTipo(e).esbooleano()) {
            if ((boolean) condicion.getValor(e) == true) {
                for (Nodo n : listanodo) {
                    if (n instanceof Ins) {
                        if (n instanceof Break) {
                            if (e.get("break") != null) {
                                return n;
                            } else {
                                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, LA SENTENCIA BREAK SOLO PUEDE SER USADA EN CICLOS Y SWITCH, Linea: " + getLinea());
                            }
                        }

                        if (n instanceof Continue) {
                            if (e.get("continue") != null) {
                                return n;
                            } else {
                                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, LA SENTENCIA CONTINUE SOLO PUEDE SER USADA EN CICLOS Y SWITCH, Linea: " + getLinea());
                            }
                        }

                        Ins inst = (Ins) n;
                        Object o = inst.ejecutar(local);
                        if(o!=null){
                            return o;
                        }
                    } else if (n instanceof Exp) {
                        Exp exp = (Exp) n;
                        //Object o = exp.getValor(local);
                        if (n instanceof Retorno) {
                            return n;
                        }
                    }
                }
            } else {
                for (Nodo nodo : listaelseif) {
                    if (nodo instanceof If) {
                        If ifactual = (If) nodo;
                        if (ifactual.condicion.getTipo(e).esbooleano()) {
                            if ((boolean) ifactual.condicion.getValor(e) == true) {
                                for (Nodo n : ifactual.listanodo) {
                                    if (n instanceof Ins) {
                                        if (n instanceof Break) {
                                            if (e.get("break") != null) {
                                                return n;
                                            } else {
                                                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, LA SENTENCIA BREAK SOLO PUEDE SER USADA EN CICLOS Y SWITCH, Linea: " + getLinea());
                                            }
                                        }

                                        if (n instanceof Continue) {
                                            if (e.get("continue") != null) {
                                                return n;
                                            } else {
                                                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, LA SENTENCIA CONTINUE SOLO PUEDE SER USADA EN CICLOS Y SWITCH, Linea: " + getLinea());
                                            }
                                        }
                                        Ins inst = (Ins) n;
                                        Object o = inst.ejecutar(local);
                                        if(o!=null){
                                            return o;
                                        }
                                    } else if (n instanceof Exp) {
                                        Exp exp = (Exp) n;
                                        //Object o = exp.getValor(local);
                                        if (n instanceof Retorno) {
                                            return n;
                                        }
                                    }
                                }
                                return null;
                            }
                        }
                    }
                }
            }
        }
        return null;
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
