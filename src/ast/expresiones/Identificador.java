/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;

/**
 *
 * @author Diego
 */
public class Identificador implements Exp {
    String val; //STRING DEL ID
    int linea;
    
    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

 
    
    
    public Identificador(String val,int linea) {
        this.val = val;
        this.linea = linea;
    }

    @Override
    public Object getValor(Entorno e) {
        if(e.get(val)!=null){
            return e.get(val).getValor();
        }else if(val.contains("|variable")){
        val = val.replace("|variable", "|arreglo");
                if(e.get(val)!=null){
                    return e.get(val).getValor();
                }else{
                    System.out.println("ERROR SEMANTICO, NO EXISTE EL ID, Linea: " + getLinea());
                }
        
        }else{
            System.out.println("ERROR SEMANTICO, NO EXISTE EL ID, Linea: " + getLinea());
        }
        return null;
    }

    @Override
    public Tipo getTipo(Entorno e) {
        Object o = e.get(val);
        if(e.get(val)!=null){
            
            return e.get(val).getTipo();
        }else if(val.contains("|variable")){
                val = val.replace("|variable", "|arreglo");
                if(e.get(val)!=null){
                    return e.get(val).getTipo();
                }else{
                    System.out.println("ERROR SEMANTICO, NO EXISTE EL ID, Linea: " + getLinea());
                }
            }else{
            System.out.println("ERROR SEMANTICO, NO EXISTE EL ID, Linea: " + getLinea());
            }
        
        return null;
    }

    @Override
    public int getLinea() {
        return this.linea;
    }

    @Override
    public int debugger(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
