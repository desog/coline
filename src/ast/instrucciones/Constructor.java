/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import Herramientas.ArbolArreglos.Arreglo;
import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import ast.expresiones.Exp;
import ast.expresiones.Identificador;
import ast.expresiones.Tipo;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Constructor extends Simbolo implements Ins {
    
    LinkedList<Parametro> parametros;
    Sentencias sentencias;
    LinkedList<Modificador> modificador;
    int linea;
    
    public Constructor(Tipo tipo, String id,LinkedList<Parametro> parametros,Sentencias sentencias,LinkedList<Modificador> modificador,int linea) {
        super(tipo, id);
        this.parametros = parametros;
        this.sentencias = sentencias;
        this.modificador = modificador;
        this.linea = linea;
    }
    

    @Override
    public Object ejecutar(Entorno e) {
        debugger(e);
        Entorno local = new Entorno(e);
        for(Parametro p : parametros){
            if(p.getArreglo()!=null){
                Arreglo arreglo = p.getArreglo();
                Simbolo s = new Simbolo(p.getTipo(),arreglo.getId(),p.getValor(),arreglo.getDimensiones());
                local.agregar(arreglo.getId()+"|arreglo", s);
            }else{
            Simbolo s = new Simbolo(p.getTipo(),p.getIdentificador(), p.getValor());
            local.agregar(p.getIdentificador()+"|variable", s);
            }
        }
        if(sentencias!=null){
        sentencias.ejecutar(local);
        }
        return null;
    }

    @Override
    public int getLinea() {
      return linea;
    }

    public LinkedList<Parametro> getParametros() {
        return parametros;
    }

    public void setParametros(LinkedList<Parametro> parametros) {
        this.parametros = parametros;
    }

    public Sentencias getSentencias() {
        return sentencias;
    }

    public void setSentencias(Sentencias sentencias) {
        this.sentencias = sentencias;
    }

    public LinkedList<Modificador> getModificador() {
        return modificador;
    }

    public void setModificador(LinkedList<Modificador> modificador) {
        this.modificador = modificador;
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
