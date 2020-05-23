/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas.ArbolArreglos;

/**
 *
 * @author Diego
 */
public class Arreglo {
    int dimensiones;
    String id;

    public Arreglo(int dimensiones, String id) {
        this.dimensiones = dimensiones;
        this.id = id;
    }

    public int getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(int dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
}
