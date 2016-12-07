package ee_p04_itosql;
import java.awt.*; 
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.util.*;
/** 
* @author (Lopez Ramirez Emanuel) 
* 
* ESTA CLASSE CREA TABLAS 
* EN CASO QUE UNA TABLA TENGA CAMPOS SIN NOMBRE O SIN CAMPOS NO SE CREA NO ES NECESARIO CREA UNA TABLA VACIA 
* EXIGE QUE LA TABLA TENGA UN NOMBRE Y CAMPOS, LOS CAMPOS SE CREAN CON EL BOTON AGREGAR CAMPOS Y TABLA SE CREA CON EL BOTON ACEPTAR
* 
*/
public class CrearTabla<T>  extends  JFrame implements ActionListener 
{
  private Container Ventana1= getContentPane(); 
  private JButton Aceptar=new JButton("Aceptar");
  private JButton Agregar=new JButton("Agregar campo >>");
  private JLabel Nombre=new JLabel("Nombre de la Tabla: ");
  private JTextField tabla=new JTextField();
  int Av=60; Tabla<T> NuevaTabla;
  public ArrayList<JTextField> variable = new ArrayList<JTextField>();
  /**
   * CONTENIDO DE LA INTERFAZ
   */
  public CrearTabla(){
      super("CREACION DE TABLA");
      setSize(1000,500);
      Ventana1.setLayout(null);
            //icono
      //Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("mysql.png"));
      //setIconImage(icon);
      setVisible(true);
           //Boton Agregar campo
      Ventana1.add(Agregar);
      Agregar.setBounds(600,20,190,30);
      Agregar.addActionListener(this);
          //Boton Acpetar
      Ventana1.add(Aceptar);
      int x=getHeight()-80;
      Aceptar.setBounds(20,x,100,30);
      Aceptar.addActionListener(this);
          //label Nombre
      Ventana1.add(Nombre);
      Nombre.setBounds(60,20,150,30);
          //text tabla
      Ventana1.add(tabla);
      tabla.setBounds(220,20,240,30);
      
}
  /**
   * CREA NUEVOS ESPSCIOS CADA VES QUE QUE ES SOLICITADO POR EL BOTON AGREGAR CAMPO
   */
  public void AgragarCampo(){
   int x=getHeight()-80;
      if(Av<600){
      this.Av=Av+50;
      String OPCIONES[]={"Texto", "Numerico" };
      JComboBox tipo=new JComboBox(OPCIONES);
      JLabel NombreC=new JLabel("Nombre de Campo: ");
          //JTextField de cah
      JLabel type=new JLabel("Tipo: ");
      JTextField caja=new JTextField();
          //lista de variables 
      variable.add(caja);
          //label
      Ventana1.add(NombreC);
      NombreC.setBounds(60,Av,150,30);
      Ventana1.add(type);
      type.setBounds(580,Av,50,30);
           //text tabla
      Ventana1.add(caja);
      caja.setBounds(220,Av,240,30);
           //JComboBox
      Ventana1.add(tipo);
      tipo.setBounds(650,Av,150,30);
     
      Aceptar.setBounds(20,x,100,30);
  }else{ JOptionPane.showMessageDialog(rootPane,"Necesita deplazarse!");}
}
/**
 * RECOLECTA LOS DATOS  QUE HAN SIDO AGREDADOS PARA CREA UN NUEVO NODO DE LA LISTA DOMBLEMENTE LIGADA
 */
public void Imprimier(){
  String fila="";
  for(int i=0;i<variable.size();i++){
    //System.out.println(variable.get(i).getText());
    if(variable.get(i).getText().equals("")||variable.get(i).getText().equals(null)){
      }else{
      fila=String.valueOf(fila+variable.get(i).getText()+"|");
      }
   }
  if(fila.equals("")){
      JOptionPane.showMessageDialog(rootPane,"Tabla vacia!!");
  }else{
      SiguienteTabla((T)tabla.getText(),(T)fila);
      JOptionPane.showMessageDialog(rootPane,"La tabla ha sido agregado");
      this.dispose();
      CrearTabla V=new CrearTabla();
      V.setLocationRelativeTo(null);
      V.setNuevaTabla(NuevaTabla);
      V.setVisible(true);
  }
}
/**
 * ACCION DE LOS BOTONES 
 */
public void actionPerformed(ActionEvent e) {//sobreescribimos el metodo del listener
   if(e.getSource()==Aceptar){
     if(variable.isEmpty()!=true){
        if(tabla.getText().equals("")){
             JOptionPane.showMessageDialog(rootPane,"Su tabla no tiene Nombre!!"+tabla.getText());//variable.size()
        }else{
             Imprimier();
        }
      }
   }else if(e.getSource()==Agregar){
        AgragarCampo();
   }
  }
public Tabla<T> getNuevaTabla(){
   return NuevaTabla;
  }
public void setNuevaTabla(Tabla<T> recibido){
   this.NuevaTabla=recibido;
  }
public Informacion<T> Columna(T columnas){
       Informacion<T> filas=null;
       Informacion<T> N=new Informacion<T>(columnas);
       N.setSiguiente(filas);
       filas=N;
       tabla.getText();
       return filas;
      }
/**
 * RECIBE UN NOMBRE Y LOS DATOS QUE TENDRA LA TABLA POSTERIOMENTE CREARLA
 * @param Nombre
 * @param c
 */
public void SiguienteTabla(T Nombre,T c){
       try{
         Tabla<T> var=NuevaTabla;
         while((var.getSiguienteTabla())!=null){
             var=var.getSiguienteTabla();
          }
         Tabla<T> N=new Tabla<T>(Nombre);
         var.setSiguienteTabla(N);
         N.setSiguienteFila(Columna(c));
         var=null;
         tabla.getText();
         }catch(Exception e){JOptionPane.showMessageDialog(null,"error!");   
          }
     }      
}