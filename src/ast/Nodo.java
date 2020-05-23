/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import ast.entorno.Entorno;

/**
 *
 * @author Diego
 */
public interface Nodo {
    int getLinea();
    int debugger(Entorno e);
}
