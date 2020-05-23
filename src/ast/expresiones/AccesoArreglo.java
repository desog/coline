/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import Herramientas.ArbolArreglos.Arbol;
import Herramientas.ArbolArreglos.Nodon;
import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class AccesoArreglo implements Exp {
    Identificador id;
    LinkedList<Exp> lista;
    int linea;

    public Identificador getId() {
        return id;
    }

    public void setId(Identificador id) {
        this.id = id;
    }

    public LinkedList<Exp> getLista() {
        return lista;
    }

    public void setLista(LinkedList<Exp> lista) {
        this.lista = lista;
    }
    
    

    public AccesoArreglo(Identificador id, LinkedList<Exp> lista, int linea) {
        this.id = id;
        this.lista = lista;
        this.linea = linea;
    }
    
    public LinkedList<Integer> getListaIndices(LinkedList<Exp> lista,Entorno e){
        LinkedList<Integer> listaresultado = new LinkedList<Integer>();
        for(Exp ex : lista){
            if(ex.getTipo(e).esInt() && Integer.valueOf(ex.getValor(e).toString())>=0){
                listaresultado.add(Integer.valueOf(ex.getValor(e).toString()));
            }else{
                System.out.println("ERROR SEMANTICO, LOS INDICES PARA ACCEDER A UN ARREGLO DEBEN SER ENTEROS POSITIVOS, Linea: "+ linea);
                break;
            }
           
        }
         return listaresultado;
    }

    @Override
    public Object getValor(Entorno e) {
        debugger(e);
        LinkedList<Integer> listaindices =getListaIndices(lista, e);
        Simbolo s =e.get(id.getVal());
        Nodon n = (Nodon) s.getValor();
        Arbol ar = new Arbol(listaindices,s.getTipo() );
        
            n = ar.ObtenerNodo(0, n);//RECORRIDO DEL ARBOL DEL ARREGLO
            if(n==null){
                System.out.println("ERROR UNA DE LAS POSICIONES A LAS QUE SE QUIERE ACCEDER DEL ARREGLO SOBREPASA LOS INDICES, Linea, " + linea);
            }else{
                if(n.etiqueta.equals(Nodon.etiquetaNodo.INTERIOR)){
                    Object o = n;
                    return o;
                }else{
                    if(n.getValor() instanceof Literal){
                        Literal valor = (Literal) n.getValor();
                        return valor.getValor(e);
                    }else{
                        if(n.getValor() instanceof Exp){
                            Exp valor = (Exp) n.getValor();
                            return valor.getValor(e);
                        }
                        Object valor = n.getValor();
                        return valor;
                    }
                    
                }
            }
            return null;
    }

    @Override
    public Tipo getTipo(Entorno e) {
         LinkedList<Integer> listaindices =getListaIndices(lista, e);
        Simbolo s =e.get(id.getVal());
        Nodon n = (Nodon) s.getValor();
        Arbol ar = new Arbol(listaindices,s.getTipo() );
        
            n = ar.ObtenerNodo(0, n);//RECORRIDO DEL ARBOL DEL ARREGLO
            if(n==null){
                System.out.println("ERROR UNA DE LAS POSICIONES A LAS QUE SE QUIERE ACCEDER DEL ARREGLO SOBREPASA LOS INDICES, Linea, " + linea);
            }else{
                Tipo valor = s.getTipo();
                return valor;
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
