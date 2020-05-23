/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this auxlate file, choose Tools | Templates
 * and open the auxlate in the editor.
 */
package ast.expresiones;

import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class OpPrefijoSuma implements Exp{
    Identificador id;
    boolean sumar;
    int linea;
    Tipo tipo;
    public OpPrefijoSuma(Identificador id, boolean sumar, int linea) {
        this.id = id;
        this.sumar = sumar;
        this.linea = linea;
    }
    
    @Override
    public Object getValor(Entorno e) {
        debugger(e);
        Simbolo s = e.get(id.val);
        Object o = id.getValor(e);
        Tipo tipo = id.getTipo(e);
        if(sumar==true){
        switch (tipo.tp){
            case INT:{
                int aux = Integer.valueOf(o.toString());
                
                ++aux;
                s.setValor(aux);
                
                return aux;   
            }case DOUBLE:{
                double aux = Double.valueOf(o.toString());
              
                aux++;
                s.setValor(aux);
                
                return aux;
            }case CHAR:{
                char aux = (char)o.toString().charAt(0);
               
                aux++;
                s.setValor(aux);
                
                return aux;
            } default:{
                System.out.println("ERROR SEMANTICO, Linea: " + getLinea());
            }
        }
        }else{
            switch (tipo.tp){
            case INT:{
                int aux = Integer.valueOf(o.toString());
              
                aux--;
                s.setValor(aux);
                return aux;
            }case DOUBLE:{
                double aux = Double.valueOf(o.toString());
           
                aux--;
                s.setValor(aux);
                
                return aux;
            }case CHAR:{
                char aux = (char)o.toString().charAt(0);
          
                aux--;
                s.setValor(aux);
                
                return aux;
            } default:{
                System.out.println("ERROR SEMANTICO, Linea: " + getLinea());
            }
        }
        }
        return null;
    }

    @Override
    public Tipo getTipo(Entorno e) {
        return id.getTipo(e);
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
