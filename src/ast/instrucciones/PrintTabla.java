/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.LinkedList;

/**
 *
 * @author Diego
 */
public class PrintTabla implements Ins {

    @Override
    public Object ejecutar(Entorno e) {
        LinkedList<Integer> listaid = new LinkedList<Integer>();
        String dot = "digraph L{ \n node [shape=record fontname=Arial]; \n";
        String enlace = "";
        for (Entorno es = e; es != null; es = es.getPadre()) {
            int numero = generarnumero();
            listaid.add(numero);
            dot += numero + " [label=\"";
            //System.out.println("NUEVO AMBITO");
            for (String k : es.tabla.keySet()) {
                String f = "";
                f = k;
                if (es.get(k).getValor() != null) {
                    String r = es.get(k).getValor().toString();
                    dot += k + " Valor: " + r + "\\l";
                    //System.out.println(k + " VALOR: " + r);
                } else {
                    dot += k + " \\l";
                   // System.out.println(k);
                }

            }
            dot += "\"]\n";
        }

        for (int i = listaid.size() - 1; i >= 0; i--) {
            if (i == 0) {
                dot += listaid.get(i) + "\n";
            } else {
                dot += listaid.get(i) + "->";
            }
        }
        dot += "}";
      
        dot = dot.replace("\\n", "\n");
        dot = dot.replace("\\\"", "\"");
        dot = dot.replace("\\\'", "\'");
        dot = dot.replace("|", "_");
        System.out.println(dot);
        BufferedWriter bw = null;
        try {

            File archivo = new File(evaluacion1junio.Evaluacion1Junio.rutarelativa + "\\" + "tabla.dot");
                if(!archivo.exists()){
                    archivo.createNewFile();
                }
                Writer writer = new FileWriter(archivo);
             bw= new BufferedWriter(writer);
                  
                   

        } catch (Exception ex) {
            evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, EL ARCHIVO NO SE HA PODIDO ESCRIBIR, Linea: ");
        }finally{
            try{
                if(bw!=null){
                    bw.close();
                }
            }catch(Exception es){
                
            }
        }
        try {
            ProcessBuilder pbuilder;

            /*
             * Realiza la construccion del comando    
             * en la linea de comandos esto es: 
             * dot -Tpng -o archivo.png archivo.dot
             */
            pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", evaluacion1junio.Evaluacion1Junio.rutarelativa + "\\tabla.dot", evaluacion1junio.Evaluacion1Junio.rutarelativa + "\\" + "grafica.dot");
            pbuilder.redirectErrorStream(true);
            //Ejecuta el proceso
            pbuilder.start();

        } catch (Exception exeception) {
            exeception.printStackTrace();
        }

        return null;
    }

    public int generarnumero() {
        int numero = (int) (Math.random() * 300) + 1;
        return numero;
    }

    @Override
    public int getLinea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int debugger(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
