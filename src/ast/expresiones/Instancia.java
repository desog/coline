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
public class Instancia implements Exp{

    
    Tipo tipo;
    LinkedList<Exp> listatamaño;
    int linea;

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public LinkedList<Exp> getListatamaño() {
        return listatamaño;
    }

    public void setListatamaño(LinkedList<Exp> listatamaño) {
        this.listatamaño = listatamaño;
    }
    
    
    
    public Instancia(Tipo tipo, LinkedList<Exp> listacorchetes,int linea) {
        this.tipo = tipo;
        this.listatamaño = listacorchetes;
        this.linea = linea;
    }
    
    
    
    @Override
    public Object getValor(Entorno e) {
        debugger(e);
                //COMPROBACION DE LA INSTANCIA
                LinkedList<Integer> listaindices = new LinkedList<Integer>();
                 //FALTA HACER PARA LAS CLASES OBJETOS
                    if (this.getListatamaño() != null) { //VERIFICACION DE LA LISTA DE INDICES
                        LinkedList<Exp> listatam = this.getListatamaño();
                        for (Exp ex : listatam) {
                            Tipo t = ex.getTipo(e); //VERIFICACION DEL TIPO
                            if (t.esInt()) {
                                listaindices.add(Integer.valueOf(ex.getValor(e).toString()));
                            } else {
                                System.out.println("ERROR SEMANTICO, LOS INDICIES SOLO PUEDEN SER ENTEROS, Linea: " + getLinea());
                            }
                        }
                    }
                  
                                            Nodon nodores;
                    Arbol a = new Arbol(listaindices, tipo);
                    
                        nodores=a.crearArreglo(0); //SE LE PASA EL INDICE CERO PARA QUE EMPIECE COMO RAIZ
                        return nodores;
                    }

                

            
    

    @Override
    public Tipo getTipo(Entorno e) {
       return tipo;
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
