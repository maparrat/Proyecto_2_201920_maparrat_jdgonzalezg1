package model.data_structures;

public interface IMaxColaCP <T>
{
	/**
	 * Retorna el número de elementos de la cola
	 * @return el número de elementos de la cola
	 */
	public int darNumeroElementos();
	
	/**
	 * Añade un elemento a la cola
	 * @param item elemento a añadir
	 */
	public void agregar(T item);
	
	/**
	 * Saca el maximo  elemento añadido a la cola
	 * @return el maximo elemento añadido a la cola
	 */
	public T sacarMax();
	
	/**
	 * Retorna el maximo  elemento añadido a la cola
	 * @return el maximo elemento añadido a la cola
	 */
	public T darMax();
	
	/**
	 * Retorna si esta vacio el arreglo
	 * @return true si el arreglo esta vacio
	 */
	public boolean estaVacia();
}