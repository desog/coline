/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.Nodo;
import ast.entorno.Entorno;

/**
 *
 * @author Diego
 */
public interface Exp extends Nodo{
    Object getValor(Entorno e);
    Tipo getTipo(Entorno e);
}
