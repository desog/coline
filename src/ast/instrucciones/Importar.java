/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.entorno.Entorno;

/**
 *
 * @author Diego
 */
public class Importar implements Ins {

    String ruta;

    public Importar(String ruta) {
        this.ruta = ruta;
    }
    
    
    
    @Override
    public Object ejecutar(Entorno e) {
        ruta = ruta.replace("\"", "");
        String rutarel =evaluacion1junio.Evaluacion1Junio.rutarelativa;
        ruta = rutarel+"\\"+ruta;
        evaluacion1junio.Evaluacion1Junio.ejecutar(ruta);
        return null;  
    }

    @Override
    public int getLinea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int debugger(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
