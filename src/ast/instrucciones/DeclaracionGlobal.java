    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import Herramientas.ArbolArreglos.Nodon;
import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import ast.expresiones.Instancia;
import ast.expresiones.Tipo;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class DeclaracionGlobal implements Ins {

    LinkedList<Modificador> Modificadores;
    Tipo tipo;
    LinkedList<Var> listaVariables;
    int linea;

    public DeclaracionGlobal(LinkedList<Modificador> Modificadores, Tipo tipo, LinkedList<Var> listaVariables, int linea) {
        this.Modificadores = Modificadores;
        this.tipo = tipo;
        this.listaVariables = listaVariables;
        this.linea = linea;
    }

    public LinkedList<Modificador> getModificadores() {
        return Modificadores;
    }

    public void setModificadores(LinkedList<Modificador> Modificadores) {
        this.Modificadores = Modificadores;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public LinkedList<Var> getListaVariables() {
        return listaVariables;
    }

    public void setListaVariables(LinkedList<Var> listaVariables) {
        this.listaVariables = listaVariables;
    }

    @Override
    public Object ejecutar(Entorno e) {
        debugger(e);
        verificarDuplicados();
        for (Var v : listaVariables) {
            if (v.dimension != 0) {
                if (e.get(v.getId()) == null) {
                    if (v.getExpresion() != null) {
                        if (v.getExpresion() instanceof Instancia) {
                            DeclaArreglo deca = new DeclaArreglo(tipo, v.getId(), v.getDimension(), linea, v.getExpresion());
                            Nodon nodoraiz = (Nodon) deca.ejecutar(e);
                            if (nodoraiz != null) {
                                Simbolo s = new Simbolo(tipo, v.getId(), nodoraiz, Modificadores, v.getDimension());
                                e.agregar(v.getId() + "|arreglo", s);
                            }
                        } else {
                            Simbolo s = new Simbolo(tipo, v.getId(), v.getExpresion().getValor(e), Modificadores, v.getDimension());
                            e.agregar(v.getId() + "|arreglo", s);
                        }

                    } else {
                        Simbolo s = new Simbolo(tipo, v.getId(), Modificadores, v.dimension);
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
                        }else if(tipo.esString() && v.getExpresion().getTipo(e).tr!=null){
                            if(v.getExpresion().getTipo(e).tr.equals("nulo")){
                            Simbolo s = new Simbolo(tipo, v.getId(), v.getExpresion().getValor(e), Modificadores);
                            e.agregar(v.getId() + "|variable", s);
                            }
                        } else if (tipo.tp == v.getExpresion().getTipo(e).tp) {
                            Simbolo s = new Simbolo(tipo, v.getId(), v.getExpresion().getValor(e), Modificadores);
                            e.agregar(v.getId() + "|variable", s);
                        } else {
                            evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO NO SE PUEDE CREAR LA VARIABLE : " + v.getId() + " EL TIPO DE LA ASIGNACION NO COINCIDE CON EL TIPO DECLARADO ,Linea: " + getLinea());
                        }
                    } else {
                        Simbolo s = new Simbolo(tipo, v.getId(),"nulo", Modificadores);
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

    public LinkedList verificarDuplicados() {
        LinkedList<Modificador.visibilidad> nuevaLista = new LinkedList<Modificador.visibilidad>();
        for (Modificador m : Modificadores) {
            if (!nuevaLista.contains(m.getV())) {
                nuevaLista.add(m.getV());
            } else {
                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDEN TENER DOS MODIFICADORES (" + m.getV().toString() + ") DE CLASE DEL MISMO TIPO, Linea: " + linea);
                return null;
            }
        }
        return Modificadores;
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
