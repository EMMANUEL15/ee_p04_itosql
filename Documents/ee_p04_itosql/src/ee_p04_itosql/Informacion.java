package ee_p04_itosql;
public class Informacion<T> {
	private T Dato;
	private Informacion<T> Siguiente;
	private Informacion<T> Anterior;
public Informacion(T dato){
	this.Dato=dato;
	this.Siguiente=null;
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
public Informacion<T> getAnterior() {
	return Anterior;
}
public void setAnterior(Informacion<T> Anterior) {
	this.Anterior = Anterior;
}
}