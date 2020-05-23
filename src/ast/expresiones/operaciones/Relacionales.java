/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.operaciones;

import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import ast.expresiones.Exp;
import ast.expresiones.Tipo;
import ast.expresiones.Tipo.Tipos;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Relacionales implements Operacion, Exp {

    Exp opizq;
    Exp opder;
    Operacion.Operador operador;
    int linea;

    public Relacionales(Exp opizq, Exp opder, Operador operador, int linea) {
        this.opizq = opizq;
        this.opder = opder;
        this.operador = operador;
        this.linea = linea;
    }

    @Override
    public Tipo getTipoDominante(Entorno e) {
        debugger(e);
        Tipo tipoder = opder.getTipo(e);
        Tipo tipoizq = opizq.getTipo(e);
        if (tipoizq.tr != null && tipoder.tr != null) {
            if (tipoizq.tr.equals("nulo") || tipoder.tr.equals("nulo")) {
                return new Tipo("nulo");
            }
        } else if (tipoizq.esbooleano() || tipoder.esbooleano()) {
            return new Tipo(Tipos.BOOLEAN);
        } else if (tipoizq.esString() || tipoder.esString()) {
            return new Tipo(Tipos.STRING);
        } else if (tipoizq.esNumerico() || tipoder.esNumerico()) {
            return new Tipo(Tipos.DOUBLE);
        }
        return null;
    }

    @Override
    public int getLinea() {
        return this.linea;
    }

    @Override
    public Object getValor(Entorno e) {
        Tipo tipo = getTipoDominante(e);
        Tipo tipoizq = opizq.getTipo(e);
        Tipo tipoder = opder.getTipo(e);
        if (tipo != null) {
            if (this.operador == Operacion.Operador.MAYOR) {
                if (tipo.esNumerico()) {

                    double rcharizq = (tipoizq.esChar() ? (char) opizq.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opizq.getValor(e))));
                    double rcharder = (tipoder.esChar() ? (char) opder.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opder.getValor(e))));

                    return rcharizq > rcharder;
                } else {
                    evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE COMPARAR CON EL OPERADOR > TIPOS QUE NO SEAN NUMERICOS, Linea: " + getLinea());
                    return "error";
                }
            } else if (this.operador == Operacion.Operador.MENOR) {
                if (tipo.esNumerico()) {

                    double rcharizq = (tipoizq.esChar() ? (char) opizq.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opizq.getValor(e))));
                    double rcharder = (tipoder.esChar() ? (char) opder.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opder.getValor(e))));

                    return rcharizq < rcharder;
                } else {
                    evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE COMPARAR CON EL OPERADOR < TIPOS QUE NO SEAN NUMERICOS, Linea" + getLinea());
                    return "error";
                }
            } else if (this.operador == Operacion.Operador.MAYORIGUAL) {
                if (tipo.esNumerico()) {

                    double rcharizq = (tipoizq.esChar() ? (char) opizq.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opizq.getValor(e))));
                    double rcharder = (tipoder.esChar() ? (char) opder.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opder.getValor(e))));

                    return rcharizq >= rcharder;
                } else {
                    evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE COMPARAR CON EL OPERADOR >= TIPOS QUE NO SEAN NUMERICOS, Linea" + getLinea());
                    return "error";
                }
            } else if (this.operador == Operacion.Operador.MENORIGUAL) {
                if (tipo.esNumerico()) {

                    double rcharizq = (tipoizq.esChar() ? (char) opizq.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opizq.getValor(e))));
                    double rcharder = (tipoder.esChar() ? (char) opder.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opder.getValor(e))));

                    return rcharizq <= rcharder;
                } else {
                    evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE COMPARAR CON EL OPERADOR <= TIPOS QUE NO SEAN NUMERICOS, Linea: " + getLinea());
                    return "error";
                }
            } else if (this.operador == Operacion.Operador.IGUALACION) {
                if (tipoizq.tr != null && tipoder.tr != null) {
                    Object Objizq = opizq.getValor(e);
                    Object Objder = opder.getValor(e);
                    return Objizq.equals(Objder);
                }
                if (tipoizq.tr != null && tipoder.tr != null) {
                    if (tipo.tr.equals("null")) {
                        if (tipoizq.tr != null && tipoder.tr != null) {
                            return opizq.getValor(e).equals(opder.getValor(e));
                        } else if (tipoizq.esString() && tipoder.tr.equals("nulo")) {
                            return opizq.getValor(e).equals(opder.getValor(e));
                        } else if (tipoder.esString() && tipoizq.tr.equals("nulo")) {
                            return opizq.getValor(e).equals(opder.getValor(e));
                        } else {
                            evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDEN COMPARAR TIPOS PRIMITIVOS CON NULL CON EL OPERADOR ==, Linea: " + getLinea());
                            return "error";
                        }
                    }
                } else if (tipo.esNumerico()) {
                    double rcharizq = (tipoizq.esChar() ? (char) opizq.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opizq.getValor(e))));
                    double rcharder = (tipoder.esChar() ? (char) opder.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opder.getValor(e))));
                    return rcharizq == rcharder;
                } else if (tipoizq.esString() && tipoder.esString()) {
                    return opizq.getValor(e).toString().equals(opder.getValor(e).toString());
                } else if (tipoizq.esbooleano() && tipoder.esbooleano()) {
                    return Objects.equals((Boolean) opizq.getValor(e), (Boolean) opder.getValor(e));
                } else {
                    evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDEN COMPARAR DOS OPERANDOS DE DISTINTO TIPO CON EL OPERADOR ==, Linea: " + getLinea());
                    return "error";
                }
            } else if (this.operador == Operacion.Operador.DIFERENTE) {
                if (tipoizq.tr != null && tipoder.tr != null) {
                    Object Objizq = opizq.getValor(e);
                    Object Objder = opder.getValor(e);
                    return !Objizq.equals(Objder);
                }
                if (tipoizq.tr != null && tipoder.tr != null) {
                    if (tipo.tr.equals("null")) {
                        if (tipoizq.tr != null && tipoder.tr != null) {
                            return opizq.getValor(e).equals(opder.getValor(e));
                        } else if (tipoizq.esString() && tipoder.tr.equals("nulo")) {
                            return opizq.getValor(e).equals(opder.getValor(e));
                        } else if (tipoder.esString() && tipoizq.tr.equals("nulo")) {
                            return opizq.getValor(e).equals(opder.getValor(e));
                        } else {
                            evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDEN COMPARAR TIPOS PRIMITIVOS CON NULL CON EL OPERADOR ==, Linea: " + getLinea());
                            return "error";
                        }

                    }
                } else if (tipo.esNumerico()) {
                    double rcharizq = (tipoizq.esChar() ? (char) opizq.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opizq.getValor(e))));
                    double rcharder = (tipoder.esChar() ? (char) opder.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opder.getValor(e))));
                    return rcharizq != rcharder;
                } else if (tipoizq.esString() && tipoder.esString()) {
                    return !(opizq.getValor(e).toString().equals(opder.getValor(e).toString()));
                } else if (tipoizq.esbooleano() && tipoder.esbooleano()) {
                    return !(Objects.equals((Boolean) opizq.getValor(e), (Boolean) opder.getValor(e)));
                } else {
                    evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDEN COMPARAR DOS OPERANDOS DE DISTINTO TIPO CON EL OPERADOR != ,Linea: " + getLinea());
                    return "error";
                }
            }
        }

        return "error";
    }

    @Override
    public Tipo getTipo(Entorno e) {
        return new Tipo(Tipos.BOOLEAN);
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
