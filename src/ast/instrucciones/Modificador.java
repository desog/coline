/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

/**
 *
 * @author Diego
 */
public class Modificador {
    public enum Modificadores{
        ABSTRACT,STATIC,FINAL;
    }
     visibilidad v ;
      Modificadores m;
     
    public enum visibilidad{
        PUBLIC,PROTECTED,PRIVATE;
    }

    public Modificador(visibilidad v) {
        this.v = v;
    }

    public Modificador(Modificadores m) {
        this.m = m;
    }

    public visibilidad getV() {
        return v;
    }

    public void setV(visibilidad v) {
        this.v = v;
    }

    public Modificadores getM() {
        return m;
    }

    public void setM(Modificadores m) {
        this.m = m;
    }
    
 
   
   

    
    
 
    
    
    
    
}
