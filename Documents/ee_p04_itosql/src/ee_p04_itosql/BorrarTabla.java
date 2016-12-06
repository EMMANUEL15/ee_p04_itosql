package ee_p04_itosql;
/** 
* @author (Lopez Ramirez Emanuel) 
*/
import java.awt.*; 
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import java.util.*;
public class BorrarTabla<T>  extends  JFrame implements ActionListener 
{
  private Container Ventana1= getContentPane(); 
  private JButton Borrar=new JButton("Borrar");
  DefaultComboBoxModel tablas=new DefaultComboBoxModel();
  JComboBox ListaTablas=new JComboBox(tablas);
  JLabel Tablas = new JLabel("Tablas: ");
  JLabel tabla = new JLabel( "BORRAR TABLA");
  public Tabla<T> NuevaTabla; String id="";
  private JButton Aceptar=new JButton("Cancelar");
  public BorrarTabla(){
      super("Borrar Tabla");
      setSize(660,250);
      Ventana1.setLayout(null);
              //icono
      //Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("mysql.png"));
      //setIconImage(icon);
      setVisible(true);
              //primer jlabel
      Ventana1.add(tabla);
      tabla.setBounds(250,10,100,30);
              //segunda jlabel
      Ventana1.add(Tablas);
      Tablas.setBounds(40,60,150,30);
             //Boton MOSTRAR
      Ventana1.add(Borrar);
      Borrar.setBounds(460,60,150,30);
      Borrar.addActionListener(this);
              //jCombobox 1
      Ventana1.add(ListaTablas);
      ListaTablas.setBounds(120,60,300,30);
             //boton Aceptar
      Ventana1.add(Aceptar);
      Aceptar.setBounds(40,150,100,30);
      Aceptar.addActionListener(this);
  }
  public void actionPerformed(ActionEvent e){
      if(e.getSource()==Borrar){
        try{  
          if(ListaTablas.getSelectedItem().equals(null)||ListaTablas.getSelectedItem().equals("")){
          }else{
              Object [] opciones ={"Aceptar","Cancelar"};
              int eleccion = JOptionPane.showOptionDialog(rootPane,"Esta seguro que desea Eliminar la tabla","Mensaje de Confirmacion",
              JOptionPane.YES_NO_OPTION,
              JOptionPane.QUESTION_MESSAGE,null,opciones,"");
              if (eleccion == JOptionPane.YES_OPTION) { 
                Eliminar((T)ListaTablas.getSelectedItem());
                tablas.removeElement(ListaTablas.getSelectedItem());
               }
              }    
          }catch(Exception x){JOptionPane.showMessageDialog(rootPane,"No hay tablas!");}      
          }else if(e.getSource()==Aceptar){
              this.dispose();
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
  public void Eliminar(T dato){
       Tabla<T> aux=NuevaTabla.getSiguienteTabla();   Tabla<T> Anterior=NuevaTabla;   Tabla<T> siguiente=null; boolean sts=false;
       while(aux.getSiguienteTabla()!=null){
         if(aux.getSiguienteTabla()!=null){siguiente=aux.getSiguienteTabla();}
         if(String.valueOf(dato).equals(String.valueOf(aux.getNombre()))){
            Anterior.setSiguienteTabla(siguiente);
            sts=true;
            break;
          }
         Anterior=Anterior.getSiguienteTabla();
         aux=aux.getSiguienteTabla();
      }
      if(String.valueOf(dato).equals(String.valueOf(aux.getNombre()))&&sts==false){
         Anterior.setSiguienteTabla(null);
      }
  }
 
}