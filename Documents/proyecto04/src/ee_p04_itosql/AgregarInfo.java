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
public class AgregarInfo<T>  extends  JFrame implements ActionListener 
{
  private Container Ventana1= getContentPane(); 
  private JButton Aceptar=new JButton("Aceptar");
  DefaultComboBoxModel tablas=new DefaultComboBoxModel();
  JComboBox ListaTablas=new JComboBox(tablas);
  JLabel Tablas = new JLabel("Tablas: ");
  JLabel tabla = new JLabel( "AGREGAR DATOS A LA TABLA");
  public Tabla<T> NuevaTabla; String id="";
  public ArrayList<JTextField> variable = new ArrayList<JTextField>();
  private JButton Agregar=new JButton("Agregar Info >>");
  public AgregarInfo(){
      super("Agregar Dato");
      setSize(850,500);
      Ventana1.setLayout(null);
              //icono
      //Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("mysql.png"));
      //setIconImage(icon);
      setVisible(true);
              //primer jlabel
      Ventana1.add(tabla);
      tabla.setBounds(250,10,300,30);
              //segunda jlabel
      Ventana1.add(Tablas);
      Tablas.setBounds(40,60,150,30);
             //Boton Aceptar
      Ventana1.add(Aceptar);
      Aceptar.setBounds(460,60,150,30);
      Aceptar.addActionListener(this);
              //jCombobox 1
      Ventana1.add(ListaTablas);
      ListaTablas.setBounds(120,60,300,30);
            //BOTON DE AGREGAR
      Ventana1.add(Agregar);
      Agregar.setBounds(600,120,190,30);
      Agregar.addActionListener(this);
  }
  public void setId(String id){this.id=id;}
  public void actionPerformed(ActionEvent e){
      if(e.getSource()==Aceptar){
          Tabla<T> var=NuevaTabla; int i=0;
         while(var!=null){
           if(String.valueOf(var.getNombre()).equals(String.valueOf(ListaTablas.getSelectedItem()))){
             if(id.equals(String.valueOf(var.getNombre()))){ 
              }else{
                this.dispose();
                AgregarInfo<T> V=new AgregarInfo<T>();
                V.setLocationRelativeTo(null);
                V.llenarfichero(NuevaTabla);
                V.setNuevaTabla(NuevaTabla);
                V.setId(String.valueOf(ListaTablas.getSelectedItem()));
                V.setVisible(true);
                this.id=String.valueOf(ListaTablas.getSelectedItem());
                V.Campos(var.getSiguienteFila());
              }
              break;
              }
           var=var.getSiguienteTabla();
         }
      }else if(e.getSource()==Agregar){
         Receptor();
      } 
  }
  public void Receptor(){
      String fila="";
      for(int i=0;i<variable.size();i++){
      if(variable.get(i).getText().equals(null)||(variable.get(i).getText().equals(""))){
              fila=String.valueOf(fila+"   |");
         }else{
              fila=String.valueOf(fila+variable.get(i).getText()+"|");
      }
      variable.get(i).setText(null);
    }
    AgregarInf((T)fila);
  }
  public void AgregarInf(T inf){// nueva fila nueva informacion
      Tabla<T> var=NuevaTabla; int i=0;
      while(var!=null){
        if(String.valueOf(var.getNombre()).equals(id)){
          Informacion<T> F =var.getSiguienteFila();
           while(F.getSiguiente()!=null){
             F=F.getSiguiente();
          }
          Informacion<T> N=new Informacion<T>(inf);
          F.setSiguiente(N);
          N.setSiguiente(null);
          if(var.getSiguienteFila()==F){ 
                N.setAnterior(null);
           }else{
               N.setAnterior(F);
           }
          break;
              }
        var=var.getSiguienteTabla();
         }
  }
  public void Campos(Informacion<T> tabla){
       String arreglo[]=String.valueOf(tabla.getDato()).split("\\|");
       int x=getHeight()-80;
       int Av=120;
       JLabel titulo=new JLabel("Nombre de la tabla: "+id);
       Ventana1.add(titulo);
       titulo.setBounds(60,90,300,30);
       titulo.setForeground(Color.BLUE);
       for(int i=0;i<arreglo.length;i++){
              //JTextField de cah
          JLabel campo=new JLabel(arreglo[i]);
          JTextField caja=new JTextField();
              //label);
          Ventana1.add(campo);
          campo.setBounds(60,Av,200,30);
          campo.setHorizontalAlignment(campo.RIGHT);
              //text tabla
          Ventana1.add(caja);
          caja.setBounds(280,Av,240,30);
          variable.add(caja);
          Av=Av+50;
          }
       if(Av>=520){setSize(520,1400);}
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
  
}
