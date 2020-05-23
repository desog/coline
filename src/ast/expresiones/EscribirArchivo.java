/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class EscribirArchivo implements Exp {
    Exp rutaEscribir;
    Exp Contenido;
    int linea;

    public EscribirArchivo(Exp rutaEscribir, Exp Contenido, int linea) {
        this.rutaEscribir = rutaEscribir;
        this.Contenido = Contenido;
        this.linea = linea;
    }

    @Override
    public Object getValor(Entorno e) {
        debugger(e);
        FileWriter archivo = null;
        PrintWriter pw = null;
        try{
            if(rutaEscribir.getTipo(e).esString()){
                archivo = new FileWriter(rutaEscribir.getValor(e).toString());
                BufferedWriter bw = new BufferedWriter(archivo);
                if(Contenido.getTipo(e)!=null){
                        if(Contenido.getValor(e)!=null){
                        bw.write(Contenido.getValor(e).toString());
                        bw.close();
                        }else{
                        evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDE ESCRIBIR EN ARCHIVO FALLO AL OBTENER VALOR, Linea: " +linea);
                    }
                    
                }
                
                
                
            }else{
                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, LA RUTA PARA ESCRIBIR EL ARCHIVO NO ES VALIDA, Linea: " + linea);
            }
        }catch(Exception ex){
            evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, EL ARCHIVO NO SE HA PODIDO ESCRIBIR, Linea: " + linea);
        }
        return null;
    }

    @Override
    public Tipo getTipo(Entorno e) {
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
