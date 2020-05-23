/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import ast.expresiones.operaciones.Operacion;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class OpPrefijo implements Exp {

    Exp operando;
    Operacion.Operador operador;
    Identificador id;
    boolean suma;
    int linea;
    
    public OpPrefijo(Identificador id, boolean suma) {
        this.id = id;
        this.suma = suma;
    }

    public OpPrefijo(Exp operando, Operacion.Operador operador,int linea) {
        this.operando = operando;
        this.operador = operador;
        this.linea = linea;
    }

    @Override
    public Object getValor(Entorno e) {
        debugger(e);
        switch (operador) {
            case MENOSU: {
                switch (operando.getTipo(e).getTp()) {
                    case INT: {
                        return -1 * Integer.valueOf(operando.getValor(e).toString());
                    }
                    case DOUBLE: {
                        return -1 * Double.valueOf(operando.getValor(e).toString());
                    }
                    case CHAR: {
                        System.out.println("ERROR SEMANTICO, NO SE PUEDE HACER NEGATIVOS DE UN TIPO CHAR, Linea: " + getLinea());
                        return null;
                    }
                    case STRING: {
                        System.out.println("ERROR SEMANTICO, NO SE PUEDE HACER NEGATIVOS DE UN TIPO STRING, Linea: " + getLinea());
                        return null;
                    }
                    case BOOLEAN: {
                        System.out.println("ERROR SEMANTICO, NO SE PUEDE HACER NEGATIVOS DE UN TIPO BOOLEAN, Linea: " + getLinea());
                        return null;
                    }
                }
            }
            case NOT: {
                switch (operando.getTipo(e).getTp()) {
                    case INT: {
                        System.out.println("ERROR SEMANTICO, EL OPERADOR NOT NO SE PUEDE APLICAR A TIPOS INT, Linea:" + getLinea());
                        return null;
                    }
                    case DOUBLE: {
                        System.out.println("ERROR SEMANTICO, EL OPERADOR NOT NO SE PUEDE APLICAR A TIPOS DOUBLE, Linea:" + getLinea());
                        return null;
                    }
                    case CHAR: {
                        System.out.println("ERROR SEMANTICO, EL OPERADOR NOT NO SE PUEDE APLICAR A TIPOS CHAR, Linea:" + getLinea());
                        return null;
                    }
                    case STRING: {
                        System.out.println("ERROR SEMANTICO, EL OPERADOR NOT NO SE PUEDE APLICAR A TIPOS STRING, Linea:" + getLinea());
                        return null;
                    }
                    case BOOLEAN: {
                        return !(boolean) operando.getValor(e);
                    }
                }
            }
            
        }
        return null;
    }

    @Override
    public Tipo getTipo(Entorno e) {
        return operando.getTipo(e);
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
