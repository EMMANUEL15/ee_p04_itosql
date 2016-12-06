package ee_p04_itosql;
import java.awt.*; 
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import java.util.*;
public class MostrarTabla<T>  extends  JFrame implements ActionListener 
{
  private Container Ventana1= getContentPane(); 
  private JButton Mostrar=new JButton("Mostrar");
  private JButton Aceptar=new JButton("Aceptar");
  DefaultComboBoxModel tablas=new DefaultComboBoxModel();
  JComboBox ListaTablas=new JComboBox(tablas);
  DefaultComboBoxModel Columnas=new DefaultComboBoxModel();
  JComboBox listaColumanas=new JComboBox(Columnas);
  String Metodos[]={"Burbuja","Burbuja con señal","Shaker Sort","Inserción directa","Inserción Binaria","Selección Directa","Shell Sort","Quick Sort"};
  JComboBox TipOrdenacion=new JComboBox(Metodos);
  public ArrayList<JTextField> variable = new ArrayList<JTextField>();
  JLabel Tablas = new JLabel("Tablas: ");
  JLabel Ordenar = new JLabel( "Ordenar por: ");
  JLabel tabla = new JLabel( "TABLA:");
  public Tabla<T> NuevaTabla; String id="";
  DefaultTableModel table=new DefaultTableModel();
  private JTable registro=new JTable();
  private JScrollPane Jsp=new JScrollPane();
  public MostrarTabla(){
      super(" TABLAS");
      setSize(900,500);
      Ventana1.setLayout(null);
              //primer jlabel
      Ventana1.add(Tablas);
      Tablas.setBounds(100,20,100,30);
              //segundo jlabel
      Ventana1.add(Ordenar);
      Ordenar.setBounds(60,70,150,30);
              //tercero jlabel
      Ventana1.add(tabla);
      tabla.setBounds(60,120,150,30);
             //Boton MOSTRAR
      Ventana1.add(Mostrar);
      Mostrar.setBounds(500,20,190,30);
      Mostrar.addActionListener(this);
              //jCombobox 1
      Ventana1.add(ListaTablas);
      ListaTablas.setBounds(160,20,300,30);
               //jCombobox 2
      Ventana1.add(listaColumanas);
      listaColumanas.setBounds(160,70,250,30);
               //jCombobox 3
      Ventana1.add(TipOrdenacion);
      TipOrdenacion.setBounds(440,70,250,30);
              //mostar tabla
      Ventana1.add(registro);
      registro.setBackground(Color.BLACK);
      registro.setForeground(Color.WHITE); 
      Ventana1.add(Jsp);
      Jsp.setViewportView(registro);
      Jsp.setBounds(20,160,0,getHeight());
  }
  public void actionPerformed(ActionEvent e){
      if(e.getSource()==Mostrar){
         Tabla<T> var=NuevaTabla; int i=0;
         while(var!=null){
           if(String.valueOf(var.getNombre()).equals(String.valueOf(ListaTablas.getSelectedItem()))){
              if(id.equals(String.valueOf(var.getNombre()))){ 
                  MostrarInformacion(var.getSiguienteFila());
              }else{
                  this.dispose();
                  MostrarTabla<T> V=new MostrarTabla<T>();
                  V.setLocationRelativeTo(null);
                  V.llenarfichero(NuevaTabla);
                  V.setNuevaTabla(NuevaTabla);
                  V.setVisible(true);
                  V.setExtendedState(JFrame.MAXIMIZED_BOTH);
                  this.id=String.valueOf(ListaTablas.getSelectedItem());
                  V.creaColumnas(var.getSiguienteFila());
                  V.MostrarInformacion(var.getSiguienteFila());
              }
              break;
              }
           var=var.getSiguienteTabla();
         }
      }
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
   public void creaColumnas(Informacion<T> tabla){
       String arreglo[]=String.valueOf(tabla.getDato()).split("\\|");
       Columnas.removeAllElements();
       for(int i=0;i<arreglo.length;i++){
          Columnas.addElement((T)arreglo[i]);
          table.addColumn(arreglo[i]);
          registro.setModel(table);
          }
       if(arreglo.length>=7){
          Jsp.setBounds(20,160,1300,getHeight());
          }else{
           Jsp.setBounds(20,160,(200*(arreglo.length)),(520));
          }
  }
  public void llenarfichero(Tabla<T> tabla){
       Tabla<T> var=tabla;
       tablas.removeAllElements(); int i=0;
       while(var!=null){
           if(i!=0){
              tablas.addElement(var.getNombre());
              }
           i++;
           var=var.getSiguienteTabla();
        }
  }
  public void setNuevaTabla(Tabla<T> NT){
      this.NuevaTabla=NT;
  }
  public static void main(String [] args){
       MostrarTabla V=new MostrarTabla();
       V.setLocationRelativeTo(null);
       V.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
       V.setVisible(true);
  }
}