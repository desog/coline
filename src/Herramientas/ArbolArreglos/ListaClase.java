/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas.ArbolArreglos;

import ast.entorno.Entorno;

/**
 *
 * @author Diego
 */
public class ListaClase {
    String id;
    Entorno entorno;

    public ListaClase(String id, Entorno entorno) {
        this.id = id;
        this.entorno = entorno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Entorno getEntorno() {
        return entorno;
    }

    public void setEntorno(Entorno entorno) {
        this.entorno = entorno;
    }
    
   
    
}
