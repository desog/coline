/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import ast.expresiones.Acceso;
import ast.expresiones.AccesoIzq;
import ast.expresiones.Exp;
import ast.expresiones.Tipo;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class AsignacionAcceso implements Ins {

    AccesoIzq acceso;
    Exp val;
    int linea;

    public AsignacionAcceso(AccesoIzq acceso, Exp val, int linea) {
        this.acceso = acceso;
        this.val = val;
        this.linea = linea;
    }

    @Override
    public Object ejecutar(Entorno e) {
        debugger(e);
        Object o = acceso.ejecutar(e);
        if (o instanceof Simbolo) {
            Simbolo s = (Simbolo) o;
            if (s != null) {
                Tipo t = val.getTipo(e);
                Tipo tid = s.getTipo();
                if (tid.tr == null && t.tr == null) {
                    if (t.tp.equals(tid.tp)) {
                        if (s.getModificadores() != null) {
                            if (VerificarFinal(s.getModificadores())) {
                                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE ASIGNAR UN VALOR A UN ELEMENTO DE TIPO FINAL, Linea: " + linea);
                            } else {
                                s.setValor(val.getValor(e));
                                Object vas = val.getValor(e);
                            }
                        } else {
                            s.setValor(val.getValor(e));
                            Object vas = val.getValor(e);
                        }
                    } else {
                        evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE ASIGNAR UN TIPO: " + t.tp.toString() + " A UN TIPO: " + tid.tp.toString() + ", Linea: " + linea);
                    }
                } else if (t.esString() && tid.tr != null) {
                    if (tid.tr.equals("nulo")) {
                        if (s.getModificadores() != null) {
                            if (VerificarFinal(s.getModificadores())) {
                                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE ASIGNAR UN VALOR A UN ELEMENTO DE TIPO FINAL, Linea: " + linea);
                            } else {
                                s.setValor(val.getValor(e));
                                Object vas = val.getValor(e);
                            }
                        } else {
                            s.setValor(val.getValor(e));
                            Object vas = val.getValor(e);
                        }
                    }

                } else if (t.tr != null && tid.tr != null) {
                    if (t.tr.equals("nulo")) {
                        if (s.getModificadores() != null) {
                            if (VerificarFinal(s.getModificadores())) {
                                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE ASIGNAR UN VALOR A LA VARIABLE: DEBIDO A QUE TIENE UN MODIFICADOR FINAL , Linea: " + linea);
                            } else {
                                s.setValor(val.getValor(e));
                                Object vas = val.getValor(e);
                            }
                        } else {
                            s.setValor(val.getValor(e));
                            Object vas = val.getValor(e);
                        }
                    } else if (t.tr.equals(tid.tr)) {
                        if (s.getModificadores() != null) {
                            if (VerificarFinal(s.getModificadores())) {
                                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE ASIGNAR UN VALOR A UN ELEMENTO DE TIPO FINAL, Linea: " + linea);
                            } else {
                                s.setValor(val.getValor(e));
                                Object vas = val.getValor(e);
                            }
                        } else {
                            s.setValor(val.getValor(e));
                            Object vas = val.getValor(e);
                        }
                    } else {
                        evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE ASIGNAR UN TIPO: " + t.tp.toString() + " A UN TIPO: " + tid.tp.toString() + ", Linea: " + linea);
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
