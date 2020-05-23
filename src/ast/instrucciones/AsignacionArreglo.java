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
import ast.expresiones.Identificador;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class AsignacionArreglo implements Ins{
    
    Identificador id;
    LinkedList<Exp> listadimensiones;
    Exp valor;
    int linea;

    public AsignacionArreglo(Identificador id, LinkedList<Exp> listadimensiones, Exp valor, int linea) {
        this.id = id;
        this.listadimensiones = listadimensiones;
        this.valor = valor;
        this.linea = linea;
    }
    
    
    
    
    
    
    @Override
    public Object ejecutar(Entorno e) {
        debugger(e);
        LinkedList<Integer> listaindices =getListaIndices(listadimensiones, e);
        Simbolo s =e.get(id.getVal());
        Nodon n = (Nodon) s.getValor();
        Arbol ar = new Arbol(listaindices,s.getTipo() );
        
            n = ar.ObtenerNodo(0, n);
            if(n==null){
                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR UNA DE LAS POSICIONES A LAS QUE SE QUIERE ACCEDER DEL ARREGLO SOBREPASA LOS INDICES, Linea, " + linea);
            }else{
                if(s.getTipo().getTp().equals(valor.getTipo(e).tp)){
                    if(s.getModificadores()!=null){
                        if(!VerificarFinal(s.getModificadores())){
                            n.valor=valor.getValor(e);
                        }else{
                            evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, EL ARREGLO AL CUAL SE LE QUIERE ASIGNAR UN VALOR ES DE TIPO FINAL, Linea, " + linea);
                        }
                    }else{
                    n.valor=valor.getValor(e);
                    }
                }else if(s.getTipo().tr!=null && valor.getTipo(e).tr !=null){
                    
                    if(s.getTipo().tr.equals(valor.getTipo(e).tr)){
                        if(s.getModificadores()!=null){
                            if(!VerificarFinal(s.getModificadores())){
                                n.valor= valor.getValor(e);
                            }else{
                                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, EL ARREGLO AL CUAL SE LE QUIERE ASIGNAR UN VALOR ES DE TIPO FINAL, Linea, " + linea);
                            }
                        }else{
                            n.valor= valor.getValor(e);
                        }
                        
                    }
                }//AQUI DEBE DE IR PARA LOS OBJETOS UN else if(s.getTipo().getTs.equals(valor.getTipo(e).ts)
                
            }
        
        return null;
    }

    @Override
    public int getLinea() {
        return linea;
    }
    
    public LinkedList<Integer> getListaIndices(LinkedList<Exp> lista,Entorno e){
        LinkedList<Integer> listaresultado = new LinkedList<Integer>();
        for(Exp ex : lista){
            if(ex.getTipo(e).esInt() && Integer.valueOf(ex.getValor(e).toString())>=0){
                listaresultado.add(Integer.valueOf(ex.getValor(e).toString()));
            }else{
                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, LOS INDICES PARA ACCEDER A UN ARREGLO DEBEN SER ENTEROS POSITIVOS, Linea: "+ linea);
                break;
            }
           
        }
         return listaresultado;
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
