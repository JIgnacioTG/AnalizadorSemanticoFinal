package analizadorsemantico;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class Interfaz extends javax.swing.JFrame {
    
    private Codigo codigo;
    private static final String LINEA = System.getProperty("line.separator"); //Variable que genera los saltos de línea detectando el sistema del usuario.
    
    private ArrayList<String> token; //En esta lista se almacenan los tokens con su orden.
    private ArrayList<String> valorToken; //En esta lista se almacenan los valores de los tokens.
    private int tPr, tIde, tCe, tCf, tOa, tOr, tOb; //Cada variable conserva el número de cierto token.
    
    DefaultTableModel modelo = new DefaultTableModel(); //Se define un nuevo modelo de tabla.
    public Interfaz() {
        initComponents();
        setLocationRelativeTo(null);    //Centrar la interfaz a la mitad de la pantalla.
        String titulos[]={"Lexema", "Tipo", "Valor"};   //Se crean los titlos a la tabla.
        modelo.setColumnIdentifiers(titulos);   //Se agregan los titulos al modelo.
        Símbolos.setModel(modelo);  //Se envía el modelo de tabla al JTable.
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Entrada = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Salida = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Símbolos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        Enviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setForeground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Analizador Semántico");

        Entrada.setColumns(20);
        Entrada.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Entrada.setRows(5);
        jScrollPane1.setViewportView(Entrada);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Introduzca las líneas de código:");

        Salida.setColumns(20);
        Salida.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Salida.setRows(5);
        jScrollPane2.setViewportView(Salida);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Output");

        Símbolos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Símbolos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(Símbolos);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tabla de símbolos");

        Enviar.setBackground(new java.awt.Color(255, 0, 0));
        Enviar.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        Enviar.setForeground(new java.awt.Color(255, 255, 255));
        Enviar.setText("Compilar");
        Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel1)
                        .addGap(0, 65, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Enviar)
                .addGap(211, 211, 211))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(Enviar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    private void EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarActionPerformed
        codigo = Analizador.busquedaErrores(Entrada.getText());
        rellenarTablas();
        //busquedaErrores(Entrada.getText()); //Se buscan errores a partir del codigo escrito.
    }//GEN-LAST:event_EnviarActionPerformed

    public void rellenarTablas() {
        for(int i=0; i<Símbolos.getRowCount();i++) //For para eliminar filas y limpiar la tabla.
        {
            modelo.removeRow(i);
            i-=1;
        }
        for(int e=0; e<codigo.variable.size(); e++)  //Se verifica el tamaño de mi vector de datos
        {
            modelo.addRow(new Object[]{codigo.variable.get(e),codigo.tipo.get(e),codigo.valor.get(e)});    //Se imprimen todos los valores en el lexema
        }
        StringBuilder stb = new StringBuilder();
        for (String error : codigo.errores) {
            stb.append(error).append(LINEA);
        }
        Salida.setText(stb.toString());
    }
    
    public void busquedaErrores(String codigo) {    //Metodo para el analisis inicial del codigo.        
        token = new ArrayList<String>(); //Se guardan los tokens.
        tPr = 0; tIde = 0; tCe = 0; tCf = 0; tOa = 0; tOr = 0; tOb = 0; //Se registran 0 de cada token.
        
        String[] linea=codigo.split(";");   //Se guarda todo lo que encuentre antes del ; en cada uno de los lugares del vector.
        
        for(int i=0; i<Símbolos.getRowCount();i++) //For para eliminar filas y limpiar la tabla.
        {
            modelo.removeRow(i);
            i-=1;
        }
        
        //Se crean los 3 vectores para almacenar la información.
        String[] Lexema = new String[100];
        String[] Tipo = new String[100];
        String[] Valor = new String[100];
        String[] CaseSwitch= new String[100];
        String[] tipoSwitch = new String[100];
        String[] Token = new String[100];
        String[] TipoAux = new String[100];
        String[] ValorAux= new String [100];
        String[] Comprobacion =new String [100];
        
        int indexLexema=0; //Indice para validar el máximo de lexemas ingresados así como su posición.
        int indexCorchetes=0; //Indice para saber cuantos corchetes hay
        int indexCase=0; //Indice para saber cuantos case hay
        int indexBreak=0; //Indice para saber cuantos break hay;
        int auxTipo=0;
        int contComprobacion=0;
        String valorSwitch="";
        String Tokens="";
        String reemplazar="";
        String AuxComprobacion="";
        
        Salida.setText("");
        
        for(int a=0; a<linea.length; a++) //Limpiar tanto por ; y salto de linea.
        {
            String cadena=linea[a];
            String linea2=cadena+" ";
            String linea3=linea2.replace("\n","");
            String linea4=linea3.replace("\t","");
            linea[a]=linea4;
            String[] lineaUsar=linea[a].split(" "); //Limpiar por espacios.
            if(linea[a].contains("int")||linea[a].contains("float")||linea[a].contains("string")||linea[a].contains("bool"))    //Se valida si se está declarando una variable
            {
                if(linea[a].contains(","))
                {
                    String tipo="";
                    String[] lineaUsar2=linea[a].split(",");
                    for(int b=0; b<1; b++)
                    {
                        String[] lineaUsar3=lineaUsar2[b].split(" ");
                        for(int c=0; c<1; c++)
                        {
                            tipo = lineaUsar3[0];
                        }
                    }
                    for (String lineaUsar21 : lineaUsar2) {
                        String[] lineaUsar3 = lineaUsar21.split(" ");
                        for(int c=0; c<lineaUsar3.length; c+=2)
                        {
                            Tipo[indexLexema]=tipo;
                            int aux=c+1;
                            Lexema[indexLexema]=lineaUsar3[aux];
                            indexLexema++;
                        }
                    }
                }
                else
                {
                    for(int b=0; b<lineaUsar.length; b+=2)  //Se guarda el lexema y el tipo
                    {
                        Tipo[indexLexema]=lineaUsar[b];
                        int aux=b+1;
                        Lexema[indexLexema]=lineaUsar[aux];
                        indexLexema++;
                    }
                }
                reemplazar=linea[a].replace(" ","");
                reemplazar=reemplazar.replace("int","int ");
                reemplazar=reemplazar.replace("float","float ");
                reemplazar=reemplazar.replace("bool","bool ");
                reemplazar=reemplazar.replace("string","string ");
                Tokens=Tokens+reemplazar+";"+"\r\n";
            }
            else
            {
                //Se valida si se está realizando una operación con variables
                if(linea[a].contains("+")||linea[a].contains("-")||linea[a].contains("*")||linea[a].contains("/"))
                {
                    String band="";
                    int indexTipoAux=0;
                    for(int c=0; c<lineaUsar.length; c+=2)
                    {
                        for(int d=0; d<indexLexema; d++)
                        {
                            int aux=c;
                            if(Lexema[d].equals(lineaUsar[aux]))
                            {
                                TipoAux[indexTipoAux]=Tipo[d];
                                ValorAux[indexTipoAux]=Valor[d];
                                indexTipoAux++;
                                break;
                            }
                            else
                            {
                                if(d==indexLexema-1)//Si estoy en mi último lugar del vector y no encontré la variable
                                {
                                    Salida.setText("Error, variable no declarada.\nLa linea del error es: "+linea[a]+"\n");
                                }
                                aux++;
                            }
                        }
                    }
                    for(int r=0; r<indexTipoAux; r++)
                    {
                        if(r==0)
                        {
                            band = TipoAux[r];
                            if(ValorAux[r]==null)
                            {
                                Salida.setText("Error, variable no inicializada.\nLa linea del error es: "+linea[a]+"\n");
                            }
                        }
                        else
                        {
                            if(TipoAux[r].equals(band))
                            {
                                if(ValorAux[r]==null)
                                {
                                    Salida.setText("Error, variable no inicializada.\nLa linea del error es: "+linea[a]+"\n");
                                }
                            }
                            else
                            {
                                Salida.setText("Error, incompatibilidad de tipos.\nLa linea del error es: "+linea[a]+"\n");
                            }
                        }
                    }
                    for(int m=2; m<lineaUsar.length; m++)
                    {
                        AuxComprobacion=AuxComprobacion+lineaUsar[m];
                    }
                    Comprobacion[contComprobacion]=AuxComprobacion;
                    contComprobacion++;
                    AuxComprobacion="";
                    
                    reemplazar=linea[a].replace(" ","");
                    Tokens=Tokens+reemplazar+";"+"\r\n";
                }
                else    //Si no se está declarando una variable ni se está realizando una operación (se verifica el Switch)}
                {
                    if(linea[a].contains("switch"))
                    {
                        valorSwitch=lineaUsar[1];
                        for(int p=0; p<indexLexema; p++)  //Se recorre el vector del lexema
                        {
                            if(Lexema[p].equals(valorSwitch))  //Si la variable ha sido declarada
                            {
                                if(Valor[p]==null)
                                {
                                    Salida.setText("Error, variable sin inicializar.\nLa linea del error es: "+linea[a]+"\n");
                                }
                                else
                                {
                                    auxTipo++;
                                    tipoSwitch[auxTipo]=Tipo[p];
                                }
                                break;
                            }
                            else
                            {
                                if(p==indexLexema-1)  //Si estoy en mi último lugar del vector y no encontré la variable
                                {
                                    Salida.setText("Error, variable no declarada.\nLa linea del error es: "+linea[a]+"\n");
                                }
                            }
                            
                        }
                        reemplazar=linea[a].replace(" ","");
                        reemplazar=reemplazar.replace("switch", "switch ");
                        Tokens=Tokens+reemplazar+";"+"\r\n";
                    }
                    else
                    {
                        if (linea[a].contains("{"))
                        {
                            indexCorchetes++;
                            reemplazar=linea[a].replace(" ","");
                            Tokens=Tokens+reemplazar+"\r\n";
                        }
                        else
                        {
                            if (linea[a].contains("}")) {
                                indexCorchetes++;
                                auxTipo--;
                                reemplazar=linea[a].replace(" ","");
                                Tokens=Tokens+reemplazar+"\r\n";
                            }
                            else
                            {
                                if (linea[a].contains("case"))
                                {
                                    indexCase++;
                                    String valorCase=lineaUsar[1];
                                    CaseSwitch[indexCase]=lineaUsar[1];
                                    boolean resultado;
                                    if(tipoSwitch[auxTipo].equals("int"))
                                    {
                                        try {
                                            Integer.parseInt(valorCase);
                                            resultado = true;
                                        }
                                        catch (NumberFormatException excepcion) {
                                            resultado = false;
                                        }
                                        if(!resultado)
                                        {
                                            if(!valorCase.equals("default"))
                                            {
                                                Salida.setText("Error, incompatibilidad de tipos.\nEl tipo del valor utilizado en el case\nes diferente al que se evalua en el switch.\nLa linea del error es: "+linea[a]+"\n");
                                            }
                                        }
                                    }
                                    if(tipoSwitch[auxTipo].equals("float"))
                                    {
                                        try {
                                            Float.parseFloat(valorCase);
                                            resultado = true;
                                        }
                                        catch (NumberFormatException excepcion) {
                                            resultado = false;
                                        }
                                        if(!resultado)
                                        {
                                            if(!valorCase.equals("default"))
                                            {
                                                Salida.setText("Error, incompatibilidad de tipos.\nEl tipo del valor utilizado en el case\nes diferente al que se evalua en el switch.\nLa linea del error es: "+linea[a]+"\n");
                                            }
                                        }
                                    }
                                    if(tipoSwitch[auxTipo].equals("bool"))
                                    {
                                        if(!valorCase.equals("0")&&!valorCase.equals("1"))
                                        {
                                            if(!valorCase.equals("default"))
                                            {
                                                Salida.setText("Error, incompatibilidad de tipos.\nEl tipo del valor utilizado en el case\nes diferente al que se evalua en el switch.\nLa linea del error es: "+linea[a]+"\n");
                                            }
                                        }
                                    }
                                    if(tipoSwitch[auxTipo].equals("string"))
                                    {
                                        try {
                                            resultado = true;
                                        }
                                        catch (NumberFormatException excepcion) {
                                            resultado = false;
                                        }
                                        if(!resultado)
                                        {
                                            if(valorCase.equals("default"))
                                            {
                                            }
                                            else
                                            {
                                                Salida.setText("Error, incompatibilidad de tipos.\nEl tipo del valor utilizado en el case\nes diferente al que se evalua en el switch.\nLa linea del error es: "+linea[a]+"\n");
                                            }
                                        }
                                    }
                                    reemplazar=linea[a].replace(" ","");
                                    reemplazar=reemplazar.replace("case", "case ");
                                    Tokens=Tokens+reemplazar+":"+"\r\n";
                                }
                                else
                                {
                                    if(linea[a].contains("break"))
                                    {
                                        indexBreak++;
                                        reemplazar=linea[a].replace(" ","");
                                        Tokens=Tokens+reemplazar+";"+"\r\n";
                                    }
                                    else
                                    {
                                        if(linea[a].contains("="))
                                        {
                                            for(int r=0; r<lineaUsar.length; r+=3)  //Se verifica la cantidad de variables
                                            {
                                                int aux2;
                                                for(int d=0; d<indexLexema; d++)  //Se recorre el vector del lexema
                                                {
                                                    int aux3=0;
                                                    aux2=r+2;
                                                    if(Lexema[d].equals(lineaUsar[aux3]))  //Si la variable ha sido declarada
                                                    {
                                                        Valor[d]=lineaUsar[aux2];   //Guarda el valor en el indice de mi variable
                                                        break;
                                                    }
                                                    else    //Si no se encuentra la segunda variable
                                                    {
                                                        aux3++; //Aumento mi señalador
                                                        if(d==indexLexema-1)//Si estoy en mi último lugar del vector y no encontré la variable
                                                        {
                                                            Salida.setText("Error, variable no declarada.\nLa linea del error es: "+linea[a]+"\n");
                                                        }
                                                    }
                                                }
                                            }
                                            reemplazar=linea[a].replace(" ","");
                                            Tokens=Tokens+reemplazar+";"+"\r\n";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
        }
        for(int d=0; d<contComprobacion; d++)
        {
            System.out.println(Comprobacion[d]);
        }
        if(indexCorchetes%2!=0)
        {
            Salida.setText("Error, falta o sobra el cierre o apertura\nde algún corchete.");
        }
        if(indexCase<indexBreak)
        {
            Salida.setText("Error, se encuentran más break de\nlos necesarios.");
        }
        else
        {
            if(indexCase>indexBreak)
            {
                Salida.setText("Error, se encuentran menos break de\nlos necesarios.");
            }
        }
        for(int e=0; e<indexLexema; e++)  //Se verifica el tamaño de mi vector de datos
        {
            modelo.addRow(new Object[]{Lexema[e],Tipo[e],Valor[e]});    //Se imprimen todos los valores en el lexema
        }
        
        String ruta = "Optimizado.txt";
        File archivo = new File(ruta);
        if(archivo.exists())
        {
            archivo.delete();
        }
        try
        {
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Tokens);
            bw.close();
            //System.out.println("Se creo de manera correcta");
        }
        catch (Exception e)
        {
        }
    }
    
    public void registrarToken(String token, String valor) {
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Interfaz().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Entrada;
    private javax.swing.JButton Enviar;
    private javax.swing.JTextArea Salida;
    private javax.swing.JTable Símbolos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}