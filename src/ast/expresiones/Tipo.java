/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import TablaSimbolos.Simbolo;

/**
 *
 * @author Diego
 */
public class Tipo {

    public enum Tipos{
        INT,CHAR,STRING,BOOLEAN,DOUBLE,VOID
    }
    
    public boolean esVoid(){
        return tp==Tipos.VOID;
    }
    
    public Tipos getTp() {
        return tp;
    }

    public void setTp(Tipos tp) {
        this.tp = tp;
    }

    public String getTr() {
        return tr;
    }

    public void setTr(String tr) {
        this.tr = tr;
    }
  public Tipos tp;
  public String tr;

    public Tipo(Tipos tp) {
        this.tp = tp;
    }

    public Tipo(String tr) {
        this.tr = tr;
    }
  
    public boolean esNumerico(){
        return tp==Tipos.CHAR || tp== Tipos.INT || tp==Tipos.DOUBLE;
    }
    
    public boolean esbooleano(){
        return tp==Tipos.BOOLEAN;
    }
    
    public boolean esChar(){
        return tp==Tipos.CHAR;
    }
    
    public boolean esString(){
        return tp==Tipos.STRING;
    }
    
    public boolean esDouble(){
        return tp==Tipos.DOUBLE;
    }
    
    public boolean esInt(){
        return tp==Tipos.INT;
    }
    
    public boolean esObjeto(){
        return tr !=null;
    }

    
    
  
}
