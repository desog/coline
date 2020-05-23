/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import Herramientas.ArbolArreglos.Arbol;
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
public class DeclaArreglo implements Ins {

    Tipo tipo;
    String id;
    int dimensiones;
    int linea;
    Exp instancia;

    public DeclaArreglo(Tipo tipo, String id, int dimensiones, int linea) {

        this.tipo = tipo;
        this.id = id;
        this.dimensiones = dimensiones;
        this.linea = linea;

    }

    public DeclaArreglo(Tipo tipo, String id, int dimensiones, int linea, Exp instancia) {
        this.tipo = tipo;
        this.id = id;
        this.dimensiones = dimensiones;
        this.linea = linea;
        this.instancia = instancia;
    }

    @Override
    public Object ejecutar(Entorno e) {
        debugger(e);
        LinkedList<Integer> listaindices = new LinkedList<Integer>();

        if (instancia != null) {
            if (instancia instanceof Instancia) { //COMPROBACION DE LA INSTANCIA
                Instancia insta = (Instancia) instancia;
                if (insta.getTipo().tp == tipo.tp) { //FALTA HACER PARA LAS CLASES OBJETOS
                    if (insta.getListatamaño() != null) { //VERIFICACION DE LA LISTA DE INDICES
                        LinkedList<Exp> listatam = insta.getListatamaño();
                        for (Exp ex : listatam) {
                            Tipo t = ex.getTipo(e); //VERIFICACION DEL TIPO
                            if (t.esInt()) {
                                listaindices.add(Integer.valueOf(ex.getValor(e).toString()));
                            } else {
                                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, LOS INDICIES SOLO PUEDEN SER ENTEROS, Linea: " + getLinea());
                            }
                        }
                    }
                    if (dimensiones == listaindices.size()) {
                        Nodon nodores;
                        Arbol a = new Arbol(listaindices, tipo);

                        nodores = a.crearArreglo(0); //SE LE PASA EL INDICE CERO PARA QUE EMPIECE COMO RAIZ
                        return nodores;
                    } else {
                        evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, EL ARREGLO ESTA DECLARADO CON: " + dimensiones + " DIMENSIONES Y SE TRATA DE INICIALIZAR CON: " + listaindices.size() + " Linea: " + linea);
                    }

                } else if (insta.getTipo().tr != null && tipo.tr != null) {
                    if (insta.getTipo(e).tr.equals(tipo.tr)) {
                        if (insta.getListatamaño() != null) { //VERIFICACION DE LA LISTA DE INDICES
                        LinkedList<Exp> listatam = insta.getListatamaño();
                        for (Exp ex : listatam) {
                            Tipo t = ex.getTipo(e); //VERIFICACION DEL TIPO
                            if (t.esInt()) {
                                listaindices.add(Integer.valueOf(ex.getValor(e).toString()));
                            } else {
                                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, LOS INDICIES SOLO PUEDEN SER ENTEROS, Linea: " + getLinea());
                            }
                        }
                    }
                    if (dimensiones == listaindices.size()) {
                        Nodon nodores;
                        Arbol a = new Arbol(listaindices, tipo);

                        nodores = a.crearArreglo(0); //SE LE PASA EL INDICE CERO PARA QUE EMPIECE COMO RAIZ
                        return nodores;
                    } else {
                        evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, EL ARREGLO ESTA DECLARADO CON: " + dimensiones + " DIMENSIONES Y SE TRATA DE INICIALIZAR CON: " + listaindices.size() + " Linea: " + linea);
                    }
                    }

                }

            }

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
