/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import Herramientas.ArbolArreglos.ListaClase;
import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import ast.expresiones.Exp;
import ast.instrucciones.Clase;
import ast.instrucciones.DeclaracionGlobal;
import ast.instrucciones.Importar;
import ast.instrucciones.Ins;
import ast.instrucciones.Metodo;
import java.util.LinkedList;

/**
 *
 * @author Diego
 */
public class Ast {

    public Ast(LinkedList<Nodo> Sentencias) {
        this.Sentencias = Sentencias;
    }
    LinkedList<Nodo> Sentencias;
    public static LinkedList<ListaClase> listaclases;
    public static LinkedList<String> listaNombresClases;
    
    public Object ejecutar() {
        listaclases = new LinkedList<ListaClase>();
        listaNombresClases= new LinkedList<String>();
        Entorno globalH = new Entorno(null);
        Entorno global = new Entorno(null);
        for (Nodo nodo : Sentencias) {
            if (nodo instanceof Clase) {
                Entorno globalclase = new Entorno(null);
                Object result = ((Clase) nodo).ejecutar(globalclase);
                globalH =globalclase;
                if (result != null) {
                    if(!listaclases.contains(result)){
                        ListaClase cl = (ListaClase) result;
                        listaNombresClases.add(cl.getId());
                    listaclases.add((ListaClase) result);
                    }
                }
            } else if (nodo instanceof Importar) {
                Importar imp = (Importar)nodo;
                imp.ejecutar(global);
            }
        }
        
        for(ListaClase clase : listaclases){
            for(Simbolo s : clase.getEntorno().tabla.values()){
                if(s instanceof Metodo){
                    Metodo metodo = (Metodo) s;
                    if(metodo.getId().equalsIgnoreCase("main")){
                        metodo.ejecutar(clase.getEntorno());
                        break;
                    }
                }
            }
        }
        return globalH;
    }
}
