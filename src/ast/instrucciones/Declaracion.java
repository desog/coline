/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import Herramientas.ArbolArreglos.Nodon;
import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import ast.expresiones.Exp;
import ast.expresiones.Instancia;
import ast.expresiones.Tipo;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Declaracion implements Ins {

    Tipo tipo;
    LinkedList<Var> listaVariables;
    int linea;

    public Declaracion(Tipo tipo, LinkedList<Var> listaVariables, int linea) {
        this.tipo = tipo;
        this.listaVariables = listaVariables;
        this.linea = linea;
    }

    @Override
    public Object ejecutar(Entorno e) {
        debugger(e);
        for (Var v : listaVariables) {
            if (v.dimension != 0) {
                if (e.get(v.getId()) == null) {
                    if (v.getExpresion() != null) {
                        if (v.getExpresion() instanceof Instancia) {
                            DeclaArreglo deca = new DeclaArreglo(tipo, v.getId(), v.getDimension(), linea, v.getExpresion());
                            Nodon nodoraiz = (Nodon) deca.ejecutar(e);
                            if (nodoraiz != null) {
                                Simbolo s = new Simbolo(tipo, v.getId(), nodoraiz, v.getDimension());
                                e.agregar(v.getId() + "|arreglo", s);
                            }
                        } else {
                            Simbolo s = new Simbolo(tipo, v.getId(), v.getExpresion().getValor(e), v.getDimension());
                            e.agregar(v.getId() + "|arreglo", s);
                        }

                    } else {
                        Simbolo s = new Simbolo(tipo, v.getId());
                        e.agregar(v.getId() + "|arreglo", s);
                    }
                }
            } else {
                if (e.get(v.getId()) == null) {
                    if (v.getExpresion() != null) {
                        if (tipo.tr != null && v.getExpresion().getTipo(e).tr != null) {
                            if (v.getExpresion().getTipo(e).tr.equals("nulo")) {
                                Simbolo s = new Simbolo(tipo, v.getId(), v.getExpresion().getValor(e));
                                e.agregar(v.getId() + "|variable", s);
                            } else if (tipo.tr.equals(v.getExpresion().getTipo(e).tr)) {
                                Simbolo s = new Simbolo(tipo, v.getId(), v.getExpresion().getValor(e));
                                e.agregar(v.getId() + "|variable", s);
                            }
                        }else if (tipo.esString() && v.getExpresion().getTipo(e).tr!=null){
                            if(v.getExpresion().getTipo(e).tr.equals("nulo")){
                            Simbolo s = new Simbolo(tipo, v.getId(), v.getExpresion().getValor(e));
                            e.agregar(v.getId() + "|variable", s);
                            }
                        } else if (VerificacionTipos(tipo,v.getExpresion().getTipo(e))) {
                            Simbolo s = new Simbolo(tipo, v.getId(), v.getExpresion().getValor(e));
                            e.agregar(v.getId() + "|variable", s);
                        } else {
                            evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO NO SE PUEDE CREAR LA VARIABLE : " + v.getId() + " EL TIPO DE LA ASIGNACION NO COINCIDE CON EL TIPO DECLARADO ,Linea: " + getLinea());
                        }
                    } else {
                        Simbolo s = new Simbolo(tipo, v.getId(),"nulo");
                        e.agregar(v.getId() + "|variable", s);
                    }
                } else {
                    evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO NO SE PUEDE CREAR LA VARIABLE : " + v.getId() + " ESTA YA EXISTE EN EL ENTRONO ACTUAL ,Linea: " + getLinea());
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

    
    public boolean VerificacionTipos(Tipo t1, Tipo t2){
        if(t1.tp!=null && t2.tp!=null){
            if(t1.esNumerico() && t2.esNumerico()){
                return true;
            }else if(t1.esString() && t2.esString()){
                return true;
            }else if(t1.esChar() && t2.esChar()){
                return true;
            }
        }else{
            return false;
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
