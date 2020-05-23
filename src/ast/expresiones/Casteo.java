/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Casteo implements Exp {
    Tipo tipodestino;
    Exp expresion;
    int linea;


    public Casteo(Tipo tipodestino, Exp expresion,int linea) {
        this.tipodestino = tipodestino;
        this.expresion = expresion;
        this.linea = linea;
    }

    @Override
    public Object getValor(Entorno e) {
        debugger(e);
        try{
        if(tipodestino.tr!=null){
            
        }else{
            switch(tipodestino.tp){
                case INT:{
                    Object val = expresion.getValor(e).toString();
                    if(expresion.getTipo(e).esDouble()){
                        double valor= Double.valueOf(expresion.getValor(e).toString());
                        return (int) valor;
                    }else if(expresion.getTipo(e).esChar()){
                        char valor = expresion.getValor(e).toString().charAt(0);
                        return (int) valor;
                    }else if(expresion.getTipo(e).esInt()){
                        return Integer.valueOf(expresion.getValor(e).toString());
                    }
                }
                case DOUBLE:{
                    if(expresion.getTipo(e).esDouble()){
                        double valor= Double.valueOf(expresion.getValor(e).toString());
                        return  valor;
                    }else if(expresion.getTipo(e).esChar()){
                        char valor = expresion.getValor(e).toString().charAt(0);
                        return (double) valor;
                    }else if(expresion.getTipo(e).esInt()){
                        return (double) Integer.valueOf(expresion.getValor(e).toString());
                    }
                }
                case CHAR:{
                    if(expresion.getTipo(e).esDouble()){
                        double valor= Double.valueOf(expresion.getValor(e).toString());
                        return  (char) valor;
                    }else if(expresion.getTipo(e).esChar()){
                        char valor = expresion.getValor(e).toString().charAt(0);
                        return valor;
                    }else if(expresion.getTipo(e).esInt()){
                        
                       
                        
                        int valor= Integer.valueOf(expresion.getValor(e).toString());
                        return (char) valor;
                    }
                }case BOOLEAN:{
                    System.out.println("ERROR SEMANTICO NO SE PUEDEN CASTEAR EXPLICITAMENTE TIPOS BOOLEANOS, Linea: " + getLinea());
                }
            }
        }
        }catch(Exception ex){
            System.out.println("ERROR SEMATICO, ERROR DE TIPOS AL CASTEAR, Linea: "+ getLinea());
        }
        return null;
    }

    @Override
    public Tipo getTipo(Entorno e) {
        return tipodestino;
    }

    @Override
    public int getLinea() {
        return this.linea;
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
