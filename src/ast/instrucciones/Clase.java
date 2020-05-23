/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import Analizador.Lexico;
import Analizador.parser;
import Herramientas.ArbolArreglos.ListaClase;
import TablaSimbolos.Simbolo;
import ast.Ast;
import ast.Nodo;
import ast.entorno.Entorno;
import ast.expresiones.Exp;
import ast.expresiones.Tipo;
import static evaluacion1junio.Evaluacion1Junio.errorsemantico;
import static evaluacion1junio.Evaluacion1Junio.hacerReporte;
import static evaluacion1junio.Evaluacion1Junio.rutarelativa;
import java.io.FileInputStream;
import java.util.LinkedList;
import static java_cup.emit.parser;
import javax.swing.JOptionPane;
/**
 *
 * @author Diego
 */
public class Clase extends Simbolo implements Ins {
    LinkedList<Modificador> modificador;
    String identificadorExtender;
    LinkedList<Nodo> ContenidoClase;
    int linea;
    
    public Clase(String id,LinkedList<Modificador> modificador,String identificadorextender,LinkedList<Nodo> contenido,int linea) {
        super(id);
        this.modificador = modificador;
        this.identificadorExtender = identificadorextender;
        this.ContenidoClase = contenido;
        this.linea = linea;
    }
    
    
    @Override
    public Object ejecutar(Entorno e) {
        debugger(e);
        if(identificadorExtender!=null){
            if(identificadorExtender!=""){
                String rutaextender = evaluacion1junio.Evaluacion1Junio.rutarelativa+"\\"+identificadorExtender+".coline";
                Entorno PadreHeredar = (Entorno) parsear(rutaextender);
                if(PadreHeredar!=null){
                    e.setPadre(PadreHeredar);
                    e.setNombrePadre(identificadorExtender);
                }
            }
        }
        LinkedList<Modificador> NuevaListMod = verificarDuplicados();
            
            for (Nodo nodo : ContenidoClase) {
            if(nodo instanceof Constructor){
                Constructor cons = (Constructor) nodo;
                e.agregar(cons.getId()+"|constructor", cons);
               
            }else if(nodo instanceof Metodo) {
                Metodo metodo = (Metodo) nodo;
                String cadenaMetodo = "";
                for(Parametro p : metodo.Parametros){
                    if(p.tipo.tr!=null){
                        cadenaMetodo+="_"+p.tipo.tr;
                    }else{
                        cadenaMetodo+="_"+p.tipo.tp.name();
                    }
                    
                }
                e.agregar(metodo.getId()+"|metodo"+cadenaMetodo, metodo);
            }else if(nodo instanceof DeclaracionGlobal){
                
                Object result = ((Ins)nodo).ejecutar(e);
                if(result!=null){
                    return result;
                }
            }else if(nodo instanceof DeclaArreglo){
                    Object result = ((Ins)nodo).ejecutar(e);
                }
        }
            ListaClase clases = new ListaClase(this.getId(), e);
            return clases;
        
    }

    @Override
    public int getLinea() {
        return linea;
    }
    
    public LinkedList verificarDuplicados (){
        LinkedList<Modificador.visibilidad> nuevaLista = new LinkedList<Modificador.visibilidad>();
        for(Modificador m : modificador){
            if(!nuevaLista.contains(m.getV())){
                nuevaLista.add(m.getV());
            }else{
                evaluacion1junio.Evaluacion1Junio.errorsemantico.add("ERROR SEMANTICO, NO SE PUEDEN TENER DOS MODIFICADORES ("+ m.getV().toString() +") DE CLASE DEL MISMO TIPO, Linea: " + linea);
                return null;
            }
        }
        return modificador;
    }
    
    
    /**
     * EJECUCION SI HEREDA
     * @param path 
     */
     public static Entorno parsear(String path){
        
       parser pars;
       Ast b = null; 
       //Arbol AST=null;
       try {
            pars=new parser(new Lexico(new FileInputStream(path)));
            pars.parse();
      
            b=parser.padre;
            
            //AST=pars.getAST();
        } catch (Exception ex) {
            hacerReporte(evaluacion1junio.Evaluacion1Junio.rutarelativa);
            for(String s : parser.ListaErroresSintacticos){
                System.out.println(s);
            }
            //System.out.println(ex);
            System.err.println("Error fatal en compilación de entrada.");
        } 
       if(b==null){
            System.err.println("ERRORES LEXICOS O SINTACTICOS");
            
        } else {
           
           Entorno ent = (Entorno)b.ejecutar();
           if(errorsemantico.size()!=0){
               JOptionPane.showMessageDialog(null, "¡ERRORES SEMANTICOS!");
               hacerReporte(rutarelativa);
           }
           return ent;
       }
       return null;
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
