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
  JLabel Opciones = new JLabel( "Opciones: " );//
  JLabel simulacion = new JLabel( "ITO_MSQL" );
  public String OPCIONES[]={"CREAR TABLA","MOSTRAR TABLA", "INCERTAR DATO","BORRAR TABLA"};
  JComboBox ListaOpciones=new JComboBox(OPCIONES);
  private JButton Aceptar=new JButton("Aceptar");
  private JButton Mostrar=new JButton("Actualizar");
  Tabla<T> NuevaTabla;
  DefaultComboBoxModel tablas=new DefaultComboBoxModel();
  JComboBox ListaTablas=new JComboBox(tablas);
  DefaultTableModel table=new DefaultTableModel();
  private JTable registro=new JTable();
  private JScrollPane Jsp=new JScrollPane();
  public ee_p04_itosql(){
      super("ee_p04_itosql");
      setSize(400,400);
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
      //Mostrar.setBounds(170,190,150,30);
      Mostrar.addActionListener(this);
      e();
       //jlabel con imag
      JLabel JImagen = new JLabel();
      Ventana1.add(JImagen);
      JImagen.setBounds(20,10,70,70);
      ImageIcon imagen = new ImageIcon("mysql.png");
      Icon icono=new ImageIcon(imagen.getImage().getScaledInstance(JImagen.getWidth(),JImagen.getHeight(),Image.SCALE_DEFAULT));
      JImagen.setIcon(icono);
      this.repaint();
  }
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
          while(var!=null){
          //System.out.print(var.ToString());
          var=var.getSiguienteTabla();
        }
        //System.out.println();
      }else if(e.getSource()==ListaTablas){
        Tabla<T> var =NuevaTabla;
         while(var!=null){
          if(ListaTablas.getSelectedItem().equals(String.valueOf(var.getNombre()))){
             this.dispose();
             ee_p04_itosql<T> V=new ee_p04_itosql<T>();
             V.setLocationRelativeTo(null);
             V.setNuevaTabla(NuevaTabla);
             V.setVisible(true);
             V.setExtendedState(JFrame.MAXIMIZED_BOTH);
             V.creaColumnas(var.getSiguienteFila(),String.valueOf(ListaTablas.getSelectedItem()));
             V.MostrarInformacion(var.getSiguienteFila());
          }
          var=var.getSiguienteTabla();
        }
      }
  }
  public static void main(String[] args) { 
       ee_p04_itosql V=new ee_p04_itosql();
       V.setLocationRelativeTo(null);
       V.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
       V.setVisible(true);
      }
  public void MisTablas(){
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
      ListaTablas.addActionListener(this);
  }
   public Tabla<T> getNuevaTabla(){
       return NuevaTabla;
  }
  public void setNuevaTabla(Tabla<T> recibido){
      this.NuevaTabla=recibido;
   }
   public void PrimerTabla(T Nombre){
       Tabla<T> N=new Tabla<T>(Nombre);
       N.setSiguienteTabla(NuevaTabla);
       N.setSiguienteFila(Columna(Nombre));
       this.NuevaTabla=N;
  }
  public Informacion<T> Columna(T columnas){
       Informacion<T> filas=null;
       Informacion<T> N=new Informacion<T>(columnas);
       N.setSiguiente(filas);
       filas=N;
       return filas;
      }
  public  void e(){
      PrimerTabla((T)""); 
  }
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
   public void creaColumnas(Informacion<T> tabla,String t){
       JLabel tab = new JLabel( "Nombre de la tabla:   "+t);
       Ventana1.add(tab);
       tab.setBounds(60,120,150,30);
       String arreglo[]=String.valueOf(tabla.getDato()).split("\\|");
       for(int i=0;i<arreglo.length;i++){
          registro.setModel(table);
          }
       if(arreglo.length>=7){
          Jsp.setBounds(20,160,1300,getHeight());
          }else{
           Jsp.setBounds(20,160,(200*(arreglo.length)),(520));
          }
  }
     }
