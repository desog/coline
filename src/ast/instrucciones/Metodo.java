/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import ast.expresiones.Tipo;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Metodo extends Simbolo implements Ins {
    LinkedList<Modificador> modificador;
    LinkedList<Parametro> Parametros;
    int Dimension;
    Sentencias sentencias;
    int linea;

    public Metodo(LinkedList<Modificador> modificador, LinkedList<Parametro> Parametros, int Dimension, Sentencias sentencias, Tipo tipo, String id,int linea) {
        super(tipo, id);
        this.modificador = modificador;
        this.Parametros = Parametros;
        this.Dimension = Dimension;
        this.sentencias = sentencias;
        this.linea = linea;
    }
    
   

    @Override
    public Object ejecutar(Entorno e) {
        debugger(e);
        return sentencias.ejecutar(e);
        
    }

    @Override
    public int getLinea() {
        return linea;
    }

    public LinkedList<Modificador> getModificador() {
        return modificador;
    }

    public void setModificador(LinkedList<Modificador> modificador) {
        this.modificador = modificador;
    }

    public LinkedList<Parametro> getParametros() {
        return Parametros;
    }

    public void setParametros(LinkedList<Parametro> Parametros) {
        this.Parametros = Parametros;
    }

    public int getDimension() {
        return Dimension;
    }

    public void setDimension(int Dimension) {
        this.Dimension = Dimension;
    }

    public Sentencias getSentencias() {
        return sentencias;
    }

    public void setSentencias(Sentencias sentencias) {
        this.sentencias = sentencias;
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
