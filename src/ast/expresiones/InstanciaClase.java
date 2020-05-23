/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import Herramientas.ArbolArreglos.ListaClase;
import TablaSimbolos.Simbolo;
import ast.Ast;
import ast.entorno.Entorno;
import ast.instrucciones.Constructor;
import ast.instrucciones.Declaracion;
import ast.instrucciones.Metodo;
import ast.instrucciones.Modificador;
import ast.instrucciones.Parametro;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class InstanciaClase implements Exp {

    Tipo tipo;
    LinkedList<Exp> ListaArgumentos;
    int linea;
    String idClase;

    public InstanciaClase(Tipo tipo, LinkedList<Exp> ListaArgumentos, int linea) {
        this.tipo = tipo;
        this.ListaArgumentos = ListaArgumentos;
        this.linea = linea;
    }

    @Override
    public Object getValor(Entorno e) {
        debugger(e);
        LinkedList<ListaClase> listaclases = Ast.listaclases;
        Entorno ClaseInstancia = ObtenerEntorno(listaclases);
        LinkedList<Metodo> Metodos = new LinkedList<Metodo>();
        LinkedList<Constructor> Constructores = new LinkedList<Constructor>();
        LinkedList<Simbolo> Variables = new LinkedList<Simbolo>();
        LinkedList<Integer> listaComprob = new LinkedList<Integer>();
        Entorno nuevoEntrono = null;
        if(ClaseInstancia.getPadre()!=null){
             nuevoEntrono = new Entorno(ClaseInstancia.getPadre());
        }else{
            nuevoEntrono= new Entorno(null);
        }
        if(ClaseInstancia.getNombrePadre()!=null){
            nuevoEntrono.setNombrePadre(ClaseInstancia.getNombrePadre());
        }
        
        Modificador mod = new Modificador(Modificador.visibilidad.PRIVATE);

        if (ClaseInstancia != null) {
            for (Simbolo s : ClaseInstancia.tabla.values()) {

                if (s instanceof Metodo) {
                    Metodo metodo = (Metodo) s;

                    if (!metodo.getModificador().contains(mod)) {

                        String cadenaMetodo = "";
                        for (Parametro p : metodo.getParametros()) {
                            if (p.getTipo().tr != null) {
                                cadenaMetodo += "_" + p.getTipo().tr;
                            } else {
                                cadenaMetodo += "_" + p.getTipo().tp.name();
                            }

                        }

                        Metodos.add((new Metodo(metodo.getModificador(), metodo.getParametros(), metodo.getDimension(), metodo.getSentencias(), metodo.getTipo(), metodo.getId(), metodo.getLinea())));
                        nuevoEntrono.agregar(s.getId() + "|metodo"+cadenaMetodo, (new Metodo(metodo.getModificador(), metodo.getParametros(), metodo.getDimension(), metodo.getSentencias(), metodo.getTipo(), metodo.getId(), metodo.getLinea())));
                    }
                } else if (s instanceof Constructor) {
                    Constructor cosnt = (Constructor) s;
                    Constructores.add((Constructor) s);
                    nuevoEntrono.agregar(s.getId() + "|constructor", new Constructor(cosnt.getTipo(), cosnt.getId(), cosnt.getParametros(), cosnt.getSentencias(), cosnt.getModificador(), cosnt.getLinea()));
                } else {
                    if (s.getDimensiones() != 0) {
                        if (s.getValor() != null) {
                            if (VerificarPrivado(s.getModificadores())) {
                                if (VerificarEstatico(s.getModificadores())) {
                                    nuevoEntrono.agregar(s.getId() + "|arreglo", s);
                                } else {
                                    nuevoEntrono.agregar(s.getId() + "|arreglo", new Simbolo(s.getTipo(), s.getId(), s.getValor(), s.getModificadores(), s.getDimensiones()));
                                }
                            }
                        } else {
                            if (VerificarPrivado(s.getModificadores())) {
                                if (VerificarEstatico(s.getModificadores())) {
                                    nuevoEntrono.agregar(s.getId() + "|arreglo", s);
                                } else {
                                    nuevoEntrono.agregar(s.getId() + "|arreglo", new Simbolo(s.getTipo(), s.getId(), s.getModificadores(), s.getDimensiones()));
                                }
                            }
                        }
                    } else if (s.getModificadores() != null && s.getValor() != null) {
                        if (VerificarPrivado(s.getModificadores())) {
                            if (VerificarEstatico(s.getModificadores())) {
                                nuevoEntrono.agregar(s.getId() + "|variable", s);
                            } else {
                                Variables.add(new Simbolo(s.getTipo(), s.getId(), s.getValor(), s.getModificadores()));
                                nuevoEntrono.agregar(s.getId() + "|variable", new Simbolo(s.getTipo(), s.getId(), s.getValor(), s.getModificadores()));
                            }
                        }
                    } else if (s.getModificadores() != null) {
                        if (VerificarPrivado(s.getModificadores())) {
                            if (s.getValor() == null) {
                                if (VerificarEstatico(s.getModificadores())) {
                                    nuevoEntrono.agregar(s.getId() + "|variable", s);
                                } else {
                                    Variables.add(new Simbolo(s.getTipo(), s.getId(), s.getModificadores()));
                                    nuevoEntrono.agregar(s.getId() + "|variable", new Simbolo(s.getTipo(), s.getId(), s.getModificadores()));
                                }
                            } else {
                                if (VerificarEstatico(s.getModificadores())) {
                                    nuevoEntrono.agregar(s.getId() + "|varible", s);
                                } else {
                                    Variables.add(new Simbolo(s.getTipo(), s.getId(), s.getValor(), s.getModificadores()));
                                    nuevoEntrono.agregar(s.getId() + "|variable", new Simbolo(s.getTipo(), s.getId(), s.getValor(), s.getModificadores()));
                                }
                            }
                        }
                    }
                }
            }
            if (Constructores.size() != 0) {
                Constructor construct = null;
                for (Constructor c : Constructores) {

                    LinkedList<Parametro> param = c.getParametros();
                    if (param.size() == ListaArgumentos.size()) {
                        construct = c;
                        if (listaComprob.size() != ListaArgumentos.size()) {
                            for (int i = 0; i < param.size(); i++) {
                                if (param.get(i).getTipo().tr != null) {
                                    if (!(param.get(i).getTipo().tr.equals(ListaArgumentos.get(i).getTipo(e).tr))) {
                                        break;
                                    } else {
                                        param.get(i).setValor(ListaArgumentos.get(i).getValor(e));
                                        listaComprob.add(i);
                                    }
                                } else {
                                    if (!(param.get(i).getTipo().tp.equals(ListaArgumentos.get(i).getTipo(e).tp))) {
                                        break;
                                    } else {
                                        param.get(i).setValor(ListaArgumentos.get(i).getValor(e));
                                        listaComprob.add(i);
                                    }
                                }
                            }
                        }
                    } else {

                    }
                }
                if (construct != null) {
                    if(construct.getSentencias()!=null){
                    construct.ejecutar(nuevoEntrono);
                    }
                } else {
                    evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE HA ENCONTRADO UN COSTRUCTOR CON LA FIRMA QUE SE ENVIA EN LA CLASE: " + idClase.toString() + " Linea:" + linea);
                    return null;
                }
            }
            return new Objeto("",nuevoEntrono);
        }
        return null;
    }

    public Entorno ObtenerEntorno(LinkedList<ListaClase> listaclases) {
        for (ListaClase l : listaclases) {
            if (l.getId().equals(tipo.tr)) {
                idClase = l.getId();
                return l.getEntorno();
            }
        }
        return null;
    }

    @Override
    public Tipo getTipo(Entorno e) {
        return tipo;
    }

    @Override
    public int getLinea() {
        return linea;
    }

    public boolean VerificarPrivado(LinkedList<Modificador> lista) {
        for (Modificador m : lista) {
            if (m.getV() != null) {
                if (m.getV().equals(Modificador.visibilidad.PRIVATE)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean VerificarEstatico(LinkedList<Modificador> lista) {
        for (Modificador m : lista) {
            if (m.getM() != null) {
                if (m.getM().equals(Modificador.Modificadores.STATIC)) {
                    return true;
                }
            }
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
