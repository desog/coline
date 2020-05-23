/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluacion1junio;

import Analizador.Lexico;
import Analizador.parser;
import TablaSimbolos.Simbolo;
import ast.Ast;
import ast.Nodo;
import ast.entorno.Entorno;
import ast.instrucciones.Bloque;
import ast.instrucciones.Constructor;
import ast.instrucciones.Metodo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Evaluacion1Junio {
    public static LinkedList<String> errorsemantico = new LinkedList<String>();
    public static String rutarelativa =""; 
    public static int LineaDebug;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Ide ide = new Ide();
        ide.show();
        //ejecutar("C:\\Users\\Diego\\Documents\\NetBeansProjects\\Evaluacion1Junio\\entrada.txt  ");
       
    }
    /**
     * 
     * @param path
     * @param linea 
     */
    public static void ejecutardebug (String path, int linea){
        LineaDebug=linea;
        rutarelativa=path.substring(0, path.lastIndexOf("\\"));
       parser pars;
       Ast b = null; 
       //Arbol AST=null;
       try {
            pars=new parser(new Lexico(new FileInputStream(path)));
            pars.parse();
      
            b=parser.padre;
            
            //AST=pars.getAST();
        } catch (Exception ex) {
            hacerReporte(rutarelativa);
            for(String s : parser.ListaErroresSintacticos){
                System.out.println(s);
            }
            //System.out.println(ex);
            System.err.println("Error fatal en compilación de entrada.");
        } 
       if(b==null){
            System.err.println("ERRORES LEXICOS O SINTACTICOS");
            return;
        } else {
           
           Entorno ent = (Entorno)b.ejecutar();
           if(errorsemantico.size()!=0){
               JOptionPane.showMessageDialog(null, "¡ERRORES SEMANTICOS!");
               hacerReporte(rutarelativa);
           }
           
       }
    }
    
    public static void ejecutar(String path){
        rutarelativa=path.substring(0, path.lastIndexOf("\\"));
       parser pars;
       Ast b = null; 
       //Arbol AST=null;
       try {
            pars=new parser(new Lexico(new FileInputStream(path)));
            pars.parse();
      
            b=parser.padre;
            
            //AST=pars.getAST();
        } catch (Exception ex) {
            hacerReporte(rutarelativa);
            for(String s : parser.ListaErroresSintacticos){
                System.out.println(s);
            }
            //System.out.println(ex);
            System.err.println("Error fatal en compilación de entrada.");
        } 
       if(b==null){
            System.err.println("ERRORES LEXICOS O SINTACTICOS");
            return;
        } else {
           
           Entorno ent = (Entorno)b.ejecutar();
           if(errorsemantico.size()!=0){
               JOptionPane.showMessageDialog(null, "¡ERRORES SEMANTICOS!");
               hacerReporte(rutarelativa);
           }
           
       }
   }
    
    
    
     public static String  hacerReporte(String rutaCHTML){
        
        
        String data= "<H1> REPORTE ERRORES </H1> ";
          data = data + "</br>";
          
          data = data + " " + "<table border='1'style='margin: 0 auto;'>";
          data = data + "<tr>"+"<td>" + "Archivo" + "</td>"+"<td>" + "DESCRIPCION" + "</td>"+"<td>" + "TIPO DE ERROR" + "</td>"+ "</td>";
          data = data + "</tr>";
           int index = rutaCHTML.lastIndexOf("\\");
           String rutaHTML =rutaCHTML.substring(index, rutaCHTML.length());
          
          //------------------------------ERROR SINTÁCTICO HTML------------------------
           for(String s : parser.ListaErroresSintacticos){
              data = data +  "<tr>"+"<td>" + rutaHTML+ "</td>";
              data = data +  "<td>" + s+ "</td>";
              data = data +  "<td>" + "SINTACTICO"+ "</td>";
              data = data + "</tr>";
          }
          //-------------------------------------ERROR LEXICO--------------------------------------
         /* for(String s: Lexico.ErrorLexico){
                String[] componentes = s.split(",");
                
                data = data +  "<tr>"+"<td>" + rutaHTML+ "</td>";
                data = data +  "<td>" + componentes[1] + "</td>";
                data = data +  "<td>" + componentes[2] + "</td>";
                data = data +  "<td>" + "ERROR LEXICO" + "</td>";
                data = data +  "<td>" + "TOKEN QUE PROVOCO EL " + componentes[0] + "</td>";
          }*/
          //------------------------------ERROR SEMANTICO---------------------------
          int contador = 0;
          for(String s : errorsemantico){
              contador ++;
              String[] componentes = s.split(",");
              data = data +  "<tr>"+"<td>" + rutaHTML+ "</td>";
              data = data +  "<td>" + s + "</td>";
              data = data +  "<td>" + "ERROR SEMANTICO"+ "</td>";
              data = data +  "<td>" + "</td>";
          }
          
        
          
          contador = 0;
         /* for(String s : AgregarElementos.ListErrorSemantico){
              contador ++;
              String[] componentes = s.split(",");
              data = data +  "<tr>"+"<td>" + rutaHTML+ "</td>";
              data = data +  "<td>" + contador + "</td>";
              data = data +  "<td>" + contador+6 + "</td>";
              data = data +  "<td>" + "ERROR SEMANTICO"+ "</td>";
              data = data +  "<td>" + componentes[1]+ "</td>";
          }*/
          
          
          
          
          data = data + "</table>";
         
          
          errorsemantico.clear();
          errorsemantico.clear();
          FileOutputStream archivo;
          PrintStream p;
          try{
              archivo= new FileOutputStream("C:\\Users\\Diego\\Documents\\NetBeansProjects\\Evaluacion1Junio\\pagina.html");
              String ruta = "\\pagina.html";
              p = new PrintStream(archivo);
              p.println(data);
              p.close();
              //Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"+" "+ruta);
          }catch(FileNotFoundException e){
              System.out.println("ERROR");
          } catch (IOException ex) {
         Logger.getLogger(Ide.class.getName()).log(Level.SEVERE, null, ex);
     }
         return data;
    }
}
