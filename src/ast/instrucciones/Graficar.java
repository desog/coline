/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import ast.expresiones.Exp;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Graficar implements Ins {

    Exp ruta;
    Exp contenido;
    int linea;

    public Graficar(Exp ruta, Exp contenido, int linea) {
        this.ruta = ruta;
        this.contenido = contenido;
        this.linea = linea;
    }

    @Override
    public Object ejecutar(Entorno e) {
        debugger(e);
        boolean banderaArchivo = false;
        if (ruta.getTipo(e).esString()) {
            if (contenido.getTipo(e).esString()) {
                String rutaescribir = ruta.getValor(e).toString();
                String content = contenido.getValor(e).toString();
                FileWriter archivo = null;
                content = content.replace("\\n", "\n");
                content = content.replace("\\\"", "\"");
                content = content.replace("\\\'", "\'");
                content = content.replace("\\b", "\b");
                content = content.replace("\\r", "\r");
                PrintWriter pw = null;
                try {

     
                    
                    archivo = new FileWriter(evaluacion1junio.Evaluacion1Junio.rutarelativa + "\\" + "grafica.dot");
                    BufferedWriter bw = new BufferedWriter(archivo);
                    bw.write(content);
                    bw.close();

                } catch (Exception ex) {
                    evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, EL ARCHIVO NO SE HA PODIDO ESCRIBIR, Linea: " + linea);
                    banderaArchivo = true;
                }

                if (banderaArchivo == false) {
                    try {
                        ProcessBuilder pbuilder;

                        /*
                         * Realiza la construccion del comando    
                         * en la linea de comandos esto es: 
                         * dot -Tpng -o archivo.png archivo.dot
                         */
                        pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", rutaescribir, evaluacion1junio.Evaluacion1Junio.rutarelativa + "\\" + "grafica.dot");
                        pbuilder.redirectErrorStream(true);
                        //Ejecuta el proceso
                        pbuilder.start();

                    } catch (Exception exeception) {
                        exeception.printStackTrace();
                    }
                }

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
        int lineadebug = evaluacion1junio.Evaluacion1Junio.LineaDebug;
        if (lineadebug != 0) {
            if (lineadebug == linea) {
                String datos = "TABLA DE SIMBOLOS ACTUAL, (ID,VAL) \n";
                for (Simbolo s : e.tabla.values()) {
                    datos += s.getId() + "," + s.getValor().toString() + "\n";
                }
                JOptionPane.showMessageDialog(null, datos);
            }
        }
        return 0;
    }
}
