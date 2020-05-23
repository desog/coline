/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.entorno;

import TablaSimbolos.Simbolo;
import java.util.HashMap;

/**
 *
 * @author Diego
 */
public class Entorno {

    

    
   



    public HashMap<String, Simbolo> getTabla() {
        return tabla;
    }

    public void setTabla(HashMap<String, Simbolo> tabla) {
        this.tabla = tabla;
    }
    
    public Entorno getPadre() {
        return Padre;
    }

    public Entorno(Entorno Padre) {
        this.Padre = Padre;
        tabla = new HashMap<>();
    }
    
  
    public Entorno getGlobal(){
        Entorno ultimo = null;
        for(Entorno ent = this; ent!=null ; ent=ent.getPadre()){
            ultimo = ent;
        }
        return ultimo;
    }
    
    
    public Entorno getGlobalClase(){
        Entorno Final = null;
        for(Entorno ent = this; ent!=null ; ent=ent.getPadre()){
            if(ent.getNombrePadre()!=null){
            Final = ent;
            }
        }
        return Final;
    }
  
    
    Entorno Padre;
    public HashMap<String, Simbolo> tabla;
    String nombrePadre;

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }
    
   
    
    public Simbolo get(String id){
        for(Entorno e = this; e!=null; e=e.getPadre()){
            Simbolo encontrado = e.tabla.get(id);
            if(encontrado != null){
                return encontrado;
            }
          
        }
        return null;
    }
    
    public void agregar(String a, Simbolo simbolo){
        tabla.put(a, simbolo);
    }

    public void setPadre(Entorno Padre) {
        this.Padre = Padre;
    }
    

    
    

}
