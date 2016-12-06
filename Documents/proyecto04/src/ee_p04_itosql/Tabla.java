package ee_p04_itosql;
/**
* @author EMMANUEL
*/
public class Tabla<T> {
    private T Nombre;
    private Tabla<T> SiguienteTabla;
    private Informacion<T> SiguienteFila;
    public Tabla(T Nombre){
        this.Nombre=Nombre;
        this.SiguienteTabla=null;
        this.SiguienteFila=null;
    }
    public T getNombre() {
        return Nombre;
    }
    public void setNombre(T dato) {
        Nombre = dato;
    }
    public Tabla<T> getSiguienteTabla() {
        return SiguienteTabla;
    }
    public void setSiguienteTabla(Tabla<T> SiguienteTabla) {
        this.SiguienteTabla = SiguienteTabla;
    }
    public Informacion<T> getSiguienteFila() {
        return SiguienteFila;
    }
    public void setSiguienteFila(Informacion<T> SiguienteFila) {
        this.SiguienteFila = SiguienteFila;
    }
    public String ToString(){
       String r;
       try{
          r="("+Nombre+")--->["+SiguienteFila.  getDato()+"]";
       }catch(Exception e){
          r="("+Nombre+")";
        }
       return r;
    }
}
