/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.operaciones;

import TablaSimbolos.Simbolo;
import ast.Nodo;
import ast.entorno.Entorno;
import ast.expresiones.Exp;
import ast.expresiones.Tipo;
import ast.expresiones.Tipo.Tipos;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Aritmeticas implements Exp, Operacion {

    Exp opizq;
    Exp opder;
    Operacion.Operador operador;
    int linea;

    public Aritmeticas(Exp opizq, Exp opder, Operacion.Operador operador, int linea) {
        this.opizq = opizq;
        this.opder = opder;
        this.operador = operador;
        this.linea = linea;
    }

    @Override
    public Object getValor(Entorno e) {
        debugger(e);
        try {
            Tipo tipo = getTipoDominante(e);
            if (tipo.tr != null) {
                if (tipo.tr.equals("nulo")) {
                    evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO NO SE PUEDEN REALIZAR OPERACIONES ARITMETICAS CON EL VALOR NULL, " + linea);
                }
            } else if (tipo != null) {
                if (this.operador == Operacion.Operador.MAS) {
                    if (tipo.esString()) {
                        return opizq.getValor(e).toString() + opder.getValor(e).toString();
                    } else if (tipo.esDouble()) {
                        Tipo tipoizq = opizq.getTipo(e);
                        Tipo tipoder = opder.getTipo(e);
                        Object o = opder.getValor(e);
                         double rchar = (tipoizq.esChar() ? (char) opizq.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opizq.getValor(e)))) + (tipoder.esChar() ? (char) opder.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opder.getValor(e))));
                         return rchar;
                    } else if (tipo.esInt()) {
                        Tipo tipoizq = opizq.getTipo(e);
                        Tipo tipoder = opder.getTipo(e);
                        //VERIFICAR LOS DE DOUBLE PARA TODO O CAMBIAR EL CASTEO
                        
                 
                        
                        int rchar = (tipoizq.esChar() ? (char) opizq.getValor(e).toString().charAt(0) : Integer.valueOf(String.valueOf(opizq.getValor(e)))) + (tipoder.esChar() ? (char) opder.getValor(e).toString().charAt(0) : Integer.valueOf(String.valueOf(opder.getValor(e))));
                        return rchar;
                    }
                } else if (this.operador == Operacion.Operador.MENOS) {
                    if (tipo.esString()) {
                        evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO NO SE PUEDEN RESTAR CADENAS");
                        return null;
                    } else if (tipo.esDouble()) {
                        Tipo tipoizq = opizq.getTipo(e);
                        Tipo tipoder = opder.getTipo(e);
                        Object o = opder.getValor(e);
                         double rchar = (tipoizq.esChar() ? (char) opizq.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opizq.getValor(e)))) - (tipoder.esChar() ? (char) opder.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opder.getValor(e))));
                         return rchar;
                    } else if (tipo.esInt()) {
                        Tipo tipoizq = opizq.getTipo(e);
                        Tipo tipoder = opder.getTipo(e);

                        int rchar = (tipoizq.esChar() ? (char) opizq.getValor(e).toString().charAt(0) : Integer.valueOf(String.valueOf(opizq.getValor(e)))) - (tipoder.esChar() ? (char) opder.getValor(e).toString().charAt(0) : Integer.valueOf(String.valueOf(opder.getValor(e))));

                        return rchar;
                    }
                } else if (this.operador == Operacion.Operador.POR) {
                    if (tipo.esString()) {
                        evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO NO SE PUEDEN MULTIPLICAR CADENAS");
                        return null;
                    } else if (tipo.esDouble()) {
                        Tipo tipoizq = opizq.getTipo(e);
                        Tipo tipoder = opder.getTipo(e);
                        Object o = opder.getValor(e);
                         double rchar = (tipoizq.esChar() ? (char) opizq.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opizq.getValor(e)))) * (tipoder.esChar() ? (char) opder.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opder.getValor(e))));
                         return rchar;
                    } else if (tipo.esInt()) {
                        Tipo tipoizq = opizq.getTipo(e);
                        Tipo tipoder = opder.getTipo(e);

                        int rchar = (tipoizq.esChar() ? (char) opizq.getValor(e).toString().charAt(0) : Integer.valueOf(opizq.getValor(e).toString())) * (tipoder.esChar() ? (char) opder.getValor(e).toString().charAt(0) : Integer.valueOf(opder.getValor(e).toString()));

                        return rchar;
                    }
                } else if (this.operador == Operacion.Operador.DIV) {
                    if (tipo.esString()) {
                        evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO NO SE PUEDEN DIVIDIR CADENAS");
                        return null;
                    } else if (tipo.esDouble()) {
                        double operandoder = Double.valueOf(opder.getValor(e).toString());
                        if (operandoder > 0) {
                            return Double.valueOf(opizq.getValor(e).toString()) / Double.valueOf(opder.getValor(e).toString());
                        } else {
                            evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDEN DIVIDIR NUMEROS ENTRE CERO, Linea: " + getLinea());
                        }
                    } else if (tipo.esInt()) {
                        Tipo tipoizq = opizq.getTipo(e);
                        Tipo tipoder = opder.getTipo(e);
                        int operadoder = (tipoder.esChar() ? (char) opder.getValor(e).toString().charAt(0) : Integer.valueOf(String.valueOf(opder.getValor(e))));
                        if (operadoder > 0) {

                            int rchar = (tipoizq.esChar() ? (char) opizq.getValor(e).toString().charAt(0) : Integer.valueOf(String.valueOf(opizq.getValor(e)))) / (tipoder.esChar() ? (char) opder.getValor(e).toString().charAt(0) : Integer.valueOf(String.valueOf(opder.getValor(e))));

                            return rchar;
                        } else {
                            evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDEN DIVIDIR NUMEROS ENTRE CERO, Linea: " + getLinea());
                        }
                    }
                } else if (this.operador == Operacion.Operador.MOD) {
                    if (tipo.esNumerico()) {
                        if (tipo.esInt()) {
                            Tipo tipoizq = opizq.getTipo(e);
                            Tipo tipoder = opder.getTipo(e);
                            int rchar = (tipoizq.esChar() ? (char) opizq.getValor(e).toString().charAt(0) : Integer.valueOf(opizq.getValor(e).toString())) % (tipoder.esChar() ? (char) opder.getValor(e).toString().charAt(0) : Integer.valueOf(opder.getValor(e).toString()));
                            return rchar;
                        } else if (tipo.esDouble()) {
                            Tipo tipoizq = opizq.getTipo(e);
                        Tipo tipoder = opder.getTipo(e);
                        Object o = opder.getValor(e);
                         double rchar = (tipoizq.esChar() ? (char) opizq.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opizq.getValor(e)))) % (tipoder.esChar() ? (char) opder.getValor(e).toString().charAt(0) : Double.valueOf(String.valueOf(opder.getValor(e))));
                         return rchar;
                        }
                    } else {
                        evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE APLICAR EL OPERADOR % A EXPRESIONES QUE NO SEAN DE TIPO NUMERICO, Linea: " + getLinea());
                    }
                } else if (this.operador == Operacion.Operador.POTENCIA) {
                    if (tipo.esNumerico()) {
                        if (tipo.esInt()) {

                            Tipo tipoizq = opizq.getTipo(e);
                            Tipo tipoder = opder.getTipo(e);
                            double operaizq = (tipoizq.esChar() ? (char) opizq.getValor(e).toString().charAt(0) : Integer.valueOf(opizq.getValor(e).toString()));
                            double operader = (tipoder.esChar() ? (char) opder.getValor(e).toString().charAt(0) : Integer.valueOf(opder.getValor(e).toString()));
                            Double res = Math.pow(operaizq, operader);
                            return res.intValue();
                        } else if (tipo.esDouble()) {
                            double res = Math.pow(Double.valueOf(opizq.getValor(e).toString()), Double.valueOf(opder.getValor(e).toString()));
                            return res;
                        }
                    } else {
                        evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE APLICAR EL OPERADOR POTENCIA A EXPRESIONES QUE NO SEAN DE TIPO NUMERICO, Linea: " + getLinea());
                    }
                }
            }
        } catch (Exception ex) {
            evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDO REALIZAR LA OPERACION ARITMETICA, Linea: " + getLinea());
            return "error";
        }
        return "error";
    }

    @Override
    public Tipo getTipo(Entorno e) {

        return getTipoDominante(e);
    }

    @Override
    public int getLinea() {
        return linea;
    }

    @Override
    public Tipo getTipoDominante(Entorno e) {
        Tipo tipoder = opder.getTipo(e);
        Tipo tipoizq = opizq.getTipo(e);
        if (tipoizq.tr != null && tipoder.tr != null) {
            if (tipoizq.tr.equals("nulo") || tipoder.tr.equals("nulo")) {
                return new Tipo("nulo");
            }
        } else if (tipoizq.getTp() == Tipos.STRING || tipoder.getTp() == Tipos.STRING) {
            return new Tipo(Tipos.STRING);
        } else if (tipoizq.getTp() == Tipos.BOOLEAN || tipoder.getTp() == Tipos.BOOLEAN) {
            evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDEN OPERAR ARITMETICAMENTE VALORES BOOLEANOS DE EXPRESIONES QUE NO SEAN CADENA, Linea: " + getLinea());
            return null;
        } else if (tipoizq.getTp() == Tipos.DOUBLE || tipoder.getTp() == Tipos.DOUBLE) {
            return new Tipo(Tipos.DOUBLE);
        } else if (tipoizq.getTp() == Tipos.INT || tipoder.getTp() == Tipos.INT || tipoizq.getTp() == Tipos.CHAR && tipoder.getTp() == Tipos.CHAR) {
            return new Tipo(Tipos.INT);
        }

        return null;

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
