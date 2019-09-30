package model.data_structures;

public interface IMaxHeapCP <T>
{
	/**
	 * Retorna el número de elementos del heap
	 * @return el número de elementos del heap
	 */
	public int darNumeroElementos();
	
	/**
	 * Añade un elemento al heap
	 * @param item elemento a añadir
	 */
	public void agregar(T item);
	
	/**
	 * Saca el maximo  elemento añadido al heap
	 * @return el maximo elemento añadido al heap
	 */
	public T sacarMax();
	
	/**
	 * Retorna el maximo  elemento añadido al heap
	 * @return el maximo elemento añadido al heap
	 */
	public T darMax();
	
	/**
	 * Retorna si esta vacio el arreglo
	 * @return true si el arreglo esta vacio
	 */
	public boolean estaVacia();
}