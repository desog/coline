/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import ast.expresiones.Identificador;
import ast.expresiones.Tipo;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class LeerConsola implements Ins {

    Identificador id;
    int linea;

    public LeerConsola(Identificador id, int linea) {
        this.id = id;
        this.linea = linea;
    }

    @Override
    public Object ejecutar(Entorno e) {
        debugger(e);
        String error = "";
        while(true){
             String dato = JOptionPane.showInputDialog("Ingrese un valor: " + error);
        if (dato == null || dato == "") {
            dato = JOptionPane.showInputDialog("Ingrese un valor: ");
        }
        if (id != null) {
            Simbolo s = e.get(id.getVal());
            if (s != null) {

                Tipo t = id.getTipo(e);
                if (t.tp != null) {
                    switch (t.tp) {
                        case INT: {
                            if (isNumero(dato)) {
                                int res = Integer.valueOf(dato);
                                s.setValor(res);
                                return null;
                            }else{
                                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, LA VARIABLE"+ id.getVal() +" QUE RECIBE LA LECTURA ES DE TIPO ENTERO Y SE LE HA ENVIADO OTRO TIPO: " + linea);
                                error = " Error, Ingrese un valor de tipo int.";
                                continue;
                            }
                        }case DOUBLE:{
                        if (isNumero(dato)) {
                                double res = Double.valueOf(dato);
                                s.setValor(res);
                                return null;
                            }else{
                                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, LA VARIABLE"+ id.getVal() +" QUE RECIBE LA LECTURA ES DE TIPO DOUBLE Y SE LE HA ENVIADO OTRO TIPO: " + linea);
                                error = " Error, Ingrese un valor de tipo double.";
                                continue;
                        }
                        }case STRING:{
                            s.setValor(dato);
                            return null;
                        }case BOOLEAN:{
                            if(isBooleano(dato)){
                                dato = dato.trim();
                                if(dato.equalsIgnoreCase("true")){
                                    s.setValor(true);
                                    return null;
                                }else if(dato.equalsIgnoreCase("false")){
                                    s.setValor(false);
                                    return null;
                                }
                            }else{
                                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, LA VARIABLE"+ id.getVal() +" QUE RECIBE LA LECTURA ES DE TIPO BOOLEANO Y SE LE HA ENVIADO OTRO TIPO: " + linea);
                                error = " Error, Ingrese un valor de tipo boolean.";
                                continue;
                            }
                        }case CHAR:{
                            if(dato.length()<2){
                                char a = dato.charAt(0);
                                s.setValor(a);
                                return null;
                            }else{
                                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, LA VARIABLE"+ id.getVal() +" QUE RECIBE LA LECTURA ES DE TIPO CHAR Y SE LE HA ENVIADO OTRO TIPO: " + linea);
                                error = " Error, Ingrese un valor de tipo char.";
                                continue;
                            }
                        }
                    }
                }
            }
        }
        }
       
    }

    public static boolean isNumero(String st) {
        try {
            Double.parseDouble(st);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isBooleano(String st){
        st = st.trim();
        if(st.equalsIgnoreCase("true") || st.equalsIgnoreCase("false")){
            return true;
        }else{
            return false;
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
