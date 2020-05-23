/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.operaciones;

import TablaSimbolos.Simbolo;
import ast.Nodo;
import ast.entorno.Entorno;
import ast.expresiones.Exp;
import ast.expresiones.Tipo;

/**
 *
 * @author Diego
 */
public interface Operacion extends Nodo {
    public enum Operador{
        MAS,MENOS,POR,DIV,POTENCIA,MENOR,MENORIGUAL,MAYOR,MAYORIGUAL,IGUALACION,DIFERENTE,
        AND,OR,NOT,XOR,MENOSU,MOD,AUMENTO,DECREMENTO;
    }
    
    
    
    public Tipo getTipoDominante(Entorno e);
    
 
}
