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
import ast.expresiones.Tipo;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Switch implements Ins {

    Exp control;
    LinkedList<Ins> listanodo;
    boolean entro = true;
    int linea;
    
    public Switch(Exp control, LinkedList<Ins> listanodo,int linea) {
        this.control = control;
        this.listanodo = listanodo;
        this.linea = linea;
    }

    @Override
    public Object ejecutar(Entorno e) {
        debugger(e);
        Entorno local = new Entorno(e);
        Simbolo s = new Simbolo(new Tipo(Tipo.Tipos.BOOLEAN), "break");
        local.agregar("break", s);

        if (control.getTipo(e).getTp() != null) {
            for (Ins n : listanodo) {
                switch (control.getTipo(e).getTp()) {
                    case INT: {

                        if (n instanceof Casos) {
                            Casos casos = (Casos) n;
                            if (casos.valor != null && casos.valor.getTipo(e).esInt()) {
                                if (Integer.valueOf(control.getValor(e).toString()) == Integer.valueOf(casos.valor.getValor(e).toString()) || entro ==false) {

                                    Object o = casos.ejecutar(local);
                                    entro = false;
                                    if (o instanceof Break) {
                                        return null;
                                    }
                                }
                            } else {
                                System.out.println("ERROR SEMANTICO, EL TIPO DE LA VARIABLE DE CONTROL (INT) NO COINCIDE CON EL DEL CASO A EVALUAR (" + casos.valor.getTipo(e).tp.toString() + ")" + ", Linea: "+ getLinea());
                            }
                        }

                    }
                }
            }
            for (Nodo n : listanodo) {
                if (n instanceof Defecto) {
                    Defecto def = (Defecto) n;
                    Object o = def.ejecutar(e);
                    return null;
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
