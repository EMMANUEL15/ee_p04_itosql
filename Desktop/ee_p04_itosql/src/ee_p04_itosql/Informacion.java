package ee_p04_itosql;
public class Informacion<T> {
	private T Dato;
	private Informacion<T> Siguiente;
public Informacion(T dato){
	this.Dato=dato;
	this.Siguiente=null;
}
public T getDato() {
	return Dato;
}
public void setDato(T dato) {
	Dato = dato;
}
public Informacion<T> getSiguiente() {
	return Siguiente;
}
public void setSiguiente(Informacion<T> Siguiente) {
	this.Siguiente = Siguiente;
}
}