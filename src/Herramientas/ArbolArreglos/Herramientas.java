/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas.ArbolArreglos;

import ast.expresiones.Literal;
import ast.expresiones.Tipo;

/**
 *
 * @author Diego
 */
public class Herramientas {
    
    public Object getValorDefecto(Tipo t){
        switch(t.tp){
            case INT:{
                return 0;
            }case DOUBLE:{
                return 0.0;
            }case CHAR:{
                return '\0';
            }case BOOLEAN:{
                return false;
            }
        }
        return null;
    }
    
    
}
