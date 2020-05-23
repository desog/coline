/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import Herramientas.ArbolArreglos.Arreglo;
import Herramientas.ArbolArreglos.Nodon;
import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import ast.instrucciones.DeclaArreglo;
import ast.instrucciones.Metodo;
import ast.instrucciones.Parametro;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Llamada implements Exp {

    String identificador;
    LinkedList<Exp> listaparametros;
    int linea;

    public Llamada(String identificador, LinkedList<Exp> listaparametros, int linea) {
        this.identificador = identificador;
        this.listaparametros = listaparametros;
        this.linea = linea;
    }
    
    @Override
    public Object getValor(Entorno e) {
        debugger(e);
        String cadenallamada = "";
        for (Exp ex : listaparametros) {
            if (ex instanceof Literal) {
                Literal lit = (Literal) ex;
                if (lit.getTipo(e).tr == null) {
                    cadenallamada += "_" + lit.getTipo(e).tp.name();
                } else {
                    cadenallamada += "_" + lit.getTipo(e).tr;
                }
            } else if (ex instanceof Identificador) {
                Identificador id = (Identificador) ex;
                if (id.getTipo(e).tr == null) {
                    cadenallamada += "_" + id.getTipo(e).tp.name();
                } else {
                    cadenallamada += "_" + id.getTipo(e).tr;
                }
            } else {
                if (ex.getTipo(e).tr == null) {
                    cadenallamada += "_" + ex.getTipo(e).tp.name();
                } else {
                    cadenallamada += "_" + ex.getTipo(e).tr;
                }
            }
            }
        Simbolo s = e.get(identificador + "|metodo" + cadenallamada);
        if (s != null) {
            Entorno local =  new Entorno(e);
            
            Metodo metodo = (Metodo) s;
            if (metodo.getParametros().size() == listaparametros.size()) {
                LinkedList<Parametro> listaparam = metodo.getParametros();
                for (int i = 0; i < listaparam.size(); i++) {
                    Tipo TipoParamFormal = listaparam.get(i).getTipo();
                    Tipo TipoParamActual = listaparametros.get(i).getTipo(e);
                    if (TipoParamFormal.tp == TipoParamActual.tp) {
                        if (listaparam.get(i).getArreglo() != null) { //LO DE LOS ARREGLOS REVISAR!!!!!!
                            Arreglo arr = listaparam.get(i).getArreglo();
                            if (listaparametros.get(i) instanceof Instancia) {
                                DeclaArreglo deca = new DeclaArreglo(TipoParamActual, listaparam.get(i).getIdentificador(), arr.getDimensiones(), linea, listaparametros.get(i));
                                Nodon nodoraiz = (Nodon) deca.ejecutar(e);
                                if (nodoraiz != null) {
                                    Simbolo SimboloArr = new Simbolo(TipoParamActual, arr.getId(), nodoraiz, arr.getDimensiones());
                                    local.agregar(listaparam.get(i).getIdentificador() + "|arreglo", SimboloArr);
                                }
                            } else {
                                Simbolo SimboloArr = new Simbolo(TipoParamActual, arr.getId(), listaparametros.get(i).getValor(e), arr.getDimensiones());
                                if (listaparam.get(i).getArreglo() != null) {
                                    local.agregar(listaparam.get(i).getArreglo().getId() + "|arreglo", SimboloArr);
                                } else {
                                    local.agregar(listaparam.get(i).getIdentificador() + "|arreglo", SimboloArr);
                                }
                            }
                        }//AQUI TERMINA LO DE LOS ARREGLOS
                        else {
                            Simbolo nuevoSimbolo = new Simbolo(TipoParamActual, listaparam.get(i).getIdentificador(), listaparametros.get(i).getValor(e));
                            local.agregar(listaparam.get(i).getIdentificador() + "|variable", nuevoSimbolo);
                        }
                    } else if (TipoParamFormal.tr != null && TipoParamActual.tr != null) {
                        if (TipoParamFormal.tr == TipoParamActual.tr) {
                            if (listaparam.get(i).getArreglo() != null) { //LO DE LOS ARREGLOS REVISAR!!!!!!
                                Arreglo arr = listaparam.get(i).getArreglo();
                                if (listaparametros.get(i) instanceof Instancia) {
                                    DeclaArreglo deca = new DeclaArreglo(TipoParamActual, listaparam.get(i).getIdentificador(), arr.getDimensiones(), linea, listaparametros.get(i));
                                    Nodon nodoraiz = (Nodon) deca.ejecutar(e);
                                    if (nodoraiz != null) {
                                        Simbolo SimboloArr = new Simbolo(TipoParamActual, arr.getId(), nodoraiz, arr.getDimensiones());
                                        local.agregar(listaparam.get(i).getIdentificador() + "|arreglo", SimboloArr);
                                    }
                                } else {
                                    Simbolo SimboloArr = new Simbolo(TipoParamActual, arr.getId(), listaparametros.get(i).getValor(e), arr.getDimensiones());
                                    local.agregar(listaparam.get(i).getIdentificador() + "|arreglo", SimboloArr);
                                }
                            }//AQUI TERMINA LO DE LOS ARREGLOS
                            else {
                                Simbolo nuevoSimbolo = new Simbolo(TipoParamActual, listaparam.get(i).getIdentificador(), listaparametros.get(i).getValor(e));
                                local.agregar(listaparam.get(i).getIdentificador() + "|variable", nuevoSimbolo);
                            }
                        }
                    } else {
                        System.out.println("ERROR SEMANTICO, EL PARAMETRO ACTUAL ES DE TIPO " + listaparametros.get(i).getTipo(e).tp.toString() + "Y NO CONCUERDA CON EL PARAMETRO FORMAL , Linea: " + linea);
                        return null;
                    }
                }

                Object resultado = metodo.ejecutar(local);
                if (resultado == null) {
                    if (metodo.getTipo().esVoid()) {
                        return resultado;
                    }
                } else {
                    return resultado;
                }
            } else {
                System.out.println("ERROR SEMANTICO, LA LLAMADA NO CONTIENE EL MISMO NUMERO DE PARAMETROS QUE EL METODO AL QUE SE HACE REFERENCIA, Linea: " + linea);
                return null;
            }
        } else {
            System.out.println("EL METODO: " + identificador + " NO EXISTE EN EL AMBITO ACTUAL, Linea: " + linea);
            return null;
        }
        return null;
    }
    /**
     * 
     * @param e
     * @param parametros
     * @return 
     */
    public Object getValorAcceso(Entorno e, Entorno parametros){
     String cadenallamada = "";
        for (Exp ex : listaparametros) {
            if (ex instanceof Literal) {
                Literal lit = (Literal) ex;
                if (lit.getTipo(parametros).tr == null) {
                    cadenallamada += "_" + lit.getTipo(parametros).tp.name();
                } else {
                    cadenallamada += "_" + lit.getTipo(parametros).tr;
                }
            } else if (ex instanceof Identificador) {
                Identificador id = (Identificador) ex;
                if (id.getTipo(parametros).tr == null) {
                    cadenallamada += "_" + id.getTipo(parametros).tp.name();
                } else {
                    cadenallamada += "_" + id.getTipo(parametros).tr;
                }
            } else {
                if (ex.getTipo(parametros).tr == null) {
                    cadenallamada += "_" + ex.getTipo(parametros).tp.name();
                } else {
                    cadenallamada += "_" + ex.getTipo(parametros).tr;
                }
            }
            }
        Simbolo s = e.get(identificador + "|metodo" + cadenallamada);
        if (s != null) {
            Entorno local =  new Entorno(e);
            
            Metodo metodo = (Metodo) s;
            if (metodo.getParametros().size() == listaparametros.size()) {
                LinkedList<Parametro> listaparam = metodo.getParametros();
                for (int i = 0; i < listaparam.size(); i++) {
                    Tipo TipoParamFormal = listaparam.get(i).getTipo();
                    Tipo TipoParamActual = listaparametros.get(i).getTipo(parametros);
                    if (TipoParamFormal.tp == TipoParamActual.tp) {
                        if (listaparam.get(i).getArreglo() != null) { //LO DE LOS ARREGLOS REVISAR!!!!!!
                            Arreglo arr = listaparam.get(i).getArreglo();
                            if (listaparametros.get(i) instanceof Instancia) {
                                DeclaArreglo deca = new DeclaArreglo(TipoParamActual, listaparam.get(i).getIdentificador(), arr.getDimensiones(), linea, listaparametros.get(i));
                                Nodon nodoraiz = (Nodon) deca.ejecutar(e);
                                if (nodoraiz != null) {
                                    Simbolo SimboloArr = new Simbolo(TipoParamActual, arr.getId(), nodoraiz, arr.getDimensiones());
                                    local.agregar(listaparam.get(i).getIdentificador() + "|arreglo", SimboloArr);
                                }
                            } else {
                                Simbolo SimboloArr = new Simbolo(TipoParamActual, arr.getId(), listaparametros.get(i).getValor(parametros), arr.getDimensiones());
                                if (listaparam.get(i).getArreglo() != null) {
                                    local.agregar(listaparam.get(i).getArreglo().getId() + "|arreglo", SimboloArr);
                                } else {
                                    local.agregar(listaparam.get(i).getIdentificador() + "|arreglo", SimboloArr);
                                }
                            }
                        }//AQUI TERMINA LO DE LOS ARREGLOS
                        else {
                            Simbolo nuevoSimbolo = new Simbolo(TipoParamActual, listaparam.get(i).getIdentificador(), listaparametros.get(i).getValor(parametros));
                            local.agregar(listaparam.get(i).getIdentificador() + "|variable", nuevoSimbolo);
                        }
                    } else if (TipoParamFormal.tr != null && TipoParamActual.tr != null) {
                        if (TipoParamFormal.tr == TipoParamActual.tr) {
                            if (listaparam.get(i).getArreglo() != null) { //LO DE LOS ARREGLOS REVISAR!!!!!!
                                Arreglo arr = listaparam.get(i).getArreglo();
                                if (listaparametros.get(i) instanceof Instancia) {
                                    DeclaArreglo deca = new DeclaArreglo(TipoParamActual, listaparam.get(i).getIdentificador(), arr.getDimensiones(), linea, listaparametros.get(i));
                                    Nodon nodoraiz = (Nodon) deca.ejecutar(e);
                                    if (nodoraiz != null) {
                                        Simbolo SimboloArr = new Simbolo(TipoParamActual, arr.getId(), nodoraiz, arr.getDimensiones());
                                        local.agregar(listaparam.get(i).getIdentificador() + "|arreglo", SimboloArr);
                                    }
                                } else {
                                    Simbolo SimboloArr = new Simbolo(TipoParamActual, arr.getId(), listaparametros.get(i).getValor(parametros), arr.getDimensiones());
                                    local.agregar(listaparam.get(i).getIdentificador() + "|arreglo", SimboloArr);
                                }
                            }//AQUI TERMINA LO DE LOS ARREGLOS
                            else {
                                Simbolo nuevoSimbolo = new Simbolo(TipoParamActual, listaparam.get(i).getIdentificador(), listaparametros.get(i).getValor(parametros));
                                local.agregar(listaparam.get(i).getIdentificador() + "|variable", nuevoSimbolo);
                            }
                        }
                    } else {
                        System.out.println("ERROR SEMANTICO, EL PARAMETRO ACTUAL ES DE TIPO " + listaparametros.get(i).getTipo(parametros).tp.toString() + "Y NO CONCUERDA CON EL PARAMETRO FORMAL , Linea: " + linea);
                        return null;
                    }
                }

                Object resultado = metodo.ejecutar(local);
                if (resultado == null) {
                    if (metodo.getTipo().esVoid()) {
                        return resultado;
                    }
                } else {
                    return resultado;
                }
            } else {
                System.out.println("ERROR SEMANTICO, LA LLAMADA NO CONTIENE EL MISMO NUMERO DE PARAMETROS QUE EL METODO AL QUE SE HACE REFERENCIA, Linea: " + linea);
                return null;
            }
        } else {
            System.out.println("EL METODO: " + identificador + " NO EXISTE EN EL AMBITO ACTUAL, Linea: " + linea);
            return null;
        }
        return null;
    }

    @Override
    public Tipo getTipo(Entorno e) {

        String cadenallamada = "";
        for (Exp ex : listaparametros) {
            if (ex instanceof Literal) {
                Literal lit = (Literal) ex;
                Tipo tipoliteral = lit.getTipo(e);
                if (tipoliteral.tr == null) {
                    cadenallamada += "_" + tipoliteral.tp.name();
                } else {
                    cadenallamada += "_" + tipoliteral.tr;
                }
            } else if (ex instanceof Identificador) {
                Identificador id = (Identificador) ex;
                Tipo tipoid = id.getTipo(e);
                if (tipoid.tr == null) {
                    cadenallamada += "_" + tipoid.tp.name();
                } else {
                    cadenallamada += "_" + tipoid.tr;
                }
            } else {
                Tipo tipoexp = ex.getTipo(e);
                if (tipoexp.tr == null) {
                    
                    cadenallamada += "_" + tipoexp.tp.name();
                } else {
                    cadenallamada += "_" + tipoexp.tr;
                }
            }
        }

        Simbolo s = e.get(identificador + "|metodo" + cadenallamada);
        if (s != null) {
            return s.getTipo();
        } else {
            return null;
        }
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
