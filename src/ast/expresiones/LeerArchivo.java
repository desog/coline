/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class LeerArchivo implements Exp {

    Exp expresion;
    int linea;

    public LeerArchivo(Exp expresion, int linea) {
        this.expresion = expresion;
        this.linea = linea;
    }

    @Override
    public Object getValor(Entorno e) {
        debugger(e);
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            if (expresion.getTipo(e).esString()) {
                String ruta = expresion.getValor(e).toString();
                archivo = new File(ruta);
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);

                String doc = null;
                String linea;
                while ((linea = br.readLine()) != null) {
                    doc+=linea+"\n";
                }
                return doc;
            }

        } catch (Exception ex) {
            System.out.println("ERROR SEMANTICO, NO SE HA PODIDO LEER EL ARCHIVO, Linea: " + linea);
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                System.out.println("ERROR SEMANTICO, NO SE HA PODIDO CERRAR EL ARCHIVO, Linea: " + linea);
            }
        }
        return null;
    }

    @Override
    public Tipo getTipo(Entorno e) {
       return new Tipo(Tipo.Tipos.STRING);
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
