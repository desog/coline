/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import Herramientas.ArbolArreglos.Nodon;
import TablaSimbolos.Simbolo;
import ast.Nodo;
import ast.entorno.Entorno;
import ast.expresiones.AccesoArreglo;
import ast.expresiones.Exp;
import ast.expresiones.Identificador;
import ast.expresiones.Literal;
import ast.expresiones.Retorno;
import ast.expresiones.Tipo;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class ForEach implements Ins {

    Parametro param;
    Exp expresion;
    LinkedList<Nodo> listanodos;
    int linea;

    public ForEach(Parametro param, Exp expresion, LinkedList<Nodo> listanodos, int linea) {
        this.param = param;
        this.expresion = expresion;
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
        Simbolo iterador = new Simbolo(param.tipo, param.identificador);
        local.agregar(param.identificador + "|variable", iterador);
        if (expresion instanceof AccesoArreglo) {
            AccesoArreglo arreglo = (AccesoArreglo) expresion;
            Object o = arreglo.getValor(e);
            Tipo t = arreglo.getTipo(e);
            if (o instanceof Nodon) {
                Nodon n = (Nodon) o;
                if (t.tp.equals(param.tipo.tp)) {
                    for (Nodon nodo : n.hijos) {
                        if (nodo.getValor() instanceof Literal) {
                            Literal valor = (Literal) nodo.getValor();
                            iterador.setValor(valor.getValor(e));
                        }

                        for (Nodo nodosent : listanodos) {
                            if (nodosent instanceof Ins) {
                                Ins inst = (Ins) nodosent;
                                Object obj = inst.ejecutar(local);
                                if (obj instanceof Break) {
                                    return null;
                                }
                                if (obj instanceof Continue) {
                                    break;
                                }
                            } else if (nodosent instanceof Exp) {
                                Exp exp = (Exp) n;
                                Object obj = exp.getValor(local);
                                if (obj instanceof Retorno) {
                                    return obj;
                                }

                            }
                        }
                    }
                }
            }
        } else if (expresion instanceof Identificador) {
            Identificador id = (Identificador) expresion;
            if (id.getVal().contains("|variable")) {
                String idarreglo = id.getVal().replace("|variable", "|arreglo");
                Simbolo arreglo = e.get(idarreglo);
                Nodon nodo = (Nodon) arreglo.getValor();
                if (arreglo.getTipo().tp == param.getTipo().tp) {

                    for (Nodon n : nodo.getHijos()) {
                        if (n.etiqueta.equals(Nodon.etiquetaNodo.HOJA)) {
                            if (n.getValor() instanceof Literal) {
                            Literal valor = (Literal) n.getValor();
                            iterador.setValor(valor.getValor(e));
                        }

                        for (Nodo nodosent : listanodos) {
                            if (nodosent instanceof Ins) {
                                Ins inst = (Ins) nodosent;
                                Object obj = inst.ejecutar(local);
                                if (obj instanceof Break) {
                                    return null;
                                }
                                if (obj instanceof Continue) {
                                    break;
                                }
                            } else if (nodosent instanceof Exp) {
                                Exp exp = (Exp) n;
                                Object obj = exp.getValor(local);
                                if (obj instanceof Retorno) {
                                    return obj;
                                }

                            }
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
