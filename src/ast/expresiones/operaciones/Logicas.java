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
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Logicas implements Exp, Operacion {

    Exp opizq;
    Exp opder;
    Operacion.Operador operador;
    int linea;

    public Logicas(Exp opizq, Exp opder, Operador operador, int linea) {
        this.opizq = opizq;
        this.opder = opder;
        this.operador = operador;
        this.linea = linea;
    }
    
    

    @Override
    public Object getValor(Entorno e) {
        debugger(e);
        if (getTipoDominante(e).esbooleano()) {

            if (operador.equals(Operador.AND)) {
                return (Boolean)opizq.getValor(e) && (Boolean)opder.getValor(e);
            }else if(operador.equals(Operador.OR)){
                return  (Boolean)opizq.getValor(e) || (Boolean)opder.getValor(e);
            }else if(operador.equals(Operador.XOR)){
                return (Boolean)opizq.getValor(e) ^ (Boolean)opder.getValor(e);
            }
        }
        return "error";
    }

    @Override
    public Tipo getTipo(Entorno e) {
        return new Tipo(Tipos.BOOLEAN);
    }

    @Override
    public int getLinea() {
        return this.linea;
    }

    @Override
    public Tipo getTipoDominante(Entorno e) {
        if (opizq.getTipo(e).esbooleano() && opder.getTipo(e).esbooleano()) {
            return new Tipo(Tipos.BOOLEAN);
        } else {
            return null;
        }
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
