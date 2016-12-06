package ee_p04_itosql;

import java.awt.*; 
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class ee_p04_itosql<T> extends  JFrame implements ActionListener 
{
  private Container Ventana1= getContentPane(); 
  JLabel Opciones = new JLabel( "Opciones   ⇓ ⇓ ⇓" );//
  JLabel simulacion = new JLabel( "ITO_MSQL" );
  public String OPCIONES[]={"CREAR TABLA","MOSTRAR TABLA", "INCERTAR DATO", "BORRAR DATO","BORRAR TABLA"};
  JComboBox ListaOpciones=new JComboBox(OPCIONES);
  private JButton Aceptar=new JButton("Aceptar");
  private JButton Mostrar=new JButton("Mostrar");
  Tabla<T> NuevaTabla;
  public ee_p04_itosql(){
      super("ee_p04_itosql");
      setSize(220,250);
      Ventana1.setLayout(null);
      
      Ventana1.add(simulacion); //Label1.setForeground(Color.WHITE);
      simulacion.setBounds(40,20,150,30);
      Font fuente = new Font("Calibri",1, 30);//TEMA,TAMAÑO
      simulacion.setFont(fuente); 
      simulacion.setForeground(Color.BLACK);
      Ventana1.add(Opciones); //Label1.setForeground(Color.WHITE);
      Opciones.setBounds(50,60,150,30);
      
      Ventana1.add(ListaOpciones);
      ListaOpciones.setBounds(20,90,300,30);
          //Boton Acpetar
      Ventana1.add(Aceptar);
      Aceptar.setBounds(20,130,150,30);
      Aceptar.addActionListener(this);
      
      Ventana1.add(Mostrar);
      Mostrar.setBounds(20,160,150,30);
      Mostrar.addActionListener(this);
      e();
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
          }else if(ListaOpciones.getSelectedItem().equals("BORRAR DATO")){
              //CrearTabla V=new CrearTabla();
              //frame(V);
          }else if(ListaOpciones.getSelectedItem().equals("BORRAR TABLA")){
              BorrarTabla V=new BorrarTabla();
              V.setLocationRelativeTo(null);
              V.llenarfichero(NuevaTabla);
              V.setNuevaTabla(NuevaTabla);
              V.setResizable(false); 
              V.setVisible(true);
          }
      }else if(e.getSource()==Mostrar){
         Tabla<T> F =NuevaTabla;
         while(F!=null){
             System.out.print(F.ToString()+"--");
             F=F.getSiguienteTabla();
          }
         System.out.println();
      }
  }
  public static void main(String[] args) { 
       ee_p04_itosql V=new ee_p04_itosql();
       V.setLocationRelativeTo(null);
       V.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
       V.setVisible(true);
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
     }
