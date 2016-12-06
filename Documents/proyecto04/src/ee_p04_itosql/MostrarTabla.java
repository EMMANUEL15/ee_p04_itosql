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
  public Tabla<T> NuevaTabla; String id;
  DefaultTableModel table=new DefaultTableModel();
  private JTable registro=new JTable();
  private JScrollPane Jsp=new JScrollPane();
  public MostrarTabla(){
      super(" TABLAS");
      setSize(900,500);
      Ventana1.setLayout(null);
              //icono
      //Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("mysql.png"));
      //setIconImage(icon);
      setVisible(true);
              //primer jlabel
      Ventana1.add(Tablas);
      Tablas.setBounds(100,20,100,30);
              //segundo jlabel
      Ventana1.add(Ordenar);
      Ordenar.setBounds(60,70,150,30);
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
      this.id="";
  }
  public String getId(){return id;}
  public void setId(String ide){this.id=ide;}
  public void actionPerformed(ActionEvent e){
      try{
      if(e.getSource()==Mostrar){
         Tabla<T> var=NuevaTabla; int i=0;
         while(var!=null){
           if(String.valueOf(var.getNombre()).equals(String.valueOf(ListaTablas.getSelectedItem()))){
              if(id.equals(String.valueOf(var.getNombre()))){ 
                  MostrarInformacion(var.getSiguienteFila());
              }else{
                  String d=(String.valueOf(listaColumanas.getSelectedItem()));
                  String m=(String.valueOf(TipOrdenacion.getSelectedItem()));
                  this.dispose();
                  MostrarTabla<T> V=new MostrarTabla<T>();
                  V.setLocationRelativeTo(null);
                  V.llenarfichero(NuevaTabla);
                  V.setNuevaTabla(NuevaTabla);
                  V.setVisible(true);
                  V.setExtendedState(JFrame.MAXIMIZED_BOTH);
                  V.creaColumnas(var.getSiguienteFila(),String.valueOf(ListaTablas.getSelectedItem()));
                  switch (m) {
                      case "Burbuja":
                          V.Burbuja(var.getSiguienteFila().getSiguiente(), V.eleccion(String.valueOf(var.getSiguienteFila().getDato()),d));
                          break;
                      case "Burbuja con señal":
                              BurbujaConSeñal(var.getSiguienteFila().getSiguiente(), V.eleccion(String.valueOf(var.getSiguienteFila().getDato()),d));
                          break;
                      case "Shaker Sort":
                              ShakerSort(var.getSiguienteFila().getSiguiente(), V.eleccion(String.valueOf(var.getSiguienteFila().getDato()),d));
                          break;
                      case "Inserción directa":
                            InserciónDirecta(var.getSiguienteFila().getSiguiente(), V.eleccion(String.valueOf(var.getSiguienteFila().getDato()),d));
                          break;
                      case "Selección Directa":
                            SeleccionDirecta(var.getSiguienteFila().getSiguiente(), V.eleccion(String.valueOf(var.getSiguienteFila().getDato()),d));
                          break;
                      default:
                          V.Burbuja(var.getSiguienteFila().getSiguiente(), V.eleccion(String.valueOf(var.getSiguienteFila().getDato()),d));
                      break;
                  }
                  V.MostrarInformacion(var.getSiguienteFila());
                  this.id=String.valueOf(listaColumanas.getSelectedItem());
              }
              break;
              }
           var=var.getSiguienteTabla();
         }
      }
    }catch(Exception x){}
  }
  public int eleccion(String d,String e){
      String arreglo[]=d.split("\\|"); int r=0;
      for(int i=0;i<arreglo.length;i++){
          if(arreglo[i].equals(e)){
              r=i;break;
          }
        }
      return r;
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
   public void Burbuja(Informacion<T> inf,int p){
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
  }
  public void BurbujaConSeñal(Informacion<T> inf,int p){
     Informacion<T> var1=inf; boolean sts=true;
     while(var1.getSiguiente()!=null){
        String arreglo1[]=String.valueOf(var1.getDato()).split("\\|"); String arreglo2[]=String.valueOf(var1.getSiguiente().getDato()).split("\\|");
		  if(arreglo1[p].compareTo(arreglo2[p])>0){
			T aux=var1.getDato();
          var1.setDato(var1.getSiguiente().getDato());
          var1.getSiguiente().setDato(aux); 
			sts=false;
		   }
        var1=var1.getSiguiente();
      }
	   if(sts==false){BurbujaConSeñal(inf,p);}
      }
 public void ShakerSort(Informacion<T> inf ,int p){
	   Informacion<T> var1=inf;  Informacion<T> aux=null;  boolean sts=true; 
     while(var1!=null){
		  if(var1.getSiguiente()!=null){
		   String arreglo1[]=String.valueOf(var1.getDato()).split("\\|"); String arreglo2[]=String.valueOf(var1.getSiguiente().getDato()).split("\\|");
		     if(arreglo1[p].compareTo(arreglo2[p])>0){
			 T z=var1.getDato();
           var1.setDato(var1.getSiguiente().getDato());
           var1.getSiguiente().setDato(z); 
			 sts=false;
		   }
		  }
		  aux=var1;
		  var1=var1.getSiguiente();
		}
	   while(aux.getAnterior()!=null){
	      String arreglo1[]=String.valueOf(aux.getDato()).split("\\|"); String arreglo2[]=String.valueOf(aux.getAnterior().getDato()).split("\\|");
		  if(arreglo1[p].compareTo(arreglo2[p])<0){
			T z=aux.getDato();
          aux.setDato(aux.getAnterior().getDato());
          aux.getAnterior().setDato(z); 
			sts=false;
		  }
		  var1=aux;
		  aux=aux.getAnterior();
		}
	   if(sts==false){ShakerSort(var1.getSiguiente(),p);}
      }
 public void InserciónDirecta(Informacion<T> inf,int p){
	   Informacion<T> var1=inf;
     while(var1.getSiguiente()!=null){
         String arreglo1[]=String.valueOf(var1.getDato()).split("\\|"); String arreglo2[]=String.valueOf(var1.getSiguiente().getDato()).split("\\|");
		  if(arreglo1[p].compareTo(arreglo2[p])>0){
			T aux=var1.getDato();
          var1.setDato(var1.getSiguiente().getDato());
          var1.getSiguiente().setDato(aux); 
			while(var1.getAnterior()!=null){
		  	   String arreglo3[]=String.valueOf(var1.getDato()).split("\\|"); String arreglo4[]=String.valueOf(var1.getAnterior().getDato()).split("\\|");
		 	 	if(arreglo3[p].compareTo(arreglo4[p])<0){
				T z=var1.getDato();
         		var1.setDato(var1.getAnterior().getDato());
         		var1.getAnterior().setDato(z); 
		 	  }
		 	  var1=var1.getAnterior();
			}
		   }
        var1=var1.getSiguiente();
      }
	}
 public void SeleccionDirecta(Informacion<T> inf, int p){
	  Informacion<T> informacion=inf;
    while(informacion.getSiguiente()!=null){
		 T aux=informacion.getDato();
		 T tem=informacion.getDato();
		 Informacion<T> Var2=null;
		 Informacion<T> buscar=informacion.getSiguiente();
       while(buscar!=null){
         String arreglo3[]=String.valueOf(tem).split("\\|"); String arreglo4[]=String.valueOf(buscar.getDato()).split("\\|");
         if(arreglo3[p].compareTo(arreglo4[p])>0){
			   Var2=buscar; 
			   tem=buscar.getDato();
			 }
		   if(buscar.getSiguiente()==null){
			try{Var2.setDato(aux);}catch(Exception x){}
			informacion.setDato(tem);
			}
       	buscar=buscar.getSiguiente();
           }
       informacion=informacion.getSiguiente();
                   }
	}
   public void creaColumnas(Informacion<T> tabla,String t){
       JLabel tab = new JLabel( "Nombre de la tabla:   "+t);
       Ventana1.add(tab);
       tab.setBounds(60,120,300,30);
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
  
}
