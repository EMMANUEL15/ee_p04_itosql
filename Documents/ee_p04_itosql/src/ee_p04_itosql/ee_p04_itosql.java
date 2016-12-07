package ee_p04_itosql;
/** 
* @author (Lopez Ramirez Emanuel) 
* 
* simulador de msql
* muestra la intefaz JComboBox de las opciones que realiza el sistema
* crear borrar y mostrar tabla, ademas incertar dato en la tabla 
* 
* hecho con arbol binario
* 
* el lado derecho de nodo tabla apunta a nodo tabla y su lado izquirdo apunta sun contenido
* 
* el lado derecho de contenido apunta la columnas que tendra la tabla y su lado derecho apunta a nodo de una 
* lista doblemeten ligada que son los datos que se almacena en la tabla.
* 
* la tabla que es mostrada solo es preliminar para tene en cuenta las acciones 
* 
*/
import java.awt.*; 
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
public class ee_p04_itosql<T> extends  JFrame implements ActionListener 
{
  private Container Ventana1= getContentPane(); 
  DefaultComboBoxModel Columnas=new DefaultComboBoxModel();
  JLabel Opciones = new JLabel( "Opciones  " );//
  JLabel simulacion = new JLabel( "ITO_MSQL" );
  public String OPCIONES[]={"CREAR TABLA","MOSTRAR TABLA", "INCERTAR DATO","BORRAR TABLA"};
  JComboBox ListaOpciones=new JComboBox(OPCIONES);
  private JButton Aceptar=new JButton("Aceptar");
  private JButton Mostrar=new JButton("Actualizar");
   private JButton ver=new JButton("ver");
  Tabla<T> NuevaTabla;
  DefaultComboBoxModel tablas=new DefaultComboBoxModel();
  JComboBox ListaTablas=new JComboBox(tablas);
  DefaultTableModel table=new DefaultTableModel();
  private JTable registro=new JTable();
  private JScrollPane Jsp=new JScrollPane();
  /**
   * contenido
   */
  public ee_p04_itosql(){
      super("ee_p04_itosql");
      setSize(400,500);
      Ventana1.setLayout(null);
      Ventana1.setBackground(Color.BLACK);
      //Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("mysql.png"));
      //setIconImage(icon);
      setVisible(true);
      
      Ventana1.add(simulacion); 
      simulacion.setBounds(120,20,150,30);
      Font fuente = new Font("Calibri",1, 30);//TEMA,TAMAÑO
      simulacion.setFont(fuente); 
      simulacion.setForeground(Color.ORANGE);
      
      Ventana1.add(Opciones);
      Opciones.setBounds(120,60,150,30);
      Opciones.setForeground(Color.WHITE);
          //jlabel
      Ventana1.add(ListaOpciones);
      ListaOpciones.setBounds(20,90,300,30);
          //Boton Acpetar
      Ventana1.add(Aceptar);
      Aceptar.setBounds(170,130,150,30);
      Aceptar.addActionListener(this);
      
      Ventana1.add(Mostrar);
      Mostrar.setBounds(170,190,150,30);
      Mostrar.addActionListener(this);
      
      Ventana1.add(ver);
      ver.addActionListener(this);
      e();
      
      Ventana1.add(Jsp);
      Jsp.setViewportView(registro);
       //jlabel con imag
      JLabel JImagen = new JLabel();
      Ventana1.add(JImagen);
      JImagen.setBounds(20,10,70,70);
      ImageIcon imagen = new ImageIcon("mysql.png");
      Icon icono=new ImageIcon(imagen.getImage().getScaledInstance(JImagen.getWidth(),JImagen.getHeight(),Image.SCALE_DEFAULT));
      JImagen.setIcon(icono);
      this.repaint();
  }
  /**
   * acciones de los botones
   */
  public  void actionPerformed(ActionEvent e) {//sobreescribimos el metodo del listener
      if(e.getSource()==Aceptar){
          if(ListaOpciones.getSelectedItem().equals("MOSTRAR TABLA")){
               MostrarTabla V=new MostrarTabla();
               V.setLocationRelativeTo(null);
               V.llenarfichero(NuevaTabla);
               V.setNuevaTabla(NuevaTabla);
               V.setVisible(true);
          }else if(ListaOpciones.getSelectedItem().equals("CREAR TABLA")){
               CrearTabla V=new CrearTabla();
               V.setLocationRelativeTo(null);
               V.setNuevaTabla(NuevaTabla);
               V.setVisible(true);
          }else if(ListaOpciones.getSelectedItem().equals("INCERTAR DATO")){
              AgregarInfo V=new AgregarInfo();
              V.setLocationRelativeTo(null);
              V.llenarfichero(NuevaTabla);
              V.setNuevaTabla(NuevaTabla);
              V.setVisible(true);
          }else if(ListaOpciones.getSelectedItem().equals("BORRAR TABLA")){
              BorrarTabla V=new BorrarTabla();
              V.setLocationRelativeTo(null);
              V.llenarfichero(NuevaTabla);
              V.setNuevaTabla(NuevaTabla);
              V.setResizable(false); 
              V.setVisible(true);
          }
      }else if(e.getSource()==Mostrar){
              Tabla<T> var =NuevaTabla;
              tablas.removeAllElements(); int i=0;
              while(var!=null){
                  if(i!=0){
                      tablas.addElement(var.getNombre());
                  }
                  i++;
                  var=var.getSiguienteTabla();
              }
              JLabel vista = new JLabel("Vista preliminar de las tablas");
              Ventana1.add(vista);
              vista.setForeground(Color.WHITE);
              vista.setBounds(100,250,220,30);
              Ventana1.add(ListaTablas);
              ListaTablas.setBounds(100,290,220,30);
              ver.setBounds(170,340,150,30);
          }else if(e.getSource()==ver){
           if(ListaOpciones.getSelectedItem().equals(null)||ListaOpciones.getSelectedItem().equals("")){
           }else {
              Tabla<T> var=NuevaTabla; int i=0;
         while(var!=null){
           if(String.valueOf(var.getNombre()).equals(String.valueOf(ListaTablas.getSelectedItem()))){
                  String d=String.valueOf(ListaTablas.getSelectedItem());
                  this.dispose();
                  ee_p04_itosql<T> V=new ee_p04_itosql<T>();
                  V.setLocationRelativeTo(null);
                  V.setNuevaTabla(NuevaTabla);
                  V.setVisible(true);
                  V.setExtendedState(JFrame.MAXIMIZED_BOTH);
                  V.creaColumnas(var.getSiguienteFila(),String.valueOf(ListaTablas.getSelectedItem()));
                  V.Burbuja(var.getSiguienteFila().getSiguiente(), V.eleccion(String.valueOf(var.getSiguienteFila().getDato()),d));
                  V.MostrarInformacion(var.getSiguienteFila());
                   break;
              }
           var=var.getSiguienteTabla();
          }
        }
          }
      }
  /**
   * Metodo ejecuable del sistema
   * @param args
   */
  public static void main(String[] args) { 
       ee_p04_itosql V=new ee_p04_itosql();
       V.setLocationRelativeTo(null);
       V.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
       V.setVisible(true);
      }
  /**
   * retorna tabla
   * @return
   */
   public Tabla<T> getNuevaTabla(){
       return NuevaTabla;
  }
   /**
    * actualiza tabla
    * @param recibido
    */
  public void setNuevaTabla(Tabla<T> recibido){
      this.NuevaTabla=recibido;
   }
  /*
   * recibe una primera tabla como referencia
   */
   public void PrimerTabla(T Nombre){
       Tabla<T> N=new Tabla<T>(Nombre);
       N.setSiguienteTabla(NuevaTabla);
       N.setSiguienteFila(Columna(Nombre));
       this.NuevaTabla=N;
  }
   /**
    * recibe datos para crear columnas
    * @param columnas
    * @return
    */
  public Informacion<T> Columna(T columnas){
       Informacion<T> filas=null;
       Informacion<T> N=new Informacion<T>(columnas);
       N.setSiguiente(filas);
       filas=N;
       return filas;
      }
  /**
   * ingresa tabla de refencia
   */
  public  void e(){
      PrimerTabla((T)""); 
  }
  /**
   * crea tablas con columas
   * @param tabla
   * @param t
   */
   public void creaColumnas(Informacion<T> tabla,String t){
       JLabel tab = new JLabel( "Nombre de la tabla:   "+t);
       Ventana1.add(tab);
       tab.setBounds(400,20,300,30);
       tab.setForeground(Color.WHITE);
       String arreglo[]=String.valueOf(tabla.getDato()).split("\\|");
       Columnas.removeAllElements();
       for(int i=0;i<arreglo.length;i++){
          Columnas.addElement((T)arreglo[i]);
          table.addColumn(arreglo[i]);
          registro.setModel(table);
          }
       Jsp.setBounds(400,50,900,595);
  }
   /**
    * Agrega la informacion en la cada fila de la tabla
    * @param x
    */
  public void MostrarInformacion(Informacion<T> x){
      Informacion<T> var=x; int n=0;
      while(var!=null){
         if(n>0){
          String arreglo[]=String.valueOf(var.getDato()).split("\\|");
          table.addRow(arreglo);
        }
        n++;
        var=var.getSiguiente();
      }
  }
  /**
   * crea retorna un valor de la columana que sera ordenada
   * @param d
   * @param e
   * @return
   */
  public int eleccion(String d,String e){
      String arreglo[]=d.split("\\|"); int r=0;
      for(int i=0;i<arreglo.length;i++){
          if(arreglo[i].equals(e)){
              r=i;break;
          }
        }
      return r;
  }
  /**
   * merodo de oredenamiento para los datos de la tabla
   * @param inf
   * @param p
   */
   public void Burbuja(Informacion<T> inf,int p){
     try{
     for(int i=0;i<2;i++){
       Informacion<T> informacion=inf;
       while(informacion.getSiguiente()!=null){
         String arreglo1[]=String.valueOf(informacion.getDato()).split("\\|"); String arreglo2[]=String.valueOf(informacion.getSiguiente().getDato()).split("\\|");
         if(arreglo1[p].compareTo(arreglo2[p])>0){
          T aux=informacion.getDato();
          informacion.setDato(informacion.getSiguiente().getDato());
          informacion.getSiguiente().setDato(aux);
          while(informacion.getAnterior()!=null){
            informacion=informacion.getAnterior();
           }
          }
         informacion=informacion.getSiguiente();
                   }
                  }
              }catch(Exception x){}
  }
     }
