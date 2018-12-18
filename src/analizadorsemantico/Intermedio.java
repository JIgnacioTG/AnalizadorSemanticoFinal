/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorsemantico;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Misael
 */
public class Intermedio {
    
    private static final String LINEA = System.getProperty("line.separator"); //Variable que genera los saltos de línea detectando el sistema del usuario.
    
    public static Codigo generarIntermedio (Codigo codigo) {
        
        // Indica el numero de instruccion.
        int numeroinstruccion = 1;
        
        // Numero de temporal
        int numerotemporal = 1;
        int numeroCon = 1;  //Número de condicion para la variable temporal.
        
        // Variables que almacenaran la tripleta
        ArrayList<Integer> numeroIns = new ArrayList<>();
        ArrayList<String> objeto = new ArrayList<>();
        ArrayList<String> fuente = new ArrayList<>();
        ArrayList<String> operador = new ArrayList<>();
        Queue<Integer> posFinalSwitch = new LinkedList<>();
        Stack<String> variableCon = new Stack<>();
        
        // se verifican los tokens uno por uno.
        for (int i = 0; i < codigo.token.size(); i++) {
            
            // cuando es una palabra reservada
            if(codigo.token.get(i).contains("PR")) {
                
                // si es un "switch".
                if (codigo.valorToken.get(i).equalsIgnoreCase("switch")) {
                    
                    
                    // Se guarda en la tripleta la variable a comprobar
                    numeroIns.add(numeroinstruccion);
                    objeto.add("T" +numerotemporal);
                    fuente.add(codigo.valorToken.get(i+2));
                    operador.add("=");
                    variableCon.push("T" +numerotemporal);  //Se guarda la variable temporal del switch.
                    numeroinstruccion++;
                    numerotemporal++;
                    
                    continue;
                }
                
                else if (codigo.valorToken.get(i).equalsIgnoreCase("case")) {
                    
                    if (variableCon.size() > 0) {    //Si hay al menos una condicional Switch activa.
                        while (operador.indexOf("Reemplazar"+variableCon.size()) != -1) {    //Se va realizar de manera cíclica el reemplazo de número de instrucción mientras exista.
                            operador.set(operador.indexOf("Reemplazar"+variableCon.size()), numeroinstruccion+""); //Se reemplaza y se indica donde empieza la siguiente instrucción.
                        }
                    }
                    
                    // Se analiza con que se debe comparar
                    numeroIns.add(numeroinstruccion);
                    objeto.add("T" +numerotemporal);
                    fuente.add(codigo.valorToken.get(i+1));
                    operador.add("=");
                    numeroinstruccion++;
                    
                    // Se analiza con que se debe comparar
                    numeroIns.add(numeroinstruccion);
                    objeto.add(variableCon.lastElement());
                    fuente.add("T" +numerotemporal);
                    operador.add("==");
                    numeroinstruccion++;
                    
                    // Si es verdadero, debe continuar con la siguiente instruccion.
                    int sigIns = numeroinstruccion + 2;
                    numeroIns.add(numeroinstruccion);
                    objeto.add("TR" +numeroCon);
                    fuente.add("TRUE");
                    operador.add(sigIns+"");
                    numeroinstruccion++;
                    
                    // Si es falso, se debe regresar al anterior do
                    numeroIns.add(numeroinstruccion);
                    objeto.add("TR" +numeroCon);
                    fuente.add("FALSE");
                    operador.add("Reemplazar"+variableCon.size());
                    
                    numeroinstruccion++;
                    numerotemporal++;
                    numeroCon++;
                    
                    continue;
                    
                }
                
                else if (codigo.valorToken.get(i).equalsIgnoreCase("default")) {
                    if (variableCon.size() > 0) {    //Si hay al menos una condicional Switch activa.
                        while (operador.indexOf("Reemplazar"+variableCon.size()) != -1) {    //Se va realizar de manera cíclica el reemplazo de número de instrucción mientras exista.
                            operador.set(operador.indexOf("Reemplazar"+variableCon.size()), numeroinstruccion+""); //Se reemplaza y se indica donde empieza la siguiente instrucción.
                        }
                    }
                }
                
                else if (codigo.valorToken.get(i).equalsIgnoreCase("break")) {
                    numeroIns.add(numeroinstruccion);
                    objeto.add("JUMP");
                    fuente.add("");
                    operador.add("break"+variableCon.size());
                    numeroinstruccion++;
                }
                
                else {
                    continue;
                }
                
            }
            
            if (codigo.token.get(i).equalsIgnoreCase("COR2")) { //Si estamos ante el final de una instrucción.
                if (variableCon.size() > 0) {    //Si hay al menos una condicional Switch activa.
                    int sigIns = numeroinstruccion; //Variable auxiliar para saber el número de la siguiente instrucción.
                    while (operador.indexOf("Reemplazar"+variableCon.size()) != -1) {    //Se va realizar de manera cíclica el reemplazo de número de instrucción mientras exista.
                        operador.set(operador.indexOf("Reemplazar"+variableCon.size()), sigIns+""); //Se reemplaza y se indica donde empieza la siguiente instrucción.
                    }
                    while (operador.indexOf("break"+variableCon.size()) != -1) {    //Se va realizar de manera cíclica el reemplazo de número de instrucción mientras exista.
                        operador.set(operador.indexOf("break"+variableCon.size()), sigIns+""); //Se reemplaza y se indica donde empieza la siguiente instrucción.
                        if (!posFinalSwitch.contains(sigIns)) {
                            posFinalSwitch.offer(sigIns);
                        }
                        
                    }
                    variableCon.pop();  //Se elimina la variable de comprobación Switch.
                }
            }
            
            // Si el token es un operador de asignación.
            if(codigo.token.get(i).equalsIgnoreCase("OAS")) {
                
                // Se considera si hay un delimitador despúes de su siguiente token
                if (codigo.token.get(i+2).equalsIgnoreCase("DEL")) {
                    
                    // De ser así estamos ante una asignación simple y la tripleta.
                    numeroIns.add(numeroinstruccion);
                    objeto.add(codigo.valorToken.get(i-1));
                    fuente.add(codigo.valorToken.get(i+1));
                    operador.add("=");
                    numeroinstruccion++;
                }
                
                // En este caso, tenemos una probable operación matemática.
                else {
                    
                    // Variable a la que se asignará el resultado final.
                    String variableAsig = codigo.valorToken.get(i-1);
                    
                    // se almacenan los tokens de la operacion.
                    ArrayList<String> tokensOperacion = new ArrayList<>();
                    
                    // Saltamos el guardado del operador de asignación.
                    i++;
                    
                    while (!codigo.token.get(i).equalsIgnoreCase("DEL")) {
                        tokensOperacion.add(codigo.valorToken.get(i));
                        i++;
                    }
                    
                    // Se va recorriendo la lista hasta que no queden variables por comparar
                    while (tokensOperacion.size() > 1) {
                        
                        // Si hay un parentesis en la operación.
                        if (tokensOperacion.indexOf(")") != -1) {
                            
                            // Se almacenan las posiciones.
                            int posFin = tokensOperacion.indexOf(")");
                            int posIni = posFin - 2;
                            
                            // Se revisa que antes dos tokens anteriores no sean "(".
                            if (!tokensOperacion.get(posIni).equalsIgnoreCase("(")) {
                                
                                // Se busca el inicio de este parentesis
                                for (int n = posFin; n >= 0; n--) {
                                    
                                    // Si se encuentra el parentesis.
                                    if (tokensOperacion.get(n).equalsIgnoreCase("(")) {
                                        // Se guarda la posicion.
                                        posIni = n;
                                        
                                        // Se termina el ciclo.
                                        break;
                                    }
                                }
                                
                                // Se guarda la operacion que se encuentra dentro del parentesis.
                                ArrayList<String> tokensParentesis = new ArrayList<>();
                                
                                // Se recorre el arreglo hasta que encontremos el final del parentesis.
                                int posReemplazo = posIni + 1;
                                while(!tokensOperacion.get(posReemplazo+1).equalsIgnoreCase(")")) {
                                    // Se guarda la operacion
                                    tokensParentesis.add(tokensOperacion.remove(posReemplazo));
                                    // Si es el último
                                    if (tokensOperacion.get(posReemplazo+1).equalsIgnoreCase(")")) {
                                        tokensParentesis.add(tokensOperacion.get(posReemplazo));
                                    }
                                }
                                
                                // Se analiza lo que se encuentra adentro del parentesis.
                                while (tokensParentesis.size() > 1) {
                                    
                                    // Si hay una multiplicación en la operación.
                                    if (tokensParentesis.lastIndexOf("*") != -1) {

                                        // Se almacena la penúltima variable en el triplo y se elimina de la lista.
                                        numeroIns.add(numeroinstruccion);
                                        objeto.add("T" +numerotemporal);
                                        fuente.add(tokensParentesis.remove(tokensParentesis.lastIndexOf("*")-1));
                                        operador.add("=");
                                        numeroinstruccion++;

                                        // En el triplo se almacena la operacion realizada.
                                        numeroIns.add(numeroinstruccion);
                                        objeto.add("T" +numerotemporal);
                                        fuente.add(tokensParentesis.remove(tokensParentesis.lastIndexOf("*")+1));
                                        operador.add(tokensParentesis.get(tokensParentesis.lastIndexOf("*")));
                                        numeroinstruccion++;

                                        // Se agrega a la lista el triplo realizado.
                                        tokensParentesis.set(tokensParentesis.lastIndexOf("*"),"T" +numerotemporal);

                                        // Se aumenta el contador del triplo
                                        numerotemporal++;

                                        continue;
                                    }

                                    // Si hay una division en la operación.
                                    else if (tokensParentesis.lastIndexOf("/") != -1) {

                                        // Se almacena la antepenúltima variable en el triplo y se elimina de la lista.
                                        numeroIns.add(numeroinstruccion);
                                        objeto.add("T" +numerotemporal);
                                        fuente.add(tokensParentesis.remove(tokensParentesis.lastIndexOf("/")-1));
                                        operador.add("=");
                                        numeroinstruccion++;

                                        // En el triplo se almacena la operacion realizada.
                                        numeroIns.add(numeroinstruccion);
                                        objeto.add("T" +numerotemporal);
                                        fuente.add(tokensParentesis.remove(tokensParentesis.lastIndexOf("/")+1));
                                        operador.add(tokensParentesis.get(tokensParentesis.lastIndexOf("/")));
                                        numeroinstruccion++;

                                        // Se agrega a la lista el triplo realizado.
                                        tokensParentesis.set(tokensParentesis.lastIndexOf("/"),"T" +numerotemporal);

                                        // Se aumenta el contador del triplo
                                        numerotemporal++;

                                        continue;
                                    }

                                    // Si solo quedan sumas y restas.
                                    else {

                                        // Se almacena la antepenúltima variable en el triplo y se elimina de la lista.
                                        numeroIns.add(numeroinstruccion);
                                        objeto.add("T" +numerotemporal);
                                        fuente.add(tokensParentesis.remove(tokensParentesis.size()-3));
                                        operador.add("=");
                                        numeroinstruccion++;

                                        // En el triplo se almacena la operacion realizada.
                                        numeroIns.add(numeroinstruccion);
                                        objeto.add("T" +numerotemporal);
                                        fuente.add(tokensParentesis.remove(tokensParentesis.size()-1));
                                        operador.add(tokensParentesis.remove(tokensParentesis.size()-1));
                                        numeroinstruccion++;

                                        // Se agrega a la lista el triplo realizado.
                                        tokensParentesis.add("T" +numerotemporal);

                                        // Se aumenta el contador del triplo
                                        numerotemporal++;
                                    }
                                    
                                }
                                
                                // Ahora la variable temporal restante se pasa al tokensOperacion.
                                tokensOperacion.set(posReemplazo, tokensParentesis.get(0));
                                
                                // El ciclo analiza la siguiente variable.
                                continue;
                            }
                            
                            // En este caso, la operación no tiene mas operaciones alrededor
                            else {
                                
                                // Primero debemos comprobar que no estemos en el final del lado derecho.
                                if (posFin < tokensOperacion.size()-1) {
                                    
                                    // Se verifica que haya una suma, resta, division o multiplicacion o parentesis final del lado derecho
                                    if (tokensOperacion.get(posFin+1).equalsIgnoreCase("+") || tokensOperacion.get(posFin+1).equalsIgnoreCase("-") || tokensOperacion.get(posFin+1).equalsIgnoreCase("/") || tokensOperacion.get(posFin+1).equalsIgnoreCase("*") || tokensOperacion.get(posFin+1).equalsIgnoreCase(")")) {
                                        
                                        // De ser así, se elimina el parentesis
                                        tokensOperacion.remove(posFin);
                                    }
                                    
                                    // En cualquier otro caso, se cambia el parentesis por una multiplicación
                                    else {
                                        tokensOperacion.set(posFin, "*");
                                    }
                                        
                                }
                                
                                // Al ser el final, solamente se elimina el parentesis.
                                else {
                                    tokensOperacion.remove(posFin);
                                }
                                
                                // Ahora debemos comprobar que no estemos al principio del lado izquierdo.
                                if (posIni > 0) {
                                    // Se verifica que haya una suma, resta, division, multiplicacion o parentesis del lado izquierdo.
                                    if (tokensOperacion.get(posIni-1).equalsIgnoreCase("+") || tokensOperacion.get(posIni-1).equalsIgnoreCase("-") || tokensOperacion.get(posIni-1).equalsIgnoreCase("/") || tokensOperacion.get(posIni-1).equalsIgnoreCase("*") || tokensOperacion.get(posIni-1).equalsIgnoreCase(")") || tokensOperacion.get(posIni-1).equalsIgnoreCase("(")) {
                                        
                                        // De ser así, se elimina el parentesis
                                        tokensOperacion.remove(posIni);
                                    }
                                    
                                    // En cualquier otro caso, se cambia el parentesis por una multiplicación
                                    else {
                                        tokensOperacion.set(posIni, "*");
                                    }
                                    
                                }
                                
                                // Al ser el principio, solamente se elimina el parentesis.
                                else {
                                    tokensOperacion.remove(posIni);
                                }
                                
                            }
                            
                            continue;
                        }
                        
                        // Si hay una multiplicación en la operación.
                        else if (tokensOperacion.lastIndexOf("*") != -1) {
                            
                            // Se almacena la antepenúltima variable en el triplo y se elimina de la lista.
                            numeroIns.add(numeroinstruccion);
                            objeto.add("T" +numerotemporal);
                            fuente.add(tokensOperacion.remove(tokensOperacion.lastIndexOf("*")-1));
                            operador.add("=");
                            numeroinstruccion++;

                            // En el triplo se almacena la operacion realizada.
                            numeroIns.add(numeroinstruccion);
                            objeto.add("T" +numerotemporal);
                            fuente.add(tokensOperacion.remove(tokensOperacion.lastIndexOf("*")+1));
                            operador.add(tokensOperacion.get(tokensOperacion.lastIndexOf("*")));
                            numeroinstruccion++;

                            // Se agrega a la lista el triplo realizado.
                            tokensOperacion.set(tokensOperacion.lastIndexOf("*"),"T" +numerotemporal);
                            
                            // Se aumenta el contador del triplo
                            numerotemporal++;
                            
                            continue;
                        }
                        
                        // Si hay una division en la operación.
                        else if (tokensOperacion.lastIndexOf("/") != -1) {
                            
                            // Se almacena la antepenúltima variable en el triplo y se elimina de la lista.
                            numeroIns.add(numeroinstruccion);
                            objeto.add("T" +numerotemporal);
                            fuente.add(tokensOperacion.remove(tokensOperacion.lastIndexOf("/")-1));
                            operador.add("=");
                            numeroinstruccion++;

                            // En el triplo se almacena la operacion realizada.
                            numeroIns.add(numeroinstruccion);
                            objeto.add("T" +numerotemporal);
                            fuente.add(tokensOperacion.remove(tokensOperacion.lastIndexOf("/")+1));
                            operador.add(tokensOperacion.get(tokensOperacion.lastIndexOf("/")));
                            numeroinstruccion++;

                            // Se agrega a la lista el triplo realizado.
                            tokensOperacion.set(tokensOperacion.lastIndexOf("/"),"T" +numerotemporal);
                            
                            // Se aumenta el contador del triplo
                            numerotemporal++;
                            
                            continue;
                        }
                        
                        // Si solo quedan sumas y restas.
                        else {
                            
                            // Se almacena la antepenúltima variable en el triplo y se elimina de la lista.
                            numeroIns.add(numeroinstruccion);
                            objeto.add("T" +numerotemporal);
                            fuente.add(tokensOperacion.remove(tokensOperacion.size()-3));
                            operador.add("=");
                            numeroinstruccion++;

                            // En el triplo se almacena la operacion realizada.
                            numeroIns.add(numeroinstruccion);
                            objeto.add("T" +numerotemporal);
                            fuente.add(tokensOperacion.remove(tokensOperacion.size()-1));
                            operador.add(tokensOperacion.remove(tokensOperacion.size()-1));
                            numeroinstruccion++;

                            // Se agrega a la lista el triplo realizado.
                            tokensOperacion.add("T" +numerotemporal);
                            
                            // Se aumenta el contador del triplo
                            numerotemporal++;
                        }
                        
                    }
                    
                    // El resultado final es almacenado en la variable
                    numeroIns.add(numeroinstruccion);
                    objeto.add(variableAsig);
                    fuente.add(tokensOperacion.get(0));
                    operador.add("=");
                    numeroinstruccion++;
                    
                }
            }
        }
        
        // Ahora se genera el texto de la tripleta.
        StringBuilder texto = new StringBuilder("\tDato Origen\tDato Fuente\tOperador");
        
        texto.append(LINEA);
        for (int i = 0; i < numeroIns.size(); i++) {
            texto.append(numeroIns.get(i)).append("\t")
                    .append(objeto.get(i)).append("\t\t")
                    .append(fuente.get(i)).append("\t\t")
                    .append(operador.get(i)).append("\t").append(LINEA);
        }
        texto.append(numeroIns.size()+1).append("\tFIN");
        
        // se guarda la tripleta
        codigo.tripleta = new Tripleta(numeroIns, objeto, fuente, operador, posFinalSwitch);
        
        // Se guarda el archivo.
        Archivo.guardar("Codigo Intermedio.txt", texto.toString());
        
        return codigo;
    }
    
}
