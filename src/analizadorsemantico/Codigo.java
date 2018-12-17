/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorsemantico;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Misael
 */
public class Codigo {
    
    ArrayList<String> token;
    ArrayList<String> valorToken;
    ArrayList<String> variable;
    ArrayList<String> tipo;
    ArrayList<String> valor;
    ArrayList<String> errores;
    Stack<String> varSwitch;
    Stack<String> tipoSwitch;
    int numSwitch;
    String codigo;
    Tripleta tripleta;
    
    public Codigo (String texto) {
        token = new ArrayList<>();
        valorToken = new ArrayList<>();
        variable = new ArrayList<>();
        tipo = new ArrayList<>();
        valor = new ArrayList<>();
        errores = new ArrayList<>();
        varSwitch = new Stack<>();
        tipoSwitch = new Stack<>();
        tripleta = new Tripleta();
        numSwitch = 0;
        codigo = texto;
    }
}
