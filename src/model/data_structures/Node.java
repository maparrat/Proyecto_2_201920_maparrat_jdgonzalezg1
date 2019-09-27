package model.data_structures;

/**
 * Clase que representa un nodo en la lista
 * @param <T> tipo de dato a guardar
 */
public class Node<T> implements INode<T>
{
	/**
	 * Dato guardado en el nodo
	 */
	private T dato;
	
	/**
	 * Siguiente nodo
	 */
	private Node siguiente;

	/**
	 * Contructor de la clase
	 * post: los atributos quedaron inicializados como nulos
	 */
	public Node()
	{
		dato = null; 
		siguiente = null;
	}

	public void asignarDato(T pDato) 
	{
		dato = pDato;
	}
	
	public void asignarSiguiente(Node pSiguiente) 
	{
		siguiente = pSiguiente;
	}
	
	public Node darSiguente()
	{
		return siguiente;
	}
	
	public T darDato()
	{
		return dato;
	}
}