package model.data_structures;

/**
 * Tomado de Algorithms 4th edition by Robert Sedgewick and Kevin Wayne (2011)
 * Consultado el 20/09/19
 * Disponible en http://www.albertstam.com/Algorithms.pdf
 */
public class MaxColaCP <T extends Comparable<T>> implements IMaxColaCP<T>, Cloneable
{
	/**
	 * Primer nodo de la lista
	 */
	private Node<T> primero;

	/**
	 * Último nodo de la lista
	 */
	private Node<T> ultimo;

	/**
	 * Cantidad de elementos de la lista
	 */
	private int cantidadElementos;

	public MaxColaCP(){
		cantidadElementos = 0;
	}

	public boolean estaVacia(){
		return cantidadElementos == 0;
	}

	public int darNumeroElementos(){
		return cantidadElementos;
	}

	public void agregar(T elemento){
		if(estaVacia()){
			Node<T> agregar = new Node<T>(elemento);
			primero = agregar;
			ultimo = primero;
		}
		else if(primero.darElemento().compareTo(elemento) < 0){
			Node<T> agregar = new Node<T>(elemento);
			agregar.cambiarSiguiente(primero);
			primero = agregar;
		}
		else if(ultimo.darElemento().compareTo(elemento) > 0){
			Node<T> ultimoViejo = ultimo;
			Node<T> agregar = new Node<T>(elemento);
			ultimoViejo.cambiarSiguiente(agregar);
			ultimo = agregar;
		}
		else{
			Node<T> actual = primero;
			for(int i = 0; i < cantidadElementos - 1; i++){
				if(((Comparable<T>) actual.darSiguiente().darElemento()).compareTo(elemento) > 0){
					actual = actual.darSiguiente();
				}
				else{
					Node<T> agregar = new Node<T>(elemento);
					agregar.cambiarSiguiente(actual.darSiguiente());
					actual.cambiarSiguiente(agregar);
					break;
				}
			}
		}
		cantidadElementos++;
	}

	public T sacarMax(){
		if(estaVacia()){
			return null;
		}
		T elim = primero.darElemento();
		primero = primero.darSiguiente();
		cantidadElementos--;
		if(estaVacia()){
			ultimo = null;
		}
		return elim;
	}

	public T darMax(){
		if(estaVacia()){
			return null;
		}
		
		return primero.darElemento();
		
		
	}
}