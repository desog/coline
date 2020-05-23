/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import TablaSimbolos.Simbolo;
import ast.Nodo;
import ast.entorno.Entorno;
import ast.expresiones.Exp;
import java.util.LinkedList;
/**
 *
 * @author Diego
 */
public class Super implements Ins {
   LinkedList<Exp> parametros;
   int linea;
   
    public Super(LinkedList<Exp> parametros, int linea) {
        this.parametros = parametros;
        this.linea = linea;
    }

    @Override
    public Object ejecutar(Entorno e) {
        if(e.getGlobalClase()!=null){
            Entorno padre = e.getGlobalClase();
            Constructor constructor = null;
            for(Simbolo s : padre.tabla.values()){
                if(s instanceof Constructor){
                    Constructor construct = (Constructor)s;
                    LinkedList<Parametro> param = construct.getParametros();
                        if(param.size()==parametros.size()){
                            constructor = construct;
                            for (int i = 0; i < param.size(); i++) {
                            if (param.get(i).getTipo().tr != null) {
                                    if (!(param.get(i).getTipo().tr.equals(parametros.get(i).getTipo(e).tr))) {
                                        break;
                                    } else {
                                        param.get(i).setValor(parametros.get(i).getValor(e));
                       
                                    }
                                } else {
                                    if (!(param.get(i).getTipo().tp.equals(parametros.get(i).getTipo(e).tp))) {
                                        break;
                                    } else {
                                        param.get(i).setValor(parametros.get(i).getValor(e));
                          
                                    }
                                }
                            }
                        }
                }
            }if (constructor != null) {
                    constructor.ejecutar(padre);
                } else {
                    evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE HA ENCONTRADO UN COSTRUCTOR CON LA FIRMA QUE SE ENVIA EN LA CLASE: " + e.getNombrePadre().toString() + " Linea:" + linea);
                    return null;
                }
        }
        return null;
    }

    @Override
    public int getLinea() {
        return linea;
    }

    @Override
    public int debugger(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
   
    
}
