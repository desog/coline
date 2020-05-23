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
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Ternario implements Exp {
    Exp verdadero;
    Exp falso;
    Exp condicion;
    int linea;

    public Ternario(Exp verdadero, Exp falso, Exp condicion, int linea) {
        this.verdadero = verdadero;
        this.falso = falso;
        this.condicion = condicion;
        this.linea = linea;
    }
    
        
    
    
    @Override
    public Object getValor(Entorno e) {
        debugger(e);
        if(condicion.getValor(e)!=null || condicion.getValor(e)!="error"){
            return (boolean)condicion.getValor(e) == true ? verdadero.getValor(e) : falso.getValor(e);
        }
        else return "error";
    }

    @Override
    public Tipo getTipo(Entorno e) {
        if(condicion.getTipo(e)!=null){
            return (boolean)condicion.getValor(e) == true ? verdadero.getTipo(e) : falso.getTipo(e);
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
