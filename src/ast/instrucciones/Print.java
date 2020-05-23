/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import TablaSimbolos.Simbolo;
import ast.entorno.Entorno;
import ast.expresiones.Exp;
import ast.expresiones.Literal;
import ast.expresiones.Tipo;
import evaluacion1junio.Ide;
import javax.swing.JOptionPane;
/**
 *
 * @author Diego
 */
public class Print implements Ins {
    
    
    Exp Imprimir;
    boolean conlinea;
    int linea;

    public Print(Exp Imprimir, boolean conlinea, int linea) {
        this.Imprimir = Imprimir;
        this.conlinea = conlinea;
        this.linea = linea;
    }

    @Override
    public Object ejecutar(Entorno e) {
        debugger(e);
        Imprimir = ConvertirCaracteresEsp(Imprimir, e);
       
        if (conlinea == true) {
            if (Imprimir != null) {
                if (Imprimir.getTipo(e).esDouble()) {
                    evaluacion1junio.Ide.template.jTextArea1.append(String.valueOf(Imprimir.getValor(e).toString())+"\n"); //REVISAR COMO PONER LA NOTACION;
                    //System.out.printf("%e %n", Double.valueOf(Imprimir.getValor(e).toString()));
                } else {
                    evaluacion1junio.Ide.template.jTextArea1.append(String.valueOf(Imprimir.getValor(e).toString())+"\n");
                   // System.out.println(Imprimir.getValor(e)); //AQUI PONER A LA CADENA UN SALTO DE LINEA
                }
            } else {
                evaluacion1junio.Ide.template.jTextArea1.append("NULO"+"\n");
                //System.out.println("NULO");//AQUI PONER A LA CADENA UN SALTO DE LINEA
            }
        } else {
            if (Imprimir != null) {
                    evaluacion1junio.Ide.template.jTextArea1.append(String.valueOf(Imprimir.getValor(e).toString()));
                    //System.out.print(Imprimir.getValor(e));
                    
            } else {
                evaluacion1junio.Ide.template.jTextArea1.append("NULO");
                //System.out.print("NULO");
            }
        }

        return null;
    }

    @Override
    public int getLinea() {
        return linea;
    }
    
    public Exp ConvertirCaracteresEsp(Exp ex, Entorno e){
        if(ex.getTipo(e).esString()){
            String cad = ex.getValor(e).toString();
            if(cad.contains("\\n")){
                cad =cad.replace("\\n", "\n");
                return new Literal(cad, new Tipo(Tipo.Tipos.STRING), linea);
            }else if(cad.contains("\\'")){
                cad =cad.replace("\\'", "\'");
                return new Literal(cad, new Tipo(Tipo.Tipos.STRING), linea);
            }else if(cad.contains("\"")){
                
            }else if(cad.contains("\\\\")){
                cad =cad.replace("\\\\", "\\");
                return new Literal(cad, new Tipo(Tipo.Tipos.STRING), linea);
            }else if(cad.contains("\\0")){
                cad =cad.replace("\\0", "\0");
                return new Literal(cad, new Tipo(Tipo.Tipos.STRING), linea);
            }
            else if(cad.contains("\\b")){
                cad =cad.replace("\\b", "\b");
                return new Literal(cad, new Tipo(Tipo.Tipos.STRING), linea);
            }else if(cad.contains("\\t")){
                cad =cad.replace("\\t", "\t");
                return new Literal(cad, new Tipo(Tipo.Tipos.STRING), linea);
            }else if(cad.contains("\\r")){
                cad =cad.replace("\\r", "\r");
                return new Literal(cad, new Tipo(Tipo.Tipos.STRING), linea);
            }
        }
        return ex;
    }

    @Override
    public int debugger(Entorno e) {
        int lineadebug = evaluacion1junio.Evaluacion1Junio.LineaDebug;
        if(lineadebug!=0){
            if(lineadebug == linea){
                String datos= "TABLA DE SIMBOLOS ACTUAL, (ID,VAL) \n";
                for(Simbolo s : e.tabla.values()){
                    datos+= s.getId()+ "," + s.getValor().toString()+"\n";
                }
                JOptionPane.showMessageDialog(null, datos);
            }
        }
        return 0;
    }

}
