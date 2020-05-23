/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import Herramientas.ArbolArreglos.ListaClase;
import Herramientas.ArbolArreglos.Nodon;
import TablaSimbolos.Simbolo;
import ast.Ast;
import ast.entorno.Entorno;
import ast.instrucciones.Ins;
import java.util.LinkedList;

/**
 *
 * @author Diego
 */
public class AccesoIzq implements Ins {

    LinkedList<Exp> listaAccesos;
    int linea;

    public AccesoIzq(LinkedList<Exp> listaAccesos, int linea) {
        this.listaAccesos = listaAccesos;
        this.linea = linea;
    }

    @Override
    public Object ejecutar(Entorno e) {
        return RecorrerAcceso(0, e, null, e);
    }

    @Override
    public int getLinea() {
        return linea;
    }

    public Object RecorrerAcceso(int indice, Entorno e, Simbolo anterior, Entorno original) {
        if (indice < listaAccesos.size()) {
            Exp expActual = listaAccesos.get(indice);
            if (indice == 0) {
                if (expActual instanceof Identificador) {
                    Identificador id = (Identificador) expActual;
                    Entorno ent = e.getGlobalClase();
                    String nombre = id.getVal();
                    if (nombre.contains("|variable") || nombre.contains("|arreglo")) {
                        int index = nombre.indexOf("|");
                        nombre = nombre.substring(0, index);
                        if (Ast.listaNombresClases.contains(nombre)) {
                            for (ListaClase lc : Ast.listaclases) {
                                if (nombre.equals(lc.getId())) {
                                    return RecorrerAcceso(indice + 1, lc.getEntorno(), anterior, original);
                                }
                            }
                        }
                    }
                    if (ent != null) {
                        if (ent.getNombrePadre() != null) {
                            if (ent.getNombrePadre().equals(id.getVal())) {
                                return RecorrerAcceso(indice + 1, ent.getPadre(), null, original);
                            }
                        }
                    }
                    if (id.getTipo(e).tr != null) {
                        Simbolo s = e.get(id.getVal());
                        Object ob = s.getValor();
                        if (ob instanceof Objeto) {
                            Objeto o = (Objeto) ob;
                            return RecorrerAcceso(indice + 1, o.ent, s, original);
                        }

                    } else if (id.getTipo(e).esString()) {
                        Simbolo s = e.get(id.getVal());
                        return RecorrerAcceso(indice + 1, e, s, original);
                    } else if (id.getVal().contains("|arreglo")) {
                        Simbolo s = e.get(id.getVal());
                        return RecorrerAcceso(indice + 1, e, s, original);
                    }
                } else if (expActual instanceof This) {
                    This este = (This) expActual;
                    Entorno entornoeste = (Entorno) este.getValor(e);
                    return RecorrerAcceso(indice + 1, entornoeste, anterior, original);
                } else if (expActual instanceof AccesoArreglo) {
                    AccesoArreglo arreglo = (AccesoArreglo) expActual;
                    Object o = arreglo.getValor(e);
                    Simbolo s = e.get(arreglo.getId().getVal());
                    Simbolo simbol = new Simbolo(s.getTipo(), s.getId(), o, s.getDimensiones());
                    return RecorrerAcceso(indice + 1, e, simbol, original);

                }

            } else if (indice == listaAccesos.size() - 1) {// FINAL DE LA LISTA!!!!
                if (expActual instanceof Llamada) {
                    Llamada llamada = (Llamada) expActual;

                     Object o = null;
                    if(!original.equals(e)){
                         o = llamada.getValorAcceso(e, original);
                    }else{
                     o = llamada.getValor(e);
                    }
                    if (o != null) {
                        if (o instanceof Objeto) {
                            Objeto ob = (Objeto) o;
                            return ob;

                        }
                        return o;
                    }

                } else if (expActual instanceof Identificador) {
                    Identificador id = (Identificador) expActual;

                    if (id.getTipo(e).tr != null) {
                        Simbolo s = e.get(id.getVal());
                        return s;

                    } else if (id.getTipo(e).tp != null) {
                        Simbolo s = e.get(id.getVal());
                        return s;
                    }
                } else if (expActual instanceof AccesoArreglo) {
                    AccesoArreglo acc = (AccesoArreglo) expActual;
                    Simbolo s = e.get(acc.getId() + "|arreglo");
                    return s;
                }
            } else {

                if (expActual instanceof Identificador) {
                    Identificador id = (Identificador) expActual;
                    if (id.getTipo(e).tr != null) {
                        Simbolo s = e.get(id.getVal());
                        Object ob = s.getValor();
                        if (ob instanceof Objeto) {
                            Objeto o = (Objeto) ob;
                            return RecorrerAcceso(indice + 1, o.ent, s, original);
                        }

                    } else if (id.getTipo(e).esString()) {
                        Simbolo s = e.get(id.getVal());
                        return RecorrerAcceso(indice + 1, e, s, original);
                    } else if (id.getVal().contains("|arreglo")) {
                        Simbolo s = e.get(id.getVal());
                        return RecorrerAcceso(indice + 1, e, s, original);
                    }
                }

            }
        }
        return null;
    }

    public Object verficarMiembrosNativos(Simbolo objeto, String nombre, LinkedList<Exp> listaparametros, Entorno e) {
        nombre = nombre.toLowerCase();
        if (objeto.getTipo().tr != null) {
            switch (nombre) {
                case "getclass": {
                    return objeto.getTipo().tr;
                }
                case "equals": {
                    if (listaparametros.size() == 1) {
                        Object o = listaparametros.get(0).getValor(e);
                        if (o.equals(objeto.getValor())) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        System.out.println("ERROR SEMANTICO, EL MIEMBRO equals SOLO ACEPTA UN 1 ARGUMENTO, Linea: " + linea);
                    }
                }
                case "tostring": {
                    return objeto.getId();
                }
            }
        } else if (objeto.getTipo().esString()) {
            switch (nombre) {
                case "length": {
                    String cadena = objeto.getValor().toString();
                    return cadena.length();
                }
                case "touppercase": {
                    String cadena = objeto.getValor().toString();
                    cadena = cadena.toUpperCase();
                    return cadena;
                }
                case "tolowercase": {
                    String cadena = objeto.getValor().toString();
                    cadena = cadena.toLowerCase();
                    return cadena;
                }
                case "tochararray": {
                    String cadena = objeto.getValor().toString();
                    char[] arr = cadena.toCharArray();

                    LinkedList<Nodon> lista = new LinkedList<Nodon>();
                    for (int i = 0; i < arr.length; i++) {
                        Literal l = new Literal(arr[i], new Tipo(Tipo.Tipos.CHAR), linea);
                        Nodon nuevo = new Nodon(Nodon.etiquetaNodo.HOJA, l);
                        lista.add(nuevo);
                    }
                    Nodon raiz = new Nodon(lista, Nodon.etiquetaNodo.INTERIOR);
                    return raiz;
                }
                case "split": {
                    if (listaparametros.size() == 1) {
                        Object o = listaparametros.get(0).getValor(e);
                        if (o instanceof String) {
                            String cadena = objeto.getValor().toString();
                            String cadsplit[] = cadena.split(o.toString());
                            LinkedList<Nodon> lista = new LinkedList<Nodon>();
                            for (int i = 0; i < cadsplit.length; i++) {
                                Literal l = new Literal(cadsplit[i], new Tipo(Tipo.Tipos.STRING), linea);
                                Nodon nodo = new Nodon(Nodon.etiquetaNodo.HOJA, l);
                                lista.add(nodo);
                            }
                            Nodon raiz = new Nodon(lista, Nodon.etiquetaNodo.INTERIOR);
                            return raiz;
                        }
                    } else {
                        System.out.println("ERROR SEMANTICO, EL METODO SPLIT SOLO ACEPTA 1 ARGUMENTO, Linea: " + linea);
                    }
                }
                case "getclass": {
                    return "String";
                }
                case "tostring": {
                    return objeto.getValor().toString();
                }
                case "equals": {
                    if (listaparametros.size() == 1) {
                        Object o = listaparametros.get(0).getValor(e);
                        if (o.equals(objeto.getValor())) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        System.out.println("ERROR SEMANTICO, EL MIEMBRO equals SOLO ACEPTA UN 1 ARGUMENTO, Linea: " + linea);
                    }
                }

            }
        }
        return null;
    }

    @Override
    public int debugger(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
