/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorsemantico;

/**
 *
 * @author Misael
 */
public class Ensamblador {
    
    private static final String LINEA = System.getProperty("line.separator"); //Variable que genera los saltos de línea detectando el sistema del usuario.
    
    public static Codigo generarObjeto(Codigo codigo) {    //Método que realiza la generación del codigo objeto (Ensamblador).
        String registroCon = "";   //Auxiliar que sirve para almacenar el registro donde se encuentra la variable condicional (Switch).
        String registro = "";   //Auxiliar que sirve para almacenar el registro que se maneja en el momento.
        int numCon = 1; //Variable que almacena el número de condicion.
        int numIns = 1; //Variable que almacena el número de instrucción.
        StringBuilder stb = new StringBuilder();    //Variable que contendrá el código ensamblador
        for (int i = 0; i < codigo.tripleta.numeroIns.size(); i++) {    //Se analiza la tripleta.
            String variable = "";   //Variable auxiliar para el guardado de la variable a trabajar.
            if (codigo.tripleta.operador.get(i).equalsIgnoreCase("==")) {   //Si estamos dentro de una comparación.
                stb.append("Condicion").append(numCon).append(":").append(LINEA);   //Se agrega una etiqueta de condición.
                numCon++;   //Se aumenta el contador de condición.
                variable = codigo.tripleta.objeto.get(i);   //Se guarda la variable a trabajar.
                registroCon = codigo.cpu.devolverRegistro(variable);  //Se busca en que registro se tiene guardado el Dato Objeto.
                variable = codigo.tripleta.fuente.get(i);   //Se guarda la variable a trabajar.
                registro = codigo.cpu.devolverRegistro(variable);  //Se busca en que registro se tiene guardado el Dato Fuente.
                stb.append("\tCMP ").append(registroCon).append(", ").append(registro).append(LINEA)    //Se agrega la línea de comparación.
                        .append("\tJE Instruccion").append(numIns).append(LINEA)    //Se realiza el salto a la siguiente instrucción.
                        .append("\tJMP Condicion").append(numCon).append(LINEA)    //Se realiza el salto a la siguiente comparación.
                        .append(LINEA)
                        .append("Instruccion").append(numIns).append(":").append(LINEA);
                numIns++;
            }
            
            // Se verifica que el operador es una asignación.
            else if (!triplo.objeto.get(i).contains("T") && !triplo.fuente.get(i).contains("T")) {
                
                stb.append("\tMOV "+triplo.objeto.get(i)+", "+triplo.fuente.get(i)+LINEA);
                
            }
            
            // Si solamente la columna 2 no contiene una variable temporal, estamos ante una asignación.
            else if (!triplo.objeto.get(i).contains("T")) {
                
                // Se mueve de AL el resultado a la variable correspondiente.
                stb.append("\tMOV "+triplo.objeto.get(i)+", AL"+LINEA);
                
                // Se limpia la variable para detectar que lo que almacena BH ya no es importante.
                AL = "";
                
            }
            
            // De no ser ningun caso anterior, estamos ante una operación aritmetica.
            else {
                
                // Si AL se encuentra vacía, se procede a realizar la operación.
                if (AL.isEmpty()) {
                    
                    // Se mueve a AL el primer número.
                    stb.append("\tMOV AL, "+triplo.fuente.get(i)+LINEA);
                    AL = triplo.fuente.get(i);
                    
                    // Se mueve al siguiente número.
                    i++;
                    
                    // Si no estamos ante una división, se utiliza el registro AH
                    if (!triplo.operador.get(i).equalsIgnoreCase("/")) {
                        stb.append("\tMOV AH, "+triplo.fuente.get(i)+LINEA);
                        AH = triplo.fuente.get(i);
                    }
                    
                    // Pero si se trata de una división.
                    else {
                        
                        // Se limpia la parte alta de AX si no se encuentra vacía.
                        if (!AH.isEmpty()) {
                            stb.append("\tMOV AH, 0"+LINEA);
                            AH = "";
                        }
                        
                        // Se pasa a CL el divisor.
                        stb.append("\tMOV CL, "+triplo.fuente.get(i)+LINEA);
                        CL = triplo.fuente.get(i);
                        
                        // Se realiza la división y se indica los registros con información.
                        stb.append("\tDIV CL"+LINEA);
                        AH = "Residuo";
                        AL = "Resultado";
                    }
                    
                    // Si se trata de una multiplicación.
                    if (triplo.operador.get(i).equalsIgnoreCase("*")) {
                        stb.append("\tMUL AL, AH"+LINEA);
                        AL = "AL * AH";
                    }
                    
                    // Si se trata de una resta.
                    else if (triplo.operador.get(i).equalsIgnoreCase("-")) {
                        stb.append("\tSUB AL, AH"+LINEA);
                        AL = "AL - AH";
                    }
                    
                    // Si se trata de una suma.
                    else if (triplo.operador.get(i).equalsIgnoreCase("+")) {
                        stb.append("\tADD AL, AH"+LINEA);
                        AL = "AL + AH";
                    }
                }
                
                // Si AL tiene guardada información, se utiliza la misma para seguir utilizandose.
                else {
                    
                    // Si no estamos ante una división, se utiliza el registro AH
                    if (!triplo.operador.get(i).equalsIgnoreCase("/")) {
                        stb.append("\tMOV AH, "+triplo.fuente.get(i)+LINEA);
                        AH = triplo.fuente.get(i);
                    }
                    
                    // Pero si se trata de una división.
                    else {
                        
                        // Se limpia la parte alta de AX si no se encuentra vacía.
                        if (!AH.isEmpty()) {
                            stb.append("\tMOV AH, 0"+LINEA);
                            AH = "";
                        }
                        
                        // Se pasa a CL el divisor.
                        stb.append("\tMOV CL, AL"+LINEA);
                        CL = triplo.fuente.get(i);
                        
                        // Se pasa a AL el número a dividir.
                        stb.append("\tMOV AL, "+triplo.fuente.get(i)+LINEA);
                        CL = triplo.fuente.get(i);
                        
                        // Se realiza la división y se indica los registros con información.
                        stb.append("\tDIV CL"+LINEA);
                        AH = "Residuo";
                        AL = "Resultado";
                        
                        // Se salta la instrucción que hace un guardado de dos temporales.
                        i++;
                    }
                    
                    // Se salta la instrucción que hace un guardado de dos temporales.
                    i++;
                    
                    // Si se trata de una multiplicación.
                    if (triplo.operador.get(i).equalsIgnoreCase("*")) {
                        stb.append("\tMUL AL, AH"+LINEA);
                        AL = "AL * AH";
                    }
                    
                    // Si se trata de una resta.
                    else if (triplo.operador.get(i).equalsIgnoreCase("-")) {
                        stb.append("\tSUB AL, AH"+LINEA);
                        AL = "AL - AH";
                    }
                    
                    // Si se trata de una suma.
                    else if (triplo.operador.get(i).equalsIgnoreCase("+")) {
                        stb.append("\tADD AL, AH"+LINEA);
                        AL = "AL + AH";
                    }
                    
                }
                
            }
            
            // Si la siguiente instrucción es un ciclo.
            if (i + 1 == numinsdo) {
                
                // Se escribe un salto hacia el ciclo.
                stb.append("\tJMP Operacion"+numdo+LINEA);
                
            }
            
            // Si la siguiente instrucción es una condicion.
            if (i + 1 == numinswhile) {
                
                // Se escribe un salto hacia el ciclo.
                stb.append("\tJMP Comparacion"+numCon+LINEA);
                
            }
            
            // Si estamos en la ultima linea.
            if (i + 1 == triplo.numeroIns.size()) {
                stb.append("\tEND");
            }
            
        }
        
        Archivo.guardar("Codigo Objeto.txt", stb.toString());
        return codigo;
    }
    
}
